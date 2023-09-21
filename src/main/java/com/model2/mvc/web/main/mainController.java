package com.model2.mvc.web.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

    @GetMapping("/")
    public String index() {
        System.out.println("메인컨트롤러");
        return "/index";
    }
}