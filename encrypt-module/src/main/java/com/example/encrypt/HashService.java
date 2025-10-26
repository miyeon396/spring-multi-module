package com.example.encrypt;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 해시 생성 서비스
 * 
 * <p>이 클래스는 다양한 해시 알고리즘을 사용하여 데이터의 해시 값을 생성합니다.</p>
 * 
 * @author Spring Multi Module Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class HashService {
    
    /**
     * SHA-256 알고리즘을 사용하여 해시를 생성합니다.
     * 
     * @param input 해시를 생성할 입력 문자열
     * @return 16진수 문자열로 인코딩된 해시 값
     * @throws IllegalArgumentException input이 null이거나 비어있는 경우
     * @throws RuntimeException 해시 생성 중 오류가 발생한 경우
     */
    public String sha256(String input) {
        return hash(input, "SHA-256");
    }
    
    /**
     * MD5 알고리즘을 사용하여 해시를 생성합니다.
     * 
     * <p><strong>주의:</strong> MD5는 보안상 취약하므로 중요한 데이터에는 사용하지 마세요.</p>
     * 
     * @param input 해시를 생성할 입력 문자열
     * @return 16진수 문자열로 인코딩된 해시 값
     * @throws IllegalArgumentException input이 null이거나 비어있는 경우
     * @throws RuntimeException 해시 생성 중 오류가 발생한 경우
     * @deprecated MD5는 보안상 취약합니다. {@link #sha256(String)}를 사용하세요.
     */
    @Deprecated(since = "1.0.0", forRemoval = true)
    public String md5(String input) {
        return hash(input, "MD5");
    }
    
    /**
     * 지정된 알고리즘으로 해시를 생성합니다.
     * 
     * @param input 해시를 생성할 입력 문자열
     * @param algorithm 해시 알고리즘 이름 (예: "SHA-256", "MD5")
     * @return 16진수 문자열로 인코딩된 해시 값
     * @throws IllegalArgumentException input이 null이거나 비어있는 경우
     * @throws RuntimeException 해시 생성 중 오류가 발생한 경우
     */
    private String hash(String input, String algorithm) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
        
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash generation failed", e);
        }
    }
    
    /**
     * Base64 인코딩을 수행합니다.
     * 
     * @param input 인코딩할 문자열
     * @return Base64로 인코딩된 문자열
     * @throws IllegalArgumentException input이 null인 경우
     */
    public String base64Encode(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }
    
    /**
     * Base64 디코딩을 수행합니다.
     * 
     * @param encoded Base64로 인코딩된 문자열
     * @return 디코딩된 문자열
     * @throws IllegalArgumentException encoded가 null인 경우
     * @throws RuntimeException 디코딩 중 오류가 발생한 경우
     */
    public String base64Decode(String encoded) {
        if (encoded == null) {
            throw new IllegalArgumentException("Encoded string cannot be null");
        }
        
        try {
            byte[] decoded = Base64.getDecoder().decode(encoded);
            return new String(decoded, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Base64 decoding failed", e);
        }
    }
}
