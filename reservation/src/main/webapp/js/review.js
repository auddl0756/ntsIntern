document.addEventListener("DOMContentLoaded", initReview);
let totalInfos;

function initReview() {
	let id = document.referrer.split("=")[1];

	constructBackUrl(id);

	requestProductDetail(id);
}

function constructBackUrl(id) {
	let backButton = document.querySelector(".btn_back");
	backButton.setAttribute("href", backButton.getAttribute("href") + "?id=" + id);
}

function requestProductDetail(displayInfoId) {
	let XHR = new XMLHttpRequest();
	XHR.addEventListener("load", function() {
		if (XHR.status == 200) {
			totalInfos = JSON.parse(XHR.responseText);

			makeGradeArea(totalInfos.averageScore, totalInfos.comments.length);

			makeCommentArea(totalInfos.comments, totalInfos.displayInfo.productDescription);

		} else {
			alert("sorry. something failed");
		}
	});

	let url = "/api/products/" + displayInfoId;

	XHR.open("GET", url);
	XHR.send();
}


function makeGradeArea(averageScore, commentCount) {
	let gradeArea = document.querySelector(".grade_area");
	let graphValue = document.querySelector(".graph_value");
	let gradeText = gradeArea.querySelector(".text_value").querySelector("span");
	let joinCount = document.querySelector(".join_count");

	graphValue.style.width = (100 * averageScore / 5) + "%";
	gradeText.innerText = averageScore;
	joinCount.children[0].innerText = commentCount + "건";
}

function makeCommentArea(comments, productDescription) {
	let template = document.querySelector("#commentArea").innerText;
	let bindTemplate = Handlebars.compile(template);
	let targetHTML = document.querySelector(".list_short_review");

	preProcessComments(comments, productDescription);

	comments.forEach(function(comment) {
		let newList = document.createElement("li");
		newList.classList.add("list_item");
		newList.innerHTML = bindTemplate(comment);

		targetHTML.appendChild(newList);
	});
}

function preProcessComments(comments, productDescription) {
	comments.forEach(function(item) {
		item.reservationDate = item.reservationDate.year + "." + item.reservationDate.monthValue + "." + item.reservationDate.dayOfMonth;
		item.productDescription = productDescription;

		if (item.commentImages.length === 0) {
			item.commentImages = "";
		} else {
			item.commentImages = item.commentImages[0].saveFileName;
		}
	});
}

