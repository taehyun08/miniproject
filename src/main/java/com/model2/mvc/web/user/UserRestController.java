package com.model2.mvc.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;


//==> È¸¿ø°ü¸® RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {

    ///Field
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    public UserRestController(){
        System.out.println(this.getClass());
    }

    @GetMapping( value="json/getUser/{userId}")
    public User getUser( @PathVariable String userId ) throws Exception{

        System.out.println("/WEB-INF/user/json/getUser : GET");
        //Business Logic
        return userService.getUser(userId);
    }

    @PostMapping( value="json/login")
    public User login(	@RequestBody User user,
                          HttpSession session ) throws Exception{

        System.out.println("/WEB-INF/user/json/login : POST");
        //Business Logic
        System.out.println("::"+user);
        User dbUser=userService.getUser(user.getUserId());

        if( user.getPassword().equals(dbUser.getPassword())){
            session.setAttribute("user", dbUser);
        }

        return dbUser;
    }

}