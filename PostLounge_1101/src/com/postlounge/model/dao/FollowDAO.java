package com.postlounge.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.postlounge.model.vo.FriendListVO;
import com.postlounge.model.vo.FriendVO;
import com.postlounge.model.vo.PostListVO;
import com.postlounge.mybatis.DBService;

public class FollowDAO {
	//팔로우 추가
	public static int insert(int follower, int followee) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = -1;
		try {
			result = ss.insert("friend.insertFollow", new FriendVO(follower, followee));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.commit();
			ss.close();
		}
		return result;
	}

	//팔로우 중복처리
	public static boolean isAlreadyFriends(int follower, int followee) {
	    SqlSession ss = DBService.getFactory().openSession();
	    int count = -1;
	    try {
	    	count = ss.selectOne("friend.checkFriendship", new FriendVO(follower, followee));
	    } catch (Exception e) {
	    	e.printStackTrace();
		} finally {
			ss.close();
		}
	    return count > 0; //친구인 경우 1을 리턴 = true
	}

	//로그인 유저의 팔로우 정보 조회하기
	public static List<FriendListVO> selectAll(int follower){
		SqlSession ss = DBService.getFactory().openSession();
		List<FriendListVO> list = null;
		try {
			list = ss.selectList("friend.selectAll", follower);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return list;
	}

	//팔로우 삭제하기
	public static int memberDelete(String friendIdx, int memberIdx) {
		SqlSession ss = DBService.getFactory().openSession(true);
		Map<String, Integer> map = new HashMap<>();
		map.put("friendIdx", Integer.parseInt(friendIdx));
	    map.put("memberIdx", memberIdx);
		
		int result = -1;
		try {
			result = ss.delete("friend.followDelete", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.commit();
			ss.close();
		}
		return result;
	}

	//로그인 유저 idx값으로 팔로우idx 목록 가져오기
	public static List<FriendVO> selectIdx(int memberIdx) {
		SqlSession ss = DBService.getFactory().openSession();
		List<FriendVO> vo = null;
		try {
			vo = ss.selectList("friend.findIdx", memberIdx);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return vo;
	}

	//(추가) 친구 idx값으로 isDel값 찾기
	public static int findFriendIsdel(int followee) {
		SqlSession ss = DBService.getFactory().openSession();
		int isDel = -1;
		try {
			isDel = ss.selectOne("friend.findFriendIsdel", followee);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return isDel;
	}
	
	//(추가) 페이지에 해당하는 팔로잉목록 가져오기
	public static List<FriendListVO> getList(int begin, int end, int memberIdx) {
		Map<String, Integer> map = new HashMap<>();
		map.put("begin", begin);
		map.put("end", end);
		map.put("memberIdx", memberIdx);
		System.out.println("map : " + map);
		
		List<FriendListVO> list = null;
		SqlSession ss = DBService.getFactory().openSession();
		try {
			list = ss.selectList("friend.list", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.commit();
			ss.close();
		}
		return list;
	}
	
	//(추가) 팔로워 목록 가져오기 -> FeedCommand에서 사용
	public static List<FriendListVO> getFollowerList(int follower) {
		SqlSession ss = DBService.getFactory().openSession();
		List<FriendListVO> list = null;
		try {
			list = ss.selectList("friend.selectFollowerList", follower);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return list;
	}
	
	//(추가) 페이지에 해당하는 팔로워 목록 가져오기 -> FollowerListCommand에서 사용
	public static List<FriendListVO> getFollowerPagingList(int begin, int end, int memberIdx) {
		Map<String, Integer> map = new HashMap<>();
		map.put("begin", begin);
		map.put("end", end);
		map.put("memberIdx", memberIdx);
		System.out.println("map : " + map);
		
		SqlSession ss = DBService.getFactory().openSession();
		List<FriendListVO> list = null;
		try {
			list = ss.selectList("friend.getFollowerPagingList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return list;
	}
	
}
