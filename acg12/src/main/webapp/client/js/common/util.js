/**
 * Created by Administrator on 2018/4/26.
 */
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

var URL = "http://localhost:8080/acg12/api/";

var URL_PERSON = URL + "person";

var URL_PERSON_INFO = URL + "person/info";

var URL_CHARACTER = URL + "character";