<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.postlounge.model.vo.CommentVO"%>
<%@page import="com.postlounge.mybatis.DBService"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.postlounge.model.vo.PostVO"%>
<%@page import="java.util.List"%>
<%@page import="com.postlounge.model.dao.PostDAO"%>
<%@page import="com.postlounge.model.vo.PostPagingVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding("UTF-8");
    String[] commIdxArray = request.getParameterValues("commentIdx");
    
    System.out.println(">del_comment.jsp postIdx 저장된 값 확인 : " + Arrays.toString(commIdxArray));
    
    // 2. 글번호 일치여부 확인 후 DB 연동 작업(삭제 - DELETE)
    int result = 0;
    result = -999; // 기본값 (-999) 글번호 불일치 의미
    
    List<Integer> commIdxList = new ArrayList<Integer>();
    
    if (commIdxArray != null && commIdxArray.length > 0) {
        for (String commIdx : commIdxArray) {
            commIdxList.add(Integer.parseInt(commIdx));
        }
        System.out.println("::: 게시글 번호 일치!! 삭제작업 진행합니다");
        // DB 데이터 삭제 처리
        result = delete(commIdxList);
    }
%>
<%!
    private int delete(List<Integer> commIdxList) {
        int result = 0;
	
        if (commIdxList.isEmpty()) {
            return result; // 삭제할 항목이 없으면 0을 반환
        }
	
        // DB 데이터 삭제 처리
        SqlSession ss = DBService.getFactory().openSession(true);
	
        try {
            result = ss.delete("comment.delCommIdxList", commIdxList);
            ss.commit();
            System.out.println(">> 정상실행 삭제건수 result : " + result);
        } catch (Exception e) {
            result = -1;
            ss.rollback();
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
		alert("댓글번호가 일치하지 않습니다.");
		location.href = "admin_manage_comment.jsp";
	}
	if (result == -1) { //예외발생시
		alert("[예외발생] 삭제실패. 담당자문의(8282)\n");
		location.href = "admin_manage_comment.jsp";
	}
	if (result == 0) { //정상실행 - 삭제안됨(데이터 없음)
		alert("[삭제실패] 이미 삭제된 댓글입니다.\n");
		location.href = "admin_manage_comment.jsp";
	}
	if (result > 0) { //삭제성공
		alert(result + "건 삭제되었습니다\n");
		location.href = "admin_manage_comment.jsp";
	}
</script>
</body>
</html>