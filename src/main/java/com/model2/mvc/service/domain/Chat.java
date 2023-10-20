package com.model2.mvc.service.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Chat {
    private String sender;
    private String receiver;
    private String message;
}
