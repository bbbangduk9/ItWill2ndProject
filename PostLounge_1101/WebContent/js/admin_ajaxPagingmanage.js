// adminmain.js

$(document).ready(function(){
	ajaxPostAll();
    $("#btnPostAll").on("click", ajaxPostAll);
    $("#btnCommAll").on("click", ajaxCommAll);
    $("#btnMemAll").on("click", ajaxMemAll);
});


$(document).on("click", ":checkbox", function() {
    if ($(this).is(":checked")) {
        // 체크박스가 선택된 경우
        const idx = $(this).closest("tr").find("td:eq(1)").text(); 
        // 해당 행의 두 번째 열 (글번호)에서 postIdx 추출
        console.log("선택된 idx: " + idx);
    }
});


// 이 함수는 날짜를 받아서 yy/MM/dd 형식의 문자열로 반환합니다.
	function formatDate(date) {
	  	const year = date.getFullYear().toString().slice(2); // 연도에서 뒤의 2자리를 가져옵니다.
	  	const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월을 2자리로 만듭니다.
	  	const day = date.getDate().toString().padStart(2, '0'); // 일을 2자리로 만듭니다.
		return year + '/' + month + '/' + day;
	}

    function ajaxPostAll() {
        fetch("postAll", {
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
            
            let htmlTag = "<thead><tr>";
            htmlTag += "<th colspan='2'>전체선택</th>"
            htmlTag += "<th>제목</th>";
            htmlTag += "<th>파일</th>";
            htmlTag += "<th>좋아요</th>";
            htmlTag += "<th>조회수</th>";
            htmlTag += "<th>작성일</th>";
            htmlTag += "</tr></thead><tbody>";

            let postList = data.list;
            postList.forEach(post => {
                htmlTag += "<tr>";
                htmlTag += "<td><input type='checkbox'></td>"
                htmlTag += "<td>" + post.postIdx + "</td>";
                htmlTag += "<td>" + post.title + "</td>";
               	htmlTag += "<td><button>" + post.oriName + "</button></td>";
                htmlTag += "<td>" + post.likeNum + "</td>";
                htmlTag += "<td>" + post.hit + "</td>";
                htmlTag += "<td>" + formatDate(new Date(post.postDate)) + "</td>";
                htmlTag += "</tr>";
            });
            htmlTag += "</tbody>";

            $("#boxTable").html(htmlTag);
        })
        .catch(error => {
            console.error(">> Fetch 처리 실패 : " + error);
        });
    }



    function ajaxCommAll() {
        
        fetch("commAll", {
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
            let htmlTag = "<thead><tr>";
            htmlTag += "<th colspan='2'>전체선택</th>";
            htmlTag += "<th>내용</th>";
            htmlTag += "<th>작성일</th>";
            htmlTag += "</tr></thead><tbody>";
            let commList = data.list; /* JSON 객체의 속성명 "list"의 값을 postList에 저장!*/
            
            commList.forEach(comm => {
                htmlTag += "<tr>";
                htmlTag += "<td><input type='checkbox'></td>"
                htmlTag += "<td>" + comm.commentIdx + "</td>";
                htmlTag += "<td>" + comm.commContent + "</td>";
                htmlTag += "<td>" + formatDate(new Date(comm.commDate)) + "</td>";
                htmlTag += "</tr>";
            });
            htmlTag += "</tbody>";
            
            $("#boxTable").html(htmlTag);
        })
        .catch(error => {
            console.error(">> Fetch 처리 실패 : " + error);
        });
        
    }
    
    function ajaxMemAll() {
        
        fetch("memAll", {
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
            
            let htmlTag = "<thead><tr>";
            htmlTag += "<th colspan='2'>전체선택</th>";
            htmlTag += "<th>아이디</th>";
            htmlTag += "<th>닉네임</th>";
            htmlTag += "<th>파일</th>";
            htmlTag += "<th>가입일</th>";
            htmlTag += "</tr></thead><tbody>";
            
             /*전달받은 JSON 데이터 사용해서 tr 태그 만들고 화면 출력*/
            let memList = data.list; /* JSON 객체의 속성명 "list"의 값을 postList에 저장!*/
            
            memList.forEach(mem => {
                htmlTag += "<tr>";
                htmlTag += "<td><input type='checkbox'></td>"
                htmlTag += "<td>" + mem.memberIdx+ "</td>";
                htmlTag += "<td>" + mem.id + "</td>";
                htmlTag += "<td>" + mem.nickname + "</td>";
                htmlTag += "<td><button>" + mem.oriName + "</button></td>";
                htmlTag += "<td>" + formatDate(new Date(mem.joinDate)) + "</td>";
                htmlTag += "</tr>";
            });
            htmlTag += "</tbody>";
            
            $("#boxTable").html(htmlTag);
        })
        .catch(error => {
            console.error(">> Fetch 처리 실패 : " + error);
        });
    }

