<%@page import="com.postlounge.model.vo.PostVO"%>
<%@page import="com.postlounge.model.dao.PostDAO"%>
<%@page import="com.postlounge.model.vo.PostPagingVO"%>
<%@page import="com.postlounge.model.dao.SearchDAO"%>
<%@page import="com.postlounge.model.vo.PostListVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	String keyword = request.getParameter("keyword");			   
	String idx = request.getParameter("idx");					   
	List<PostListVO> list = SearchDAO.getSearchAdmin(idx, keyword);
	request.setAttribute("list", list);							   
	request.setAttribute("keyword", keyword);					   
	request.setAttribute("idx", idx);							 %>

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
			<button onclick="go_report()" id="btnReport">신고</button>
		</div>
	</div>

<c:if test="${idx == '1'}">
	<div class="center">
	<h1>'${keyword}' 검색 결과</h1>
		<table id="boxTable" border="1">
			<thead>
					<tr>
						<th>글번호</th>
						<th>카테고리</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
			</thead>
			<tbody>
			<c:choose>
					<c:when test="${empty list }">
						<tr>
							<td colspan="6">
								<h2>검색 결과가 없습니다.</h2>
							</td>
						</tr>
					</c:when>
				<c:otherwise>
					<c:forEach var="vo" items="${list }">
						<tr>
							<td>${vo.postIdx }</td>
							<td>${vo.postType }</td>
							<td calss="align-left">
								<a id="aa" href="admin_view.jsp?postIdx=${vo.postIdx }">${vo.title }</a>
							</td>
							<td>${vo.nickname }</td>
							<td>${vo.postDate }</td>
							<td>${vo.hit }</td>
						</tr>
					</c:forEach>
				</c:otherwise>	
			</c:choose>	
			</tbody>	
		</table>
	</div>
</c:if>

<c:if test="${idx == '2'}">
	<div class="center">
	<h1>'${keyword}' 검색 결과</h1>
		<table id="boxTable" border="1">
			<thead>
					<tr>
						<th>글번호</th>
						<th>카테고리</th>
						<th>내용</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
			</thead>
			<tbody>
			<c:choose>
					<c:when test="${empty list }">
						<tr>
							<td colspan="6">
								<h2>검색 결과가 없습니다.</h2>
							</td>
						</tr>
					</c:when>
				<c:otherwise>
					<c:forEach var="vo" items="${list }">
						<tr>
							<td>${vo.postIdx }</td>
							<td>${vo.postType }</td>
							<td calss="align-left">
								<a id="aa" href="admin_view.jsp?postIdx=${vo.postIdx }">${vo.postContent }</a>
							</td>
							<td>${vo.postDate }</td>
							<td>${vo.hit }</td>
						</tr>
					</c:forEach>
				</c:otherwise>	
			</c:choose>	
			</tbody>	
		</table>
	</div>
</c:if>

<c:if test="${idx == '3'}">
	<div class="center">
	<h1>'${keyword}' 검색 결과</h1>
		<table id="boxTable" border="1">
			<thead>
					<tr>
						<th>회원번호</th>
						<th>아이디</th>
						<th>닉네임</th>
						<th>상태</th>
						<th>가입일</th>
					</tr>
			</thead>
			<tbody>
				<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="5">
							<h2>검색 결과가 없습니다.</h2>
						</td>
					</tr>
				</c:when>
					<c:otherwise>
						<c:forEach var="vo" items="${list }">
							<tr>
								<td>${vo.memberIdx }</td>
										<td>${vo.id }</td>
								<td>${vo.nickname }</td>
								<td>${vo.isDel }</td>
								<td>${vo.joinDate }</td>
							</tr>
						</c:forEach>
					</c:otherwise>	
				</c:choose>	
			</tbody>	
		</table>
	</div>
</c:if>

<c:if test="${idx == '4'}">
	<div class="center">
	<h1>'${keyword}' 검색 결과</h1>
	<table id="boxTable" border="1">
		<thead>
			<tr>
				<th>댓글 번호</th>
				<th>댓글 내용</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="2">
							<h2>검색 결과가 없습니다.</h2>
						</td>
					</tr>
				</c:when>
			<c:otherwise>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo.commentIdx}</td>
						<td><a id="aa" href="admin_view.jsp?postIdx=${vo.postIdx }">${vo.commContent }</a></td>
						<td>${vo.commDate }</td>
					</tr>
				</c:forEach>
			</c:otherwise>	
			</c:choose>	
			</tbody>	
		</table>
	</div>
</c:if>	
</body>
</html>