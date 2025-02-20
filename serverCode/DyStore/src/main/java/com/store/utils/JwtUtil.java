package com.store.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    //生成令牌
    public static String GenerateJwt(Map<String, Object> claim){
        String Jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"6ams54SV5qyj")//base64编码，密钥(马焕欣)
                .addClaims(claim)
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
                .compact();
        return Jwt;
    }

    //解析令牌
    public static Claims ParseJwt(String token){
        String key = "6ams54SV5qyj";
        Claims claim = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claim;
    }
}
