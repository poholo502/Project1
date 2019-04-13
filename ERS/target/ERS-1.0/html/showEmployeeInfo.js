function showEmployeeInf(){
	var request = new XMLHttpRequest();
	request.opent("GET", "url", true);//need to change the url 
	request.send(); //this is how I make Ajax call

	if(xhttp.readyState == 4 && xhttp.status == 200){
		var response = request.reponse;
		document.write(response);
	}
}