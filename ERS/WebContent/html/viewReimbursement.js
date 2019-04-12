
function viewPending(str){
	var request;
	if (str == "") {
	    document.getElementById("table_here").innerHTML = "";
	    return;
	  }//end of first if
	 request = new XMLHttpRequest();
	 request.open("GET", "http://localhost:8080/ERS/"+str,true);//need to change the url 
	request.send(); //this is how I make Ajax call
	request.onreadystatechange = function(){
		if (request.readyState == 4 && request.status == 200) {
			
			var pform = JSON.parse(request.responseText);
			console.log(pform);
			
			table = document.getElementById("mainTable1");
			
			//this delete the old data so new filter data could be display
			var table = document.getElementById("mainTable1");
			for(var i = table.rows.length - 1; i > 0; i--)
			{
			    table.deleteRow(i);
			}
			for(let i in pform){
			
				newRow = document.createElement("tr");
				
				if(pform[i].resolved_date == null)
					pform[i].resolved_date = " ";
				
				if(pform[i].reimb_resolver_id == 0)
					pform[i].reimb_resolver_id = " ";
				if(pform[i].reimb_status_id==77)
					pform[i].reimb_status_id ="Pending";
				if(pform[i].reimb_status_id ==88)
					pform[i].reimb_status_id = "Approved"
				if(pform[i].reimb_status_id==99)
					pform[i].reimb_status_id ="Denied";
				if(pform[i].reimb_type_id==100)
					pform[i].reimb_type_id="Lodging"
				if(pform[i].reimb_type_id==101)
					pform[i].reimb_type_id="Travel"
				if(pform[i].reimb_type_id==102)
					pform[i].reimb_type_id="Food"
				if(pform[i].reimb_type_id==103)
					pform[i].reimb_type_id="Other"
				
				newRow.innerHTML =
					
					`<td>${pform[i].reimb_id}</td>
					<td>${pform[i].reimb_amount}</td>
					<td>${pform[i].reimb_date}</td>
					<td>${pform[i].resolved_date}</td>
					<td>${pform[i].reimb_description}</td>
					<td>${pform[i].reimb_author_id}</td>
					<td>${pform[i].reimb_resolver_id}</td>
					<td>${pform[i].reimb_status_id}</td>
					<td>${pform[i].reimb_type_id}</td>`;
				
				table.appendChild(newRow);
				//table.insertBefore(document.getElementById("table_here"), newRow);
			}//end of for loop
			
		}//end of if
		
	}; //end of anyonomous function
	
}//end of function

function mViewPending(){
	let request = new XMLHttpRequest();
	request.open("GET", "http://localhost:8080/ERS/viewreimbursement");//need to change the url 
	request.send(); //this is how I make Ajax call
	
	request.onreadystatechange = function(){
		if (request.readyState == 4 && request.status == 200) {
			
			var pform = JSON.parse(request.responseText);
			
			table = document.getElementById("mainTable1");
			for(let i in pform){
				newRow = document.createElement("tr");
				
				newRow.innerHTML = 
					`<td>${pform[i].reimb_id}</td>
					<td>${pform[i].reimb_amount}</td>
					<td>${pform[i].reimb_date}</td>
					<td>${pform[i].reimb_resolved}</td>
					<td>${pform[i].reimb_description}</td>
					<td>${pform[i].reimb_author_id}</td>
					<td>${pform[i].reimb_resolver_id}</td>
					<td>${pform[i].reimb_status_id}: Pending</td>
					<td>${pform[i].reimb_type_id}</td><button type = "submit" id="approve">Approve</button><button type="submit" id="deny">Deny</button>`;
				
				table.appendChild(newRow);
				
				document.getElementById("aprrove").addEventListener("click", function(){
					
					let request = new XMLHttpRequest();
					request.open("POST", "http://localhost:8080/ERS/approve");//need to change the url 
					request.send("approve"); //this is how I make Ajax call
					
					request.onreadystatechange = function(){
						if (request.readyState > 3 && request.status == 200) {
							document.getElementById("aprrove").disabled = "true";
							document.getElementById("deny").disabled = "true";
						}
							
						}
				});
				
				document.getElementById("deny").addEventListener("click", function(){
					
					let request = new XMLHttpRequest();
					request.open("POST", "http://localhost:8080/ERS/deny");//need to change the url 
					request.send("deny"); //this is how I make Ajax call
					
					request.onreadystatechange = function(){
						if (request.readyState == 4 && request.status == 200) {
							document.getElementById("aprrove").disabled = "true";
							document.getElementById("deny").disabled = "true";
						}
							
						}
				});
				
			}
			
		}
	}
}