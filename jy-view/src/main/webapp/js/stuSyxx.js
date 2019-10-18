
$(document).ready(function () {
    $('#syd').chosen();

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
            xsxh:"required",
            csrq:"required",
            xb:"required",
            mz:"required",
            zzmm:"required",
            sfz:"required",
            jtdz:"required",
            syd:"required",
            rxnf:"required",
            bynf:"required",
            byrq:"required",
            cxsy:"required",
            ksh:"required",
            xxmc:"required",
            xz:"required",
            ssxy:"required",
            xszy:"required",
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
            sjh:{
                required: true,
                isMobile: true
            },
            yx:{
                email: true
            },
        },
        messages: {
            xm:"请填写",
            xsxh:"请填写",
            csrq:"请填写",
            xb:"请选择",
            mz:"请选择",
            zzmm:"请选择",
            sfz:"请填写",
            jtdz:"请填写",
            syd:"请选择",
            rxnf:"请填写",
            bynf:"请填写",
            byrq:"请填写",
            cxsy:"请选择",
            ksh:"请填写",
            xxmc:"请填写",
            xz:"请选择",
            ssxy:"请填写",
            xszy:"请填写",
            szbj:"请填写",
            pyfs:"请选择",
            wpdw:"请填写",
            knslb:"请选择",
            sfslb:"请选择",
            qqhm:"请填写",
            jtdh:"请填写",
            jtyb:"请填写",
            jtyb:"请填写",
            xlcc:"请选择",
            sjh:  {
                required: "请填写",
                email: "手机号有误"
            },
            yx: {
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

    $("#xb").val($("#xb").attr("data-val"))
    $("#mz").val($("#mz").attr("data-val"))
    $("#zzmm").val($("#zzmm").attr("data-val"))
    $("#syd1").val($("#syd1").attr("data-val"))
    $("#syd").parent().find('.chosen-container  a.chosen-single span').html($("#syd1 option:selected").html())
    $("#cxsy").val($("#cxsy").attr("data-val"))
    $("#xz").val($("#xz").attr("data-val"))
    $("#pyfs").val($("#pyfs").attr("data-val"))
    $("#knslb").val($("#knslb").attr("data-val"))
    $("#sfslb").val($("#sfslb").attr("data-val"))
    $("#xlcc").val($("#xlcc").attr("data-val"))
    $("#sfrx").val($("#sfrx").attr("data-val"))
    $("#hkrx").val($("#hkrx").attr("data-val"))

    // getJyBaseInfo()
})


function getJyBaseInfo() {

    var jsonObj ={
        "owid":getCookie("stuOwid")
    }
    ajax("zustcommon/bckjBizSyb/getSyInfo", jsonObj, function (data) {
        if(data.backCode==0){
            if(data.bean){


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
    if(!isTimeOut()) {
        var jsonObj = $("#registerForm").serializeObject()
        jsonObj.xsxh = $("#xsxh").val();
        if($("#owid").val()){
            jsonObj.owid = $("#owid").val();
        }
        ajax("zustcommon/bckjBizSyb/saveSybInfo", jsonObj, function (data) {
            if (data.backCode == 0) {
                walert("保存成功")
            } else {
                walert(data.errorMess)
            }
        })
    }
}
