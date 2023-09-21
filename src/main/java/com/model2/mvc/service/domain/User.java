package com.model2.mvc.service.domain;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userId;
    private String userName;
    private String password;
    private String role;
    private String ssn;
    private String phone;
    private String addr;
    private String email;
    private Date regDate;

}