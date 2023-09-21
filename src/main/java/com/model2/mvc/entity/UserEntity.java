package com.model2.mvc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name= "user")
@Builder
@ToString(exclude = "purchaseEntities")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private String userId;

    @Column(length = 50, nullable = false)
    private String userName;

    @Column(length = 10, nullable = false)
    private String password;

    private String role;

    private String ssn;

    private String cellPhone;

    private String addr;

    private String email;

    private Date regDate;

    @OneToMany(mappedBy = "userEntity",fetch = FetchType.LAZY)
    private List<PurchaseEntity> purchaseEntities;

}
