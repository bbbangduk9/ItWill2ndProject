package com.postlounge.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.postlounge.model.vo.AllMixVO;
import com.postlounge.model.vo.CommentVO;
import com.postlounge.model.vo.PostVO;
import com.postlounge.mybatis.DBService;

public class CommentDAO {

	// 댓글 작성
	public static int insertComment(CommentVO vo) {
		SqlSession ss = DBService.getFactory().openSession();
		int result = 0;
		try {
			result = ss.insert("comment.insertComment", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.commit();
			ss.close();
		}

		return result;
	}

	// 댓글 삭제
	public static int deleteComment(int commentIdx) {
		SqlSession ss = DBService.getFactory().openSession();
		int result = 0;
		try {
			result = ss.delete("comment.deleteComment", commentIdx);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("deleteComment() 실패");
		} finally {
			ss.commit();
			ss.close();
		}
		return result;
	}

	// 댓글 조회
	public static List<AllMixVO> selectComment(int postIdx) {
		SqlSession ss = DBService.getFactory().openSession();
		List<AllMixVO> vo = null;
		try {
			vo = ss.selectList("comment.selectComment", postIdx);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return vo;
	}

	// 댓글 수정
	public static int updateComment(CommentVO vo) {
		SqlSession ss = DBService.getFactory().openSession();
		int result = 0;

		try {
			result = ss.update("comment.updateComment", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.commit();
			ss.close();
		}
		return result;
	}
	
	//댓글 더 불러오기(무한페이지)
	public static List<CommentVO> moreComment(int postIdx, int lastCommentIdx){
		SqlSession ss = DBService.getFactory().openSession();
		List<CommentVO> vo = null;
		
		try {
	        Map<String, Integer> parameters = new HashMap<>();
	        parameters.put("postIdx", postIdx);
	        parameters.put("lastCommentIdx", lastCommentIdx);

	        vo = ss.selectList("comment.getMoreComments", parameters);
	    } catch (Exception e) {
	        e.printStackTrace(); // 또는 로그에 기록
	        // 예외 처리 코드를 추가하여 적절히 처리
	        // 빈 목록 또는 예외를 적절히 처리하도록 수정
	    } finally {
	        ss.close();
	    }

	    return vo;
	}
	
	//댓글IDX로 해당댓글 정보 가져오기(신고)
	public static AllMixVO commInfo(int commentIdx) {
		SqlSession ss = DBService.getFactory().openSession();
		AllMixVO vo = null;
		
		try {
			vo = ss.selectOne("comment.getCommentInfo", commentIdx);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return vo;
	}
	
	// [이하영] 페이지에 해당하는 최신댓글 목록 가져오기
	public static List<CommentVO> manageComm(int begin, int end) {
		Map<String, Integer> map = new HashMap<>();
		map.put("begin", begin);
		map.put("end", end);

		SqlSession ss = DBService.getFactory().openSession();
		List<CommentVO> list = ss.selectList("comment.commBox", map);
		ss.close();

		return list;
	}
	
	// 게시글에 딸린 댓글 목록
	public static List<CommentVO> getPostComm(int postIdx) {
		SqlSession ss = DBService.getFactory().openSession();
		List<CommentVO> list = ss.selectList("comment.onePostComm", postIdx);
		ss.close();

		return list;
	}

	// DB의 COMMENTS 테이블에 있는 전체 게시글 수 조회
	public static int getTotalCnt() {
		int totalCnt = 0;
		
		SqlSession ss = DBService.getFactory().openSession();
		try {
			totalCnt = ss.selectOne("comment.totalCnt");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		
		return totalCnt;
	}
}
