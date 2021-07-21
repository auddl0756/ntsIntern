function updateState(id) {	
	var request =new XMLHttpRequest();
	
	request.addEventListener("load",function(){
		if (request.status === 200) {
    		// 이상 없음!
			console.log("ok");
		} else {
			alert("updated failed");
		}
	});
	
	var url  = "http://localhost:8080/todo/update?id="+id;
	
	request.open("GET", url,true);
	request.send();
}
