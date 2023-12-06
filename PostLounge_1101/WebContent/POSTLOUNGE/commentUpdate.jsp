<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글작성</title>
<script>

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
<h1>댓글 수정 페이지[commentUpdate.jsp]</h1>
	<div>
	<form action="controller?type=updateComment" method="post" id="writeForm">		
		<fieldset>
			<legend>댓글 수정</legend>
				<textarea name="commContent" rows="3" cols="30" title="댓글내용" oninput="checkLength(this, 300)" ></textarea>
		</fieldset>
		<div>
			<button type="submit">수정확인</button>
			<input type="hidden" name=commentIdx value="${param.commentIdx}">
		</div>
	</form>
</div>
	
</body>
</html>