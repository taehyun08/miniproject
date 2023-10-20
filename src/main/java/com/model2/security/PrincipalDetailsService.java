package com.model2.security;

import com.model2.mvc.mapper.UserMapper;
import com.model2.mvc.service.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final HttpSession session;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("실행됨");

        com.model2.mvc.service.domain.User user = userRepository.findById(userId).map(userMapper::userEntityToUser).orElseThrow(()-> new UsernameNotFoundException("없는 회원"));
        session.setAttribute("user", user);
        return User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

}
