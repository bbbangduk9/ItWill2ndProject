package com.postlounge.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.postlounge.model.vo.PostListVO;
import com.postlounge.model.vo.PostVO;
import com.postlounge.mybatis.DBService;

public class PostDAO {

	// 게시물 업로드
	public static int writePost(PostVO vo) {
		SqlSession ss = null;
		int result = -1;
		try {
			ss = DBService.getFactory().openSession();
			result = ss.insert("post.insertPost", vo);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(ss != null) {
				ss.commit();
				ss.close();
			}
		}
		return result;
	}
	
	//--- 모든 글 리스트 가져오기(주현님 코드 수정해서 가져옴) => 베스트로 사용해야됨 ----
	public static List<PostListVO> getPostList() {
		SqlSession ss = DBService.getFactory().openSession();
		List<PostListVO> list = null;
		try {
			list = ss.selectList("post.getPostList");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	// 내가 팔로우하는 사람들의 게시글 목록 가져오기
	public static List<PostListVO> getFriendsPost(int memberIdx) {
		SqlSession ss = null;
		List<PostListVO> list = null;
		
		try {
			ss = DBService.getFactory().openSession();
			list = ss.selectList("post.getFriendsPost", memberIdx);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(ss != null) {
				ss.close();
			}
		}	
		return list;
	}

	// 글 상세보기
	public static PostListVO selectOne(int postIdx) {
		SqlSession ss = null;
		PostListVO vo = null;

		try {
			ss = DBService.getFactory().openSession();
			vo = ss.selectOne("post.selectOne", postIdx);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss != null) {
				ss.close();
			}
		}
		return vo;
	}

	// (추가)게시물 클릭시 조회수 증가시키기
	public static int updateHit(int postIdx) {
		SqlSession ss = DBService.getFactory().openSession();
		int result = -1;
		try {
			result = ss.update("post.updateHit", postIdx);
			ss.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return result;
	}
	
	//(추가) 페이지에 해당하는 내 팔로우의 게시글 가져오기
	public static List<PostListVO> getList(int begin, int end, int memberIdx) {
		Map<String, Integer> map = new HashMap<>();
		map.put("begin", begin);
		map.put("end", end);
		map.put("memberIdx", memberIdx);
		List<PostListVO> list = null;
		SqlSession ss = DBService.getFactory().openSession();
		try {
			list = ss.selectList("post.list", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.commit();
			ss.close();
		}
		return list;
	}

	//[김주현]=====================================================
	// --- 카테고리별 글 리스트 가져오기
	public static List<PostListVO> ctTypelist(String type) {
		SqlSession ss = DBService.getFactory().openSession();
		List<PostListVO> result = null;
		try {
			result = ss.selectList("post.ctTypelist", type);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return result;
	}

	// 내 게시글 리스트 가져오기
	public static List<PostListVO> getMyPostList(String memberId) {
		SqlSession ss = DBService.getFactory().openSession();
		List<PostListVO> list = null;
		try {
			list = ss.selectList("post.getMyPostList", memberId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return list;
	}

	// 내 게시글 수정하기
	public static int updatePost(int postIdx, String title, String postContent) {
		SqlSession ss = DBService.getFactory().openSession();
	        PostListVO vo = new PostListVO();
	        vo.setPostIdx(postIdx);
	        vo.setTitle(title);
	        vo.setPostContent(postContent);
	        System.out.println("updateDAO : " + postContent);
	        int postUpdate = ss.update("post.updatePost", vo);
	        System.out.println("PostDAO vo : " + vo);
	        System.out.println("PostDAO update : " + postUpdate);
		        
	        try {
	        	ss.commit();
	        } finally {
	        	ss.close();
	        }
    	return postUpdate;
	}

	// 게시물 삭제하기
	public static int deleteSelect(int postIdx) {
		SqlSession ss = DBService.getFactory().openSession();
		int vo = -1;
		try {
			vo = ss.delete("post.deleteSelect", postIdx);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.commit();
			ss.close();
		}
		return vo;
	}

	// =========================================================
	
	// @@@ A.관리자 메인화면에서 오늘 업데이트 된 게시글 수를 불러오는 메소드 @@@ //	
		//오늘 게시된 전체 게시글 수 조회 - POSTS 테이블
		public static int getCntPostAll() {
			int cntPost = 0;
			
			SqlSession ss = DBService.getFactory().openSession();
			try {
				cntPost = ss.selectOne("post.cntPostAll");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (ss != null) {
					ss.close();
				}
			}
			return cntPost;
		}
		
		//오늘 게시된 푸드 카테고리 게시글 수 조회 - POSTS 테이블
		public static int getCntFoodAll() {
			int cntPost = 0;
			
			SqlSession ss = DBService.getFactory().openSession();
			try {
				cntPost = ss.selectOne("post.cntFoodAll");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (ss != null) {
					ss.close();
				}
			}
			return cntPost;
		}
		
		//오늘 게시된 건강 카테고리 게시글 수 조회 - POSTS 테이블
		public static int getCntHealthAll() {
			int cntPost = 0;
			
			SqlSession ss = DBService.getFactory().openSession();
			try {
				cntPost = ss.selectOne("post.cntHealthAll");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (ss != null) {
					ss.close();
				}
			}
			return cntPost;
		}
		
		//오늘 게시된 음악 카테고리 게시글 수 조회 - POSTS 테이블
		public static int getCntMusicAll() {
			int cntPost = 0;
			
			SqlSession ss = DBService.getFactory().openSession();
			try {
				cntPost = ss.selectOne("post.cntMusicAll");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (ss != null) {
					ss.close();
				}
			}
			return cntPost;
		}

		//오늘 게시된 스포츠 카테고리 게시글 수 조회 - POSTS 테이블
		public static int getCntSportAll() {
			int cntPost = 0;
			
			SqlSession ss = DBService.getFactory().openSession();
			try {
				cntPost = ss.selectOne("post.cntSportAll");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (ss != null) {
					ss.close();
				}
			}
			return cntPost;
		}
		
		//오늘 게시된 영화 카테고리 게시글 수 조회 - POSTS 테이블
		public static int getCntMovieAll() {
			int cntPost = 0;
			
			SqlSession ss = DBService.getFactory().openSession();
			try {
				cntPost = ss.selectOne("post.cntMovieAll");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (ss != null) {
					ss.close();
				}
			}
			return cntPost;
		}
	//@@@ A. 끝@@@//
		
		
		// @@@ B. 관리자 메인화면에서 게시판을 불러오기 위한 메소드@@@ //
		
		// 최신글 순서대로 전체 게시글 조회
			public static List<PostVO> getPost() {
				List<PostVO> post = null;
				SqlSession ss = null;
				
				try {
					ss = DBService.getFactory().openSession();
					post = ss.selectList("post.datePost");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (ss != null) {
						ss.close();
					}
				}
				return post;
			}
			
			// 푸드 카테고리 최신글 순서대로 게시글 조회
			public static List<PostVO> getPostFood() {
				List<PostVO> post = null;
				SqlSession ss = null;
				
				try {
					ss = DBService.getFactory().openSession();
					post = ss.selectList("post.datePostFood");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (ss != null) {
						ss.close();
					}
				}
				return post;
			}
			
			// 건강 카테고리 최신글 순서대로 게시글 조회
			public static List<PostVO> getPostHealth() {
				List<PostVO> post = null;
				SqlSession ss = null;
				
				try {
					ss = DBService.getFactory().openSession();
					post = ss.selectList("post.datePostHealth");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (ss != null) {
						ss.close();
					}
				}
				return post;
			}
			
			// 음악 카테고리 최신글 순서대로 게시글 조회
			public static List<PostVO> getPostMusic() {
				List<PostVO> post = null;
				SqlSession ss = null;
						
					try {
						ss = DBService.getFactory().openSession();
						post = ss.selectList("post.datePostMusic");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (ss != null) {
							ss.close();
						}
					}
					return post;
				}
			
			// 스포츠 카테고리 최신글 순서대로 게시글 조회
			public static List<PostVO> getPostSport() {
				List<PostVO> post = null;
				SqlSession ss = null;
						
					try {
						ss = DBService.getFactory().openSession();
						post = ss.selectList("post.datePostSport");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (ss != null) {
							ss.close();
						}
					}
					return post;
				}
			
			// 영화 카테고리 최신글 순서대로 게시글 조회
			public static List<PostVO> getPostMovie() {
				List<PostVO> post = null;
				SqlSession ss = null;
						
					try {
						ss = DBService.getFactory().openSession();
						post = ss.selectList("post.datePostMovie");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (ss != null) {
							ss.close();
						}
					}
					return post;
				}
			
			// 하나의 게시글 상세보기를 위한 메소드
			public static PostVO onePost(int postIdx) {
				SqlSession ss = DBService.getFactory().openSession();
				PostVO vo = ss.selectOne("post.onePost", postIdx);
				ss.close();
				
				return vo;
			}
			

		//@@@ B. 끝@@@//
			
		//@@@ C. 관리자 화면의 베스트에서 게시글 페이징 처리를 위한 메소드 @@@//
			
			//1. DB의 POSTS 테이블에 있는 전체 게시글 수 조회
			public static int getTotalCnt() {
				int totalCnt = 0;
				
				SqlSession ss = DBService.getFactory().openSession();
				try {
					totalCnt = ss.selectOne("post.totalCnt");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					ss.close();
				}
				
				return totalCnt;
			}
			
			//1-1. DB의 POSTS 테이블에 있는 REPORT 게시글 수 조회
			public static int getTotalReportCnt() {
				int totalCnt = 0;
				
				SqlSession ss = DBService.getFactory().openSession();
				try {
					totalCnt = ss.selectOne("post.totalReportCnt");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					ss.close();
				}
				
				return totalCnt;
			}
			
			//2. 페이지에 해당하는 글목록(게시글) 가져오기(조회) managePost
			public static List<PostVO> getList(int begin, int end) {
				Map<String, Integer> map = new HashMap<>();
				map.put("begin", begin);
				map.put("end", end);
				
				SqlSession ss = DBService.getFactory().openSession();
				List<PostVO> list = ss.selectList("post.pageList", map);
				ss.close();
				
				return list;
			}
			
			//3. 페이지에 해당하는 글목록(게시글) 가져오기(조회)
			public static List<PostVO> managePost(int begin, int end) {
				Map<String, Integer> map = new HashMap<>();
				map.put("begin", begin);
				map.put("end", end);
				
				SqlSession ss = DBService.getFactory().openSession();
				List<PostVO> list = ss.selectList("post.managePost", map);
				ss.close();
				
				return list;
			}
			
			//4. 페이지에 해당하는 글목록(게시글) 가져오기(조회)
			public static List<PostVO> searchAll(int begin, int end) {
				Map<String, Integer> map = new HashMap<>();
				map.put("begin", begin);
				map.put("end", end);
				
				SqlSession ss = DBService.getFactory().openSession();
				List<PostVO> list = ss.selectList("post.searchCnt", map);
				ss.close();
				
				return list;
			}
			
		//@@@ C. 끝 @@@//
			
		//@@@ D. 관리페이지에서 사용하는 메소드 @@@//	
			
			// 최신글 순서대로 전체 게시글 조회
			public static List<PostVO> postBox() {
				List<PostVO> post = null;
				SqlSession ss = null;
				
				try {
					ss = DBService.getFactory().openSession();
					post = ss.selectList("post.postBox");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (ss != null) {
						ss.close();
					}
				}
				return post;
			}
			
			// 게시글 삭제
			public static int postDel(int idx) {
			    SqlSession ss = DBService.getFactory().openSession(true);
			    int result = ss.delete("post.postDel", idx);
			    ss.close();
			    return result;
			}
			
			// 신고게시판(페이징)에서 POST_TYPE = REPORT 인 게시글만 불러오기
			public static List<PostVO> manageReport(int begin, int end) {
				Map<String, Integer> map = new HashMap<>();
				map.put("begin", begin);
				map.put("end", end);
				
				SqlSession ss = DBService.getFactory().openSession();
				List<PostVO> list = ss.selectList("post.manageReport", map);
				ss.close();
				
				return list;
			}
			
			

			
		//@@@ D. 끝 @@@//	
	}


