<!-- 全局js -->
<script src="${base}/resources/js/jquery-2.1.1.min.js"></script>
<script src="${base}/resources/js/bootstrap.min.js?v=3.4.0"></script>

<!-- Peity -->
<script src="${base}/resources/js/plugins/peity/jquery.peity.min.js"></script>

<!-- 自定义js -->
<script src="${base}/resources/js/content.js?v=1.0.0"></script>

<!-- iCheck -->
<script src="${base}/resources/js/plugins/iCheck/icheck.min.js"></script>

<!-- Peity -->
<script src="${base}/resources/js/demo/peity-demo.js"></script>

<!-- Sweet alert -->
<script src="${base}/resources/js/plugins/sweetalert/sweetalert.min.js"></script>

<!-- layer  -->
<script src="${base}/resources/js/plugins/layer/layer.min.js"></script>

<!-- Bootbox  -->
<script src="${base}/resources/js/plugins/bootbox/bootbox.min.js"></script>

<!-- Chosen -->
<script src="${base}/resources/js/plugins/chosen/chosen.jquery.js"></script>

<!-- Jquery Validate -->    
<script src="${base}/resources/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${base}/resources/js/plugins/validate/messages_zh.min.js"></script>

<!-- Prettyfile -->
<script src="${base}/resources/js/plugins/prettyfile/bootstrap-prettyfile.js"></script>

 <!--kindeditor-->
<script src="${base}/resources/plugins/editor/kindeditor.js" type="text/javascript"></script>

<!-- Data picker -->
<script src="${base}/resources/js/plugins/datapicker/bootstrap-datepicker.js"></script>

<!-- Artdialog -->
<script src="${base}/resources/plugins/artdialog/artDialog.min.js?skin=default" type="text/javascript"></script>
<script src="${base}/resources/plugins/artdialog/artDialog.plugins.js" type="text/javascript"></script>

<!-- jsTree plugin javascript -->
<script src="${base}/resources/js/plugins/jsTree/jstree.min.js"></script>

<!-- Loippi Javascript -->    
<script src="${base}/resources/js/admin/common.js" type="text/javascript"></script>
<script src="${base}/resources/js/admin/list.js" type="text/javascript"></script>
<script src="${base}/resources/js/admin/jquery.lSelect.js" type="text/javascript"></script>

<script src="${base}/resources/js/plugins/layer/laydate/laydate.js"></script>


<link href="${base}/resources/js/plugins/bootstraptable/bootstrap-table.css?t=2" rel="stylesheet"/>
<script src="${base}/resources/js/plugins/bootstraptable/bootstrap-table.js?t=3" type="text/javascript"></script>
<script src="${base}/resources/js/plugins/bootstraptable/bootstrap-table-zh-CN.js" type="text/javascript"></script>

 <!-- Full Calendar -->
<script src="${base}/resources/js/plugins/fullcalendar/fullcalendar.min.js"></script>
<script src="${base}/resources/js/jquery-ui.custom.min.js"></script>

<!-- bootstrap select-->
<link href="${base}/resources/js/plugins/bootstrapselect/bootstrap-select.css" rel="stylesheet"/>
<script src="${base}/resources/js/plugins/bootstrapselect/bootstrap-select.min.js"></script>

<!-- bootstrap Resizable-->
<script src="${base}/resources/js/plugins/bootstraptable-resizable/colResizable-1.6.js"></script>
<script src="${base}/resources/js/plugins/bootstraptable-resizable/bootstrap-table-resizable.js"></script>
<#--上传文件-->
<script src="${base}/resources/plugins/webupload/HHuploadify.js"></script>
  <script>
      /**************************************获取地址参数************************************/
      function getUrlParam(name) {
          var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
          var r = window.location.search.substr(1).match(reg);  //匹配目标参数
          if (r != null) return unescape(r[2]);
          return null; //返回参数值
      }


      /**************************************时间格式化处理************************************/
      function dateFtt(fmt, date) { //author: meizz
          var o = {
              "M+": date.getMonth() + 1,                 //月份
              "d+": date.getDate(),                    //日
              "h+": date.getHours(),                   //小时
              "m+": date.getMinutes(),                 //分
              "s+": date.getSeconds(),                 //秒
              "q+": Math.floor((date.getMonth() + 3) / 3), //季度
              "S": date.getMilliseconds()             //毫秒
          };
          if (/(y+)/.test(fmt))
              fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
          for (var k in o)
              if (new RegExp("(" + k + ")").test(fmt))
                  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
          return fmt;
      }

      /**
       * 将form序列成对象
       * @returns {{}}
       */
      $.fn.serializeObject = function () {
          var o = {}
          var a = this.serializeArray();
          $.each(a, function () {
              if (o[this.name] !== undefined) {
                  if (!o[this.name].push) {
                      o[this.name] = [o[this.name]]
                  }
                  o[this.name].push(this.value || '')
              } else {
                  o[this.name] = this.value || ''
              }
          })
          var $radio = $('input[type=radio],input[type=checkbox]', this)
          $.each($radio, function () {
              if (!o.hasOwnProperty(this.name)) {
                  o[this.name] = ''
              }
          })
          return o;
      }

      /**
       * ajax请求加loading
       * @param params
       */
      var ajaxReq = function (params) {

          var index = layer.load(1, {
              shade: [0.1, '#fff'] //0.1透明度的白色背景
          });

          params.dataType = params.dataType || 'json'

          var success = params.success
          params.success = function (res) {
              console.log(params.url + " ---> ", res)
              layer.close(index)
              success(res)
          }

          params.error = function (e) {
              layer.msg('提交失败', {icon: 2, time: 2000})
              layer.close(index)
          }

          $.ajax(params)
      }

  </script>

<#--<script src="${base}/resources/js/jumper.js"></script>-->

<script>
    /**
     * 上传图片
     * @param container   图片div  #upload
     * @param inputValue  存图片url的input选择器 #headerImage
     * @param cdnSel      上传到那个域名
     * @param chooseText  文字    请选择图片
     * @param baseUrl     服务地址
     * @param folderName   前缀，及文件夹名称
     * @author hjc
     */
    function initHHuploadify(container, inputValue, cdnSelector, chooseText, baseUrl, folderName, files) {

        var options = {
            container: container,
            inputValue: inputValue,
            cdnSelector: cdnSelector,
            auto: true,
            chooseText: chooseText,
            single: true,
            files: files || [],
            preProcessOptions: function (options, file) {

                var token = jumper.getQiniuToken(options.cdnSelector, baseUrl, folderName, file);
                options.url = jumper.QN_Url + "?token=" + token
            }
        }
        new HHuploadify(options)
    }
</script>

