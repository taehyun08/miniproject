package com.model2.mvc.web.main;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

    @GetMapping("/")
    public String index(@AuthenticationPrincipal User user, Model model) {
        System.out.println("메인컨트롤러");
        System.out.println(user.getUsername());
        System.out.println(user);
        model.addAttribute("user", user);
        return "/index";
    }
}