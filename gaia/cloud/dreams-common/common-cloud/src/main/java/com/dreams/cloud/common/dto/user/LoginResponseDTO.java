package com.dreams.cloud.common.dto.user;

import java.util.Date;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String userId;
    private String account;
    private String phone;
    private String name;
    private Integer gender;
    private String accessToken;
    private Long expires;
    private Date loginTime;
}
