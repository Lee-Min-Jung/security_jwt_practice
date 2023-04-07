package com.example.security_jwt_practice.dto;

import com.example.security_jwt_practice.entity.Authority;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private List<Authority> roles = new ArrayList<>();
    private TokenDto token;
}
