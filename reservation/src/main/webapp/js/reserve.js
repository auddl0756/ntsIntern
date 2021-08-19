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

	//console.log(productData);

	let titleArea = new TitleArea(displayInfoId, productData);

	titleArea.makeBackUrl();
	titleArea.makeTitle();
	titleArea.makeTitleImage();
	titleArea.makeDetails();

	let ticketBodyArea = new TicketBodyArea(displayInfoId, productData.priceInfos);
	ticketBodyArea.makeTicketBodyArea();

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
}