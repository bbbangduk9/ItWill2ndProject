<!-- profileImage.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>프로필 페이지</title>
</head>
<body>
    <h1>프로필 정보</h1>
  
    <c:if test="${not empty loginUser}">
        <p>이름: ${loginUser.name}</p>
        <p>닉네임: ${loginUser.nickname}</p>
        <!-- 기타 프로필 정보 표시 -->
         <img src="../imageupload/${loginUser.memberFileName}" alt="프로필 이미지">
    </c:if>

    <a href="profileEdit.jsp">프로필 수정</a>
    <p><a href="index.jsp">메인으로 가기</a></p>
</body>
</html>
