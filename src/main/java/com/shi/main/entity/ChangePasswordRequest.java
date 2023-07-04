package com.shi.main.entity;

import lombok.Data;

/**
 * @author: 扑腾的江鱼
 * @description: TODO 类描述
 * @create: 2023/06/08 18:52
 **/
@Data
public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
}
