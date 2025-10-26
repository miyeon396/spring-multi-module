# Spring Multi-Module Project 사용 가이드

## 📁 프로젝트 구조

생성된 프로젝트는 다음과 같은 구조로 되어 있습니다:

```
multi-module-project/
├── build.gradle                           # 루트 빌드 설정
├── settings.gradle                        # 모듈 설정
├── gradle/wrapper/                        # Gradle Wrapper
├── mkdocs.yml                            # MkDocs 설정
├── requirements.txt                      # Python 의존성
├── README.md                             # 프로젝트 README
│
├── util-module/                          # 유틸리티 모듈
│   ├── build.gradle
│   └── src/main/java/com/example/util/
│       ├── StringUtils.java
│       └── DateTimeUtils.java
│
├── encrypt-module/                       # 암호화 모듈
│   ├── build.gradle
│   └── src/main/java/com/example/encrypt/
│       ├── AesEncryptionService.java
│       └── HashService.java
│
├── domain-module/                        # 도메인 모듈
│   ├── build.gradle
│   └── src/main/java/com/example/domain/
│       ├── entity/User.java
│       ├── repository/UserRepository.java
│       └── service/UserService.java
│
├── docs/                                 # MkDocs 문서
│   ├── index.md
│   ├── getting-started/
│   │   ├── introduction.md
│   │   ├── installation.md
│   │   └── quick-start.md
│   ├── modules/
│   │   ├── util-module.md
│   │   ├── encrypt-module.md
│   │   └── domain-module.md
│   ├── development/
│   │   ├── architecture.md
│   │   ├── coding-conventions.md
│   │   └── testing.md
│   ├── contributing.md
│   └── javadoc/                          # Javadoc 출력 디렉토리
│
└── .github/workflows/
    └── deploy-docs.yml                   # GitHub Actions 워크플로우
```

## 🚀 빠른 시작

### 1. 프로젝트 빌드

```bash
cd multi-module-project

# Linux/macOS
./gradlew build

# Windows
gradlew.bat build
```

### 2. Javadoc 생성

```bash
# 모든 모듈의 Javadoc 생성
./gradlew generateAllJavadocs
```

생성된 Javadoc 위치:
- `docs/javadoc/util-module/`
- `docs/javadoc/encrypt-module/`
- `docs/javadoc/domain-module/`
- `docs/javadoc/aggregated/` (통합 Javadoc)

### 3. MkDocs 문서 확인

```bash
# Python 가상환경 생성 (권장)
python -m venv venv
source venv/bin/activate  # Linux/macOS
# 또는
venv\Scripts\activate     # Windows

# 의존성 설치
pip install -r requirements.txt

# 로컬 서버 실행
mkdocs serve
```

브라우저에서 `http://127.0.0.1:8000` 접속하여 문서 확인

## 📚 문서화 워크플로우

### Javadoc → MkDocs 통합 과정

1. **Javadoc 생성**
   ```bash
   ./gradlew generateAllJavadocs
   ```
   
2. **docs/javadoc/에 생성됨**
   - Gradle 태스크가 자동으로 `docs/javadoc/` 디렉토리에 Javadoc 생성
   - 각 모듈별로 독립적인 Javadoc
   - 통합 Javadoc도 별도 생성

3. **MkDocs 빌드**
   ```bash
   mkdocs build
   ```
   
4. **site/ 디렉토리에 통합 사이트 생성**
   - Markdown 문서와 Javadoc이 함께 포함됨

### MkDocs 네비게이션 구조

`mkdocs.yml`에 정의된 네비게이션:

```yaml
nav:
  - 홈: index.md
  - 시작하기:
      - 소개: getting-started/introduction.md
      - 설치: getting-started/installation.md
      - 빠른 시작: getting-started/quick-start.md
  - 모듈:
      - Util Module: modules/util-module.md
      - Encrypt Module: modules/encrypt-module.md
      - Domain Module: modules/domain-module.md
  - API 문서:
      - Util Module API: javadoc/util-module/index.html
      - Encrypt Module API: javadoc/encrypt-module/index.html
      - Domain Module API: javadoc/domain-module/index.html
      - 통합 API: javadoc/aggregated/index.html
```

## 🌐 GitHub Pages 배포

### 설정 방법

1. **GitHub 저장소 생성**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git branch -M main
   git remote add origin https://github.com/yourusername/spring-multi-module.git
   git push -u origin main
   ```

2. **GitHub Pages 활성화**
   - GitHub 저장소 > Settings > Pages
   - Source: Deploy from a branch
   - Branch: `gh-pages` 선택
   - Save

3. **자동 배포**
   - `main` 브랜치에 푸시하면 자동으로 GitHub Actions 실행
   - Javadoc 생성 → MkDocs 빌드 → GitHub Pages 배포
   - 배포 URL: `https://yourusername.github.io/spring-multi-module/`

### 수동 배포

```bash
# Javadoc 생성
./gradlew generateAllJavadocs

# MkDocs로 GitHub Pages에 배포
mkdocs gh-deploy
```

## 🔧 커스터마이징

### 새 모듈 추가

1. **settings.gradle에 모듈 추가**
   ```gradle
   include 'new-module'
   ```

2. **모듈 디렉토리 생성**
   ```bash
   mkdir -p new-module/src/main/java/com/example/newmodule
   ```

3. **build.gradle 작성**
   ```gradle
   dependencies {
       implementation project(':util-module')
       // 필요한 의존성 추가
   }
   
   tasks.named('bootJar') {
       enabled = false
   }
   
   tasks.named('jar') {
       enabled = true
   }
   ```

4. **문서 추가**
   - `docs/modules/new-module.md` 작성
   - `mkdocs.yml`에 네비게이션 추가

### Javadoc 커스터마이징

`build.gradle`에서 Javadoc 옵션 수정:

```gradle
tasks.register('generateModuleJavadoc', Javadoc) {
    options {
        encoding = 'UTF-8'
        charSet = 'UTF-8'
        docEncoding = 'UTF-8'
        links 'https://docs.oracle.com/en/java/javase/21/docs/api/'
        
        // 추가 옵션
        windowTitle = 'My Project API'
        doctitle = 'My Project API Documentation'
        header = '<b>My Project</b>'
        bottom = 'Copyright © 2025 My Company'
    }
}
```

### MkDocs 테마 커스터마이징

`mkdocs.yml`에서 테마 수정:

```yaml
theme:
  name: material
  palette:
    primary: blue  # 원하는 색상으로 변경
    accent: amber
  logo: assets/logo.png  # 로고 추가
  favicon: assets/favicon.ico
```

## 📝 개발 워크플로우

### 1. 새 기능 개발

```bash
# 브랜치 생성
git checkout -b feature/new-feature

# 코드 작성
# ...

# 빌드 및 테스트
./gradlew clean build test

# Javadoc 업데이트
./gradlew generateAllJavadocs

# 커밋
git add .
git commit -m "feat: Add new feature"
git push origin feature/new-feature
```

### 2. 문서 업데이트

```bash
# docs/ 디렉토리에서 마크다운 파일 편집
vim docs/modules/new-module.md

# 로컬에서 미리보기
mkdocs serve

# 커밋
git add docs/
git commit -m "docs: Update module documentation"
```

### 3. 배포

```bash
# main 브랜치에 머지되면 자동으로 GitHub Actions 실행
# 또는 수동 배포
mkdocs gh-deploy
```

## 🧪 테스트

### 단위 테스트

```bash
# 전체 테스트
./gradlew test

# 특정 모듈
./gradlew :util-module:test

# 커버리지 리포트
./gradlew jacocoTestReport
# 리포트 위치: build/reports/jacoco/test/html/index.html
```

### 통합 테스트

```bash
# Spring Boot 통합 테스트
./gradlew :domain-module:test
```

## 💡 유용한 팁

### Gradle 태스크 확인

```bash
# 사용 가능한 모든 태스크 보기
./gradlew tasks

# 특정 그룹의 태스크만 보기
./gradlew tasks --group documentation
```

### 의존성 트리 확인

```bash
# 전체 의존성 트리
./gradlew dependencies

# 특정 모듈의 의존성
./gradlew :domain-module:dependencies
```

### 빌드 캐시 정리

```bash
./gradlew clean
```

### IntelliJ IDEA에서 열기

1. IntelliJ IDEA 실행
2. File > Open > `multi-module-project` 디렉토리 선택
3. Gradle 프로젝트로 자동 인식

## 📞 문제 해결

### Javadoc이 생성되지 않을 때

```bash
# docs/javadoc 디렉토리 확인
ls -la docs/javadoc/

# 직접 생성 시도
./gradlew generateAllJavadocs --info
```

### MkDocs 빌드 오류

```bash
# 의존성 재설치
pip install -r requirements.txt --force-reinstall

# 빌드 다시 시도
mkdocs build --clean
```

### Gradle 빌드 실패

```bash
# Gradle Wrapper 권한 부여 (Linux/macOS)
chmod +x gradlew

# 캐시 삭제 후 재빌드
./gradlew clean build --refresh-dependencies
```

## 🎯 다음 단계

1. 프로젝트를 GitHub에 푸시
2. GitHub Pages 설정
3. 커스텀 도메인 연결 (선택사항)
4. CI/CD 파이프라인 확장

프로젝트 구조와 문서화 시스템이 완성되었습니다!
