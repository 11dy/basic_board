package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BoardController{
    //localhost:8080 요청 시 출력
    @GetMapping("/")
    public String list(){
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
//        로그인한 사람만 사용 가능해야한다.
//
//        로그인 하지 않았다면 리스트 보기로 자동이동
//
//        세션에서 로그인한 정보를 읽어들인다.
        return "redirect:/";
    }
}
