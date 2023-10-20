package com.model2.mvc.mapper;

import com.model2.mvc.entity.ChatEntity;
import com.model2.mvc.service.domain.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    Chat chatEntityToChat(ChatEntity entity);

    @Mapping(target = "chatId", ignore = true)
    ChatEntity chatToChatEntity(Chat chat);
}
