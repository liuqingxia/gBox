var queryid;
var querytype;
var url;
var xmlhttp;
var root = "/trace-data/eagle/queryparams.do";

function datashow(){
	formvalues();
	var params = "?queryid="+queryid+"&querytype="+querytype; 
	url = root + params;
	loadXMLDoc(url);
}

function formvalues(){
	var select = document.getElementById("querytype"); 
	var index = select.selectedIndex;  
	querytype = select.options[index].value;
	var input = document.getElementById("queryid");
	queryid = input.value;
}

function loadXMLDoc(url){
	if (window.XMLHttpRequest){
		xmlhttp=new XMLHttpRequest();
	}else{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.onreadystatechange=function(){
		if (xmlhttp.readyState==4 && xmlhttp.status==200){
			if(xmlhttp.responseText){
				var efms = eval("(" + xmlhttp.responseText + ")");
				var datademo = document.getElementById("datademo");
				for(var i = 0; i < efms.length; i++){
					var efm = efms[i];
					var tr = document.createElement("tr");
					tr.setAttribute("style", "font-size:18px");
					if(i % 2 == 0){
						tr.setAttribute("bgcolor","#FFEFD5");
					}else{
						tr.setAttribute("bgcolor","#FFFFFF");
					}
					addtd(tr, efm);
					datademo.appendChild(tr);
				}
			}else{
				document.getElementById("msg").innerHTML='<h4><font color="red">No data is available</font></h4>';
			}
		}
	};

	xmlhttp.open("GET",url,true);
	xmlhttp.send();
}

function addtd(tr, efm){
	var td1 = document.createElement("td");
	var values = document.createTextNode(efm.originMessageID);
	td1.appendChild(values);
	tr.appendChild(td1);
	
	
	var td2 = document.createElement("td");
	values = document.createTextNode(efm.traceID);
	td2.appendChild(values);
	tr.appendChild(td2);
	
	var td3 = document.createElement("td");
	values = document.createTextNode(efm.serviceID);
	td3.appendChild(values);
	tr.appendChild(td3);
	
	var td4 = document.createElement("td");
	values = document.createTextNode(efm.status);
	td4.appendChild(values);
	tr.appendChild(td4);
	
	var td5 = document.createElement("td");
	values = document.createTextNode(efm.realQID);
	td5.appendChild(values);
	tr.appendChild(td5);
	
	var td6 = document.createElement("td");
	values = document.createTextNode(efm.serviceName);
	td6.appendChild(values);
	tr.appendChild(td6);
	
	var td7 = document.createElement("td");
	values = document.createTextNode(efm.messageID);
	td7.appendChild(values);
	tr.appendChild(td7);
}




	




