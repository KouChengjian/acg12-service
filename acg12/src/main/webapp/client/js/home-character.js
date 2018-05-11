var belong_ = 1, type_ = 0, gender_ = 0, bloodtype_ = 0, birthday_ = 0, full_ = 0;
var type_id = 0, gender_id = 0, bloodtype_id = 0, birthday_id = "", full_id = 0, page_id = 1;

window.onload = function () {
    loaddingHeader();
    trimTowHtml();
    querySearchList(belong_, type_id, gender_id, bloodtype_id, birthday_id, full_id ,page_id);
}

$("img").one("error", function(e){
    $(this).attr("src", "/acg12/view/img/bg_loading_pic.png");
});

function querySearchList(belong, type, gender, bloodtype, birthday, full, page) {
    var xhr;
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {  //状态等于4的时候返回值
            if (xhr.status == 200 || xhr.status == 304) {
                // var data = xhr.responseText;
                // console.log(data);
                var data = eval('(' + xhr.responseText + ')');
                var html = '';
                if (data.code == 20000) {
                    var html = '';
                    if(belong == 1){
                        for (var i = 0, total = data.data.characterEntitieList.length; i < total; i++) {
                            html += paddingHtml(data.data.characterEntitieList[i]);
                        }
                    } else {
                        for (var i = 0, total = data.data.personEntityList.length; i < total; i++) {
                            html += paddingHtml(data.data.personEntityList[i]);
                        }
                    }
                    loaddingPagination(Math.ceil(data.data.totalResult / 10), page);
                } else {
                    var pagehtml = document.getElementById("page");
                    var m_stylehtml = document.getElementsByClassName("m-style");
                    pagehtml.removeChild(m_stylehtml[0])
                    html = "<div class=\"loaddingNull\">没有更多内容哦，请更换条词再次搜索。 </div>"
                }
                document.getElementsByClassName('browser-list')[0].innerHTML = html;
            }
        }
    }
    xhr.open("GET", (belong == 1 ? URL_CHARACTER : URL_PERSON )+ "?type=" + type + "&gender=" + gender + "&bloodtype=" + bloodtype + "&birthday=" + birthday + "&page=" + page, true);
    xhr.send(null);
}

function paddingHtml(item) {
    var h = "<div class=\"browser-item\">";
    h += "<a href=\"character/"+ item.characterId +"\" class=\"avatar\">";
    h += "<img src=\"" + item.image + "\"onerror="+ "this.src=\"img/bg_loading_pic.png\""  + "  class=\"avatar\">";
    h += "</a>";
    h += "<div style=\"padding-left: 90px;\">";
    h += "<h3><a href=\"character/"+ item.characterId + "\" class=\"on\">" + getTitle(item) + "</a></h3>";
    h += "<div class=\"prsn-info\">";
    h += "<span class=\"tip\"> " + getSubject(item) + "</span>";
    h += "</div>";
    h += "<p class=\"content\">";
    // h += item.;
    h += "</p>";
    h += "</div>";
    h += "</div>";
    return h;
}

function getTitle(item) {
    if(item.nameCn == ""){
        return item.name;
    }
    return item.nameCn;
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
        s1 += "/ 生日 " +item.birthday;
    }
    if(item.bloodtype == 1){
        s1 += "/ 血型 A"
    } else if(item.bloodtype == 2){
        s1 += "/ 血型 B"
    } else if(item.bloodtype == 3){
        s1 += "/ 血型 AB"
    } else if(item.bloodtype == 4){
        s1 += "/ 血型 O"
    }
    return s1;
}

function trimTowHtml() {
    var type = document.getElementById("type");
    var typelis = type.getElementsByTagName("li");
    var html = "";
    for (var i = 0; i < typelis; i++) {
        type.removeChild(typelis[i])
    }
    if(belong_ == 1){
        html = "<li><a class=\"l focus\" data-type=\"0\">全部</a></li>"
            + "<li><a class=\"l \" data-type=\"1\">角色</a></li>"
            + "<li><a class=\"l \" data-type=\"2\">机体</a></li>"
            + "<li><a class=\"l \" data-type=\"3\">舰船</a></li>"
            + "<li><a class=\"l \" data-type=\"4\">组织机构</a></li>"
    } else {
        html = "<li><a class=\"l focus\" data-type=\"0\">全部</a></li>"
            + "<li><a class=\"l \" data-type=\"1\">声优</a></li>"
            + "<li><a class=\"l \" data-type=\"2\">漫画家</a></li>"
            + "<li><a class=\"l \" data-type=\"3\">制作人</a></li>"
            + "<li><a class=\"l \" data-type=\"4\">音乐人</a></li>"
            + "<li><a class=\"l \" data-type=\"6\">演员</a></li>"
            + "<li><a class=\"l \" data-type=\"7\">绘师</a></li>"
            + "<li><a class=\"l \" data-type=\"8\">作家</a></li>"
    }
    type.innerHTML = html;
    for (var i = 0; i < typelis.length; i++) {
        (function (i) {
            typelis[i].onclick = function () {
                typelis[type_].getElementsByTagName("a")[0].classList.remove("focus");
                typelis[i].getElementsByTagName("a")[0].classList.add("focus");
                type_ = i;
                type_id = typelis[i].getElementsByTagName("a")[0].dataset.type;
                querySearchList(belong_, type_id, gender_id, bloodtype_id, birthday_id, full_id ,page_id);
            };
        })(i);
    }

    var bloodtype = document.getElementById("bloodtype");
    var bloodtypelis = bloodtype.getElementsByTagName("li");
    for (var i = 0; i < bloodtypelis; i++) {
        bloodtype.removeChild(bloodtypelis[i])
    }
    var html = "<li><a class=\"l focus\" data-bloodtype=\"0\">全部</a></li>"
        + "<li><a class=\"l \" data-bloodtype=\"1\">A</a></li>"
        + "<li><a class=\"l \" data-bloodtype=\"2\">B</a></li>"
        + "<li><a class=\"l \" data-bloodtype=\"3\">AB</a></li>"
        + "<li><a class=\"l \" data-bloodtype=\"4\">O</a></li>"
    bloodtype.innerHTML = html;
    for (var i = 0; i < bloodtypelis.length; i++) {
        (function (i) {
            bloodtypelis[i].onclick = function () {
                console.log(i)
                bloodtypelis[bloodtype_].getElementsByTagName("a")[0].classList.remove("focus");
                bloodtypelis[i].getElementsByTagName("a")[0].classList.add("focus");
                bloodtype_ = i;
                bloodtype_id = bloodtypelis[i].getElementsByTagName("a")[0].dataset.bloodtype;
                querySearchList(belong_, type_id, gender_id, bloodtype_id, birthday_id, full_id ,page_id);
            };
        })(i);
    }

    var gender = document.getElementById("gender");
    var genderlis = gender.getElementsByTagName("li");
    for (var i = 0; i < genderlis; i++) {
        gender.removeChild(genderlis[i])
    }
    var html = "<li><a class=\"l focus\" data-gender=\"0\">全部</a></li>"
        + "<li><a class=\"l \" data-gender=\"1\">男性</a></li>"
        + "<li><a class=\"l \" data-gender=\"2\">女性</a></li>"
    gender.innerHTML = html;
    for (var i = 0; i < genderlis.length; i++) {
        (function (i) {
            genderlis[i].onclick = function () {
                console.log(i)
                genderlis[gender_].getElementsByTagName("a")[0].classList.remove("focus");
                genderlis[i].getElementsByTagName("a")[0].classList.add("focus");
                gender_ = i;
                gender_id = genderlis[i].getElementsByTagName("a")[0].dataset.gender;
                querySearchList(belong_, type_id, gender_id, bloodtype_id, birthday_id, full_id ,page_id);
            };
        })(i);
    }

    var birthday = document.getElementById("birthday");
    var birthdaylis = birthday.getElementsByTagName("li");
    for (var i = 0; i < birthdaylis; i++) {
        birthday.removeChild(birthdaylis[i])
    }
    var html = "<li><a class=\"l focus\" data-birthday=\"0\">全部</a></li>"
        + "<li><a class=\"l \" data-birthday=\"1\">一月</a></li>"
        + "<li><a class=\"l \" data-birthday=\"2\">二月</a></li>"
        + "<li><a class=\"l \" data-birthday=\"3\">三月</a></li>"
        + "<li><a class=\"l \" data-birthday=\"4\">四月</a></li>"
        + "<li><a class=\"l \" data-birthday=\"5\">五月</a></li>"
        + "<li><a class=\"l \" data-birthday=\"6\">六月</a></li>"
        + "<li><a class=\"l \" data-birthday=\"7\">七月</a></li>"
        + "<li><a class=\"l \" data-birthday=\"8\">八月</a></li>"
        + "<li><a class=\"l \" data-birthday=\"9\">九月</a></li>"
        + "<li><a class=\"l \" data-birthday=\"10\">十月</a></li>"
        + "<li><a class=\"l \" data-birthday=\"11\">十一月</a></li>"
        + "<li><a class=\"l \" data-birthday=\"12\">十二月</a></li>"
    birthday.innerHTML = html;
    for (var i = 0; i < birthdaylis.length; i++) {
        (function (i) {
            birthdaylis[i].onclick = function () {
                console.log(i)
                birthdaylis[birthday_].getElementsByTagName("a")[0].classList.remove("focus");
                birthdaylis[i].getElementsByTagName("a")[0].classList.add("focus");
                birthday_ = i;
                birthday_id = birthdaylis[i].getElementsByTagName("a")[0].dataset.birthday;
                querySearchList(belong_, type_id, gender_id, bloodtype_id, birthday_id, full_id ,page_id);
            };
        })(i);
    }

    var full = document.getElementById("full");
    var fulllis = full.getElementsByTagName("li");
    for (var i = 0; i < fulllis; i++) {
        full.removeChild(fulllis[i])
    }
    var html = "<li><a class=\"l focus\" data-full=\"0\">全部</a></li>"
        + "<li><a class=\"l \" data-full=\"1\">完整</a></li>"
        + "<li><a class=\"l \" data-full=\"2\">残缺</a></li>"
    full.innerHTML = html;
    for (var i = 0; i < fulllis.length; i++) {
        (function (i) {
            fulllis[i].onclick = function () {
                console.log(i)
                fulllis[full_].getElementsByTagName("a")[0].classList.remove("focus");
                fulllis[i].getElementsByTagName("a")[0].classList.add("focus");
                full_ = i;
                full_id = fulllis[i].getElementsByTagName("a")[0].dataset.full;
                querySearchList(belong_, type_id, gender_id, bloodtype_id, birthday_id, full_id ,page_id);
            };
        })(i);
    }

    querySearchList(belong_, type_id, gender_id, bloodtype_id, birthday_id, full_id ,page_id);
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
