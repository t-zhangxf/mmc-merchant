package com.pay.merchant.integration.service;
import com.pay.merchant.integration.resp.MerchantDetailResponse;
import com.pay.merchant.integration.req.PageQueryRequest;
import com.pay.merchant.integration.resp.MerchantAndMemberDetailResponse;
import com.pay.merchant.integration.resp.PageQueryResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * 调用商户后台服务
 */
@FeignClient(url = "${pay.member.service.url}", name = "pay-member-service")
public interface MerchantFeignService {

	/**
	 * 查看商户会员详情
	 *
	 * @param merchantNo
	 * @return Result
	 */
	@RequestMapping(value = "/omc/merchantMember/{merchantNo}", method = RequestMethod.GET)
	MerchantAndMemberDetailResponse getMerchantAndMemberDetail(@PathVariable("merchantNo")String merchantNo);


	/**
	 * 分页查询商户列表
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/omc/merchant/pageQuery", method = RequestMethod.POST)
    PageQueryResponse pageQuery(PageQueryRequest request);
    /**
     * 查询商户详细接口
     * @param merchantNo
     * @return
     */
    @RequestMapping(value = "/merchant/detail/{merchantNo}", method = RequestMethod.GET)
    MerchantDetailResponse merchantDetailByMemberNo(@PathVariable("merchantNo")String merchantNo);
}
