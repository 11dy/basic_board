package com.example.board.controller;

import com.example.board.dto.Board;
import com.example.board.dto.LoginInfo;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class BoardController{
    private final BoardService boardService;
    //localhost:8080 요청 시 출력
    @GetMapping("/")
    public String list(HttpSession httpSession, Model model){ // Httpsession, model은 스프링이 자동으로 넣어줌
        //게시물 정보를 읽어와 페이징 처리
        LoginInfo loginInfo = (LoginInfo)httpSession.getAttribute("loginInfo");
        model.addAttribute("LoginInfo", loginInfo);

        int page = 1; //일단 선언만 해줌 그래야 자동으로 생성이 됨
        int totalCount = boardService.getTotalCount(); // 전체 건수 가져오기
        List<Board> list = boardService.getBoards(page);
        int pageCount = totalCount /10;
        if(totalCount % 10 > 0){ // 나머지가 있을 경우 1page 추가
            pageCount++;
        }
        int currentPage = page;
        model.addAttribute("list", list);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", currentPage);
        return "list";

    }
    // /board?id=1or2or3  // 파라미터 id, 값은 정수
    @GetMapping("/board")
    public String board(@RequestParam("id") int id){  // 아이디 뒤에 있는 값을 자동으로 넘겨받음

        return "board";
    }

    @GetMapping("/writeForm")
    public String writeForm(HttpSession session, Model model){
        LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
        if(loginInfo == null){
            return "redirect: /loginform";
        }
        model.addAttribute("LoginInfo", loginInfo);

        return "writeForm";
    }

    @PostMapping("/write")
    public String write(
            @RequestParam("title") String title,
            @RequestParam("content")String content,
            HttpSession session
    ){
        LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
        if(loginInfo == null){
            return "redirect: /loginform";
        }

        //전달 받을 값 데이터베이스에 저장
        boardService.addBoard(loginInfo.getUserId(), title, content);

        return "redirect:/";
    }
}
