<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴 페이지 </title>
</head>
<body>
	<h1>회원탈퇴를 하기 위한 절차를 수행합니다.</h1>
	<form  action="memberDelete" method="post">
		<div>
			이름 : <input type="text" name="name">
			아이디 : <input type="text" name="id">
			비밀번호 : <input type="password" name="pwd">
		</div> 
		<div>
			<input type="submit" value="탈퇴하기">
		</div>
	</form>
	<button id="cancel" onclick="location.href='index.jsp'">취소하기</button> 
</body>
</html>