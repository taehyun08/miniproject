package com.model2.mvc.service.chat;

import java.util.List;

public interface ChatService {
    public List<Object> getChatList(String sender, String receiver) throws Exception;

}
