package com.model2.mvc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Entity
@Table(name= "product")
@Builder
@ToString(exclude = "purchaseEntities")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prodNo;

    private String prodName;

    private String prodDetail;

    private Date manuDate;

    private int price;

    private String fileName;

    private int views;

    private int stock;

    @OneToMany(mappedBy = "productEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseEntity> purchaseEntities;



}
