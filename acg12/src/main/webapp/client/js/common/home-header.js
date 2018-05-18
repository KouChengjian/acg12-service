/**
 * "<ul><li><a href=\"#\">TV</a></li><li><a href=\"#\">WEB</a></li><li><a href=\"#\">OVA</a></li><li><a href=\"#\">剧场版</a></li><li><a href=\"#\">其他</a></li></ul>"
 */
function loaddingHeader(){
    var header =
    "<div class=\"header-container\">"+
    "<a href=\"#\"><img class=\"header-logo\" src=\"/acg12/view/img/logo.png\"></a>"

    +"<nav class=\"nav nav1\"> <ul>"
        +" <li><a href="+ LINK_HOME_ANIMAT +">动画</a></li>"
        +" <li><a href="+ LINK_HOME_CARICATURE +">漫画</a></li>"
        +" <li><a href="+ LINK_HOME_GAME +">游戏</a></li>"
        +" <li><a href="+ LINK_HOME_NOVEL +">小说</a></li>"
        +" <li><a href="+ LINK_HOME_CHARACTER +">人物</a></li>"
    +"</ul> </nav>"

    +"<div class=\"header-login\">"
    +"<a href=\"javascript:void(0);\" id=\"header-login-btn\">"
    +"<img data-isload=\"0\" class=\"header-avatar\" src=\"/acg12/view/img/login.gif\">"
    +"</a>"
    +"</div>"

    +"<div class=\"heaser-search\">"
    +"<form class=\"search-form\">"
    +"<input type=\"text\" autocomplete=\"off\" accesskey=\"s\" x-webkit-speech=\"\" x-webkit-grammar=\"builtin:translate\" placeholder=\"这个夏天我们还要在一起❤\" value=\"\" class=\"search-keyword\">"
    +"<button type=\"submit\" class=\"search-submit\"></button>"
    +"</form>"
    +"</div>"
    +"</div>";

    var element = document.getElementById("header-menu");
    if(element !=null){
        element.innerHTML = header;
    }
}