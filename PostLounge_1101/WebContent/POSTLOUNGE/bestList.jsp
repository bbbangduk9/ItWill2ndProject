<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>베스트 [bestList.jsp]</title>
<script>

	function search_go(){
		location.href="controller?type=search";
	}
	function join_go(){
		location.href="controller?type=join";
	}
	function best_go(){
		location.href="controller?type=best";
	}
	function feed_go(){
		location.href="controller?type=feed";
	}
	function my_go(){
		location.href="my.jsp";
	}

</script>
<style>
/* 	body {
		background-color: #E4FFF2
	} */
	thead {
		position: relative; /* 상대 위치 설정 */
	}

	.top-right-buttons {
    	position: absolute; /* 절대 위치 설정 */
    	top: 20px;
    	right:30px;
	}
	
	div button {
		background-color: #81c147;
		padding: 10px 20px;
		border: none;
		cursor: pointer;
		border-radius: 10px; /* 모서리를 10px 둥글게 만듭니다. */
	   	overflow: hidden; /* 테이블 내용이 둥근 테두리를 벗어나지 않도록 합니다. */
	   	box-shadow: 0px 1px 0px rgba(0, 0, 0, 0.5);
	   	/* 효과를 부드럽게 만들기 위한 트랜지션 설정 */
	   	transition: background-color 0.3s;
	}
	
	div button:active {
		background-color: #6a9c3c;
	}
</style>
</head>
<body>
<!-- 로고버튼 클릭기능 이미지로 하려 했으나 서버내에 저장된 데이터만 사용가능하기에
 버튼으로 기능만 구현하여 대체 -->
<jsp:include page="mainPageLogo.jsp" />
	<thead>
			<h1>포스트 라운지 [bestList.jsp]</h1>
		<div class="top-right-buttons">
			<button onclick="search_go()">검색</button>
			<button onclick="javascript:location.href='login.jsp'">로그인</button>
		</div>
		<hr>
		<div> <%--best_go()는 메인화면 겸 전체게시물 기준 조회수 top10 페이지 --%>
			<button onclick="best_go()">베스트(main)</button>
			<button onclick="feed_go()">피드(친구게시물)</button>
			<button onclick="my_go()">my(내정보)</button>
		</div>
	</thead>
	<hr>
	<%--아래 JSON 데이터 활용 --%>
	<tbody>
		
		
		<hr>
		
		
		<div>
			<button type="button" id="top10">TOP10</button>
			<button type="button" id="current">최근</button>
		</div>
		<%-- 내 용 --%>
		<div>
		<c:forEach var="vo" items="${list }">
			<p>${vo.title }</p>
			<p>${vo.postContent }</p>
			<p>${vo.hit }</p>
			<hr>
		</c:forEach>
		</div>
	</tbody>
</body>
</html>