package com.example.domain.service;

import com.example.domain.entity.User;
import com.example.domain.repository.UserRepository;
import com.example.encrypt.HashService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 사용자 서비스
 * 
 * <p>사용자 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.</p>
 * 
 * @author Spring Multi Module Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class UserService {
    
    private final UserRepository userRepository;
    private final HashService hashService;
    
    /**
     * UserService 생성자
     * 
     * @param userRepository 사용자 리포지토리
     * @param hashService 해시 서비스
     */
    public UserService(UserRepository userRepository, HashService hashService) {
        this.userRepository = userRepository;
        this.hashService = hashService;
    }
    
    /**
     * 새로운 사용자를 등록합니다.
     * 
     * <p>비밀번호는 SHA-256 해시로 암호화되어 저장됩니다.</p>
     * 
     * @param user 등록할 사용자 정보
     * @return 등록된 사용자 객체
     * @throws IllegalArgumentException 사용자 이름이나 이메일이 이미 존재하는 경우
     */
    @Transactional
    public User registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + user.getUsername());
        }
        
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        }
        
        // 비밀번호 해시 처리
        String hashedPassword = hashService.sha256(user.getPassword());
        user.setPassword(hashedPassword);
        
        return userRepository.save(user);
    }
    
    /**
     * 사용자 ID로 사용자를 조회합니다.
     * 
     * @param id 사용자 ID
     * @return 사용자가 존재하면 Optional로 감싸진 User 객체, 없으면 빈 Optional
     */
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    /**
     * 사용자 이름으로 사용자를 조회합니다.
     * 
     * @param username 사용자 이름
     * @return 사용자가 존재하면 Optional로 감싸진 User 객체, 없으면 빈 Optional
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    /**
     * 모든 사용자를 조회합니다.
     * 
     * @return 사용자 목록
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    /**
     * 사용자 정보를 수정합니다.
     * 
     * @param id 수정할 사용자 ID
     * @param updatedUser 수정할 사용자 정보
     * @return 수정된 사용자 객체
     * @throws IllegalArgumentException 사용자를 찾을 수 없는 경우
     */
    @Transactional
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
        
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().equals(existingUser.getEmail())) {
            if (userRepository.existsByEmail(updatedUser.getEmail())) {
                throw new IllegalArgumentException("Email already exists: " + updatedUser.getEmail());
            }
            existingUser.setEmail(updatedUser.getEmail());
        }
        
        if (updatedUser.getEnabled() != null) {
            existingUser.setEnabled(updatedUser.getEnabled());
        }
        
        return userRepository.save(existingUser);
    }
    
    /**
     * 사용자를 삭제합니다.
     * 
     * @param id 삭제할 사용자 ID
     * @throws IllegalArgumentException 사용자를 찾을 수 없는 경우
     */
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found: " + id);
        }
        userRepository.deleteById(id);
    }
    
    /**
     * 사용자 인증을 수행합니다.
     * 
     * @param username 사용자 이름
     * @param password 비밀번호
     * @return 인증 성공 시 true, 실패 시 false
     */
    public boolean authenticate(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        
        if (userOpt.isEmpty()) {
            return false;
        }
        
        User user = userOpt.get();
        String hashedPassword = hashService.sha256(password);
        
        return user.getPassword().equals(hashedPassword) && user.getEnabled();
    }
}
