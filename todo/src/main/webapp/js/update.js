function updateState(id,type) {	
	var request =new XMLHttpRequest();
	
	request.addEventListener("load",function(){
		if (request.status === 200) {
			console.log("ok");
		} else {
			console.log("updated failed");
		}
		
		location.reload();
	});
	
	var url  = "http://localhost:8080/todo/update?id="+id+"&type="+type;
	
	request.open("GET", url,true);
	request.send();
}
