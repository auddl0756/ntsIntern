document.addEventListener("DOMContentLoaded", initReviewPage);

function initReviewPage() {
	reviewObj.initReview(reviewObj.reviewData);
}

let reviewObj = {
	reviewData: {},
	initReview(reviews) {

		this.getDisplayInfoId(reviews)
			.then(function(result) {
				return reviewObj.getAllReviews(result);
			})
			.then(function(result) {
				return reviewObj.makeUI(result);
			});
	},

	getDisplayInfoId(reviewData) {
		return new Promise(function(resolve, reject) {
			let tokens = location.href.split("/");
			reviewData.displayInfoId = tokens[tokens.length - 1];

			resolve(reviewData);
		});
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

		reviewObj.preprocessComments(comments, productDescription);

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
			comment.reservationDate = comment.reservationDate.year + "." + comment.reservationDate.monthValue + "." + comment.reservationDate.dayOfMonth;
			comment.productDescription = productDescription;

			if (comment.commentImages.length === 0) {
				comment.commentImages = "none";
			} else {
				comment.commentImages = comment.commentImages[0].saveFileName;
			}
		}
	}
};