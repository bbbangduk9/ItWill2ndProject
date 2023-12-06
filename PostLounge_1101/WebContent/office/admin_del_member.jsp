<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.postlounge.model.vo.CommentVO"%>
<%@page import="com.postlounge.mybatis.DBService"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.postlounge.model.vo.PostVO"%>
<%@page import="java.util.List"%>
<%@page import="com.postlounge.model.dao.PostDAO"%>
<%@page import="com.postlounge.model.vo.PostPagingVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding("UTF-8");
    
    String[] memIdxArray = request.getParameterValues("memberIdx");
    
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> memIdxArray" + Arrays.toString(memIdxArray));
    
    System.out.println(">del_ok.jsp memIdx 저장된 값 확인 : " + Arrays.toString(memIdxArray));
    
    // 2. 글번호 일치여부 확인 후 DB 연동 작업(삭제 - DELETE)
    int result = 0;
    result = -999; // 기본값 (-999) 글번호 불일치 의미
    
    List<Integer> memIdxList = new ArrayList<Integer>();
    
    if (memIdxArray != null && memIdxArray.length > 0) {
        for (String memIdx : memIdxArray) {
            memIdxList.add(Integer.parseInt(memIdx));
        }
        
        System.out.println("::: 회원 번호 일치!! 삭제작업 진행합니다");
        // DB 데이터 삭제 처리
        result = update(memIdxList);
    }
%>
<%!
    private int update(List<Integer> memIdxList) {
        int result = 0;
	
        if (memIdxList.isEmpty()) {
            return result; // 삭제할 항목이 없으면 0을 반환
        }
	
        // DB 데이터 삭제 처리
        SqlSession ss = DBService.getFactory().openSession(true); // 오토커밋 상태
	
        try {
            result = ss.update("member.delMemIdxList", memIdxList);
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
		alert("회원번호가 일치하지 않습니다.");
		location.href = "admin_manage_member.jsp";
	}
	if (result == -1) { //예외발생시
		alert("[예외발생] 삭제실패. 개발팀에 문의해주세요.\n" +
				"pstlng_dev@postlounge.com");
		location.href = "admin_manage_member.jsp";
	}
	if (result == 0) { //정상실행 - 삭제안됨(데이터 없음)
		alert("[삭제실패] 이미 삭제된 댓글입니다.\n");
		location.href = "admin_manage_member.jsp";
	}
	if (result > 0) { //삭제성공
		alert(result + "건 삭제되었습니다\n");
		location.href = "admin_manage_member.jsp";
	}
</script>
</body>
</html>