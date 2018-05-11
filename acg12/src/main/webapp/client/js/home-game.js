/**
 * Created by Administrator on 2018/5/10.
 */
var typeName_ = 1, year_ = 0, month_ = 0, status_ = 0;
var type = 4, typeName = 0, year = 0, month = 0, status = "", page = 1;
window.onload = function () {
    loaddingHeader();

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
                console.log(data)
                var html = '';
                if (data.code == 20000) {
                    var html = '';
                    for (var i = 0, total = data.data.list.length; i < total; i++) {
                        html += paddingHtml(data.data.list[i]);
                    }
                    if(data.data.list.length >= 20){
                        loaddingPagination(Math.ceil(data.data.totalResult / 10), page);
                    }

                } else {
                    // var pagehtml = document.getElementById("page");
                    // var m_stylehtml = document.getElementsByClassName("m-style");
                    // pagehtml.removeChild(m_stylehtml[0])
                    // html = "<div class=\"loaddingNull\">没有更多内容哦，请更换条词再次搜索。 </div>"
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
    h+="<a href=" + item.image + " target=\"_blank\" title="+ item.name+">";
    h+= "<img  src="+ item.image +" alt=" + item.name +">";
    h+="<div class=\"shadow\">"   +"<span class=\"sort-info\">387.4万人追番</span>"+ "</div>";
    h+="<div class=\"cover-tag-payfast\">付费抢先</div>";
    h += "</a>";
    h += "</div>";
    h+="<div class=\"info-wrp\">";
    h+="<div class=\"info\">"
    h+="<a href=\"https://bangumi.bilibili.com/anime/21542\" target=\"_blank\" title="+ item.name +">";
    h+=" <div class=\"t\" style=\"word-wrap: break-word;\">"+ item.name +"</div>"
    h += "</a>";
    h+="<p class=\"num\">全13话</p>"
    h += "</div>";
    h += "</div>";
    h += "</div>";
    h += "</div>";
    return h;
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