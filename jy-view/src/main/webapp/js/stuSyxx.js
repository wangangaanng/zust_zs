
$(document).ready(function () {

    $(".list-group-item").click(function(e) {
        var index=$(this).index()
        window.location.href="/stuCenter/"+index
    })

    laydate.render({
        elem: '#csrq' //指定元素
    });

    laydate.render({
        elem: "#rxnf" //指定元素
    });

    laydate.render({
        elem: "#byrq" //指定元素
    });

    //年选择器
    laydate.render({
        elem: '#bynf'
        ,type: 'year'
    });

    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^1[345789]\d{9}$/;/*/^1(3|4|5|7|8)\d{9}$/*/
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写您的手机号码");

    $("#registerForm").validate({
        rules: {
            xm:"required",
            xh:"required",
            csrq:"required",
            xb:"required",
            mz:"required",
            zzmm:"required",
            sfzh:"required",
            jtdz:"required",
            syd:"required",
            rxnf:"required",
            bynf:"required",
            byrq:"required",
            cxsy:"required",
            xqda:"required",
            sfrx:"required",
            hkpcs:"required",
            ksh:"required",
            hkrx:"required",
            ssxx:"required",
            xz:"required",
            ssxy:"required",
            xxzy:"required",
            szbj:"required",
            pyfs:"required",
            wpdw:"required",
            knslb:"required",
            sfslb:"required",
            qqhm:"required",
            jtdh:"required",
            jtyb:"required",
            jtyb:"required",
            xlcc:"required",
            sjhm:{
                required: true,
                isMobile: true
            },
            dzyx:{
                required: true,
                email: true
            },
        },
        messages: {
            xm:"请填写",
            xh:"请填写",
            csrq:"请填写",
            xb:"请选择",
            mz:"请填写",
            zzmm:"请填写",
            sfzh:"请填写",
            jtdz:"请填写",
            syd:"请填写",
            rxnf:"请填写",
            bynf:"请填写",
            byrq:"请填写",
            cxsy:"请填写",
            xqda:"请填写",
            sfrx:"请选择",
            hkpcs:"请填写",
            ksh:"请填写",
            hkrx:"请选择",
            ssxx:"请填写",
            xz:"请填写",
            ssxy:"请填写",
            xxzy:"请填写",
            szbj:"请填写",
            pyfs:"请填写",
            wpdw:"请填写",
            knslb:"请填写",
            sfslb:"请填写",
            qqhm:"请填写",
            jtdh:"请填写",
            jtyb:"请填写",
            jtyb:"请填写",
            xlcc:"请填写",
            sjhm:  {
                required: "请填写",
                email: "手机号有误"
            },
            dzyx: {
                required: "请填写",
                email: "邮箱有误"
            },
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

    $("#xb").val($("#sel1").val())
    $("#sfrx").val($("#sel2").val())
    $("#hkrx").val($("#sel3").val())
    getSyInfo()
})

function getSyInfo() {

    var jsonObj ={
        "owid":getCookie("stuOwid")
    }
    ajax("zustjy/bckjBizSyb/getSyInfo", jsonObj, function (data) {
        if(data.backCode==0){
            if(data.bean){
                // if(data.bean.xh){
                //     $("#xh").val(data.bean.xh)
                //     $("#xh").attr("disabled","disabled")
                // }
            }
        }else{
            walert(data.errorMess)
        }
    })
}

$.validator.setDefaults({
    submitHandler: function () {
        saveSyxx()
    }
});

function saveSyxx() {
    var jsonObj = $("#registerForm").serializeObject()
    jsonObj.xh=$("#xh").val();
    ajax("zustjy/bckjBizSyb/saveSyxx", jsonObj, function (data) {
        if(data.backCode==0){
            walert("保存成功")
        }else{
            walert(data.errorMess)
        }
    })
}
