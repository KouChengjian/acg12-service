<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 主页</title>
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
                    <h5>添加管理员</h5>
                    <div class="ibox-tools">
                        <button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi"
                                onclick="location.href='list.html'"><i class="fa fa-reply-all"></i> 返回列表
                        </button>
                    </div>
                </div>
                <div class="ibox-content">
                    <form id="saveAclForm" class="form-horizontal" action="save.html" method="post">

                        <div class="form-group">
                            <div class="row">

                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label"><span class="required"> * </span>登录账户</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="username" id="username" class="form-control"
                                               maxlength="20">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label"><span class="required"> * </span>姓名</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="nickname" id="nickname" class="form-control"
                                               maxlength="10">
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="form-group">
                            <div class="row">

                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label"><span class="required"> * </span>角色</label>
                                    <div class="col-sm-9">
                                        <select name="roleId" id="roleId" data-placeholder="选择角色..."
                                                class="chosen-select" style="width:350px;">
                                     		[#list roles as role]
                                                <option hassubinfo="true" value="${role.id}">${role.name}</option>
                                            [/#list]
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label"><span class="required"> * </span>密码</label>
                                    <div class="col-sm-9">
                                        <input type="password" name="password" id="password" class="form-control"
                                               maxlength="20">
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="form-group">
                            <div class="row">

                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label"><span class="required"> * </span>重复密码</label>
                                    <div class="col-sm-9">
                                        <input type="password" name="repassword" id="repassword" class="form-control"
                                               maxlength="20">
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label"><span class="required"> * </span>手机号</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="phone" id="phone" class="form-control" maxlength="20">
                                    </div>
                                </div>

                            </div>
                        </div>


                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label"><span class="required"> * </span>性别</label>
                                    <div class="col-sm-9">
                                        <select name="sex" id="sex" data-placeholder="选择性别..." class="chosen-select"
                                                style="width:350px;">
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                    </div>
                                </div>

                            </div>
                        </div>


                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label"><span class="required"> * </span>身份证</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="idcard" id="idcard" class="form-control"
                                               maxlength="50">
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label"><span class="required"> * </span>详细地址</label>
                                    <div class="col-sm-9">
                                        <input type="text" name="address" id="address" class="form-control"
                                               maxlength="100">
                                    </div>
                                </div>

                            </div>
                        </div>


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

    jQuery("#saveAclForm").validate({
        rules: {
            username: {
                required: true,
                pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
                minlength: 2,
                maxlength: 20,
                remote: {
                    url: "check_username.jhtml",
                    cache: false
                }
            },
            password: {
                required: true,
                pattern: /^[^\s&\"<>]+$/,
                minlength: 4,
                maxlength: 20
            },
            repassword: {
                required: true,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true
            },

            roleId: {required: true},
            nickname: {required: true},
            phone: {required: true},
            sex: {required: true},
            address: {required: true},
            idcard: {required: true}
        },
        messages: {
            username: {
                pattern: "${message("admin.validate.illegal")}",
                remote: "${message("admin.validate.exist")}"
            },
            password: {
                pattern: "${message("admin.validate.illegal")}"
            }
        }
    });

    $(document).ready(function () {
        	[@flash_message /]
        var $browserButton = $("#browserButton");
        $browserButton.browser({input: $("#avatar")});
        $(".chosen-select").chosen({width: "100%"});

        $('.i-checks').iCheck({checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-green',});

    });

</script>
</body>

</html>
