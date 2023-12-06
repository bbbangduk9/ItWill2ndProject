<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 게시글</title>
<style>
/* 	body {
		background-color: #E4FFF2
	}
	 */
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
    	text-align: center; /* 표의 마지막 열 우측 정렬 */
  	}
</style>
</head>
<body>	
	<table border>
		<thead>
			<tr>
				<th>글번호</th>
				<th>카테고리</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th>작성일자</th>
				<th>조회수</th>
			</tr>
		</thead>
		<!-- 조회수 순으로 순위정렬 구현안됌 -->
		<tbody>
			<c:forEach var="post" items="${myPostList }" varStatus="status"
							begin="0" end="9" step="1">
					<tr>
						<td>${status.index + 1}</td> <!-- 게시물 위에서부터 순위 표시 -->
						<td>${post.postType }</td>
						<td><a href="controller?type=detail&postIdx=${post.postIdx}">
								${post.title }</a></td>
						<td>${post.postContent }</td>
						<td>${post.nickname }</td>
						<td>
							<fmt:formatDate value="${post.postDate}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>${post.hit }</td>
						<td><a href="updateSelect?postIdx=${post.postIdx}">게시물 수정</a></td>
						<td><a href="postdelete.jsp?postIdx=${post.postIdx}">게시물 삭제</a></td>
					</tr>
				</c:forEach>
		</tbody>
	</table>
</body>
</html>