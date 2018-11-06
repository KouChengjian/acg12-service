<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0052)http://b2b2c.leimingtech.com/leimingtech-admin/login -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${setting.siteName}</title>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${base}/resources/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${base}/resources/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${base}/resources/css/animate.css" rel="stylesheet">
    <link href="${base}/resources/css/style.css?v=4.1.0" rel="stylesheet">

    <script src="${base}/resources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="${base}/resources/js/admin/jsbn.js" type="text/javascript"></script>
    <script src="${base}/resources/js/admin/prng4.js" type="text/javascript"></script>
    <script src="${base}/resources/js/admin/rng.js" type="text/javascript"></script>
    <script src="${base}/resources/js/admin/rsa.js" type="text/javascript"></script>
    <script src="${base}/resources/js/admin/base64.js" type="text/javascript"></script>
    <script src="${base}/resources/js/admin/common.js" type="text/javascript"></script>

    <link href="${base}/resources/plugins/artdialog/skins/simple.css" rel="stylesheet" type="text/css">
    <script src="${base}/resources/plugins/artdialog/artDialog.min.js?skin=default" type="text/javascript"></script>
    <script src="${base}/resources/plugins/artdialog/artDialog.plugins.js" type="text/javascript"></script>
    [#--<!--[if lt IE 9]>--]
    [#--<meta http-equiv="refresh" content="0;ie.html"/>--]
    [#--<![endif]-->--]
    [#--<script>if (window.top !== window.self) {--]
        [#--window.top.location = window.location;--]
    [#--}</script>--]
    <script>
        $().ready(function () {
            [@flash_message /]

            var $loginForm = $("#loginForm");
            var $username = $("#username");
            var $password = $("#password");
            var $enPassword = $("#enPassword");
            var $isRememberUsername = $("#isRememberUsername");
            // 记住用户名
            if (getCookie("adminUsername") != null) {
                $isRememberUsername.prop("checked", true);
                $username.val(getCookie("adminUsername"));
                $password.focus();
            } else {
                $isRememberUsername.prop("checked", false);
                $username.focus();
            }
            // 表单验证、记住用户名
            $loginForm.submit(function () {
                if ($username.val() == "") {
                    art.warn("${message("admin.login.usernameRequired")}");
                    return false;
                }
                if ($password.val() == "") {
                    art.warn("${message("admin.login.passwordRequired")}");
                    return false;
                }
                if ($isRememberUsername[0]) {
                    if ($isRememberUsername.prop("checked")) {
                        addCookie("adminUsername", $username.val(), {expires: 7 * 24 * 60 * 60});
                    } else {
                        removeCookie("adminUsername");
                    }
                }
                var rsaKey = new RSAKey();
                rsaKey.setPublic(b64tohex("${modulus}"), b64tohex("${exponent}"));
                var enPassword = hex2b64(rsaKey.encrypt($password.val()));
                $enPassword.val(enPassword);
            });
        });
    </script>


</head>
<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>
            <h1 class="logo-name">漫友</h1>
        </div>
        <h3>欢迎使用 漫友</h3>

        <form class="m-t" id="loginForm" role="form" action="${base}/admin/login.html" method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="username" id="username" placeholder="用户名">
                <input type="hidden" id="enPassword" name="enPassword" required=""/>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="password" id="password" placeholder="密码">
            </div>
            <button name="submit" class="btn btn-primary block full-width m-b">登 录</button>

            <p class="text-muted text-center"><a href="login.html#">
                <small>忘记密码了？</small>
            </a> | <a href="register.html">注册一个新账号</a>
            </p>

        </form>
    </div>
</div>

[#--<!-- 全局js -->--]
[#--<script src="js/jquery.min.js?v=2.1.4"></script>--]
[#--<script src="js/bootstrap.min.js?v=3.3.6"></script>--]

</body>
</html>