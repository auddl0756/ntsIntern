document.addEventListener("DOMContentLoaded", initDetail);
let totalInfos;

function initDetail() {
	let id = location.href.split("=")[1];

	document.querySelector("#detailExplain").addEventListener("click", explainMoreEvent);

	requestProductDetail(id, requestEtcImages);

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

	document.querySelector(".detail_location").className += " hide";
	let className = document.querySelector(".detail_area_wrap").className;
	document.querySelector(".detail_area_wrap").className = className.split(" ")[0];

	makeItemDetailArea(preprocessItemDetailInfo());
}

function preprocessItemDetailInfo() {
	let itemDetailInfo = {};
	itemDetailInfo.productContent = totalInfos.displayInfo.productContent;
	itemDetailInfo.saveFileName = totalInfos.displayInfoImage.saveFileName;

	return itemDetailInfo;
}

function makeItemDetailArea(info) {
	let targetArea = document.querySelector("#detail_area_wrap");

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

	document.querySelector(".detail_area_wrap").className += " hide";
	let className = document.querySelector(".detail_location").className;
	document.querySelector(".detail_location").className = className.split(" ")[0];

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

	if (nowImageNumber >= totalImageNumber) {
		return;
	}

	const imageWidth = 414;

	titleImageArea.style.transition = '0.5s ease-in';

	titleImageArea.style.left = (parseInt(titleImageArea.style.left) - imageWidth) + "px";

	document.querySelector(".num").innerText = parseInt(nowImageNumber) + 1;
}

function prevTitleImageEvent() {
	let titleImageArea = document.querySelector(".visual_img.detail_swipe");
	let nowImageNumber = document.querySelector(".num").innerText;
	let totalImageNumber = document.querySelector(".num.off").querySelector("SPAN").innerText;

	if (parseInt(nowImageNumber) === 1) {
		return;
	}

	const imageWidth = 414;

	titleImageArea.style.transition = '0.5s ease-in';

	titleImageArea.style.left = (parseInt(titleImageArea.style.left) + imageWidth) + "px";

	document.querySelector(".num").innerText = parseInt(nowImageNumber) - 1;
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
	let template = document.querySelector("#titleArea").innerText;
	let bindTemplate = Handlebars.compile(template);
	let targetHTML = document.querySelector(".visual_img.detail_swipe");

	document.querySelector(".num.off").querySelector("SPAN").innerText = "2";

	if (etcImages.length === 0) {
		document.querySelector(".num.off").querySelector("SPAN").innerText = "1";
		document.querySelector(".btn_nxt").style.display = "none";
		document.querySelector(".btn_prev").style.display = "none";
		return;
	}

	for (let iter = 0; iter < 2; iter++) {
		targetHTML.appendChild(targetHTML.children[0].cloneNode(true));
	}

	targetHTML.children[1].style.backgroundImage = "url('/" + etcImages[0].saveFileName + "')";

}

function explainMoreEvent(event) {
	let target = event.target;
	let openTag = document.querySelector(".bk_more._open");
	let closeTag = document.querySelector(".bk_more._close");
	let storeDetailTag = document.querySelector("#storeDetail");

	target = target.closest("A");

	if (target === null) {
		return;
	}

	if (target.className === "bk_more _open") {
		openTag.style.display = "none";
		closeTag.style.display = "block";
		storeDetailTag.className = "store_details";
	} else if (target.className === "bk_more _close") {
		openTag.style.display = "block";
		closeTag.style.display = "none";
		storeDetailTag.className = "store_details close3";
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

	preProcessComments(comments, productDescription);

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

function preProcessComments(comments, productDescription) {
	comments.forEach(function(item) {
		item.reservationDate = item.reservationDate.year + "." + item.reservationDate.monthValue + "." + item.reservationDate.dayOfMonth;
		item["productDescription"] = productDescription;

		if (item.commentImages.length == 0) {
			item.commentImages = "";
		} else {
			item.commentImages = item.commentImages[0].saveFileName;
		}
	});
}

