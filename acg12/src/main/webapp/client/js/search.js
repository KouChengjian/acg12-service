window.onload = function () {
    console.log(getQueryVariable("k"));


    var xhr;
    if (window.XMLHttpRequest) {
        // code for IE7+, Firefox, Chrome, Opera, Safari
        xhr = new XMLHttpRequest();
    } else {
        // code for IE6, IE5
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }
     //alert(xhr);
    //2.请求方式，GET,POST，URL，TRUE
    //要查看状态的值，0，刚创建AJAX对象1,已经调用了OPEP方法，2=已经调用SEND方法3,
    //4-AJAX请求完成，数据返回完整
    //设置AJAX设置事件
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {  //状态等于4的时候返回值
            //console.log(xhr.responseText);
            //onreadystatechange  事件
            if (xhr.status == 200 || xhr.status == 304) {
                var data = xhr.responseText;
                // alert(data);
                console.log(data);
            }
        }
    }

    xhr.open("GET","http://localhost:8080/acg12/res/index",true);
    //3.发送请求，send方法，"a=6&b=9"
    xhr.send(null);
}

function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}

document.getElementById("full_selector").onclick = function () {
    // alert("hello world!");
    var arr = document.getElementsByClassName("full_selector");
    arr[0].classList.add("active");

    var arr = document.getElementsByClassName("grid_selector");
    arr[0].classList.remove("active");

    var arr = document.getElementsByClassName("browserItem");
    for (var i = 0; i < arr.length; i++) {
        arr[i].getElementsByClassName("grid")[0].classList.add("list");
        arr[i].getElementsByClassName("grid")[0].classList.remove("grid");
        arr[i].getElementsByClassName("top")[0].classList.add("left");
        arr[i].getElementsByClassName("top")[0].classList.remove("top");
        arr[i].getElementsByClassName("botton")[0].classList.add("right");
        arr[i].getElementsByClassName("botton")[0].classList.remove("botton");
    }
}

document.getElementById("grid_selector").onclick = function () {
    var arr = document.getElementsByClassName("full_selector");
    arr[0].classList.remove("active");

    var arr = document.getElementsByClassName("grid_selector");
    arr[0].classList.add("active");

    var arr = document.getElementsByClassName("browserItem");
    for (var i = 0; i < arr.length; i++) {
        // var c = arr[i].getElementsByClassName("list")[0].classList.add("grid");
        // console.log(c);
        arr[i].getElementsByClassName("list")[0].classList.add("grid");
        arr[i].getElementsByClassName("list")[0].classList.remove("list");
        arr[i].getElementsByClassName("left")[0].classList.add("top");
        arr[i].getElementsByClassName("left")[0].classList.remove("left");
        arr[i].getElementsByClassName("right")[0].classList.add("botton");
        arr[i].getElementsByClassName("right")[0].classList.remove("right");
    }
}
