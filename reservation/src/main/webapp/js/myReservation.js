document.addEventListener("DOMContentLoaded", initMyReservationPage);

function initMyReservationPage() {
	let cancel = new Cancel();
}

class Cancel {
	constructor() {
		this.addEventListeners();
		this.popupArea = document.querySelector(".popup_booking_wrapper");
		this.clickedReservationCardId = "";
		this.clickedReservationId = 0;
	}

	addEventListeners() {
		let cancelButtons = document.querySelectorAll(".booking_cancel .btn");

		for (let button of Array.from(cancelButtons)) {
			if (button.classList.contains("disabled")) {
				continue;
			}

			button.addEventListener("click", this.popupOn);
		}

		let popupCloseButton = document.querySelector(".popup_btn_close");
		popupCloseButton.addEventListener("click", Cancel.popupOff);

		let popupNoButton = document.querySelector("#cancle_no");
		popupNoButton.addEventListener("click", Cancel.popupOff);

		let cancelAgreeButton = document.querySelector(".btn_green");
		cancelAgreeButton.addEventListener("click", this.sendCancelRequest);

	}

	popupOn(event) {
		const reservationCard = event.target.closest(".card_item");
		const popupArea = document.querySelector(".popup_booking_wrapper");
		const reservationTitle = reservationCard.querySelector(".tit").innerText;
		const reservationDate = reservationCard.querySelector("#reservation_date").dataset.reservation_date;
		const reservationInfoId = reservationCard.querySelector(".reservation_info_id").dataset.reservation_info_id;

		Cancel.clickedReservationCard = reservationCard;
		Cancel.clickedReservationId = reservationInfoId;

		popupArea.style.display = "block";
		popupArea.querySelector(".pop_tit SPAN").innerText = reservationTitle;
		popupArea.querySelector(".sm").innerText = reservationDate;

	}

	static popupOff() {
		document.querySelector(".popup_booking_wrapper").style.display = "none";
	}

	static moveCard() {
		let cancelArea = document.querySelector(".cancel");
		cancelArea.appendChild(Cancel.clickedReservationCard);

		let beforeCountArea = document.querySelector("#beforeCount");
		beforeCountArea.innerText = parseInt(beforeCountArea.innerText) - 1;

		let cancelCountArea = document.querySelector("#cancelCount");
		cancelCountArea.innerText = parseInt(cancelCountArea.innerText) + 1;

	}

	sendCancelRequest() {
		let XHR = new XMLHttpRequest();
		XHR.addEventListener("load", function() {
			if (XHR.status == 200) {
				Cancel.moveCard();
				Cancel.popupOff();

			} else if (XHR.status == 403) {
				alert("forbidden.");
			} else if (XHR.status == 404) {
				alert("not found.");
			} else {
				alert("sorry,something failed with unknown reason.");
			}
		});

		const url = "/api/reservations/" + Cancel.clickedReservationId;

		XHR.open("PUT", url);
		XHR.send();
	}

}

