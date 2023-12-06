<%@page import="java.util.List"%>
<%@page import="com.postlounge.model.vo.PostPagingVO"%>
<%@page import="com.postlounge.model.dao.PostDAO"%>
<%@page import="com.postlounge.model.vo.PostVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>

<%//@@@ A.오늘 업데이트 된 게시글 수를 표시하기 위함 @@@ //
	//0. Post 객체 생성
	PostVO post = new PostVO();
	
	//1. 전체 게시물 수량 구하기
 	post.setNewPostAll(PostDAO.getCntPostAll()); 
	post.setNewFoodAll(PostDAO.getCntFoodAll());
	post.setNewHealthAll(PostDAO.getCntHealthAll());
	post.setNewMusicAll(PostDAO.getCntMusicAll());
	post.setNewSportAll(PostDAO.getCntSportAll());
	post.setNewMovieAll(PostDAO.getCntMovieAll());
	
 	System.out.println("> 오늘 올라온 포스트 수 : " + post.getNewPostAll()); 
 	
	//@@@ A. 끝 @@@// %>
<%//@@@ JSTL, EL 사용을 위해 scope에 데이터 등록(page 영역)	   
	pageContext.setAttribute("post", post);				 %>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>(관리자)Post Lounge</title>
<link href="../css/admin_main.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script> <!-- .js 파일에 있는 jquery 함수를 쓰려면 html 안에 선언해야한다  -->
<script src="<%= request.getContextPath() %>/js/admin_main.js"></script> <!-- .js 파일을 불러와서 쓰려면 요상하다. jsp 파일이라서 그런가보다 -->
<script>
	function go_search() {
		location.href = "controller?type=admin_search_all";
	}
	function go_best() {
		location.href = "controller?type=admin_best";
	}
	function go_manage_post() {
		location.href = "controller?type=admin_manage_post";
	}
	function go_manage_comment() {
		location.href = "controller?type=admin_manage_comment";
	}
	function go_manage_member() {
		location.href = "controller?type=admin_manage_member";
	}
	function go_manage_opr() {
		location.href = "controller?type=admin_manage_opr";
	}
	function go_report() {
		location.href = "controller?type=admin_manage_report";
	}
	function go_main() {
		location.href = "../POSTLOUNGE/index.jsp";
	}
</script>
</head>
<body>
	<div class="firstBar">
		<ul class="firstMenu">
			<li id="postlounge"><a href="#">Post Lounge(관리자 모드)</a></li>
			<li><button onclick="go_main()" id="btnLogout">로그아웃</button></li>
		</ul>
	</div>
	
	<div class="secondBar">
		<div class="adminMenu">
			<button onclick="go_search()" id="btnSearch">검색</button>
			<button onclick="go_best()" id="btnBest">Best</button>
			<button onclick="go_manage_post()" id="btnPost">게시글</button>
	 		<button onclick="go_manage_comment()" id="btnComment">댓글</button>
	 		<button onclick="go_manage_member()" id="btnMember">회원</button>
	 		<button onclick="go_manage_opr()" id="btnOpr">관리자</button>
			<button onclick="go_report()" id="btnReport">신고</button>
		</div>
	</div>
	
	<div class="thirdBar">
		<div class="categoryMenu">
			<button id="btnAll">전체 - new&nbsp;<p>${post.newPostAll}</p></button>
			<button id="btnFood">푸드 - new&nbsp;<p>${post.newFoodAll}</p></button>
			<button id="btnHealth">건강 - new&nbsp;<p>${post.newHealthAll}</p></button>
			<button id="btnMusic">음악 - new&nbsp;<p>${post.newMusicAll}</p></button>
			<button id="btnSport">스포츠 - new&nbsp;<p>${post.newSportAll}</p></button>
			<button id="btnMovie">영화 - new&nbsp;<p>${post.newMovieAll}</p></button>
		</div>
	</div>
	
	<div class="board">
		<table class="boardTable" border="1">
			<thead>
				<tr>
					<!-- <th>글 번호</th> -->
					<th colspan="2">제목</th>
					<th>글쓴이</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			
			<tbody id="postTable">
				<%--
				admin_main.js 파일에서 데이터 출력됨					
				 --%>
			
			</tbody>
			
		</table>
	</div>
</body>
</html>












