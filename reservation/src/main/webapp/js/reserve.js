document.addEventListener("DOMContentLoaded", initReservationPage);

function getDisplayInfoId() {
	let tokens = location.href.split("/");
	return tokens[tokens.length - 1].replace("#", "");
}

function getProductData(displayInfoId, productData) {
	let XHR = new XMLHttpRequest();
	return new Promise(function(resolve, reject) {
		XHR.addEventListener("load", function() {
			if (XHR.status == 200) {
				let product = JSON.parse(XHR.responseText);

				for (let key in product) {
					productData[key] = product[key];
				}

				resolve(productData);

			} else {
				alert("sorry. something failed");
				reject(new Error("sorry. something failed"));
			}
		});

		let url = "/api/reservation/product/" + displayInfoId;

		XHR.open("GET", url);
		XHR.send();
	});
}

async function initReservationPage() {
	const displayInfoId = getDisplayInfoId();

	let productData = await getProductData(displayInfoId, {});
	console.log(productData);

	let titleArea = new TitleArea(displayInfoId, productData);

	titleArea.makeBackUrl();
	titleArea.makeTitle();
	titleArea.makeTitleImage();
	titleArea.makeDetails();

	let ticketBodyArea = new TicketBodyArea(displayInfoId, productData.priceInfos);
	ticketBodyArea.makeTicketBodyArea();
	ticketBodyArea.addEventListeners();

	let bookingForm = new BookingForm(productData.reservationDate);
	bookingForm.addEventListeners();

}

class TitleArea {
	constructor(displayInfoId, productData) {
		this.displayInfoId = displayInfoId;
		this.productData = productData;
	}

	makeBackUrl() {
		let backButton = document.querySelector(".btn_back");
		backButton.setAttribute("href", backButton.getAttribute("href") + "/" + this.displayInfoId);
	}

	makeTitle() {
		let title = document.querySelector(".title");
		title.innerText = this.productData.productDescription;
	}

	makeTitleImage() {
		let thumbNail = document.querySelector(".img_thumb");
		let saveFileName = this.productData.saveFileName;

		thumbNail.src = "/" + saveFileName;
		thumbNail.style.width = "414px";
		thumbNail.style.height = "414px";

		let preview = document.querySelector(".preview_txt");
		preview.children[1].innerText = "₩" + this.productData.minimumPrice + "원~";
		preview.children[2].innerText = this.productData.openingHours.split("\n")[0];
	}

	makeDetails() {
		this.productData.openingHours = this.productData.openingHours.split('\n');

		let template = document.querySelector("#detailTemplate").innerText;
		let bindTemplate = Handlebars.compile(template);
		let resultHTML = bindTemplate(this.productData);

		let targetHTML = document.querySelector(".section_store_details");
		targetHTML.innerHTML = resultHTML;
	}
}


class TicketBodyArea {
	constructor(displayInfoId, priceInfos) {
		this.displayInfoId = displayInfoId;
		this.priceInfos = priceInfos;
	}

	makeTicketBodyArea() {
		let template = document.querySelector("#ticketBodyTemplate").innerText;
		let bindTemplate = Handlebars.compile(template);
		let resultHTML = this.priceInfos.map((info) => {
			return bindTemplate(info);
		});

		resultHTML = resultHTML.join(" ");

		let targetHTML = document.querySelector(".ticket_body");
		targetHTML.innerHTML = resultHTML;
	}

	addEventListeners() {
		let ticketButtons = document.querySelectorAll(".clearfix");

		for (let button of Array.from(ticketButtons)) {
			button.addEventListener("click", this.ticketAddSubEvent);
			button.addEventListener("click", this.calculatePriceEvent);
		}
	}

	ticketAddSubEvent() {
		let clickedTitle = event.target.title;

		let wrapperArea = event.target.closest(".clearfix");

		let subtractButton = wrapperArea.children[0];
		let ticketCount = wrapperArea.children[1];
		let addButton = wrapperArea.children[2];

		if (clickedTitle === "더하기") {
			subtractButton.className = "btn_plus_minus spr_book2 ico_minus3";
			ticketCount.className = "count_control_input";
			ticketCount.value = parseInt(ticketCount.value) + 1;

		} else if (clickedTitle === "빼기") {
			ticketCount.value = parseInt(ticketCount.value) - 1;

			if (parseInt(ticketCount.value) <= 0) {
				ticketCount.value = "0";
				ticketCount.className = "count_control_input disabled";
				subtractButton.className = "btn_plus_minus spr_book2 ico_minus3 disabled";
			} else {
				subtractButton.className = "btn_plus_minus spr_book2 ico_minus3";
				ticketCount.className = "count_control_input";
			}

		} else if (clickedTitle === "수량") {
			return;
		}
	}

	calculatePriceEvent() {
		let priceArea = event.currentTarget.nextElementSibling;

		let price = parseInt(event.currentTarget.closest(".qty").querySelector(".price").innerText);
		let count = parseInt(event.currentTarget.children[1].value);

		if (count <= 0) {
			priceArea.className = "individual_price";
		} else {
			priceArea.className = "individual_price on_color";
		}

		priceArea.children[0].innerText = price * count;
	}
}

class BookingForm {
	constructor(reservationDate) {
		this.setRervationDate(reservationDate);
	}

	setRervationDate(reservationDate) {
		document.querySelector(".inline_txt").childNodes[0].textContent = reservationDate + ", 총 ";
	}

	addEventListeners() {
		let termButtons = document.querySelectorAll(".btn_agreement");
		for (let button of Array.from(termButtons)) {
			button.addEventListener("click", this.viewTermsEvent);
		}

		let agreeButton = document.querySelector("#chk3");
		agreeButton.addEventListener("click", this.bookingAgreeEvent);

		let ticketButtons = document.querySelectorAll(".clearfix");

		for (let button of Array.from(ticketButtons)) {
			button.addEventListener("click", this.bookingMessageEvent);
		}

		const reservationButton = document.querySelector(".bk_btn_wrap");
		reservationButton.addEventListener("click", this.validateForm);
	}

	bookingMessageEvent() {
		const ticketArea = document.querySelector(".ticket_body");

		const valueNodeList = Array.from(ticketArea.querySelectorAll(".clearfix .count_control_input"));

		let totalTicketCount = 0;

		for (let ticketCount of valueNodeList) {
			totalTicketCount += parseInt(ticketCount.value);
		}

		document.querySelector("#totalCount").innerText = totalTicketCount;
	}

	viewTermsEvent() {
		const termTag = event.target.closest(".agreement");
		const arrowSign = termTag.querySelector(".btn_agreement I");

		if (termTag.className.includes("open")) {
			termTag.className = termTag.className.replace("open", "");
			arrowSign.className = "fn fn-down2";
		} else {
			termTag.className += " open";
			arrowSign.className = "fn fn-up2";
		}
	}

	bookingAgreeEvent() {
		let isChecked = document.querySelector("#chk3").checked;
		let formSubmitButton = document.querySelector(".bk_btn_wrap");

		if (isChecked === true) {
			formSubmitButton.className = "bk_btn_wrap";
		} else if (isChecked === false) {
			formSubmitButton.className = "bk_btn_wrap disable";
		}
	}

	validateForm() {
		if (BookingForm.validatePhoneNumber() && BookingForm.validateEmail()) {

			/* not yet developed
			
			this.submitReservationForm();
			*/
		}
	}

	static validatePhoneNumber() {
		const phoneNumberInput = document.querySelector("[name='tel']");
		let phoneNumber = phoneNumberInput.value;

		let isValid = false;

		let telValidation = [(/^\d{2,3}-\d{3,4}-\d{4}$/), (/^\d{2,3}\d{3,4}\d{4}$/)];
		let mobileValidation = [(/^\d{3}-\d{3,4}-\d{4}$/), (/^\d{3}\d{3,4}\d{4}$/)];

		for (let regExpr of telValidation) {
			isValid ||= regExpr.test(phoneNumber);
		}

		for (let regExpr of mobileValidation) {
			isValid ||= regExpr.test(phoneNumber);
		}

		if (isValid === false) {
			alert("연락처 형식에 맞게 올바르게 입력해주세요");
		}

		return isValid;
	}

	static validateEmail() {
		const emailInput = document.querySelector("[name='email']");
		let email = emailInput.value;
		let validationRegExpr = (/^[\w\.]+@\w+\.\w+/).test(email);

		if (validationRegExpr === false) {
			alert("이메일 형식에 맞게 올바르게 입력해주세요")
		}
		return validationRegExpr;
	}

	submitReservationForm() {
		let form = document.querySelector(".form_horizontal");

		form.submit();
	}
}