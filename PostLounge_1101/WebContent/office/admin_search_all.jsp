<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>(관리자)Post Lounge</title>
<link href="../css/admin_all.css" rel="stylesheet" type="text/css">
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
			<li id="postlounge"><a href="admin_main.jsp">Post Lounge(관리자 모드)</a></li>
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
		<form action="controller?type=admin_all" type="search" method="post">
			<select name="idx">
				<option value="1">제목</option>
				<option value="2">내용</option>
				<option value="3">회원</option>
				<option value="4">댓글</option>
			</select>
			<input id="serWindow" type="text" name="keyword">
			<input id="btnSer" type="submit" value="검색">
		</form>
	</div>	
</body>
</html>