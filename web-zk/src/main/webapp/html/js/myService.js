 var curDom = "";
$(function () {

    //解决我的服务取消之后 返回有问题 路径不则将前一个页面路径进行存储
    var prePage = document.referrer;
    var curPage = window.location.href;
    if(prePage.split("?")[0] != curPage.split("?")[0]){
        localStorage.setItem("prePage",prePage);
    }

    //取消服务
    actCancel();
    //点击评价弹出
    $(".service-state_btn").click(function () {
        serviceOwid = $(this).attr("owid");
        curDom = $(this).parent();
        contentLayer();
    });

    //点击提交评价
    $(".up-btn input").click(function () {
        var mark = 0;
        orderPjArr = [];
        $.each($("#ser-evulate .eva-stars"),function (k,p) {
            var  thisScore = $(p).attr("score");
            if(thisScore&&thisScore!=0){
                orderPjArr.push($(p).attr("score"));
                if((k+1)==$("#ser-evulate .eva-stars").length){
                    areaDeclare = $(".eva-textarea").val();
                    if(areaDeclare.length<1){
                        mark=1;
                        $(".eva-textarea").focus();
                        layer.msg("评语必填");
                    }
                }
            }else{
                mark = 1;
                layer.msg("评分项星级必选");
                return;
            }
        });

        if(mark==0){
            getEvaluateContent();
        }

    });
});

var serviceOwid = "";//服务单id
var orderPjArr = [];
var areaDeclare = "";
//评价服务单
function getEvaluateContent() {
    var paramThis = {
        "owid": serviceOwid,//服务单id
        "orderPj1":orderPjArr[0],//评价1
        "orderPj2":orderPjArr[1],
        "orderPj3":orderPjArr[2],
        "orderPj4":orderPjArr[3],
        "orderPj5":orderPjArr[4],
        "declare":areaDeclare,
        timestamp: new Date().getTime(),
    };
   window.parent.ajax("target/bckjBizMidcompOrder/evaluateOrder", paramThis, function (data) {
        if (data.backCode == 0) {
            layer.closeAll();
            curDom.html('<p class="service-state" style="color: #333">已结束</p>');
            //这里应该改成重新刷新当前页面 并加上页数 显示已评价 下边的评价提示可以不展示
            layer.msg("评价成功", {icon: 1});
            //弹出
        } else {
            layer.msg(data.errorMess);
        }
    });
}

function contentLayer() {
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        area: '440px',
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: true,
        content: $('#ser-evulate')
    });
}
 //点击取消服务+++++++++++++++++++++++++++++++++++++++++++
 function actCancel() {
     $(".ser-cancel").click(function () {
         serviceOwid = $(this).attr("owid");
         //询问框
         layer.confirm('确定取消服务？', {
             btn: ['确定','取消'], //按钮
             title:"提示"
         }, function(){
             sureCancel();
         }, function(){
         });
     });
 }
 
 function sureCancel() {
     var paramThis = {
         "owid": serviceOwid,//服务单id
         timestamp: new Date().getTime(),
     };
     window.parent.ajax("target/bckjBizMidcompOrder/cancelOrder", paramThis, function (data) {
         if (data.backCode == 0) {
             var liLength = $(".mechanism-list li").length;
             if(liLength>0){
                 window.location.href = changeURLArg(thisUrl,"pageNo",curNum);
             }else{
                 //如果是第二页最后一条被删掉 页数-1
                 window.location.href = changeURLArg(thisUrl,"pageNo",(parseInt(curNum)-1));
             }
             layer.msg("取消成功", {icon: 1});
         } else {
             layer.msg(data.errorMess);
         }
     })
 }

