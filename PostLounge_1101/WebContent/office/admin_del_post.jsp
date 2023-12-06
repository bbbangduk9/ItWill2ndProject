<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.postlounge.mybatis.DBService"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.postlounge.model.vo.PostVO"%>
<%@page import="java.util.List"%>
<%@page import="com.postlounge.model.dao.PostDAO"%>
<%@page import="com.postlounge.model.vo.PostPagingVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.setCharacterEncoding("UTF-8");
    
    String[] postIdxArray = request.getParameterValues("postIdx");
    
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> postIdxArray" + Arrays.toString(postIdxArray));
    
    int result = 0;
    // 1-2. 전달받은 session 데이터(VO) 확인
    PostVO vo = (PostVO) session.getAttribute("PostVO");
    
    if (vo == null) {
        vo = new PostVO();
        session.setAttribute("PostVO", vo);
    }
    
    System.out.println("저장된 postIdx 값 : " + vo.getPostIdx());
    
    System.out.println(">del_ok.jsp postIdx 저장된 값 확인 : " + Arrays.toString(postIdxArray));
    
    // 2. 글번호 일치여부 확인 후 DB 연동 작업(삭제 - DELETE)
    result = -999; // 기본값 (-999) 글번호 불일치 의미
    
    List<Integer> postIdxList = new ArrayList<Integer>();
    
    if (postIdxArray != null && postIdxArray.length > 0) {
        for (String postIdx : postIdxArray) {
            postIdxList.add(Integer.parseInt(postIdx));
        }
        
        System.out.println("::: 게시글 번호 일치!! 삭제작업 진행합니다");
        // DB 데이터 삭제 처리
        result = delete(postIdxList);
    }
%>
<%!
    private int delete(List<Integer> postIdxList) {
        int result = 0;
	
        if (postIdxList.isEmpty()) {
            return result; // 삭제할 항목이 없으면 0을 반환
        }
	
        // DB 데이터 삭제 처리
        SqlSession ss = DBService.getFactory().openSession(true); // 오토커밋 상태
	
        try {
            result = ss.delete("post.delPostIdxList", postIdxList);
            ss.commit(); // 명시적 커밋 처리
            System.out.println(">> 정상실행 삭제건수 result : " + result);
        } catch (Exception e) {
            result = -1;
            ss.rollback(); // 명시적 롤백 처리
            System.out.println(">> 예외발생 result : " + result);
            e.printStackTrace();
        } finally {
            System.out.println(">>>> finally 구문 실행");
            ss.close();
        }
        return result;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>(관리자)Post Lounge</title>
<link href="../css/admin_post.css?after" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	let result = <%=result %>;
	
	if (result == -999) { //암호불일치
		alert("글번호가 일치하지 않습니다.");
		location.href = "admin_manage_post.jsp";
	}
	if (result == -1) { //예외발생시
		alert("[예외발생] 삭제실패. 개발팀에 문의해주세요.\n" +
				"pstlng_dev@postlounge.com");
		location.href = "admin_manage_post.jsp";
	}
	if (result == 0) { //정상실행 - 삭제안됨(데이터 없음)
		alert("[삭제실패] 이미 삭제된 게시글입니다.\n");
		location.href = "admin_manage_post.jsp";
	}
	if (result > 0) { //삭제성공
		alert(result + "건 삭제되었습니다\n");
		location.href = "admin_manage_post.jsp";
	}
</script>
</body>
</html>