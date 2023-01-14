package com.example.board.controller;

import com.example.board.dto.LoginInfo;
import com.example.board.dto.User;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    //localhost:8080/userRegForm
    @GetMapping("/userRegForm")
    public String userRegForm(){
        return "userRegForm";
    }

    @PostMapping("/userReg")
    public String userReg(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam(value = "password") String password
    ){
        System.out.println("name: " + name);
        System.out.println("email: " + email);
        System.out.println("password: " + password);

        userService.addUser(name, email, password);

        return "redirect:/welcome"; //브라우저에게 자동으로 8080/welcom으로 이동하라는 의미
    }


    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/loginform")
    public String loginform(){
        return "loginform";
    }

    @PostMapping("/login")
    public String login(
            //loginform에서 뭘 받아들일지
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession httpSession  // 스프링이 자동으로 session을 처리하는 httpsession 객체를 넣는다.
    ){
        // email에 해당하는 회원 정보를 일거온 후
        // 아이디 암호가 맞다면 세션에 회원정보를 저장한다.
        System.out.println(email);
        System.out.println(password);

        try{
            User user = userService.getUser(email);
            if(user.getPassword().equals(password)){
                System.out.println(("암호가 같습니다."));
                LoginInfo loginInfo = new LoginInfo(user.getUserId(), user.getEmail(), user.getName());
                httpSession.setAttribute("loginInfo", loginInfo);
            }else{
                throw new RuntimeException("암호가 다릅니다.");
            }
        }catch(Exception ex){
            return "redirect: /loginform?error=true";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("loginInfo");//세션에서 해당 키가 가리키는 회원정보 삭제
        return "redirect:/";
    }
}
