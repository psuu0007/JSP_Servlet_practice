/**
 * 
 */

function memberList() {

	location.href = './list';

}

function memberAdd() {

	location.href = './add';

}

function memberView(no) {

	location.href = './update?memberNo=' + no;

}

function addCheck() {

	let memberName = document.getElementById('memberName');
	let email = document.getElementById('email');
	let password = document.getElementById('password');
	let memberNameValue = memberName.value;
	let emailValue = email.value;
	let passwordValue = password.value;

	if (memberNameValue.length < 1) {
		alert('이름을 입력 하세요!');
		memberName.focus();
		return false;
	}
	else if (emailValue.length < 1) {
		alert('이메일을 입력 하세요!');
		email.focus();
		return false;
	}
	else if (passwordValue.length < 1) {
		alert('비밀번호를 입력 하세요!');
		password.focus();
		return false;
	}
	else {
		return;
	}

}

function updateCheck() {

	let memberName = document.getElementById('memberName');
	let email = document.getElementById('email');
	let memberNameValue = memberName.value;
	let emailValue = email.value;

	if (memberNameValue.length < 1) {
		alert('이름을 입력 하세요!');
		memberName.focus();
		return false;
	}
	else if (emailValue.length < 1) {
		alert('이메일을 입력 하세요!');
		email.focus();
		return false;
	}
	else {
		return;
	}

}

function passCheck() {

	let passn = document.getElementById('passN');
	let pass1 = document.getElementById('pass1');
	let pass2 = document.getElementById('pass2');
	let passnValue = passn.value;
	let pass1Value = pass1.value;
	let pass2Value = pass2.value;

	if (passnValue != pass1Value) {
		alert('이전 비밀번호를 확인 하세요!');
		pass1.focus();
		return false;
	}
	else {

		if (pass2Value.length > 0) {
			passn.value = pass2Value;
		}

		return;
	}

}

function memberDelete(sendNo, sendName) {

	if (confirm('선택하신 회원 정보를 삭제 하시겠습니까?')) {

		let form = document.getElementById('memberForm');
		let memberNo = document.getElementById('memberNo');
		let memberName = document.getElementById('memberName');

		memberNo.value = sendNo;
		memberName.value = sendName;

		form.submit();
	}
	else {
		alert('삭제가 취소 되었습니다!');
		return;
	}
}

function loginCheck() {

	let loginEmail = document.getElementById('loginEmail');
	let loginPass = document.getElementById('loginPass');
	let loginEmailValue = loginEmail.value;
	let loginPassValue = loginPass.value;

	if (loginEmailValue.length < 1) {
		alert('이메일을 입력 하세요!');
		loginEmail.focus();
		return false;
	}
	else if (loginPassValue.length < 1) {
		alert('비밀번호를 입력 하세요!');
		loginPass.focus();
		return false;
	}
	else {
		return;
	}

}

function boardList() {

	location.href = './list';

}

function boardAdd() {

	location.href = './add';

}

function boardView(id) {

	location.href = './detail?freeBoardId=' + id;

}
