<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 공간</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="<%= request.getContextPath() %>/JSfile/postCallComment.js"></script>
</head>
<script>

</script>
<body>
현재 로그인 유저 IDX : ${sessionScope.loginUser.memberIdx }
	<h1>댓글 작성 + 댓글 리스트(무한스크롤)</h1>
	<!-- 댓글 작성란 -->
	<!-- 댓글 작성란 -->
	<div>
		<form id="writeForm" action="JsonInsertCommentController" method="post" >
			<c:choose>
				<c:when test="${empty sessionScope.loginUser }">
				</c:when>
				<c:otherwise>
					<caption>유저 '${sessionScope.loginUser.nickname}' 댓글 작성합니다.</caption>
				</c:otherwise>
			</c:choose>
			<fieldset>
				<legend>댓글 작성</legend>
				<c:choose>
					<c:when test="${empty sessionScope.loginUser}">
						<textarea title="댓글" id="textarea" rows="5" cols="50" disable></textarea>
						<label>댓글을 작성하려면 <a href="login.jsp">로그인</a> 해주세요
						</label>
					</c:when>
					<c:otherwise>
						<textarea id="commContent" name="content" rows="3" cols="30" title="댓글내용"
							oninput="checkLength(this, 300)"
							placeholder="주제와 무관한 댓글, 악플은 삭제될 수 있습니다."></textarea>
						<div id="charCount">0/300 characters remaining</div>
					</c:otherwise>
				</c:choose>
			</fieldset>
			<c:choose>
				<c:when test="${empty sessionScope.loginUser}">
					<input type="button" onclick="confirm_login()" value="등록">
				</c:when>
				<c:otherwise>
					<div>
						<button id="submitComment" type="submit">등록</button>
						<input type="hidden" id="postIdx" name="postIdx" value="${vo.postIdx}"> <!-- JS에서 쓸거임 -->
						<hr>
					</div> 
				</c:otherwise>
			</c:choose>
		</form>
		
	</div>
	<hr><hr>
	<p>세션 memberIdx : ${sessionScope.loginUser.memberIdx } </p>
	
		<!-- 댓글 리스트 Ajax(페이징처리) -->
	<div id="commentHere">
		<p>nickname</p>
		<p>commContent</p>
		<p>commDate</p>
		<p>신고버튼 (commentIdx전달로 신고)</p>
	</div>
</body>
</html>