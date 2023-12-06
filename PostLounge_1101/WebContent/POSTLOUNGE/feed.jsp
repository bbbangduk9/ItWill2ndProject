<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Post Lounge</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="<%= request.getContextPath() %>/JSfile/index_script01.js"></script>
<script src="<%= request.getContextPath() %>/JSfile/index_script02.js"></script>
<!-- <style>
	body {
		background-color: blue;
		text: red;
	}
</style> -->
<script>
	//followerList 요청처리. 팔로워가 없는 경우 -> 피드페이지로 이동.
	var followermessage = "${followermessage}"
	if (followermessage.trim() !== "") {
		alert(followermessage);
	}
	
	//followingList에서 팔로우가 없는 경우 -> 피드페이지로 이동.
	var followingmessage = "${followingmessage}"
	if (followingmessage.trim() !== "") {
		alert(followingmessage);
	}
	
	function logout_go(){ 
		location.href="logout.jsp";
	}
	function write_go() {
		location.href = "insertPost.jsp";
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
        <form class="col-12 col-lg-auto mb-3 mb-lg-0" role="search">
            <div class="input-group">
                <input type="search" class="form-control" id="searchContent" placeholder="Search..." aria-label="Search">
                <button class="btn btn-outline-secondary" type="button">Search</button>
            </div>
        </form>
        <div class="d-flex align-items-center">
            <!-- 글쓰기와 로그아웃 링크 추가 -->
            <button onclick="write_go()" class="btn btn-outline-secondary btn-sm mx-2">포스트 쓰기</button>
			<button onclick="logout_go()" class="btn btn-outline-secondary btn-sm">로그아웃</button>
        </div>
    </div>
</header>

<!-- ================================================================ -->
	<div class="container mt-5">
		<div class="row">
			<div class="col-12">
				<div class="follow_list_wrap">
					<div class="followerList">
						팔로워 <a class="btn btn-link" href="controller?type=followerList">${followerListCnt}</a>
					</div>
					<div class="followingList">
						팔로잉 <a class="btn btn-link" href="controller?type=followingList">${followListCnt}</a>
					</div>
				</div>
				<hr>

				<c:choose>
					<c:when test="${not empty pList }">
						<div class="post_list_wrap">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th scope="col">no</th>
										<th scope="col">글번호</th>
										<th scope="col">카테고리</th>
										<th scope="col">제목</th>
										<th scope="col">내용</th>
										<th scope="col">작성자</th>
										<th scope="col">작성일자</th>
										<th scope="col">조회수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="vo" items="${pList}">
										<tr>
											<td>${vo.rnum}</td>
											<td>${vo.postIdx}</td>
											<td>${vo.postType}</td>
											<td><a
												href="controller?type=detail&postIdx=${vo.postIdx}">${vo.title}</a></td>
											<td><a
												href="controller?type=detail&postIdx=${vo.postIdx}">${vo.postContent}</a></td>
											<td>${vo.nickname}</td>
											<td><fmt:formatDate value="${vo.postDate}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${vo.hit}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

							<ol class="pagination justify-content-center">
								<li class="page-item ${pvo.beginPage == 1 ? 'disabled' : ''}">
									<a class="page-link"
									href="controller?type=followList&cPage=${pvo.beginPage - 1}">이전</a>
								</li>

								<c:forEach var="pageNo" begin="${pvo.beginPage}"
									end="${pvo.endPage}">
									<li class="page-item ${pageNo == pvo.nowPage ? 'active' : ''}">
										<a class="page-link"
										href="controller?type=followList&cPage=${pageNo}">${pageNo}</a>
									</li>
								</c:forEach>

								<li
									class="page-item ${pvo.endPage >= pvo.totalPage ? 'disabled' : ''}">
									<a class="page-link"
									href="controller?type=followList&cPage=${pvo.endPage + 1}">다음</a>
								</li>
							</ol>
						</div>
					</c:when>
					<c:otherwise>
						<p class="text-center">표시할 게시물이 없습니다.</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>

</body>
</html>