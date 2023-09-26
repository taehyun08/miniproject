package com.model2.mvc.service.user.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.entity.UserEntity;
import com.model2.mvc.mapper.UserMapper;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserRepository;
import com.model2.mvc.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

;

@RequiredArgsConstructor
@Service("userServiceImpl")
public class UserServiceImpl implements UserService{

    ///Field
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    ///Method
    public void addUser(User user) throws Exception {
        userRepository.save(userMapper.userToUserEntity(user));
    }

    public User getUser(String userId) throws Exception {
        return userRepository.findById(userId).map(userMapper::userEntityToUser).orElse(null);
    }

    public Map<String , Object > getUserList(Search search) throws Exception {
        Sort sort = Sort.by(search.getOrderBy());
        Pageable pageable = PageRequest.of(search.getCurrentPage(), search.getPageUnit(), sort);
        Page<UserEntity> page;
        if(search.getSearchKeyword() == null || search.getSearchKeyword().isEmpty()){
            page = userRepository.findAll(pageable);
        }else{
            page = userRepository.findByUserIdContaining(search.getSearchKeyword(), pageable);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", page.map(userMapper::userEntityToUser).toList());
        map.put("totalCount", page.getTotalPages());
        return map;
    }

    public void updateUser(User user) throws Exception {
        userRepository.save(userMapper.userToUserEntity(user));
    }

    public boolean checkDuplication(String userId) throws Exception {
        boolean result=true;
        User user = userRepository.findById(userId).map(userMapper::userEntityToUser).orElse(null);
        if(user != null) {
            result=false;
        }
        return result;
    }
}