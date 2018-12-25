<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - scheduleJob管理</title>
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
                        <h5>scheduleJob管理 </h5>
                        <div class="ibox-tools">
                            <a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi"
                               href="javascript:to_add()"><i
                                    class="fa fa-plus"></i> 新增</a>
                            <a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group2"
                               href='javascript:dels()'><i class="fa fa-trash"></i> 删除</a>

                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row">

                            <div id="filter-div-id" class="col-sm-3 m-b-xs">
                                <input type="text" placeholder="id" name="filter_ids" id="filter_ids"
                                       class="input-sm form-control  ">
                            </div>
                            <div id="filter-div-jobName" class="col-sm-3 m-b-xs">
                                <input type="text" placeholder="任务名称" name="filter_jobName" id="filter_jobName"
                                       class="input-sm form-control">
                            </div>
                            <div id="filter-div-jobGroup" class="col-sm-3 m-b-xs">
                                <input type="text" placeholder="任务分组" name="filter_jobGroup" id="filter_jobGroup"
                                       class="input-sm form-control">
                            </div>
                            <div id="filter-div-jobStatus" class="col-sm-3 m-b-xs">
                                <input type="text" placeholder="状态 0 未开启 1 开启" name="filter_jobStatus"
                                       id="filter_jobStatus" class="input-sm form-control">
                            </div>
                            <div id="filter-div-cronExpression" class="col-sm-3 m-b-xs">
                                <input type="text" placeholder="请输入cron表达式" name="filter_cronExpression"
                                       id="filter_cronExpression" class="input-sm form-control">
                            </div>
                            <div id="filter-div-isConcurrent" class="col-sm-3 m-b-xs">
                                <input type="text" placeholder="请输入任务是否有状态" name="filter_isConcurrent"
                                       id="filter_isConcurrent" class="input-sm form-control">
                            </div>

                            <div class="col-sm-2　 m-b-xs">
                                　　　　
                                <div class="btn-group">
                                    <span> <button type="button"
                                                   class="btn btn-sm btn-primary btn-primary-search"> 搜索</button> </span>
                                    <span>  <div id="toolbar" class="btn-group"></span>
                                </div>
                            </div>
                        </div>
                        <div class="dataTables_wrapper form-inline">
                            <table id="Acg12ScheduleJob_list"></table>
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
    $('#Acg12ScheduleJob_list').bootstrapTable({
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
                title: "任务名称", field: "jobName", align: "center", valign: "middle", sortable: true
            },
            {
                title: "任务分组", field: "jobGroup", align: "center", valign: "middle", sortable: true
            },
            {
                title: "cron表达式", field: "cronExpression", align: "center", valign: "middle", sortable: true
            },
            {
                title: "描述", field: "description", align: "center", valign: "middle", sortable: true
            },
            {
                title: "路径", field: "beanClass", align: "center", valign: "middle", sortable: true
            },
            // {
            //     title: "任务是否有状态", field: "isConcurrent", align: "center", valign: "middle", sortable: true
            // },
            {
                title: "任务调用的方法名", field: "methodName", align: "center", valign: "middle", sortable: true
            },
            // {
            //     title: "spring bean", field: "springId", align: "center", valign: "middle", sortable: true
            // },
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
                title: "状态", field: "jobStatus", align: "center", valign: "middle",
                formatter: function (value, row, index) {
                    var a ="";
                    if("0" == row.jobStatus){
                        a += "<a class='btn btn-info btn-xs btn-edit-loippi' data-id='" + row.id + "'  href='javascript:to_changeJobStatus(" + row.id + ",1)' ><i class='fa fa-paste'></i> 开启</a>";
                    } else {
                        a += "<a class='btn btn-danger btn-xs btn-edit-loippi' data-id='" + row.id + "'  href='javascript:to_changeJobStatus(" + row.id +",0)' ><i class='fa fa-paste'></i> 停止</a>";
                    }
                    return a;
                }
            },
            {
                title: "操作", field: "id2", align: "center", valign: "middle",
                formatter: function (value, row, index) {
                    //通过formatter可以自定义列显示的内容
                    //value：当前field的值，即id
                    //row：当前行的数据
                    var a =
                            "<a class='btn btn-danger btn-xs btn-delete-loippi2'  href='javascript:del(" + row.id + ")' data-id='" + row.id + "' ><i class='fa fa-trash'></i> 删除</a>　"
                            + "<a class='btn btn-info btn-xs btn-edit-loippi' data-id='" + row.id + "'  href='javascript:to_edit(" + row.id + ")' ><i class='fa fa-paste'></i> 编辑</a>　";
                    // if("0" == row.jobStatus){
                    //     a += "<a class='btn btn-info btn-xs btn-edit-loippi' data-id='" + row.id + "'  href='javascript:to_changeJobStatus(" + row.id + ",1)' ><i class='fa fa-paste'></i> 开启</a>　";
                    // } else {
                    //     a += "<a class='btn btn-info btn-xs btn-edit-loippi' data-id='" + row.id + "'  href='javascript:to_changeJobStatus(" + row.id +",0)' ><i class='fa fa-paste'></i> 停止</a>　";
                    // }
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


            filter_jobNamelike: $("#filter_jobName").val(),

            filter_jobGrouplike: $("#filter_jobGroup").val(),

            filter_jobStatuslike: $("#filter_jobStatus").val(),

            filter_cronExpressionlike: $("#filter_cronExpression").val(),

            filter_descriptionlike: $("#filter_description").val(),

            filter_beanClasslike: $("#filter_beanClass").val(),

            filter_isConcurrentlike: $("#filter_isConcurrent").val(),

            filter_methodNamelike: $("#filter_methodName").val(),

            filter_springIdlike: $("#filter_springId").val(),

            filter_createTimes: $("#filter_createTimes").val(),
            filter_createTimee: $("#filter_createTimee").val(),

            filter_updateTimes: $("#filter_updateTimes").val(),
            filter_updateTimee: $("#filter_updateTimee").val(),
            sortName: params.sortName,
            sortOrder: params.sortOrder
        };
        return temp;
    }


    //初始化显示列,搜索字段
    function initColumns() {
        //初始化显示列
        [#list systemColumnList as systemColumn]
	        $('#Acg12ScheduleJob_list').bootstrapTable('${systemColumn.columnType}', '${systemColumn.columnName}');
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
            url: "updateColumnSearchLists.jhtml",
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
        $('#Acg12ScheduleJob_list').bootstrapTable('selectPage', $('#searchPageNum').val());
    };

    //搜索按钮
    $(".btn-primary-search").click(function () {
        $('#Acg12ScheduleJob_list').bootstrapTable('selectPage', 1);
        $('#Acg12ScheduleJob_list').bootstrapTable(
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
                    url: "delete.jhtml",
                    type: "get",
                    data: {ids: val},
                    dataType: "json",
                    cache: false,
                    success: function (message) {
                        $('#Acg12ScheduleJob_list').bootstrapTable(
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
            var rows = $.map($('#Acg12ScheduleJob_list').bootstrapTable('getSelections'), function (row) {
                return row.id
            });
            var ids = "";
            for (i = 0; i < rows.length; i++) {
                ids = ids + "," + rows[i];
            }
            if (result) {
                $.ajax({
                    url: "delete.jhtml",
                    type: "get",
                    data: {ids: ids},
                    dataType: "json",
                    cache: false,
                    success: function (message) {
                        $('#Acg12ScheduleJob_list').bootstrapTable(
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

    var to_add = function () {
        layer.open({
            type: 2,
            title: '弹出窗口',
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['80%', '90%'],
            content: 'add.html',
            cancel: function (index) {
                layer.close(index);
                location.reload();
                return false;
            }
        });
    }

    var to_edit = function (id) {
        layer.open({
            type: 2,
            title: '弹出窗口',
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['80%', '90%'],
            content: 'edit/' + id + '.html',
            cancel: function (index) {
                layer.close(index);
                location.reload();
                return false;
            }
        });
    }

    var to_changeJobStatus = function (jobId , cmd) {
        // art.dialog.message({type:"success", content:"操作成功"});
        $.ajax({
            type : "POST",
            async : false,
            dataType : "JSON",
            cache : false,
            url : "changeJobStatus.json",
            data : {
                jobId : jobId,
                cmd : cmd
            },
            success : function(data) {
                if (data.code == 2000000) {
                    location.reload();
                } else {
                    art.dialog.message({type:"success", content:data.message});
                    // jumper.error(data.message)
                }
                // hideWaitMsg();
                // if (data.flag) {
                //     location.reload();
                // } else {
                //     alert(data.msg);
                // }
                // art.dialog.message({type:"success", content:"操作成功"});

            }//end-callback
        });//end-ajax
    }

</script>


</body>

</html>

