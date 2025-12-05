# 아이템 정보 관리 시스템 (Item Management CLI Project)

## 1. 프로젝트 개요
아이템 정보 관리 시스템은 콘솔(CLI) 환경에서 아이템을 등록, 조회, 수정, 삭제하고
검색·정렬 기능을 제공하는 Java 학습용 프로젝트입니다.
비전공자·입문자도 쉽게 개발할 수 있도록 협업 규칙과 구조를 상세하게 정리했습니다.

---

## 2. 주요 기능

### 필수 기능
- 아이템 등록(Create)
- 아이템 조회(Read)
- 아이템 수정(Update)
- 아이템 삭제(Delete)
- 검색 기능(이름, 카테고리 등)

### 선택 기능
- 아이템 정렬 기능(가격순, 이름순)
- 예외 처리 및 입력 검증
- 로그 파일 기록

---

## 3. 개발 환경
- Java 17 이상
- CLI 기반(콘솔 프로그램)
- 외부 라이브러리 미사용
- GitHub 기반 협업

---

## 4. 프로젝트 구조

src  
 └─ main/java  
     ├─ item  
     │   ├─ Item.java               (아이템 정보 클래스)  
     │   ├─ ItemRepository.java     (저장/검색/삭제 기능)  
     │   └─ ItemService.java        (비즈니스 로직)  
     ├─ ui  
     │   └─ ItemCLI.java            (CLI 메뉴, 사용자 입력 처리)  
     └─ common  
         └─ Validator.java          (입력 검증)

tests  
README.md

---

## 5. GitHub 협업 규칙

### 5.1 브랜치 전략
- main : 항상 실행 가능한 안정 버전
- develop : 개발 통합 브랜치
- feature/* : 기능 개발용 브랜치  
  예) feature/item-create, feature/search

### 5.2 이슈 사용 규칙
- 모든 개발은 반드시 Issue 생성 후 시작
- 반드시 담당자 지정
- 반드시 milestone 연결
- PR 완료 시 Issue 닫기 (close #이슈번호 사용)

### 5.3 마일스톤 구성

1) 프로젝트 구조 세팅  
목표: 패키지 구성, 템플릿 준비, 기본 CLI 틀 구현  

2) 핵심 기능 구현  
목표: CRUD, 검색, 저장·불러오기  

3) 확장 기능 구현  
목표: 정렬, 예외 처리, 로그 기록  

4) 통합 및 테스트  
목표: 버그 수정, 리팩터링, 최종 검증

### 5.4 커스텀 라벨
- feature : 새 기능 개발
- bug : 오류 수정
- refactor : 리팩터링
- enhancement : 기능 개선
- docs : 문서 작업
- urgent : 긴급 처리
- discussion : 논의 필요
- test : 테스트 코드 관련

---

## 6. 커밋 메시지 규칙

형식: prefix: 설명

prefix 목록:
- feat : 새로운 기능 추가
- fix : 버그 수정
- refactor : 코드 구조 개선
- docs : 문서 작성/수정
- test : 테스트 관련
- chore : 설정 파일, 빌드 작업 등 코드 외 작업

예시:
feat: 아이템 등록 기능 구현  
fix: 검색 시 null 에러 해결  
docs: README에 협업 규칙 추가

---

## 7. Pull Request(PR) 규칙

### PR 제목 규칙
[feat] 아이템 검색 기능 추가  
[fix] 검색 오류 수정

### PR 본문 템플릿
작업 내용  
- 무엇을 개발/수정했는지 상세히 작성  

관련 Issue  
- close #이슈번호  

변경 사항  
- 어떤 파일/로직이 변경되었는지 명시  

리뷰 요청  
- 확인해줬으면 하는 부분 기재  

### PR 규칙 요약
- 최소 1명 리뷰어 지정
- 코드 리뷰 승인 후 merge
- merge 대상: develop 브랜치
- main에 직접 merge 금지

---

## 8. 코드 컨벤션 (Java)

1) 네이밍  
- 클래스: PascalCase (ItemService)  
- 메서드/변수: camelCase (findItem, itemName)  
- 상수: UPPER_SNAKE_CASE (MAX_SIZE)

2) 들여쓰기  
- 스페이스 4칸

3) 메서드 길이  
- 너무 길어지면 private 메서드로 분리

4) import  
- 사용하지 않는 import 금지  
- * 와일드카드 import 금지

---

## 9. Ground Rules (팀 규칙)

- main 브랜치에 직접 push 금지
- 모든 개발은 Issue → 브랜치 → PR → 리뷰 → merge 순서
- commit은 작은 단위로, 의미 있는 기능 단위로 분리
- 매일 develop pull로 최신 코드 유지
- 혼자 판단 어려울 경우 반드시 Issue나 PR에 논의 남기기
- 매주 1회 짧은 진행 상황 공유(15분 이내)

---

## 10. WBS (Work Breakdown Structure)

1단계: 프로젝트 초기 세팅  
- GitHub 레포 생성  
- 패키지 구성  
- readme 작성  

2단계: 아이템 CRUD 개발  
- Item 클래스 생성  
- Repository CRUD 구현  
- Service CRUD 로직  

3단계: CLI 개발  
- 메뉴 출력  
- 사용자 입력 처리  
- 기능 연결  

4단계: 검색/정렬 기능 개발  
- 조건별 검색  
- 가격/이름 정렬  

5단계: 예외 처리 & Validator 개발  
- 입력 값 검증  
- 잘못된 입력 안내  

6단계: 통합 테스트  
- 전체 기능 점검  
- 버그 수정  

7단계: 문서화 & 배포  
- README 최종 수정  
- v1.0 릴리스 작성

---

## 11. 팀원 역할 분담(예시)

A : CRUD 기능  
B : 검색/정렬 기능  
C : CLI 개발  
D : 예외 처리 & 통합

(필요 시 서로 리뷰 교차)

---

## 12. 문의 / 개선 제안
개선 아이디어는 언제든지 Issue → discussion 라벨로 생성하여 팀원과 공유할 것.
