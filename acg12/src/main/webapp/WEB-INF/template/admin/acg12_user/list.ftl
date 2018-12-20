<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 用户表管理</title>
    <meta name="keywords" content="${setting.siteName}">
    <meta name="description" content="${setting.siteName}">

    <!-- BEGIN HEADER -->
		[#include "/admin/include/style.ftl"]
    <!-- END HEADER -->
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <form id="listForm" action="list.html" method="get">
                    <div class="ibox-title">
                        <h5>用户表管理 </h5>
                        <div class="ibox-tools">
                            <a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="add.html"><i
                                    class="fa fa-plus"></i> 新增</a>
                            <a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group2"
                               href='javascript:dels()'><i class="fa fa-trash"></i> 删除</a>

                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row">

                            <div id="filter-div-id" class="col-sm-3 m-b-xs">
                                <div class="input-group">
                                    <input type="text" placeholder="请输入id" name="filter_ids" id="filter_ids"
                                           class="input-sm form-control  ">
                                    <span class="input-group-addon">--</span>
                                    <input type="text" placeholder="请输入id" name="filter_ide" id="filter_ide"
                                           class="input-sm form-control  ">
                                </div>
                            </div>


                            <div id="filter-div-username" class="col-sm-3 m-b-xs">
                                <input type="text" placeholder="请输入账号" name="filter_username" id="filter_username"
                                       class="input-sm form-control">
                            </div>


                            <div id="filter-div-password" class="col-sm-3 m-b-xs">
                                <input type="text" placeholder="请输入密码" name="filter_password" id="filter_password"
                                       class="input-sm form-control">
                            </div>


                            <div id="filter-div-email" class="col-sm-3 m-b-xs">
                                <input type="text" placeholder="请输入邮箱" name="filter_email" id="filter_email"
                                       class="input-sm form-control">
                            </div>


                            <div id="filter-div-avatar" class="col-sm-3 m-b-xs">
                                <input type="text" placeholder="请输入头像" name="filter_avatar" id="filter_avatar"
                                       class="input-sm form-control">
                            </div>


                            <div id="filter-div-nick" class="col-sm-3 m-b-xs">
                                <input type="text" placeholder="请输入昵称" name="filter_nick" id="filter_nick"
                                       class="input-sm form-control">
                            </div>


                            <div id="filter-div-sex" class="col-sm-3 m-b-xs">
                                <div class="input-group">
                                    <input type="text" placeholder="请输入性别" name="filter_sexs" id="filter_sexs"
                                           class="input-sm form-control  ">
                                    <span class="input-group-addon">--</span>
                                    <input type="text" placeholder="请输入性别" name="filter_sexe" id="filter_sexe"
                                           class="input-sm form-control  ">
                                </div>
                            </div>


                            <div id="filter-div-sign" class="col-sm-3 m-b-xs">
                                <input type="text" placeholder="请输入签名" name="filter_sign" id="filter_sign"
                                       class="input-sm form-control">
                            </div>


                            [#--<div id="filter-div-createTime" class="col-sm-3 m-b-xs">--]
                                [#--<div class="input-daterange input-group" id="datepicker">--]
                                    [#--<input type="text" placeholder="请输入创建时间日期" name="filter_createTimes"--]
                                           [#--id="filter_createTimes" class="input-sm form-control  ">--]
                                    [#--<span class="input-group-addon">——</span>--]
                                    [#--<input type="text" placeholder="请输入创建时间日期" name="filter_createTimee"--]
                                           [#--id="filter_createTimee" class="input-sm form-control  ">--]
                                [#--</div>--]
                            [#--</div>--]


                            [#--<div id="filter-div-updateTime" class="col-sm-3 m-b-xs">--]
                                [#--<div class="input-daterange input-group" id="datepicker">--]
                                    [#--<input type="text" placeholder="请输入更新时间日期" name="filter_updateTimes"--]
                                           [#--id="filter_updateTimes" class="input-sm form-control  ">--]
                                    [#--<span class="input-group-addon">——</span>--]
                                    [#--<input type="text" placeholder="请输入更新时间日期" name="filter_updateTimee"--]
                                           [#--id="filter_updateTimee" class="input-sm form-control  ">--]
                                [#--</div>--]
                            [#--</div>--]


                            <div class="col-sm-2　 m-b-xs">
                                　　　　
                                <div class="btn-group">
                                    <span> <button type="button"
                                                   class="btn btn-sm btn-primary btn-primary-search"> 搜索</button> </span>
                                    <span>  <div id="toolbar" class="btn-group"></span>
                                </div>
                            </div>
                        </div>
                        <div class="dataTables_wrapper form-inline">
                            <table id="Acg12User_list"></table>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
<!-- END Script -->

<script>

    $(document).ready(function () {
        	[@flash_message /]

        //时间控件
        $(".input-daterange").datepicker({
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            calendarWeeks: true,
            autoclose: true
        });

    });
</script>


<script>
    $('#Acg12User_list').bootstrapTable({
        method: 'get',
        toolbar: '#toolbar',    //工具按钮用哪个容器
        striped: true,      //是否显示行间隔色
        cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,     //是否显示分页（*）
        sortable: true,      //是否启用排序
        sortOrder: "id desc",     //排序方式
        pageNumber: 1,      //初始化加载第一页，默认第一页
        pageSize: 20,      //每页的记录行数（*）
        pageList: [20, 50, 100, 150],  //可供选择的每页的行数（*）
        url: "listNew.json",//这个接口需要处理bootstrap table传递的固定参数
        queryParamsType: '', //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
        // 设置为 '' 在这种情况下传给服务器的参数为：pageSize,pageNumber
        queryParams: queryParams,//前端调用服务时，会默认传递上边提到的参数，如果需要添加自定义参数，可以自定义一个函数返回请求参数
        sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
        showColumns: true, //是否显示所有的列
        showRefresh: true, //是否显示刷新按钮
        minimumCountColumns: 2,    //最少允许的列数
        clickToSelect: false,    //是否启用点击选中行
        searchOnEnterKey: true,
        checkboxHeader: true,
        columns: [
            {checkbox: true},
            {
                title: "id", field: "id", align: "center", valign: "middle", sortable: true
            },
            {
                title: "账号", field: "username", align: "center", valign: "middle", sortable: true
            },
            {
                title: "邮箱", field: "email", align: "center", valign: "middle", sortable: true
            },
            {
                title: "头像", field: "avatar", align: "center", valign: "middle", sortable: true
            },
            {
                title: "昵称", field: "nick", align: "center", valign: "middle", sortable: true
            },
            {
                title: "性别", field: "sex", align: "center", valign: "middle", sortable: true
            },
            {
                title: "签名", field: "sign", align: "center", valign: "middle", sortable: true
            },
            {
                title: "创建时间", field: "createTime", align: "center", valign: "middle", sortable: true
                , formatter: function (value, row, index) {
                    if ("undefined" != value + "") {
                        var a = dateFtt("yyyy-MM-dd hh:mm:ss", new Date(value));
                        return a;
                    } else {
                        return value;
                    }
                }

            },
            {
                title: "更新时间", field: "updateTime", align: "center", valign: "middle", sortable: true
                , formatter: function (value, row, index) {
                    if ("undefined" != value + "") {
                        var a = dateFtt("yyyy-MM-dd hh:mm:ss", new Date(value));
                        return a;
                    } else {
                        return value;
                    }
                }

            },
            {
                title: "操作", field: "id2", align: "center", valign: "middle",
                formatter: function (value, row, index) {
                    //通过formatter可以自定义列显示的内容
                    //value：当前field的值，即id
                    //row：当前行的数据
                    var a =
                            "<a class='btn btn-xs btn-default' data-id='" + value + "'  href='view/" + row.id + ".html' ><i class='fa fa-paste'></i>查看 </a>　"
                            + "<a class='btn btn-danger btn-xs btn-delete-loippi2'  href='javascript:del(" + row.id + ")' data-id='" + row.id + "' ><i class='fa fa-trash'></i> 删除</a>　"
                            + "<a class='btn btn-info btn-xs btn-edit-loippi' data-id='" + row.id + "'  href='edit/" + row.id + ".html' ><i class='fa fa-paste'></i> 编辑</a>";
                    return a;
                }
            },
        ],
        pagination: true
    });

    //初始化显示隐藏字段
    initColumns();

    function queryParams(params) {  //配置参数
        var temp = {
            pageNumber: params.pageNumber,   //页面大小
            pageSize: params.pageSize,   //页面大小
            pageList: params.pageList,   //页面大小

            filter_ids: $("#filter_ids").val(),
            filter_ide: $("#filter_ide").val(),

            filter_usernamelike: $("#filter_username").val(),

            filter_passwordlike: $("#filter_password").val(),

            filter_emaillike: $("#filter_email").val(),

            filter_avatarlike: $("#filter_avatar").val(),

            filter_nicklike: $("#filter_nick").val(),

            filter_sexs: $("#filter_sexs").val(),
            filter_sexe: $("#filter_sexe").val(),

            filter_signlike: $("#filter_sign").val(),

            sortName: params.sortName,
            sortOrder: params.sortOrder
        };
        return temp;
    }


    //初始化显示列,搜索字段
    function initColumns() {
        //初始化显示列
        [#list systemColumnList as systemColumn]
	        $('#Acg12User_list').bootstrapTable('${systemColumn.columnType}', '${systemColumn.columnName}');
        [/#list]
        //  初始化搜索字段
        [#list systemColumnSearchList as systemColumn]
            $('#checkbox-search-${systemColumn.columnName}').iCheck('${systemColumn.columnType}');
            $('#checkbox-search-${systemColumn.columnName}').attr('checked', '${systemColumn.columnType}');
        [/#list]

        $("[name='checkbox-search']").each(function () {
            if (!$(this).is(":checked")) {
                var id = "#filter-div-" + $(this).attr("data-field");
                $(id).hide();
            }
        });
    }


    //监听搜索字段
    $(".dropdown-menu-search").change(function () {
        var visibleColumns = "";
        var hiddenColumns = "";
        $("[name='checkbox-search']").each(function () {
            if ($(this).is(":checked")) {
                visibleColumns = visibleColumns + $(this).attr("data-field") + ",";
                var id = "#filter-div-" + $(this).attr("data-field");
                $(id).show();
            } else {
                hiddenColumns = hiddenColumns + $(this).attr("data-field") + ",";
                var id = "#filter-div-" + $(this).attr("data-field");
                $(id).hide();
                $(id).val("");

            }
        });

        $.ajax({
            url: "updateColumnSearchLists.html",
            type: "GET",
            data: {
                visibleColumns: visibleColumns,
                hiddenColumns: hiddenColumns
            },
            success: function (message) {
            }
        });

    });

    //页面跳转
    function refreshNum() {
        $('#Acg12User_list').bootstrapTable('selectPage', $('#searchPageNum').val());
    };

    //搜索按钮
    $(".btn-primary-search").click(function () {
        $('#Acg12User_list').bootstrapTable('selectPage', 1);
        $('#Acg12User_list').bootstrapTable(
                "refresh",
                {
                    url: "listNew.json"
                }
        );
    });


    // 删除单条记录
    function del(val) {
        bootbox.confirm(message("admin.dialog.deleteConfirm"), function (result) {
            if (result) {
                $.ajax({
                    url: "delete.html",
                    type: "get",
                    data: {ids: val},
                    dataType: "json",
                    cache: false,
                    success: function (message) {
                        $('#Acg12User_list').bootstrapTable(
                                "refresh",
                                {
                                    url: "listNew.json"
                                }
                        );
                    }
                });
            }
        });
    };

    // 删除多条条记录
    function dels(val) {
        bootbox.confirm(message("admin.dialog.deleteConfirm"), function (result) {
            var rows = $.map($('#Acg12User_list').bootstrapTable('getSelections'), function (row) {
                return row.id
            });
            var ids = "";
            for (i = 0; i < rows.length; i++) {
                ids = ids + "," + rows[i];
            }
            if (result) {
                $.ajax({
                    url: "delete.html",
                    type: "get",
                    data: {ids: ids},
                    dataType: "json",
                    cache: false,
                    success: function (message) {
                        $('#Acg12User_list').bootstrapTable(
                                "refresh",
                                {
                                    url: "listNew.json"
                                }
                        );
                    }
                });
            }
        });
    };


</script>


</body>

</html>

