package com.merchant.web.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.merchant.web.common.entity.request.TradePageQueryRequest;
import com.merchant.web.common.entity.response.TradePageQueryResponse;
import com.merchant.web.common.enums.PayStatusEnum;
import com.merchant.web.common.enums.RefundStatusEnum;
import com.merchant.web.common.utils.EmptyUtil;
import com.merchant.web.common.utils.PageUtils;
import com.merchant.web.integration.feignClient.TradeServerClient;
import com.merchant.web.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    TradeServerClient tradeServerClient;

    @Override
    public void addRowsContent(TradePageQueryRequest request, XSSFWorkbook wb) {
        if(request.getPageNum()==null){
            request.setPageNum(1);
        }
        if(request.getPageSize()==null){
            request.setPageSize(50000);
        }
        TradePageQueryResponse response =tradeServerClient.pageQuery(request);
        int rowNum = 2;
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row3 = sheet.getRow(rowNum);
        XSSFCellStyle xssfCellStyle = row3.getCell(0).getCellStyle();
        log.info("response[{}]",JSON.toJSONString(response));
        if(response!=null){
            if("0000".equals(response.getBizCode())){
                int totalCount = response.getTotalElements();
                if (totalCount > request.getPageSize()) {//大于50000
                    PageUtils pageUtils = new PageUtils(totalCount, request.getPageSize());//每次查询5000条数据
                    for (int i = 1; i <= pageUtils.getAvgNum(); i++) {
                        XSSFSheet sheet1 = wb.cloneSheet(0);//复制sheet
                        request.setPageNum(i);
                        TradePageQueryResponse response1 = tradeServerClient.pageQuery(request);
                        log.info("response1[{}]",JSON.toJSONString(response1));
                        addRowsCount(sheet1,response1.getData().getPayOrderList(), rowNum);
                        setStyle(sheet1, xssfCellStyle, 2);
                    }
                    wb.removeSheetAt(0);//去除第一sheet
                }else {
                    addRowsCount(sheet, response.getData().getPayOrderList(), rowNum);
                }

            }
        }
    }
    void addRowsCount(XSSFSheet sheet, List<TradePageQueryResponse.data.payOrderList> paymentOrders, int rowNum) {
        if (paymentOrders != null && !paymentOrders.isEmpty()) {
            for (TradePageQueryResponse.data.payOrderList paymentOrder : paymentOrders) {
                log.info("paymentOrder[{}]",JSON.toJSONString(paymentOrder));
                XSSFRow row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(paymentOrder.getCreateTimeUtc());//订单创建时间
                row1.createCell(1).setCellValue(paymentOrder.getOrderId());//商户订单号
                row1.createCell(2).setCellValue(paymentOrder.getTradeNo());//交易订单号
                row1.createCell(3).setCellValue(paymentOrder.getProductInfo());//支付产品描述
                row1.createCell(4).setCellValue(paymentOrder.getCustId());//商户用户id
                row1.createCell(5).setCellValue(paymentOrder.getAmount().toString());//支付金额
                row1.createCell(6).setCellValue(PayStatusEnum.getByStatus(paymentOrder.getPayStatus()).getDesc());//支付状态
                row1.createCell(7).setCellValue(paymentOrder.getPayOrderNo());//支付订单号
                row1.createCell(8).setCellValue(paymentOrder.getUpdateTimeUtc());//支付订单更新时间
                row1.createCell(9).setCellValue(paymentOrder.getChannelTxnNo());//渠道订单号
                row1.createCell(10).setCellValue(paymentOrder.getRemark());//支付备注
                row1.createCell(11).setCellValue(null !=paymentOrder.getRefundStatus()?null:RefundStatusEnum.getByStatus(paymentOrder.getRefundStatus()).getDesc());//退款状态
                row1.createCell(12).setCellValue(paymentOrder.getRefundId());//退款编号
                row1.createCell(13).setCellValue(paymentOrder.getRefundCreateTimeUtc());//退款单创建时间
                row1.createCell(14).setCellValue(paymentOrder.getRefundUpdateTimeUtc());//退款单更新时间
                row1.createCell(15).setCellValue(null==paymentOrder.getRefundAmount()?null:paymentOrder.getRefundAmount().toString());//退款金额
                row1.createCell(16).setCellValue(paymentOrder.getComment());//退款原因
                rowNum++;
            }
        }
    }
    public void setStyle(XSSFSheet sheet, XSSFCellStyle xssfCellStyle, int rowNum) {
        System.out.println(sheet.getLastRowNum());
        for (int i = rowNum; i <= sheet.getLastRowNum(); i++) {
            for (Cell cell : sheet.getRow(i)) {
                cell.setCellStyle(xssfCellStyle);
            }
        }
    }
}
