package com.merchant.web.client.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "【健康检查】")
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "merchant client is working";
    }
}
