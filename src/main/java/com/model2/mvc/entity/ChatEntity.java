package com.model2.mvc.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chat")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;

    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity receiver;
}
