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

	let titleArea = new TitleArea(displayInfoId, productData);

	let ticketBodyArea = new TicketBodyArea(displayInfoId, productData.priceInfos);

	let bookingForm = new BookingForm(productData);
}

class TitleArea {
	constructor(displayInfoId, productData) {
		this.displayInfoId = displayInfoId;
		this.productData = productData;

		this.makeBackUrl();
		this.makeTitle();
		this.makeTitleImage();
		this.makeDetails();
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

		let minimumPrice = this.productData.priceInfos
			.map(priceInfo => priceInfo.discountedProductPrice)
			.reduce((price1, price2) => {
				return Math.min(price1, price2);
			});

		preview.children[1].innerText = "₩" + minimumPrice + "원~";
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

		this.makeTicketBodyArea();
		this.addEventListeners();
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
		let clickedId = event.target.id;

		let wrapperArea = event.target.closest(".clearfix");

		let subtractButton = wrapperArea.children[0];
		let ticketCount = wrapperArea.children[1];

		if (clickedId === "add_button") {
			subtractButton.className = "btn_plus_minus spr_book2 ico_minus3";
			ticketCount.className = "count_control_input";
			ticketCount.value = parseInt(ticketCount.value) + 1;

		} else if (clickedId === "subtract_button") {
			ticketCount.value = parseInt(ticketCount.value) - 1;

			if (parseInt(ticketCount.value) <= 0) {
				ticketCount.value = "0";
				ticketCount.classList.add("disabled");
				subtractButton.classList.add("disabled");

			} else {
				ticketCount.classList.remove("disabled");
				subtractButton.classList.remove("disabled");
			}
		}
	}

	calculatePriceEvent() {
		let priceArea = event.currentTarget.nextElementSibling;

		let price = parseInt(event.currentTarget.closest(".qty").querySelector(".price").innerText);
		let count = parseInt(event.currentTarget.children[1].value);

		if (count <= 0) {
			priceArea.classList.remove("on_color");
		} else {
			priceArea.classList.add("on_color");
		}

		priceArea.children[0].innerText = price * count;
	}
}

class BookingForm {
	constructor(productData) {
		this.setRervationDate(productData.reservationDate);
		this.addEventListeners();
		this.setFormHiddenData(productData);
	}

	setRervationDate(reservationDate) {
		document.querySelector(".inline_txt").childNodes[0].textContent = reservationDate + ", 총 ";
	}

	setFormHiddenData(productData) {
		const form = document.querySelector(".form_horizontal");
		form.querySelector("#form_product_id").value = productData.productId;
		form.querySelector("#form_display_info_id").value = productData.displayInfoId;
		form.querySelector("#form_date").value = productData.reservationDate;

	}

	addEventListeners() {
		let termButtons = document.querySelectorAll(".btn_agreement");
		for (let button of Array.from(termButtons)) {
			button.addEventListener("click", this.viewTermsEvent);
		}

		let ticketButtons = document.querySelectorAll(".clearfix");

		for (let button of Array.from(ticketButtons)) {
			button.addEventListener("click", this.bookingMessageEvent);
			button.addEventListener("click", BookingForm.validateTicketCount);
		}

		const form = document.querySelector(".form_horizontal");

		form.addEventListener("change", BookingForm.validateForm);
		form.addEventListener("focus", BookingForm.validateForm);
		form.addEventListener("blur", BookingForm.validateForm);
		form.addEventListener("click", BookingForm.validateForm);
		form.addEventListener("keypress", BookingForm.validateForm);

		let agreeButton = document.querySelector(".label.chk_txt_label");
		agreeButton.addEventListener("click", BookingForm.bookingAgreeEvent);

		const reservationButton = document.querySelector(".bk_btn_wrap");
		reservationButton.addEventListener("click", BookingForm.submitReservationForm);
		reservationButton.addEventListener("mouseover", BookingForm.validateAgreeButton);
		reservationButton.addEventListener("mouseover", BookingForm.validateSubmitButton);
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

	static bookingAgreeEvent() {
		let isChecked = document.querySelector("#chk3").checked;

		if (isChecked === false) {
			const wrapper = document.querySelector('.agreement.all');
			const errorMsg = wrapper.querySelector(".invalid");
			errorMsg.style.display = "none";
		}

		isChecked != isChecked;
	}

	viewTermsEvent() {
		const termTag = event.target.closest(".agreement");
		const arrowSign = termTag.querySelector(".btn_agreement I");

		if (termTag.className.includes("open")) {
			termTag.classList.remove("open");
			arrowSign.className = "fn fn-down2";
		} else {
			termTag.classList.add("open");
			arrowSign.className = "fn fn-up2";
		}
	}

	static validateForm() {
		let isValid = true;

		isValid &= BookingForm.validateName();
		isValid &= BookingForm.validatePhoneNumber();
		isValid &= BookingForm.validateEmail();
		isValid &= BookingForm.validateTicketCount();

		let formSubmitButton = document.querySelector(".bk_btn_wrap");

		if (isValid) {
			formSubmitButton.classList.remove("disable");
		} else {
			formSubmitButton.classList.add("disable");
		}
		return isValid;
	}

	static validateName() {
		const inputTag = document.querySelector("[name='name']");
		const name = inputTag.value;

		const regExprKoreanName = /^[ㄱ-ㅎㅏ-ㅣ가-힣]/;
		const regExprEnglishName = /^[a-zA-Z]/;

		let isValid = (regExprKoreanName).test(name);
		isValid ||= (regExprEnglishName).test(name);

		const wrapper = inputTag.closest(".inline_form");
		const errorMsg = wrapper.querySelector(".invalid");

		if (isValid) {
			errorMsg.style.display = "none";
		} else {
			errorMsg.style.display = "block";
		}

		return isValid;
	}

	static validatePhoneNumber() {
		const inputTag = document.querySelector("[name='tel']");
		let phoneNumber = inputTag.value;

		let isValid = false;

		const telValidation = (/^\d{2,3}-\d{3,4}-\d{4}$/);
		const mobileValidation = (/^\d{3}-\d{3,4}-\d{4}$/);

		isValid ||= telValidation.test(phoneNumber);
		isValid ||= mobileValidation.test(phoneNumber);

		const wrapper = inputTag.closest(".inline_form");
		const errorMsg = wrapper.querySelector(".invalid");

		if (isValid) {
			errorMsg.style.display = "none";
		} else {
			errorMsg.style.display = "block";
		}

		return isValid;
	}

	static validateEmail() {
		const inputTag = document.querySelector("[name='email']");
		let email = inputTag.value;
		const isValid = (/^[\w\.]+@\w+\.\w+/).test(email);

		const wrapper = inputTag.closest(".inline_form");
		const errorMsg = wrapper.querySelector(".invalid");

		if (isValid) {
			errorMsg.style.display = "none";
		} else {
			errorMsg.style.display = "block";
		}

		return isValid;
	}

	static validateAgreeButton() {
		let isChecked = document.querySelector("#chk3").checked;
		const wrapper = document.querySelector('.agreement.all');
		const errorMsg = wrapper.querySelector(".invalid");

		if (isChecked) {
			errorMsg.style.display = "none";
		} else {
			errorMsg.style.display = "block";
		}

		return isChecked;
	}

	static validateTicketCount() {
		const ticketCount = parseInt(document.querySelector("#totalCount").innerText);
		const wrapper = document.querySelector("#totalCount").closest(".inline_control");
		const errorMsg = wrapper.querySelector(".invalid");

		if (ticketCount === 0) {
			errorMsg.style.display = "block";
			return false;
		} else {
			errorMsg.style.display = "none";
			return true;
		}
	}

	static submitReservationForm() {
		let form = document.querySelector(".form_horizontal");

		if (BookingForm.validateSubmitButton()) {
			BookingForm.setPriceInfos();
			form.submit();
		}
	}

	static setPriceInfos() {
		let form = document.querySelector(".form_horizontal");

		let ticketButtons = document.querySelectorAll(".clearfix");
		let priceInfos = [];

		for (let button of Array.from(ticketButtons)) {
			let info = {};
			info.productPriceId = button.querySelector(".count_control_product_price_id").value;
			info.count = button.querySelector(".count_control_input").value;

			if (info.count == 0) {
				continue;
			}

			priceInfos.push(info);
		}

		form.querySelector("#form_prices").value = JSON.stringify(priceInfos);
	}

	static validateSubmitButton() {
		const formSubmitButton = document.querySelector(".bk_btn_wrap");

		if (BookingForm.validateForm() && BookingForm.validateAgreeButton()) {
			formSubmitButton.classList.remove("disable");

			const errorMsg = document.querySelector("#by_wrong_request");

			if (errorMsg !== null) {
				errorMsg.style.display = "none";
			}

			return true;
		} else {
			formSubmitButton.classList.add("disable");
			return false;
		}
	}
}