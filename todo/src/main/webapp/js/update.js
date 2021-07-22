function updateState(id,type) {	
	var request =new XMLHttpRequest();
	
	request.addEventListener("load",function(){
		if (request.status === 200) {
			if(type==="TODO"){
				var todoListElement = document.getElementById("todoList_"+id);
				var doingElement = document.getElementById("doing").firstElementChild;
				
				doingElement.appendChild(todoListElement);
			}else if(type==="DOING"){
				
				var doingListElement = document.getElementById("doingList_"+id);
				var doneElement = document.getElementById("done").firstElementChild;
				var buttonElement = document.getElementById("doingListButton_"+id);
				
				doneElement.appendChild(doingListElement);
				buttonElement.remove();
			}
		} else {
			console.log("update failed");
		}
	});
	
	var url  = "http://localhost:8080/todo/update?id="+id+"&type="+type;
	
	request.open("GET", url,true);
	request.send();
}
