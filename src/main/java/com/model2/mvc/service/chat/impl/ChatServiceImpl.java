package com.model2.mvc.service.chat.impl;

import com.model2.mvc.entity.ChatEntity;
import com.model2.mvc.entity.UserEntity;
import com.model2.mvc.mapper.ChatMapper;
import com.model2.mvc.service.chat.ChatRepository;
import com.model2.mvc.service.chat.ChatService;
import com.model2.mvc.service.domain.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;


    @Override
    public List<Object> getChatList(String sender, String receiver) throws Exception {
        Sort sort = Sort.by("reg_date");
        Pageable pageable = PageRequest.of(1, 10, sort);
        UserEntity senderUserEntity = UserEntity.builder().userId(sender).build();
        UserEntity receiverUserEntity = UserEntity.builder().userId(receiver).build();
        Page<ChatEntity> page = chatRepository.findByReceiverOrSenderContaining(senderUserEntity, receiverUserEntity, pageable);

        List<Chat> chatList = page.map(chatMapper::chatEntityToChat).toList();

        // 리턴 값 해결해야함.
        return null;
    }
}
