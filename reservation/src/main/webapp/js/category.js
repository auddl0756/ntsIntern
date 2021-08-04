document.querySelector("#categoryTab")
	.addEventListener("click", categoryChangeEvent);

document.querySelector(".more .btn")
	.addEventListener("click", categoryMoreEvent);


let categoryObj = {
	clickedCategory: 0,
	totalCategoryCount: { 0: 0 },
	cachedProductInfos: {},
	oldestDisplayInfoId: { 0: 0 },
	latestDisplayInfoId: { 0: 0 },

	init() {
		categoryInfos = arguments[0];
		const totalCategoryId = 0;

		for (info of categoryInfos) {
			categoryObj.totalCategoryCount[info.id] = info.count;
			categoryObj.totalCategoryCount[totalCategoryId] += info.count;

			categoryObj.cachedProductInfos[info.id] = [];

			categoryObj.oldestDisplayInfoId[info.id] = 1e9;
			categoryObj.latestDisplayInfoId[info.id] = 0;
		}
		categoryObj.cachedProductInfos[totalCategoryId] = [];

		document.querySelector(".pink").innerText = categoryObj.totalCategoryCount[categoryObj.clickedCategory] + "개";

		templateCategoryTab(categoryInfos);

	}
};


function requestCategoryTab() {
	let XHR = new XMLHttpRequest();
	let categoryInfos = null;

	XHR.addEventListener("load", function() {
		if (XHR.status == 200) {
			categoryInfos = JSON.parse(XHR.responseText);

			categoryObj.init(categoryInfos);


		} else {
			alert("sorry. something failed");
		}
	});

	let url = "/api/categories";

	XHR.open("GET", url);
	XHR.send();
}


function templateCategoryTab(categoryInfos) {
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

	templateProducts(categoryObj.cachedProductInfos[categoryObj.clickedCategory]);

}


function changeCategoryState(event) {
	let target = event.target;
	let liTag = target;
	let categoryTab = document.querySelector("#categoryTab");

	if (target.tagName === "SPAN") {
		liTag = target.parentNode.parentNode;
	} else if (target.tagName === "A") {
		liTag = target.parentNode;
	} else if (target.tagName === "UL") {
		return;
	}

	for (let categoryElem of categoryTab.children) {
		categoryElem.children[0].className = "anchor";
	}

	categoryObj.clickedCategory = parseInt(liTag.dataset.category);

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

				categoryObj.latestDisplayInfoId[clickedCategory] = Math.max(categoryObj.latestDisplayInfoId[clickedCategory], info.displayInfoId);
				categoryObj.oldestDisplayInfoId[clickedCategory] = Math.min(categoryObj.oldestDisplayInfoId[clickedCategory], info.displayInfoId);
			}

			templateProducts(items);

			let moreButton = document.querySelector(".more .btn");
			moreButton.style.display = "block";

			if (items.length === 0) {
				moreButton.style.display = "none";
			}

		} else {
			alert("sorry. something failed");
		}
	});

	let url = "/api/products";

	url += "?categoryId=" + clickedCategory;
	url += "&excludeFirst=" + categoryObj.oldestDisplayInfoId[clickedCategory];
	url += "&excludeLast=" + categoryObj.latestDisplayInfoId[clickedCategory];

	XHR.open("GET", url);
	XHR.send();
}


function categoryMoreEvent(event) {
	requestProducts();
}


function templateProducts(categoryItems) {
	let targetHTML = document.querySelector(".wrap_event_box");

	let htmlTemplate = document.querySelector("#itemList").innerHTML;

	let htmlLocation = "left";
	let leftBox = targetHTML.children[0];
	let rightBox = targetHTML.children[1];

	let leftHTML = "";
	let rightHTML = "";

	for (let item of categoryItems) {
		let hereHTML = htmlTemplate;
		for (let iter = 0; iter < 2; iter++) {
			for (let key in item) {
				hereHTML = hereHTML.replace("${" + key + "}", item[key]);
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


function initCategory() {
	requestCategoryTab();
	requestProducts();
}

initCategory();

