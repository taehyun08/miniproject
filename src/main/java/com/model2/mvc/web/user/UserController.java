package com.model2.mvc.web.user;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


//==> 회원관리 Controller
@Controller
@RequestMapping("/user/*")
public class UserController {

    ///Field
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;
    //setter Method 구현 않음

    public UserController(){
        System.out.println(this.getClass());
    }

    @Value("${pageUnit}")
    int pageUnit;

    @Value("${pageSize}")
    int pageSize;


    //@RequestMapping("/addUserView.do")
    //public String addUserView() throws Exception {
    @RequestMapping( value="addUser", method=RequestMethod.GET )
    public String addUser() throws Exception{

        System.out.println("/webapp/WEB-INF/view/user/addUser : GET");

        return "redirect:/user/addUserView";
    }

    //@RequestMapping("/addUser.do")
    @RequestMapping( value="addUser", method=RequestMethod.POST )
    public String addUser( @ModelAttribute("user") User user ) throws Exception {

        System.out.println("/webapp/WEB-INF/view/user/addUser : POST");
        //Business Logic
        userService.addUser(user);

        return "redirect:/user/loginView";
    }

    //@RequestMapping("/getUser.do")
    @RequestMapping( value="getUser", method=RequestMethod.GET )
    public String getUser( @RequestParam("userId") String userId , Model model ) throws Exception {

        System.out.println("/webapp/WEB-INF/view/user/getUser : GET");
        //Business Logic
        User user = userService.getUser(userId);
        // Model 과 View 연결
        model.addAttribute("user", user);

        return "forward:/user/getUser";
    }

    //@RequestMapping("/updateUserView.do")
    //public String updateUserView( @RequestParam("userId") String userId , Model model ) throws Exception{
    @RequestMapping( value="updateUser", method=RequestMethod.GET )
    public String updateUser( @RequestParam("userId") String userId , Model model ) throws Exception{

        System.out.println("/webapp/WEB-INF/view/user/updateUser : GET");
        //Business Logic
        User user = userService.getUser(userId);
        // Model 과 View 연결
        model.addAttribute("user", user);

        return "forward:/user/updateUser";
    }

    //@RequestMapping("/updateUser.do")
    @RequestMapping( value="updateUser", method=RequestMethod.POST )
    public String updateUser( @ModelAttribute("user") User user , Model model , HttpSession session) throws Exception{

        System.out.println("/webapp/WEB-INF/view/user/updateUser : POST");
        //Business Logic
        userService.updateUser(user);

        String sessionId=((User)session.getAttribute("user")).getUserId();
        if(sessionId.equals(user.getUserId())){
            session.setAttribute("user", user);
        }

        //return "redirect:/getUser.do?userId="+user.getUserId();
        return "redirect:/user/getUser?userId="+user.getUserId();
    }

    //@RequestMapping("/loginView.do")
    //public String loginView() throws Exception{
    @RequestMapping( value="login", method=RequestMethod.GET )
    public String login() throws Exception{

        System.out.println("/webapp/WEB-INF/view/user/logon : GET");

        return "/user/loginView";
    }

    //@RequestMapping("/login.do")
    @RequestMapping( value="login", method=RequestMethod.POST )
    public String login(@ModelAttribute("user") User user , HttpSession session ) throws Exception{

        System.out.println("/webapp/WEB-INF/view/user/login : POST");
        //Business Logic
        User dbUser=userService.getUser(user.getUserId());

        if( user.getPassword().equals(dbUser.getPassword())){
            session.setAttribute("user", dbUser);
        }
        System.out.println(dbUser);
        return "redirect:/";
    }

    //@RequestMapping("/logout.do")
    @RequestMapping( value="logout", method=RequestMethod.GET )
    public String logout(HttpSession session ) throws Exception{

        System.out.println("/webapp/WEB-INF/view/user/logout : POST");

        session.invalidate();

        return "redirect:/";
    }


    //@RequestMapping("/checkDuplication.do")
    @RequestMapping( value="checkDuplication", method=RequestMethod.POST )
    public String checkDuplication( @RequestParam("userId") String userId , Model model ) throws Exception{

        System.out.println("/webapp/WEB-INF/view/user/checkDuplication : POST");
        //Business Logic
        boolean result=userService.checkDuplication(userId);
        // Model 과 View 연결
        model.addAttribute("result", result);
        model.addAttribute("userId", userId);

        return "forward:/user/checkDuplication";
    }

    //@RequestMapping("/listUser.do")
    @RequestMapping( value="listUser" )
    public String listUser( @ModelAttribute Search search , Model model) throws Exception{

        System.out.println("/webapp/WEB-INF/view/user/listUser : GET / POST");

        if(search.getCurrentPage() ==0 ){
            search.setCurrentPage(1);
        }
        search.setPageUnit(pageSize);
        if(search.getOrderBy() == null){
            search.setOrderBy("userId");
        }
        int startRowNum = search.getCurrentPage() * pageSize - pageSize+1;
        int endRowNum = startRowNum + pageSize - 1;
        search.setStartRowNum(startRowNum);
        search.setEndRowNum(endRowNum);
        // Business logic 수행
        Map<String , Object> map=userService.getUserList(search);

        Page resultPage = new Page( search.getCurrentPage(), (Integer) map.get("totalCount"), pageUnit, pageSize);
        System.out.println(resultPage);
        // Model 과 View 연결
        model.addAttribute("list", map.get("list"));
        model.addAttribute("resultPage", resultPage);
        model.addAttribute("search", search);
        System.out.println("되나");
        return "/user/listUser";
    }

}