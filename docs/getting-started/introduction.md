# 소개

Spring Boot 3.x와 Java 21을 사용한 멀티모듈 프로젝트입니다.

## 🎯 프로젝트 목적

이 프로젝트는 다음과 같은 목적을 가지고 있습니다:

- Spring Boot 멀티모듈 아키텍처의 베스트 프랙티스 제시
- 모듈 간 의존성 관리 방법 시연
- 재사용 가능한 컴포넌트 설계 예제 제공
- 문서화 자동화 (MkDocs + Javadoc) 구현

## 🏗️ 기술 스택

### Backend
- **Java**: 21
- **Spring Boot**: 3.2.0+
- **Gradle**: 8.5+
- **JPA/Hibernate**: 데이터 영속성
- **H2 Database**: 개발 및 테스트용 인메모리 데이터베이스

### Security & Encryption
- **BouncyCastle**: 암호화 라이브러리

### Documentation
- **MkDocs**: 문서 생성
- **Material Theme**: MkDocs 테마
- **Javadoc**: API 문서 생성

## 📦 모듈 개요

### 1. Util Module
공통 유틸리티 기능을 제공하는 기본 모듈입니다.

**주요 기능:**
- 문자열 처리 유틸리티
- 날짜/시간 처리 유틸리티

**의존성:** 없음 (독립 모듈)

### 2. Encrypt Module
암호화 및 보안 관련 기능을 제공하는 모듈입니다.

**주요 기능:**
- AES 암호화/복호화
- SHA-256, MD5 해시 생성
- Base64 인코딩/디코딩

**의존성:** Util Module

### 3. Domain Module
비즈니스 도메인 모델과 로직을 담당하는 모듈입니다.

**주요 기능:**
- User 엔티티 및 리포지토리
- 사용자 관리 서비스
- 데이터베이스 연동

**의존성:** Util Module, Encrypt Module

## 🔄 모듈 의존성 그래프

```
┌─────────────────┐
│  Util Module    │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Encrypt Module  │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Domain Module  │
└─────────────────┘
```

## ✨ 주요 특징

1. **모듈화된 구조**: 기능별로 분리된 모듈 구조
2. **의존성 주입**: Spring의 DI 컨테이너 활용
3. **자동 문서화**: Gradle 태스크를 통한 Javadoc 자동 생성
4. **타입 안정성**: Java 21의 최신 기능 활용
5. **테스트 지원**: 각 모듈별 독립적인 테스트 가능

## 🎓 학습 목표

이 프로젝트를 통해 다음을 학습할 수 있습니다:

- Spring Boot 멀티모듈 프로젝트 구성 방법
- Gradle을 이용한 모듈 간 의존성 관리
- MkDocs와 Javadoc을 이용한 문서화 자동화
- 모듈화된 아키텍처 설계 원칙
- Spring Boot 3.x와 Java 21의 최신 기능

## 📖 다음 단계

- [설치 가이드](installation.md): 프로젝트 설치 방법
- [빠른 시작](quick-start.md): 프로젝트 실행 및 기본 사용법
- [모듈 가이드](../modules/util-module.md): 각 모듈의 상세 설명

## 🤝 커뮤니티

질문이나 제안사항이 있으시면 GitHub Issues를 통해 문의해주세요.

- GitHub: [https://github.com/yourusername/spring-multi-module](https://github.com/yourusername/spring-multi-module)
- Issues: [https://github.com/yourusername/spring-multi-module/issues](https://github.com/yourusername/spring-multi-module/issues)
