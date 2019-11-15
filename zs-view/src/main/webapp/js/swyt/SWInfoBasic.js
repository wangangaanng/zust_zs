/**
 * Created by Xia Yu on 2019/11/04.
 * 三位一体基本信息
 */
var imgType = "";//区分图片类型：身份证正反面 户籍
var idType="1";//上传身份证还是上传户籍证明 默认1身份证 2户籍证明
$(function () {
    //已经提交报名表 不可在修改与提交
    if(processState>0){
        $('#basicForm').find('button[type=submit]').hide()
        $('#contactForm').find('button[type=submit]').hide()
        $('#gradeForm').find('button[type=submit]').hide()
        $('#selectForm').find('button[type=submit]').hide()
        $('#selectForm').find('.upimg-wrap').eq(1).hide();
        //隐藏于阻止点击上传
        $(".file-btn").hide();
        $(".uploadlabel").html("已上传").attr("for","");
    }

    //浏览器不要自动填充
    $('input:not([autocomplete]),textarea:not([autocomplete]),select:not([autocomplete])').attr('autocomplete', 'off');

    //表单验证rule
    initValidate();

    //身份证和户籍上传
    uploadImg();

    //获取基本信息进来填充
    getInfo()

    //点击上传按钮
    $(".uploadlabel").click(function () {
        imgType = $(this).parents(".upimg-wrap").attr("typeNum");
    });

    //选择证件类型：身份证/户籍
    $("#confrimType").on('change',function () {//监听select的改变
        idType = $("#confrimType").val();//获取选中的value值
        (idType=="1")?($("#idCard").show(),$("#household").hide()):($("#idCard").hide(),$("#household").show());
    })
    getByType111('10022');
    getByType111('10023');
    getByType('10020');
    getByType('10024');
    getXkkm();
    var t = setInterval(function () {
        if(readyNum==3){
            getXkcj()
            clearInterval(t)
        }
    },100)
    $('#file3').change(function () {
        console.log($('#file3').prop('files'))
        var str = '<li style="float: left;">' +
            '<div class="file-btn_wrap" style="margin-right: 10px;">' +
            '<img src="'+URL.createObjectURL($('#file3')[0].files[0])+'">' +
            '</div>' +
            '<label class="uploadlabel" onclick="removeJsfj(this)">删除</label>' +
            '</li>';
        $('#fileList').append(str)
        fileUpload(2, $('#file3')[0].files[0], function (res) {
            if (res.backCode == 0) {
                jsfj.push(res.bean.owid);
            } else {
                walert(res.errorMess)
            }
        })
    })
});


//保存基本信息
function saveBasic() {
    var jsonObj = $("#basicForm").serializeObject();
    jsonObj.yhRefOwid=getCookie("swOwid");
    //身份证或者护照 必传单独判断
    switch (idType){
        case "1":
            if(jsonObj.sfzfm==""||jsonObj.sfzzm==""){
                $("#idCard .error-tip").html("身份证正反面必传");
                return;
            }else{
                $("#idCard .error-tip").html("");
                break;
            }
        case "2":
            if(jsonObj.hjzm==""){
                $("#household .error-tip").html("户籍证明必传");
                return;
            }else{
                $("#idCard .error-tip").html("");
                break;
            }
    }
    ajax("zustswyt/bckjBizJbxx/finishInfo", jsonObj, function (data) {
        if(data.backCode==0){
            //基本信息完成 下一步获取联系人
            getContactors();

            $("#basicForm").hide();
            $("#contactForm").show();
            $(".jf-items .jf-item").eq(1).addClass("jf-active");
        }else{
            walert(data.errorMess)
        }
    })
}

//获取基本信息
function getInfo() {
    var data = {
        "yhRefOwid":getCookie("swOwid")
    }
    ajax("zustswyt/bckjBizJbxx/getInfo", data, function (data) {
        if(data.backCode==0){
            var data = data.bean;
            $("#xm").val(data.xm);
            $("#xb option[value='"+data.xb+"']").prop("selected","selected");
            $("#sfzh").val(data.sfzh);
            $("#mz").val(data.mz);
            $("#jtzz").val(data.jtzz);
            $("#lxdh").val(data.lxdh);
            $("#yx").val(data.yx);
            $("#qq").val(data.qq);
            if(data.sfzzm){
                $("#sfzzm").val(data.sfzzm);
                $("#sfzzmImg").attr("src",imagePath+data.sfzzm).addClass("fullImg");
            }
            if(data.sfzfm){
                $("#sfzfm").val(data.sfzfm);
                $("#sfzfmImg").attr("src",imagePath+data.sfzfm).addClass("fullImg");
            }
            if(data.hjzm){
                idType = "2";
                $("#hjzm").val(data.hjzm);
                $("#hjzmImg").attr("src",imagePath+data.hjzm).addClass("fullImg");
                $("#confrimType option[value='2']").prop("selected","selected");
                $("#household").show();
                $("#idCard").hide();
            }
        }else{
            walert(data.errorMess)
        }
    })
}

//图片上传
function uploadImg(){
    $(".file-btn").change(function (e) {
        var $parents = $(this).parents(".upimg-wrap");
        imgType =  $parents.attr("typeNum");
        var file = e.target.files[0] || e.dataTransfer.files[0];
        if (file) {
            if(file.size>2000000){
                walert("图片过大，请选择2M以下的图片")
                return
            }
            var reader = new FileReader();
            reader.onload = function () {
                $parents.find(".up-btn_img").attr("src",this.result);
                $parents.find(".up-btn_img").addClass("fullImg");
                switch (imgType){
                    case "1"://户籍证明
                        idOcr(imgType,file,function (d) {
                            if(d.backCode==0){
                                $("#hjzm").val(d.bean.fileName);
                            }else{
                                walert(d.errorMess)
                            }
                        });//1是普通文件
                        break;
                    case "2"://2身份证正面
                        idOcr(imgType,file,function (d) {
                            if(d.backCode==0){
                                if(d.bean.image_status!="normal"){
                                    switch (d.bean.image_status) {
                                        case "reversed_side":
                                            statusStr = "身份证正反面颠倒，请重新选择";
                                            break;
                                        case "non_idcard":
                                            statusStr = "上传的图片中不包含身份证，请重新选择"
                                            break;
                                        case "blurred":
                                            statusStr = "身份证模糊，请重新选择"
                                            break;
                                        case "other_type_card":
                                            statusStr = "上传照片为其他类型证照，请重新选择"
                                            break;
                                        case "over_exposure":
                                            statusStr = "身份证关键字段反光或过曝，请重新选择"
                                            break;
                                        case "over_dark":
                                            statusStr = "身份证欠曝（亮度过低），请重新选择"
                                            break;
                                        default:
                                            if(d.bean.fileName){$("#sfzzm").val(d.bean.fileName);}
                                            statusStr = status;
                                            break;
                                    }
                                    $("#idCard .error-tip").html(statusStr);
                                }else{
                                    if(d.bean.fileName){$("#sfzzm").val(d.bean.fileName);}
                                    $("#idCard .error-tip").html("");
                                }
                                if(d.bean['姓名']&&d.bean['姓名'].words){$("#xm").val(d.bean['姓名'].words);}
                                if(d.bean['性别']){
                                    if(d.bean['性别'].words == "男"){
                                        $("#xb option[value='1']").prop("selected","selected");
                                        $("#xb").val("1");
                                    }
                                    if(d.bean['性别'].words == "女"){
                                        $("#xb option[value='2']").prop("selected","selected");
                                        $("#xb").val("2");
                                    }
                                }
                                if(d.bean['民族']&&d.bean['民族'].words){$("#mz").val(d.bean['民族'].words);}
                                if(d.bean['住址']&&d.bean['住址'].words){$("#jtzz").val(d.bean['住址'].words);}
                                if(d.bean['公民身份号码']&&d.bean['公民身份号码'].words){$("#sfzh").val(d.bean['公民身份号码'].words);}
                            }else {
                                walert(d.errorMess)
                            }
                        });//1是普通文件
                        break;
                    case "3"://身份证反面
                        idOcr(imgType,file,function (d) {
                            if(d.backCode==0){
                                $("#sfzfm").val(d.bean.fileName);
                            }else {
                                walert(d.errorMess)
                            }
                        });//1是普通文件
                        break;
                }
            }
            reader.readAsDataURL(file);
        }
    })
}

//初始化验证信息 包括联系人和基本信息表单
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

    //表单验证规则
    $("#contactForm").validate({
        rules: {
            faxm: "required",
            falxsj: {
                required: true,
                isMobile: true
            },
            faxb: "required",
            moxm: "required",
            molxsj: {
                required: true,
                isMobile: true
            },
            moxb: "required",
            texm: "required",
            telxsj: {
                required: true,
                isMobile: true
            },
            texb: "required"
        },
        errorElement: "em",
        errorPlacement: function ( error, element ) {
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

function process(e) {
    switch (e) {
        case 1://基本信息
            $("#basicForm").show();
            $("#contactForm").hide();
            $("#gradeForm").hide();
            $("#selectForm").hide();
            break;
        case 2://联系人
            if(!emptyCheck($("#faxm").val())&&!emptyCheck($("#falxsj").val())){
                getContactors();
            }
            $("#basicForm").hide();
            $("#contactForm").show();
            $("#gradeForm").hide();
            $("#selectForm").hide();
            break;
        case 3: //学考信息
            $("#basicForm").hide();
            $("#contactForm").hide();
            $("#gradeForm").show();
            $("#selectForm").hide();
            break;
        case 4: //选考信息
            $("#basicForm").hide();
            $("#contactForm").hide();
            $("#gradeForm").hide();
            $("#selectForm").show();
            break;
    }
}