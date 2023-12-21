package com.mfs.merchantQR.utils;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 11/1/2021
 * Time: 12:26 AM
 * Project : JSCash
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class JWTSecurity {

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

    @SuppressWarnings("unused")
    public String createJWT(String subject) {

        String id = Constants.JWTId;
        String issuer = Constants.JWTissuer;
        int ttlMints = 15;

        // The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // We will sign our JWT with our ApiKey secret
        setKey();
        Key signingKey = new SecretKeySpec(key, signatureAlgorithm.getJcaName());

        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(new Date()).setSubject(subject).setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        // if it has been specified, let's add the expiration

        if (ttlMints >= 0) {
            Calendar calendar = Calendar.getInstance();
            // Add 15 minutes to the calendar time
            calendar.add(Calendar.MINUTE, ttlMints);
            builder.setExpiration(null);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    @SuppressWarnings("unused")
    public String createJWTWithClaims(String subject, Map<String, Object> claims) {

        String id = Constants.JWTId;
        String issuer = Constants.JWTissuer;
        int ttlMints = 555;

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        
        //We will sign our JWT with our ApiKey secret
        setKey();
        Key signingKey = new SecretKeySpec(key, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .setClaims(claims)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMints > 0) {
            Calendar calendar = Calendar.getInstance();
            // Add 15 minutes to the calendar time
            calendar.add(Calendar.MINUTE, ttlMints);
            builder.setExpiration(calendar.getTime());
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();

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
            jwtmap.put(Constants.jwtExpiration, claims.getExpiration() == null ? "" : claims.getExpiration().toString());
            jwtmap.put(Constants.jwtExpired, Constants.equalsIgnoreCase);


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

    public HashMap<String, String> parseCLientSecretJWT(String jwt) {
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
            jwtmap.put(Constants.jwtExpiration, claims.getExpiration() == null ? "" : claims.getExpiration().toString());
            jwtmap.put(Constants.jwtExpired, Constants.equalsIgnoreCase);


            return jwtmap;
        } catch (Exception e) {
            System.out.println("\n\n" + e.getMessage());
            if (e.getMessage().contains(Constants.contains)) {
                HashMap<String, String> jwtmap = new HashMap<String, String>();
                jwtmap.put(Constants.jwtExpired, Constants.jwtExpiredCheck);
                return jwtmap;
            }
            return null;
        }
    }


}
