package com.merchant.web.Do;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionDo {
   private Integer Id;
   private String name;//权限名称
   private String permitId;//权限id
   private String sort;//排序
   private String enName;//英文
}
