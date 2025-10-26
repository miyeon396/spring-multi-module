# Domain Module

사용자 관리를 위한 도메인 모델, 리포지토리, 서비스를 제공하는 모듈입니다.

## 📦 의존성

```gradle
dependencies {
    implementation project(':domain-module')
}
```

이 모듈은 `util-module`과 `encrypt-module`에 의존합니다.

## 🏗️ 아키텍처

Domain Module은 다음과 같은 레이어로 구성됩니다:

```
domain-module/
├── entity/           # JPA 엔티티
├── repository/       # Spring Data JPA 리포지토리
└── service/          # 비즈니스 로직
```

## 🔧 주요 구성요소

### User Entity

사용자 정보를 나타내는 JPA 엔티티입니다.

#### 필드

| 필드 | 타입 | 설명 | 제약조건 |
|------|------|------|---------|
| id | Long | 사용자 ID | Primary Key, Auto Increment |
| username | String | 사용자 이름 | Unique, Not Null, 3-50자 |
| email | String | 이메일 | Unique, Not Null, Email 형식 |
| password | String | 비밀번호 (해시) | Not Null |
| enabled | Boolean | 활성화 여부 | Not Null, 기본값: true |
| createdAt | LocalDateTime | 생성 일시 | Not Null, 자동 설정 |
| updatedAt | LocalDateTime | 수정 일시 | Not Null, 자동 갱신 |

#### 예제

```java
User user = new User();
user.setUsername("johndoe");
user.setEmail("john@example.com");
user.setPassword("password123");
user.setEnabled(true);
```

### UserRepository

User 엔티티에 대한 데이터 액세스 인터페이스입니다.

#### 주요 메서드

**findByUsername(String username)**

사용자 이름으로 사용자를 조회합니다.

```java
Optional<User> user = userRepository.findByUsername("johndoe");
```

**findByEmail(String email)**

이메일로 사용자를 조회합니다.

```java
Optional<User> user = userRepository.findByEmail("john@example.com");
```

**existsByUsername(String username)**

사용자 이름이 존재하는지 확인합니다.

```java
boolean exists = userRepository.existsByUsername("johndoe");
```

**existsByEmail(String email)**

이메일이 존재하는지 확인합니다.

```java
boolean exists = userRepository.existsByEmail("john@example.com");
```

### UserService

사용자 관련 비즈니스 로직을 처리하는 서비스입니다.

#### 주요 메서드

**registerUser(User user)**

새로운 사용자를 등록합니다. 비밀번호는 자동으로 SHA-256 해시로 암호화됩니다.

```java
User newUser = new User("johndoe", "john@example.com", "password123");
User registered = userService.registerUser(newUser);
```

**findById(Long id)**

사용자 ID로 사용자를 조회합니다.

```java
Optional<User> user = userService.findById(1L);
```

**findByUsername(String username)**

사용자 이름으로 사용자를 조회합니다.

```java
Optional<User> user = userService.findByUsername("johndoe");
```

**findAllUsers()**

모든 사용자를 조회합니다.

```java
List<User> users = userService.findAllUsers();
```

**updateUser(Long id, User updatedUser)**

사용자 정보를 수정합니다.

```java
User updates = new User();
updates.setEmail("newemail@example.com");
updates.setEnabled(false);

User updated = userService.updateUser(1L, updates);
```

**deleteUser(Long id)**

사용자를 삭제합니다.

```java
userService.deleteUser(1L);
```

**authenticate(String username, String password)**

사용자 인증을 수행합니다.

```java
boolean authenticated = userService.authenticate("johndoe", "password123");
```

## 📖 API 문서

더 자세한 API 문서는 [Javadoc](../javadoc/domain-module/index.html)을 참조하세요.

## 💡 사용 예제

### Spring Boot 컨트롤러에서 사용

```java
import com.example.domain.entity.User;
import com.example.domain.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            User registered = userService.registerUser(user);
            return ResponseEntity.ok(registered);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, 
                                       @RequestParam String password) {
        boolean authenticated = userService.authenticate(username, password);
        if (authenticated) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
```

## 🗄️ 데이터베이스 설정

### H2 데이터베이스 (개발용)

```yaml
# application.yml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  h2:
    console:
      enabled: true
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
```

### PostgreSQL (운영용)

```yaml
# application-prod.yml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/mydb
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false
```

## 🧪 테스트

```bash
./gradlew :domain-module:test
```

## 🔒 보안 고려사항

1. **비밀번호 저장**: 비밀번호는 SHA-256으로 해시되어 저장됩니다.
2. **입력 검증**: Jakarta Validation을 사용하여 입력값을 검증합니다.
3. **트랜잭션 관리**: @Transactional을 통해 데이터 일관성을 보장합니다.

!!! tip "권장사항"
    실제 운영 환경에서는 비밀번호 해싱 시 BCrypt나 Argon2와 같은 더 강력한 알고리즘을 사용하고, Salt를 추가하는 것을 권장합니다.

## 📚 관련 문서

- [Util Module](util-module.md)
- [Encrypt Module](encrypt-module.md)
- [아키텍처 가이드](../development/architecture.md)
