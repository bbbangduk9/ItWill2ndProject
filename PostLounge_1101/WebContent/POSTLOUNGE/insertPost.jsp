<%@page import="com.postlounge.model.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성하기</title>
<script>
	
	function list_go(){
		location.href="index.jsp";
	}
	
</script>
<style>
/* body {
		background-color: #E4FFF2
	} */
	
	/* 캡션 (제목)을 가운데 정렬하기 위한 스타일 */
	table {
		margin: 0 auto;
	}
	
	caption {
		/* 셀 내용 가운데 정렬하기 위한 스타일 */
		text-align: center;
		/* 캡션 (제목)의 글꼴 스타일 변경 */
		font-family: Arial, sans-serif; /* 원하는 글꼴 설정 */
		font-size: 24px; /* 원하는 글꼴 크기 설정 */
		
	}
	
	/* 내용 입력 칸의 크기 조정 */
	td textarea {
		width: 800px; /* 원하는 가로 크기로 조정 */
		height: 500px; /* 원하는 세로 크기로 조정 */
	}
	/* tfoot에 있는 버튼 목록에 일괄적으로 줄 스타일 */
	tfoot tr td input {
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
	/* 클릭 시 배경색 변경 */
	tfoot tr td input:active {
		background-color: #6a9c3c;
	}
</style>
</head>
<body>
<!-- 로고버튼 클릭기능 이미지로 하려 했으나 서버내에 저장된 데이터만 사용가능하기에
 버튼으로 기능만 구현하여 대체 -->
<jsp:include page="mainPageLogo.jsp" />
<div id="bbs">
	<form action="insertPost" method="post" enctype="multipart/form-data" 
		id="writeForm">		
	<table>
		<caption>게시판 글쓰기</caption>
		<tbody>
			<tr>
				<th style="font-size: 18px;">제목</th>
				<td>
					<input type="text" name="title" title="제목" style="font-size: 18px;">
				</td>
			</tr>
			<tr>
				<th>카테고리</th>
				<td>
					<select name="postType" title="카테고리">
						<option value="0">음식</option>
						<option value="1">건강</option>
						<option value="2">스포츠</option>
						<option value="3">음악</option>
						<option value="4">영화</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<p><input type="text" name="writer" title="작성자" 
						value="<%=((MemberVO)session.getAttribute("loginUser")).getNickname() %>" readonly></p>
				</td>
			</tr>	
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" rows="8" cols="50" title="내용"></textarea>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="file" name="fileName">
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2" style="text-align: center;">
					<input type="submit" value="저장" style="margin-right: 30px;">
					<input type="reset" value="초기화" style="margin-right: 30px;">
					<input type="button" value="메인페이지" onclick="list_go()">
				</td>
			</tr>
	</table>	
	</form>
</div>
</body>
</html>