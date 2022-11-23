## 개발 환경
* Gradle 7.1
* Spring Boot 2.7.5
* jdk 1.8
* JPA + querydsl
* DB : h2, redis
* docker

## Getting Started
* redis 설치 및 run

docker-compose 간편하게 사용가능 docker 설치방법은 [링크](https://docs.docker.com/get-docker/) 확인.

```shell
docker-compose up -d
```

* boot run

```shell
./gradlew build
./gradlew bootRun
```

## API 목록
* 로그인(토큰발급) : api/users/login
* 인증번호 발급 : api/sms/send
* 인증번호 확인 : api/sms/verify
* 회원가입 (POST) : api/users
* 회원수정 (PUT) : api/users
* 회원정보 : api/users/profile (인증필요)

## Running the tests
[swagger](http://localhost:8080/swagger-ui/index.html) 접속으로 사용하면 편합니다.

* login flow

1. jwt token 발급

```shell
curl -X POST "http://localhost:8080/api/users/login" -H "accept: application/json;charset=UTF-8" -H "Content-Type: application/json;charset=UTF-8" -d "{ \"email\": \"user@user.com\", \"password\": \"2222\"}" 
```

2. response > accessToken 저장
3. api 요청시 헤더 Authorization: bearer {발급받은accessToken} 추가.
4. api/users/profile API는 인증후 사용.

* 정보 확인하기

```
curl -X GET "http://localhost:8080/api/users/profile" -H "accept: application/json;charset=UTF-8" -H "Authorization: Bearer {token}"
```

* 회원가입 + 인증번호 flow

1. 인증번호 발급

```shell
curl -X POST "http://localhost:8080/api/sms/send" -H "accept: application/json;charset=UTF-8" -H "Content-Type: application/json;charset=UTF-8" -d "{ \"phoneNumber\": \"01033333333\", \"smsType\": \"SIGNUP\"}"
```

2. 인증번호 확인
```
curl -X POST "http://localhost:8080/api/sms/verify" -H "accept: application/json;charset=UTF-8" -H "Content-Type: application/json;charset=UTF-8" -d "{ \"certificationNumber\": \"861325\", \"phoneNumber\": \"01033333333\", \"smsType\": \"SIGNUP\"}"
```

3. 회원가입

```
curl -X POST "http://localhost:8080/api/users" -H "accept: application/json;charset=UTF-8" -H "Content-Type: application/json;charset=UTF-8" -d "{ \"email\": \"eyeeshot@gmail.com\", \"name\": \"김병주\", \"nickName\": \"김병주\", \"password\": \"1234\", \"phoneNumber\": \"01033333333\"}"
```

4. 회원가입시 인증번호 확인을 거쳤는지 확인함.

* 패스워드 변경 flow

1. 인증번호 발급

```shell
curl -X POST "http://localhost:8080/api/sms/send" -H "accept: application/json;charset=UTF-8" -H "Content-Type: application/json;charset=UTF-8" -d "{ \"phoneNumber\": \"01022222222\", \"smsType\": \"PASSWORDCHANGE\"}"
```

2. 인증번호 확인
```
curl -X POST "http://localhost:8080/api/sms/verify" -H "accept: application/json;charset=UTF-8" -H "Content-Type: application/json;charset=UTF-8" -d "{ \"certificationNumber\": \"574146\", \"phoneNumber\": \"01022222222\", \"smsType\": \"PASSWORDCHANGE\"}"
```

3. 패스워드 변경 요청

```
curl -X PUT "http://localhost:8080/api/users" -H "accept: application/json;charset=UTF-8" -H "Content-Type: application/json;charset=UTF-8" -d "{ \"password\": \"1234\", \"phoneNumber\": \"01022222222\"}"
```

4. 패스워드 변경시 인증번호 확인을 거쳤는지 확인함.

## 인증번호 확인방법
```shell
//docker-compose 사용 가정시

//도커 터미널 접속
docker exec -it redis bash

// 레디스 접속
redis-cli

// key 확인
keys *

// key 선택후 인증번호 확인
get key1
```