# ItWill2stProject
2차프로젝트 - 커뮤니티 웹사이트 / Java, Oracle SQL 이용

## 프로젝트 소개

Servlet, JSP를 이용한 커뮤니티 웹사이트 개발 
<br>

## 개발 기간
*23.10.24 - 23.10.31
<br>

### 개발환경
- `Java 8`
- `JDK 1.8`
- **IDE** : Eclipse 2020-06
- **Database** : Oracle DB(11g)
- **ORM** : Mybatis 3.5.13
- **Server** : Apache Tomcat 9.0.80
<br>

## 담당한 주요 기능 
-회원관련 CRUD

-회원 블로그 프로필 관리


## 화면 구현
-**메인페이지**
<br>
![Untitled](https://github.com/bbbangduk9/ItWill2ndProject/assets/142999206/18794726-c8b2-46b4-a63e-b584062a7257)
<br>
- 카테고리별 조회,작성 글 조회가 가능.
- 조회수 별 작성글 조회 가능.
- 검색,로그인,회원가입 가능.
<br>

-**회원가입**
<br>
![Untitled (1)](https://github.com/bbbangduk9/ItWill2ndProject/assets/142999206/b15fdf40-6068-423d-9fc2-28b82ce57334)
<br>
- 회원가입시 입력한 데이터를 이용하여 로그인
<br>

![Untitled (2)](https://github.com/bbbangduk9/ItWill2ndProject/assets/142999206/c286a14c-25e5-43c2-a57f-1222ce4173e9)

![Untitled (3)](https://github.com/bbbangduk9/ItWill2ndProject/assets/142999206/28f5211e-abca-4b22-8bd6-135eb4ebff62)

![Untitled (4)](https://github.com/bbbangduk9/ItWill2ndProject/assets/142999206/0e261256-9449-4aad-9569-2b8c5d8bd7f5)
<br>
- 아이디 찾기 / 비밀번호 찾기 / 회원 탈퇴

-**프로필 관리**
<br>
![Untitled (5)](https://github.com/bbbangduk9/ItWill2ndProject/assets/142999206/713d3004-0a6b-459e-9e39-e89a0da25c52)
<br>
-이미지 업로드 → DB저장 → 화면출력
-DB에 FILENAME / ORINAME을 나눠서 원래경로/업로드경로 나눠서 구현
-Server.xml에 업로드 할 경로 작성



