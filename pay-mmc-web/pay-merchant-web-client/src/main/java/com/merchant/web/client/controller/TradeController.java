package com.merchant.web.client.controller;

import com.alibaba.fastjson.JSON;
import com.merchant.web.client.secutity.UserHelper;
import com.merchant.web.common.entity.request.TradeDetailQueryRequest;
import com.merchant.web.common.entity.request.TradePageQueryRequest;
import com.merchant.web.common.entity.response.TradeDetailQueryResponse;
import com.merchant.web.common.entity.response.TradePageQueryResponse;
import com.merchant.web.common.entity.result.BizResult;
import com.merchant.web.common.enums.PayStatusEnum;
import com.merchant.web.common.enums.SystemEnum;
import com.merchant.web.common.utils.AssertHelperUtil;
import com.merchant.web.common.utils.EmptyUtil;
import com.merchant.web.integration.feignClient.TradeServerClient;
import com.merchant.web.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class TradeController {

    @Autowired
    TradeServerClient tradeServerClient;

    @Autowired
    ExcelService excelService;

    @Autowired
    UserHelper userHelper;

    private String isSuccess = "0000";

    private String noResult = "8029";

    /**
     * 分页查询交易列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/payOrder/list", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8;"})
    public BizResult pageQuery (@RequestBody TradePageQueryRequest request, HttpServletRequest httpServletRequest) {
        log.info("pageQuery request{}",JSON.toJSONString(request));
        String merchantId=userHelper.getCurrentMerchantId(httpServletRequest);
        request.setMerchantId(merchantId);
        AssertHelperUtil.hasText(request.getMerchantId(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),"【merchantId】 cannot be empty");
        AssertHelperUtil.isTrue(null != request.getPageNum() && request.getPageNum() > 0,
                SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "【pageNum】 Cannot be empty");
        AssertHelperUtil.isTrue(null != request.getPageSize() && request.getPageSize() > 0,
                SystemEnum.SYSTEM_PARAMETER_NULL.getCode(), "【pageSize】 Cannot be empty");
        if(request.getStatus()!=null){
            AssertHelperUtil.isTrue(PayStatusEnum.hasStatus(request.getStatus()), SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),
                    "【status】 Illegal");
        }
        TradePageQueryResponse response=tradeServerClient.pageQuery(request);
        log.info("pageQuery response{}",JSON.toJSONString(response));
        if(isSuccess.equals(response.getBizCode())){
            response.setBizCode("200");
            List<TradePageQueryResponse.data.payOrderList> payOrderList=response.getData().getPayOrderList();
            List<TradePageQueryResponse.data.rows> rows=new ArrayList<>();
            if(EmptyUtil.isNotEmpty(payOrderList)){
                for(TradePageQueryResponse.data.payOrderList list:payOrderList){
                    TradePageQueryResponse.data.rows rsp=TradePageQueryResponse.data.rows.builder()
                            .amount(list.getAmount()).createTimeUtc(list.getCreateTimeUtc())
                            .orderId(list.getOrderId()).payStatus(list.getPayStatus())
                            .refundStatus(list.getRefundStatus()).tradeNo(list.getTradeNo()).build();
                    rows.add(rsp);
                }
            }
            return  BizResult.buildBizResult(response.getPageNum().toString(),response.getPageSize().toString(),response.getTotalElements().toString(),response.getBizCode()
                    ,response.getMessage(),rows);
        }
        if(noResult.equals(response.getBizCode())){
            return BizResult.buildBizResult("200","no result");
        }
        return BizResult.buildBizResult(response.getBizCode(),response.getMessage());
    }

    /**
     * 查询交易详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/payOrder/detail", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8;"})
    public TradeDetailQueryResponse orderDetail ( @RequestBody TradeDetailQueryRequest request,HttpServletRequest httpServletRequest) {
        log.info("pageQuery request{}",JSON.toJSONString(request));
        String merchantId=userHelper.getCurrentMerchantId(httpServletRequest);
        request.setMerchantId(merchantId);
        AssertHelperUtil.hasText(request.getMerchantId(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),"【merchantId】 cannot be empty");
        AssertHelperUtil.hasText(request.getOrderId(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),"【orderId】 cannot be empty");
        TradeDetailQueryResponse response=tradeServerClient.orderDetail(request);
        log.info("pageQuery response{}",JSON.toJSONString(response));
        if(isSuccess.equals(response.getBizCode()) || noResult.equals(response.getBizCode())){
            response.setBizCode("200");
        }
        return response;
    }

    /**
     *交易列表下载
     * @param request
     * @return
     */
    @RequestMapping(value = "/payOrder/list/down", method = {RequestMethod.GET, RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public ModelAndView downloadOrder (TradePageQueryRequest request, HttpServletResponse response,HttpServletRequest httpServletRequest) {
        String merchantId=userHelper.getCurrentMerchantId(httpServletRequest);
        request.setMerchantId(merchantId);
        AssertHelperUtil.hasText(request.getMerchantId(),SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),"【merchantId】 cannot be empty");
        if(request.getStatus()!=null){
            AssertHelperUtil.isTrue(PayStatusEnum.hasStatus(request.getStatus()), SystemEnum.SYSTEM_PARAMETER_NULL.getCode(),
                    "【status】 Illegal");
        }
        InputStream fis = null;
        try {
            fis=new DefaultResourceLoader().getResource("classpath:/templates/order_list.xlsx").getInputStream();
            Workbook workbook = WorkbookFactory.create(fis);
            XSSFWorkbook wb = (XSSFWorkbook) workbook;
            excelService.addRowsContent(request,wb);
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=payment_order_history.xlsx");//默认Excel名称
            response.flushBuffer();
            workbook.write(response.getOutputStream());
            response.getOutputStream().close();
        }catch (Exception e){
            log.error("Download Order Error,message{}",e.getMessage());
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    log.info("e:" + e.getMessage());
                }

            }
        }
        return new ModelAndView();
    }

}
