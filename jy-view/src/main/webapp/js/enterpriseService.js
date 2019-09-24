
$(document).ready(function () {
    var provice=[];
    var city=[];
    var pindex=0;
    var cindex=0;
    var _cityData=[];
    cityData3.forEach(function(e) {
        provice.push(e.text)
        city.push(e.children)
        if($("#qyProv").attr("data-val")==e.text) {
            $("#qyProv").append("<option value='" + e.text + "' selected>" + e.text + "</option>")
        }else{
            $("#qyProv").append("<option value='" + e.text + "' >" + e.text + "</option>")
        }
    });

    pindex=parseInt($("#qyProv option:selected").index())-1;
    if(pindex>-1){
        _cityData=city[pindex]
        _cityData.forEach(function(e) {
            if($("#qyCity").attr("data-val")==e.text){
                $("#qyCity").append("<option value='"+e.text+"' selected>"+e.text+"</option>")
            }else{
                $("#qyCity").append("<option value='"+e.text+"'>"+e.text+"</option>")
            }

        });

    }
    cindex=parseInt($("#qyCity option:selected").index())-1;
    if(cindex>-1){
        var _areaData=_cityData[cindex].children
        _areaData.forEach(function(e) {
            if($("#qyArea").attr("data-val")==e.text){
                $("#qyArea").append("<option value='"+e.text+"' selected>"+e.text+"</option>")
            }else{
                $("#qyArea").append("<option value='"+e.text+"'>"+e.text+"</option>")
            }
        });

    }

    $("#qyGsxz").val($("#qyGsxz").attr("data-val"))
    $("#qyHylb").val($("#qyHylb").attr("data-val"))
    $("#qyGsgm").val($("#qyGsgm").attr("data-val"))
    $("#qyGsjs").val($("#qyGsjs").attr("data-val"))

    $(".list-group-item").click(function(e) {
        var index=$(this).index()
        window.location.href="/enterpriseService/"+index
    })

    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^1[345789]\d{9}$/;/*/^1(3|4|5|7|8)\d{9}$/*/
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写您的手机号码");

    $("#registerForm").validate({
        rules: {
            qymc:"required",
            qyProv:"required",
            qyCity:"required",
            qyArea:"required",
            qydz:"required",
            qyLxr:"required",
            qyLxrdh:{
                required: true,
                isMobile: true
            },
            qyYx:{
                required: true,
                email: true
            },
            qyGsxz:"required",
            qyHylb:"required",
            qyGsgm:"required",
            qyGsjs:"required",
        },
        messages: {
            qymc: "请填写",
            qyProv: "请选择",
            qyCity: "请选择",
            qyArea: "请选择",
            qydz: "请填写",
            qyLxr: "请填写",
            qyLxrdh:  {
                required: "请填写",
                email: "请填写正确的11位手机号码"
            },
            qyYx: {
                required: "请填写",
                email: "请填写正确电子邮箱"
            },
            qyGsxz: "请选择",
            qyHylb: "请选择",
            qyGsgm: "请选择",
            qyGsjs: "请填写",
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


})

$.validator.setDefaults({
    submitHandler: function () {
        fixCompany()
    }
});

function fixCompany() {

    var jsonObj = $("#registerForm").serializeObject()
    jsonObj.owid=getCookie("qyOwid")
    console.log(jsonObj)
    ajax("zustjy/bckjBizQyxx/fixCompany", jsonObj, function (data) {
        if(data.backCode==0){
            walert("修改成功")
        }else{
            walert(data.errorMess)
        }
    })
}


