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

sendAjaxGet("http://localhost:8080/ERS/allemp", display);

function display(xhr) {
	//var pform = JSON.parse(request.responseText);
    requests = JSON.parse(xhr.responseText);
   
	table = document.getElementById("mainTable1");
	
	/*for(var i = table.rows.length - 1; i > 0; i--)
	{
	    table.deleteRow(i);
	}*/
	
	for(let i in requests) {
        let newRow = document.createElement("tr");
			if(requests[i].employee_role_id==333)
				requests[i].employee_role_id="Employee";
			if(requests[i].employee_role_id==444)
				requests[i].employee_role_id="Manager";
        newRow.innerHTML =
        	`<td>${requests[i].employee_id}</td>
			<td>${requests[i].username}</td>
			<td>${requests[i].password}</td>
			<td>${requests[i].firstName}</td>
			<td>${requests[i].lastName}</td>
			<td>${requests[i].email}</td>
			<td>${requests[i].employee_role_id}</td>`;
        		
        	table.appendChild(newRow);
	}
}