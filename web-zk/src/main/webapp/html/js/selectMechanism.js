var thisloHref = "";
$(function () {
    tabClick();//服务点击选择
    btnSelect();//点击选择中介
    liClick();//点击进入详情
    thisloHref = window.location.href;
});
//点击头部选择区域
/*function areaSelect() {
    $(".midselect li").click(function () {
        cityId = $(this).attr("cityid");
        $(this).addClass("select-area").siblings().removeClass("select-area");
        console.log(thisloHref);
        window.location.href = changeURLArg(thisloHref,"city",cityId)//地址，参数，替换值
    });
}*/
//点击头部tab选择服务类型
function tabClick() {
    $("#tabData li a").click(function(){
        serviceType = $(this).attr("val");
        typeName = $(this).html();
        $("#tabData li a").removeClass("slect-active");
        $(this).addClass("slect-active");
        if(thisloHref.indexOf("serviceType")!= -1){
            window.location.href = changeURLArg(thisloHref,"serviceType",serviceType)//地址，参数，替换值
        }else{
            window.location.href = thisloHref+"&serviceType="+serviceType;
        }
    })
}
//点击选择中介机构
function btnSelect() {
    $(".btn-select").click(function () {
        midRefOwid = $(this).attr("owid");
        var comName = $(this).parents("li").find(".comName").html();
        //询问框
        layer.confirm('确定选择“'+comName+'”？', {
            btn: ['确定','取消'], //按钮
            title:"提示"
        }, function(){
           sureSelect();
        }, function(){
        });
        return false;
    })
}

function liClick() {
    $(".mechanism-list li").click(function () {
        var thisOwid = $(this).attr("val");
        window.location.href = "mechanismDetail.htm?owid="+thisOwid+"&projectOwid="+projectOwid;
    })
}

//选择机构
function sureSelect() {
    var mark = 0;
    var paramThis = {
        "exp1": typeName,
        "projectRefOwid": projectOwid,//项目id
        "serviceType": serviceType,//服务类型
        "midRefOwid": midRefOwid,//中介id
        timestamp: new Date().getTime(),
    };
    window.parent.ajax("target/bckjBizMidcompOrder/takeOrder", paramThis, function (data) {
        if (data.backCode == 0) {
            var thisIndex = thisloHref.lastIndexOf("\/");
            var  thisHref = thisloHref .substring(0, thisIndex + 1);
           window.location.href = thisHref+"myService.htm?projectOwid="+projectOwid;
        } else {
            layer.msg(data.errorMess);
        }
    });
}

//改变地址中某个指定字段的值
function changeURLArg(url,arg,arg_val){//地址，参数，替换值
    var pattern=arg+'=([^&]*)';
    var replaceText=arg+'='+arg_val;
    if(url.match(pattern)){
        var tmp='/('+ arg+'=)([^&]*)/gi';
        tmp=url.replace(eval(tmp),replaceText);
        return tmp;
    }else{
        if(url.match('[\?]')){
            return url+'&'+replaceText;
        }else{
            return url+'?'+replaceText;
        }
    }
    return url+'\n'+arg+'\n'+arg_val;
}



