<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script>
    var errorMessage = "${errorMessage}";
    if (errorMessage.trim() !== "") {
        alert(errorMessage);
    }
</script>
<link href="../css/member.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="login-page">
  <div class="form">
    <form id="frm" class="login-form" action="controller?type=login" method="post">
      <input type="text" name = "id" id = "id" placeholder="Id"/>
      <input type="password" name = "pwd" id = "pwd" placeholder="Password"/>
      <div class="formHover">
	      <input class="hover" type="submit" value="로그인" />
	      <input class="hover" type="button" onclick="javascript:location.href='join.jsp'" value="회원가입" />
	      <p class="find"><a href="#">아이디 찾기</a></p>              
	      <p class="find"><a href="#">비밀번호 찾기</a></p>
      </div>
    </form>
  </div>
</div>
	

</body>
</html>