<%@page import="com.postlounge.model.paging.Paging"%>
<%@page import="com.postlounge.model.dao.BbsDAO"%>
<%@page import="com.postlounge.model.vo.BbsVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Post Lounge</title>
<link href="../css/comment.css" rel="stylesheet" type="text/css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
	
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="<%= request.getContextPath() %>/JSfile/postCallComment.js"></script>
<script>
	var errorMessage = "${errorMessage}"; //팔로우 추가 실패(이미 친구인 경우, 탈퇴한 회원인 경우)
	if (errorMessage.trim() !== "") {
	    alert(errorMessage);
	}
	
	var successMessage = "${successMessage}" //팔로우 성공 메세지가 있으면 띄움
	if (successMessage.trim() !== "") {
		alert(successMessage);
	}
	
	var myPostMessage = "${myPostMessage}"; //내 게시물에서 친구 추가한 경우
	if (myPostMessage.trim() !== "") {
		alert(myPostMessage);
	}
	
	function follow_go(followee, postIdx) {
		// 팔로우 확인 대화 상자 표시
        var confirm2 = confirm("팔로우를 추가하시겠습니까?");
		// 사용자가 확인을 선택한 경우 -> 프론트컨트롤러로 이동
		if (confirm2) {
			location.href = "controller?type=follow&postIdx=" + postIdx 
					+ "&param=" + followee; 
		}
	}
</script>
</head>
<body>

<header class="py-3 mb-4 border-bottom">
    <div class="container d-flex flex-wrap justify-content-between align-items-center">
        <a href="index.jsp" class="d-flex align-items-center mb-3 mb-lg-0 me-3 link-body-emphasis text-decoration-none">
            <svg class="bi me-2" width="40" height="32">
                <use xlink:href="#bootstrap"></use>
            </svg>
            <span class="fs-4">Post Lounge</span>
        </a>
        <!-- <form class="col-12 col-lg-auto mb-3 mb-lg-0" role="search">
            <div class="input-group">
                <input type="search" class="form-control" id="searchContent" placeholder="Search..." aria-label="Search">
                <button class="btn btn-outline-secondary" type="button">Search</button>
            </div>
        </form> -->
        <%-- <div class="d-flex align-items-center">
            <!-- 글쓰기와 로그아웃 링크 추가 -->
            <a href="write_go()" class="nav-link link-body-emphasis px-2 me-3">포스트 쓰기</a>
            <button id="follow" onclick="follow_go(${vo.memberIdx}, ${vo.postIdx})">친구추가</button>
        </div> --%>
        
        <div class="d-flex align-items-center">
		    <!-- 글쓰기와 로그아웃 링크 추가 -->
		    <a href="write_go()" class="nav-link link-body-emphasis px-2 me-3">포스트 쓰기</a>
		    <a href="#" onclick="follow_go(${vo.memberIdx}, ${vo.postIdx})" class="btn btn-outline-success">+팔로우</a>
		</div>
    </div>
</header>
<div class="container my-3">
    <h6 class="py-2"> ${vo.postType}</h6>
    <h1 class="py-2"> ${vo.title}</h1>
    <h5 class="py-2"> ${vo.nickname}</h5>
    <h6 class="border-bottom py-2"> ${vo.postDate}</h6>
    <div class="card my-3">
        <div class="card-body">
            <div class="card-text" style="white-space: pre-line;">${vo.postContent}</div>
            <div class="d-flex justify-content-end">
            </div>
        </div>
    </div>
</div>

<!-- ================================================ -->

	<hr class="Post_hr_Comment">
	<!-- 댓글 작성란 -->
	<div class="write_CommentArea">
		<form id="writeForm" action="JsonInsertCommentController" method="post" >
			<c:choose>
				<c:when test="${empty sessionScope.loginUser }">
				</c:when>
				<c:otherwise>
					<caption class="caption_nickname">유저 '${sessionScope.loginUser.nickname}'</caption>
				</c:otherwise>
			</c:choose>
			<fieldset>
				<legend class="legend_comment">댓글 작성</legend>
				<c:choose>
					<c:when test="${empty sessionScope.loginUser}">
						<textarea class="commentInsertArea" title="댓글" id="textarea" rows="6" cols="90" disable></textarea>
						<label>댓글을 작성하려면 <a href="login.jsp">로그인</a> 해주세요
						</label>
					</c:when>
					<c:otherwise>
						<textarea id="commContent" class="auto-resize-textarea" name="content" rows="6" cols="90" title="댓글내용"
							oninput="checkLength(this, 300)"
							placeholder="주제와 무관한 댓글, 악플은 삭제될 수 있습니다."></textarea>
						<div id="charCount" class="limiteCharacter">현재 입력한 글자수 0/전체 입력 가능한 글자수 300</div>
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
	

	<div class="bottom_section">
	
		<!-- 댓글 리스트 Ajax(페이징처리) -->
	<div id="commentHere" class="comment_css"></div>
	</div>
<!-- 	Ajax, css, detail.jsp 만 수정함. -->
	
<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>		
</body>
</html>