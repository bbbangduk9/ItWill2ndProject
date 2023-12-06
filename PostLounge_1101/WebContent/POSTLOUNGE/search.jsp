<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
/*    body {
      background-color: #E4FFF2;
   } */
</style>
<meta charset="UTF-8">
<title>검색창</title>
</head>
<body>
<!-- 메인페이지랑 겹쳐서 일단 주석처리 -->
<!-- 로고버튼 클릭기능 이미지로 하려 했으나 서버내에 저장된 데이터만 사용가능하기에
 버튼으로 기능만 구현하여 대체 -->
<%-- <jsp:include page="mainPageLogo.jsp" /> --%>
	<form action="controller?type=search" type="search" method="post">
		<select name="idx">
			<option value="0">통합검색</option>
			<option value="1">제목</option>
			<option value="2">내용</option>
			<option value="3">작성자</option>
			<option value="4">댓글</option>
		</select>
		<input type="text" name="keyword">
		<input type="submit" value="검색">
	</form>
</body>
</html>