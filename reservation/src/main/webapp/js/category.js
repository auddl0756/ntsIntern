(document.querySelector(".section_event_tab .event_tab_lst")
	.addEventListener("click", categoryChangeEvent));

(document.querySelector(".more .btn")
	.addEventListener("click", categoryMoreEvent));


let categoryObj = {
	clickedCategory: 0,
	totalCategoryCount: [],
	cachedCategoryInfos: [[], [], [], [], [], []],
	pagingStartIdx: 0
}

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

	let before = categoryArea.children[categoryObj.clickedCategory].children[0].children[0];
	before.style.color = "black";
	before.style.fontWeight = "normal";

	let beforeParent = before.parentElement;
	beforeParent.className = "anchor";

	categoryObj.clickedCategory = parseInt(liTag.getAttribute('data-category'));

	let after = categoryArea.children[categoryObj.clickedCategory].children[0].children[0];
	after.style.color = "#00c73c";
	after.style.fontWeight = "bold";

	let afterParent = after.parentElement;
	afterParent.className = "anchor active";

	requestContents("/api/productImages", event);
	requestTotalSize("/api/productImages");
}

function categoryMoreEvent(event) {
	requestContents("/api/productImages", event);
}


function requestContents(url, event) {
	categoryObj.pagingStartIdx = categoryObj.cachedCategoryInfos[categoryObj.clickedCategory].length;

	document.querySelector(".more .btn").style.display = "block";
	if (categoryObj.pagingStartIdx == categoryObj.totalCategoryCount[categoryObj.clickedCategory]) {
		document.querySelector(".more .btn").style.display = "none";
		let targetHTML = document.querySelector(".wrap_event_box");
		makeTemplateCategory(targetHTML);
		return;
	}

	if (event.currentTarget.className === "event_tab_lst tab_lst_min") {
		if (categoryObj.cachedCategoryInfos[categoryObj.clickedCategory].length !== 0) {
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
				categoryObj.cachedCategoryInfos[categoryObj.clickedCategory].push(info);
			}

			let targetHTML = document.querySelector(".wrap_event_box");

			makeTemplateCategory(targetHTML);

		} else {
			alert("sorry. something failed");
		}
	});

	url += "/" + categoryObj.clickedCategory + "?type=th";
	url += "&start=" + categoryObj.pagingStartIdx;

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

			categoryObj.totalCategoryCount[categoryObj.clickedCategory] = categorySize;

		} else {
			alert("sorry. something failed");
		}
	});

	url += "/size";
	if (categoryObj.clickedCategory !== 0) {
		url += "/" + categoryObj.clickedCategory;
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

	let categoryInfos = categoryObj.cachedCategoryInfos[categoryObj.clickedCategory];

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

