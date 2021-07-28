let clickedCategory = 0;

function makeTemplate(data) {
	let html = document.querySelector("#itemList").innerHTML;
	let resultHTML = html;

	for (let elem of data) {
		for (let key in elem) {
			resultHTML = resultHTML.replace("${" + key + "}", elem[key]);
		}
	}

	return resultHTML;
}

function requestContents(url) {
	let XHR = new XMLHttpRequest();
	XHR.addEventListener("load", function() {
		if (XHR.status == 200) {
			// console.log(XHR.response);
			let data = JSON.parse(XHR.responseText);

			let html = makeTemplate(data);
			let eventBox = document.querySelector(".wrap_event_box");

			eventBox.innerHTML = html;

		} else {
			alert("sorry. something failed");
		}
	});

	XHR.open("GET", url);
	XHR.send();
}

let ulTag = document.querySelector(".section_event_tab .event_tab_lst");

ulTag.addEventListener("click", function(event) {
	//ajax통신할 때 cache 필요할 것.

	let target = event.target;
	let liTag = target;

	if (target.tagName === "SPAN") {
		liTag = target.parentNode.parentNode;
	} else if (target.tagName === "A") {
		liTag = target.parentNode;
	} else if (target.tagName === "UL") {
		return;
	}

	let before = ulTag.children[clickedCategory].children[0].children[0];
	before.style.color = "black";
	before.style.fontWeight = "normal";

	clickedCategory = parseInt(liTag.getAttribute('data-category'));
	 
	let after = ulTag.children[clickedCategory].children[0].children[0];
	after.style.color = "#00c73c";
	after.style.fontWeight = "bold";

	requestContents("/sampleJson.json");
});