$(function(){
	//得到焦点
	$("#password").focus(function(){
		$("#left_hand").animate({
			left: "150",
			top: " -38"
		},{step: function(){
			if(parseInt($("#left_hand").css("left"))>140){
				$("#left_hand").attr("class","left_hand");
			}
		}}, 2000);
		
		$("#right_hand").animate({
			right: "-64",
			top: "-38px"
		},{step: function(){
			if(parseInt($("#right_hand").css("right"))> -70){
				$("#right_hand").attr("class","right_hand");
			}
		}}, 2000);
	});

	//失去焦点
	$("#password").blur(function(){
		$("#left_hand").attr("class","initial_left_hand");
		$("#left_hand").attr("style","left:100px;top:-12px;");
		$("#right_hand").attr("class","initial_right_hand");
		$("#right_hand").attr("style","right:-112px;top:-12px");
	});
	
	
	$("#login").click(function () {
		var username = document.getElementById('username').value;
		var password = document.getElementById('password').value;
        if(username == ''){
            alert('用户名不能为空');
            return false;
        }
        if(password == ''){
            alert('密码不能为空');
            return false;
        }
        var xmlobj; //定义XMLHttpRequest对象
        if(window.ActiveXObject){
        	//如果当前浏览器支持Active Xobject，则创建ActiveXObject对象
        	//xmlobj = new ActiveXObject("Microsoft.XMLHTTP");
        	try {
        	    xmlobj = new ActiveXObject("Msxml2.XMLHTTP");
        	} catch (e) {
        	    try {
        	       xmlobj = new ActiveXObject("Microsoft.XMLHTTP");
        	    } catch (E) {
        	          xmlobj = false;
        	    }
        	 }
        }else if(window.XMLHttpRequest){
        	//如果当前浏览器支持XMLHttp Request，则创建XMLHttpRequest对象
        	xmlobj = new XMLHttpRequest();
        }
        
        xmlobj.onreadystatechange = function() {

    		if(xhr.readyState == 4) {
    			if(xhr.status == 200) {
    				alert(xhr.responseText);
    				//var data = JSON.parse(xhr.responseText);
    				//result.innerHTML = '';
    				//result.style.textAlign = 'left';
    				//data.forEach(function(e, i, a) {
    				//	result.innerHTML += '<p><a href="' + e + '">' + e + '</a></p>';
    				//});
    			} else {
    				//result.style.textAlign = 'center';
    				//result.innerHTML = 'Σ( ° △ °|||';
    			}s
    		}
    	}
        
        var parm = "username=" + username + "&password=" + password;//构造URL参数
        xmlobj.open("POST", "http://localhost:8080/acg12/admin/login", true); //调用weather.php
        xmlobj.setRequestHeader("cache-control","no-cache");
        xmlobj.setRequestHeader("contentType","text/html;charset=uft-8") //指定发送的编码
        xmlobj.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");  //设置请求头信息
        xmlobj.send(parm);
    });
	
	$("#reg").click(function () {
		alert('全力开发中...');
	});
	
    $("#resetpwd").click(function () {
    	alert('全力开发中...');
	});
    
   
    
    function SubmitArticle(xmlobj , parm) {
      //var parm = "act=firstweather" ;//构造URL参数
      //antique = escape(antique);
      //var parm = "act=" + act + "&cityname=" + cityname + "&antique=" + antique;//构造URL参数
      //xmlobj.open("POST", "{dede:global.cfg_templeturl/}/../include/weather.php", true); //调用weather.php
      xmlobj.open("POST", "/weather/include/weather.php", true); //调用weather.php
      xmlobj.setRequestHeader("cache-control","no-cache");
      xmlobj.setRequestHeader("contentType","text/html;charset=uft-8") //指定发送的编码
      xmlobj.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");  //设置请求头信息
      xmlobj.onreadystatechange = StatHandler;  //判断URL调用的状态值并处理
      xmlobj.send(parm); //设置为发送给服务器数据
    }
});

