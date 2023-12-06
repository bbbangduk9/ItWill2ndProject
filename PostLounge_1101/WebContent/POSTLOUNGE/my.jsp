<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My페이지</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
<script src="<%= request.getContextPath() %>/JSfile/my_script.js"></script>
</head>
<style>
/* 	body {
		background-color: #E4FFF2
	} */
		h1 {
		background-color: #09ffa2;
		padding: 10px 30px;
		margin: 0;
		border-radius: 10px; /* 모서리를 10px 둥글게 만듭니다. */
	   	overflow: hidden;
	   	box-shadow: 0px 2px 0px rgba(0, 0, 0, 0.5);
	}
	.ddd {
		background-color: #edf5e0;
	}
	.myselecttable {
    	margin: 0 auto;
    	text-align: center;
  	}
	#profile button {
		margin-right: 50px; /* 프로필 버튼 오른쪽에 25px 간격 설정 */
		background-color: #81c147;
		padding: 10px 20px;
		border: none;
		cursor: pointer;
		border-radius: 10px; /* 모서리를 10px 둥글게 만듭니다. */
	   	overflow: hidden; /* 테이블 내용이 둥근 테두리를 벗어나지 않도록 합니다. */
	   	box-shadow: 0px 1px 0px rgba(0, 0, 0, 0.5);
	}
	#mypostlist button {
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
	#mypostlist button:active {
		background-color: #6a9c3c;
	}
</style>
<body>
<!-- 로고버튼 클릭기능 이미지로 하려 했으나 서버내에 저장된 데이터만 사용가능하기에
 버튼으로 기능만 구현하여 대체 -->
<jsp:include page="mainPageLogo.jsp" />
	<h1>My페이지</h1>
	
<!-- 프로필 이미지 넣을 공간 -->
	<%-- ${내사진 }
	${닉네임 } --%>
	
	<h4>내 게시글 <a href="controller?type=myPostList">${myPostListCnt}</a></h4>
		
		<table class="myselecttable">
			<tr>
				<th id="profile"><button>프로필</button></th>
				<th id="mypostlist"><button>내가 쓴 게시글</button></th>
			</tr>
		</table>
	<br><br>
		<div id="myprofileContent"></div>
		
		
<script>
$(document).ready(function() {
	$("#profile").click(function() {
	location.href="profileInfo.jsp";
	});
});
</script>
	<!-- 프로필
	1. 아이디
	2. 닉네임
	3. 게시한 포스트 갯수
		내가 쓴 포스트 리스트
	 idx 값으로 테이블 조인해서 id 데이터 내에 게시글 조회
	-->
	
</body>
</html>