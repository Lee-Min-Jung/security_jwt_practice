package com.example.security_jwt_practice.security.jwt;

import com.example.security_jwt_practice.security.CustomUserDetailsService;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
@RequiredArgsConstructor

public class JwtProvider {

    private Key key;

    @Value("${jwt.secret.key}")
    private String jwtKey;

    private final long exp = 1000L * 60 * 60;

    private final CustomUserDetailsService customUserDetailsService;

    @PostConstruct
    // 스프링을 실행시켰을 때 초기화 되어야 할 빈들이 모두 초기화 되고
    // 이 어노테이션이 붙은 메서드가 실행된다. 호출하지 않아도 그냥 실행되는 개념.
    // 이 어노테이션이 붙은 메서드는 딱 한 번만 실행되기 때문에 한 번만 초기화하고 이후에는 초기화 할 필요없이 값만 가져오면 될 경우에
    // 이 어노테이션을 붙이면 좋다
    protected void init() {

    }
}
