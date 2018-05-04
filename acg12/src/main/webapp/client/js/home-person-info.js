/**
 * Created by Administrator on 2018/5/3.
 */
window.onload = function () {
    loaddingHeader();
    // console.log(window.location);
    // var pId = getQueryVariable("pId");

    queryPersonInfo(pId);
}


function queryPersonInfo(pId) {
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
                data = eval('(' + data + ')');
                console.log(data);
                var html = '';
                if (data.code == 20000) {
                    document.getElementsByClassName("cover")[0].innerHTML = "<img src= \""+ data.data.image +"\">";
                    document.getElementsByClassName("title")[0].innerHTML = "<a title="+ data.data.name+">"+ data.data.name +"</a>" + "<a class='name' title="+ data.data.nameCn+">"+ data.data.nameCn +"</a>";
                    document.getElementsByClassName("subtitle")[0].innerHTML = "<a> 职业："+ getType(data.data)+"</a>";
                    document.getElementsByClassName("status")[0].innerHTML = "<a>"+ getSubject(data.data)+"</a>";
                    document.getElementsByClassName("content")[0].innerHTML =data.data.summary;
                } else {

                }
            }
        }
    }

    xhr.open("GET", URL_PERSON_INFO + "?personId=" + pId, true);
    //3.发送请求，send方法，"a=6&b=9"
    xhr.send(null);
}

document.getElementById("expand").onclick = function () {
    var  container_centerA = document.getElementById("container-centerA");
    var  info_box = document.getElementById("info-box");
    // var item_time = document.getElementById("item-time");
    if(info_box.getAttribute("style").indexOf("0px") != -1){
        info_box.style.cssText = "height: 120px;";
        container_centerA.style.cssText = "height: 325px;";
        // item_time..remove("expand");
        // item_time.classLiclassListst.add("shrink");
    } else {
        // time_type.style.cssText = "max-height: 120px; height: 120px;";
        // item_time.classList.add("expand");
        // item_time.classList.remove("shrink");
    }
}

function getType(item) {
    var typeName = "";
    var type = item.type.split("、");
    for (var i = 0 ; i < type.length ; i++){
        if(i != 0){
            typeName += "、";
        }
        if(type[i] == 1){
            typeName += "声优";
        } else if(type[i] == 2){
            typeName += "漫画家";
        } else if(type[i] == 3){
            typeName += "制作人";
        } else if(type[i] == 4){
            typeName += "音乐人";
        } else if(type[i] == 6){
            typeName += "演员";
        } else if(type[i] == 7){
            typeName += "绘师";
        } else if(type[i] == 8){
            typeName += "作家";
        }
    }
    return typeName;
}

function getSubject(item) {
    var s1 = "";
    if(item.gender != 0){
        if(item.gender == 1){
            s1 += "性别 男"
        } else {
            s1 += "性别 女"
        }
    }
    if(item.birthday != null && item.birthday != ""){
        s1 += " / 生日 " +item.birthday;
    }
    if(item.bloodtype == 0){
        s1 += " / 血型 A"
    } else if(item.bloodtype == 1){
        s1 += " / 血型 B"
    } else if(item.bloodtype == 2){
        s1 += " / 血型 AB"
    } else if(item.bloodtype == 3){
        s1 += " / 血型 O"
    }
    return s1;
}