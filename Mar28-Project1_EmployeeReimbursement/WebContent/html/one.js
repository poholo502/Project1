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