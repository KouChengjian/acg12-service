/**
 * Created by Administrator on 2018/5/18.
 */
var page = 1;
window.onload = function () {
    loaddingHeader();

    queryAlbum(page);
}

window.onscroll = function(){
    if(getCheck()){
        page += 1;
        console.log("ssssssssssss")
        // queryAlbum(page);
    }
}

function queryAlbum(page) {
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
                    // var pagehtml = document.getElementById("page");
                    // pagehtml.style.cssText="display:inline;";
                    // loaddingPagination(Math.ceil(data.data.totalResult / 20), page);
                } else {
                    // var pagehtml = document.getElementById("page");
                    // pagehtml.style.cssText="display:none;";
                    // html = "<div class=\"loaddingNull\">没有更多内容哦，请更换条词再次搜索。 </div>"
                }
                document.getElementById("wrap").innerHTML = html;
                PBL('wrap','box');
            }
        }
    }
    xhr.open("GET", URL_RES_SEARCH_ALBUM + "?key=" + key + "&page=" + page, true);
    xhr.send(null);
}

function paddingHtml(item) {
    var h = "<div class=\"box\">";
    h += "<div class=\"info\">";
    h += "<a class=\"image\">";
    h += "<img src="+item.image+" >";
    h += "</a>";
    h += "<p class=\"description\">" + item.content +"</p>";
    h += "<p class=\"stats\">";
    h += "<span title=\"转采\" class=\"repin\"><i></i>"+item.favorites+"</span>";
    h += "<span title=\"喜欢\" class=\"like\"><i></i>"+item.love+"</span>";
    h += "</p>";
    h += "</div>";
    h += "</div>";
    return h;


}

document.getElementById("info-expand").onclick = function () {
    var  container_centerA = document.getElementById("container-preview");
    var  info_box = document.getElementById("info-box");
    var  section = document.getElementById("section");
    var content = document.getElementById("intro");
    var info = document.getElementById("info");
    var expand = document.getElementById("info-expand");
    if(parseInt(info_box.offsetHeight) == 0){
        console.log(info.offsetHeight)
        content.setAttribute("class", "content1");
        console.log(info.offsetHeight)
        var contentH = info.offsetHeight;
        // var nextH = content.offsetHeight;
        // restHeight(left , right , nextH );
        var left = 26; // 前期偏移26px
        var lis = info_box.getElementsByTagName("li");
        for (var i = 0 ; i < lis.length ; i++){
            left += lis[i].offsetHeight;
        }
        var right = 0;
        if(parseInt(catLine) != 0){
            right = (48 + 30) * catLine + 10 + 10 + 35;
        }

        if (parseInt(left) > parseInt(right)) {
            container_centerA.style.cssText = "height: " + (250 + left) + "px;";
            info_box.style.cssText = "height: " + left + "px;";
            if(parseInt(right) == 0){
                section.style.cssText = "height: 0px;";
                expand.style.marginTop = (left - 40)+"px";
            } else {
                section.style.cssText = "height: " + (250 + left - contentH - 35) + "px;";
                expand.style.marginTop = ((250 + left - contentH - 35) - 40)+"px";
            }
            expand.innerHTML = "<a>收起详情<<</a>"
        } else {
            container_centerA.style.cssText = "height: " + (contentH + 35 + right) + "px;";
            info_box.style.cssText = "height: " + left + "px;";
            section.style.cssText = "height: " + right + "px;";
        }
    } else {
        content.setAttribute("class", "content");
        container_centerA.style.cssText = "height: 250px;";
        info_box.style.cssText = "height: 0px;";
        section.style.cssText = "height: 0px;";
        expand.style.marginTop = "10px";
        expand.innerHTML = "<a>查看详情>></a>"
    }
}