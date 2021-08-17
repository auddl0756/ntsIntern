document.addEventListener("DOMContentLoaded", initReviewPage);

function initReviewPage() {
	reviewObj.initReview(reviewObj.reviewData);
}

let reviewObj = {
	reviewData: {},
	async initReview(reviews) {

		this.reviewData = await this.getDisplayInfoId(reviews);
		this.reviewData = await this.getAllReviews(this.reviewData);

		this.makeUI(this.reviewData);
	},

	getDisplayInfoId(reviewData) {
		let tokens = location.href.split("/");
		reviewData.displayInfoId = tokens[tokens.length - 1].replace("#", "");

		return reviewData;
	},

	getAllReviews(reviewData) {
		let XHR = new XMLHttpRequest();
		return new Promise(function(resolve, reject) {
			XHR.addEventListener("load", function() {
				if (XHR.status == 200) {
					let reviews = JSON.parse(XHR.responseText);

					for (let key in reviews) {
						reviewData[key] = reviews[key];
					}

					resolve(reviewData);

				} else {
					alert("sorry. something failed");
					reject(new Error("sorry. something failed"));
				}
			});

			let url = "/api/products/all/" + reviewData.displayInfoId;

			XHR.open("GET", url);
			XHR.send();
		});
	},

	makeUI(reviewData) {
		document.querySelector(".title").innerText = reviewData.displayInfo.placeName;
		this.makeBackUrl(reviewData.displayInfoId);
		this.makeGradeArea(reviewData.averageScore, reviewData.comments.length);
		this.makeCommentArea(reviewData.comments, reviewData.displayInfo.productDescription);

	},

	makeBackUrl(id) {
		let backButton = document.querySelector(".btn_back");
		backButton.setAttribute("href", backButton.getAttribute("href") + "/" + id);
	},

	makeGradeArea(averageScore, commentCount) {
		let gradeArea = document.querySelector(".grade_area");
		let graphValue = document.querySelector(".graph_value");
		let gradeText = gradeArea.querySelector(".text_value").querySelector("span");
		let joinCount = document.querySelector(".join_count");

		graphValue.style.width = (100 * averageScore / 5) + "%";
		gradeText.innerText = averageScore;
		joinCount.children[0].innerText = commentCount + "건";
	},

	makeCommentArea(comments, productDescription) {
		let template = document.querySelector("#commentArea").innerText;
		let bindTemplate = Handlebars.compile(template);
		let targetHTML = document.querySelector(".list_short_review");

		reviewObj.reviewData.comments = reviewObj.preprocessComments(comments, productDescription);

		for (let comment of comments) {
			let newList = document.createElement("li");
			newList.classList.add("list_item");
			newList.innerHTML = bindTemplate(comment);

			if (comment.commentImages === "none") {
				newList.querySelector(".thumb_area").style.display = "none";
			}

			targetHTML.appendChild(newList);
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
	}
};