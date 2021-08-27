document.addEventListener("DOMContentLoaded", initReviewWritePage);

function getReservationInfoId() {
	let tokens = location.href.split("/");
	return tokens[tokens.length - 1].replace("#", "");
}

function getInitData(reservationInfoId, initData) {
	let XHR = new XMLHttpRequest();
	return new Promise(function(resolve, reject) {
		XHR.addEventListener("load", function() {
			if (XHR.status == 200) {

				initData = JSON.parse(XHR.responseText);

				resolve(initData);

			} else {
				alert("sorry. something failed");
				reject(new Error("sorry. something failed"));
			}
		});

		let url = "/api/productDescription/" + reservationInfoId;

		XHR.open("GET", url);
		XHR.send();
	});
}

async function initReviewWritePage() {
	const reservationInfoId = getReservationInfoId();

	let initData = await getInitData(reservationInfoId, {});

	console.log(initData);

	const title = document.querySelector(".title");
	title.innerText = initData.productDescription;

	let rating = new Rating();
	let reviewWrite = new ReviewWrite(reservationInfoId, initData.productId);

}

class Rating {
	constructor() {
		Rating.ratingStar = document.querySelector(".rating");
		Rating.ratingStar.addEventListener("click", Rating.rateStarEvent);
		Rating.ratingScore = document.querySelector(".star_rank");

	}

	static rateStarEvent(event) {
		if (event.target.closest(".rating_rdo") === null) {
			return;
		}

		let selectedStarValue = parseInt(event.target.closest(".rating_rdo").value);
		let allStar = Rating.ratingStar.querySelectorAll(".rating_rdo");

		for (let star of allStar) {
			if (parseInt(star.value) <= selectedStarValue) {
				star.classList.add("checked");
			} else {
				star.classList.remove("checked");
			}
		}

		Rating.ratingScore.innerText = selectedStarValue;

		if (selectedStarValue === 0) {
			Rating.ratingScore.classList.add("gray_star");
		} else {
			Rating.ratingScore.classList.remove("gray_star");
		}

	}
}

class ReviewWrite {
	constructor(reservationInfoId, productId) {
		ReviewWrite.reservationInfoId = reservationInfoId;
		ReviewWrite.productId = productId;

		ReviewWrite.textAreaWrapper = document.querySelector(".review_write_info");
		ReviewWrite.textArea = document.querySelector(".review_textarea");
		ReviewWrite.nowTextLengthArea = document.querySelector(".guide_review SPAN");
		ReviewWrite.submitButton = document.querySelector(".box_bk_btn");
		ReviewWrite.errorMsg = document.querySelector(".invalid");
		ReviewWrite.form = document.querySelector("#reviewForm");

		ReviewWrite.thumbNailTemplateWrapper = document.querySelector(".item");
		ReviewWrite.thumbNailTemplate = document.querySelector(".item_thumb");
		ReviewWrite.image = document.querySelector("#reviewImageFileOpenInput");
		ReviewWrite.thumbNailDeleteButton = document.querySelector(".spr_book.ico_del");


		ReviewWrite.addEventListeners();
	}


	static addEventListeners() {
		ReviewWrite.textAreaWrapper.addEventListener("click", ReviewWrite.clickTextArea);
		ReviewWrite.textArea.addEventListener("keyup", ReviewWrite.checkTextLengthEvent);
		ReviewWrite.submitButton.addEventListener("click", ReviewWrite.submitEvent);
		ReviewWrite.image.addEventListener("change", ReviewWrite.createThumbNailEvent);
		ReviewWrite.thumbNailDeleteButton.addEventListener("click", ReviewWrite.deleteThumbNailEvent);

	}

	static clickTextArea() {
		ReviewWrite.textAreaWrapper.classList.add("hide");
		ReviewWrite.textArea.focus();
	}

	static checkTextLengthEvent() {
		let text = ReviewWrite.textArea.value;
		if (text.length > 400) {
			text = text.substring(0, 400);
		}

		ReviewWrite.textArea.value = text;
		ReviewWrite.nowTextLengthArea.innerText = text.length;
	}

	static createThumbNailEvent() {
		ReviewWrite.thumbNailTemplateWrapper.style.display = "inline-block";
		ReviewWrite.thumbNailTemplate.src = window.URL.createObjectURL(ReviewWrite.image.files[0]);
	}

	static deleteThumbNailEvent() {
		ReviewWrite.thumbNailTemplateWrapper.style.display = "none";
		ReviewWrite.thumbNailTemplate.src = "";
	}

	static setFormHiddenData() {
		ReviewWrite.form.action = `api/reservations/${ReviewWrite.reservationInfoId}/comments`;

		ReviewWrite.form['form_comment'].value = ReviewWrite.textArea.value;
		ReviewWrite.form['form_productId'].value = ReviewWrite.productId;
		ReviewWrite.form['form_score'].value = Rating.ratingScore.innerText;
	}

	static submitEvent() {
		let textLength = ReviewWrite.textArea.value.length;
		if (textLength < 5 || textLength > 400) {
			ReviewWrite.errorMsg.style.display = "block";
			return;
		} else {
			ReviewWrite.errorMsg.style.display = "none";

			//submit
			ReviewWrite.setFormHiddenData();
			ReviewWrite.form.submit();
		}
	}


}