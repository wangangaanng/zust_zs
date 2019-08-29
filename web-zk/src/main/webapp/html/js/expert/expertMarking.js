/**
 * Created by Xia Yu on 2019/4/17.
 */
var finalMark = "";//最终打分意见
var ansPoint = "";
var ansOpinion= "";
var owid = "";
var qusId = "";
var currQuePar = "";//当前题目
var projectCount = 0;

var answerArrMap = {};//回答问题列表
var upImgName = "";//签名图片名称
$(function () {
    if(localStorage.getItem("answerArrMap")&&localStorage.getItem("answerArrMap")!="{}"){
        ansOwid = localStorage.getItem("ansOwid");//回答的id 在第一存的时候保存
        answerArrMap = JSON.parse(localStorage.getItem("answerArrMap"));
    }
    textAreaH();
    lisetnInput();
    totalCount();//计算总分

    //未提交刷新进来数据填充
    fillData();
});

//listen  input textarea select 实时保存
function lisetnInput() {

    $('input').bind('input propertychange', function() {
        var thisVal = $(this).val();
        //不能输入小于0 否则清空输入框
        if(thisVal<0){
            $(this).val("");
            return;
        }
        //控制输入框只能最多2位小数
        if(thisVal.substring(thisVal.indexOf(".")).length>3){
            $(this).val(thisVal.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3')); //只能输入两个小数
        }
        userData(this,1);
    });

    $('textarea').bind('input  propertychange', function() {
        userData(this,2);
    });

    $("select").change(function () {
        userData(this,3);
    })
}


var markOwid = "";
function userData(a,type) {
        var maxpoint = 0;//每道题最大分之
        currQuePar = $(a).parents("li");
        switch (type){
            case 1://输入框
                ansPoint = $(a).val();
                maxpoint = $(a).attr("max");
                ansOpinion =$(currQuePar).find("textarea").val();
                break;
            case 2://多文本输入框
                if($(currQuePar).find("input").length>0){
                    if($(currQuePar).find(".weui-cell__ft").css("display")!="none"){
                        ansPoint = $(currQuePar).find("input").attr("inputval");
                    }else{
                        ansPoint = $(currQuePar).find("input").val();
                    }
                }else{
                    ansPoint = $(currQuePar).find("select").val();
                }
                ansOpinion =$(a).val();
                break;
            case 3://下拉框
                ansPoint = $(a).val();
                ansOpinion =$(currQuePar).find("textarea").val();
                break;
        }
        maxpoint = $(currQuePar).attr("score");//最大分值
        qusId = $(currQuePar).attr("qusId");
        markOwid = $(currQuePar).attr("markOwid");
        if(type != 2){
            $(currQuePar).find(".score").html(ansPoint);
        }
        //分值不超出且或者分值没有填写
        if((!ansPoint)||((parseFloat(ansPoint)>parseFloat(maxpoint)))){
            //必填提示
            if(((parseFloat(ansPoint)>parseFloat(maxpoint)))){
                $(currQuePar).find(".weui-cell__ft span").html("分值超出");
            }else if(!emptyCheck($(currQuePar).find("input").val())){
                $(currQuePar).find(".weui-cell__ft span").html("必填项");
            }
            $(currQuePar).find(".weui-cell__ft").show();
            //return;
        }else{
            if(type==1){
                $(currQuePar).find(".weui-cell__ft").hide();
            }
            totalCount();//实时计算总分
        }
        throttle(saveDetail,null,800,"",1000);//节流函数
}

//实时保存++++++++++++++++++++++++++++++++++++++++++++++++++++
function saveDetail() {
    if(emptyCheck(ansPoint)){
        //调用打分保存接口 原来调用实时调用接口保存改为localstorage
        if(JSON.stringify(answerArrMap)=="{}"){
             startMark();
        }
        var currentQue = {};
        currentQue.ansPoint = ansPoint;
        currentQue.ansOpinion = ansOpinion;

        answerArrMap[qusId] = currentQue;
        localStorage.setItem("answerArrMap",JSON.stringify(answerArrMap));
    }
}
//实时保存 end+++++++++++++++++++++++++++++++++++++++++++++++++

//记录开始打分
function startMark() {
    var paramThis = {
        "testRefOwid":refOwid,
        "empWeixin":weixinId,
    }
    ajaxPost("bckjBizMidtestAnsdetail/saveAnsdetail",paramThis,1,"","","expertMark");
}

//签名图片base64上传接口
function uploadImg() {
    var paramThis = {
        "base64Data":(signaturePad.toDataURL('image/png'))
    }
    ajaxPost("",paramThis,4,"","","expertMark");
}

//最终提交 start+++++++++++++++++++++++++++++++++++++++++++++++
$("#submitBtn").click(function () {
    finalMark = $("#finalMark").html();
    var FaceImg = localStorage.getItem('faceImg');
    var paramThis = {
        "fileName":upImgName,
        "testRefOwid":refOwid,
        "ansRefOwid":ansOwid,
        "ansList":answerArrMap,
        "ansFinal":projectCount,//最终分数
        "ansFinalMark":finalMark,//评分意见
        "empWeixin":weixinId,
        "faceImg":FaceImg
    }
    ajaxPost("bckjBizMidtestAnswers/submitAllAnswers",paramThis,3,"","","expertMark");
});


//提交详情
$(".exregister-btn").click(function () {
    var hasNull = false;
    var overScore = false;
    $.each($(".score"),function (k,p) {
        if(!emptyCheck($(p).html())){
            $(p).parents(".weui-cells").find(".weui-cell__ft").show();
            layer.msg("评分项未填写完整，请检查");
            hasNull = true;
            return;
        }
    });

    $.each($(".weui-cell__ft"),function (k,p) {
        if($(p).css('display') != 'none'){
            if($(p).find("span").html()=="分值超出"){
                layer.msg("评分项分值超出，请检查");
                overScore = true;
                return;
            }
        }
    });

    $("#fiScore").html($("#totalScore").html());
    if(!hasNull&&!overScore){
        confirmLayer();
    }

});

function textAreaH() {
    //控制多文本输入框的高度随着内容的增多而变高
    $("textarea").autoHeight();
}

//统一数据处理
function handleData(data,index,typeId) {
    if(data.backCode == 0){
        switch (index){
            case 1://记录开始打分
                ansOwid = data.bean.ansRefOwid;//整个表的回答owid
                localStorage.setItem("ansOwid",ansOwid);
                break;
            case 2://提交详情 弹出分数确认框
                $("#fiScore").html(data.bean.ansFinal);
                $("#fiDev").html(data.bean.ansFinalMark);
                confirmLayer();
                break;
            case 3://最终提交分数
                $(".exregister-btn").hide();
                layer.closeAll();
                layer.msg("最终评分提交成功",{icon: 6});
                //提交之后清除记录
                localStorage.setItem("answerArrMap","{}");
                localStorage.setItem("ansOwid","");
                setTimeout(function(){$(window).scrollTop(0);window.location.reload()},1000);
                break;
            case 4://签名提交返回文件名称
                upImgName = data.bean;
                break;
        }
    }else{
        if(index==1){
            //第一次保存接口调通 才能继续答题
            localStorage.setItem("answerArrMap","{}");
        }
        layer.msg(data.errorMess);
    }
}
//计算总分
function totalCount() {
    projectCount = 0;
    $.each($(".score"),function (k,p) {
        if(emptyCheck($(p).html())){
            projectCount = parseFloat(projectCount) + parseFloat($(p).html());
        }
    });
    projectCount = projectCount.toFixed(2);
    $("#totalScore").html(projectCount);
}

jQuery.fn.extend({
    autoHeight: function(){
        return this.each(function(){
            var $this = jQuery(this);
            if( !$this.attr('_initAdjustHeight') ){
                $this.attr('_initAdjustHeight', $this.outerHeight());
            }
            _adjustH (this).on('input', function(){
                _adjustH(this);
            });
        });
        /**
         * 重置高度
         * @param {Object} elem
         */
        function _adjustH(elem){
            var $obj = jQuery(elem);
            return $obj.css({height: $obj.attr('_initAdjustHeight'), 'overflow-y': 'hidden'})
                .height( elem.scrollHeight );
        }
    }
});
//节流函数 在实时保存的时候用到
function throttle(fn,context,delay,text,mustApplyTime){
    clearTimeout(fn.timer);
    fn._cur=Date.now();  //记录当前时间
    if(!fn._start){      //若该函数是第一次调用，则直接设置_start,即开始时间，为_cur，即此刻的时间
        fn._start=fn._cur;
    }
    if(fn._cur-fn._start>mustApplyTime){
        //当前时间与上一次函数被执行的时间作差，与mustApplyTime比较，若大于，则必须执行一次函数，若小于，则重新设置计时器
        fn.call(context,text);
        fn._start=fn._cur;
    }else{
        fn.timer=setTimeout(function(){
            fn.call(context,text);
        },delay);
    }
}
//初始数据填充
function fillData() {
    var qusLiArr = $(".question-wrap li.weui-cells");
    $.each(qusLiArr,function (k,p) {
        var thisId = $(p).attr('qusid');
        var thisval = answerArrMap[thisId];
        if(thisval){
            $(p).find(".score").html(thisval.ansPoint);
            $(p).find("select option[value='"+thisval.ansPoint+"']").prop("selected","selected");
            $(p).find(".weui-input").val(thisval.ansPoint);
            $(p).find(".weui-input").attr("inputval",thisval.ansPoint);
            $(p).find(".weui-textarea").val(thisval.ansOpinion);
        }
        //刷新进来 最后一个计算总分
        if(k==(qusLiArr.length)-1){
            totalCount();
        }
    })
}

//提交 弹出最终打分情况
function confirmLayer() {
    var signNull = isCanvasBlank(document.getElementById('signature-canvas'));
    if(signNull){
        layer.msg("请签名");
        return;
    }
    //上传签名
    uploadImg();
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        area: '82%',
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: true,
        content: $('#finalScore')
    });

    //解决ipone7及以上键盘弹出后收起底部留有空白
    const windowHeight = window.innerHeight;
    $("#finalMark").blur(function () {
        var windowFocusHeight = window.innerHeight
        if (windowHeight == windowFocusHeight) {
            return
        }
        var currentPosition;
        var speed = 1; //页面滚动距离
        currentPosition = document.documentElement.scrollTop || document.body.scrollTop;
        currentPosition -= speed;
        window.scrollTo(0, currentPosition); //页面向上滚动
        currentPosition += speed; //speed变量
        window.scrollTo(0, currentPosition); //页面向下滚动
    });
}
//returns true if all color channels in each pixel are 0 (or "blank")
function isCanvasBlank(canvas) {
    return !canvas.getContext('2d').getImageData(0, 0, canvas.width, canvas.height).data.some(function(channel){return channel !==0;})
}



