// adminmain.js

$(document).ready(function(){
	ajaxGetCateAll();
    $("#btnAll").on("click", ajaxGetCateAll);
    $("#btnFood").on("click", ajaxGetFoodAll);
    $("#btnHealth").on("click", ajaxGetHealthAll);
    $("#btnMusic").on("click", ajaxGetMusicAll);
    $("#btnSport").on("click", ajaxGetSportAll);
    $("#btnMovie").on("click", ajaxGetMovieAll);
});

    function ajaxGetCateAll() {
        //alert("> ajaxGetCateAll 메소드가 실행된다~");
        
        fetch("getCateAll", {
            method: "GET",
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            console.log(data.list);
            
             /*전달받은 JSON 데이터 사용해서 tr 태그 만들고 화면 출력*/
            let htmlTag = "";
            let postList = data.list; /* JSON 객체의 속성명 "list"의 값을 postList에 저장!*/
            
            postList.forEach(post => {
                htmlTag += "<tr>";
                htmlTag += "<td>" + post.postIdx + "</td>";
                htmlTag += "<td>" + post.title + "</td>";
                htmlTag += "<td>" + post.nickname + "</td>";
                htmlTag += "<td>" + post.postDate + "</td>";
                htmlTag += "<td>" + post.hit + "</td>";
                htmlTag += "</tr>";
            });
            
            $("#postTable").html(htmlTag);
        })
        .catch(error => {
            console.error(">> Fetch 처리 실패 : " + error);
        });
    }

    function ajaxGetFoodAll() {
        //alert("> 푸드 실행된다~");
        
        fetch("getFoodAll", {
            method: "GET",
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            console.log(data.list);
            
             /*전달받은 JSON 데이터 사용해서 tr 태그 만들고 화면 출력*/
            let htmlTag = "";
            let postList = data.list; /* JSON 객체의 속성명 "list"의 값을 postList에 저장!*/
            
			 postList.forEach(post => {
                htmlTag += "<tr>";
                htmlTag += "<td>" + post.postIdx + "</td>";
                htmlTag += "<td>" + post.title + "</td>";
                htmlTag += "<td>" + post.nickname + "</td>";
                htmlTag += "<td>" + post.postDate + "</td>";
                htmlTag += "<td>" + post.hit + "</td>";
                htmlTag += "</tr>";
            });
            
            $("#postTable").html(htmlTag);
        })
        .catch(error => {
            console.error(">> Fetch 처리 실패 : " + error);
        });
    }
    
    function ajaxGetHealthAll() {
        //alert("건강 실행된다~");
        
        fetch("getHealthAll", {
            method: "GET",
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            console.log(data.list);
            
             /*전달받은 JSON 데이터 사용해서 tr 태그 만들고 화면 출력*/
            let htmlTag = "";
            let postList = data.list; /* JSON 객체의 속성명 "list"의 값을 postList에 저장!*/
            
            postList.forEach(post => {
                htmlTag += "<tr>";
                htmlTag += "<td>" + post.postIdx + "</td>";
                htmlTag += "<td>" + post.title + "</td>";
                htmlTag += "<td>" + post.nickname + "</td>";
                htmlTag += "<td>" + post.postDate + "</td>";
                htmlTag += "<td>" + post.hit + "</td>";
                htmlTag += "</tr>";
            });
            
            $("#postTable").html(htmlTag);
        })
        .catch(error => {
            console.error(">> Fetch 처리 실패 : " + error);
        });
    }
    
    function ajaxGetMusicAll() {
        //alert("음악 실행된다~");
        
        fetch("getMusicAll", {
            method: "GET",
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            console.log(data.list);
            
             /*전달받은 JSON 데이터 사용해서 tr 태그 만들고 화면 출력*/
            let htmlTag = "";
            let postList = data.list; /* JSON 객체의 속성명 "list"의 값을 postList에 저장!*/
            
            postList.forEach(post => {
                htmlTag += "<tr>";
                htmlTag += "<td>" + post.postIdx + "</td>";
                htmlTag += "<td>" + post.title + "</td>";
                htmlTag += "<td>" + post.nickname + "</td>";
                htmlTag += "<td>" + post.postDate + "</td>";
                htmlTag += "<td>" + post.hit + "</td>";
                htmlTag += "</tr>";
            });
            
            $("#postTable").html(htmlTag);
        })
        .catch(error => {
            console.error(">> Fetch 처리 실패 : " + error);
        });
    }
    
    function ajaxGetSportAll() {
        //alert("스포츠 실행된다~");
        
        fetch("getSportAll", {
            method: "GET",
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            console.log(data.list);
            
             /*전달받은 JSON 데이터 사용해서 tr 태그 만들고 화면 출력*/
            let htmlTag = "";
            let postList = data.list; /* JSON 객체의 속성명 "list"의 값을 postList에 저장!*/
            
            postList.forEach(post => {
                htmlTag += "<tr>";
                htmlTag += "<td>" + post.postIdx + "</td>";
                htmlTag += "<td>" + post.title + "</td>";
                htmlTag += "<td>" + post.nickname + "</td>";
                htmlTag += "<td>" + post.postDate + "</td>";
                htmlTag += "<td>" + post.hit + "</td>";
                htmlTag += "</tr>";
            });
            
            $("#postTable").html(htmlTag);
        })
        .catch(error => {
            console.error(">> Fetch 처리 실패 : " + error);
        });
    }
    
    function ajaxGetMovieAll() {
        //alert("영화 실행된다~");
        
        fetch("getMovieAll", {
            method: "GET",
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            console.log(data.list);
            
             /*전달받은 JSON 데이터 사용해서 tr 태그 만들고 화면 출력*/
            let htmlTag = "";
            let postList = data.list; /* JSON 객체의 속성명 "list"의 값을 postList에 저장!*/
            
            postList.forEach(post => {
                htmlTag += "<tr>";
                htmlTag += "<td>" + post.postIdx + "</td>";
                htmlTag += "<td>" + post.title + "</td>";
                htmlTag += "<td>" + post.nickname + "</td>";
                htmlTag += "<td>" + post.postDate + "</td>";
                htmlTag += "<td>" + post.hit + "</td>";
                htmlTag += "</tr>";
            });
            
            $("#postTable").html(htmlTag);
        })
        .catch(error => {
            console.error(">> Fetch 처리 실패 : " + error);
        });
    }

