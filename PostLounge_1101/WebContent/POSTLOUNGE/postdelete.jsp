<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포스트 삭제 페이지</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
<script>
    $(document).ready(function() {
        $("#postdelete").click(function() {
            var postIdx = "${param.postIdx}";
            alert("메세지 : " + postIdx);
            location.href = "postdelete?postIdx=" + postIdx;
        });
    });
</script>
<style>
	/* body 전체 배경색 */
/* 	body {
		background-color: #E4FFF2
	} */
	h1 {
	text-align: center;
    font-size: 30px;
    margin-top: 50px; /* 50px 아래로 이동 */
	}
	.deletebutton{
		text-align: center;
	}
	.deletebutton button {
    margin-right: 30px; /* 각 버튼 우측에 10px 마진을 추가하여 간격을 띄웁니다. */
    background-color: #81c147;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
    border-radius: 10px; /* 모서리를 10px 둥글게 만듭니다. */
   	overflow: hidden; /* 테이블 내용이 둥근 테두리를 벗어나지 않도록 합니다. */
   	box-shadow: 0px 1px 0px rgba(0, 0, 0, 0.5);
   	transition: background-color 0.3s;
	}
	.deletebutton button:active {
		background-color: #6a9c3c;
	}
</style>
</head>
<body>
<!-- 로고버튼 클릭기능 이미지로 하려 했으나 서버내에 저장된 데이터만 사용가능하기에
 버튼으로 기능만 구현하여 대체 -->
<jsp:include page="mainPageLogo.jsp" />
	<h1>게시물을 삭제하시겠습니까?</h1>
	<div class="deletebutton">
		<button id="postdelete" value="${param.postIdx}">게시물 삭제</button>
		<button id="cancel" onclick="location.href='my.jsp'">취소하기</button> 
	</div>
</body>
</html>