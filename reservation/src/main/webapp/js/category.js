document.addEventListener("DOMContentLoaded", initCategory);

function initCategory() {
	document.querySelector("#categoryTab")
		.addEventListener("click", categoryChangeEvent);

	document.querySelector(".more .btn")
		.addEventListener("click", categoryMoreEvent);

	requestCategoryTab(categoryObj.init);
}

let categoryObj = {
	clickedCategory: 0,
	totalCategoryCount: { 0: 0 },
	cachedProductInfos: {},
	smallestDisplayInfoId: {},
	biggestDisplayInfoId: {},

	init(categoryInfos, callBack) {
		const totalCategoryId = 0;
		const MAX_VALUE = 1000_000_000;

		for (info of categoryInfos) {
			categoryObj.totalCategoryCount[info.id] = info.count;
			categoryObj.totalCategoryCount[totalCategoryId] += info.count;

			categoryObj.cachedProductInfos[info.id] = [];

			categoryObj.smallestDisplayInfoId[info.id] = MAX_VALUE;
			categoryObj.biggestDisplayInfoId[info.id] = 0;
		}

		categoryObj.cachedProductInfos[totalCategoryId] = [];
		categoryObj.smallestDisplayInfoId[totalCategoryId] = MAX_VALUE;
		categoryObj.biggestDisplayInfoId[totalCategoryId] = 0;

		document.querySelector(".pink").innerText = categoryObj.totalCategoryCount[categoryObj.clickedCategory] + "개";

		drawCategoryTab(categoryInfos);

		callBack();
	}
};

function requestCategoryTab(callBack) {
	let XHR = new XMLHttpRequest();

	XHR.addEventListener("load", function() {
		if (XHR.status == 200) {
			let categoryInfos = JSON.parse(XHR.responseText);
			//categoryObj.init(categoryInfos);
			callBack(categoryInfos, requestProducts);

			//debugger;

		} else {
			alert("sorry. something failed");
		}
	});

	let url = "/api/categories";

	XHR.open("GET", url);
	XHR.send();
}

function drawCategoryTab(categoryInfos) {
	let categoryTab = document.querySelector("#categoryTab");
	let htmlTemplate = document.querySelector("#categoryList").innerHTML;

	let tabHTML = "";

	for (info of categoryInfos) {
		let hereHTML = htmlTemplate;
		for (let key in info) {
			hereHTML = hereHTML.replace("${" + key + "}", info[key]);
		}
		tabHTML += hereHTML;
	}
	categoryTab.innerHTML += tabHTML;
}


function categoryChangeEvent(event) {
	changeCategoryState(event);

	if (categoryObj.cachedProductInfos[categoryObj.clickedCategory].length === 0) {
		requestProducts(event.currentTarget);
	}

	drawProducts(categoryObj.cachedProductInfos[categoryObj.clickedCategory]);
	DisplayMoreButton();
}


function changeCategoryState(event) {
	let categoryTab = document.querySelector("#categoryTab");
	let categoryItem = event.target;

	if (categoryItem.tagName === "UL") {
		return;
	}

	categoryItem = categoryItem.closest(".item");

	for (let categoryElem of categoryTab.children) {
		categoryElem.children[0].className = "anchor";
	}

	categoryObj.clickedCategory = parseInt(categoryItem.dataset.category);

	let after = categoryTab.children[categoryObj.clickedCategory].children[0].children[0];

	let afterParent = after.parentElement;
	afterParent.className = "anchor active";

	document.querySelector(".pink").innerText = categoryObj.totalCategoryCount[categoryObj.clickedCategory] + "개";
}

function requestProducts() {
	const clickedCategory = categoryObj.clickedCategory;

	let XHR = new XMLHttpRequest();
	XHR.addEventListener("load", function() {
		if (XHR.status == 200) {
			let categoryInfos = JSON.parse(XHR.responseText);

			let items = categoryInfos.items;

			for (let info of items) {
				categoryObj.cachedProductInfos[clickedCategory].push(info);

				categoryObj.biggestDisplayInfoId[clickedCategory] = Math.max(categoryObj.biggestDisplayInfoId[clickedCategory], info.displayInfoId);
				categoryObj.smallestDisplayInfoId[clickedCategory] = Math.min(categoryObj.smallestDisplayInfoId[clickedCategory], info.displayInfoId);
			}

			drawProducts(categoryObj.cachedProductInfos[clickedCategory]);

		} else {
			alert("sorry. something failed");
		}
	});

	let url = "/api/products";

	url += "?categoryId=" + clickedCategory;
	url += "&excludeFirst=" + categoryObj.smallestDisplayInfoId[clickedCategory];
	url += "&excludeLast=" + categoryObj.biggestDisplayInfoId[clickedCategory];

	XHR.open("GET", url);
	XHR.send();
}

function categoryMoreEvent(event) {
	requestProducts();
	DisplayMoreButton();
}

function DisplayMoreButton() {
	let moreButton = document.querySelector(".more .btn");
	let clickedCategory = categoryObj.clickedCategory;

	if (categoryObj.cachedProductInfos[clickedCategory].length === categoryObj.totalCategoryCount[clickedCategory]) {
		moreButton.style.display = "none";
	} else {
		moreButton.style.display = "block";
	}
}

function drawProducts(categoryItems) {
	let targetHTML = document.querySelector(".wrap_event_box");

	let htmlTemplate = document.querySelector("#itemList").innerHTML;

	let htmlLocation = "left";
	let leftBox = targetHTML.children[0];
	let rightBox = targetHTML.children[1];

	let leftHTML = "";
	let rightHTML = "";

	for (let item of categoryItems) {
		let hereHTML = htmlTemplate;

		for (let key in item) {
			hereHTML = hereHTML.split("${" + key + "}").join(item[key]);
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

