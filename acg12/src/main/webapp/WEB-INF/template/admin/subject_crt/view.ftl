<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - subjectCrt详情</title>
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
                    <h5>subjectCrt详情</h5>
                    <div class="ibox-tools">
                        <button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi"
                                onclick="location.href='${base}/admin/acg12_subject_crt/list.jhtml'"><i
                                class="fa fa-reply-all"></i> 返回列表
                        </button>
                    </div>
                </div>
                <div class="ibox-content">
                    <form id="inputForm" class="form-horizontal" action="update.jhtml" method="post">
                        <input type="hidden" value="${acg12SubjectCrt.id}"
                               name="id"/>

                                <div class="form-group">
                                    <div class="row">
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        </label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12SubjectCrt.subjectId}</p>
                                                    </div>
                                                </div>
                                    </div>
                                </div>
                            <!--/row-->
                                <div class="form-group">
                                    <div class="row">
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        外部获取的id</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12SubjectCrt.sId}</p>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        外部获取的id - 虚拟人物</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12SubjectCrt.cId}</p>
                                                    </div>
                                                </div>
                                    </div>
                                </div>
                            <!--/row-->
                                <div class="form-group">
                                    <div class="row">
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        外部获取的id - 真实人物</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12SubjectCrt.pId}</p>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        名称</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12SubjectCrt.name}</p>
                                                    </div>
                                                </div>
                                    </div>
                                </div>
                            <!--/row-->
                                <div class="form-group">
                                    <div class="row">
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        中文名</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12SubjectCrt.nameN}</p>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        角色名</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12SubjectCrt.roleName}</p>
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
                                                        <p class="form-control-static">${acg12SubjectCrt.image}</p>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        cv-名称</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12SubjectCrt.pName}</p>
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
                                                        <p class="form-control-static">${acg12SubjectCrt.pNameCn}</p>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        </label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12SubjectCrt.pImage}</p>
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
                                                        <p class="form-control-static">${acg12SubjectCrt.createtime}</p>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        </label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12SubjectCrt.updatetime}</p>
                                                    </div>
                                                </div>
                                    </div>
                                </div>
                            <!--/row-->
                                <div class="form-group">
                                    <div class="row">
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        创建时间</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12SubjectCrt.createTime}</p>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="col-sm-3 control-label">
                                                        修改时间</label>
                                                    <div class="col-sm-9">
                                                        <p class="form-control-static">${acg12SubjectCrt.updateTime}</p>
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
