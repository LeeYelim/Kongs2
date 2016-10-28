var loginPlace = $("#loginPlace");
var loginForm = $("#loginForm");
var signUpForm = $("#signUpForm");
var userModify = $("#userModify");
var userSecession = $("#userSecession");
var addTeam = $("#addTeam");
var teamTextarea = addTeam.find("textarea");
var showTeamUserProfile = $("#showTeamUserProfile");

var loginDuration = 500;
var visibleDuration = 350;

$(function() {

	// hover시 프로필 정보 보여주기
	$("#profileShow").mouseover(function() {
		$("#profile").css("display", "block");
	});
	$("#profile").mouseout(function() {
		$("#profile").css("display", "none");
	});
	$("#profile").mouseover(function() {
		$("#profile").css("display", "block");
	});

	$("#loginPlace input[type=button]").click(function() {
		var btnTmp = $(this).get(0).name;

		switch (btnTmp) {
		case "cancle":
			loginPlace.hide();
			userSecession.hide();
			closeMemberOverLap();
			userModify.hide();
			addTeam.hide();
			loginForm.css({
				"right" : "50%"
			});
			reset();
			break;

		case "sign":
			showSignUpView();
			break;

		case "login":
			showLoginView();
			break;
		}
	});

	$("#profile_btn a").click(function() {
		var aName = $(this).get(0).name;

		if (aName == "userModify") {
			// 회원 수정 버튼
			showUserModify();
		} else if (aName) {
			// 팀 추가 버튼
			showAddTeam();
		}
	});

	// 로그인 버튼 클릭
	$("#loginView").click(function() {
		loginForm.fadeIn(visibleDuration);
		signUpForm.fadeOut(0);
		$("#loginPlace").fadeIn(visibleDuration);
	});

	// 로그아웃 버튼 클릭
	$("#logoutView").click(function() {
		alert('로그아웃합니다.');
		location.href = "logout.jsp";
	});

	// 회원 탈퇴 문구 다시 묻기
	$("input[name=secession]").click(function() {
		userSecession.fadeIn(visibleDuration);
	});

	// 회원 탈퇴 이벤트
	$("#userSecession .btns>input[type=button]").click(function() {
		var btnName = $(this).get(0).name;

		if (btnName == "secessionYes") {
			// 현재 로그인된 유저 삭제 이벤트

		} else if (btnName == "secessionNo") {
			// 뒤로가기(개인정보 삭제 다이얼로그 없애기)
			userSecession.fadeOut(visibleDuration);
		}
	});

	// 팀 추가의 조회하기 버튼 이벤트
	$("#addTeam input[name=findMember]").click(function() {
		var memberName = addTeam.find("#teamMember").val();
		// 맴버이름 추가 메소드
		addMemberName(memberName);
	});

	// 팀원 등록시에 팀원이 중복되거나, 가입되지 않은 회원일때 뜨는 화면을 닫는 버튼 리스너
	$("#showTeamUserProfile .showUserFalse input[name=userFalse]").click(
			function() {
				closeMemberOverLap();
			});

	// 팀원 등록시에 등록하는 버튼 리스너
	$("#showTeamUserProfile .showUserTrue input[name=userTrue]").click(
			function() {
				doAddTeamMember(name);
			});

	// 팀원 등록시에 등록을 취소하는 버튼 리스너
	$("#showTeamUserProfile .showUserTrue input[name=userCancle]").click(
			function() {
				closeAddTeamMember();
			});

	$("#addTeamSubmit").submit(function() {
		// 시간남으면 생성되었다는 dialog만들기
		alert("생성되었습니다.");
	});
});

// 회원가입창 보여주기
function showSignUpView() {
	loginForm.animate({
		right : "60%"
	}, {
		duration : loginDuration,
		queue : false
	}).fadeOut(loginDuration);

	signUpForm.css({
		right : "40%"
	}).animate({
		right : "50%"
	}, {
		duration : loginDuration,
		queue : false
	}).fadeIn(loginDuration);
}

// 로그인창 보여주기
function showLoginView() {
	signUpForm.animate({
		right : "40%"
	}, {
		duration : loginDuration,
		queue : false
	}).fadeOut(loginDuration);

	loginForm.css({
		right : "60%"
	}).animate({
		right : "50%"
	}, {
		duration : loginDuration,
		queue : false
	}).fadeIn(loginDuration);
}

// 개인정보 수정 페이지 보여주기
function showUserModify() {
	resetModify();
	loginForm.hide();
	userModify.show();
	loginPlace.fadeIn(visibleDuration);
}

// 팀 추가 페이지 보여주기
function showAddTeam() {
	loginForm.hide();
	addTeam.show();
	loginPlace.fadeIn(visibleDuration);
}

// 팀 텍스트에리어에 맴버추가
function addMemberName(name) {
	tmp = teamTextarea.val();
	arr = tmp.trim().split("\n");
	nick = name;
	type = false;

	$.each(arr, function(index, item) {
		if (item == nick) {
			type = false;
			return;
		} else {
			type = true;
		}
	});
	if (type == true) {
		// 팀원으로 등록이 가능하다는 dialog를 띄운다.
		// db에 접속을 해서 먼저 등록이 가능한 id인지 확인한다.
		showAddTeamMember(teamTextarea, nick);
	} else {
		// 중복입력이라는 dialog를 띄운다.
		showMemberOverLap();
	}
}

// 팀원으로 등록이 가능하다는 창 띄우는 함수
function showAddTeamMember(teamTextarea, name) {
	var showUser = showTeamUserProfile.find(".showUserTrue");

	showUser.show();
	showTeamUserProfile.fadeIn(visibleDuration);

	showUser.find("p").text(name + "님을 등록하시겠습니까?");
}

// 팀원으로 등록하는 함수
function doAddTeamMember() {
	teamTextarea.val(tmp += (nick + "\n"));
	closeAddTeamMember();
}

// 팀원으로 등록이 가능하다는 창 닫는 함수
function closeAddTeamMember() {
	var showUser = showTeamUserProfile.find(".showUserTrue");

	showUser.hide();
	showTeamUserProfile.fadeOut(visibleDuration);

	showUser.find("p").text("");
}

// 중복입력 창 띄우는 함수
function showMemberOverLap() {
	showTeamUserProfile.find(".showUserFalse").show();
	showTeamUserProfile.fadeIn(visibleDuration);
}

// 중복입력 창 닫는 함수
function closeMemberOverLap() {
	showTeamUserProfile.find(".showUserFalse").hide();
	showTeamUserProfile.fadeOut(visibleDuration);
}

// login dialog를 닫았을때 내용 초기화
function reset() {
	signUpForm.find("input[type=text]").val('');
	signUpForm.find("input[type=password]").val('');

	loginForm.find("input[type=text]").val('');
	loginForm.find("input[type=password]").val('');
}

// 유저 수정 부분 초기화
function resetModify() {
	userModify.find("input[name=name]").val('');
	userModify.find("input[name=pass]").val('');
	userModify.find("input[name=pass_re]").val('');
}