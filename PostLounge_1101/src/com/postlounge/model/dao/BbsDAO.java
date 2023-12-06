package com.postlounge.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.postlounge.model.vo.BbsVO;
import com.postlounge.model.vo.CommentVO;
import com.postlounge.mybatis.DBService;

public class BbsDAO {
	
	//수업자료~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	//게시글(BBS) 전체 건수 조회
	public static int getTotalCount() {
		int totalCount = 0;
		
		SqlSession ss = DBService.getFactory().openSession();
		try {
			totalCount = ss.selectOne("bbs.totalCount");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		
		return totalCount;
	}
	
	//페이지에 해당하는 글목록(게시글) 가져오기(조회)
	public static List<BbsVO> getList(int begin, int end) {
		Map<String, Integer> map = new HashMap<>();
		map.put("begin", begin);
		map.put("end", end);
		
		SqlSession ss = DBService.getFactory().openSession();
		List<BbsVO> list = ss.selectList("bbs.list", map);
		ss.close();
		
		return list;
	}
	
	//게시글 조회
	public static BbsVO selectOne(int bbsIdx) {
		SqlSession ss = DBService.getFactory().openSession();
		BbsVO vo = ss.selectOne("bbs.one", bbsIdx);
		ss.close();
		return vo;
	}
	
	//게시글 조회수 증가
	public static void hitAdd (int bbsIdx) {
		
		SqlSession ss = DBService.getFactory().openSession();
		
		try {
			ss.update("bbs.hitAdd", bbsIdx);
			ss.commit(); // UPDATE는 commit()을 안해주면 변경내용이 저장되지 않는다.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
	}
	
	// 게시글 입력 
	public static int insert (BbsVO bvo) {
		
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = 0;
		
		try {
			result = ss.insert("bbs.insert", bvo);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		
		return result;
	}
	
	
	
	//===============================댓 글 =============================
	
	//게시물에 해당하는 댓글 리스트 가져오기(조회)
	public static List<CommentVO> getCommList(int bbsIdx) {
		SqlSession ss = DBService.getFactory().openSession();
		List<CommentVO> list = ss.selectList("bbs.commList", bbsIdx);
		ss.close();
		
		return list;
	}

}







