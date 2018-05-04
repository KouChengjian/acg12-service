/**
 * Created by Administrator on 2018/4/27.
 */
window.onload = function () {
    loaddingHeader();
}

document.getElementById("item-time").onclick = function () {
    var  time_type = document.getElementById("time-type");
    var item_time = document.getElementById("item-time");
    if(time_type.getAttribute("style").indexOf("120") != -1){
        time_type.style.cssText = "max-height: 320px; height: 320px;";
        item_time.classList.remove("expand");
        item_time.classList.add("shrink");
    } else {
        time_type.style.cssText = "max-height: 120px; height: 120px;";
        item_time.classList.add("expand");
        item_time.classList.remove("shrink");
    }
}