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

sendAjaxGet("http://localhost:8080/ERS/allpending", display);

function display(xhr) {
	//var pform = JSON.parse(request.responseText);
    requests = JSON.parse(xhr.responseText);
   console.log(requests);
	table = document.getElementById("mainTable1");
	
	for(var i = table.rows.length - 1; i > 0; i--)
	{
	    table.deleteRow(i);
	}
	
	for(let i in requests) {
        let newRow = document.createElement("tr");
        
        if(requests[i].resolved_date == null)
			requests[i].resolved_date = " ";
		
		if(requests[i].reimb_resolver_id == 0)
			requests[i].reimb_resolver_id = " ";
		if(requests[i].reimb_status_id==77)
			requests[i].reimb_status_id ="Pending";
		if(requests[i].reimb_status_id ==88)
			requests[i].reimb_status_id = "Approved"
		if(requests[i].reimb_status_id==99)
			requests[i].reimb_status_id ="Denied";
		if(requests[i].reimb_type_id==100)
			requests[i].reimb_type_id="Lodging"
		if(requests[i].reimb_type_id==101)
			requests[i].reimb_type_id="Travel"
		if(requests[i].reimb_type_id==102)
			requests[i].reimb_type_id="Food"
		if(requests[i].reimb_type_id==103)
			requests[i].reimb_type_id="Other"
		
        newRow.innerHTML =
        	`<td>${requests[i].reimb_id}</td>
			<td>${requests[i].reimb_amount}</td>
			<td>${requests[i].reimb_date}</td>
			<td>${requests[i].resolved_date}</td>
			<td>${requests[i].reimb_description}</td>
			<td>${requests[i].reimb_author_id}</td>
			<td>${requests[i].reimb_resolver_id}</td>
			<td>${requests[i].reimb_status_id}</td>
			<td>${requests[i].reimb_type_id}</td>
        
        <a class="btn btn-success" href='./../approve?id=${requests[i].reimb_id}'>Approve</a>               
        <a class="btn btn-danger" href='./../deny?id=${requests[i].reimb_id}'>Deny</a>`;
        	table.appendChild(newRow);
        	
       
	}
}