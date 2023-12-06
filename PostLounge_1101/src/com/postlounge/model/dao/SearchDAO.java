package com.postlounge.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.postlounge.model.vo.PostListVO;
import com.postlounge.model.vo.PostVO;
import com.postlounge.mybatis.DBService;

public class SearchDAO {

	// 1. 제목, 내용, 작성자 검색 (분기점: mapper.xml)
	public static List<PostListVO> getSearch(String idx, String keyword) {
		Map<String, String> map = new HashMap<>();
		SqlSession ss = null;
		List<PostListVO> list = null;
		
		try {
			map.put("idx", idx); // 옵션 idx
			map.put("keyword", keyword); // 검색어
			ss = DBService.getFactory().openSession();
			list = ss.selectList("post.search", map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(ss != null) {
				ss.close();
			}
		}
		return list;
	}
	
	// 1-2. 제목, 내용, 작성자 검색 (분기점: mapper.xml) // 관리자 페이지 검색 기능 위해 복붙+수정
		public static List<PostListVO> getSearchAdmin (String idx, String keyword){
			Map<String, String> map = new HashMap<>();
			map.put("idx", idx); //옵션 idx
			map.put("keyword", keyword); //검색어
			System.out.println("idx : " + idx + "," + "keyword : " + keyword);
			
			new DBService();
			SqlSession ss = DBService.getFactory().openSession();
			List<PostListVO> list = ss.selectList("post.searchAdmin", map);
			//dao에서 idx 분기점 나누지 말고, mapper에서 하자.
			ss.close();
			
			return list;
		}

	// 2. 베스트 누르면 전체게시물 조회순으로 10개 나열 (겸 첫접속화면과 동일한 UI)
	public static List<PostVO> getTopList() {
		SqlSession ss = null;
	    List<PostVO> list = null;

	    try {
	        ss = DBService.getFactory().openSession();
	        list = ss.selectList("post.getTopList");
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (ss != null) {
	            ss.close();
	        }
	    }
	    return list;
	}

	
}
