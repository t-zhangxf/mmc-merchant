package com.merchant.web.common.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PageUtils {
    private int   restNum;//余数
    private int   avgNum;//平均数
    public PageUtils(int totalCounts, int pageSize){
         this.restNum=totalCounts%pageSize;
         this.avgNum=totalCounts/pageSize;
         if(restNum>0){
            avgNum=avgNum+1;
         }
    }
}
