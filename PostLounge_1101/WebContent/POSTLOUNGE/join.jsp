<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회원가입</title>
	<link rel="stylesheet" type="text/css" href="../css/join.css">
	
</head>
<body>

<div class="login-page">
  <div class="form">
    <form class="register-form" action = "controller?type=join" method = "post">
      <input type = "text" name="name" id="name" maxlength= "15" placeholder = "이름 입력">
      <input type = "text" name="id" id="id" maxlength= "50" placeholder = "아이디 입력">
      <input type = "password" name="pwd" id="passwd" maxlength= "16" placeholder = "비밀번호 6자리 - 12자리 이하 입력">
      <input type = "text" name="nickname" id="nickname" maxlength= "13" placeholder = "별명 입력">
      <button type="submit">create</button>
      <p>Already registered? <a href="login.jsp">Sign In</a></p>
    </form>
  </div>
</div>

</body>
</html>