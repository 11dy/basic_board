package com.example.board.service;

import com.example.board.dao.BoardDao;
import com.example.board.dto.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardDao boardDao;
    @Transactional
    public void addBoard(int userId, String title, String content){ // 받아들인 값으로 게시물 저장
        boardDao.addBoard(userId, title, content );
    }

    @Transactional(readOnly = true)
    public int getTotalCount() {
        return boardDao.getTotalCount();

    }

    @Transactional(readOnly = true)
    public List<Board> getBoards(int page) {
        return boardDao.getBoards(page);

    }
    @Transactional
    public Board getBoard(int boardId) {
       return getBoard(boardId, true);
    }

    // updateViewCnt가 true면 조회수 증가 아니면 증가 x
    @Transactional
    public Board getBoard(int boardId, boolean updateViewCnt) {
        Board board = boardDao.getBoard(boardId); // id에 해당하는 게시물 읽어오기
        if(updateViewCnt){
            boardDao.updateViewCnt(boardId);         //id에 해당하는 게시물의 조회수 1 증가 > 두 가지 이상의 동작은 트랜잭션 처리가 필요하다.
        }
            return board;
    }



    @Transactional
    public void deleteBoard(int userId, int boardId) {
        Board board = boardDao.getBoard(boardId); // boardid에 해당하는 글 읽어 옴
        if(board.getUserId() == userId ){  // 보드에서 가져온 아이디와 로그인한 유저 아이디가 같은 경우만 삭제
            boardDao.deleteBoard(boardId);
        }

    }
    @Transactional
    public void deleteBoard(int boardId) {// 조건없이 관리자 권한 있으면 삭제
        boardDao.deleteBoard(boardId);

    }


    @Transactional
    public void updateBoard(int boardId, String title, String content) {
        boardDao.updateBoard(boardId, title, content);
    }





}
