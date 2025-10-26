# 설치 가이드

프로젝트를 설치하고 실행하는 방법을 안내합니다.

## 📋 사전 요구사항

### 필수 요구사항

- **Java 21 이상**: OpenJDK 또는 Oracle JDK
- **Gradle 8.5 이상**: 빌드 도구 (Gradle Wrapper 포함)
- **Git**: 소스 코드 관리

### 선택 사항

- **IDE**: IntelliJ IDEA, Eclipse, VSCode
- **MkDocs**: 문서 로컬 빌드 시 필요
- **Python 3.8+**: MkDocs 실행을 위해 필요

## 🔧 Java 설치 확인

```bash
java -version
```

예상 출력:
```
openjdk version "21.0.1" 2023-10-17
OpenJDK Runtime Environment (build 21.0.1+12-29)
```

Java 21이 설치되어 있지 않다면:

### macOS (Homebrew)
```bash
brew install openjdk@21
```

### Ubuntu/Debian
```bash
sudo apt update
sudo apt install openjdk-21-jdk
```

### Windows
[Oracle JDK](https://www.oracle.com/java/technologies/downloads/) 또는 [Adoptium](https://adoptium.net/)에서 다운로드

## 📥 프로젝트 클론

```bash
git clone https://github.com/yourusername/spring-multi-module.git
cd spring-multi-module
```

## 🏗️ 프로젝트 빌드

### Gradle Wrapper 사용 (권장)

프로젝트에는 Gradle Wrapper가 포함되어 있어 별도의 Gradle 설치 없이 빌드할 수 있습니다.

```bash
# Linux/macOS
./gradlew build

# Windows
gradlew.bat build
```

### 전체 빌드 및 테스트

```bash
./gradlew clean build test
```

### 특정 모듈만 빌드

```bash
# util-module만 빌드
./gradlew :util-module:build

# encrypt-module만 빌드
./gradlew :encrypt-module:build

# domain-module만 빌드
./gradlew :domain-module:build
```

## 📚 Javadoc 생성

### 모든 모듈의 Javadoc 생성

```bash
./gradlew generateAllJavadocs
```

생성된 Javadoc은 `docs/javadoc/` 디렉토리에 저장됩니다:

```
docs/javadoc/
├── util-module/
├── encrypt-module/
├── domain-module/
└── aggregated/
```

### 특정 모듈의 Javadoc 생성

```bash
./gradlew :util-module:generateModuleJavadoc
./gradlew :encrypt-module:generateModuleJavadoc
./gradlew :domain-module:generateModuleJavadoc
```

### 통합 Javadoc 생성

```bash
./gradlew generateAggregatedJavadoc
```

## 📖 MkDocs 설치 및 실행

### MkDocs 설치

```bash
# pip를 사용한 설치
pip install mkdocs mkdocs-material

# 또는 requirements.txt가 있는 경우
pip install -r requirements.txt
```

### 로컬에서 문서 서버 실행

```bash
mkdocs serve
```

브라우저에서 `http://127.0.0.1:8000` 접속

### 문서 빌드

```bash
mkdocs build
```

빌드된 문서는 `site/` 디렉토리에 생성됩니다.

## 🚀 IDE 설정

### IntelliJ IDEA

1. IntelliJ IDEA 실행
2. `File` > `Open` > 프로젝트 루트 디렉토리 선택
3. Gradle 프로젝트로 자동 인식되어 import됨
4. `File` > `Project Structure` > `Project SDK`를 Java 21로 설정

### Eclipse

1. Eclipse 실행
2. `File` > `Import` > `Gradle` > `Existing Gradle Project`
3. 프로젝트 루트 디렉토리 선택
4. Finish

### VSCode

1. VSCode 실행
2. `Extension Pack for Java` 설치
3. 프로젝트 폴더 열기
4. Java 21 경로를 `settings.json`에 설정:

```json
{
    "java.configuration.runtimes": [
        {
            "name": "JavaSE-21",
            "path": "/path/to/jdk-21",
            "default": true
        }
    ]
}
```

## ✅ 설치 확인

### 빌드 확인

```bash
./gradlew build
```

성공 시 출력:
```
BUILD SUCCESSFUL in 10s
```

### 테스트 실행

```bash
./gradlew test
```

### 모듈 확인

```bash
./gradlew projects
```

출력 예시:
```
Root project 'spring-multi-module'
+--- Project ':util-module'
+--- Project ':encrypt-module'
\--- Project ':domain-module'
```

## 🐛 문제 해결

### Gradle Wrapper 실행 권한 오류 (Linux/macOS)

```bash
chmod +x gradlew
```

### Java 버전 불일치

```bash
# 현재 Java 버전 확인
java -version

# JAVA_HOME 설정 (Linux/macOS)
export JAVA_HOME=/path/to/jdk-21
export PATH=$JAVA_HOME/bin:$PATH

# JAVA_HOME 설정 (Windows)
set JAVA_HOME=C:\Path\To\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%
```

### 빌드 캐시 문제

```bash
./gradlew clean build --no-build-cache
```

### 의존성 다운로드 실패

```bash
# 의존성 다시 다운로드
./gradlew build --refresh-dependencies
```

## 📞 도움말

문제가 지속되면 다음을 확인하세요:

- [FAQ](../development/testing.md)
- [GitHub Issues](https://github.com/yourusername/spring-multi-module/issues)
- [Gradle 공식 문서](https://docs.gradle.org/)

## 다음 단계

설치가 완료되었다면 [빠른 시작 가이드](quick-start.md)를 확인하세요.
