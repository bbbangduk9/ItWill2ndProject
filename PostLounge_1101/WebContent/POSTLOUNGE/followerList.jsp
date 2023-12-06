<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script>
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


	
<!-- ========================================== -->

<p class="fs-3 fw-semibold">${sessionScope.loginUser.nickname }님의 팔로워 목록 ${followerListCnt}</p>	
<hr>


<div class="container mt-4">
    <div class="follower_list_wrap">
        <div class="follower_list">
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th class="rnum">no</th>
                            <th class="id">ID</th>
                            <th class="name">이름</th>
                            <th class="nickname">닉네임</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="vo" items="${list }">
                            <tr>
                                <td class="rnum">${vo.rnum}</td>
                                <td class="id">${vo.id}</td>
                                <td class="name">${vo.name}</td>
                                <td class="nickname">${vo.nickname}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="paging text-center">
                <ul class="pagination">
                    <li class="page-item ${pvo.beginPage == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="controller?type=followList&cPage=${pvo.beginPage - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <c:forEach var="pageNo" begin="${pvo.beginPage}" end="${pvo.endPage}">
                        <li class="page-item ${pageNo == pvo.nowPage ? 'active' : ''}">
                            <a class="page-link" href="controller?type=followList&cPage=${pageNo}">${pageNo}</a>
                        </li>
                    </c:forEach>

                    <li class="page-item ${pvo.endPage >= pvo.totalPage ? 'disabled' : ''}">
                        <a class="page-link" href="controller?type=followList&cPage=${pvo.endPage + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
	
<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>	
</body>
</html>