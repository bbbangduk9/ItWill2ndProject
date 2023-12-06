
<%@page import="com.postlounge.model.vo.PostVO"%>
<%@page import="java.util.List"%>
<%@page import="com.postlounge.model.dao.PostDAO"%>
<%@page import="com.postlounge.model.vo.PostPagingVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%//@@@ 게시글 페이징 처리를 하기 위함 @@@ //
	//0. 페이징 처리를 위한 객체(PostPagingVO) 생성
	PostPagingVO postPage = new PostPagingVO();
	
	//1. 전체 게시물 수량 구하기
	postPage.setTotalRecord(PostDAO.getTotalCnt());
	postPage.setTotalPage();
	System.out.println(">> DB의 POSTS 테이블 게시글 수 : " + postPage.getTotalRecord());
	System.out.println(">> 전체 페이지 수 : " + postPage.getTotalPage());
	
	//2. 현재 페이지 구하기
	String cPage = request.getParameter("cPage");
	if (cPage != null) {
		postPage.setNowPage(Integer.parseInt(cPage)); //PostPagingVO에 nowPage 는 int 타입이기 때문에, 받은 파라미터값을 숫자로 변환
	}
	System.out.println("파라미터명 cPage, 파라미터 값 : " + cPage);
	System.out.println("> paging nowPage : " + postPage.getNowPage());
	
	//3. 현재 페이지에 표시할 게시글 시작번호(begin), 끝번호 구하기
	postPage.setEnd(postPage.getNowPage() * postPage.getNumPerPage());
	postPage.setBegin(postPage.getEnd() - postPage.getNumPerPage() + 1);
	
	//3-1. (옵션) 끝 번호가 데이터 건수보다 많아지면 데이터 건수와 동일하게 설정
	if (postPage.getEnd() > postPage.getTotalRecord()) {
		postPage.setEnd(postPage.getTotalRecord());
	}
	System.out.println(">> (글번호/시작) 한 페이지에서의 시작번호(begin) : " + postPage.getBegin());
	System.out.println(">> (글번호/끝) 한 페이지에서의 끝번호(end) : " + postPage.getEnd());
	
	//4. 블록 시작페이지, 끝페이지 구하기(현재페이지 번호 사용)
	//4-1. 시작페이지 구하기
	int beginPage = (postPage.getNowPage() - 1) / postPage.getPagePerBlock() * postPage.getPagePerBlock() + 1;
	postPage.setBeginPage(beginPage);
	postPage.setEndPage(beginPage + postPage.getPagePerBlock() - 1);
	
	//4-2. 끝페이지(endPage)가 전체 페이지 수(totalPage) 보다 크면
	// 끝페이지를 전체 페이지 수로 변경처리(설정)
	if (postPage.getEndPage() > postPage.getTotalPage()) {
		postPage.setEndPage(postPage.getTotalPage());
	}
	
	System.out.println(">> 시작 클릭 블록 : " + postPage.getBeginPage());
	System.out.println(">> 끝 클릭 블록 : " + postPage.getEndPage());
	
	//현재 페이지번호 사용 블록번호 구하고, 블록번호로 시작/끝 페이지 구하기 (???)
	
	System.out.println("postPage" + postPage);
	//@@@ B. 끝 @@@// %>

<%	//현재 페이지 기준으로 DB 데이터(게시글) 가져오기
	//시작번호(begin), 끝번호(end) 사용해서
	List<PostVO> list = PostDAO.getList(postPage.getBegin(), postPage.getEnd());
	System.out.println(">> 현재페이지 글목록(list) : " + list);		%>

<%//@@@ JSTL, EL 사용을 위해 scope에 데이터 등록(page 영역)
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("postPage", postPage);		 %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>(관리자)Post Lounge</title>
<link href="../css/admin_best.css" rel="stylesheet" type="text/css">
<script>
	function go_main() {
		location.href = "../POSTLOUNGE/index.jsp";
	}
</script>

</head>
<body>
	<div class="firstBar">
		<ul class="firstMenu">
			<li id="postlounge"><a href="admin_main.jsp">Post Lounge(관리자 모드)</a></li>
			<li><button onclick="go_main()" id="btnLogout">로그아웃</button></li>
		</ul>
	</div>
	
	<div class="secondBar">
		<div class="secondMenu">
			<h2>Best</h2>
		</div>
	</div>
	
<div class="center">
	<div class="box">
	    <table id="boxTable" border="1">
			<thead>
				<tr class="title">
					<th colspan="2" class="subject">제목</th>
					<th class="writer">작성자</th>
					<th class="regdate">작성일</th>
					<th class="hit">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty list }">
						<tr>
							<td colspan="6">
								<h2>마지막페이지입니다.</h2>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="vo" items="${list }">
							<tr>
								<td>${vo.postIdx }</td>
								<td class="align-left">
									<a id="aa" href="admin_view.jsp?postIdx=${vo.postIdx }&cPage=${postPage.nowPage}">${vo.title }</a>
								</td>
								<td>${vo.nickname }</td>
								<td>${vo.postDate }</td>
								<td>${vo.hit }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">
						<ol class="paging">
						
						<!-- [이전으로]에 대한 사용여부 처리 -->
							<c:if test="${postPage.beginPage == 1 }">
								<li class="disable">이전으로</li>
							</c:if>
							<c:if test="${postPage.beginPage != 1 }">
								<li>
									<a href="admin_best.jsp?cPage=${postPage.beginPage - 1 }">이전으로</a>
								</li>
							</c:if>
						
						<!-- 블록내에 표시할 페이지 태그 작성(시작페이지~끝페이지) -->
						<c:forEach var="pageNo" begin="${postPage.beginPage }" end="${postPage.endPage }">
							<c:if test="${pageNo == postPage.nowPage }">	
								<li class="now">${pageNo }</li>
							</c:if>
							<c:if test="${pageNo != postPage.nowPage }">	
								<li>
									<a href="admin_best.jsp?cPage=${pageNo }">${pageNo }</a>
								</li>
							</c:if>
						</c:forEach>
						
						<!-- [다음으로]에 대한 사용여부 처리 -->
							<c:if test="${postPage.endPage > postPage.totalPage }">
								<li class="disable">으로</li>
							</c:if>
							<c:if test="${postPage.beginPage < postPage.totalPage }">
								<li>
									<a href="admin_best.jsp?cPage=${postPage.endPage + 1 }">다음으로</a>
								</li>
							</c:if>
						</ol>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
</body>
</html>