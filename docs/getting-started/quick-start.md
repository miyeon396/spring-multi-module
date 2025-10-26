# 빠른 시작

프로젝트를 빠르게 시작하는 방법을 안내합니다.

## 🚀 5분 안에 시작하기

### 1. 프로젝트 클론 및 빌드

```bash
git clone https://github.com/yourusername/spring-multi-module.git
cd spring-multi-module
./gradlew build
```

### 2. Javadoc 생성

```bash
./gradlew generateAllJavadocs
```

### 3. 문서 확인

```bash
mkdocs serve
```

브라우저에서 `http://127.0.0.1:8000` 접속

## 💡 첫 번째 예제

### Util Module 사용하기

```java
import com.example.util.StringUtils;
import com.example.util.DateTimeUtils;

public class QuickStartExample {
    public static void main(String[] args) {
        StringUtils stringUtils = new StringUtils();
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        
        // 문자열 처리
        String input = "hello_world";
        String camelCase = stringUtils.toCamelCase(input);
        System.out.println(camelCase); // "helloWorld"
        
        // 날짜/시간 처리
        String now = dateTimeUtils.getCurrentDateTime();
        System.out.println(now); // "2025-10-25 14:30:00"
    }
}
```

### Encrypt Module 사용하기

```java
import com.example.encrypt.AesEncryptionService;
import com.example.encrypt.HashService;

public class EncryptionExample {
    public static void main(String[] args) {
        AesEncryptionService aes = new AesEncryptionService();
        HashService hash = new HashService();
        
        // AES 암호화
        String secretKey = aes.generateSecretKey();
        String encrypted = aes.encrypt("Hello World", secretKey);
        String decrypted = aes.decrypt(encrypted, secretKey);
        
        System.out.println("Original: Hello World");
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        
        // 해시 생성
        String password = "myPassword123";
        String hashed = hash.sha256(password);
        System.out.println("Hashed: " + hashed);
    }
}
```

### Domain Module 사용하기

```java
import com.example.domain.entity.User;
import com.example.domain.service.UserService;
import com.example.domain.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "com.example")
public class DomainExample {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DomainExample.class, args);
        UserService userService = context.getBean(UserService.class);
        
        // 사용자 등록
        User newUser = new User("johndoe", "john@example.com", "password123");
        User registered = userService.registerUser(newUser);
        
        System.out.println("User registered: " + registered.getUsername());
        
        // 사용자 인증
        boolean authenticated = userService.authenticate("johndoe", "password123");
        System.out.println("Authentication: " + authenticated);
    }
}
```

## 📁 프로젝트 구조 이해하기

```
spring-multi-module/
├── build.gradle                 # 루트 빌드 설정
├── settings.gradle              # 모듈 설정
├── mkdocs.yml                   # MkDocs 설정
├── docs/                        # 문서 소스
│   ├── index.md
│   ├── modules/
│   ├── getting-started/
│   └── javadoc/                # Javadoc 출력 디렉토리
├── util-module/                # 유틸리티 모듈
│   ├── build.gradle
│   └── src/
│       └── main/java/
│           └── com/example/util/
├── encrypt-module/              # 암호화 모듈
│   ├── build.gradle
│   └── src/
│       └── main/java/
│           └── com/example/encrypt/
└── domain-module/               # 도메인 모듈
    ├── build.gradle
    └── src/
        └── main/java/
            └── com/example/domain/
```

## 🔨 주요 Gradle 태스크

### 빌드 관련

```bash
# 전체 빌드
./gradlew build

# 클린 빌드
./gradlew clean build

# 특정 모듈 빌드
./gradlew :util-module:build
```

### 테스트 관련

```bash
# 전체 테스트
./gradlew test

# 특정 모듈 테스트
./gradlew :domain-module:test

# 테스트 리포트 생성
./gradlew test jacocoTestReport
```

### 문서화 관련

```bash
# 모든 Javadoc 생성
./gradlew generateAllJavadocs

# 특정 모듈 Javadoc 생성
./gradlew :util-module:generateModuleJavadoc

# 통합 Javadoc 생성
./gradlew generateAggregatedJavadoc
```

### 실행 관련

```bash
# 의존성 확인
./gradlew dependencies

# 프로젝트 구조 확인
./gradlew projects

# 사용 가능한 태스크 목록
./gradlew tasks
```

## 📚 학습 경로

1. **기초 이해**
   - [프로젝트 소개](introduction.md)
   - [설치 가이드](installation.md)

2. **모듈 학습**
   - [Util Module](../modules/util-module.md)
   - [Encrypt Module](../modules/encrypt-module.md)
   - [Domain Module](../modules/domain-module.md)

3. **심화 학습**
   - [아키텍처 가이드](../development/architecture.md)
   - [코딩 컨벤션](../development/coding-conventions.md)
   - [테스트 가이드](../development/testing.md)

## 🎯 일반적인 작업 흐름

### 1. 새로운 기능 개발

```bash
# 1. 브랜치 생성
git checkout -b feature/new-feature

# 2. 코드 작성
# ... 코딩 ...

# 3. 빌드 및 테스트
./gradlew clean build test

# 4. Javadoc 업데이트
./gradlew generateAllJavadocs

# 5. 커밋 및 푸시
git add .
git commit -m "Add new feature"
git push origin feature/new-feature
```

### 2. 문서 업데이트

```bash
# 1. docs/ 디렉토리에서 마크다운 파일 편집
# ... 문서 작성 ...

# 2. 로컬에서 미리보기
mkdocs serve

# 3. 빌드 확인
mkdocs build

# 4. 커밋
git add docs/
git commit -m "Update documentation"
```

### 3. 새 모듈 추가

```bash
# 1. settings.gradle에 모듈 추가
echo "include 'new-module'" >> settings.gradle

# 2. 모듈 디렉토리 생성
mkdir -p new-module/src/main/java/com/example/newmodule

# 3. build.gradle 작성
# ... 빌드 스크립트 작성 ...

# 4. 문서 작성
mkdir -p docs/modules
touch docs/modules/new-module.md

# 5. mkdocs.yml에 네비게이션 추가
# ... mkdocs.yml 수정 ...
```

## 🔍 다음 단계

프로젝트를 시작했다면 다음 문서를 확인하세요:

- **모듈 상세 가이드**: 각 모듈의 자세한 사용법을 학습하세요
- **API 문서**: Javadoc으로 생성된 API 레퍼런스를 참조하세요
- **개발 가이드**: 프로젝트 개발에 참여하는 방법을 알아보세요

## ❓ 자주 묻는 질문

### Q1. 모듈을 독립적으로 사용할 수 있나요?

네, 각 모듈은 독립적인 JAR로 빌드되며 다른 프로젝트에서 사용할 수 있습니다.

### Q2. Javadoc이 생성되지 않아요

`docs/javadoc/` 디렉토리가 존재하는지 확인하고, `./gradlew generateAllJavadocs`를 실행하세요.

### Q3. MkDocs에서 Javadoc 링크가 작동하지 않아요

Javadoc을 먼저 생성한 후 MkDocs를 빌드해야 합니다.

```bash
./gradlew generateAllJavadocs
mkdocs build
```

### Q4. GitHub Pages에 배포하려면?

```bash
mkdocs gh-deploy
```

더 많은 질문은 [GitHub Issues](https://github.com/yourusername/spring-multi-module/issues)에서 확인하세요.
