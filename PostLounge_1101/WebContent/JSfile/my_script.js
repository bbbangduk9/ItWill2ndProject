$(document).ready(function() {
		$("#profile").click(function() {
			myProfile("myprofile.jsp");
		});
		
		$("#mypostlist").click(function() {
			mypostlist("controller?type=myPostList");
		});
		
		function myProfile(url) {
			$.ajax({
			     url: url,
			     type: "GET",
			     success: function(response) {
			       $("#myprofileContent").html(response); // 응답 받은 데이터를 searchContent 요소에 삽입
			     },
			     error: function(xhr, status, error) { 
			       console.log(error);
			     }
			});
		}
		
		function mypostlist(url) {
			$.ajax({
			     url: url,
			     type: "GET",
			     success: function(response) {
			       $("#myprofileContent").html(response); // 응답 받은 데이터를 searchContent 요소에 삽입
			     },
			     error: function(xhr, status, error) { 
			       console.log(error);
			     }
			});
		}
	});