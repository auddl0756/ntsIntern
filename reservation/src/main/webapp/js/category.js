(document.querySelector(".section_event_tab .event_tab_lst")
	.addEventListener("click", categoryChangeEvent));

let clickedCategory = 0;

function categoryChangeEvent(event) {
	//ajax통신할 때 cache 필요할 것.

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

	let before = categoryArea.children[clickedCategory].children[0].children[0];
	before.style.color = "black";
	before.style.fontWeight = "normal";

	let beforeParent = before.parentElement;
	beforeParent.className = "anchor";

	clickedCategory = parseInt(liTag.getAttribute('data-category'));

	let after = categoryArea.children[clickedCategory].children[0].children[0];
	after.style.color = "#00c73c";
	after.style.fontWeight = "bold";

	let afterParent = after.parentElement;
	afterParent.className = "anchor active";

	requestContents("/api/productImages");
}


function requestContents(url) {
	let XHR = new XMLHttpRequest();
	XHR.addEventListener("load", function() {
		if (XHR.status == 200) {

			let categoryInfos = JSON.parse(XHR.responseText);
			let targetHTML = document.querySelector(".wrap_event_box");

			makeTemplateCategory(targetHTML, categoryInfos);

		} else {
			alert("sorry. something failed");
		}
	});
	if (clickedCategory === 0) {
		url += "/" + "ALL_CATEGORY";
	} else {
		url += "/" + clickedCategory;
	}
	url += "?type=th";

	XHR.open("GET", url);
	XHR.send();
}

function makeTemplateCategory(targetHTML, categoryInfos) {
	let htmlTemplate = document.querySelector("#itemList").innerHTML;

	let htmlLocation = "left";
	let leftBox = targetHTML.children[0];
	let rightBox = targetHTML.children[1];

	let leftHTML = "";
	let rightHTML = "";

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

