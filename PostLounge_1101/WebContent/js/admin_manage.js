// adminmain.js

$(document).ready(function(){
	ajaxPostAll();
    $("#btnPostAll").on("click", ajaxPostAll);
    $("#btnCommAll").on("click", ajaxCommAll);
    $("#btnMemAll").on("click", ajaxMemAll);
});
// 페이지 로드 시 postIdx 값을 데이터 속성으로 저장
$(document).ready(function() {
    $("td:eq(1)").each(function() {
        const idx = $(this).text();
        $(this).data("original-idx", idx);
    });
});
/*
$(document).on("click", ":checkbox", function() {
    if ($(this).is(":checked")) {
        // 체크박스가 선택된 경우
        const idx = $(this).closest("tr").find("td:eq(1)").text(); 
        // 해당 행의 두 번째 열 (글번호)에서 postIdx 추출
        console.log("선택된 idx: " + idx);
    }
});
*/

$(document).on("click", ":checkbox", function() {
    if ($(this).is(":checked")) {
        // 체크박스가 선택된 경우
        const idx = $(this).closest("tr").find("td:eq(1)").text();
        // 해당 행의 두 번째 열 (글번호)에서 postIdx 추출
        console.log("선택된 idx: " + idx);

        // 선택된 행의 두 번째 열을 삭제 버튼으로 변경
        $(this).closest("tr").find("td:eq(1)").html('<input type="button" value="삭제" class="deleteButton">');
    } else {
        // 체크박스가 선택 해제된 경우
        const idx = $(this).closest("tr").find("td:eq(1)").data("original-idx");
        console.log("해제된 idx: " + idx);
    
        // 선택 해제된 열에 원하는 데이터를 넣습니다.
        $(this).closest("tr").find("td:eq(1)").text(idx);
    
        // 삭제 버튼이 있을 경우 삭제 버튼을 삭제
        $(this).closest("tr").find(".deleteButton").remove();
    }
});

// 삭제 버튼을 클릭하는 경우의 이벤트 핸들러
$(document).on("click", ".deleteButton", function() {
    const idx = $(this).data("idx");
    // 여기에 삭제 동작을 수행하는 코드를 추가하세요.
    console.log("삭제 버튼 클릭 - idx: " + idx);
    // 예를 들어, 삭제를 위한 AJAX 요청을 보낼 수 있습니다.
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
            htmlTag += "<th colspan='2'><button id='chkAll'>전체선택</button></th>"
            htmlTag += "<th>제목</th>";
            htmlTag += "<th>파일</th>";
            htmlTag += "<th>좋아요</th>";
            htmlTag += "<th>조회수</th>";
            htmlTag += "<th>작성일</th>";
            htmlTag += "</tr></thead><tbody>";

            let postList = data.list;
            postList.forEach(post => {
                htmlTag += "<tr>";
                htmlTag += "<td><input type='checkbox' name='idx' id='chk'" + post.postIdx + "></td>"
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
            htmlTag += "<th colspan='2'><button id='chkAll'>전체선택</button></th>";
            htmlTag += "<th>내용</th>";
            htmlTag += "<th>작성일</th>";
            htmlTag += "</tr></thead><tbody>";
            let commList = data.list; /* JSON 객체의 속성명 "list"의 값을 postList에 저장!*/
            
            commList.forEach(comm => {
                htmlTag += "<tr>";
                htmlTag += "<td><input type='checkbox' name='idx' value=" + comm.commentIdx + "></td>"
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
            htmlTag += "<th colspan='2'><button id='chkAll'>전체선택</button></th>";
            htmlTag += "<th>아이디</th>";
            htmlTag += "<th>닉네임</th>";
            htmlTag += "<th>파일</th>";
            htmlTag += "<th>가입일</th>";
            htmlTag += "</tr></thead><tbody>";
            
             /*전달받은 JSON 데이터 사용해서 tr 태그 만들고 화면 출력*/
            let memList = data.list; /* JSON 객체의 속성명 "list"의 값을 postList에 저장!*/
            
            memList.forEach(mem => {
                htmlTag += "<tr>";
                htmlTag += "<td><input type='checkbox' name='idx' value=" + mem.memberIdx + "></td>"
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

