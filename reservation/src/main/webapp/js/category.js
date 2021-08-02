(document.querySelector(".section_event_tab .event_tab_lst")
	.addEventListener("click", categoryChangeEvent));

(document.querySelector(".more .btn")
	.addEventListener("click", categoryMoreEvent));

//가장 최근에 클릭한 카테고리 2개를 저장. 인덱스가 작을수록 최근 클릭한 카테고리.
let clickedCategory = [0, 0];

let totalCategoryCount = [];
let cachedCategoryInfos = [[], [], [], [], [], []];
let pagingStartIdx = 0;

function categoryChangeEvent(event) {
	let target = event.target;
	let liTag = target;
	let categoryArea = document.querySelector(".section_event_tab .event_tab_lst");

	if (target.tagName === "SPAN") {
		liTag = target.parentNode.parentNode;
	} else if (target.tagName === "A") {
		liTag = target.parentNode;
	} else if (target.tagName === "UL") {
		return;
	}

	let before = categoryArea.children[clickedCategory[0]].children[0].children[0];
	before.style.color = "black";
	before.style.fontWeight = "normal";

	let beforeParent = before.parentElement;
	beforeParent.className = "anchor";

	clickedCategory[1] = clickedCategory[0];
	clickedCategory[0] = parseInt(liTag.getAttribute('data-category'));

	let after = categoryArea.children[clickedCategory[0]].children[0].children[0];
	after.style.color = "#00c73c";
	after.style.fontWeight = "bold";

	let afterParent = after.parentElement;
	afterParent.className = "anchor active";

	if (clickedCategory[0] === clickedCategory[1]) {
		return;
	}

	requestContents("/api/productImages", event);
	requestTotalSize("/api/productImages");
}

function categoryMoreEvent(event) {
	requestContents("/api/productImages", event);
}


function requestContents(url, event) {
	pagingStartIdx = cachedCategoryInfos[clickedCategory[0]].length;

	if (pagingStartIdx == totalCategoryCount[clickedCategory[0]]) {
		document.querySelector(".more .btn").style.display = "none";
		return;
	} else {
		document.querySelector(".more .btn").style.display = "block";
	}

	if (event.currentTarget.className === "event_tab_lst tab_lst_min") {
		if (cachedCategoryInfos[clickedCategory[0]].length !== 0) {
			let targetHTML = document.querySelector(".wrap_event_box");
			makeTemplateCategory(targetHTML);
			return;
		}
	}

	let XHR = new XMLHttpRequest();
	XHR.addEventListener("load", function() {
		if (XHR.status == 200) {

			let categoryInfos = JSON.parse(XHR.responseText);

			for (let info of categoryInfos) {
				cachedCategoryInfos[clickedCategory[0]].push(info);
			}

			let targetHTML = document.querySelector(".wrap_event_box");

			makeTemplateCategory(targetHTML);

		} else {
			alert("sorry. something failed");
		}
	});

	url += "/" + clickedCategory[0] + "?type=th";
	url += "&start=" + pagingStartIdx;

	XHR.open("GET", url);
	XHR.send();
}


function requestTotalSize(url) {
	let XHR = new XMLHttpRequest();
	XHR.addEventListener("load", function() {
		if (XHR.status == 200) {

			let categorySize = JSON.parse(XHR.responseText);
			let targetHTML = document.querySelector(".pink");

			targetHTML.innerText = categorySize + "개";

			totalCategoryCount[clickedCategory[0]] = categorySize;

		} else {
			alert("sorry. something failed");
		}
	});

	url += "/size";
	if (clickedCategory[0] !== 0) {
		url += "/" + clickedCategory[0];
	}
	url += "/?type=th";

	XHR.open("GET", url);
	XHR.send();
}

function makeTemplateCategory(targetHTML) {
	let htmlTemplate = document.querySelector("#itemList").innerHTML;

	let htmlLocation = "left";
	let leftBox = targetHTML.children[0];
	let rightBox = targetHTML.children[1];

	let leftHTML = "";
	let rightHTML = "";

	let categoryInfos = cachedCategoryInfos[clickedCategory[0]];

	for (let info of categoryInfos) {
		let hereHTML = htmlTemplate;
		for (let iter = 0; iter < 2; iter++) {
			for (let key in info) {
				hereHTML = hereHTML.replace("${" + key + "}", info[key]);
			}
		}

		if (htmlLocation === "left") {
			leftHTML += hereHTML;
			htmlLocation = "right";
		} else {
			rightHTML += hereHTML;
			htmlLocation = "left";
		}
	}

	leftBox.innerHTML = leftHTML;
	rightBox.innerHTML = rightHTML;
}

