package com.example.jpaTest;

import com.model2.mvc.common.Search;
import com.model2.mvc.entity.UserEntity;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserRepository;
import com.model2.mvc.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class UserJpaTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void addUserTest(){
        IntStream.rangeClosed(1,30).forEach(i ->{
            UserEntity user = UserEntity.builder()
                    .userId("user" + i)
                    .userName("scott")
                    .password(""+i+i+i+i)
                    .role("user")
                    .build();
            userRepository.save(user);
        });
        UserEntity user = UserEntity.builder()
                .userId("admin")
                .userName("adm")
                .password("1111")
                .role("admin")
                .build();
        userRepository.save(user);
    }

    @Test
    public void getUserListTest() throws Exception{
        Search search = Search.builder()
                        .orderBy("userId")
                                .currentPage(1)
                                        .pageUnit(5)
                                                .startRowNum(1)
                                                        .endRowNum(5)
                                                                .build();
        System.out.println(userService.getUserList(search));
    }
}
