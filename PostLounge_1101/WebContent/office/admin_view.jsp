<%@page import="com.postlounge.model.dao.CommentDAO"%>
<%@page import="com.postlounge.model.vo.CommentVO"%>
<%@page import="com.postlounge.model.dao.PostDAO"%>
<%@page import="com.postlounge.model.vo.PostVO"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	//파일미터 값 추출(확인)
	int postIdx = Integer.parseInt(request.getParameter("postIdx"));
	String cPage = request.getParameter("cPage");
	
	//1. 게시글 조회수 1 증가(실습)
	
	//2. 게시글(bbsIdx) 데이터 조회 후 화면 표시
	PostVO post = PostDAO.onePost(postIdx);
	System.out.println("post : " + post);
	
	//3. 게시글(bbsIdx)에 딸린 댓글이 있으면 조회 후 화면 표시
	List<CommentVO> commList = CommentDAO.getPostComm(postIdx);
	System.out.println("commList : " + commList);
	
	//JSTL, EL 사용을 위한 scope 상에 데이터 등록하기
	session.setAttribute("post", post); //게시글 데이터
	session.setAttribute("cPage", cPage);
	
	pageContext.setAttribute("c_list", commList); //댓글 목록
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글상세</title>
<link href="../css/admin_view.css" rel="stylesheet" type="text/css">
<script>
	function modify_go() {
		location.href = "modify.jsp";
	}
	function delete_go() {
		location.href = "delete.jsp";
	}
	function list_go() {
		location.href = "list.jsp?cPage=${cPage}";
	}
	function go_main() {
		location.href = "../POSTLOUNGE/index.jsp";
	}
	function go_prv() {
		history.back();
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
		<div class="secondMenu">
			<h2>상세보기</h2>
 		</div>
	</div>
<div id="center">
	<%--게시글 내용 표시 --%>
	<div class="box">
	<table id="boxTable" border="1">
			<thead>
				<tr>
					<th colspan="3">${post.title }</th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
					<td colspan="3"><p>${post.postContent }</p></td>
				</tr>
				
				<tr>
					<td>작성자: ${post.nickname }</td>
					<td>첨부파일: ${post.postOriName }</td>
					<td>${post.postDate } 작성됨</td>
				</tr>
			</tbody>
		<tfoot>
			<tr>
				<td colspan="3">
					<input type="button" value="목록보기" onclick="go_prv()">
				</td>
			</tr>
		</tfoot>
	</table>
	
	<%--게시글에 딸린 댓글 표시 영역 --%>
	<c:forEach var="commVO" items="${c_list }">
	<div class="comment">
		<form action="ans_del.jsp" method="post">
			<p>작성자: ${commVO.nickname } &nbsp;/&nbsp;${commVO.commDate }</p>
			<p>${commVO.commContent }</p>
			<input type="hidden" name="commentIdx" value="${commVO.commentIdx }">
			
		</form>
	</div>
	</c:forEach>
	</div>
</div>
</body>
</html>