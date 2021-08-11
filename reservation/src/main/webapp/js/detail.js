document.addEventListener("DOMContentLoaded", initDetail);
let totalInfos;

function initDetail() {
	let id = location.href.split("=")[1];
	requestProductDetail(id, requestEtcImages);

	document.querySelector("#detailExplain").addEventListener("click", explainMoreEvent);

	document.querySelector(".btn_nxt").addEventListener("click", nextTitleImageEvent);
	document.querySelector(".btn_prev").addEventListener("click", prevTitleImageEvent);

	document.querySelector("#itemDetail").addEventListener("click", itemDetailEvent);
	document.querySelector("#itemPath").addEventListener("click", itemPathEvent);
}

function itemDetailEvent() {
	let target = event.target.closest("LI");
	if (target === null) {
		return;
	}

	changeTabState(target);

	$("#detail_location").hide();
	$("#detail_info").show();

	makeItemDetailArea(preprocessItemDetailInfo());
}

function preprocessItemDetailInfo() {
	let itemDetailInfo = {};
	itemDetailInfo.productContent = totalInfos.displayInfo.productContent;
	itemDetailInfo.saveFileName = totalInfos.displayInfoImage.saveFileName;

	return itemDetailInfo;
}

function makeItemDetailArea(info) {
	let targetArea = document.querySelector("#detail_info");

	let itemDetailTemplate = document.querySelector("#itemDetailTemplate").innerText;
	let bindTemplate = Handlebars.compile(itemDetailTemplate);

	let resultHTML = bindTemplate(info);
	targetArea.innerHTML = resultHTML;
}

function itemPathEvent() {
	let target = event.target.closest("LI");
	if (target === null) {
		return;
	}

	changeTabState(target);

	$("#detail_info").hide();
	$("#detail_location").show();

	makeItemPathArea(preprocessItemPathInfo());
}

function preprocessItemPathInfo() {
	let itemPathInfo = {};
	itemPathInfo.saveFileName = totalInfos.displayInfoImage.saveFileName;
	itemPathInfo.placeDescription = totalInfos.displayInfo.placeDescription;
	itemPathInfo.placeStreet = totalInfos.displayInfo.placeStreet;
	itemPathInfo.placeLot = totalInfos.displayInfo.placeLot;
	itemPathInfo.placeName = totalInfos.displayInfo.placeName;
	itemPathInfo.telephone = totalInfos.displayInfo.telephone;

	return itemPathInfo;
}

function makeItemPathArea(info) {
	let itemPathArea = document.querySelector("#detail_location");

	let itemPathTemplate = document.querySelector("#itemPathTemplate").innerText;
	let bindTemplate = Handlebars.compile(itemPathTemplate);

	let resultHTML = bindTemplate(info);
	itemPathArea.innerHTML = resultHTML;
}

function changeTabState(target) {
	for (let tab of target.parentNode.children) {
		tab.children[0].className = "anchor";
	}

	target.children[0].className = "anchor active";
}

function nextTitleImageEvent() {
	let titleImageArea = document.querySelector(".visual_img.detail_swipe");
	let nowImageNumber = document.querySelector(".num").innerText;
	let totalImageNumber = document.querySelector(".num.off").querySelector("SPAN").innerText;

	const imageWidth = 414;

	if (nowImageNumber == 2) {
		titleImageArea.style.transition = "none";
		titleImageArea.style.left = "0px";
		setTimeout(() => {
			titleImageArea.style.transition = '0.5s ease-in';
			titleImageArea.style.left = (-imageWidth) + "px";
		}, 10);

		document.querySelector(".num").innerText = 1;
	} else if (nowImageNumber == 1) {
		titleImageArea.style.transition = '0.5s ease-in';
		titleImageArea.style.left = (parseInt(titleImageArea.style.left) - imageWidth) + "px";
		document.querySelector(".num").innerText = 2;
	}
}

function prevTitleImageEvent() {
	let titleImageArea = document.querySelector(".visual_img.detail_swipe");
	let nowImageNumber = document.querySelector(".num").innerText;

	const imageWidth = 414;

	if (nowImageNumber == 1) {
		titleImageArea.style.transition = '0.5s ease-in';
		titleImageArea.style.left = (parseInt(titleImageArea.style.left) + imageWidth) + "px";

		document.querySelector(".num").innerText = 2;

	} else if (nowImageNumber == 2)  {
		titleImageArea.style.transition = "none";
		titleImageArea.style.left = (-imageWidth * 2) + "px";
		setTimeout(() => {
			titleImageArea.style.transition = '0.5s ease-in';
			titleImageArea.style.left = (parseInt(titleImageArea.style.left) + imageWidth) + "px";
		}, 10);

		document.querySelector(".num").innerText = 1;
	}
}

function requestEtcImages(displayInfoId, makeEtcCallback) {
	let XHR = new XMLHttpRequest();
	XHR.addEventListener("load", function() {
		if (XHR.status == 200) {
			etcImages = JSON.parse(XHR.responseText);

			makeEtcCallback(etcImages);
		} else {
			alert("sorry. something failed");
		}
	});

	let url = "/api/products/etcImages/" + displayInfoId;

	XHR.open("GET", url);
	XHR.send();
}

function makeEtcImages(etcImages) {
	let targetHTML = document.querySelector(".visual_img.detail_swipe");

	document.querySelector(".num.off").querySelector("SPAN").innerText = "2";

	if (etcImages.length === 0) {
		document.querySelector(".num.off").querySelector("SPAN").innerText = "1";
		document.querySelector(".btn_nxt").style.display = "none";
		document.querySelector(".btn_prev").style.display = "none";
		return;
	}

	for (let iter = 0; iter < 3; iter++) {
		targetHTML.appendChild(targetHTML.children[0].cloneNode(true));
	}

	targetHTML.children[0].style.backgroundImage = "url('/" + etcImages[0].saveFileName + "')";
	targetHTML.children[2].style.backgroundImage = "url('/" + etcImages[0].saveFileName + "')";

	const imageWidth = 414;
	targetHTML.style.left = (-imageWidth) + "px";
}

function explainMoreEvent() {
	$("#bk_more_open").toggle();
	$("#bk_more_close").toggle();

	let className = $("#storeDetail").attr('class');

	if (className === "store_details") {
		$("#storeDetail").attr('class', 'store_details close3');
	} else {
		$("#storeDetail").attr('class', 'store_details');
	}
}

function requestProductDetail(displayInfoId, requestEtcCallback) {
	let XHR = new XMLHttpRequest();
	XHR.addEventListener("load", function() {
		if (XHR.status == 200) {
			totalInfos = JSON.parse(XHR.responseText);

			makeTitleArea(totalInfos.displayInfo, totalInfos.displayInfoImage);

			makeGradeArea(totalInfos.averageScore, totalInfos.comments.length);

			makeCommentsArea(totalInfos.comments, totalInfos.displayInfo.productDescription);

			requestEtcCallback(displayInfoId, makeEtcImages);

			makeItemDetailArea(preprocessItemDetailInfo());
			makeItemPathArea(preprocessItemPathInfo());

		} else {
			alert("sorry. something failed");
		}
	});

	let url = "/api/products/" + displayInfoId;

	XHR.open("GET", url);
	XHR.send();
}

function makeTitleArea(displayInfo, displayInfoImage) {
	let template = document.querySelector("#titleArea").innerText;
	let bindTemplate = Handlebars.compile(template);

	let resultHTML = bindTemplate(displayInfoImage);

	let targetHTML = document.querySelector(".visual_img.detail_swipe");

	targetHTML.innerHTML = resultHTML;

	document.querySelector(".visual_txt_tit").innerHTML = displayInfo.productDescription;
	document.querySelector(".store_details").innerHTML = displayInfo.productContent;
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

function makeCommentsArea(comments, productDescription) {
	let commentArea = document.querySelector(".list_short_review");
	let template = document.querySelector("#commentArea").innerText;
	let bindTemplate = Handlebars.compile(template);
	let targetHTML = document.querySelector(".list_short_review");

	preprocessComments(comments, productDescription);

	let idx = commentArea.childElementCount;

	for (let count = 0; count < 3; count++) {
		if (idx + count >= comments.length) {
			break;
		}

		let newList = document.createElement("li");
		newList.classList.add("list_item");
		newList.innerHTML = bindTemplate(comments[idx + count]);

		targetHTML.appendChild(newList);
	}
}

function preprocessComments(comments, productDescription) {
	comments.forEach(function(item) {
		item.reservationDate = item.reservationDate.year + "." + item.reservationDate.monthValue + "." + item.reservationDate.dayOfMonth;
		item.productDescription = productDescription;

		if (item.commentImages.length == 0) {
			item.commentImages = "";
		} else {
			item.commentImages = item.commentImages[0].saveFileName;
		}
	});
}

