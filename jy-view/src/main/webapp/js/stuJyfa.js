
$(document).ready(function () {

    $(".list-group-item").click(function(e) {
        var index=$(this).index()
        window.location.href="/stuCenter/"+index
    })

    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^1[345789]\d{9}$/;/*/^1(3|4|5|7|8)\d{9}$/*/
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写您的手机号码");

    $("#registerForm").validate({
        rules: {
            xm:"required",
            sfz:"required",
            xb:"required",
            xxmc:"required",
            xsxy:"required",
            xszy:"required",
            zyfx:"required",
            pyfs:"required",
            syd:"required",
            sjh:{
                required: true,
                isMobile: true
            },
            byqx:"required",
            yrdwmc:"required",
            yrdwdm:"required",
            yrdwxz:"required",
            dwlbmc:"required",
            dwszdmc:"required",
            gzzwlbmc:"required",
            sfzydk:"required",
            datddw:"required",
        },
        messages: {
            xm:"请填写",
            sfz:"请填写",
            xb:"请选择",
            xxmc:"请填写",
            xsxy:"请填写",
            xszy:"请填写",
            zyfx:"请填写",
            pyfs:"请填写",
            syd:"请填写",
            sjh:  {
                required: "请填写",
                email: "手机号有误"
            },
            byqx:"请填写",
            yrdwmc:"请填写",
            yrdwdm:"请填写",
            yrdwxz:"请填写",
            dwlbmc:"请填写",
            dwszdmc:"请填写",
            gzzwlbmc:"请填写",
            sfzydk:"请选择",
            datddw:"请填写",
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

    $("#sfzydk").val($("#sel1").val())
    $("#sfdydwbdz").val($("#sel2").val())
    $("#xb").val($("#sel3").val())
    getJyBaseInfo()
})

function getJyBaseInfo() {

    var jsonObj ={
        "owid":getCookie("stuOwid")
    }
    ajax("zustjy/bckjBizJyscheme/getJyBaseInfo", jsonObj, function (data) {
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
        saveJyFaInfo()
    }
});

function saveJyFaInfo() {
    if(!isTimeOut()) {
        var jsonObj = $("#registerForm").serializeObject()
        jsonObj.xh = $("#xh").val();
        // if($("#owid").val()){
        //     jsonObj.owid = $("#owid").val();
        // }
        ajax("zustjy/bckjBizJyscheme/saveJyFaInfo", jsonObj, function (data) {
            if (data.backCode == 0) {
                walert("保存成功")
            } else {
                walert(data.errorMess)
            }
        })
    }
}
