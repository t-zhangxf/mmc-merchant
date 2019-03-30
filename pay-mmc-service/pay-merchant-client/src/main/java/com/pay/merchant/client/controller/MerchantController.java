package com.pay.merchant.client.controller;

import com.pay.common.entity.result.BizResult;
import com.pay.common.entity.vo.MerchantVo;
import com.pay.merchant.manage.MerchantManage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant")
@Api(description = "【商户服务】")
@Slf4j
public class MerchantController {

    @Autowired
    MerchantManage merchantManage;


    @ApiOperation(value = "根据merchantNo获取商户信息", notes = "根据merchantNo获取商户信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/list/v1", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public BizResult merchantList(@RequestBody @ApiParam(name="商户对象",value="传入json格式",required=true) MerchantVo merchantVo){
       log.info("merchantList request param:"+merchantVo.toString());
       return merchantManage.getMerchantListByMerchantNo(merchantVo);
    }
    @ApiOperation(value = "根据merchantNo获取商户信息", notes = "根据merchantNo获取商户信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/list/v12", method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8;"})
    public BizResult merchantList3(@RequestBody @ApiParam(name="商户对象",value="传入json格式",required=true) MerchantVo merchantVo){
        log.info("merchantList request param:"+merchantVo.toString());
        return merchantManage.getMerchantAndMemberDetail(merchantVo.getMerchantNo());
    }
}
