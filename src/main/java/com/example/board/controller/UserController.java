package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
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

        return "redirect:/welcome"; //브라우저에게 자동으로 8080/welcom으로 이동하라는 의미
    }
    //http://localhost:8080/welcome
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
            @RequestParam("password") String password
    ){
        System.out.println(email);
        System.out.println(password);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(){
        //세션에서 회원정보 삭제
        return "redirect:/";
    }
}
