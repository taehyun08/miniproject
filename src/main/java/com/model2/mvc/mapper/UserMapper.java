package com.model2.mvc.mapper;

import com.model2.mvc.entity.ProductEntity;
import com.model2.mvc.entity.UserEntity;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userEntityToUser(UserEntity entity);

    UserEntity userToUserEntity(User user);

}
