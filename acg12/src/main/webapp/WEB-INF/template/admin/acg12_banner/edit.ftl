<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 编辑banner</title>
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
                    <h5>编辑banner</h5>
                    <div class="ibox-tools">
                        <button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi"
                                onclick="location.href='${base}/admin/acg12_banner/list.html'"><i
                                class="fa fa-reply-all"></i> 返回列表
                        </button>
                    </div>
                </div>
                <div class="ibox-content">
                    <form id="inputForm" class="form-horizontal" action="${base}/admin/acg12_banner/update.jhtml"
                          method="post">
                        <input type="hidden" value="${acg12Banner.id}" name="id"/>

                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">标题</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="title" class="form-control" maxlength="200"
                                               value="${(acg12Banner.title)!''}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/row-->

                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label"><span class="required"> * </span>类型</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="type" class="form-control" maxlength="200"
                                               value="${(acg12Banner.type)!''}"/>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">类型名</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="typeName" class="form-control" maxlength="200"
                                               value="${(acg12Banner.typeName)!''}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/row-->

                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">锁定</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="isLock" class="form-control" maxlength="200"
                                               value="${(acg12Banner.isLock)!''}"/>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">图片</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="cover" class="form-control" maxlength="200"
                                               value="${(acg12Banner.cover)!''}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/row-->

                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">排序</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="sort" class="form-control" maxlength="200"
                                               value="${(acg12Banner.sort)!''}"/>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">内容</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="content" class="form-control" maxlength="200"
                                               value="${(acg12Banner.content)!''}"/>
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
            rules: {
                type: {
                    required: true
                },
            },
            messages: {}
        });
    });
</script>
</body>
</html>
