document.addEventListener("DOMContentLoaded", initDetailPage);

function initDetailPage() {
	detailObj.initDetail(detailObj.detailData);
	detailEvent.initDetailEvent();
}

let detailObj = {
	detailData: {},
	async initDetail(details) {

		this.detailData = await this.getDisplayInfoId(details);
		this.detailData = await this.getProductDetailData(this.detailData);
		this.detailData = await this.getEtcImages(this.detailData);
		this.makeUI(this.detailData);
	},

	getDisplayInfoId(detailData) {
		return new Promise(function(resolve, reject) {
			let tokens = location.href.split("/");
			detailData.displayInfoId = tokens[tokens.length - 1].replace("#", "");

			resolve(detailData);
		});
	},

	getProductDetailData(detailData) {
		let XHR = new XMLHttpRequest();

		return new Promise(function(resolve, reject) {
			XHR.addEventListener("load", function() {
				if (XHR.status == 200) {
					let details = JSON.parse(XHR.responseText);

					for (let key in details) {
						detailData[key] = details[key];
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
					let etcImages = JSON.parse(XHR.responseText);

					detailData.etcImages = etcImages;
					resolve(detailData);
				} else {
					alert("sorry. something failed");
					reject(new Error("sorry. something failed"));
				}
			});

			let url = "/api/products/" + detailData.displayInfoId + "/etcImages";

			XHR.open("GET", url);
			XHR.send();
		});
	},

	makeUI(detailData) {
		detailObj.makeTitleArea(detailData.displayInfo, detailData.productImages);
		detailObj.makeEtcImages(detailData.etcImages);
		detailObj.makeGradeArea(detailData.averageScore, detailData.totalCommentsCount);
		detailObj.makeCommentsArea(detailData.comments, detailData.displayInfo.productDescription);
		detailObj.makeDiscountArea(detailData.productPrices);
		detailObj.makeItemDetailArea(detailObj.preprocessItemDetailInfo());
		detailObj.makeItemPathArea(detailObj.preprocessItemPathInfo());

	},

	makeTitleArea(displayInfo, productImages) {
		let template = document.querySelector("#titleArea").innerText;
		let bindTemplate = Handlebars.compile(template);

		let resultHTML = bindTemplate(productImages[0]);

		let targetHTML = document.querySelector(".visual_img.detail_swipe");

		targetHTML.innerHTML = resultHTML;

		document.querySelector(".visual_txt_tit").innerHTML = displayInfo.productDescription;
		document.querySelector(".store_details .dsc").innerHTML = displayInfo.productContent;
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

	makeGradeArea(averageScore, totalCommentsCount) {
		let gradeArea = document.querySelector(".grade_area");
		let graphValue = document.querySelector(".graph_value");
		let gradeText = gradeArea.querySelector(".text_value").querySelector("span");
		let joinCount = document.querySelector(".join_count");

		graphValue.style.width = (100 * averageScore / 5) + "%";

		gradeText.innerText = averageScore;
		joinCount.children[0].innerText = totalCommentsCount + "건";
	},

	makeCommentsArea(comments, productDescription) {
		let commentArea = document.querySelector(".list_short_review");
		let template = document.querySelector("#commentArea").innerText;
		let bindTemplate = Handlebars.compile(template);

		detailObj.detailData.comments = detailObj.preprocessComments(comments, productDescription);

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

			commentArea.appendChild(newList);
		}
	},

	makeItemDetailArea(info) {
		let targetArea = document.querySelector("#detailInfo");

		let itemDetailTemplate = document.querySelector("#itemDetailTemplate").innerText;
		let bindTemplate = Handlebars.compile(itemDetailTemplate);

		let resultHTML = bindTemplate(info);
		targetArea.innerHTML = resultHTML;
	},

	makeItemPathArea(info) {
		let itemPathArea = document.querySelector("#detailLocation");

		let itemPathTemplate = document.querySelector("#itemPathTemplate").innerText;
		let bindTemplate = Handlebars.compile(itemPathTemplate);

		let resultHTML = bindTemplate(info);
		itemPathArea.innerHTML = resultHTML;
	},

	makeDiscountArea(productPrices) {
		let eventInfoArea = document.querySelector(".event_info .in_dsc");

		eventInfoArea.innerHTML = "[네이버예약 특별할인]</br>";

		let discountInfos = [];

		for (let product of productPrices) {
			if (product.discountRate == 0) {
				continue;
			}

			let discountInfo = product.priceTypeName + "석 " + product.discountRate + "%";
			discountInfos.push(discountInfo);
		}
		eventInfoArea.innerHTML += discountInfos.join(",") + " 할인";

		if (discountInfos.length === 0) {
			eventInfoArea.innerHTML = "해당 상품에는 할인 이벤트가 없습니다.";
		}
	},

	preprocessComments(comments, productDescription) {
		for (comment of comments) {
			let dateInfo = [comment.reservationDate.year, comment.reservationDate.monthValue, comment.reservationDate.dayOfMonth];
			comment.reservationDate = dateInfo.join(".");

			comment.productDescription = productDescription;
			comment.score = comment.score.toFixed(1);

			if (comment.commentImages.length === 0) {
				comment.commentImages = "none";
			} else {
				comment.commentImages = comment.commentImages[0].saveFileName;
			}
		}
		return comments;
	},

	preprocessItemDetailInfo() {
		let itemDetailInfo = {};
		itemDetailInfo.productContent = detailObj.detailData.displayInfo.productContent;

		return itemDetailInfo;
	},

	preprocessItemPathInfo() {
		let itemPathInfo = {};

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

		document.querySelector(".btn_review_more").addEventListener("click", this.moreCommentEvent);
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

		let detailInfoTag = document.querySelector("#detailInfo");
		let detailLocationTag = document.querySelector("#detailLocation");

		if (target.id === "itemDetail") {
			detailInfoTag.className = "detail_area_wrap";
			detailLocationTag.className = "detailLocation hide";
		} else if (target.id === "itemPath") {
			detailInfoTag.className = "detail_area_wrap hide";
			detailLocationTag.className = "detailLocation";
		}
	},

	nextTitleImageEvent() {
		let titleImageArea = document.querySelector(".visual_img.detail_swipe");
		let nowImageNumber = document.querySelector(".num").innerText;
		const totalImageNumber = 2;

		if (nowImageNumber == totalImageNumber) {
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
			document.querySelector(".num").innerText = totalImageNumber;
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
	},

	moreCommentEvent() {
		let target = event.target.closest("A");

		target.href += "/" + detailObj.detailData.displayInfoId;
	}
};
