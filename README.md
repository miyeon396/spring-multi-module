# Spring Multi-Module Project

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Gradle](https://img.shields.io/badge/Gradle-8.5-blue.svg)](https://gradle.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Spring Boot 3.x와 Java 21을 사용한 멀티모듈 프로젝트입니다.

## 📋 개요

이 프로젝트는 Spring Boot 기반의 멀티모듈 아키텍처 예제로, 재사용 가능한 모듈 구조와 체계적인 문서화를 보여줍니다.

### 주요 특징

- 🏗️ **모듈화된 아키텍처**: 기능별로 분리된 독립적인 모듈
- 📚 **자동 문서화**: MkDocs + Javadoc 통합'
- 🔒 **보안 기능**: AES 암호화, SHA-256 해시
- 🗄️ **JPA 통합**: Spring Data JPA를 활용한 데이터 관리
- ✅ **높은 테스트 커버리지**: 단위/통합 테스트 포함

## 🏗️ 프로젝트 구조

```
spring-multi-module/
├── util-module/          # 공통 유틸리티
├── encrypt-module/       # 암호화 기능
├── domain-module/        # 도메인 모델 및 비즈니스 로직
└── docs/                 # MkDocs 문서
```

## 📦 모듈 설명

| 모듈 | 설명 | 의존성 |
|------|------|--------|
| **util-module** | 문자열, 날짜/시간 처리 유틸리티 | - |
| **encrypt-module** | AES 암호화, SHA-256 해시 | util-module |
| **domain-module** | User 엔티티, 리포지토리, 서비스 | util-module, encrypt-module |

## 🚀 시작하기

### 요구사항

- Java 21 이상
- Gradle 8.5 이상
- Python 3.8+ (문서 빌드 시)

### 설치

```bash
# 저장소 클론
git clone https://github.com/yourusername/spring-multi-module.git
cd spring-multi-module

# 빌드
./gradlew build

# 테스트
./gradlew test
```

### Javadoc 생성

```bash
# 모든 모듈의 Javadoc 생성
./gradlew generateAllJavadocs

# 특정 모듈만
./gradlew :util-module:generateModuleJavadoc
./gradlew :encrypt-module:generateModuleJavadoc
./gradlew :domain-module:generateModuleJavadoc
```

생성된 Javadoc은 `docs/javadoc/` 디렉토리에 저장됩니다.

### 문서 빌드

```bash
# MkDocs 설치
pip install mkdocs mkdocs-material mkdocs-minify-plugin

# 로컬 서버 실행
mkdocs serve

# 문서 빌드
mkdocs build
```

브라우저에서 `http://127.0.0.1:8000` 접속

## 📚 문서

- 📖 **전체 문서**: [https://yourusername.github.io/spring-multi-module](https://yourusername.github.io/spring-multi-module)
- 🔧 **Util Module**: [문서](docs/modules/util-module.md) | [API](docs/javadoc/util-module/index.html)
- 🔒 **Encrypt Module**: [문서](docs/modules/encrypt-module.md) | [API](docs/javadoc/encrypt-module/index.html)
- 🗄️ **Domain Module**: [문서](docs/modules/domain-module.md) | [API](docs/javadoc/domain-module/index.html)

## 💡 사용 예제

### Util Module

```java
import com.example.util.StringUtils;
import com.example.util.DateTimeUtils;

StringUtils stringUtils = new StringUtils();
String camelCase = stringUtils.toCamelCase("hello_world");
// Result: "helloWorld"

DateTimeUtils dateTimeUtils = new DateTimeUtils();
String now = dateTimeUtils.getCurrentDateTime();
// Result: "2025-10-25 14:30:00"
```

### Encrypt Module

```java
import com.example.encrypt.AesEncryptionService;
import com.example.encrypt.HashService;

AesEncryptionService aes = new AesEncryptionService();
String secretKey = aes.generateSecretKey();
String encrypted = aes.encrypt("Hello World", secretKey);
String decrypted = aes.decrypt(encrypted, secretKey);

HashService hash = new HashService();
String hashed = hash.sha256("password123");
```

### Domain Module

```java
import com.example.domain.entity.User;
import com.example.domain.service.UserService;

@Autowired
private UserService userService;

// 사용자 등록
User user = new User("john", "john@example.com", "password123");
User registered = userService.registerUser(user);

// 사용자 인증
boolean authenticated = userService.authenticate("john", "password123");
```

## 🔨 주요 Gradle 태스크

```bash
# 빌드
./gradlew build

# 테스트
./gradlew test

# Javadoc 생성
./gradlew generateAllJavadocs

# 통합 Javadoc 생성
./gradlew generateAggregatedJavadoc

# 커버리지 리포트
./gradlew jacocoTestReport

# 특정 모듈 빌드
./gradlew :util-module:build
```

## 🧪 테스트

```bash
# 전체 테스트
./gradlew test

# 커버리지 리포트
./gradlew test jacocoTestReport

# 특정 모듈 테스트
./gradlew :domain-module:test
```

테스트 커버리지 리포트: `build/reports/jacoco/test/html/index.html`

## 📊 기술 스택

### Backend
- Java 21
- Spring Boot 3.2.0+
- Spring Data JPA
- Gradle 8.5+

### Security
- BouncyCastle (암호화)

### Database
- H2 Database (개발/테스트)

### Documentation
- MkDocs
- Material for MkDocs
- Javadoc

### Testing
- JUnit 5
- Mockito
- Spring Boot Test
- JaCoCo

## 🤝 기여하기

기여를 환영합니다! [기여 가이드](docs/contributing.md)를 참조해주세요.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 라이센스

이 프로젝트는 MIT 라이센스를 따릅니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.

## 👥 Contributors

감사합니다! 모든 기여자분들께 감사드립니다.

<!-- ALL-CONTRIBUTORS-LIST:START -->
<!-- ALL-CONTRIBUTORS-LIST:END -->

## 📞 연락처

프로젝트 관리자 - [@yourusername](https://github.com/yourusername)

프로젝트 링크: [https://github.com/yourusername/spring-multi-module](https://github.com/yourusername/spring-multi-module)

## 🙏 감사의 말

- [Spring Boot](https://spring.io/projects/spring-boot)
- [MkDocs](https://www.mkdocs.org/)
- [Material for MkDocs](https://squidfunk.github.io/mkdocs-material/)
- [BouncyCastle](https://www.bouncycastle.org/)

---

⭐ 이 프로젝트가 도움이 되었다면 Star를 눌러주세요!
