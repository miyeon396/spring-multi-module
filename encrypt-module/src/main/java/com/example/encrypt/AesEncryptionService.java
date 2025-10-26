package com.example.encrypt;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * AES 암호화/복호화 서비스
 * 
 * <p>이 클래스는 AES 알고리즘을 사용하여 데이터를 암호화하고 복호화하는 기능을 제공합니다.</p>
 * 
 * <p><strong>사용 예제:</strong></p>
 * <pre>{@code
 * AesEncryptionService service = new AesEncryptionService();
 * String secretKey = service.generateSecretKey();
 * String encrypted = service.encrypt("Hello World", secretKey);
 * String decrypted = service.decrypt(encrypted, secretKey);
 * }</pre>
 * 
 * @author Spring Multi Module Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class AesEncryptionService {
    
    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 256;
    
    /**
     * AES 비밀키를 생성합니다.
     * 
     * @return Base64로 인코딩된 비밀키 문자열
     * @throws RuntimeException 키 생성 중 오류가 발생한 경우
     */
    public String generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(KEY_SIZE);
            SecretKey secretKey = keyGenerator.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate secret key", e);
        }
    }
    
    /**
     * 주어진 텍스트를 AES 알고리즘으로 암호화합니다.
     * 
     * @param plainText 암호화할 평문 텍스트
     * @param secretKey Base64로 인코딩된 비밀키
     * @return Base64로 인코딩된 암호화된 텍스트
     * @throws IllegalArgumentException plainText나 secretKey가 null이거나 비어있는 경우
     * @throws RuntimeException 암호화 중 오류가 발생한 경우
     */
    public String encrypt(String plainText, String secretKey) {
        if (plainText == null || plainText.isEmpty()) {
            throw new IllegalArgumentException("Plain text cannot be null or empty");
        }
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("Secret key cannot be null or empty");
        }
        
        try {
            SecretKeySpec keySpec = new SecretKeySpec(
                Base64.getDecoder().decode(secretKey), 
                ALGORITHM
            );
            
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            
            byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }
    
    /**
     * 암호화된 텍스트를 복호화합니다.
     * 
     * @param encryptedText Base64로 인코딩된 암호화된 텍스트
     * @param secretKey Base64로 인코딩된 비밀키
     * @return 복호화된 평문 텍스트
     * @throws IllegalArgumentException encryptedText나 secretKey가 null이거나 비어있는 경우
     * @throws RuntimeException 복호화 중 오류가 발생한 경우
     */
    public String decrypt(String encryptedText, String secretKey) {
        if (encryptedText == null || encryptedText.isEmpty()) {
            throw new IllegalArgumentException("Encrypted text cannot be null or empty");
        }
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("Secret key cannot be null or empty");
        }
        
        try {
            SecretKeySpec keySpec = new SecretKeySpec(
                Base64.getDecoder().decode(secretKey), 
                ALGORITHM
            );
            
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
}
