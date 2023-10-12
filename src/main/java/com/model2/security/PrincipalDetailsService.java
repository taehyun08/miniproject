package com.model2.security;

import com.model2.mvc.entity.UserEntity;
import com.model2.mvc.service.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("실행됨");
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        UserEntity userEntity = userEntityOptional.orElseThrow(()-> new UsernameNotFoundException("없는 회원"));
        session.setAttribute("user", userEntity);
        return new PrincipalDetails(userEntity);
    }

}
