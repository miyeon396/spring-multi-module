# Spring Multi-Module Project

Spring Boot 3.x와 Java 21을 사용한 멀티모듈 프로젝트입니다.

## 📋 프로젝트 개요

이 프로젝트는 Spring Boot 기반의 멀티모듈 아키텍처를 보여주는 예제 프로젝트입니다. 각 모듈은 독립적인 기능을 제공하며, 서로 의존성을 가지고 있습니다.

## 🏗️ 모듈 구조

```
spring-multi-module/
├── util-module/          # 공통 유틸리티
├── encrypt-module/       # 암호화 기능
└── domain-module/        # 도메인 모델 및 비즈니스 로직
```

### Util Module
문자열 처리, 날짜/시간 처리 등 공통 유틸리티 기능을 제공합니다.

- StringUtils: 문자열 처리 유틸리티
- DateTimeUtils: 날짜/시간 처리 유틸리티

[자세히 보기](modules/util-module.md) | [API 문서](javadoc/util-module/index.html)

### Encrypt Module
암호화, 복호화, 해시 생성 등 보안 관련 기능을 제공합니다.

- AesEncryptionService: AES 암호화/복호화
- HashService: 해시 생성 (SHA-256, MD5, Base64)

[자세히 보기](modules/encrypt-module.md) | [API 문서](javadoc/encrypt-module/index.html)

### Domain Module
사용자 관리를 위한 도메인 모델, 리포지토리, 서비스를 제공합니다.

- User: 사용자 엔티티
- UserRepository: 사용자 데이터 액세스
- UserService: 사용자 비즈니스 로직

[자세히 보기](modules/domain-module.md) | [API 문서](javadoc/domain-module/index.html)

## 🚀 빠른 시작

### 요구사항

- Java 21 이상
- Gradle 8.5 이상

### 빌드

```bash
./gradlew build
```

### Javadoc 생성

```bash
# 모든 모듈의 Javadoc 생성
./gradlew generateAllJavadocs

# 특정 모듈의 Javadoc 생성
./gradlew :util-module:generateModuleJavadoc
./gradlew :encrypt-module:generateModuleJavadoc
./gradlew :domain-module:generateModuleJavadoc

# 통합 Javadoc 생성
./gradlew generateAggregatedJavadoc
```

생성된 Javadoc은 `docs/javadoc/` 디렉토리에 저장됩니다.

## 📚 문서

- [시작하기](getting-started/introduction.md)
- [모듈 가이드](modules/util-module.md)
- [개발 가이드](development/architecture.md)
- [API 문서](javadoc/util-module/index.html)

## 🤝 기여하기

프로젝트에 기여하고 싶으시다면 [기여 가이드](contributing.md)를 참고해주세요.

## 📝 라이센스

이 프로젝트는 MIT 라이센스를 따릅니다.
