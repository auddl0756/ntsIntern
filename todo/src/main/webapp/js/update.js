var updateButtonList = document.querySelectorAll(".updateButton");

for (button of updateButtonList) {
	button.addEventListener("click", function(event) {
		var buttonId = event.target.id;

		var idx = buttonId.indexOf("_") + 1;
		var id = buttonId.substring(idx, buttonId.length);
		var type = "TODO";
		if (buttonId.substr(0, 5) === "doing") {
			type = "DOING";
		}

		updateState(id, type);
	});
}


function updateState(id, type) {
	var request = new XMLHttpRequest();
	request.addEventListener("load", function() {

		if (request.status === 200) {
			var todoListElement = document.getElementById("todoList_" + id);
			var doingListElement = document.getElementById("doingList_" + id);

			if (todoListElement !== null) {
				var doingElement = document.getElementById("doing-nav");
				var buttonElement = document.getElementById("todoListButton_" + id);

				doingElement.appendChild(todoListElement);
				buttonElement.id = "doingListButton_" + id;
				todoListElement.id = "doingList_" + id;

			} else if (doingListElement !== null) {
				var doneElement = document.getElementById("done-nav")
				var buttonElement = document.getElementById("doingListButton_" + id);

				doneElement.appendChild(doingListElement);
				doingListElement.id = "doneList_" + id;

				buttonElement.remove();

				if (type === "TODO") {
					updateState(id, "DOING");
				}
			}
		} else {
			alert("update failed");
		}
	});

	var url = "/todo/update";
	var updateInfo = {
		id: id,
		type: type
	};

	request.open("POST", url);
	request.setRequestHeader('Content-Type', 'application/json;');
	request.send(JSON.stringify(updateInfo));
}
