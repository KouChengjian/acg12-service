<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 编辑scheduleJob</title>
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
                    <h5>编辑scheduleJob</h5>
                    <div class="ibox-tools">
                        <button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi"
                                onclick="location.href='${base}/admin/schedule_job/list.html'"><i
                                class="fa fa-reply-all"></i> 返回列表
                        </button>
                    </div>
                </div>
                <div class="ibox-content">
                    <form id="inputForm" class="form-horizontal" action="${base}/admin/schedule_job/update.html"
                          method="post">
                        <input type="hidden" value="${acg12ScheduleJobEntity.id}" name="id"/>

                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">任务名称</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="jobName" class="form-control" maxlength="200"
                                               value="${(acg12ScheduleJobEntity.jobName)!''}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/row-->

                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">任务分组</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="jobGroup" class="form-control" maxlength="200"
                                               value="${(acg12ScheduleJobEntity.jobGroup)!''}"/>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">0 未开启 1 开启</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="jobStatus" class="form-control" maxlength="200"
                                               value="${(acg12ScheduleJobEntity.jobStatus)!''}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/row-->

                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">cron表达式</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="cronExpression" class="form-control" maxlength="200"
                                               value="${(acg12ScheduleJobEntity.cronExpression)!''}"/>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">描述</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="description" class="form-control" maxlength="200"
                                               value="${(acg12ScheduleJobEntity.description)!''}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/row-->

                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">任务执行时调用哪个类的方法 包名+类名</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="beanClass" class="form-control" maxlength="200"
                                               value="${(acg12ScheduleJobEntity.beanClass)!''}"/>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">任务是否有状态</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="isConcurrent" class="form-control" maxlength="200"
                                               value="${(acg12ScheduleJobEntity.isConcurrent)!''}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/row-->

                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">任务调用的方法名</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="methodName" class="form-control" maxlength="200"
                                               value="${(acg12ScheduleJobEntity.methodName)!''}"/>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">spring bean</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="springId" class="form-control" maxlength="200"
                                               value="${(acg12ScheduleJobEntity.springId)!''}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/row-->

                        [#--<div class="form-group">--]
                            [#--<div class="row">--]
                                [#--<div class="col-sm-6">--]
                                    [#--<label class="col-sm-3 control-label">创建时间</label>--]
                                    [#--<div class="col-sm-9">--]
                                        [#--<input type="text" name="createTime" class="form-control" maxlength="200"--]
                                               [#--value="${(acg12ScheduleJobEntity.createTime?string('yyyy-MM-dd'))!''}"/>--]
                                    [#--</div>--]
                                [#--</div>--]
                                [#--<div class="col-sm-6">--]
                                    [#--<label class="col-sm-3 control-label">更新时间</label>--]
                                    [#--<div class="col-sm-9">--]
                                        [#--<input type="text" name="updateTime" class="form-control" maxlength="200"--]
                                               [#--value="${(acg12ScheduleJobEntity.updateTime?string('yyyy-MM-dd'))!''}"/>--]
                                    [#--</div>--]
                                [#--</div>--]
                            [#--</div>--]
                        [#--</div>--]
                        <!--/row-->
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-12 col-sm-offset-10">
                                <button type="button" class="btn btn-default" onclick="window.history.back();">返回
                                </button>
                                <button class="btn btn-success" type="submit">保存内容</button>
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
<script>
    $(document).ready(function () {
        	[@flash_message /]
        $(".chosen-select").chosen({width: "100%"});
        $(".input-daterange").datepicker({keyboardNavigation: false, forceParse: false, autoclose: true});

        $(".browserButton").browser({
            callback: function (url) {
                $("#" + $(this).attr('for')).val(url);
            }
        });

        $(".input-group.date").datepicker({
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            calendarWeeks: true,
            autoclose: true
        });
        $("#inputForm").validate({
            rules: {},
            messages: {}
        });
    });
</script>
</body>
</html>
