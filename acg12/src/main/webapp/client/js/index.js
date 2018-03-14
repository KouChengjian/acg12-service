

$('页面中的某个按钮等标签').on('click',function(){

    $.ajax({
        type: "post",
        url: '/config/scm/env/add',
        data: $("#envForm").serialize(),
        dataType: 'json',

        success: function (data) {
            if (data.errorMsg) {
                $.alert("错误", data.errorMsg);
            } else {
                $('.edit-env-modal').modal('hide');
                $.toast("保存环境成功,正在刷新...", "success", "center");
                window.location.reload();
            }
        },

        error: function (e) {
            $.alert("失败", e);
        },

        complete: function (XMLHttpRequest, textStatus) {
            $('.submitEnvModal').attr('disabled', false);
            $('select[name="type"]').attr('disabled', false);
        }
    });

})