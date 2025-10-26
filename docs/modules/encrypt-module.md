# Encrypt Module

암호화, 복호화, 해시 생성 등 보안 관련 기능을 제공하는 모듈입니다.

## 📦 의존성

```gradle
dependencies {
    implementation project(':encrypt-module')
}
```

이 모듈은 내부적으로 `util-module`에 의존합니다.

## 🔧 주요 기능

### AesEncryptionService

AES 알고리즘을 사용한 암호화/복호화 서비스입니다.

#### 주요 메서드

**generateSecretKey()**

AES 암호화에 사용할 비밀키를 생성합니다.

```java
AesEncryptionService aes = new AesEncryptionService();
String secretKey = aes.generateSecretKey();
// Base64로 인코딩된 256비트 키 반환
```

**encrypt(String plainText, String secretKey)**

평문 텍스트를 암호화합니다.

```java
String encrypted = aes.encrypt("Hello World", secretKey);
// Base64로 인코딩된 암호화된 텍스트 반환
```

**decrypt(String encryptedText, String secretKey)**

암호화된 텍스트를 복호화합니다.

```java
String decrypted = aes.decrypt(encrypted, secretKey);
// "Hello World"
```

### HashService

다양한 해시 알고리즘을 제공하는 서비스입니다.

#### 주요 메서드

**sha256(String input)**

SHA-256 해시를 생성합니다.

```java
HashService hashService = new HashService();
String hash = hashService.sha256("password123");
// 16진수 문자열로 인코딩된 해시 값 반환
```

**md5(String input)** ⚠️ Deprecated

MD5 해시를 생성합니다. (보안상 취약하므로 사용 권장하지 않음)

```java
@Deprecated
String hash = hashService.md5("password123");
```

!!! warning "보안 주의사항"
    MD5는 보안상 취약하므로 중요한 데이터에는 SHA-256을 사용하세요.

**base64Encode(String input)**

Base64 인코딩을 수행합니다.

```java
String encoded = hashService.base64Encode("Hello World");
// "SGVsbG8gV29ybGQ="
```

**base64Decode(String encoded)**

Base64 디코딩을 수행합니다.

```java
String decoded = hashService.base64Decode("SGVsbG8gV29ybGQ=");
// "Hello World"
```

## 📖 API 문서

더 자세한 API 문서는 [Javadoc](../javadoc/encrypt-module/index.html)을 참조하세요.

## 💡 사용 예제

### 데이터 암호화 및 복호화

```java
import com.example.encrypt.AesEncryptionService;
import org.springframework.stereotype.Service;

@Service
public class SecureDataService {
    
    private final AesEncryptionService aesService;
    private final String secretKey;
    
    public SecureDataService(AesEncryptionService aesService) {
        this.aesService = aesService;
        // 실제 운영에서는 환경 변수나 설정 파일에서 로드
        this.secretKey = aesService.generateSecretKey();
    }
    
    public String encryptSensitiveData(String data) {
        return aesService.encrypt(data, secretKey);
    }
    
    public String decryptSensitiveData(String encryptedData) {
        return aesService.decrypt(encryptedData, secretKey);
    }
}
```

### 비밀번호 해싱

```java
import com.example.encrypt.HashService;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    
    private final HashService hashService;
    
    public PasswordService(HashService hashService) {
        this.hashService = hashService;
    }
    
    public String hashPassword(String password) {
        return hashService.sha256(password);
    }
    
    public boolean verifyPassword(String password, String hashedPassword) {
        String hash = hashService.sha256(password);
        return hash.equals(hashedPassword);
    }
}
```

## 🔒 보안 권장사항

1. **비밀키 관리**: AES 비밀키는 환경 변수나 보안 볼트에 저장하세요.
2. **해시 알고리즘**: 비밀번호 해싱에는 SHA-256을 사용하세요. MD5는 사용하지 마세요.
3. **Salt 추가**: 실제 운영 환경에서는 비밀번호 해싱 시 Salt를 추가하세요.
4. **키 로테이션**: 주기적으로 암호화 키를 교체하세요.

## 🧪 테스트

```bash
./gradlew :encrypt-module:test
```

## 📚 참고 자료

- [AES 암호화 표준](https://en.wikipedia.org/wiki/Advanced_Encryption_Standard)
- [SHA-256 해시 함수](https://en.wikipedia.org/wiki/SHA-2)
- [BouncyCastle 라이브러리](https://www.bouncycastle.org/)
