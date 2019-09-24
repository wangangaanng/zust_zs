
$(document).ready(function () {
    var provice=[];
    var city=[];
    var pindex=0;
    var cindex=0;
    var _cityData=[];
    cityData3.forEach(function(e) {
        provice.push(e.text)
        city.push(e.children)
        if($("#zwPro").attr("data-val")==e.text) {
            $("#zwPro").append("<option value='" + e.text + "' selected>" + e.text + "</option>")
        }else{
            $("#zwPro").append("<option value='" + e.text + "' >" + e.text + "</option>")
        }
    });

    pindex=parseInt($("#zwPro option:selected").index())-1;
    if(pindex>-1){
        _cityData=city[pindex]
        _cityData.forEach(function(e) {
            if($("#zwCity").attr("data-val")==e.text){
                $("#zwCity").append("<option value='"+e.text+"' selected>"+e.text+"</option>")
            }else{
                $("#zwCity").append("<option value='"+e.text+"'>"+e.text+"</option>")
            }

        });

    }
    cindex=parseInt($("#zwCity option:selected").index())-1;
    if(cindex>-1){
        var _areaData=_cityData[cindex].children
        _areaData.forEach(function(e) {
            if($("#zwArea").attr("data-val")==e.text){
                $("#zwArea").append("<option value='"+e.text+"' selected>"+e.text+"</option>")
            }else{
                $("#zwArea").append("<option value='"+e.text+"'>"+e.text+"</option>")
            }
        });

    }

    $("#zwGzzn").val($("#zwGzzn").attr("data-val"))
    $("#zwGzxz").val($("#zwGzxz").attr("data-val"))
    $("#zwNlyq").val($("#zwNlyq").attr("data-val"))
    $("#zwXlyq").val($("#zwXlyq").attr("data-val"))
    $("#zwGznx").val($("#zwGznx").attr("data-val"))
    $("#zwYyyq").val($("#zwYyyq").attr("data-val"))
    $("#zwGwzz").val($("#zwGwzz").attr("data-val"))

    $("#registerForm").validate({
        rules: {
            zwbt:"required",
            zwPro:"required",
            zwCity:"required",
            zwArea:"required",
            zwGzzn:"required",
            zwGzxz:"required",
            qyLxrdh:{
                required: true,
                isMobile: true
            },
            zwLxyx:{
                required: true,
                email: true
            },
            zwXs:"required",
            zwZprs:"required",
            zwXs:"required",
            zwNlyq:"required",
            zwXlyq:"required",
            zwGznx:"required",
            zwYyyq:"required",
            zwGwzz:"required",
        },
        messages: {
            zwbt: "请填写",
            zwPro: "请选择",
            zwCity: "请选择",
            zwArea: "请选择",
            zwGzzn: "请选择",
            zwGzxz: "请选择",
            qyLxrdh:  {
                required: "请填写",
                email: "请填写正确的11位手机号码"
            },
            zwLxyx: {
                required: "请填写",
                email: "请填写正确电子邮箱"
            },
            zwXs: "请选择",
            zwZprs: "请填写",
            zwXs: "请填写",
            zwNlyq: "请选择",
            zwXlyq: "请选择",
            zwGznx: "请选择",
            zwYyyq: "请选择",
            zwGwzz: "请填写",
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
        fixJob()
    }
});

function fixJob() {
    var jsonObj = $("#registerForm").serializeObject()
    jsonObj.qyxxRefOwid=getCookie("qyOwid")
    jsonObj.zwlx=0
    jsonObj.owid=$("#zwOwid").val();
    console.log(jsonObj)
    ajax("zustjy/bckjBizJob/fixJob", jsonObj, function (data) {
        if(data.backCode==0){
            layer.open({
                title:'提示',
                content: '职位发布成功',
                yes: function(index, layero){
                    layer.close(index);
                }
            });
        }
    })
}
