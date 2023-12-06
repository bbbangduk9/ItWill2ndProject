$(document).ready(function() {
		  $("#ctgr_all").click(function() {
		    loadContent("getPostList"); // PostAllList에서 Controller를 통해 all.jsp를 경유해 내용 로드
		  });
	
		  $("#ctgr_food").click(function() {
		    loadFoodContent("post"); // food.jsp에서 내용 로드
		  });
		  function loadFoodContent(url) {
			   $.ajax({
			     url: url,
			     type: "GET",
			     data: { type: "food" }, // 예시로 음식 카테고리인 경우 "food"라는 값을 전달
			     success: function(response) {
			       $("#categoryContent").html(response); // 응답 받은 데이터를 content 요소에 삽입
			     },
			     error: function(xhr, status, error) {
			       console.log(error); 
			     }
			   });
			}
	
		  $("#ctgr_health").click(function() {
		    loadHealthContent("post"); // health.jsp에서 내용 로드
		  });
		  function loadHealthContent(url) {
			   $.ajax({
			     url: url,
			     type: "GET",
			     data: { type: "health" }, // 예시로 건강 카테고리인 경우 "health"라는 값을 전달
			     success: function(response) {
			       $("#categoryContent").html(response); // 응답 받은 데이터를 content 요소에 삽입
			     },
			     error: function(xhr, status, error) { 
			       console.log(error); 
			     }
			   });
			}
	
		  $("#ctgr_sport").click(function() {
		    loadSportContent("post"); // sport.jsp에서 내용 로드
		  });
		  function loadSportContent(url) {
			   $.ajax({
			     url: url,
			     type: "GET",
			     data: { type: "sport" }, // 예시로 스포츠 카테고리인 경우 "sport"라는 값을 전달
			     success: function(response) {
			       $("#categoryContent").html(response); // 응답 받은 데이터를 content 요소에 삽입
			     },
			     error: function(xhr, status, error) { 
			       console.log(error); 
			     }
			   });
			}
	
		  $("#ctgr_music").click(function() {
		    loadMusicContent("post"); // music.jsp에서 내용 로드
		  });
		  function loadMusicContent(url) {
			   $.ajax({
			     url: url,
			     type: "GET",
			     data: { type: "music" }, // 예시로 음악 카테고리인 경우 "music"라는 값을 전달
			     success: function(response) {
			       $("#categoryContent").html(response); // 응답 받은 데이터를 content 요소에 삽입
			     },
			     error: function(xhr, status, error) { 
			       console.log(error); 
			     }
			   });
			}
	
		  $("#ctgr_movie").click(function() {
		    loadMovieContent("post"); // movie.jsp에서 내용 로드
		  });
		});
		function loadMovieContent(url) {
		   $.ajax({
		     url: url,
		     type: "GET",
		     data: { type: "movie" }, // 예시로 영화 카테고리인 경우 "movie"라는 값을 전달
		     success: function(response) {
		       $("#categoryContent").html(response); // 응답 받은 데이터를 content 요소에 삽입
		     },
		     error: function(xhr, status, error) { 
		       console.log(error); 
		     }
		   });
		}
		
		// 전체 게시글 응답
		function loadContent(url) {
		   $.ajax({
		     url: url,
		     type: "GET",
		     success: function(response) {
		       $("#categoryContent").html(response); // 응답 받은 데이터를 content 요소에 삽입
		     },
		     error: function(xhr, status, error) { 
		       console.log(error); 
		     }
		   });
		}