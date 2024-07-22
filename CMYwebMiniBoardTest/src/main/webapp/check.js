/**
 * 
 */

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