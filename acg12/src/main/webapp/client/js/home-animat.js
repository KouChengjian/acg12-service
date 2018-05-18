/**
 * Created by Administrator on 2018/4/27.
 */
var typeName_ = 0, year_ = 0, month_ = 0, status_ = 0;
var type = 2, typeName = 0, year = 0, month = 0, status = "", page = 1;
window.onload = function () {
    loaddingHeader();
    chooseTypeClickListener();
    querySearchList(type, typeName, year, month, status, page);
}

function querySearchList(type, typeName, year, month, status, page) {
    var xhr;
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {  //状态等于4的时候返回值
            if (xhr.status == 200 || xhr.status == 304) {
                var data = eval('(' + xhr.responseText + ')');
                // console.log(data)
                var html = '';
                if (data.code == 20000) {
                    var html = '';
                    for (var i = 0, total = data.data.list.length; i < total; i++) {
                        html += paddingHtml(data.data.list[i]);
                    }
                    var pagehtml = document.getElementById("page");
                    pagehtml.style.cssText="display:inline;";
                    loaddingPagination(Math.ceil(data.data.totalResult / 20), page);
                } else {
                    var pagehtml = document.getElementById("page");
                    pagehtml.style.cssText="display:none;";
                    html = "<div class=\"loaddingNull\">没有更多内容哦，请更换条词再次搜索。 </div>"
                }
                document.getElementsByClassName('browser-list')[0].innerHTML = html;
            }
        }
    }
    xhr.open("GET", URL_SUBJECT + "?type=" + type + "&typeName=" + typeName + "&year=" + year + "&month=" + month + "&status=" + status + "&page=" + page, true);
    xhr.send(null);
}

function paddingHtml(item) {
    var h = "<div class=\"list-item\">";
    h+="<div class=\"preview\">"
    h += "<div class=\"cover\">";
    h+="<a href=" + LINK_SUBJECT + item.sId + " target=\"_blank\" title="+ item.name+">";
    h+= "<img  src="+ item.image +" alt=" + item.name +">";
    h+="<div class=\"shadow\">"   +"<span class=\"sort-info\">"+ getEpsCount(item.epsCount) +"</span>"+ "</div>";
    // h+="<div class=\"cover-tag-payfast\">付费抢先</div>";
    h += "</a>";
    h += "</div>";
    h+="<div class=\"info-wrp\">";
    h+="<div class=\"info\">"
    h+="<a href=" + LINK_SUBJECT + item.sId +" target=\"_blank\" title="+ item.name +">";
    h+=" <div class=\"t\" style=\"word-wrap: break-word;\">"+ item.name +"</div>"
    h += "</a>";
    // h+="<p class=\"num\">全13话</p>"
    h += "</div>";
    h += "</div>";
    h += "</div>";
    h += "</div>";
    return h;
}

function getEpsCount(epsCount) {
    if(parseInt(epsCount) == 0){
        return "";
    } else {
        return "全"+epsCount+"话";
    }
}

function chooseTypeClickListener() {
    var attribute_type = document.getElementById("attribute-type");
    var attribute_type_li = attribute_type.getElementsByTagName("li");
    for (var i = 0; i < attribute_type_li.length; i++) {
        (function (i) {
            attribute_type_li[i].onclick = function () {
                attribute_type_li[typeName_].getElementsByTagName("a")[0].classList.remove("focus");
                attribute_type_li[i].getElementsByTagName("a")[0].classList.add("focus");
                typeName_ = i;
                typeName = attribute_type_li[i].getElementsByTagName("a")[0].dataset.attributeType;
                querySearchList(type, typeName, year, month, status, page);
            };
        })(i);
    }

    var time_type = document.getElementById("time-type");
    var time_type_li = time_type.getElementsByTagName("li");
    for (var i = 0; i < time_type_li.length - 1; i++) {
        (function (i) {
            time_type_li[i].onclick = function () {
                time_type_li[year_].getElementsByTagName("a")[0].classList.remove("focus");
                time_type_li[i].getElementsByTagName("a")[0].classList.add("focus");
                year_ = i;
                year = time_type_li[i].getElementsByTagName("a")[0].dataset.time;
                querySearchList(type, typeName, year, month, status, page);
            };
        })(i);
    }

    // var month_type = document.getElementById("month-type");
    // var month_type_li = month_type.getElementsByTagName("li");
    // for (var i = 0; i < month_type_li.length; i++) {
    //     (function (i) {
    //         month_type_li[i].onclick = function () {
    //             month_type_li[month_].getElementsByTagName("a")[0].classList.remove("focus");
    //             month_type_li[i].getElementsByTagName("a")[0].classList.add("focus");
    //             month_ = i;
    //             month = month_type_li[i].getElementsByTagName("a")[0].dataset.month;
    //             querySearchList(type, typeName, year, month, status, page);
    //         };
    //     })(i);
    // }
}

// 分页
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
            page_id = api.getCurrent();
            querySearchList(belong_, type_id, gender_id, bloodtype_id, birthday_id, full_id ,page_id);
        }
    });
}

document.getElementById("item-time").onclick = function () {
    var time_type = document.getElementById("time-type");
    var item_time = document.getElementById("item-time");
    if (time_type.getAttribute("style").indexOf("120") != -1) {
        time_type.style.cssText = "max-height: 320px; height: 320px;";
        item_time.classList.remove("expand");
        item_time.classList.add("shrink");
    } else {
        time_type.style.cssText = "max-height: 120px; height: 120px;";
        item_time.classList.add("expand");
        item_time.classList.remove("shrink");
    }
}