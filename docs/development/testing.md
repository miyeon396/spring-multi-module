# 테스트 가이드

프로젝트의 테스트 전략과 작성 방법을 설명합니다.

## 🎯 테스트 전략

### 테스트 피라미드

```
        /\
       /  \
      / E2E\         (소수)
     /------\
    /  통합   \      (중간)
   /----------\
  /  단위 테스트 \   (다수)
 /--------------\
```

- **단위 테스트 (70%)**: 개별 메서드/클래스 테스트
- **통합 테스트 (20%)**: 여러 컴포넌트 간 상호작용 테스트
- **E2E 테스트 (10%)**: 전체 시스템 테스트

## 🧪 단위 테스트

### Util Module 테스트

```java
package com.example.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StringUtils 테스트")
class StringUtilsTest {
    
    private final StringUtils stringUtils = new StringUtils();
    
    @Test
    @DisplayName("빈 문자열 확인")
    void isEmpty_EmptyString_ReturnsTrue() {
        // given
        String input = "";
        
        // when
        boolean result = stringUtils.isEmpty(input);
        
        // then
        assertTrue(result);
    }
    
    @Test
    @DisplayName("null 문자열 확인")
    void isEmpty_NullString_ReturnsTrue() {
        // given
        String input = null;
        
        // when
        boolean result = stringUtils.isEmpty(input);
        
        // then
        assertTrue(result);
    }
    
    @Test
    @DisplayName("카멜케이스 변환")
    void toCamelCase_UnderscoreString_ReturnsCamelCase() {
        // given
        String input = "hello_world";
        
        // when
        String result = stringUtils.toCamelCase(input);
        
        // then
        assertEquals("helloWorld", result);
    }
    
    @Test
    @DisplayName("null 입력 시 예외 발생")
    void toCamelCase_NullInput_ThrowsException() {
        // given
        String input = null;
        
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            stringUtils.toCamelCase(input);
        });
    }
}
```

### Encrypt Module 테스트

```java
package com.example.encrypt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AesEncryptionService 테스트")
class AesEncryptionServiceTest {
    
    private final AesEncryptionService aesService = new AesEncryptionService();
    
    @Test
    @DisplayName("암호화 및 복호화")
    void encryptDecrypt_PlainText_ReturnsSameText() {
        // given
        String plainText = "Hello World";
        String secretKey = aesService.generateSecretKey();
        
        // when
        String encrypted = aesService.encrypt(plainText, secretKey);
        String decrypted = aesService.decrypt(encrypted, secretKey);
        
        // then
        assertNotEquals(plainText, encrypted);
        assertEquals(plainText, decrypted);
    }
    
    @Test
    @DisplayName("비밀키 생성")
    void generateSecretKey_ReturnsValidKey() {
        // when
        String key1 = aesService.generateSecretKey();
        String key2 = aesService.generateSecretKey();
        
        // then
        assertNotNull(key1);
        assertNotNull(key2);
        assertNotEquals(key1, key2);  // 매번 다른 키 생성
    }
}
```

### 테스트 실행

```bash
# 전체 테스트
./gradlew test

# 특정 모듈 테스트
./gradlew :util-module:test
./gradlew :encrypt-module:test
./gradlew :domain-module:test

# 특정 테스트 클래스만 실행
./gradlew test --tests StringUtilsTest

# 특정 테스트 메서드만 실행
./gradlew test --tests StringUtilsTest.isEmpty_EmptyString_ReturnsTrue
```

## 🔗 통합 테스트

### Domain Module 통합 테스트

```java
package com.example.domain.service;

import com.example.domain.entity.User;
import com.example.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@DisplayName("UserService 통합 테스트")
class UserServiceIntegrationTest {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    @DisplayName("사용자 등록 및 조회")
    void registerAndFindUser() {
        // given
        User user = new User("john", "john@example.com", "password123");
        
        // when
        User registered = userService.registerUser(user);
        User found = userService.findById(registered.getId()).orElse(null);
        
        // then
        assertNotNull(found);
        assertEquals("john", found.getUsername());
        assertEquals("john@example.com", found.getEmail());
        assertNotEquals("password123", found.getPassword());  // 해시되어야 함
    }
    
    @Test
    @DisplayName("중복 사용자 이름으로 등록 시 예외 발생")
    void registerUser_DuplicateUsername_ThrowsException() {
        // given
        User user1 = new User("john", "john1@example.com", "password");
        User user2 = new User("john", "john2@example.com", "password");
        
        // when
        userService.registerUser(user1);
        
        // then
        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(user2);
        });
    }
    
    @Test
    @DisplayName("사용자 인증")
    void authenticate_ValidCredentials_ReturnsTrue() {
        // given
        User user = new User("john", "john@example.com", "password123");
        userService.registerUser(user);
        
        // when
        boolean authenticated = userService.authenticate("john", "password123");
        
        // then
        assertTrue(authenticated);
    }
    
    @Test
    @DisplayName("잘못된 비밀번호로 인증 시 실패")
    void authenticate_InvalidPassword_ReturnsFalse() {
        // given
        User user = new User("john", "john@example.com", "password123");
        userService.registerUser(user);
        
        // when
        boolean authenticated = userService.authenticate("john", "wrongpassword");
        
        // then
        assertFalse(authenticated);
    }
}
```

### Repository 테스트

```java
package com.example.domain.repository;

import com.example.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("UserRepository 테스트")
class UserRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    @DisplayName("사용자 이름으로 조회")
    void findByUsername_ExistingUser_ReturnsUser() {
        // given
        User user = new User("john", "john@example.com", "password");
        entityManager.persist(user);
        entityManager.flush();
        
        // when
        Optional<User> found = userRepository.findByUsername("john");
        
        // then
        assertTrue(found.isPresent());
        assertEquals("john@example.com", found.get().getEmail());
    }
    
    @Test
    @DisplayName("존재하지 않는 사용자 조회")
    void findByUsername_NonExistingUser_ReturnsEmpty() {
        // when
        Optional<User> found = userRepository.findByUsername("nonexistent");
        
        // then
        assertFalse(found.isPresent());
    }
    
    @Test
    @DisplayName("사용자 이름 존재 여부 확인")
    void existsByUsername_ExistingUser_ReturnsTrue() {
        // given
        User user = new User("john", "john@example.com", "password");
        entityManager.persist(user);
        entityManager.flush();
        
        // when
        boolean exists = userRepository.existsByUsername("john");
        
        // then
        assertTrue(exists);
    }
}
```

## 🗄️ 테스트 데이터베이스 설정

### application-test.yml

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  h2:
    console:
      enabled: false
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        
logging:
  level:
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
```

## 📊 테스트 커버리지

### JaCoCo 설정

```gradle
// build.gradle
plugins {
    id 'jacoco'
}

jacoco {
    toolVersion = "0.8.11"
}

test {
    finalizedBy jacocoTestReport
}

jacocoTestReport {
    dependsOn test
    
    reports {
        xml.required = true
        html.required = true
        csv.required = false
    }
    
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.collect {
            fileTree(dir: it, exclude: [
                '**/entity/**',
                '**/config/**'
            ])
        }))
    }
}

jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = 0.80  // 80% 커버리지
            }
        }
    }
}

check.dependsOn jacocoTestCoverageVerification
```

### 커버리지 리포트 생성

```bash
# 테스트 실행 및 커버리지 리포트 생성
./gradlew test jacocoTestReport

# 커버리지 검증
./gradlew jacocoTestCoverageVerification
```

리포트 위치: `build/reports/jacoco/test/html/index.html`

## 🎭 Mocking

### Mockito 사용

```java
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceMockTest {
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private HashService hashService;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    @DisplayName("사용자 조회 - Mock 사용")
    void findById_WithMock() {
        // given
        User user = new User("john", "john@example.com", "hashedPassword");
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        
        // when
        Optional<User> found = userService.findById(1L);
        
        // then
        assertTrue(found.isPresent());
        assertEquals("john", found.get().getUsername());
        verify(userRepository, times(1)).findById(1L);
    }
}
```

## 🚀 테스트 베스트 프랙티스

### 1. 테스트는 독립적이어야 함

```java
// Good: 각 테스트가 독립적
@Test
void test1() {
    User user = createTestUser();
    // test...
}

@Test
void test2() {
    User user = createTestUser();
    // test...
}

// Bad: 테스트 간 상태 공유
private User sharedUser;

@BeforeEach
void setup() {
    sharedUser = createTestUser();
}
```

### 2. 명확한 테스트 이름

```java
// Good
@Test
void registerUser_ValidInput_SavesUserSuccessfully() { }

// Bad
@Test
void test1() { }
```

### 3. Given-When-Then 패턴 사용

```java
@Test
void testExample() {
    // given (준비): 테스트 데이터 준비
    User user = new User("john", "john@example.com", "password");
    
    // when (실행): 테스트 대상 메서드 실행
    User result = userService.registerUser(user);
    
    // then (검증): 결과 검증
    assertNotNull(result.getId());
}
```

### 4. 하나의 개념만 테스트

```java
// Good: 하나의 기능만 테스트
@Test
void encrypt_ValidInput_ReturnsEncryptedString() {
    String result = aesService.encrypt("test", key);
    assertNotNull(result);
}

@Test
void decrypt_EncryptedInput_ReturnsOriginalString() {
    String encrypted = aesService.encrypt("test", key);
    String decrypted = aesService.decrypt(encrypted, key);
    assertEquals("test", decrypted);
}
```

## 📝 테스트 체크리스트

- [ ] 모든 public 메서드에 테스트 작성
- [ ] 예외 케이스 테스트
- [ ] 경계값 테스트
- [ ] null 체크 테스트
- [ ] 테스트 커버리지 80% 이상
- [ ] 테스트 이름이 명확함
- [ ] 각 테스트가 독립적임
- [ ] Given-When-Then 패턴 사용

## 🛠️ 유용한 명령어

```bash
# 테스트 실행
./gradlew test

# 특정 모듈만
./gradlew :util-module:test

# 커버리지 리포트
./gradlew jacocoTestReport

# 지속적 테스트 (파일 변경 감지)
./gradlew test --continuous

# 병렬 테스트 실행
./gradlew test --parallel

# 실패한 테스트만 재실행
./gradlew test --rerun-tasks
```

## 📚 참고 자료

- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Spring Boot Testing](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing)
