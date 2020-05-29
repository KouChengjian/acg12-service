/**
 * jumper common js function
 */
(function($){
    var jumper = function () { }

    jumper.prototype = {

        integralAmuoutCheck : function(priceSelector,integralAmountSelector,integralSelector,param){
            /**
             * 金额换算
             */
            $(integralAmountSelector).bind('input propertychange', function() {
                var amount = $(integralAmountSelector).val();
                var number;

                if (amount == '') {
                    number = 0;
                } else {
                    number = parseFloat(amount);
                }

                var price = $(priceSelector).val();
                if (number > parseFloat(price == '' ? '0' : price)) {
                    layer.msg('抵扣金额不能大与支付金额',{icon:2,time:2000})
                    $(integralAmountSelector).val('0');
                    $(integralSelector).val('0');
                    return;
                }

                if (number < 0 || (number < 1 && number > 0)){
                    layer.msg('抵扣金额最少是1元')
                    $(integralAmountSelector).val('0');
                    $(integralSelector).val('0');
                    return;
                }
                $(integralSelector).val(number * parseInt(param));
            });
        },
        getQiniuToken : function (cdnSelector,baseUrl,folderName,file) {

            var timestamp = new Date().getTime();
            var randoms = Math.floor(Math.random() * 100) + 9000;
            timestamp = timestamp + 'K' + randoms;

            var key = "sy-app/bangshou/" + folderName + timestamp + file.type + this.getSuffix(file.name);

            var token = ""
            ajaxReq({
                url:baseUrl + '/admin/qnToken.json',
                data:{
                    token: $(cdnSelector).val(),
                    key:key
                },
                async:false,
                dataType:'text',
                success:function(res){
                    token = res;
                }
            })
            return token;
        },
        /**
         * 取文件名后缀
         */
        getSuffix:function (fileName) {

            var index1 = fileName.lastIndexOf(".");
            var index2 = fileName.length;
            return fileName.substring(index1,index2);
        },
        /**
         * 七牛url
         */
        QN_Url : 'http://up-z2.qiniup.com',
        /**
         * prefix
         */
        getPrefix:function (cdnSelector) {
            switch ($(cdnSelector).val()) {
                case 'QINIU_PUBLIC_TOKEN':
                    return "http://public.api.acg12.com/"
                case 'QINIU_PROTECTED_TOKEN':
                    return "http://protected.api.acg12.com/"
                case 'QINIU_PRIVATE_TOKEN':
                    return "http://private.api.acg12.com/"
            }
            return "";
        }
    }

    window.jumper = new jumper();
})(jQuery);



