# External-Service
> 외부 서비스와 통신하는 모듈

## Google Cloud Storage 로컬 테스트

로컬 환경 변수에 `GOOGLE_APPLICATION_CREDENTIALS` 를 추가한다.

```bash
export GOOGLE_APPLICATION_CREDENTIALS={Dev 서비스 어카운트 키 경로}
```

그 다음, `application-external-gcp.yaml`에 로컬 프로필 설정을 dev-storage를 가르키도록 변경한다.

```yaml
spring:
  config:
    activate:
      on-profile: local
  cloud:
    gcp:
      storage:
        enabled: true
        project-id: "dev-melon-fan-platform-project"
        bucket: "dev-melon-fan-lounge-static-resources-bucket"
```

로컬 실행이 끝나면 `application-external-gcp.yaml`을 원복한다.
