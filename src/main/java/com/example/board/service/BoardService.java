package com.example.board.service;

import com.example.board.dao.BoardDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardDao boardDao;
    @Transactional
    public void addBoard(int userId, String title, String content){ // 받아들인 값으로 게시물 저장
        boardDao.addBoard(userId, title, content );
    }

}
