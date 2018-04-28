<%@ page import="com.acg12.utils.search.SearchUtli" %>
<%@ page import="org.json.JSONObject" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/23
  Time: 8:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>搜索</title>
    <link href="css/index.css" rel="stylesheet">
    <link href="css/search.css" rel="stylesheet">
    <link href="css/common/pagination/pagination.css" rel="stylesheet">
    <script type="text/javascript" src="js/common/jquery-3.2.0.js"></script>
    <script src="js/common/pagination/jquery.pagination.js"></script>
</head>
<body class="bangumi">

<div >
    <div class="title"></div>

    <div class="search-box">
        <form action="/acg12/view/search" method="get" class="new-searching-unit" data-regestered="regestered">
            <input id="query" type="text" size="27" name="k" autocomplete="off" placeholder="搜索你喜欢的" value="">
            <a href="#" onclick="return false;" class="go"></a>
        </form>

    </div>

    <div class="columns">

        <div id="columnSearchA" class="column">
        </div>

        <div class="columnSearchB column">
            <div class="browserTools">
                <ul class="browserTypeSelector">
                    <li><a href="javascript:void(0);" class="full_selector active" id="full_selector"><span>列表</span></a></li>
                    <li><a href="javascript:void(0);" class="grid_selector" id="grid_selector"><span>网格</span></a></li>
                </ul>
            </div>

            <ul class="browserItemList">
                <%
                    String key = request.getParameter("key");
                    String start = request.getParameter("start");
                    JSONObject msg = SearchUtli.getSubjectSearchList(key, 0, start);
                    for (int i = 0 , total = msg.length() ; i< total ; i++){
                        JSONObject results = msg.getString("results");

                %>

                <%
                    }
                %>
            </ul>

            <div class="loaddingNull">
                没有更多内容哦，请更换条词。
            </div>
        </div>

        <div id="columnSearchC" class="column">
        </div>

    </div>

    <div class="page">
        <div class="m-style M-box3"></div>
    </div>

</div>

<script type="text/javascript" src="js/search.js"></script>

</body>
</html>
