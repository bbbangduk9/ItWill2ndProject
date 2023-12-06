package com.postlounge.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.postlounge.model.vo.MemberVO;
import com.postlounge.mybatis.DBService;

public class MemberDAO {

	//회원가입 
	public static int insert(MemberVO vo) {
		SqlSession ss = DBService.getFactory().openSession();
		int result = -1;
		try {
			result = ss.insert("member.insertMember", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.commit();
			ss.close();
		}
		return result;
	}
	
	//아이디 파라미터로 이름, 아이디, 비밀번호, 닉네임 출력 
	public static MemberVO selectOne (String id) {
		SqlSession ss = DBService.getFactory().openSession();
		MemberVO vo = null;
		try {
			vo = ss.selectOne("member.login", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return vo;
	}
	
	// 회원 정보 모두 출력(회원가입 중복체크) - 신민기 추가?
	public static List<MemberVO> memberInfos() {
		SqlSession ss = DBService.getFactory().openSession();
		List<MemberVO> vo = ss.selectList("member.infos");
		ss.close();
		
		return vo;
	}
	
	// 회원탈퇴 (---> 결과값 확인해야하지 않나요? - 이초희)
	public static void memberDelete(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		try {
			ss.delete("member.memberDelete", id);
			ss.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
	}
	
	//[이초희] ------------------------------------------------
	//회원 아이디 모두 출력 (기존 파일 코드 덧붙임. 필요없으면 지워도 됨 - 이초희)
	public static List<MemberVO> selectId(){
		SqlSession ss = DBService.getFactory().openSession();
		List<MemberVO> vo = null;
		try {
			vo = ss.selectList("member.findId");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return vo;
	}
	
	//회원가입시 아이디와 닉네임 중복 체크 
	public static boolean checkDuplicate(String id, String nickname){
		SqlSession ss = DBService.getFactory().openSession();
		boolean isDuplicate = false;
		try {
	        Map<String, String> params = new HashMap<>();
	        params.put("id", id);
	        params.put("nickname", nickname);
	        int result = ss.selectOne("member.duplicate", params);

	        if (result > 0) {
	            isDuplicate = true;
	        }
	    } finally {
	        ss.close();
	    }
	    return isDuplicate;
	}
	//-------------------------------------------------------
	
	//[이하영] ------------------------------------------------
	// 페이지에 해당하는 회원 가입일자 최근 순으로 가져오기
	public static List<MemberVO> manageMem(int begin, int end) {
		Map<String, Integer> map = new HashMap<>();
		map.put("begin", begin);
		map.put("end", end);
		SqlSession ss = DBService.getFactory().openSession();
		List<MemberVO> list = null;
		try {
			list = ss.selectList("member.memBox", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return list;
	}
	
	// DB의 POSTS 테이블에 있는 전체 게시글 수 조회
	public static int getTotalCnt() {
		int totalCnt = 0;
		SqlSession ss = DBService.getFactory().openSession();
		try {
			totalCnt = ss.selectOne("member.totalCnt");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return totalCnt;
	}
	//-------------------------------------------------------
	
	//[유재현] ------------------------------------------------
	//프로필
	public static MemberVO profileInfo(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		MemberVO member = ss.selectOne("member.profileInfo", id);
		ss.close();
		
		return member;
	}
	 // 프로필 업데이트
    public static int updateProfile(MemberVO member) {
        SqlSession ss = DBService.getFactory().openSession();
        int result = ss.update("member.updateProfile", member);
        ss.commit();
        ss.close();
        return result;
    }
	//아이디찾기
    public static MemberVO findMyId(String name, String nickname) {
        SqlSession ss = DBService.getFactory().openSession();
        MemberVO vo = null;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("name", name);
            params.put("nickname", nickname);
            vo = ss.selectOne("findMyId", params); //네임스페이스 안 써도 실행됨
        } finally {
            ss.close();
        }
        return vo;
    }

	//비밀번호찾기
	public static MemberVO findMyPwd(String name,String id, String nickname) {
        SqlSession ss = DBService.getFactory().openSession();
      MemberVO vo = null;
      try {
          Map<String, Object> params = new HashMap<>();
          params.put("name", name);
          params.put("id", id);
          params.put("nickname", nickname);
          vo = ss.selectOne("findMyPwd", params); //네임스페이스 안 써도 실행됨
      } finally {
          ss.close();
      }
      return vo;
  }

	//-------------------------------------------------------
}
