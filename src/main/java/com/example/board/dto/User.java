package com.example.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class User {
    private int userId;
    private String email;
    private String name;
    private String password;
    private String reqdate;
}
