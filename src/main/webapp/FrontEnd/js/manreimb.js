
function closeForm() {
  document.getElementById("reimbform").style.display = "none";
}

function openForm() {
  document.getElementById("reimbform").style.display = "block";
}





async function asyncFetch(url, expression){
  const response = await fetch(url);
  const json = await response.json();
  expression(json);
}

function reimbType(num){
  switch (num){    
    case 0:
      return "Travel";
    case 1:
      return "Food";
    case 2:
      return "Equipment";
    case 3:
      return "Lodging";
    case 4:
      return "Relocation";
    case 5:
      return "Other";
    default:
      return "Other";  
  }
 }


function renderPendingTable(reimbs) {
  console.log("Rendering Pending Table");
  console.log(reimbs);
  for (const reimb of reimbs) {
    const tr = document.createElement("tr");
    const idTD = document.createElement("td");
    const uidTD = document.createElement("td");
    const amountTD = document.createElement("td");
    const submittedTD = document.createElement("td");
    const descriptionTD = document.createElement("td");
    const typeTD = document.createElement("td");
    idTD.innerText = reimb.reimbID;
    uidTD.innerText = reimb.authorID;
    amountTD.innerText = reimb.reimbAmount;
    reimb.submitted = new Date(reimb.submitted);
    reimb.submitted.toDateString()

    submittedTD.innerText = reimb.submitted;
    descriptionTD.innerText = reimb.description;
    typeTD.innerText = reimbType(reimb.typeID);
    tr.append(idTD, uidTD, amountTD, submittedTD, descriptionTD, typeTD);

    document.getElementById("pendingReimbursements").append(tr);
  }
}

function renderResolvedTable(reimbs) {
  console.log("Rendering Resolved Table");
  console.log(reimbs);
  for (const reimb of reimbs) {
    const tr = document.createElement("tr");
    const idTD = document.createElement("td");
    const uidTD = document.createElement("td");
    const amountTD = document.createElement("td");
    const submittedTD = document.createElement("td");
    const resolvedTD = document.createElement("td");
    const descriptionTD = document.createElement("td");
    const typeTD = document.createElement("td");
    const statusTD = document.createElement("td");
    const resolverTD = document.createElement("td");
    uidTD.innerText = reimb.authorID;
    idTD.innerText = reimb.reimbID;
    amountTD.innerText = reimb.reimbAmount;
    reimb.submitted = new Date(reimb.submitted);
    reimb.submitted.toDateString()
    reimb.resolved = new Date(reimb.resolved);
    reimb.resolved.toDateString()

    submittedTD.innerText = reimb.submitted;
    resolvedTD.innerText = reimb.resolved;
    descriptionTD.innerText = reimb.description;
    console.log(reimb.statusID);
    if(reimb.statusID == 2){
      statusTD.innerText = "Approved";
    }
    else{
      statusTD.innerText = "Denied";
    }
    resolverTD.innerText = reimb.resolverID;
    typeTD.innerText = reimbType(reimb.typeID);
    tr.append(idTD, uidTD, amountTD, submittedTD, resolvedTD, descriptionTD, typeTD, statusTD, resolverTD);

    document.getElementById("resolvedReimbursements").append(tr);
  }
}


asyncFetch("http://3.15.238.90:8081/Reimbursement/Pending.json", renderPendingTable);
asyncFetch("http://3.15.238.90:8081/Reimbursement/Resolved.json", renderResolvedTable);


 