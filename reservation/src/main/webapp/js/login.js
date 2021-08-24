document.addEventListener("DOMContentLoaded", initLoginPage);

function initLoginPage(){
	let loginArea = new LoginArea();
	
}

class LoginArea {
	constructor(){
		this.addEventListeners();
	}
	
	addEventListeners(){
		const loginForm = document.querySelector("#form1");
		loginForm.addEventListener("submit",this.submitForm);
	}
	
	static validateEmail() {
		const emailInput = document.querySelector("[name='resrv_email']");
		let email = emailInput.value;
		const validationRegExpr = (/^[\w\.]+@\w+\.\w+/).test(email);

		if (validationRegExpr === false) {
			alert("이메일 형식에 맞게 올바르게 입력해주세요")
		}

		return validationRegExpr;
	}
	
	
	submitForm(event){
		event.preventDefault();
		
		if(LoginArea.validateEmail()){
			const loginForm = document.querySelector("#form1");
			const emailInput = document.querySelector("[name='resrv_email']");
			let email = emailInput.value;
			
			loginForm.submit();
		}
		
	}
}



