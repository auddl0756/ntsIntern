document.addEventListener("DOMContentLoaded", initLoginPage);

function initLoginPage() {
	const loginArea = new LoginArea();
}

class LoginArea {
	constructor() {
		this.addEventListeners();
	}

	addEventListeners() {
		const loginForm = document.querySelector("#form1");
		loginForm.addEventListener("submit", this.submitForm);
	}
	
	static validateEmail() {
		const inputTag = document.querySelector("[name='resrv_email']");
		let email = inputTag.value;
		const isValid = (/^[\w\.]+@\w+\.\w+/).test(email);

		const wrapper = inputTag.closest(".login_form");
		const errorMsg = wrapper.querySelector(".invalid");

		if (isValid)  {
			errorMsg.style.display = "none";
		} else {
			errorMsg.style.display = "block";
		}

		return isValid;
	}

	submitForm(event) {
		const loginForm = document.querySelector("#form1");
		event.preventDefault();

		if (LoginArea.validateEmail()) {
			loginForm.submit();
		}
	}
}



