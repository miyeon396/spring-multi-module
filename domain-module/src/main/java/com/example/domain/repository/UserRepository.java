package com.example.domain.repository;

import com.example.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 사용자 리포지토리
 * 
 * <p>User 엔티티에 대한 데이터베이스 액세스 인터페이스입니다.</p>
 * 
 * <p>Spring Data JPA를 사용하여 기본 CRUD 작업과 커스텀 쿼리 메서드를 제공합니다.</p>
 * 
 * @author Spring Multi Module Team
 * @version 1.0.0
 * @since 1.0.0
 * @see User
 * @see JpaRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * 사용자 이름으로 사용자를 조회합니다.
     * 
     * @param username 조회할 사용자 이름
     * @return 사용자가 존재하면 Optional로 감싸진 User 객체, 없으면 빈 Optional
     */
    Optional<User> findByUsername(String username);
    
    /**
     * 이메일 주소로 사용자를 조회합니다.
     * 
     * @param email 조회할 이메일 주소
     * @return 사용자가 존재하면 Optional로 감싸진 User 객체, 없으면 빈 Optional
     */
    Optional<User> findByEmail(String email);
    
    /**
     * 사용자 이름이 존재하는지 확인합니다.
     * 
     * @param username 확인할 사용자 이름
     * @return 사용자 이름이 존재하면 true, 그렇지 않으면 false
     */
    boolean existsByUsername(String username);
    
    /**
     * 이메일 주소가 존재하는지 확인합니다.
     * 
     * @param email 확인할 이메일 주소
     * @return 이메일이 존재하면 true, 그렇지 않으면 false
     */
    boolean existsByEmail(String email);
}
