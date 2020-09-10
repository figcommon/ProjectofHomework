/**
 * 初始化XMLHttp请求对象
 * create by qzl on 2019.3.30 9:23
 * @returns {xmlHttp/null}
 */
function initXMLHttp() {
    var xmlHttp;
    try {
        //Firefox, Opera 8.0+, Safari
        xmlHttp = new XMLHttpRequest();
    }catch (e) {
        try {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        }catch (e) {
            try {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }catch (e) {
                return null;
            }
        }
    }
    //alert("Ajax技术已启动！");
    return xmlHttp;
}

/**
 * 同步发送get
 * create by qzl on 2019.3.30 9:27
 * @param url
 * @param keyValue
 * @param ElementId
 */

//同步get发送
function get(url, keyValue, ElementId) {
    var xmlHttp = initXMLHttp();
    if (xmlHttp == null){
        alert("您的浏览器不支持AJAX技术！");
        return;
    }

    //GET同步发送请求报文
    alert(url+"?"+keyValue)
    xmlHttp.open("GET", url+"?"+keyValue, false);
    xmlHttp.send();


    //接收响应
    var txtHint = xmlHttp.responseText;
    document.getElementById(ElementId).innerHTML = txtHint;
}

//同步post发送
function post(url, keyValue, ElementId) {
    var xmlHttp = initXMLHttp();
    if (xmlHttp == null){
        alert("您的浏览器不支持AJAX技术！");
        return;
    }

    //POST同步发送请求报文
    xmlHttp.open("POST", url, false);
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.send(keyValue);

    //接收响应
    var txtHint = xmlHttp.responseText;
    document.getElementById(ElementId).innerHTML = txtHint;
}



/**
 * create by qzl on 2019.4.3 17:17
 * 用于异步传输的两个对外接口
 * synGet和synPost发送方法
 */
//请求函数和回调函数共有的一个变量
//不能作为回调函数的参数传入，否则回调函数的xmlHttp。readyState始终等于0
var xmlHttp;
var id;
//异步get发送
function synGet(url, keyValue, callback) {
    xmlHttp = initXMLHttp();
    if (xmlHttp == null){
        alert("您的浏览器不支持AJAX技术！");
        return;
    }
    //GET同步发送请求报文
    xmlHttp.open("GET", url+"?"+keyValue, true);
    xmlHttp.send();

    //注册回调函数,处理响应
    xmlHttp.onreadystatechange = callback;
}

//异步post发送
function synPost(url, keyValue, callback) {
    xmlHttp = initXMLHttp();
    if (xmlHttp == null){
        alert("您的浏览器不支持AJAX技术！");
        return;
    }
    //POST异步发送请求报文
    xmlHttp.open("POST", url, true);
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.send(keyValue);

    //定义回调函数,处理响应
    xmlHttp.onreadystatechange = callback;
}


/**
 * create by qzl on 2019.4.3 17:35
 * 封装发送请求
 * 回调函数可以直接用xmlHttp得到返回结果
 */
function mySend(reqMethod, url, keyValue, callback) {
    if (reqMethod === "Get" || reqMethod === "get" || reqMethod === "GET"){
        synGet(url, keyValue, callback);
    } 
    else if (reqMethod === "Post" || reqMethod === "post" || reqMethod === "POST"){
        synPost(url, keyValue, callback);
    }
    else{
    	alert(reqMethod);
        alert('无此请求！');
    }
}


//回调函数 示例
function Acallback() {
    try {
        var div = document.getElementById('txtHint');
        alert("callbackABC:"+xmlHttp.readyState);
        if(xmlHttp.readyState == 0){
            div.innerHTML= div.innerHTML + "0: 请求未初始化";
        }
        else if(xmlHttp.readyState == 1){
            div.innerHTML= div.innerHTML + "1: 服务器连接已建立";
        }
        else if(xmlHttp.readyState == 2){
            div.innerHTML= div.innerHTML + "2: 请求已接收";
        }
        else if(xmlHttp.readyState == 3){
            div.innerHTML= div.innerHTML + "3: 请求处理中";
        }
        else if(xmlHttp.readyState == 4){
            div.innerHTML= div.innerHTML + "4: 请求已完成，且响应已就绪";
        }

        if (xmlHttp.readyState==4 && xmlHttp.status==200){
            div.innerHTML = div.innerHTML + xmlHttp.responseText;
        }else if(xmlHttp.status==404){
            div.innerHTML = div.innerHTML + "404: 未找到页面";
        }
    }catch (e) {
        alert(e);
    }

}

