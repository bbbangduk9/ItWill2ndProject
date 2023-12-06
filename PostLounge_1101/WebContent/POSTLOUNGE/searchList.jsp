<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 결과</title>
</head>
<body>
	<h1>'${keyword}' 검색 결과</h1>
	<hr>
	<c:choose>
		<c:when test="${not empty searchResult}">
			<p>${searchResult}</p>
		</c:when>
		<c:otherwise>
			<c:forEach var="vo" items="${list }"> 
				<table border>
					<tr>
						<th>글번호</th>
						<td>${vo.postIdx}</td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td>${vo.postType}</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><a href="controller?type=detailMain&postIdx=${vo.postIdx}">
							${vo.title}</a></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><a href="controller?type=detailMain&postIdx=${vo.postIdx}">
							${vo.postContent}</a></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${vo.nickname}</td>
					</tr>
					<tr>
						<th>작성일</th>
						<td>${vo.postDate}</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td>${vo.hit}</td>
					</tr>
					<%-- <tr>
						<th>댓글</th>
						<td>${vo.commContent}</td>
					</tr> --%>
				</table>
				<br>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
	<hr><hr>
</body>
</html>