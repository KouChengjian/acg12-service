<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - person详情</title>
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
                    <h5>person详情</h5>
                    <div class="ibox-tools">
                        <button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi"
                                onclick="location.href='${base}/admin/acg12_person/list.jhtml'"><i
                                class="fa fa-reply-all"></i> 返回列表
                        </button>
                    </div>
                </div>
                <div class="ibox-content">
                    <form id="inputForm" class="form-horizontal" action="update.jhtml" method="post">
                        <input type="hidden" value="${acg12Person.id}"
                               name="id"/>

                                <div class="form-group">
                                    <div class="row">
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        pId</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.pId}</p>
                                                    </div>
                                                </div>
                                    </div>
                                </div>
                            <!--/row-->
                                <div class="form-group">
                                    <div class="row">
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        名称</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.name}</p>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        中文名称</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.nameCn}</p>
                                                    </div>
                                                </div>
                                    </div>
                                </div>
                            <!--/row-->
                                <div class="form-group">
                                    <div class="row">
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        图片</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.image}</p>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        简介</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.summary}</p>
                                                    </div>
                                                </div>
                                    </div>
                                </div>
                            <!--/row-->
                                <div class="form-group">
                                    <div class="row">
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        身高</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.height}</p>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        体重</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.weight}</p>
                                                    </div>
                                                </div>
                                    </div>
                                </div>
                            <!--/row-->
                                <div class="form-group">
                                    <div class="row">
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        别名</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.alias}</p>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                         1、声优 2、漫画家 3、制作人 4、音乐人 5、 6、演员 7、绘师 8、作家</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.type}</p>
                                                    </div>
                                                </div>
                                    </div>
                                </div>
                            <!--/row-->
                                <div class="form-group">
                                    <div class="row">
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        // 1、男 2、女</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.gender}</p>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        血型  1、A   2、B   3、AB   4、O</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.bloodtype}</p>
                                                    </div>
                                                </div>
                                    </div>
                                </div>
                            <!--/row-->
                                <div class="form-group">
                                    <div class="row">
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        生日</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.birthday}</p>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        </label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.createtime}</p>
                                                    </div>
                                                </div>
                                    </div>
                                </div>
                            <!--/row-->
                                <div class="form-group">
                                    <div class="row">
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        </label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.updatetime}</p>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        创建时间</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.createTime}</p>
                                                    </div>
                                                </div>
                                    </div>
                                </div>
                            <!--/row-->
                                <div class="form-group">
                                    <div class="row">
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        更新时间</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12Person.updateTime}</p>
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
