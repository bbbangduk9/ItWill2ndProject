<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 찾기 결과</title>
</head>
<body>
    <h1>비밀번호 찾기 결과</h1>
    <c:if test="${not empty foundPwd}">
        <p>비밀번호는 다음과 같습니다: ${foundPwd}</p>
        <p><a href="login.jsp">로그인하러 가기</a></p> <!-- 로그인 링크 추가 -->
    </c:if>
    <c:if test="${not empty errorMessage}">
        <p>${errorMessage}</p>
    </c:if>
</body>
</html>
