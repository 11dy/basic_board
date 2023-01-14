package com.example.board.controller;

import com.example.board.dto.LoginInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class BoardController{
    //localhost:8080 요청 시 출력
    @GetMapping("/")
    public String list(HttpSession httpSession, Model model){ // Httpsession, model은 스프링이 자동으로 넣어줌
        //게시물 정보를 읽어와 페이징 처리
        LoginInfo loginInfo = (LoginInfo)httpSession.getAttribute("loginInfo");
        model.addAttribute("LoginInfo", loginInfo);
        return "list";

    }
    // /board?id=1or2or3  // 파라미터 id, 값은 정수
    @GetMapping("/board")
    public String board(@RequestParam("id") int id){  // 아이디 뒤에 있는 값을 자동으로 넘겨받음

        return "board";
    }

    @GetMapping("/writeForm")
    public String writeForm(){
        return "writeForm";
    }

    @PostMapping("/write")
    public String write(
            @RequestParam("title") String title,
            @RequestParam("content")String content
    ){

        return "redirect:/";
    }
}
