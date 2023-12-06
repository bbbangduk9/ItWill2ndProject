<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 신고 하기</title>
</head>
<body>
	<h1>신고하기</h1>
	<hr>
	<div>
		<p>전달받은 값 | </p>
		<p>작성자 | </p>
		<p>내 용 | </p>
	</div>	
	
	<div>
		<h2>사유선택</h2>
		<div>
			<form id="reportComment" action="#" method="get">
				<label>
					<input type="radio" name="report" value="spam">
					스팸홍보/도배글입니다.
				</label><br>
				<label>
					<input type="radio" name="report" value="pornography">
					음란물입니다.
				</label><br>
				<label>
					<input type="radio" name="report" value="wrongInfo">
					불법정보를 포함하고 있습니다.
				</label><br>
				<label>
					<input type="radio" name="report" value="swear">
					욕설/생명경시/혐오/차별적 표현입니다.
				</label><br>
				<label>
					<input type="radio" name="report" value="personal">
					개인정보 노출 게시물입니다.
				</label><br>
				<button type="submit">신고하기</button>
			</form>
		</div>
	</div>
</body>
</html>