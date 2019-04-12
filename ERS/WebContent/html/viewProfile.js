function sendAjaxGet(url, func) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 200) {
            func(this);
        }
    }

    xhr.open("GET", url);
    xhr.send();
}

sendAjaxGet("http://localhost:8080/ERS/viewprofile", display);

function display(xhr) {
	//var pform = JSON.parse(request.responseText);
    requests = JSON.parse(xhr.responseText);
   
	table = document.getElementById("mainTable1");
	/*for(var i = table.rows.length - 1; i > 0; i--)
	{
	    table.deleteRow(i);
	}*/
	
	//for(let i in requests) {
        let newRow = document.createElement("tr");
        if(requests.employee_role_id==333)
			requests.employee_role_id="Employee";
		if(requests.employee_role_id==444)
			requests.employee_role_id="Manager";
		
        newRow.innerHTML =
        	`<td>${requests.employee_id}</td>
			<td>${requests.username}</td>
			<td>${requests.password}</td>
			<td>${requests.firstName}</td>
			<td>${requests.lastName}</td>
			<td>${requests.email}</td>
			<td>${requests.employee_role_id}</td>`;
        		
        	table.appendChild(newRow);
	//}
}