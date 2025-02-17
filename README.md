# AniMonster API 프로젝트

## 목차
1. [소개](#소개)
2. [시스템 아키텍처](#시스템-아키텍처)
3. [API 요청 처리 흐름](#api-요청-처리-흐름)
4. [주요 컴포넌트](#주요-컴포넌트)
5. [데이터베이스](#데이터베이스)
6. [보안](#보안)
7. [API 문서](#api-문서)


## 소개
AniMonster는 Spring 기반의 API 프로젝트입니다. 이 프로젝트는 효율적인 데이터 처리와 보안을 위한 다양한 컴포넌트를 포함하고 있습니다.

## 시스템 아키텍처
AniMonster 프로젝트는 다음과 같은 계층 구조로 설계되었습니다:

1. API 계층 (api)
2. 비즈니스 로직 계층 (business)
3. 데이터 접근 계층 (postgresql)

## API 요청 처리 흐름

1. **API 요청 진입**
    - Filter: 전역적인 필터링 작업 수행
    - Interceptor: JWT 인증 처리
    - Controller: 요청 매핑 및 초기 처리

2. **비즈니스 로직 처리**
    - Business: 핵심 비즈니스 로직 수행
    - Converter: 데이터 매핑 및 변환
    - Service: 세부 비즈니스 로직 처리

3. **데이터베이스 작업**
    - JPA: CRUD 연산 수행

4. **응답 처리**
    - Service: 결과 데이터 처리
    - Converter: 직렬화 작업
    - Controller: 최종 응답 생성
        - 필요시 Refresh Token 확인
        - ResponseAPI 변환

## 주요 컴포넌트

- **Filter**: 전역적인 요청/응답 처리
- **Interceptor**: JWT 기반 인증 처리
- **Controller**: 요청 매핑 및 응답 생성, 리프레시 토큰 처리
- **Business**: 핵심 비즈니스 로직
- **Converter**: 데이터 변환 및 매핑
- **Service**: 세부 비즈니스 로직
- **JPA Repository**: 데이터베이스 연산

## 데이터베이스
이 프로젝트는 JPA를 사용하여 데이터베이스와 상호작용합니다.

## 보안
JWT(JSON Web Token)를 사용한 인증 시스템이 구현되어 있습니다.

## API 문서
swagger를 참조하세요(실행 도메인(/swagger-ui/index.html#)


