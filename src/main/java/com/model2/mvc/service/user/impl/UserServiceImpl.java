package com.model2.mvc.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.UserRepository;;

@NoArgsConstructor
@Service("userServiceImpl")
public class UserServiceImpl implements UserService{

    ///Field
    private UserRepository userRepository;
    ///Constructor
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    ///Method
    public void addUser(User user) throws Exception {
        //userDao.addUser(user);
    }

    public User getUser(String userId) throws Exception {
//        return userDao.getUser(userId);
        return null;
    }

    public Map<String , Object > getUserList(Search search) throws Exception {
//        List<User> list= userDao.getUserList(search);
//        int totalCount = userDao.getTotalCount(search);

        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("list", list );
//        map.put("totalCount", totalCount);

        return map;
    }

    public void updateUser(User user) throws Exception {
//        userDao.updateUser(user);
    }

    public boolean checkDuplication(String userId) throws Exception {
        boolean result=true;
//        User user=userDao.getUser(userId);
//        if(user != null) {
//            result=false;
//        }
        return result;
    }
}