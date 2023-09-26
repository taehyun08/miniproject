package com.model2.mvc.service.user;

import com.model2.mvc.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, String> {

    Page<UserEntity> findByUserIdContaining(String userId, Pageable pageable);

}