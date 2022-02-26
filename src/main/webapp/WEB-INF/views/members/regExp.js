function joinCheck() {
	let uid = document.getElementById('mId');
	let name = document.getElementById('mName');
	let email = document.getElementById('mEmail');
	let pwd = document.getElementById('mPwd');
	let rePwd = document.getElementById('rePwd');
	let addr = document.getElementById('address_kakao');
	let detailAddr = document.getElementById('detailAddress');
	let tel = document.getElementById('mTel');
	let birth = document.getElementById('mBirth');

	if (uid.value == "") { // 해당 입력값이 없을 경우 같은말: if(!uid.value)
		alert("아이디를 입력하세요.");
		uid.focus(); // focus(): 커서가 깜빡이는 현상, blur(): 커서가 사라지는 현상
		return false; // return: 반환하다 return false: 아무것도 반환하지 말아라 아래 코드부터 아무것도
						// 진행하지 말것
	}
	;

	if (name.value == "") {
		alert("이름을 입력하세요.");
		name.focus();
		return false;
	}
	;

	if (email.value == "") {
		alert("이메일 주소를 입력하세요.");
		email.focus();
		return false;
	}

	if (pwd.value == "") {
		alert("비밀번호를 입력하세요.");
		pwd.focus();
		return false;
	}
	;

	// 비밀번호 영문자+숫자+특수조합(8~25자리 입력) 정규식
	var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;

	if (!pwdCheck.test(pwd.value)) {
		alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야 합니다.");
		pwd.focus();
		return false;
	}
	;

	if (rePwd.value !== pwd.value) {
		alert("비밀번호가 일치하지 않습니다..");
		rePwd.focus();
		return false;
	}
	;

	var reg = /^[0-9]+/g; // 숫자만 입력하는 정규식

	if (!reg.test(tel.value)) {
		alert("전화번호는 숫자만 입력할 수 있습니다.");
		tel.focus();
		return false;
	}

	/*if (!agree.checked) { // 체크박스 미체크시
		alert("약관 동의를 체크하세요.");
		agree.focus();
		return false;
	}
*/
	if (addr.value == "") {
		alert("주소를 입력하세요.");
		addr.focus();
		return false;
	}
	;
	if (addrDetail.value == "") {
		alert("상세주소를 입력하세요.");
		addrDetail.focus();
		return false;
	}
	;
	if (birth.value == "") {
		alert("생년월일을 입력하세요.");
		birth.focus();
		return false;
	}
	;
	// 입력 값 전송
	document.frm.submit(); // 유효성 검사의 포인트
}

/*// 아이디 중복체크 팝업창(현재 공백문서)
function id_check() {
	// window.open("팝업될 문서 경로", "팝업될 문서 이름", "옵션");
	window.open("", "", "width=600, height=200, left=200, top=100");
}*/
