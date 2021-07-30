let clickedCategory = 0;

function makeTemplateCategory(targetHTML,data){
    let htmlTemplate = document.querySelector("#itemList").innerHTML;
    let resultHTML =targetHTML;
	
	let htmlLocation="left";
	let leftBox= targetHTML.children[0];
	let rightBox= targetHTML.children[1];
	console.log(leftBox);
	console.log(rightBox);
	
	let leftHTML="";
	let rightHTML="";
	
    for(let elem of data){
        let hereHTML = htmlTemplate;
        for(let iter=0;iter<2;iter++){
            for(let key in elem){
                hereHTML=hereHTML.replace("${"+key+"}",elem[key]);
            }
        }
    	
		if(htmlLocation==="left"){
			leftHTML+=hereHTML;
			htmlLocation ="right";
		}else{
			rightHTML+=hereHTML;
			htmlLocation ="left";
		} 
    }

	console.log(leftHTML);
	console.log(rightHTML);
    
	leftBox.innerHTML=leftHTML;
	rightBox.innerHTML=rightHTML; 
}

function requestContents(url) {
	let XHR = new XMLHttpRequest();
	XHR.addEventListener("load", function() {
		if (XHR.status == 200) {
			
			let data = JSON.parse(XHR.responseText);
			console.log(data);
			
			let targetHTML = document.querySelector(".wrap_event_box");
			
			makeTemplateCategory(targetHTML,data);
			
		} else {
			alert("sorry. something failed");
		}
	});
	
	url+="/"+clickedCategory+"?type=th";
	XHR.open("GET", url);
	XHR.send();
}

let ulTag = document.querySelector(".section_event_tab .event_tab_lst");

ulTag.addEventListener("click", function(event) {
	//ajax통신할 때 cache 필요할 것.

	let target = event.target;
	let liTag = target;

	if (target.tagName === "SPAN") {
		liTag = target.parentNode.parentNode;
	} else if (target.tagName === "A") {
		liTag = target.parentNode;
	} else if (target.tagName === "UL") {
		return;
	}

	let before = ulTag.children[clickedCategory].children[0].children[0];
	before.style.color = "black";
	before.style.fontWeight = "normal";

	clickedCategory = parseInt(liTag.getAttribute('data-category'));
	 
	let after = ulTag.children[clickedCategory].children[0].children[0];
	after.style.color = "#00c73c";
	after.style.fontWeight = "bold";

	//requestContents("/sampleJson.json");
	requestContents("/api/productImages");
	
});