package com.model2.mvc.service.domain;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Product {

    private String fileName;
    private String manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private int prodNo;
    private Date regDate;
    private String proTranCode;
    private int views;
    private int stock;

}