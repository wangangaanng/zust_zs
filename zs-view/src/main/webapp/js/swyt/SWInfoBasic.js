/**
 * Created by Xia Yu on 2019/11/04.
 * 三位一体保存基本信息
 */
$(function () {
    //浏览器不要自动填充
    $('input:not([autocomplete]),textarea:not([autocomplete]),select:not([autocomplete])').attr('autocomplete', 'off');

    // 身份证号码验证
    $.validator.addMethod("isIdCardNo", function(value, element) {
        return this.optional(element) || isIdCardNo(value);
    }, "请输入正确的身份证号码");


    //表单验证规则
    $("#basicForm").validate({
        rules: {
            xm: "required",
            sfzh: {
                required: true,
                isIdCardNo: true
            },
            xb: "required",
            mz: "required",
            jtzz: "required",
            lxdh: { //联系电话
                required: true,
                tel: true,
            },
            yx: { //邮箱
                required: true,
                email:true
            },
            qq:"required"
        },
        errorElement: "em",
        errorPlacement: function ( error, element ) {
            // Add the `help-block` class to the error element
            error.addClass( "help-block" );

            if ( element.prop( "type" ) === "checkbox" ) {
                error.insertAfter( element.parent( "label" ) );
            } else {
                error.insertAfter( element );
            }
        },
        highlight: function ( element, errorClass, validClass ) {
            $( element ).parent().addClass( "has-error" ).removeClass( "has-success" );
        },
        unhighlight: function (element, errorClass, validClass) {
            $( element ).parent().removeClass( "has-error" );
        }
    });
});

//表单验证触发保存基本信息
$.validator.setDefaults({
    submitHandler: function () {
        saveBasic();
        //return;
    }
});

//保存基本信息
function saveBasic() {
    if(!isTimeOut()){
        var jsonObj = $("#basicForm").serializeObject();
        jsonObj.yhRefOwid=getCookie("yhRefOwid");
        console.log(jsonObj)
        ajax("zustswyt/bckjBizJbxx/finishInfo", jsonObj, function (data) {
            if(data.backCode==0){
                $("#basicForm").hide();
                $("#contactForm").show();
                $(".jf-items .jf-item").eq(1).addClass("jf-active");
            }else{
                walert(data.errorMess)
            }
        })
    }
}

