function getAllPrice(info){
    // var info = document.getElementById(id).value;
    // alert("code="+info);
    if (info == null || info === ""){
        alert("code 不可以为空哦！");
        return;
    }
    mySend("POST", "/Parking/html/SearchAllPrice.do", "code="+info, getAllPriceFunction);
}
function getAllPriceFunction() {
    var div = document.getElementById("Code");
    if (xmlHttp.readyState==4 && xmlHttp.status==200){
        var tableId = "priceTable";
        var info = xmlHttp.responseText;
        // div.innerHTML = info;
        showOperatorTable(tableId, info);
    }else if(xmlHttp.status==404){
        // div.innerHTML = "404: 未找到页面";
        alert("404: 未找到页面");
    }
}



function showOperatorTable(tableId, info){
    var parentNode=document.getElementById(tableId);
    while (parentNode.firstChild) {
        var oldNode = parentNode.removeChild(parentNode.firstChild);
        oldNode = null;
    }
    var Cookies = info.split(';');
    for(var i=0; i<Cookies.length-1; i++) {
        var c = Cookies[i].split("+");
        var para=document.createElement("tr");
        for(var j = 0; j < c.length; j++){
            var node=document.createElement("td");
            var text=document.createTextNode(c[j]);
            node.appendChild(text);
            para.appendChild(node);
        }

        var element=document.getElementById(tableId);
        element.appendChild(para);
    }
}

function deletePrice(typeid) {
//    alert(typeid);
    mySend("POST", "/Parking/html/DeletePrice.do", "typeid="+typeid, showInfoFunction);
}



function showInTable(tableId, info){
    var parentNode=document.getElementById(tableId);
    while (parentNode.firstChild) {
        var oldNode = parentNode.removeChild(parentNode.firstChild);
        oldNode = null;
    }
    var Cookies = info.split(';');
    for(var i=0; i<Cookies.length-1; i++) {
        var c = Cookies[i].split("+");
        var para=document.createElement("tr");
        for(var j = 0; j < c.length; j++){
            var node=document.createElement("td");
            var text=document.createTextNode(c[j]);
            node.appendChild(text);
            para.appendChild(node);
        }
        var element=document.getElementById(tableId);
        element.appendChild(para);
    }
}


function getAllParkingInfo(info){
    // var info = document.getElementById(id).value;
    // alert("code="+info);
    if (info == null || info === ""){
        alert("code 不可以为空哦！");
        return;
    }
    mySend("POST", "/Parking/html/SearchAllParkingInfo.do", "code="+info, getAllParkingInfoFunction);
}
function getAllParkingInfoFunction() {
    var div = document.getElementById("Code");
    if (xmlHttp.readyState==4 && xmlHttp.status==200){
        var tableId = "parkingInfoTable";
        var info = xmlHttp.responseText;
        // div.innerHTML = info;
        showInTable(tableId, info);
    }else if(xmlHttp.status==404){
        // div.innerHTML = "404: 未找到页面";
        alert("404: 未找到页面");
    }
}


function getAllCardInfo(info){
    // var info = document.getElementById(id).value;
    // alert("code="+info);
    if (info == null || info === ""){
        alert("code 不可以为空哦！");
        return;
    }
    mySend("POST", "/Parking/html/SeatchAllCardInfo.do", "code="+info, getAllCardInfoFunction);
}
function getAllCardInfoFunction() {
    var div = document.getElementById("Code");
    if (xmlHttp.readyState==4 && xmlHttp.status==200){
        var tableId = "cardInfoTable";
        var info = xmlHttp.responseText;
        // div.innerHTML = info;
        showInModifyTable(tableId, info);
    }else if(xmlHttp.status==404){
        // div.innerHTML = "404: 未找到页面";
        alert("404: 未找到页面");
    }
}


function showInModifyTable(tableId, info){
    var parentNode=document.getElementById(tableId);
    while (parentNode.firstChild) {
        var oldNode = parentNode.removeChild(parentNode.firstChild);
        oldNode = null;
    }
    var Cookies = info.split(';');
    console.log(Cookies[1]);
    for(var i=0; i<Cookies.length-1; i++) {
        var c = Cookies[i].split("+");
        var para=document.createElement("tr");
        for(var j = 0; j < c.length; j++){
        	
            var node=document.createElement("td");
            var text=document.createTextNode(c[j]);
            node.appendChild(text);
            para.appendChild(node);
        }
        
        var node=document.createElement("td");
        var delNode = document.createElement("a");
        var time = c[5].split(" ");
        delNode.innerHTML = "<i style='color: blue' onclick=modifyCardInfo('"+ c[0]+"','"+c[1]+"','"+c[2]+"','"+c[3]+"','"+c[4]+"','"+time[0]+"+"+time[1]+"')> 修改</i>";
        node.appendChild(delNode);
        para.appendChild(node);
        
        var element=document.getElementById(tableId);
        element.appendChild(para);
    }
}


function modifyCardInfo(id, name, telnum, password, cid, endtime){
//	alert("id="+id+"&name="+name+"&telnum="+telnum+"&password="+password+"&cid="+cid+"&endtime="+endtime);
	var tmp = document.getElementById("u_id");
	tmp.value = id;
	tmp = document.getElementById("u_name");
	tmp.value = name;
	tmp = document.getElementById("u_telnum");
	tmp.value = telnum;
	tmp = document.getElementById("u_password");
	tmp.value = password;
	tmp = document.getElementById("u_cid");
	tmp.value = cid;
	tmp = document.getElementById("u_endtime");
	var time = endtime.split("+");
	tmp.value = time[0];
}

function modify(id, name, telnum, password, cid, endtime){
	alert("id="+id+"&name="+name+"&telnum="+telnum+"&password="+password+"&cid="+cid+"&endtime="+endtime);

    if (id == null || id === ""){
        alert("typeid 不可以为空哦！");
        return;
    }
    if (name == null || name === ""){
        alert("typename 不可以为空哦！");
        return;
    }
    if (password == null || password === ""){
        alert("perprice 不可以为空哦！");
        return;
    }
    if (telnum == null || telnum === ""){
        alert("typeid 不可以为空哦！");
        return;
    }
    if (cid == null || cid === ""){
        alert("typename 不可以为空哦！");
        return;
    }
    if (endtime == null || endtime === ""){
        alert("perprice 不可以为空哦！");
        return;
    }
//    alert("typeid="+typeid+"&typename="+typename+"perprice="+perprice);
    mySend("POST", "/Parking/html/Modify.do", "id="+id+"&name="+name+"&telnum="+telnum+"&password="+password+"&cid="+cid+"&endtime="+endtime, showInfoFunction);
    
}

function addPrice(typeid, typename, perprice) {
    if (typeid == null || typeid === ""){
        alert("typeid 不可以为空哦！");
        return;
    }
    if (typename == null || typename === ""){
        alert("typename 不可以为空哦！");
        return;
    }
    if (perprice == null || perprice === ""){
        alert("perprice 不可以为空哦！");
        return;
    }
//    alert("typeid="+typeid+"&typename="+typename+"perprice="+perprice);
    mySend("POST", "/Parking/html/AddPrice.do", "typeid="+typeid+"&typename="+typename+"&perprice="+perprice, showInfoFunction);
}

function showInfoFunction() {
    if (xmlHttp.readyState==4 && xmlHttp.status==200){
        var info = xmlHttp.responseText;
        // div.innerHTML = info;
        alert(info);
        getAllCardInfo("a");
        getAllCardInfoFunction("b");
        getAllPrice("c");
    }else if(xmlHttp.status==404){
        // div.innerHTML = "404: 未找到页面";
        alert("404: 未找到页面");
    }
}


function searchInfo(cid, inttime, outtime) {
//    alert("cid="+cid+"&inttime="+inttime+"&outtime="+outtime);
    mySend("POST", "/Parking/html/SearchInfo.do", "cid="+cid+"&inttime="+inttime+"&outtime="+outtime, SearchInfoFunction);
}

function SearchInfoFunction() {
    if (xmlHttp.readyState==4 && xmlHttp.status==200){
        var tableId = "parkingInfoTable";
        var info = xmlHttp.responseText;
        // div.innerHTML = info;
        showInTable(tableId, info);
    }else if(xmlHttp.status==404){
        // div.innerHTML = "404: 未找到页面";
        alert("404: 未找到页面");
    }
}