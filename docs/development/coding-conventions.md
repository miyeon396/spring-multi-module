# 코딩 컨벤션

프로젝트의 코드 일관성을 위한 코딩 컨벤션입니다.

## 📝 Java 코딩 스타일

### 네이밍 규칙

#### 클래스와 인터페이스

```java
// PascalCase 사용
public class UserService { }
public interface EncryptionService { }

// 의미 있는 이름 사용
public class AesEncryptionService { }  // Good
public class AES { }                    // Bad
```

#### 메서드

```java
// camelCase 사용
public String getUserName() { }
public void calculateTotal() { }

// 동사로 시작
public void saveUser() { }      // Good
public void user() { }          // Bad

// boolean은 is/has/can으로 시작
public boolean isValid() { }
public boolean hasPermission() { }
public boolean canAccess() { }
```

#### 변수

```java
// camelCase 사용
String userName;
int totalCount;

// 상수는 UPPER_SNAKE_CASE
public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
public static final int MAX_RETRY_COUNT = 3;
```

#### 패키지

```java
// 소문자, 점으로 구분
com.example.util
com.example.encrypt
com.example.domain.entity
```

### 코드 포맷팅

#### 들여쓰기

```java
// 4칸 공백 사용
public class Example {
    public void method() {
        if (condition) {
            doSomething();
        }
    }
}
```

#### 중괄호

```java
// K&R 스타일 (same line)
public void method() {
    if (condition) {
        doSomething();
    } else {
        doOtherThing();
    }
}

// 한 줄짜리도 중괄호 사용
if (condition) {
    return;  // Good
}

if (condition) return;  // Avoid
```

#### 줄 길이

```java
// 최대 120자 권장
// 길면 적절히 줄바꿈
String result = someObject.method(param1, param2, param3)
    .anotherMethod(param4)
    .finalMethod();
```

#### 공백

```java
// 연산자 주위에 공백
int sum = a + b;  // Good
int sum=a+b;      // Bad

// 쉼표 뒤에 공백
method(a, b, c);  // Good
method(a,b,c);    // Bad

// 메서드 선언 시 괄호와 공백
public void method(int a, int b) {  // Good
public void method (int a, int b){  // Bad
```

## 📚 Javadoc 작성 규칙

### 클래스 문서화

```java
/**
 * 사용자 관련 비즈니스 로직을 처리하는 서비스
 * 
 * <p>이 클래스는 사용자 등록, 조회, 수정, 삭제 등의 기능을 제공합니다.</p>
 * 
 * @author Spring Multi Module Team
 * @version 1.0.0
 * @since 1.0.0
 * @see User
 * @see UserRepository
 */
@Service
public class UserService {
    // ...
}
```

### 메서드 문서화

```java
/**
 * 새로운 사용자를 등록합니다.
 * 
 * <p>비밀번호는 SHA-256 해시로 자동 암호화됩니다.</p>
 * 
 * <p><strong>사용 예제:</strong></p>
 * <pre>{@code
 * User user = new User("john", "john@example.com", "password");
 * User registered = userService.registerUser(user);
 * }</pre>
 * 
 * @param user 등록할 사용자 정보
 * @return 등록된 사용자 객체 (ID 포함)
 * @throws IllegalArgumentException 사용자 이름이나 이메일이 이미 존재하는 경우
 * @see #findById(Long)
 * @see HashService#sha256(String)
 */
@Transactional
public User registerUser(User user) {
    // ...
}
```

### 필드 문서화

```java
/**
 * 사용자 고유 식별자
 */
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

/**
 * 기본 날짜 포맷 패턴
 * 
 * <p>형식: yyyy-MM-dd</p>
 */
public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
```

### Javadoc 태그 사용

| 태그 | 용도 | 예제 |
|------|------|------|
| `@param` | 매개변수 설명 | `@param username 사용자 이름` |
| `@return` | 반환값 설명 | `@return 사용자 객체` |
| `@throws` | 예외 설명 | `@throws IllegalArgumentException ...` |
| `@see` | 참조 링크 | `@see UserRepository` |
| `@since` | 도입 버전 | `@since 1.0.0` |
| `@deprecated` | 사용 중단 | `@deprecated Use {@link #newMethod()} instead` |
| `@author` | 작성자 | `@author Team Name` |
| `@version` | 버전 | `@version 1.0.0` |

## 🏗️ 클래스 구조 순서

```java
public class Example {
    // 1. 상수
    public static final String CONSTANT = "value";
    
    // 2. 정적 변수
    private static int staticField;
    
    // 3. 인스턴스 변수
    private String instanceField;
    
    // 4. 생성자
    public Example() { }
    
    public Example(String field) {
        this.instanceField = field;
    }
    
    // 5. 정적 메서드
    public static void staticMethod() { }
    
    // 6. public 메서드
    public void publicMethod() { }
    
    // 7. protected 메서드
    protected void protectedMethod() { }
    
    // 8. private 메서드
    private void privateMethod() { }
    
    // 9. getter/setter
    public String getInstanceField() {
        return instanceField;
    }
    
    public void setInstanceField(String instanceField) {
        this.instanceField = instanceField;
    }
}
```

## 🎯 모범 사례

### 예외 처리

```java
// Good: 구체적인 예외 처리
public User findUser(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found: " + id));
}

// Bad: 일반적인 예외 사용
public User findUser(Long id) throws Exception {
    return userRepository.findById(id)
        .orElseThrow(() -> new Exception("Error"));
}
```

### Null 처리

```java
// Good: Optional 사용
public Optional<User> findUser(Long id) {
    return userRepository.findById(id);
}

// Good: null 검증
public void processUser(User user) {
    if (user == null) {
        throw new IllegalArgumentException("User cannot be null");
    }
    // process...
}
```

### 불변성

```java
// Good: final 키워드 사용
public class UserService {
    private final UserRepository userRepository;
    private final HashService hashService;
    
    public UserService(UserRepository userRepository, 
                      HashService hashService) {
        this.userRepository = userRepository;
        this.hashService = hashService;
    }
}
```

### 리소스 관리

```java
// Good: try-with-resources 사용
try (InputStream input = new FileInputStream("file.txt");
     BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
    String line = reader.readLine();
    // process...
}

// Bad: 수동 close
InputStream input = new FileInputStream("file.txt");
try {
    // process...
} finally {
    input.close();
}
```

## 🧪 테스트 코드 규칙

### 테스트 메서드 네이밍

```java
// 패턴: should_ExpectedBehavior_When_StateUnderTest
@Test
void should_ReturnTrue_When_StringIsEmpty() {
    // given
    String input = "";
    StringUtils utils = new StringUtils();
    
    // when
    boolean result = utils.isEmpty(input);
    
    // then
    assertTrue(result);
}

// 또는: methodName_StateUnderTest_ExpectedBehavior
@Test
void isEmpty_EmptyString_ReturnsTrue() {
    // ...
}
```

### Given-When-Then 패턴

```java
@Test
void testUserRegistration() {
    // given (준비)
    User user = new User("john", "john@example.com", "password");
    
    // when (실행)
    User registered = userService.registerUser(user);
    
    // then (검증)
    assertNotNull(registered.getId());
    assertEquals("john", registered.getUsername());
}
```

## 📋 체크리스트

코드 커밋 전 확인사항:

- [ ] 네이밍 규칙을 따랐는가?
- [ ] Javadoc이 작성되었는가?
- [ ] 예외 처리가 적절한가?
- [ ] 테스트 코드가 작성되었는가?
- [ ] 불필요한 주석이 없는가?
- [ ] 코드 포맷팅이 일관적인가?
- [ ] 매직 넘버를 상수로 정의했는가?

## 🔧 도구 설정

### IntelliJ IDEA

```xml
<!-- Settings > Editor > Code Style > Java -->
<!-- Google Java Style Guide 또는 프로젝트 스타일 적용 -->
```

### Checkstyle

```xml
<!-- build.gradle -->
plugins {
    id 'checkstyle'
}

checkstyle {
    toolVersion = '10.12.4'
    configFile = file("${rootDir}/config/checkstyle/checkstyle.xml")
}
```

### SonarQube

```gradle
plugins {
    id 'org.sonarqube' version '4.4.1.3373'
}

sonarqube {
    properties {
        property "sonar.projectKey", "spring-multi-module"
        property "sonar.java.source", "21"
    }
}
```

## 📚 참고 자료

- [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- [Oracle Code Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-contents.html)
- [Effective Java](https://www.oreilly.com/library/view/effective-java/9780134686097/)
