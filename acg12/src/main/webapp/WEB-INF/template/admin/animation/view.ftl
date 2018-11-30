<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 埋点模块详情</title>
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
                <div class="ibox-title">
                    <h5>埋点模块详情</h5>
                    <div class="ibox-tools">
                        <button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi"
                                onclick="location.href='${base}/admin/sy_event_report_iteam/list.jhtml'"><i
                                class="fa fa-reply-all"></i> 返回列表
                        </button>
                    </div>
                </div>
                <div class="ibox-content">
                    <form id="inputForm" class="form-horizontal" action="update.jhtml" method="post">
                        <input type="hidden" value="${syEventReportIteam.id}"
                               name="id"/>

                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">
                                        埋点ID</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">${syEventReportIteam.eventId}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/row-->
                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">
                                        对应模块名称</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">${syEventReportIteam.eventName}</p>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">
                                        父级ID</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">${syEventReportIteam.parentEventId}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/row-->
                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">
                                        父级模块名</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">${syEventReportIteam.parentEventName}</p>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">
                                        排序</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">${syEventReportIteam.sort}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/row-->
                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">
                                        1.点击事件（待扩展）</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">${syEventReportIteam.evenType}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/row-->

                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-12 col-sm-offset-10">
                                <button type="button" class="btn btn-default" onclick="window.history.back();">返回
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- BEGIN Script -->
[#include "/admin/include/script.ftl"]
<!-- END Script -->
</body>
</html>
