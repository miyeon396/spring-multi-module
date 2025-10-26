# Util Module

공통 유틸리티 기능을 제공하는 모듈입니다.

## 📦 의존성

```gradle
dependencies {
    implementation project(':util-module')
}
```

## 🔧 주요 기능

### StringUtils

문자열 처리를 위한 유틸리티 클래스입니다.

#### 주요 메서드

**isEmpty(String str)**

문자열이 비어있는지 확인합니다.

```java
StringUtils stringUtils = new StringUtils();
boolean result = stringUtils.isEmpty(""); // true
boolean result2 = stringUtils.isEmpty("hello"); // false
```

**isNotEmpty(String str)**

문자열이 비어있지 않은지 확인합니다.

```java
boolean result = stringUtils.isNotEmpty("hello"); // true
```

**toCamelCase(String str)**

문자열을 카멜 케이스로 변환합니다.

```java
String result = stringUtils.toCamelCase("hello_world"); // "helloWorld"
String result2 = stringUtils.toCamelCase("hello-world"); // "helloWorld"
```

### DateTimeUtils

날짜와 시간 처리를 위한 유틸리티 클래스입니다.

#### 주요 메서드

**getCurrentDateTime()**

현재 날짜와 시간을 기본 포맷으로 반환합니다.

```java
DateTimeUtils dateTimeUtils = new DateTimeUtils();
String now = dateTimeUtils.getCurrentDateTime();
// 예: "2025-10-25 14:30:00"
```

**formatDateTime(LocalDateTime dateTime, String pattern)**

LocalDateTime을 지정된 포맷으로 변환합니다.

```java
LocalDateTime now = LocalDateTime.now();
String formatted = dateTimeUtils.formatDateTime(now, "yyyy년 MM월 dd일");
// 예: "2025년 10월 25일"
```

**daysBetween(LocalDateTime start, LocalDateTime end)**

두 날짜 사이의 일수를 계산합니다.

```java
LocalDateTime start = LocalDateTime.of(2025, 1, 1, 0, 0);
LocalDateTime end = LocalDateTime.of(2025, 1, 10, 0, 0);
long days = dateTimeUtils.daysBetween(start, end); // 9
```

## 📖 API 문서

더 자세한 API 문서는 [Javadoc](../javadoc/util-module/index.html)을 참조하세요.

## 💡 사용 예제

### Spring Boot 애플리케이션에서 사용

```java
import com.example.util.StringUtils;
import com.example.util.DateTimeUtils;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    
    private final StringUtils stringUtils;
    private final DateTimeUtils dateTimeUtils;
    
    public MyService(StringUtils stringUtils, DateTimeUtils dateTimeUtils) {
        this.stringUtils = stringUtils;
        this.dateTimeUtils = dateTimeUtils;
    }
    
    public void processData(String input) {
        if (stringUtils.isNotEmpty(input)) {
            String camelCase = stringUtils.toCamelCase(input);
            String timestamp = dateTimeUtils.getCurrentDateTime();
            
            System.out.println("Processed: " + camelCase + " at " + timestamp);
        }
    }
}
```

## 🧪 테스트

```bash
./gradlew :util-module:test
```

## 📝 참고사항

- 모든 메서드는 null-safe하게 설계되었습니다.
- 잘못된 입력에 대해서는 IllegalArgumentException이 발생합니다.
- StringUtils는 Spring의 @Component로 등록되어 있어 의존성 주입이 가능합니다.
