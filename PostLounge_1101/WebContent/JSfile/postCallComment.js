$(document).ready(function() {

	//페이지 로드시 불러오기 실행
	getComments();


	//댓글작성		
	$('#writeForm').submit(function(e) {
		e.preventDefault();
		insertCommentFunction();
	});

	/*//댓글삭제 (키면 두번 실행됨)
	$(document).on('click', '.deleteButton', function() {
		let commentIdx = $(this).data('commentIdx');
		deleteCommentFunction(commentIdx);
	});*/
});

//댓글 입력하기
function insertCommentFunction() {

	let content = $("#commContent").val();
	let postIdx = $("#postIdx").val(); //hidden으로 넘겨받은 값

	$.ajax({
		url: 'JsonInsertCommentController', //Json댓글입력 컨트롤러의 어노테이션 주소
		method: 'post',
		data: {
			"content": content,
			"postIdx": postIdx
		},
		dataType: 'json',
		success: function(data) {
			if (data.result == "success") {
				alert("댓글이 정상적으로 작성됐버렸네");
				getComments();

				$('#commContent').val('');
				checkLength(document.getElementById('commContent'), 300);
				$('#charCount').text('0/300');


			} else {
				alert("댓글 작성에 실패하였습니다.");
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(">> Ajax 처리 실패 : \n"
				+ "jqHR.readyState : " + jqXHR.readyState + "\n"
				+ "textStatus : " + textStatus + "\n"
				+ "errorThrown : " + errorThrown);
		}
	});

}


//댓글 리스트 	
function getComments() {
	let postIdx = $("#postIdx").val(); //요놈이 문제 : 로그인 안했을 때 null값이 됨 
	//alert(">> getCommentList 실행~~ postIdx : " + postIdx);

	$.ajax({
		url: "jsonCommentList",  //Json댓글조회 컨트롤러의 어노테이션.
		type: "post",
		data: { "postIdx": postIdx }, //"type" : "JsonSelectComment", 
		dataType: "json",
		success: function(data) {
			console.log(data);
			//전달받은 JSON 데이터 사용 tr 태그 만들고 화면출력
			let htmlTag = "";

			if (data.length === 0) {
				htmlTag = "<h3>댓글이 없습니다.</h3>";
			} else {

				$.each(data, function(index, comment) { //data라는 객체의 
					htmlTag += "<h2>" + comment.nickname + "</h2>";
					htmlTag += "<h5>" + comment.commDate + "</h5>";
					htmlTag += "<p>" + comment.commContent + "</p>";

					//수정&삭제 버튼 생성(로그인된 idx와 댓글 작성자의 idx 비교) 
					if (comment.loginUserMemberIdx === comment.memberIdx) {
						htmlTag += "<table>";
						htmlTag += "<tr>";
						htmlTag += "<td>";
						htmlTag += "<button class='editButton'>수정</button>";
						htmlTag += "<div class='editArea' style='display: none;'>";
						htmlTag += "<textarea class='editContent'></textarea>";
						htmlTag += "<button class='saveButton' onclick='updateCommentFunction(" + comment.commentIdx + ")'>수정 완료</button>";
						htmlTag += "</div>";
						htmlTag += "</td>";
						htmlTag += "<td>";
						htmlTag += "<button class='deleteButton' onclick='deleteCommentFunction(" + comment.commentIdx + ")'>삭제</button>";
						htmlTag += "</td>";
						htmlTag += "</tr>";
						htmlTag += "</table>";
					}
					htmlTag += "<div class='horizontal-hr'>";
					htmlTag += "<hr>";
					htmlTag += "<hr>";
					htmlTag += "</div>";
					htmlTag += "<br>";
				});
			}
			$("#commentHere").html(htmlTag);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(">> Ajax 처리 실패 : \n"
				+ "jqXHR.readyState : " + jqXHR.readyState + "\n"
				+ "textStatus : " + textStatus + "\n"
				+ "errorThrown : " + errorThrown);
		}
	});
}


// 수정 버튼 클릭 시
$(document).on('click', '.editButton', function() {
	let editArea = $(this).closest('tr').find('.editArea');
	let editContent = editArea.find('.editContent');
	editArea.show();

	// 현재 댓글 내용을 편집 입력란에 설정
	let originalContent = $(this).closest('tr').find('.commentContent').text();
	editContent.val(originalContent);

	// 편집 영역 표시
	editArea.show();

	// 등록 버튼 보이기
	let saveButton = editArea.find('.saveButton');
	saveButton.show();
});





//댓글 수정

$(document).on('click', '.editButton', function() {
	let editArea = $(this).closest('tr').find('.editArea');
	let editContent = editArea.find('.editContent');
	editArea.show();

	// 현재 댓글 내용을 편집 입력란에 설정
	let originalContent = $(this).closest('tr').find('.commentContent').text();
	editContent.val(originalContent);

	// 편집 영역 표시
	editArea.show();

	// 등록 버튼 보이기
	let saveButton = editArea.find('.saveButton');
	saveButton.show();
});



// 저장 버튼 클릭 시
function updateCommentFunction(commentIdx) {
	let editContent = $('.editContent').val();

	$.ajax({
		url: 'JsonUpdateCommentController',
		type: 'post',
		data: {
			"commentIdx": commentIdx,
			"content": editContent
		},
		dataType: 'json',
		success: function(data) {
			if (data.result == "success") {
				alert('댓글이 성공적으로 수정되었습니다.');
				getComments();
			} else {
				alert('댓글 수정에 실패하였습니다.');
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('Ajax 처리 실패: ' + textStatus);
		}
	});
}



//댓글 삭제하기(댓글Idx)
function deleteCommentFunction(commentIdx) {
    $.ajax({
        url: 'JsonDeleteCommentController',
        type: 'post',
        data: {
            "commentIdx": commentIdx
        },
        dataType: 'text',
        success: function(data) {
            if (data === "댓글이 잘 삭제됨") {
                alert("댓글이 정상적으로 삭제되었습니다.");
                getComments();
            } else {
                alert("댓글이 삭제되지 못했습니다.");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(">> Ajax 처리 실패 : \n" +
                "jqXHR.readyState : " + jqXHR.readyState + "\n" +
                "textStatus : " + textStatus + "\n" +
                "errorThrown : " + errorThrown);
        }
    });
}


//댓글작성 시 로그인 필요
function confirm_login() {
	var confirmation = confirm("라운지 로그인 하신 후 이용해 주시기 바랍니다.");
	if (confirmation) {
		location.href = "login.jsp"
	}
	//취소 아무런 작업 없음
}

//작성 클릭시 댓글작성란으로
function insertComment_go() {
	window.location.href = "insertComment.jsp";
}

//댓글란 글자 수 제한
function checkLength(textArea, maxLength) {
	const currentLength = textArea.value.length;
	const remaining = maxLength - currentLength;
	const charCountElement = document.getElementById('charCount');

	const maxLimitMessage = "최대 " + maxLength + "자까지 입력 가능합니다.";

	if (currentLength > maxLength) {
		const cutText = textArea.value.substring(0, maxLength);
		textArea.value = cutText; //Max까지의 값을 영역에 초기화
		charCountElement.textContent = '현재 입력한 글자수' + maxLength + '/' + '전체 입력 가능한 글자수' + maxLength;
		alert(maxLimitMessage);
	} else {
		charCountElement.textContent = '현재 입력한 글자수' + currentLength + '/' + '전체 입력 가능한 글자수' + maxLength;
	}
}


/*	 
	//유해단어 클린 패치
	 function filterText(text){
		 
		 const filters = [
			 {word : '개새끼', replacement: '귀여운 강아지'},
			 {word : '병신', replacement: '(저는 걸레를 물었어요)'},
		 ];
		 
		 filters.forEach(filter => {
			 const regex = new RegExp(filter.word, 'g');
			 text = text.replace(regex, filter.replacement);
		 });
		 
		 return text;
	 }
	 const textarea = document.getElementById('commContent');
	 const orinText = textarea.value();
	 const filterdText = filterText(orinText);
	 console.log(filterdText);
*/

$('#reportComment').on('click', function() {
	rptComment('reportInfo');
});

function rptComment(url) {
	$.ajax({
		url: url,
		type: 'post',
		data: { "commentIdx": commentIdx },
		success: function(data) {
			// 서버에서 받은 텍스트 응답을 처리
			var responseArray = data.split('|'); // 파이프(|)로 분리된 응답을 배열로 변환

			if (responseArray.length >= 4) {
				var commentContent = responseArray[0];
				var nickname = responseArray[1];
				var commDate = responseArray[2];
				var name = responseArray[3];

				// 'reportComment.jsp'로 데이터를 전달하여 표시
				window.open('reportComment.jsp?commentContent=' + commentContent + '&nickname=' + nickname + '&commDate=' + commDate + '&name=' + name, '신고하기', 'width=500,height=500');
			} else {
				console.log("올바른 응답을 받지 못했습니다.");
			}
		}
	});
}



// JavaScript를 사용하여 textarea 크기 자동 조절
function autoResizeTextarea() {
    const textarea = document.querySelector(".auto-resize-textarea");
    textarea.style.height = "auto"; // 초기 높이로 재설정
    textarea.style.height = textarea.scrollHeight + "px"; // 스크롤 높이로 조절
}

// textarea 내용이 변경될 때 크기를 자동 조절
document.querySelector(".auto-resize-textarea").addEventListener("input", autoResizeTextarea);

