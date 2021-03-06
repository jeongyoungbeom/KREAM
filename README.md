# KREAM 리셀사이트

### KREAM 리셀 사이트

* 기간 : 2021.08.15 ~ 2021.10.15(60일간)
* 환경 : Apache Tomcat 8.0, Chrome 브라우저
* 주제 : 아이템 리셀
* 개발 : Intellij, Oracle
* 사용 언어 : JAVA(JDK 11), Oracle SQL, Html5, CSS3, Javascript ES6
* 사용 기술 : JDBC, thymeleaf, jQuery, Ajax, Spring boot 2.5, Mail API, JPA/Hibernate, Rest Api, queryDSL

## 현재 코드 수정중
### 수정 일자
- 2022년 01월 17일 수정
  - AddressService
  - CustomerService
  - ProductService
  - PurchaseService
  - StyleimgService
  - TransactionService
  - WithdrawalService
  - DashBoardService
  - FollowService
  - HashTagService
  - ProductQnaService

- 2022년 01월 18일 수정
  - ProductService
  - ProImgService
  - ProSizeService
  - ProductTagService
  - StyleService(수정중)
- 2022년 01월 21일 수정
  - 나머지 Service 모두 수정완료

# 프로젝트 상세내용
## 구현목표
> 사용자 페이지
1. 입찰 시스템 구현(구매입찰/판매입찰)
2. 상품별 최근/평균 가격 구현
3. SNS와 같은 게시물 등록/수정 기능 구현
4. 해당 게시물과 댓글에 좋아요/댓글/팔로우 기능 구현
5. 세분화된 필터(다중필터)

>관리자 페이지
1. 회원관리(전체회원/탈퇴회원/블랙리스트)
2. 배송현황 관리
3. 문의 및 공지사항 관리
4. 상품관리(상품 등록/수정 및 게시상태)

## 나의 역할
 - 백엔드 모든부분을 맡아서 진행하였습니다.

## 문제점
- 코드가 깔끔하지 않아서 가독성이 조금 떨어진것 같습니다.
- 다양한 API활용의 어려움을 겪었습니다.
- 백엔드를 혼자 다루다 보니 하나하나 자세하게 신경쓰지못해서 아쉬움이 있었습니다.

## 해결방안
- 코드를 조금더 가독성있게 수정하고있습니다.
- 다음 프로젝트에서 보다 심화된 개념으로 접근 필요하다고 생각합니다.
- 시간 분배를 잘해서 다음 프로젝트에서는 조금 더 신경써서 가독성있게 코딩을 해야겠다고 생각합니다.

## 시연 영상
![동영상](./image/동영상.gif)

## ERD
![ERD](/image/ERD1.png)

## 구동 화면
  ### 메인 화면1

![main1](/image/main1.png)
  ### 메인 화면2

![main2](/image/main2.png)
  ### 로그인 화면

![login](/image/login.png)
  ### 마이 페이지 화면

![mypage](/image/mypage.png)
  ### 정보 수정 화면

![edit](/image/edit.png)
  ### Shop 화면

![shop](/image/shop.png)
  ### Shop 화면 디테일1

![shopdetail](/image/shopdetail.png)
  ### Shop 화면 디테일2

![shopdetail2](/image/shopdetail2.png)
  ### Style 화면

![style](/image/style.png)
  ### 관리자 화면

![admin](/image/admin.png)
