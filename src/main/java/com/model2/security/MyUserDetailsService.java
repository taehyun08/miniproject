package com.model2.security;

import com.model2.mvc.entity.UserEntity;
import com.model2.mvc.service.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("실행됨");
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        UserEntity userEntity = userEntityOptional.orElseThrow(()-> new UsernameNotFoundException("없는 회원"));
        return User.builder()
                   .username(userEntity.getUserId())
                   .password(userEntity.getPassword())
                   .roles(userEntity.getRole())
                   .build();
    }

}
