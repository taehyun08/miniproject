package com.model2.mvc.service.user.impl;

import com.model2.mvc.entity.UserEntity;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserRepository;
import com.model2.mvc.service.user.oauth.Oauth2UserInfo;
import com.model2.mvc.service.user.oauth.impl.GoogleUserInfo;
import com.model2.mvc.service.user.oauth.impl.KakaoUserInfo;
import com.model2.mvc.service.user.oauth.impl.NaverUserInfo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class Oauth2UserServiceImpl extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("getAttributes : {}", oAuth2User.getAttributes());

        Oauth2UserInfo oAuth2UserInfo = null;

        String provider = userRequest.getClientRegistration().getRegistrationId();

        if(provider.equals("google")) {
            log.info("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo( oAuth2User.getAttributes() );
        } else if(provider.equals("kakao")) {
            log.info("카카오 로그인 요청");
            oAuth2UserInfo = new KakaoUserInfo( (Map) oAuth2User.getAttributes() );
        } else if(provider.equals("naver")) {
            log.info("네이버 로그인 요청");
            oAuth2UserInfo = new NaverUserInfo( (Map)oAuth2User.getAttributes().get("response") );
        }

//        String providerId = oAuth2UserInfo.getProviderId();
        String email = oAuth2UserInfo.getEmail();
//        String loginId = provider + "_" + providerId;
        String nickname = oAuth2UserInfo.getName();

        log.info(email);
        log.info(nickname);
        Optional<UserEntity> optionalUser = userRepository.findById(email);
        UserEntity userEntity = null;

        if(optionalUser.isEmpty()) {
            userEntity = UserEntity.builder()
                    .userId(email)
                    .userName(nickname)
                    .role("user")
                    .password(passwordEncoder.encode("1111"))
                    .build();
            userRepository.save(userEntity);
        }else{
            userEntity = optionalUser.get();
        }
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("userId", email);
        attributes.put("name", nickname);
        attributes.put("role", "user");

        User user = User.builder()
                .userName(nickname)
                .userId(email)
                .role("user").build();
        httpSession.setAttribute("user", user);
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("user")), attributes, "userId");
    }
}
