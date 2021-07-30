document.addEventListener("DOMContentLoaded", init());

function init() {
	requestPromotions("/api/promotions/");
}

function requestPromotions(url) {
	let XHR = new XMLHttpRequest();
	XHR.addEventListener("load", function() {
		if (XHR.status == 200) {
			let promotionInfos = JSON.parse(XHR.responseText);

			let resultHtml = makeTemplatePromotion(promotionInfos);

			let promotionArea = document.querySelector(".container_visual .container_visual .visual_img");
			//let promotionArea = document.querySelector(".container_visual .container_visual");

			promotionArea.innerHTML = resultHtml;

			/* 제대로 작동하지 않지만 requestAnimationFrame이 setInterval보다 정확히 동작하므로 requestAnimation 사용하도록 개선 필요. */
			/*	var curIndex = 0;
				function move(){
					//console.log(curIndex);				
					if(curIndex === 10){
						curIndex = -1;
					} 
					curIndex++;
					
					promotionArea.style.transition = '0.2s';
					promotionArea.style.transform = "translate3d(-"+200*(curIndex+1)+"px, 0px, 0px)";
					
					console.log(curIndex,promotionArea.style.transform);
							
					requestAnimationFrame(move);
				}
				
				requestAnimationFrame(move);
			*/

			//프로모션 영역 무한 슬라이딩
			(function() {
				var curIndex = 0;

				setInterval(function() {
					promotionArea.style.transition = '0.2s ease-in';
					promotionArea.style.transform = "translate3d(-" + 414 * (curIndex + 1) + "px, 0px, 0px)";

					curIndex++;

					if (curIndex === 10) {
						curIndex = -1;
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

	for (let info of promotionInfos) {
		let hereHTML = html;
		for (let key in info) {
			hereHTML = hereHTML.replace("${" + key + "}", info[key]);
		}
		resultHTML += hereHTML;
	}

	return resultHTML;
}

