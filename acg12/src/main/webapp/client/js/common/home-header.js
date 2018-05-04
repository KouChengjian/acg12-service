/**
 * "<ul><li><a href=\"#\">TV</a></li><li><a href=\"#\">WEB</a></li><li><a href=\"#\">OVA</a></li><li><a href=\"#\">剧场版</a></li><li><a href=\"#\">其他</a></li></ul>"
 */
function loaddingHeader(){
    var header =
    "<div class=\"header-container\">"+
    "<a href=\"home.html\"><img class=\"header-logo\" src=\"http://css99tel.cdndm5.com/v201804091412/blue/images/header-logo.png\"></a>"
    +"<nav class=\"nav nav1\"> <ul>"
        +" <li><a href=\"home-animat.html\">动画</a></li>"
        +" <li><a href=\"home-caricature.html\">漫画</a></li>"
        +" <li><a href=\"home-game.html\">游戏</a></li>"
        +" <li><a href=\"home-novel.html\">小说</a></li>"
        +" <li><a href=\"home-person.html\">人物</a></li>"
    +"</ul> </nav>";

    var element = document.getElementById("header-menu");
    if(element !=null){
        element.innerHTML = header;
    }
}