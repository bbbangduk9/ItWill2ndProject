<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기</title>
</head>
<body>
    <form action="controller?type=findMyId" method="post">
        <table>
            <tr>
                <td>이름 :</td>
                <td><input type="text" name="name" required></td>
            </tr>
            <tr>
                <td>닉네임 :</td>
                <td><input type="text" name="nickname" required></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit"><span>아이디 찾기</span></button>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>