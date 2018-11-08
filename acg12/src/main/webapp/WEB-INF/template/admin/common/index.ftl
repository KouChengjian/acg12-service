<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 系统信息</title>
    <meta name="keywords" content="${setting.siteName}">
    <meta name="description" content="${setting.siteName}">

    <link href="${base}/resources/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="${base}/resources/css/font-awesome.min.css?v=4.3.0" rel="stylesheet">
    <link href="${base}/resources/css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="${base}/resources/css/animate.css" rel="stylesheet">
    <link href="${base}/resources/css/style.css?v=3.0.0" rel="stylesheet">
    <!-- iCheck -->
    <link href="${base}/resources/css/plugins/iCheck/custom.css" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="${base}/resources/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="${base}/resources/css/plugins/datapicker/datepicker3.css" rel="stylesheet">

    <style>
        html, body, div, span, input, p, h1, h2, h3, h4, h5, h6, blockquote, a, abbr, acronym, address, strong, b, u, i, center, dl, dt, dd, ol, ul, td, tr {
            font-family: "Microsoft Yahei", "微软雅黑", "宋体", "Simsun", "Open Sans";
        }
    </style>
</head>

<body class="gray-bg">


<div class="wrapper wrapper-content  animated fadeInRight">
    <!--第一个表BEGIN-->
    <div class="row">
        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <span style="font-weight:bold;"> 系统信息</span>
                </div>
                <div class="ibox-content">
                    <p><i class="fa fa-laptop"></i>欢迎 ${nickname}
                    </p>
                    <p>最后登录时间：${logindate} ${loginaddress}
                    </p>
                </div>
            </div>


        </div>
    </div>
</div>
</div>
</div>
</div>
</div>

<!-- 全局js -->
<script src="${base}/resources/js/jquery-2.1.1.min.js"></script>
<script src="${base}/resources/js/bootstrap.min.js?v=3.4.0"></script>
<!-- Peity -->
<script src="${base}/resources/js/plugins/peity/jquery.peity.min.js"></script>
<!-- 自定义js -->
<script src="${base}/resources/js/content.js?v=1.0.0"></script>
<!-- iCheck -->
<script src="${base}/resources/js/plugins/iCheck/icheck.min.js"></script>
<!-- layer javascript -->
<script src="${base}/resources/js/plugins/layer/layer.min.js"></script>
<script src="${base}/resources/js/plugins/bootbox/bootbox.min.js"></script>
<script src="${base}/resources/js/admin/common.js" type="text/javascript" ></script>
</body>

</html>
