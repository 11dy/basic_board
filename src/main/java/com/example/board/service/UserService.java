package com.example.board.service;

import com.example.board.dao.UserDao;
import com.example.board.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    @Transactional
    public User addUser(String name, String email, String password){
        User user1 = userDao.getUser(email);
        if(user1 != null){
            throw new RuntimeException("이미 가입한 이메일입니다.");
        }
        User user = userDao.addUser(email, name, password);
        userDao.mappingUserRole(user.getUserId());  // 권한부여
        return user;
        //트랜잭션 종료
    }

    @Transactional
    public User getUser(String email){
        return userDao.getUser(email);
    }

    @Transactional(readOnly = true)
    public List<String> getRoles(int userId) {  //userid의 role 정보를 가져온다.
        return userDao.getRoles( userId );
    }

}
