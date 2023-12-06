<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글작성</title>
<script>

	function confirm_login(){
		var confirmation = confirm("라운지 로그인 하신 후 이용해 주시기 바랍니다.");
		if (confirmation){
			location.href="login.jsp"
		} 
		//취소 아무런 작업 없음
	}
	
	//댓글란 글자 수 제한
	function checkLength(textarea, maxLength) {
	    var text = textarea.value;
	    if (text.length > maxLength) {
	        textarea.value = text.substring(0, maxLength);
	    }
	    
	    var remaining = maxLength - textarea.value.length;
	    var charCount = document.getElementById("charCount");
	    charCount.textContent = remaining + " characters remaining";
	}
	
</script>
</head>
<body>
	<div>
	<form action="controller?type=comment" method="post" id="writeForm">		
		<c:choose>
			<c:when test="${empty sessionScope.loginUser }">
			</c:when>
			<c:otherwise>
				<caption>${sessionScope.loginUser.nickname}</caption>
			</c:otherwise>
		</c:choose>
		<tbody>
			<fieldset>
				<legend>댓글쓰기</legend>
				<c:choose>
					<c:when test="${empty sessionScope.loginUser}">
						<textarea title="댓글" id="textarea" rows="5" cols="50" ></textarea>
							<label>댓글을 작성하려면 <a href="login.jsp">로그인</a> 해주세요</label>
					</c:when>
					<c:otherwise>
						<textarea name="content" rows="3" cols="30" title="댓글내용" oninput="checkLength(this, 300)" ></textarea>
						<div id="charCount">300 characters remaining</div>
					</c:otherwise>
				</c:choose>
			</fieldset>
		</tbody>
		<tfoot>
			<c:choose>
				<c:when test="${empty sessionScope.loginUser}">
					<input type="button" onclick="confirm_login()" value="등록" >
				</c:when>
				<c:otherwise>
					<div>
	       				<button type="submit">등록</button>
	       				<input type="hidden" name="postIdx" value="${param.postIdx}">
	       				<p></p>
	       			</div>
   				</c:otherwise>
			</c:choose>
	</form>
	</div>
	
</body>
</html>