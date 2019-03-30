package com.pay.merchant.client.controller;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@ApiModel(value="user对象",description="用户对象user")
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="用户名",name="username",example="xingguo")
    private String username;
    @ApiModelProperty(value="状态",name="state",required=true)
    private Integer state;
    private String password;
    private String nickName;
    private Integer isDeleted;
    @ApiModelProperty(value="id数组",hidden=true)
    private String[] ids;
    private List<String> idList;

}
