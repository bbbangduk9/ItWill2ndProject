<%@page import="com.postlounge.model.vo.PostVO"%>
<%@page import="java.util.List"%>
<%@page import="com.postlounge.model.dao.PostDAO"%>
<%@page import="com.postlounge.model.vo.PostPagingVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%//@@@ B. 게시글 페이징 처리를 하기 위함 @@@ //
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
	List<PostVO> list = PostDAO.managePost(postPage.getBegin(), postPage.getEnd());
	System.out.println(">> 현재페이지 글목록(list) : " + list);		%>

<%//@@@ JSTL, EL 사용을 위해 scope에 데이터 등록(page 영역)
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("postPage", postPage);		 %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>(관리자)Post Lounge</title>
<link href="../css/admin_post.css?after" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	$(document).ready(function(){
	    $(".chk").change(function(){
	        console.log(">> checkbox change 이벤트 발생!!");
	        
	        let chkValue = "";
	        $(".chk:checked").each(function(){
	        	chkValue += $(this).val() + ",";
	        });
	        console.log("chkValue : " + chkValue);
	        
	    });
	
	    // "삭제" 버튼 클릭 이벤트
	    $("#del").on("click", function(){
	        console.log("삭제 버튼이 실행된다.");
	
	        // 선택된 체크박스 값을 배열에 저장
	        let chkValue = "";
	        $(".chk:checked").each(function(){
				chkValue += $(this).val() + ",";
			});
	
	        if (chkValue == "") {
	            alert('삭제할 글 번호를 선택해주세요.');
	        } else {
	            // 폼을 사용하여 선택된 데이터를 전송
	            $("#deleteForm").submit();
	        }
	    });
	});
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
	
	<div class="<!-- secondBar -->">
	    	<div class=center>
	        <!-- 삭제 버튼을 두면서 form을 사용하여 선택된 데이터 전송 -->
		        <form id="deleteForm" action="admin_del_post.jsp" method="post">
		            <!-- "삭제" 버튼 추가 -->
		            <button type="button" id="del">삭제</button>
			            <table id="boxTable" border="1">
							<thead>
								<tr>
									<!-- <th><input type="checkbox"></th> -->
									<th colspan="2">글번호</th>
									<th>제목</th>
									<th>카테고리</th>
									<th>조회수</th>
									<th>작성일</th>
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
								<td><input type="checkbox" name="postIdx" class="chk" value="${vo.postIdx}" ></td>
								<td>${vo.postIdx }</td>
								<td class="align-left">
									<a id="aa" href="admin_view.jsp?postIdx=${vo.postIdx }&cPage=${postPage.nowPage}">${vo.title }</a>
								</td>
								<td>${vo.postType }</td>
								<td>${vo.hit }</td>
								<td>${vo.postDate }</td>
							</tr>
							</c:forEach>
						</c:otherwise>
						</c:choose>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="7">
									<ol class="paging">
									<!-- [이전으로]에 대한 사용여부 처리 -->
									<c:if test="${postPage.beginPage == 1 }">
										<li class="disable">이전으로</li>
									</c:if>
									<c:if test="${postPage.beginPage != 1 }">
										<li>
											<a href="admin_manage_post.jsp?cPage=${postPage.beginPage - 1 }">이전으로</a>
										</li>
									</c:if>
									<!-- 블록내에 표시할 페이지 태그 작성(시작페이지~끝페이지) -->
									<c:forEach var="pageNo" begin="${postPage.beginPage }" end="${postPage.endPage }">
									<c:if test="${pageNo == postPage.nowPage }">	
										<li class="now">${pageNo }</li>
									</c:if>
									<c:if test="${pageNo != postPage.nowPage }">	
										<li>
											<a href="admin_manage_post.jsp?cPage=${pageNo }">${pageNo }</a>
										</li>
									</c:if>
									</c:forEach>
									<!-- [다음으로]에 대한 사용여부 처리 -->
									<c:if test="${postPage.beginPage < postPage.totalPage }">
										<li>
											<a href="admin_manage_post.jsp?cPage=${postPage.endPage + 1 }">다음으로</a>
										</li>
									</c:if>
									</ol>
								</td>
							</tr>
						</tfoot>
					</table>
		    </form>
		</div>
	</div>
</body>
</html>


