package com.merchant.web.integration.feignClient;
import com.merchant.web.common.entity.request.TradeDetailQueryRequest;
import com.merchant.web.common.entity.request.TradePageQueryRequest;
import com.merchant.web.common.entity.response.TradeDetailQueryResponse;
import com.merchant.web.common.entity.response.TradePageQueryResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alibaba.fastjson.JSONObject;

@FeignClient(url = "${aggregate.pay.core.service.url}", name = "aggregate-pay-core-service")
public interface TradeServerClient {
    /**
     * 分页查询
     *
     * @param request
     * @return Result
     */
    @RequestMapping(value = "/query-service/queryPayOrderList", method = RequestMethod.POST)
    TradePageQueryResponse pageQuery(@RequestBody TradePageQueryRequest request);

    /**
     * 详情查询
     *
     * @param request
     * @return Result
     */
    @RequestMapping(value = "/query-service/queryPayOrderDetails", method = RequestMethod.POST)
    TradeDetailQueryResponse orderDetail(@RequestBody TradeDetailQueryRequest request);
}
