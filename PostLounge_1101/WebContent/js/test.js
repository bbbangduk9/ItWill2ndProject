$(document).ready(function () {
    // 카테고리 버튼에 대한 클릭 이벤트 처리
    $("#btnAll, #btnFood, #btnHealth, #btnMusic, #btnSport, #btnMovie").on("click", function () {
        // 해당 버튼의 ID 값을 사용하여 데이터를 불러오고 화면에 표시
        var categoryId = $(this).attr("id");
        ajaxGetCategoryAll(categoryId);
    });
});

function ajaxGetCategoryAll(category) {
    // 각 버튼에 대한 카테고리를 정의
    var categoryMap = {
        btnAll: "getCateAll",
        btnFood: "getFoodAll",
        btnHealth: "getHealthAll",
        btnMusic: "getMusicAll",
        btnSport: "getSportAll",
        btnMovie: "getMovieAll"
    };

    var categoryEndpoint = categoryMap[category];

    // AJAX 요청 보내기
    fetch(categoryEndpoint, {
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

        // JSON 데이터를 화면에 출력
        displayData(data.list);
    })
    .catch(error => {
        console.error(">> Fetch 처리 실패 : " + error);
    });
}

function displayData(postList) {
    // JSON 데이터 사용해서 tr 태그 만들고 화면 출력
    let htmlTag = "";
    postList.forEach(post => {
        htmlTag += "<tr>";
        htmlTag += "<td>" + post.postIdx + "</td>";
        htmlTag += "<td>" + post.title + "</td>";
        htmlTag += "<td>" + post.nickname + "</td>";
        htmlTag += "<td>" + post.postDate + "</td>";
        htmlTag += "<td>" + post.hit + "</td>";
        htmlTag += "<td>" + post.likeNum + "</td>";
        htmlTag += "</tr>";
    });

    $("#postTable").html(htmlTag);
}
