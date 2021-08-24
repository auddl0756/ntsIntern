document.addEventListener("DOMContentLoaded", initLoginPage);

function initLoginPage() {
	const loginArea = new LoginArea();
}

class LoginArea {
	constructor() {
		this.loginForm = document.querySelector("#form1");
		this.addEventListeners();
	}

	addEventListeners() {
		this.loginForm.addEventListener("submit", this.submitForm);
	}

	static validateEmail() {
		const emailInput = document.querySelector("[name='resrv_email']");
		const email = emailInput.value;

		const validateResult = (/^[\w\.]+@\w+\.\w+/).test(email);

		if (validateResult === false) {
			alert("이메일 형식에 맞게 올바르게 입력해주세요")
		}

		return validateResult;
	}

	submitForm(event) {
		event.preventDefault();

		if (LoginArea.validateEmail()) {
			this.loginForm.submit();
		}
	}
}



