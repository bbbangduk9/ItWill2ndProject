$(document).ready(function(){
	ajaxPostAll();
    $("#btnPostAll").on("click", ajaxPostAll);
    $("#btnCommAll").on("click", ajaxCommAll);
    $("#btnMemAll").on("click", ajaxMemAll);
});

function ajaxPostAll(pageNumber, pageSize) {
    fetch(`postAll?page=${pageNumber}&pageSize=${pageSize}`, {
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
