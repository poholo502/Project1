function validate(){
	var email = document.getElementById("eample");
	if( typeof(email) != "undefined" ){
		if(!email.contains("@")){
			document.getElementById("error").innerHTML = "Invalid email";
			document.getElementById("error").style.color = "red";
			return false;
		}
	}
}

function showEmployeeInf(){
	var request = new XMLHttpRequest();
	request.opent("GET", "http://localhost:8080/ERS/html/employeeInfo", true);
	request.send();
	

	request.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			var response = request.reponse;
			document.write(response);
		}
	}
}

function mydate(){
	document.getElementById("date").hidden=false;
	//document.getElementById("ndate").hidden=true; //if they choose to write
}

function mydate1(){
	d = new Date(document.getElementById("date").value);
	date = d.getDate();
	month = d.getMonth();
	month++;
	year = d.getFullYear();
	document.getElementById("date").hidden = false;
}