package com.example.security_jwt_practice.service;

import com.example.security_jwt_practice.dto.LoginRequestDto;
import com.example.security_jwt_practice.dto.UserDto;
import com.example.security_jwt_practice.entity.Authority;
import com.example.security_jwt_practice.entity.User;
import com.example.security_jwt_practice.repository.UserRepository;
import com.example.security_jwt_practice.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    public UserDto login(LoginRequestDto request) throws Exception {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() ->
                new BadCredentialsException("잘못된 계정정보입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .token(jwtProvider.createToken(authentication))
                .build();

    }
}
