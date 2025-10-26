package com.example.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * 사용자 엔티티
 * 
 * <p>시스템의 사용자 정보를 나타내는 JPA 엔티티 클래스입니다.</p>
 * 
 * <p><strong>테이블 매핑:</strong> users</p>
 * 
 * @author Spring Multi Module Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "users")
public class User {
    
    /**
     * 사용자 고유 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * 사용자 이름 (로그인 ID)
     * 
     * <p>3~50자 사이여야 하며, null이나 공백일 수 없습니다.</p>
     */
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    
    /**
     * 사용자 이메일 주소
     * 
     * <p>유효한 이메일 형식이어야 하며, 중복될 수 없습니다.</p>
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String email;
    
    /**
     * 암호화된 비밀번호
     */
    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;
    
    /**
     * 계정 활성화 여부
     */
    @Column(nullable = false)
    private Boolean enabled = true;
    
    /**
     * 계정 생성 일시
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * 마지막 수정 일시
     */
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    /**
     * JPA 엔티티 저장 전 호출되는 메서드
     * 
     * <p>생성 일시와 수정 일시를 자동으로 설정합니다.</p>
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    /**
     * JPA 엔티티 업데이트 전 호출되는 메서드
     * 
     * <p>수정 일시를 자동으로 갱신합니다.</p>
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    
    /**
     * 기본 생성자
     */
    public User() {
    }
    
    /**
     * 필수 필드를 초기화하는 생성자
     * 
     * @param username 사용자 이름
     * @param email 이메일 주소
     * @param password 비밀번호
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    // Getters and Setters
    
    /**
     * 사용자 ID를 반환합니다.
     * 
     * @return 사용자 ID
     */
    public Long getId() {
        return id;
    }
    
    /**
     * 사용자 ID를 설정합니다.
     * 
     * @param id 사용자 ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * 사용자 이름을 반환합니다.
     * 
     * @return 사용자 이름
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * 사용자 이름을 설정합니다.
     * 
     * @param username 사용자 이름
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * 이메일 주소를 반환합니다.
     * 
     * @return 이메일 주소
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * 이메일 주소를 설정합니다.
     * 
     * @param email 이메일 주소
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * 암호화된 비밀번호를 반환합니다.
     * 
     * @return 암호화된 비밀번호
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * 비밀번호를 설정합니다.
     * 
     * @param password 비밀번호
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * 계정 활성화 여부를 반환합니다.
     * 
     * @return 활성화 여부
     */
    public Boolean getEnabled() {
        return enabled;
    }
    
    /**
     * 계정 활성화 여부를 설정합니다.
     * 
     * @param enabled 활성화 여부
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    /**
     * 계정 생성 일시를 반환합니다.
     * 
     * @return 생성 일시
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    /**
     * 마지막 수정 일시를 반환합니다.
     * 
     * @return 수정 일시
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
