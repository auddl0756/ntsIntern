document.addEventListener("DOMContentLoaded", initPromotion);

function initPromotion() {
	requestPromotions("/api/promotions/");
}

function requestPromotions(url) {
	let XHR = new XMLHttpRequest();

	XHR.addEventListener("load", function() {
		if (XHR.status == 200) {
			let promotionInfos = JSON.parse(XHR.responseText);
			let promotionInfosLength = Object.keys(promotionInfos).length + 1;

			let resultHtml = makeTemplatePromotion(promotionInfos);

			let promotionArea = document.querySelector(".container_visual .container_visual .visual_img");

			promotionArea.innerHTML = resultHtml;
			promotionArea.style.left = "0px";
			promotionArea.style.transition = '0.5s ease-in';

			//프로모션 영역 무한 슬라이딩
			(function() {
				let curIndex = 0;
				const imageWidth = 414;

				setInterval(function() {
					promotionArea.style.left = (parseInt(promotionArea.style.left) - imageWidth) + "px";
					curIndex++;

					if (curIndex === promotionInfosLength) {
						promotionArea.style.transition = "none";
						promotionArea.style.left = "0px";
						setTimeout(() => {
							promotionArea.style.transition = '0.5s ease-in';
							promotionArea.style.left = (parseInt(promotionArea.style.left) - imageWidth) + "px";
							curIndex = 1;
						}, 10);

					} else {
						promotionArea.style.transition = '0.5s ease-in';
					}
				}, 1000);
			}).call(promotionArea);

		} else {
			alert("sorry. something failed");
		}
	});

	XHR.open("GET", url);
	XHR.send();
}


function makeTemplatePromotion(promotionInfos) {
	let html = document.querySelector("#promotionItem").innerHTML;
	let resultHTML = "";
	let firstHTML = "";
	let isFirst = true;

	for (let info of promotionInfos) {
		let hereHTML = html;
		for (let key in info) {
			hereHTML = hereHTML.replace("${" + key + "}", info[key]);
		}
		resultHTML += hereHTML;

		if (isFirst) {
			firstHTML = hereHTML;
			isFirst = false;
		}
	}

	resultHTML += firstHTML;

	return resultHTML;
}

