let clickedCategory = 0;

function requestContents(url) {
	let XHR = new XMLHttpRequest();
	XHR.addEventListener("load", function() {
		if (XHR.status == 200) {
			console.log(XHR.response);
		}
	});

	XHR.open("GET", url);
	XHR.send();
}

let ulTag = document.querySelector(".section_event_tab .event_tab_lst");

ulTag.addEventListener("click", function(event) {
	//ajax통신할 때 cache 필요할 것.

	//선택된 카테고리의 글자색을 바꾸려면 자바스크립트를 꼭 써야하나? css만으론 안 되나?
	let target = event.target;
	let LiTag = target;

	console.log("target : ", target);

	if (target.tagName === "SPAN") {
		LiTag = target.parentNode.parentNode;
	} else if (target.tagName === "A") {
		LiTag = target.parentNode;
	} else if (target.tagName === "UL") {
		return;
	}

	let before = ulTag.children[clickedCategory].children[0].children[0];
	before.style.color = "black";
	before.style.fontWeight = "normal";

	clickedCategory = parseInt(LiTag.getAttribute('data-category'));

	let after = ulTag.children[clickedCategory].children[0].children[0];
	after.style.color = "#00c73c";
	after.style.fontWeight = "bold";

	requestContents("./sampleText.txt");
});