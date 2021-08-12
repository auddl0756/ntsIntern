document.addEventListener("DOMContentLoaded", initDetailPage);

function initDetailPage() {
	detailObj.initDetail(detailObj.detailData);
	detailEvent.initDetailEvent();
}

let detailObj = {
	detailData: {},
	initDetail(data) {

		this.getDisplayInfoId(data)
			.then(function(result) {
				return detailObj.getProductDetailData(result);
			})
			.then(function(result) {
				return detailObj.getEtcImages(result);
			})
			.then(function(result) {
				detailObj.makeUI(result);
			});
	},

	getDisplayInfoId(detailData) {
		return new Promise(function(resolve, reject) {
			detailData.displayInfoId = location.href.split("=")[1];
			resolve(detailData);
		});
	},

	getProductDetailData(detailData) {
		let XHR = new XMLHttpRequest();

		return new Promise(function(resolve, reject) {
			XHR.addEventListener("load", function() {
				if (XHR.status == 200) {
					let data = JSON.parse(XHR.responseText);

					for (let key in data) {
						detailData[key] = data[key];
					}

					resolve(detailData);
				} else {
					alert("sorry. something failed");
					reject(new Error("sorry. something failed"));
				}
			});
			
			
			let url = "/api/products/" + detailData.displayInfoId;
			
			XHR.open("GET", url);
			XHR.send();
		});
	},

	getEtcImages(detailData) {
		let XHR = new XMLHttpRequest();
		return new Promise(function(resolve, reject) {
			XHR.addEventListener("load", function() {
				if (XHR.status == 200) {
					let data = JSON.parse(XHR.responseText);

					detailData.etcImages = JSON.parse(XHR.responseText);
					resolve(detailData);
				} else {
					alert("sorry. something failed");
					reject(new Error("sorry. something failed"));
				}
			});

			let url = "/api/products/etcImages/" + detailData.displayInfoId;

			XHR.open("GET", url);
			XHR.send();
		});
	},

	makeUI(detailData) {
		detailObj.makeTitleArea(detailData.displayInfo, detailData.productImages);
		detailObj.makeEtcImages(detailData.etcImages);
		detailObj.makeGradeArea(detailData.averageScore, detailData.comments.length);
		detailObj.makeCommentsArea(detailData.comments, detailData.displayInfo.productDescription);
	},

	makeTitleArea(displayInfo, productImages) {
		let template = document.querySelector("#titleArea").innerText;
		let bindTemplate = Handlebars.compile(template);

		let resultHTML = bindTemplate(productImages[0]);

		let targetHTML = document.querySelector(".visual_img.detail_swipe");

		targetHTML.innerHTML = resultHTML;

		document.querySelector(".visual_txt_tit").innerHTML = displayInfo.productDescription;
		document.querySelector(".store_details").innerHTML = displayInfo.productContent;
	},

	makeEtcImages(etcImages) {
		let targetHTML = document.querySelector(".visual_img.detail_swipe");

		document.querySelector(".num.off").querySelector("SPAN").innerText = "2";

		if (etcImages.length === 0) {
			document.querySelector(".num.off").querySelector("SPAN").innerText = "1";
			document.querySelector(".btn_nxt").style.display = "none";
			document.querySelector(".btn_prev").style.display = "none";
			return;
		}

		for (let iter = 0; iter < 3; iter++) {
			targetHTML.appendChild(targetHTML.children[0].cloneNode(true));
		}

		targetHTML.children[0].style.backgroundImage = "url('/" + etcImages[0].saveFileName + "')";
		targetHTML.children[2].style.backgroundImage = "url('/" + etcImages[0].saveFileName + "')";

		const imageWidth = 414;
		targetHTML.style.left = (-imageWidth) + "px";
	},


	makeGradeArea(averageScore, commentsCount) {
		let gradeArea = document.querySelector(".grade_area");
		let graphValue = document.querySelector(".graph_value");
		let gradeText = gradeArea.querySelector(".text_value").querySelector("span");
		let joinCount = document.querySelector(".join_count");

		graphValue.style.width = (100 * averageScore / 5) + "%";

		gradeText.innerText = averageScore;
		joinCount.children[0].innerText = commentsCount + "건";
	},

	makeCommentsArea(comments, productDescription) {
		let commentArea = document.querySelector(".list_short_review");
		let template = document.querySelector("#commentArea").innerText;
		let bindTemplate = Handlebars.compile(template);
		let targetHTML = document.querySelector(".list_short_review");

		this.preprocessComments(comments, productDescription);

		let idx = commentArea.childElementCount;

		for (let count = 0; count < 3; count++) {
			if (idx + count >= comments.length) {
				break;
			}

			let newList = document.createElement("li");
			newList.classList.add("list_item");
			newList.innerHTML = bindTemplate(comments[idx + count]);

			if (comments[idx + count].commentImages === "none") {
				newList.querySelector(".thumb_area").style.display = "none";
			}

			targetHTML.appendChild(newList);
		}
	},

	makeItemDetailArea(info) {
		let targetArea = document.querySelector("#detail_info");

		let itemDetailTemplate = document.querySelector("#itemDetailTemplate").innerText;
		let bindTemplate = Handlebars.compile(itemDetailTemplate);

		let resultHTML = bindTemplate(info);
		targetArea.innerHTML = resultHTML;
	},

	makeItemPathArea(info) {
		let itemPathArea = document.querySelector("#detail_location");

		let itemPathTemplate = document.querySelector("#itemPathTemplate").innerText;
		let bindTemplate = Handlebars.compile(itemPathTemplate);

		let resultHTML = bindTemplate(info);
		itemPathArea.innerHTML = resultHTML;
	},

	preprocessComments(comments, productDescription) {
		for (comment of comments) {
			comment.reservationDate = comment.reservationDate.year + "." + comment.reservationDate.monthValue + "." + comment.reservationDate.dayOfMonth;
			comment.productDescription = productDescription;

			if (comment.commentImages.length === 0) {
				comment.commentImages = "none";
			} else {
				comment.commentImages = comment.commentImages[0].saveFileName;
			}
		}
	},
	preprocessItemDetailInfo() {
		let itemDetailInfo = {};
		itemDetailInfo.productContent = detailObj.detailData.displayInfo.productContent;
		itemDetailInfo.saveFileName = detailObj.detailData.displayInfoImage.saveFileName;

		return itemDetailInfo;
	},

	preprocessItemPathInfo() {
		let itemPathInfo = {};
		itemPathInfo.saveFileName = detailObj.detailData.displayInfoImage.saveFileName;
		itemPathInfo.placeDescription = detailObj.detailData.displayInfo.placeDescription;
		itemPathInfo.placeStreet = detailObj.detailData.displayInfo.placeStreet;
		itemPathInfo.placeLot = detailObj.detailData.displayInfo.placeLot;
		itemPathInfo.placeName = detailObj.detailData.displayInfo.placeName;
		itemPathInfo.telephone = detailObj.detailData.displayInfo.telephone;

		return itemPathInfo;
	}
};


let detailEvent = {
	imageWidth: 414,
	initDetailEvent() {
		document.querySelector("#bk_more_open").addEventListener("click", this.explainMoreEvent);
		document.querySelector("#bk_more_close").addEventListener("click", this.explainMoreEvent);

		document.querySelector(".btn_nxt").addEventListener("click", this.nextTitleImageEvent);
		document.querySelector(".btn_prev").addEventListener("click", this.prevTitleImageEvent);

		document.querySelector("#detailInfoTab").addEventListener("click", this.detailInfoEvent);
	},

	explainMoreEvent() {
		let openTag = document.querySelector("#bk_more_open");
		let closeTag = document.querySelector("#bk_more_close");
		let detailExplanationTag = document.querySelector("#detailExplanation");

		if (closeTag.style.display === "none") {
			closeTag.style.display = "block";
			openTag.style.display = "none";
			detailExplanationTag.className = "store_details";
		} else if (closeTag.style.display === "block") {
			closeTag.style.display = "none";
			openTag.style.display = "block";
			detailExplanationTag.className = "store_details close3";
		}
	},

	changeTabState(target) {
		for (let tab of target.parentNode.children) {
			tab.children[0].className = "anchor";
		}

		target.children[0].className = "anchor active";

		let detailInfoTag = document.querySelector("#detail_info");
		let detailLocationTag = document.querySelector("#detail_location");

		if (target.id === "itemDetail") {
			detailInfoTag.className = "detail_area_wrap";
			detailLocationTag.className = "detail_location hide";
		} else if (target.id === "itemPath") {
			detailInfoTag.className = "detail_area_wrap hide";
			detailLocationTag.className = "detail_location";
		}
	},

	nextTitleImageEvent() {
		let titleImageArea = document.querySelector(".visual_img.detail_swipe");
		let nowImageNumber = document.querySelector(".num").innerText;
		let totalImageNumber = document.querySelector(".num.off").querySelector("SPAN").innerText;

		if (nowImageNumber == 2) {
			titleImageArea.style.transition = "none";
			titleImageArea.style.left = "0px";
			setTimeout(() => {
				titleImageArea.style.transition = '0.5s ease-in';
				titleImageArea.style.left = (- detailEvent.imageWidth) + "px";
			}, 10);

			document.querySelector(".num").innerText = 1;
		} else if (nowImageNumber == 1) {
			titleImageArea.style.transition = '0.5s ease-in';
			titleImageArea.style.left = (parseInt(titleImageArea.style.left) - detailEvent.imageWidth) + "px";
			document.querySelector(".num").innerText = 2;
		}
	},

	prevTitleImageEvent() {
		let titleImageArea = document.querySelector(".visual_img.detail_swipe");
		let nowImageNumber = document.querySelector(".num").innerText;

		if (nowImageNumber == 1) {
			titleImageArea.style.transition = '0.5s ease-in';
			titleImageArea.style.left = (parseInt(titleImageArea.style.left) + detailEvent.imageWidth) + "px";

			document.querySelector(".num").innerText = 2;

		} else if (nowImageNumber == 2) {
			titleImageArea.style.transition = "none";
			titleImageArea.style.left = (-detailEvent.imageWidth * 2) + "px";
			setTimeout(() => {
				titleImageArea.style.transition = '0.5s ease-in';
				titleImageArea.style.left = (parseInt(titleImageArea.style.left) + detailEvent.imageWidth) + "px";
			}, 10);

			document.querySelector(".num").innerText = 1;
		}
	},

	detailInfoEvent() {
		let target = event.target.closest("LI");
		if (target === null) {
			return;
		}

		detailEvent.changeTabState(target);

		if (target.id === "itemDetail") {
			detailObj.makeItemDetailArea(detailObj.preprocessItemDetailInfo());
		} else if (target.id === "itemPath") {
			detailObj.makeItemPathArea(detailObj.preprocessItemPathInfo());
		}
	}
};
