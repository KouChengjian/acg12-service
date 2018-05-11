window.onload = function () {
    var k = getQueryVariable("k");
    console.log(k);
    if (k != false) {
        document.getElementById("query").value = decodeURI(k);
    }

    querySearchList(1);
}

function querySearchList(page) {
    var k = getQueryVariable("k");

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
                var o = eval('(' + data + ')');
                // console.log(data);
                var obj = eval('(' + o.data + ')');
                // console.log(obj.results);
                // console.log(obj.list);
                var html = '';
                if (o.code == 20000) {
                    for (var i = 0; i < obj.list.length; i++) {
                        var item = obj.list[i];
                        // console.log(item);
                        // console.log(item.images);
                        var h;
                        if(haslist){
                            h = " <li class=\"browserItem\" > <div class=\"list\"> <div class=\"left\"> <a> <img class=\"cover\" src=";
                            h += item.images == null ? "" : item.images.large + ">";
                            h += "</a> </div>";
                            h += "<div class=\"right\"> <h3>";
                            h += "<a href=\"//www.bilibili.com/video/av20642794/\" target=\"_blank\" title=" + item.name_cn + " class=\"t\">" + item.name_cn + "</a>";
                            h += "<small class=\"grey\">" + item.name + "</small>  </h3>";
                            h += "<div class=\"desc\">" + item.summary + "</div>";
                            h += "<p class=\"info\">" + item.air_date + "</p>  </div> </div> </li>";
                        } else {
                            h = " <li class=\"browserItem\" > <div class=\"grid\"> <div class=\"top\"> <a> <img class=\"cover\" src=";
                            h += item.images == null ? "" : item.images.large + ">";
                            h += "</a> </div>";
                            h += "<div class=\"botton\"> <h3>";
                            h += "<a href=\"//www.bilibili.com/video/av20642794/\" target=\"_blank\" title=" + item.name_cn + " class=\"t\">" + item.name_cn + "</a>";
                            h += "<small class=\"grey\">" + item.name + "</small>  </h3>";
                            h += "<div class=\"desc\">" + item.summary + "</div>";
                            h += "<p class=\"info\">" + item.air_date + "</p>  </div> </div> </li>";
                        }

                        html += h;
                    }
                    loaddingPagination(Math.ceil(obj.results / 10), page);
                    document.getElementById("page").classList.remove("page");
                } else {
                    html = "<div class=\"loaddingNull\">没有更多内容哦，请更换条词再次搜索。 </div>"
                    document.getElementById("page").classList.add("page");
                }
                document.getElementsByClassName('browserItemList')[0].innerHTML = html;
            }
        }
    }

    xhr.open("GET", "http://localhost:8080/acg12/api/search/subject?" + "key=" + k + "&start=" + 10 * (page - 1), true);
    //3.发送请求，send方法，"a=6&b=9"
    xhr.send(null);
}

function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return (false);
}

function loaddingPagination(num, cur) {
    $('.M-box3').pagination({
        pageCount: num,
        jump: true,
        coping: true,
        current: cur,
        homePage: '首页',
        endPage: '末页',
        prevContent: '上页',
        nextContent: '下页',
        callback: function (api) {
            console.log(api.getCurrent())
            querySearchList(api.getCurrent());
        }
    });
}


// $('.M-box3').pagination({
//     pageCount: 3,
//     jump: true,
//     coping: true,
//     homePage: '首页',
//     endPage: '末页',
//     prevContent: '上页',
//     nextContent: '下页',
//     callback: function (api) {
//         console.log(api.getCurrent())
//     }
// });

var haslist = true;

document.getElementById("full_selector").onclick = function () {
    haslist = true;
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
    haslist = false;
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