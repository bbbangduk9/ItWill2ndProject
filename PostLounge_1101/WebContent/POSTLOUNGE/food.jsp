<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음식</title>
</head>
<body>
	<h1>음식</h1>
		<table border>
			<thead>
				<tr>
					<th>순위</th>
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
				<c:forEach var="post" items="${postList }" varStatus="status"
							begin="0" end="9" step="1">
					<tr>
						<td>${status.index + 1}</td> <!-- 게시물 위에서부터 순위 표시 -->
						<td>${post.postType }</td>
						<td><a href="controller?type=detailMain&postIdx=${post.postIdx}">
								${post.title }</a></td>
						<td><a href="controller?type=detailMain&postIdx=${post.postIdx}">
								${post.postContent} </a></td>
						<td>${post.nickname }</td>
						<td>
							<fmt:formatDate value="${post.postDate}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>${post.hit }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</body>
</html>