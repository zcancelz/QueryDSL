# QueryDSL Sample

QueryDSL 을 이용한 API Sample.

## 연동 규격
JWT 를 이요하여 권한 체크.

### JWT Spec
[jwt 표준 스펙](https://jwt.io/introduction/)을 따른다.  
암호화에 필요한 secret token은 PallyCon에서 따로 발급한 키를 사용한다.
```
header + "." + payload + "." + signature
```

### payload Spec
Base64(json payload)
```json
{
    "sub" : "QuesDSLSample",
    "aud" : "brown",
    "iss" : "Brown",
    "iat" : 20190801175900,
    "admin": true,
    "userId" : "{{adminUser}}"
}
```

## API
### 기본 Response 규격
- Response Type : Json

| Key | type | Value |
|:---|----|----|
|error_code| String | 0000: 성공 / 이외 실패|
|error_message| String | 에러 메세지 |
|data| Json | Api 호출 결과물 |
