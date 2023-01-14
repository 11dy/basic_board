package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor // 모든 필드를 초기화 시켜주는 생성자를 받아들이도록하는 것. / 롬복이 자동으로 생성자를 만들어줌
public class LoginInfo {
    private int userId;
    private String email;
    private String name;
}
