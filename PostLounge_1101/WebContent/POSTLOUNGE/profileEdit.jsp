<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>프로필 수정</title>
</head>
<body>
    <h1>프로필 수정</h1>
    <form action="controller?type=profileEdit" method="post" enctype="multipart/form-data">
        <div>
            <label for="imageFile">프로필 이미지 선택:</label>
            <input type="file" name="imageFile" id="imageFile" accept="image/*">
        </div>
        <input type="submit" value="프로필 이미지 업로드">
    </form>
    <!-- 기타 프로필 정보 수정 필드를 추가할 수 있습니다. -->
    <p><a href="index.jsp">메인화면로 가기</a></p>
</body>
</html>
