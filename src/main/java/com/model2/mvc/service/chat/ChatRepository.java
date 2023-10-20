package com.model2.mvc.service.chat;

import com.model2.mvc.entity.ChatEntity;
import com.model2.mvc.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {
    Page<ChatEntity> findByReceiverOrSenderContaining(UserEntity receiver, UserEntity sender, Pageable pageable);
}
