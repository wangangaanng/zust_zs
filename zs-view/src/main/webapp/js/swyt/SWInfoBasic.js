/**
 * Created by Xia Yu on 2019/11/04.
 * 三位一体保存基本信息
 */
var imgType = "";//区分图片类型：身份证正反面 户籍
var idType="1";//上传身份证还是上传户籍证明 默认1身份证 2户籍证明
$(function () {
    //浏览器不要自动填充
    $('input:not([autocomplete]),textarea:not([autocomplete]),select:not([autocomplete])').attr('autocomplete', 'off');

    //表单验证rule
    initValidate();

    //身份证和户籍上传
    uploadImg();

    //点击上传按钮
    $(".uploadlabel").click(function () {
        imgType = $(this).parents(".upimg-wrap").attr("typeNum");
        console.log(imgType);
    });

    $("#confrimType").on('change',function () {//监听select的改变
        idType = $("#confrimType").val();//获取选中的value值
    })
});

//表单验证触发保存基本信息
$.validator.setDefaults({
    submitHandler: function () {
        saveBasic();
        return;
    }
});

//保存基本信息
function saveBasic() {
    console.log(isTimeOut());
    if(!isTimeOut()){
        var jsonObj = $("#basicForm").serializeObject();
        jsonObj.yhRefOwid=getCookie("swOwid");
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

//图片上传
function uploadImg(){
    $(".file-btn").change(function (e) {
        var $parents = $(this).parents(".upimg-wrap");
        imgType =  $parents.attr("typeNum");
        var file = e.target.files[0] || e.dataTransfer.files[0];
        if (file) {
            console.log("file.size",file.size)
            if(file.size>2000000){
                walert("图片过大，请选择2M以下的图片")
                return
            }
            var reader = new FileReader();
            reader.onload = function () {
                console.log("file",file);
                switch (imgType){
                    case "1"://户籍证明
                        fileUpload("1",file,function (res) {
                            
                        })
                        break;
                    case "2"://2身份证正面
                        idOcr(imgType,file);//1是普通文件
                        break;
                    case "3"://身份证反面
                        fileUpload("1",file,function (res) {

                        })
                        break;
                }
            }
            reader.readAsDataURL(file);
        }
    })
}

//身份证Ocr识别
function idOcr(imgType,file) {
    var fd = new FormData();
    fd.append("file",file);
    fd.append("method","zustcommon/common/picUpload")
    fd.append('data', JSON.stringify({
        "type": imgType
    }));
    $parents.find(".up-btn_img").attr("src",this.result);
    $parents.find(".up-btn_img").addClass("fullImg")
    beginLoad();
    $.ajax({
        url:  hostUrl+'/webAjax/picUpload',
        type: "POST",
        processData: false,
        contentType: false,
        data: fd,
        success: function(d) {
            // console.log(d);
            finishLoad()
            if(d.bean){
                if(d.bean.fileName){
                    $("#sfzzm").val(d.bean.fileName);
                    $("#xm").val(d.bean['姓名'].words);
                    $("#xb").val(d.bean['性别'].words);
                    $("#mz").val(d.bean['民族'].words);
                    $("#jtzz").val(d.bean['住址'].words);
                    $("#sfzh").val(d.bean['公民身份号码'].words);
                    walert("上传成功");
                }
            }else{
                walert("上传失败")
            }
        },
        fail:function () {
            finishLoad()
        }
    });
}

//初始化验证信息
function initValidate() {
    // 身份证号码验证
    $.validator.addMethod("isIdCardNo", function(value, element) {
        return this.optional(element) || isIdCardNo(value);
    }, "请输入正确的身份证号码");

    //手机号码验证
    $.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^1[345789]\d{9}$/;/*/^1(3|4|5|7|8)\d{9}$/*/
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请输入正确的手机号码");


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
                isMobile: true,
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
}

