function fnSendTopic(){
    var topic1 = document.getElementById("topic1").value;
    var topic2 = document.getElementById("topic1").value;
    var topic3 = document.getElementById("topic1").value;
    var topic4 = document.getElementById("topic1").value;
    var xmlhttp = getXMLHttpRequestObject();
    var url = "getTweets?topic1=" + topic1 + "&topic2=" + topic2 + "&topic3=" + topic3 + "&topic4=" + topic4;
    xmlhttp.open("POST",url, true);
    xmlhttp.send(null);
    xmlhttp.onreadystatechange=function(){
      if (xmlhttp.readyState==4 && xmlhttp.status==200){
        alert(xmlhttp.responseText);
      }
    }
}


function getXMLHttpRequestObject(){
    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp=new XMLHttpRequest();
    }else{// code for IE6, IE5
      xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}