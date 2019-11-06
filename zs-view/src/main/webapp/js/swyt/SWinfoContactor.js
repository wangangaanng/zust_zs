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
    dataArr[0] = {//父亲
        "cylb":0
        ,"xm": params.faxm
        , "xb": params.faxb
        , "whcd": params.fawhcd
        , "gzdw": params.fagzdw
        , "gzzw": params.fagzzw
        , "lxsj": params.falxsj
        , "dwdh": params.fadwdh
    };
    dataArr[1] = {//母亲
        "cylb": 1
        ,"xm": params.moxm
        , "xb": params.moxb
        , "whcd": params.mowhcd
        , "gzdw": params.mogzdw
        , "gzzw": params.mogzzw
        , "lxsj": params.molxsj
        , "dwdh": params.modwdh
    }
    dataArr[2] = {//高中老师
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
            $.each(data.bean,function (k,p) {
                switch(p.cylb){
                    case 0:
                        for (x in p) {
                            key = x;
                            value = p[x];
                            (key == "xb")?($("#faxb option[value='"+p[x]+"']").prop("selected","selected")):'';
                            $("input[name='fa"+key+"']").val(value);
                        }
                        break;
                    case 1:
                        for (x in p) {
                            key = x;
                            value = p[x];
                            (key == "xb")?($("#moxb option[value='"+p[x]+"']").prop("selected","selected")):'';
                            $("input[name='mo"+key+"']").val(value);
                        }
                        break;
                    case 2:
                        for (x in p) {
                            key = x;
                            value = p[x];
                            (key == "xb")?($("#texb option[value='"+p[x]+"']").prop("selected","selected")):'';
                            $("input[name='te"+key+"']").val(value);
                        }
                        break;
                }
            });
        }else{
            walert(data.errorMess)
        }
    })
}



