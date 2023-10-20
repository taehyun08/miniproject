package com.model2.mvc.web.chat;

import com.model2.mvc.service.chat.ChatService;
import com.model2.mvc.service.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/*")
public class ChatController {

    private final ChatService chatService;

    @GetMapping(value = "chat")
    public String chat(Model model, String receiver, @SessionAttribute User user) throws Exception {
        String sender = user.getUserId();
        if(receiver == null){
            receiver = "admin";
        }
        List<Object> chatting = chatService.getChatList(sender, receiver);
        model.addAttribute("receiver", receiver);
        return "/chat/chat";
    }



}
