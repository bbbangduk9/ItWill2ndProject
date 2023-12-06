<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면(PostLounge_pro)</title>
<script>
	//글쓰기 업로드 성공시 알림 (---> 안뜸)
	var successMessage = "${successMessage}"
	if (successMessage.trim() !== "") {
		alert(successMessage);
	}
	
	//feed에서 팔로우가 없는 경우 -> 메인페이지로 이동.
	var message = "${message}"
	if (message.trim() !== "") {
		alert(message);
	}
</script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
<script src="<%= request.getContextPath() %>/JSfile/index_script01.js"></script>
<script src="<%= request.getContextPath() %>/JSfile/index_script02.js"></script>
<style>
/* 	body {
		background-color: #E4FFF2
	} */
    table {
    	width: 100%; /* 표 너비를 100%로 설정하여 가로폭을 최대화합니다. */
    	border-collapse: collapse; /* 표의 셀 경계를 합칩니다. */
    }
    
    th, td {
    	padding: 10px; /* 셀 내용과 경계 사이의 여백을 추가합니다. */
    }
    th {
    	text-align: center; /* 표 머리글 중앙 정렬 */
    }
    td {
    	text-align: center; /* 표 데이터 중앙 정렬 */
    }
  	td:last-child {
    	text-align: right; /* 표의 마지막 열 우측 정렬 */
  	}
  
  	#searchContent {
		position: absolute;
		top: 30px;
		right: 30px;
  	}
  
  	#idxwrte {
  		margin-left: 20px;
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
  
  	#idxlogout {
  		float: right; /* 로그아웃 우츠 이동 */
    	margin-right: 20px; /* 로그아웃 버튼 우측에서 20px 띄우기 */
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
  	
  	.meunebar {
  		margin-top: 30px;
  		text-align: center;
  		background-color: #81c147;
  		padding: 10px 0;
  		box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.5);
  	}
  	
  	.meunebar button {
  		margin: 0 20px; /* 각 버튼 간격 20px 씩 띄어놓기 */
  		background-color: #81c147;
		padding: 15px 30px;
		border: none;
		cursor: pointer;
		border-radius: 10px; /* 모서리를 10px 둥글게 만듭니다. */
	   	overflow: hidden; /* 테이블 내용이 둥근 테두리를 벗어나지 않도록 합니다. */
	   	/* 효과를 부드럽게 만들기 위한 트랜지션 설정 */
	   	transition: background-color 0.3s;
	   	position: relative;
  	}
  	
  	.idxcategory {
  		text-align: center;
  	}
  
  	.idxcategory button {
  		margin: 0 20px; /* 각 버튼 간격 20px 씩 띄어놓기 */
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
  	
  	.top10best button {
  		margin: 0 20px; /* 각 버튼 간격 20px 씩 띄어놓기 */
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
</style>
</head>
<body>
<%-- <!-- 로고버튼 클릭기능 이미지로 하려 했으나 서버내에 저장된 데이터만 사용가능하기에
 버튼으로 기능만 구현하여 대체 -->
<jsp:include page="mainPageLogo.jsp" /> --%>
	<thead>
		<div>
			<h1>포스트 라운지 [index.jsp]</h1>

			<!-- 검색기능 ajax연결 -->
			<div id="searchContent"></div>

			<c:choose>
				<c:when test="${empty sessionScope.loginUser}">
					<!--  로그인 안한 상태 -->
					<button onclick="login_go()">로그인</button>
					<button onclick="join_go()">회원가입(서블릿)</button>
				</c:when>
				<c:otherwise>
					<!--  로그인 했음 -->
					<h2>환영합니다. ${sessionScope.loginUser.nickname }님!</h2>
					<button id="idxwrte" onclick="write_go()">글쓰기</button>
					<button id="idxlogout" onclick="logout_go()">로그아웃</button>
				</c:otherwise>
			</c:choose>
		</div>
		<br>
		<div class="meunebar">
			<%-- 네이버 포스트 기준 메인페이지가 [베스트]임 --%>
			<button onclick="index_go()">베스트</button>
			<button onclick="feed_go()">피드</button>
			<button onclick="my_go()">MY</button>
		</div>
	</thead>
	<hr>
	<%--아래 JSON 데이터 활용 --%>
	<tbody>
		<div class="idxcategory">
			<a><button id="ctgr_all">전체</button></a> 
			<a><button id="ctgr_food">음식</button></a> 
			<a><button id="ctgr_health">건강</button></a>
			<a><button id="ctgr_sport">스포츠</button></a> 
			<a><button id="ctgr_music">음악</button></a> 
			<a><button id="ctgr_movie">영화</button></a>
		</div>
		<hr>
		<div class="top10best">
			<button type="button" id="top10">TOP10</button>
			<%--best_go()는 전체게시물 기준 조회수 top10 --%>
			<button onclick="best_go()">베스트</button>
			<hr>
			<!-- top10 내용을 표시할 요소 -->
			<div id="categoryContent"></div>
		</div>
	</tbody>
	

	<div>
		<a href="memberDelete.jsp">회원탈퇴</a>
		<a href="../office/admin_login.jsp">Office Lounge</a>
	</div>
</body>
</html>