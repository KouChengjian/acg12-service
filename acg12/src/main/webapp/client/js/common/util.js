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

// ------------------------------------------------api-------------------------------------------------------
var URL = "http://localhost:8080/acg12/api/";
// var URL = "http://139.196.46.40/acg12/api/";
var URL_SUBJECT = URL + "subject";
var URL_PERSON = URL + "person";
var URL_CHARACTER = URL + "character";

var URL_PERSON_INFO = URL + "person/info";

var URL_RES_ALBUM = URL + "p/album";
var URL_RES_BOARDS = URL + "p/boards";
var URL_RES_BOARDS_ALBUM = URL + "p/boards/album";
var URL_RES_SEARCH_ALBUM= URL + "search/album";
var URL_RES_SEARCH_BOARDS = URL + "search/boards";

// -----------------------------------------------html---------------------------------------------------------
var LINK = "/acg12/view/";
var LINK_HOME_ANIMAT = LINK + "animat";
var LINK_HOME_CARICATURE = LINK + "caricature";
var LINK_HOME_GAME = LINK + "game";
var LINK_HOME_NOVEL= LINK + "novel";
var LINK_HOME_CHARACTER = LINK + "character";
var LINK_HOME_PERSON = LINK + "person";

var LINK_SUBJECT= LINK + "subject/";
var LINK_CHARACTER= LINK + "character/";
var LINK_PERSON= LINK + "person/";
