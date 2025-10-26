# 기여하기

Spring Multi-Module 프로젝트에 기여해 주셔서 감사합니다! 🎉

## 🤝 기여 방법

### 1. 이슈 생성

버그 리포트나 기능 제안은 [GitHub Issues](https://github.com/yourusername/spring-multi-module/issues)에서 생성해주세요.

#### 버그 리포트 템플릿

```markdown
## 버그 설명
[버그에 대한 명확하고 간결한 설명]

## 재현 방법
1. ...
2. ...
3. ...

## 예상 동작
[예상했던 동작 설명]

## 실제 동작
[실제로 발생한 동작 설명]

## 환경
- OS: [예: Ubuntu 22.04]
- Java 버전: [예: 21.0.1]
- Gradle 버전: [예: 8.5]

## 추가 정보
[스크린샷, 로그 등]
```

#### 기능 제안 템플릿

```markdown
## 기능 설명
[제안하는 기능에 대한 설명]

## 동기
[이 기능이 왜 필요한지]

## 예상 구현
[어떻게 구현될 수 있는지]

## 대안
[고려한 다른 대안들]
```

### 2. Fork & Clone

```bash
# Fork 저장소를 로컬에 클론
git clone https://github.com/your-username/spring-multi-module.git
cd spring-multi-module

# 원본 저장소를 upstream으로 추가
git remote add upstream https://github.com/yourusername/spring-multi-module.git
```

### 3. 브랜치 생성

```bash
# 최신 코드로 업데이트
git checkout main
git pull upstream main

# 새 브랜치 생성
git checkout -b feature/your-feature-name
# 또는
git checkout -b fix/bug-description
```

브랜치 명명 규칙:
- `feature/기능명`: 새로운 기능
- `fix/버그명`: 버그 수정
- `docs/문서명`: 문서 수정
- `refactor/내용`: 리팩토링
- `test/테스트명`: 테스트 추가/수정

### 4. 코드 작성

- [코딩 컨벤션](development/coding-conventions.md) 준수
- 의미 있는 커밋 메시지 작성
- 테스트 코드 작성

### 5. 테스트 실행

```bash
# 전체 테스트
./gradlew test

# 코드 스타일 검사
./gradlew checkstyleMain checkstyleTest

# 커버리지 확인
./gradlew jacocoTestReport
```

### 6. 커밋

커밋 메시지 컨벤션:

```
<type>(<scope>): <subject>

<body>

<footer>
```

**Type:**
- `feat`: 새로운 기능
- `fix`: 버그 수정
- `docs`: 문서 수정
- `style`: 코드 포맷팅
- `refactor`: 리팩토링
- `test`: 테스트 추가/수정
- `chore`: 빌드 작업, 패키지 매니저 설정 등

**예시:**

```bash
git commit -m "feat(encrypt): Add RSA encryption support

- Implement RSA key generation
- Add encrypt/decrypt methods
- Include unit tests

Closes #123"
```

### 7. Push & Pull Request

```bash
# 변경사항 푸시
git push origin feature/your-feature-name
```

GitHub에서 Pull Request 생성:

1. Fork한 저장소로 이동
2. "Compare & pull request" 클릭
3. PR 템플릿 작성
4. "Create pull request" 클릭

#### PR 템플릿

```markdown
## 변경사항
[무엇을 변경했는지 설명]

## 변경 이유
[왜 이 변경이 필요한지]

## 관련 이슈
Closes #이슈번호

## 테스트
- [ ] 단위 테스트 추가/수정
- [ ] 통합 테스트 추가/수정
- [ ] 수동 테스트 완료

## 체크리스트
- [ ] 코딩 컨벤션 준수
- [ ] Javadoc 작성
- [ ] 테스트 작성
- [ ] 문서 업데이트
- [ ] 커버리지 80% 이상

## 스크린샷 (해당하는 경우)
[스크린샷 첨부]
```

## 📋 개발 가이드라인

### 코드 품질

1. **코딩 스타일**
   - [Java 코딩 컨벤션](development/coding-conventions.md) 준수
   - 일관된 들여쓰기 (4칸 공백)
   - 의미 있는 변수명 사용

2. **Javadoc**
   - 모든 public 클래스/메서드에 Javadoc 작성
   - 매개변수, 반환값, 예외 문서화
   - 사용 예제 포함 (가능한 경우)

3. **테스트**
   - 새로운 기능에는 테스트 필수
   - 커버리지 80% 이상 유지
   - Given-When-Then 패턴 사용

### 모듈 추가

새로운 모듈을 추가하는 경우:

1. `settings.gradle`에 모듈 추가
2. 모듈 `build.gradle` 작성
3. 패키지 구조 생성
4. 모듈 문서 작성 (`docs/modules/`)
5. `mkdocs.yml`에 네비게이션 추가

### 문서화

1. **모듈 문서**
   - 각 모듈의 목적과 사용법 설명
   - 코드 예제 포함
   - API 문서 링크 추가

2. **Javadoc 생성**
   ```bash
   ./gradlew generateAllJavadocs
   ```

3. **MkDocs 미리보기**
   ```bash
   mkdocs serve
   ```

## 🔍 코드 리뷰 프로세스

1. **자동 검사**
   - CI/CD 파이프라인 통과
   - 테스트 성공
   - 코드 스타일 검사 통과

2. **리뷰어 검토**
   - 코드 품질
   - 테스트 커버리지
   - 문서 완성도
   - 아키텍처 일관성

3. **피드백 반영**
   - 리뷰 코멘트 확인
   - 수정 후 재푸시
   - 승인 후 머지

## 🎨 커밋 아이콘

커밋 메시지에 이모지 사용 (선택사항):

- ✨ `:sparkles:` 새로운 기능
- 🐛 `:bug:` 버그 수정
- 📝 `:memo:` 문서 추가/수정
- 🎨 `:art:` 코드 포맷팅
- ♻️ `:recycle:` 리팩토링
- ✅ `:white_check_mark:` 테스트 추가
- 🔧 `:wrench:` 설정 파일 수정
- 🚀 `:rocket:` 성능 개선

## 💬 커뮤니케이션

### 질문하기

질문이 있으시면:

1. 먼저 [문서](index.md)와 [이슈](https://github.com/yourusername/spring-multi-module/issues)를 검색
2. 기존 이슈가 없으면 새 이슈 생성
3. Discord/Slack 채널 활용 (있는 경우)

### 토론 참여

- GitHub Discussions에서 의견 공유
- PR 리뷰에 적극 참여
- 다른 기여자들과 협업

## 📜 행동 강령

### 우리의 약속

모두가 환영받는 커뮤니티를 만들기 위해:

- 존중하고 포용적인 언어 사용
- 다른 관점과 경험 존중
- 건설적인 비판 수용
- 커뮤니티에 최선인 것에 집중

### 금지 행동

- 성적인 언어/이미지 사용
- 모욕적/경멸적 발언
- 괴롭힘 (공개/비공개)
- 개인정보 무단 공개
- 비전문적 행동

## 🏆 기여자 인정

모든 기여자는 다음과 같이 인정됩니다:

- README.md의 Contributors 섹션에 추가
- 릴리스 노트에 기여 내용 명시
- 특별한 기여에 대한 배지 부여

## 📞 도움이 필요하신가요?

- 📧 Email: team@example.com
- 💬 Discussions: [GitHub Discussions](https://github.com/yourusername/spring-multi-module/discussions)
- 🐛 Issues: [GitHub Issues](https://github.com/yourusername/spring-multi-module/issues)

## 🙏 감사합니다!

여러분의 기여가 이 프로젝트를 더 좋게 만듭니다. 작은 기여라도 큰 차이를 만듭니다!
