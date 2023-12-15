package com.mfs.merchantQR.utils;

/*
Author Name: romail.ahmed

Project Name: configurations

Package Name: com.mfs.configurations.controller.blacklist

Class Name: JWTSecurity

Date and Time:3/13/2023 2:41 PM

Version:1.0
*/



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class JWTSecurity implements Serializable {

    private SecretKeySpec secretKey;

    private byte[] key;

    private void setKey() {
        MessageDigest sha = null;
        try {
            String myKey = Constants.secKey;
            key = myKey.getBytes(Constants.charsetName);
            sha = MessageDigest.getInstance(Constants.sha);
            key = sha.digest(key);
            key = Arrays.copyOf(key, 32);
            secretKey = new SecretKeySpec(key, Constants.algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public HashMap<String, String> parseJWT(String jwt) {
        try {
            AESencryption aeSencryption = new AESencryption();
            jwt = aeSencryption.decrypt(jwt);
            setKey();
            // This line will throw an exception if it is not a signed JWS (as expected)

            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();

            HashMap<String, String> jwtmap = new HashMap<String, String>();


            jwtmap.put(Constants.jwtID, claims.getId());
            jwtmap.put(Constants.jwtSubject, claims.getSubject());
            jwtmap.put(Constants.jwtIssuer, claims.getIssuer());
            jwtmap.put(Constants.jwtExpiration, claims.getExpiration() == null ? Constants.EMPTY : claims.getExpiration().toString());
            jwtmap.put(Constants.jwtExpired, Constants.SET_NO);


            jwtmap.put(Constants.userId, String.valueOf(claims.get(Constants.userId)));
            jwtmap.put(Constants.loginId, String.valueOf(claims.get(Constants.userId)));
            String userType = String.valueOf(claims.get(Constants.userTypeId));
            jwtmap.put(Constants.userTypeId, userType);
            return jwtmap;
        } catch (Exception e) {
            HashMap<String, String> jwtmap = new HashMap<String, String>();
            if (e.getMessage().contains(Constants.contains)) {

                jwtmap.put(Constants.jwtExpired, Constants.jwtExpiredCheck);
                return jwtmap;
            }
            return Constants.mapUnhandleException;
        }
    }


    public  String generateJwtToken(int expiryMinutes,String SECRET_KEY) {
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(expiryMinutes);

        Date expirationDate = Date.from(expiryTime.atZone(ZoneId.systemDefault()).toInstant());

        SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }

    public  boolean isJwtTokenValid(String jwtToken,String SECRET_KEY) {

        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwtToken);

            return true;
        } catch (Exception e) {
            return false;
        }


//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
//                    .build()
//                    .parseClaimsJws(jwtToken);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
    }
}
