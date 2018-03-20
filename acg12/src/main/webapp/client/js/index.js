

document.getElementsByClassName("go")[0].onclick = function () {
    var key = document.getElementById("query").value;
    console.log(key);
}

document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if(e && e.keyCode==27){ // 按 Esc
        //要做的事情
    }
    if(e && e.keyCode==113){ // 按 F2
        //要做的事情
    }
    if(e && e.keyCode==13){ // enter 键
        //要做的事情
        var key = document.getElementById("query").value;
        console.log(key);
    }
};

// $('页面中的某个按钮等标签').on('click',function(){
//
//     $.ajax({
//         type: "post",
//         url: '/config/scm/env/add',
//         data: $("#envForm").serialize(),
//         dataType: 'json',
//
//         success: function (data) {
//             if (data.errorMsg) {
//                 $.alert("错误", data.errorMsg);
//             } else {
//                 $('.edit-env-modal').modal('hide');
//                 $.toast("保存环境成功,正在刷新...", "success", "center");
//                 window.location.reload();
//             }
//         },
//
//         error: function (e) {
//             $.alert("失败", e);
//         },
//
//         complete: function (XMLHttpRequest, textStatus) {
//             $('.submitEnvModal').attr('disabled', false);
//             $('select[name="type"]').attr('disabled', false);
//         }
//     });
//
// })