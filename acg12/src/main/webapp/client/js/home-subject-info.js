/**
 * Created by Administrator on 2018/5/14.
 */
window.onload = function () {
    loaddingHeader();
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
