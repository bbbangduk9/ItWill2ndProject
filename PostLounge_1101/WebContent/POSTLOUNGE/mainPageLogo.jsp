<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지 로고</title>
</head>
<body>
<script>
	function index_go() {
		location.href="index.jsp"
	}
</script>
	<h4><button onclick="index_go()">메인페이지</button></h4>
	<!-- 로고 이미지는 서버에서 데이터를 가져와서 써야함. 밑에는 컴퓨터에서 불러오기 
	중 실패한 구문 -->
	<!-- <p><img alt="" src="./WebContent/img/img03.jpg" width="110" height="60" /></p>  -->
</body>
</html>