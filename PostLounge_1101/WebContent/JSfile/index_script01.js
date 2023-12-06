//ajax를 이용해서 검색창 해당페이지에 바로 띄우기
	$(document).ready(function() {
		searchContent("search.jsp");
	});
	function searchContent(url) {
	   $.ajax({
	     url: url,
	     type: "GET",
	     success: function(response) {
	       $("#searchContent").html(response); // 응답 받은 데이터를 searchcontent 요소에 삽입
	     },
	     error: function(xhr, status, error) { 
	       console.log(error);
	     }
	   });
	}
	
	function search_go(){
		location.href="controller?type=search";
	}
	function join_go(){
		location.href="join.jsp";
	}
	function login_go(){
		location.href="login.jsp";
	}
	function best_go(){
		location.href="controller?type=best";
	}
	function index_go(){
		location.href="index.jsp";
	}
	function feed_go(){
		location.href="controller?type=feed";
	}
	function my_go(){
		location.href="my.jsp";
	}
	function logout_go(){
		location.href="logout.jsp";
	}
	function write_go() {
		let loginUser = '<%= session.getAttribute("loginUser") %>';
	    if (loginUser == null) {
	        window.location.href = "login.jsp?redirect=write";
	    } else {
	        window.location.href = "insertPost.jsp";
	    }
	}
	
	$(document).ready(function() {
		$("#getDataBtn").on("click");
    });
	
	$(document).ready(function() {
        $.ajax({
            url: "getPostList",
            type: "GET",
            success: function(response) {
                $("#categoryContent").html(response);
            }
        });
    });