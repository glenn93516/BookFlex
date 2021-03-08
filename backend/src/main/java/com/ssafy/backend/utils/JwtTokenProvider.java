package com.ssafy.backend.utils;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.token-validity-in-seconds}")
    private long tokenValidMillisecond;

    private final UserDetailsService userDetailsService;
    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes()); // SecretKey Base64로 인코딩
    }

    // JWT 토큰 생성
    public String createToken(Long userId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(Long.toString(userId));
        claims.put("roles", roles);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond)) // 토큰 만료일 설정
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 유저 이름 추출
    public String getUserId(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Request header에서 token 꺼내옴
    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        // 가져온 Authorization Header 가 문자열이고, Bearer 로 시작해야 가져옴
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return null;
    }

    // JWT 토큰 유효성 체크
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (SecurityException | MalformedJwtException | IllegalArgumentException exception) {
            logger.info("잘못된 Jwt 토큰입니다");
        } catch (ExpiredJwtException exception) {
            logger.info("만료된 Jwt 토큰입니다");
        } catch (UnsupportedJwtException exception) {
            logger.info("지원하지 않는 Jwt 토큰입니다");
        }

        return false;
    }
}
