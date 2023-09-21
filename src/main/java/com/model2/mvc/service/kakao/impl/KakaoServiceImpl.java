package com.model2.mvc.service.kakao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.kakao.KakaoDao;
import com.model2.mvc.service.kakao.KakaoService;

@Service("kakaoServiceImpl")
public class KakaoServiceImpl implements KakaoService {

    @Autowired
    @Qualifier("kakaoDaoImpl")
    private KakaoDao kakaoDao;

    public void setKakaoDao(KakaoDao kakaoDao) {
        this.kakaoDao = kakaoDao;
    }

    public KakaoServiceImpl() {
        kakaoDao = new KakaoDaoImpl();
    }



}