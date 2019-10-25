
$(document).ready(function () {
    $('#syd').chosen();
    $('#dwszdmc').chosen();
    $('#bdzqwszdmc').chosen();

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
        window.location.href=base+"/stuCenter/"+index
    })

    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^1[345789]\d{9}$/;/*/^1(3|4|5|7|8)\d{9}$/*/
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写您的手机号码");

    $("#registerForm").validate({
        rules: {
            xm:"required",
            xxmc:"required",
            byqx:"required",
            bdzqwszdmc:"required",
            bdkssj:"required",
            bdjssj:"required"
        },
        messages: {
            xm:"请填写",
            xxmc:"请填写",
            byqx:"请选择",
            bdzqwszdmc:"请选择",
            bdkssj:"请选择",
            bdjssj:"请选择"
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

    $("#byqx").val($("#byqx").attr("data-val"))
    $("#sfzydk").val($("#sfzydk").attr("data-val"))
    $("#yrdwxzmc").val($("#yrdwxzmc").attr("data-val"))
    $("#dwhylbmc").val($("#dwhylbmc").attr("data-val"))

    $("#dwszdmc1").val($("#dwszdmc1").attr("data-val"))
    $("#dwszdmc").val($("#dwszdmc").attr("data-val"))
    $("#dwszdmc").parent().find('.chosen-container  a.chosen-single span').html($("#dwszdmc1 option:selected").html())

    $("#gzzwlbmc").val($("#gzzwlbmc").attr("data-val"))
    $("#bdzqflbmc").val($("#bdzqflbmc").attr("data-val"))

    $("#bdzqwszdmc1").val($("#bdzqwszdmc1").attr("data-val"))
    $("#bdzqwszdmc").val($("#bdzqwszdmc").attr("data-val"))
    $("#bdzqwszdmc").parent().find('.chosen-container  a.chosen-single span').html($("#bdzqwszdmc1 option:selected").html())

    $("#sfdydwbdz").val($("#sfdydwbdz").attr("data-val"))

    // getJyBaseInfo()
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
        if(!$("#bdzqwszdmc").val()){
            walert("请选择报到证签往单位所在地")
            return
        }
        var jsonObj = $("#registerForm").serializeObject()
        jsonObj.xsxh = $("#xsxh").val();
        if($("#owid").val()){
            jsonObj.owid = $("#owid").val();
        }
        ajax("zustjy/bckjBizJyscheme/insertssInfo", jsonObj, function (data) {
            if (data.backCode == 0) {
                walert("保存成功")
            } else {
                walert(data.errorMess)
            }
        })
    }
}
