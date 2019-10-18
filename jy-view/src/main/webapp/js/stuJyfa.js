
$(document).ready(function () {
    $('#syd').chosen();
    $('#dwszdmc').chosen();
    $('#bdzszdmc').chosen();

    laydate.render({
        elem: '#bdkssj',
        format: 'yyyy-MM-dd'
    });

    laydate.render({
        elem: "#bdjssj",
        format: 'yyyy-MM-dd'
    });

    //年选择器
    laydate.render({
        elem: '#bynf'
        ,type: 'year'
    });

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
            mz:"required",
            xxmc:"required",
            xsxy:"required",
            xszy:"required",
            xsbj:"required",
            bynf:"required",
            byxl:"required",
            xz:"required",
            sfsf:"required",
            sfzz:"required",
            sfdlxy:"required",
            sfsf:"required",
            syddm:"required",
            syd:"required",
            jyqdbz:"required",
            sjh:{
                required: true,
                isMobile: true
            },
            yx:{
                email: true
            },
            byqx:"required",
            bdzqflbmc:"required",
        },
        messages: {
            xm:"请填写",
            sfz:"请填写",
            xb:"请选择",
            mz:"请选择",
            xxmc:"请填写",
            xsxy:"请填写",
            xszy:"请填写",
            xsbj:"请填写",
            bynf:"请填写",
            byxl:"请填写",
            xz:"请选择",
            sfdlxy:"请选择",
            sfsf:"请选择",
            sfzz:"请选择",
            sfsf:"请选择",
            syddm:"请填写",
            syd:"请选择",
            jyqdbz:"请填写",
            sjh:  {
                required: "请填写",
                email: "手机号有误"
            },
            yx: {
                email: "邮箱有误"
            },
            byqx:"请选择",
            bdzqflbmc:"请选择",
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
    $("#xz").val($("#xz").attr("data-val"))
    $("#sfsf").val($("#sfsf").attr("data-val"))
    $("#sfzz").val($("#sfzz").attr("data-val"))
    $("#sfdlxy").val($("#sfdlxy").attr("data-val"))

    $("#syd1").val($("#syd1").attr("data-val"))
    $("#syd").val($("#syd").attr("data-val"))
    $("#syd").parent().find('.chosen-container  a.chosen-single span').html($("#syd1 option:selected").html())

    $("#dwszdmc1").val($("#dwszdmc1").attr("data-val"))
    $("#dwszdmc").val($("#dwszdmc").attr("data-val"))
    $("#dwszdmc").parent().find('.chosen-container  a.chosen-single span').html($("#dwszdmc1 option:selected").html())

    $("#bdzszdmc1").val($("#bdzszdmc1").attr("data-val"))
    $("#bdzszdmc").val($("#bdzszdmc").attr("data-val"))
    $("#bdzszdmc").parent().find('.chosen-container  a.chosen-single span').html($("#bdzszdmc1 option:selected").html())

    $("#byqx").val($("#byqx").attr("data-val"))
    $("#bdzqflbmc").val($("#bdzqflbmc").attr("data-val"))
    $("#yrdwxz").val($("#yrdwxz").attr("data-val"))
    $("#gzzwlbmc").val($("#gzzwlbmc").attr("data-val"))
    $("#dwlbmc").val($("#dwlbmc").attr("data-val"))
    $("#sfzydk").val($("#sfzydk").attr("data-val"))
    $("#bdzqflbmc").val($("#bdzqflbmc").attr("data-val"))
    $("#sfdydwbdz").val($("#sfdydwbdz").attr("data-val"))
    getJyBaseInfo()
})

function getJyBaseInfo() {

    var jsonObj ={
        "owid":getCookie("stuOwid")
    }
    ajax("zustcommon/bckjBizJyscheme/getStudentOne", jsonObj, function (data) {
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
        jsonObj.xsxh = $("#xsxh").val();
        if($("#owid").val()){
            jsonObj.owid = $("#owid").val();
        }
        ajax("zustcommon/bckjBizJyscheme/insertssInfo", jsonObj, function (data) {
            if (data.backCode == 0) {
                walert("保存成功")
            } else {
                walert(data.errorMess)
            }
        })
    }
}
