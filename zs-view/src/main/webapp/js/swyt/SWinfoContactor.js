/**
 *  作者：2515421994@qq.com
    时间：2019-10-25
    描述：基本信息2：添加联系人
 */
//用于判断联系人上一步还是下一步
$('#pre').click(function () {
    preNext = 1;
});
$('#saveContact').click(function () {
    preNext = 0;
});
//保存家庭成员信息
function saveContactors(a) {
    var params = $("#contactForm").serializeObject();
    var dataArr = [];
    dataArr[1] = {//父亲
        "cylb":params.facylb
        ,"xm": params.faxm
        , "xb": params.faxb
        , "whcd": params.fawhcd
        , "gzdw": params.fagzdw
        , "gzzw": params.fagzzw
        , "lxsj": params.falxsj
        , "dwdh": params.fadwdh
    };
    dataArr[0] = {//高中老师
        "cylb": 2
        ,"xm": params.texm
        , "xb": params.texb
        , "whcd": params.tewhcd
        , "gzdw": params.tegzdw
        , "gzzw": params.tegzzw
        , "lxsj": params.telxsj
        , "dwdh": params.tedwdh
    }
   var data = {
       "dataList": dataArr,
       "yhRefOwid": getCookie("swOwid")
   }
    ajax("zustswyt/bckjBizJtcyxx/finish", data, function (data) {
        if(data.backCode==0){
            console.log(a);
            if(a==1){//上一步
                $(".article-detail-text .form-horizontal").eq(0).show().siblings(".form-horizontal").hide();
            }else{
                $(".article-detail-text .form-horizontal").eq(2).show().siblings(".form-horizontal").hide();
                $(".jf-items .jf-item").eq(2).addClass("jf-active");
            }
        }else{
            walert(data.errorMess)
        }
    })
}

//初始化获取家庭成员信息
function getContactors() {
    var data = {
        "yhRefOwid":getCookie("swOwid")
    }
    ajax("zustswyt/bckjBizJtcyxx/getInfo", data, function (data) {
        if(data.backCode==0){
            $.each(JSON.parse(base64_decode(data.bean)),function (k,p) {
                if(p.cylb==0||p.cylb==1){ //父亲母亲
                    for (x in p) {
                        key = x;
                        value = p[x];
                        (key == "cylb")?($("#facylb option[value='"+p[x]+"']").prop("selected","selected")):''; //关系回显
                        (key == "xb")?($("#faxb option[value='"+p[x]+"']").prop("selected","selected")):''; //性别回显
                        (key == "whcd")?($("#fawhcd option[value='"+p[x]+"']").prop("selected","selected")):''; //文化程度回显
                        $("input[name='fa"+key+"']").val(value);
                    }
                }else{//高中联系人
                    for (x in p) {
                        key = x;
                        value = p[x];
                        (key == "xb")?($("#texb option[value='"+p[x]+"']").prop("selected","selected")):'';
                        (key == "whcd")?($("#tewhcd option[value='"+p[x]+"']").prop("selected","selected")):'';
                        $("input[name='te"+key+"']").val(value);
                    }
                }
               /* switch(p.cylb){
                    case 0: //父亲母亲
                        for (x in p) {
                            key = x;
                            value = p[x];
                            (key == "xb")?($("#faxb option[value='"+p[x]+"']").prop("selected","selected")):'';
                            (key == "whcd")?($("#fawhcd option[value='"+p[x]+"']").prop("selected","selected")):'';
                            $("input[name='fa"+key+"']").val(value);
                        }
                        break;
                    case 1: //其他
                        for (x in p) {
                            key = x;
                            value = p[x];
                            (key == "xb")?($("#moxb option[value='"+p[x]+"']").prop("selected","selected")):'';
                            (key == "whcd")?($("#mowhcd option[value='"+p[x]+"']").prop("selected","selected")):'';
                            $("input[name='mo"+key+"']").val(value);
                        }
                        break;
                    case 2: //高中联系人
                        for (x in p) {
                            key = x;
                            value = p[x];
                            (key == "xb")?($("#texb option[value='"+p[x]+"']").prop("selected","selected")):'';
                            (key == "whcd")?($("#tewhcd option[value='"+p[x]+"']").prop("selected","selected")):'';
                            $("input[name='te"+key+"']").val(value);
                        }
                        break;
                }*/
            });
        }else{
            walert(data.errorMess)
        }
    })
}



