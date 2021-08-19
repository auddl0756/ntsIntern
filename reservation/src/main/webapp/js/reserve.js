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
	ticketBodyArea.enrollEvent();

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
		let resultHTML = this.priceInfos.map((info) =>  {
			return bindTemplate(info);
		});

		resultHTML = resultHTML.join(" ");

		let targetHTML = document.querySelector(".ticket_body");
		targetHTML.innerHTML = resultHTML;
	}

	enrollEvent() {
		let ticketButtons = document.querySelectorAll(".clearfix");

		for (let button of Array.from(ticketButtons)) {
			button.addEventListener("click", this.ticketEvent);
			button.addEventListener("click", this.calculatePriceEvent);
		}
	}

	ticketEvent() {
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


		let priceArea = wrapperArea.nextElementSibling;

		let price = parseInt(wrapperArea.closest(".qty").querySelector(".price").innerText);
		let count = parseInt(wrapperArea.children[1].value);

		if (count <= 0) {
			priceArea.className = "individual_price";
		} else {
			priceArea.className = "individual_price on_color";
		}

		priceArea.children[0].innerText = price * count;

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