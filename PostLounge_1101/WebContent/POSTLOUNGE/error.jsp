<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<script>
    var errorMessage = '<%= request.getAttribute("errorMessage") %>';
    alert(errorMessage); // 또는 다른 JavaScript 코드로 errorMessage를 처리할 수 있습니다.
    history.back();
</script>
