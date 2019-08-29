/*
 * @Author: zhangqiaojun 
 * @Date: 2019-07-29 9:36:44 
 * @Last Modified by: zhangqiaojun
 * @Last Modified time: 2019-08-14 09:59:09
 */
var nullTip = "<div class = 'null-tip'><img src = '../html/img/index_nulltip.png'/><p style='margin-top: 10px'>查询结果暂为空</p></div>";
// 年份
var yearArray=[];
$(".yeartab a").each(function () {
    yearArray.push($(this).text())
})
// ['全部','2019','2018']
var currentYear="";//年份值
var currentYearIndex=0;//年份选项
var yearLength="";
yearLength=yearArray.length;
// yearLength=$(".yeartab").find("a").length;
// currentYear=$(".yeartab").find("a").eq(currentYearIndex).text();
//图形
var graphArray=['pie','bar'];//饼图、折线图
var currentGraph="";//图形值
var currentGraphIndex=0;//图形选项
currentGraph=graphArray[currentGraphIndex];
var intervalGraph="60000";//图形间隔时间
var intervalYear=intervalGraph*(graphArray.length);//年份间隔时间


//图形2
var graphArray2=['pie','bar','line'];//饼图、折线图
var currentGraph2="";//图形值
var currentGraphIndex2=0;//图形选项
currentGraph2=graphArray2[currentGraphIndex2];

//图形3
var graphArray3=['map','gautt','line'];//地图、甘特图、折线图
var currentGraph3="";//图形值
var currentGraphIndex3=0;//图形选项
currentGraph3=graphArray3[currentGraphIndex3];
var intervalGraph3="40000";//图形间隔时间

//定时器
var graphInterval="";
var yearInterval="";
var xmjdInterval="";

//图表数据
//项目投资
var dataXmtz_jhztz=[];
var dataXmtz_jhzftz=[];
var dataXmtz_sjztz=[];
var dataXmtz_sjzftz=[];
var dataXmtz_sjztzR=dataXmtz_sjztz.concat().reverse();
var dataXmtz_sjzftzR=dataXmtz_sjzftz.concat().reverse();
var dataXmtz_jhztzR=dataXmtz_jhztz.concat().reverse();
var dataXmtz_jhzftzR=dataXmtz_jhzftz.concat().reverse();
var sjtzGoverment=[];//一条政府
var sjtzFzftz=[];//一条其他


// 政府投资进度
var dataTzjd_zhongy=[];//投资进度-中央投资
var dataTzjd_shenj=[]//投资进度-省级投资
var dataTzjd_shij=[];//投资进度-市级投资
var dataTzjd_qix=[];//投资进度-旗县政府

//项目进度-甘特图
var dataGautt_y=[]//任务名称
var dataGautt_jhks=[]
var dataGautt_jhsj=[]
var dataGautt_sjks=[]
var dataGautt_sjsj=[]
var gauttFlag="null"

//项目进度-工程量
var dataGcl_wc=[]
var dataGcl_jh=[]
var dataGcl_xlabel=[]
var gclFlag="null"

//招标采购
var chartDom = $(".report-content_b_left .pie-bidding");
var pieChart = ["bidding-pie1","bidding-pie2","bidding-pie3"];



$(document).ready(function () {
    map_init();
    //开关控制自动播放
    $(".toggle-switch").click(function(){
        if($(this).hasClass("toggle-on")){//关闭自动
            $(this).removeClass("toggle-on")
            window.clearInterval(graphInterval);//停止图形
            window.clearInterval(yearInterval);//停止年份
            window.clearInterval(xmjdInterval);//停止图形
            document.getElementById("content-a_left_c").removeEventListener("mouseover",stopTiming_xmtz,false)
            document.getElementById("content-a_left_c").removeEventListener("mouseout",startTiming_xmtz,false)
            document.getElementById("content-a_left_d").removeEventListener("mouseover",stopTiming_zftz,false)
            document.getElementById("content-a_left_d").removeEventListener("mouseout",startTiming_zftz,false)
            document.getElementById("content-b_center_a").removeEventListener("mouseover", stopTiming_xmjd,false)
            document.getElementById("content-b_center_a").removeEventListener("mouseout",startTiming_xmjd,false)
        }else{//打开自动
            $(this).addClass("toggle-on")
            graphInterval=setInterval(function(){//图形选项卡
                graphTask()
            },intervalGraph);
            yearInterval=setInterval(function(){//年份选项卡
                yearTask()
            },intervalYear);
            xmjdInterval=setInterval(function(){//图形选项卡
                graphTask3()
            },intervalGraph3);
            //项目投资-鼠标移入移出，关闭、开启定时器
            document.getElementById("content-a_left_c").addEventListener("mouseover",stopTiming_xmtz,false)
            document.getElementById("content-a_left_c").addEventListener("mouseout",startTiming_xmtz,false)
            document.getElementById("content-a_left_d").addEventListener("mouseover",stopTiming_zftz,false)
            document.getElementById("content-a_left_d").addEventListener("mouseout",startTiming_zftz,false)
            document.getElementById("content-b_center_a").addEventListener("mouseover", stopTiming_xmjd,false)
            document.getElementById("content-b_center_a").addEventListener("mouseout",startTiming_xmjd,false)
            // $(".content-a_left_c").mouseover(function(){
            //     stopTiming_xmtz(1)
            // }).mouseout(function () {
            //     stopTiming_xmtz(0)
            // })
            // //政府投资-鼠标移入移出，关闭、开启年份定时器
            // $(".content-a_left_d").mouseover(function(){
            //     stopTiming_zftz(1)
            // }).mouseout(function () {
            //     stopTiming_zftz(0)
            // })
            // //项目进度-鼠标移入移出，关闭、开启定时器
            // $(".content-b_center_a").mouseover(function(){
            //     stopTiming_xmjd(1)
            // }).mouseout(function () {
            //     stopTiming_xmjd(0)
            // })
        }
    })
    // 设置全屏：目前这个方法无法监听 ESC 键盘按键
    $('#alarm-fullscreen-toggler').on('click', function (e) {
        var element = document.documentElement;		// 返回 html dom 中的root 节点 <html>
        if(!$('body').hasClass('full-screen')) {
            $('body').addClass('full-screen');
            $('#alarm-fullscreen-toggler').addClass('active');
            // 判断浏览器设备类型
            if(element.requestFullscreen) {
                element.requestFullscreen();
            } else if (element.mozRequestFullScreen){	// 兼容火狐
                element.mozRequestFullScreen();
            } else if(element.webkitRequestFullscreen) {	// 兼容谷歌
                element.webkitRequestFullscreen();
            } else if (element.msRequestFullscreen) {	// 兼容IE
                element.msRequestFullscreen();
            }
        } else {			// 退出全屏
            console.log(document);
            $('body').removeClass('full-screen');
            $('#alarm-fullscreen-toggler').removeClass('active');
            //	退出全屏
            if(document.exitFullscreen) {
                document.exitFullscreen();
            } else if (document.mozCancelFullScreen) {
                document.mozCancelFullScreen();
            } else if (document.webkitCancelFullScreen) {
                document.webkitCancelFullScreen();
            } else if (document.msExitFullscreen) {
                document.msExitFullscreen();
            }
        }
    });

    $('.content-a_left_c .feature a').click(function(){//项目投资-图像切换
        $(this).addClass('active').siblings().removeClass('active');
        currentGraphIndex=$(this).index();
        currentGraph=graphArray[currentGraphIndex];
        eventTask();
    })
    $('.content-a_left_c .yeartab a').click(function(){//项目投资-手动-年份切换
        currentYearIndex=$(this).index();
        if(currentYearIndex==0){
            currentYear="";
        }else{
            currentYear=yearArray[currentYearIndex];
        }
        yearChange();
    })
    $('.content-a_left_d .feature a').click(function(){//政府投资进度-图像切换
        $(this).addClass('active').siblings().removeClass('active');
        currentGraphIndex2=$(this).index();
        currentGraph2=graphArray2[currentGraphIndex2];
        eventTask2();
       
    })
    $('.progresstab a').click(function(){//项目进度-图像切换
        $(this).addClass('active').siblings().removeClass('active');
        currentGraphIndex3=$(this).index();
        currentGraph3=graphArray3[currentGraphIndex3];
        eventTask3();
    })
    //项目投资-鼠标移入移出，关闭、开启定时器
    // $(".content-a_left_c").mouseover(function(){
    //     stopTiming_xmtz(1)
    // }).mouseout(function () {
    //     stopTiming_xmtz(0)
    // })
    // //政府投资-鼠标移入移出，关闭、开启年份定时器
    // $(".content-a_left_d").mouseover(function(){
    //     stopTiming_zftz(1)
    // }).mouseout(function () {
    //     stopTiming_zftz(0)
    // })
    // //项目进度-鼠标移入移出，关闭、开启定时器
    // $(".content-b_center_a").mouseover(function(){
    //     stopTiming_xmjd(1)
    // }).mouseout(function () {
    //     stopTiming_xmjd(0)
    // })


    initPieProgress('basic-pie',dataBasic);
    if(dataLife_xmqq[0].value>0){
        initPieProgress('life-pie1',dataLife_xmqq);
    }
    if(dataLife_xmsg[0].value>0){
        initPieProgress('life-pie2',dataLife_xmsg);
    }
    if(dataLife_jgys[0].value>0){
        initPieProgress('life-pie3',dataLife_jgys);
    }
    if(dataLife_jxpj[0].value>0){
        initPieProgress('life-pie4',dataLife_jxpj);
    }

    initBar();

    // 项目投资-初始化+定时
    getInvestmentInit();
    // 政府投资-初始化
    getInvestmentData2();

    biddingPie()
    //项目进度-施工
    getGauttData()
    //项目进度-工程量
    getGcl();
    //图片进度
    projectImgList();
})
//停止与开启定时器-项目投资（图形停止+年份停止）
function stopTiming_xmtz() {
    window.clearInterval(graphInterval);//停止图形
    window.clearInterval(yearInterval);//停止年份
}
function startTiming_xmtz() {
    graphInterval=setInterval(function(){//启动图形
        graphTask()
    },intervalGraph);
    yearInterval=setInterval(function(){//启动年份
        yearTask()
    },intervalYear);
}
//停止与开启定时器-政府投资（年份停止）
function stopTiming_zftz() {
    window.clearInterval(yearInterval);
}
function startTiming_zftz() {
    yearInterval=setInterval(function(){//年份选项卡
        yearTask()
    },intervalYear);
}

//停止与开启定时器-项目进度 地图、施工、工程量
function stopTiming_xmjd() {
    window.clearInterval(xmjdInterval);//停止图形
}
function startTiming_xmjd() {
    xmjdInterval=setInterval(function(){//启动图形
        graphTask3()
    },intervalGraph3);
}

//自动-年份切换
function yearTask(){
    if(currentYearIndex+1<yearLength){
        currentYearIndex+=1;
        currentYear=yearArray[currentYearIndex];
    }else if(currentYearIndex+1==yearLength){
        currentYearIndex=0;
        currentYear="";
    }
    yearChange();
}
//年份切换-触发事件
function yearChange() {
    $('.content-a_left_c .yeartab a').eq(currentYearIndex).addClass('active').siblings().removeClass('active');
    currentGraphIndex=0;//年份改变-图形初始
    currentGraph=graphArray[currentGraphIndex];
    $('.content-a_left_c .feature a').eq(currentGraphIndex).addClass('active').siblings().removeClass('active');
    // 年份改变重新获取项目投资
    getInvestmentData();
    getInvestmentData2();
}
//项目投资-图形切换
function graphTask(){//图形切换
    if(currentGraphIndex+1<graphArray.length){
        currentGraphIndex+=1;
        currentGraph=graphArray[currentGraphIndex];
    }else if(currentGraphIndex+1==graphArray.length){
        currentGraphIndex=0;
        currentGraph=graphArray[currentGraphIndex];
    }
    $('.content-a_left_c .feature a').eq(currentGraphIndex).addClass('active').siblings().removeClass('active');
    eventTask()
}
//政府投资-图形切换
function graphTask2(){
    if(currentGraphIndex2+1<graphArray2.length){
        currentGraphIndex2+=1;
        currentGraph2=graphArray2[currentGraphIndex2];
    }else if(currentGraphIndex2+1==graphArray2.length){
        currentGraphIndex2=0;
        currentGraph2=graphArray2[currentGraphIndex2];
    }
    $('.content-a_left_d .feature a').eq(currentGraphIndex2).addClass('active').siblings().removeClass('active');
}
//项目进度-图形切换
function graphTask3(){//地图图形切换
    if(currentGraphIndex3+1<graphArray3.length){
        currentGraphIndex3+=1;
        currentGraph3=graphArray3[currentGraphIndex3];
    }else if(currentGraphIndex3+1==graphArray3.length){
        currentGraphIndex3=0;
        currentGraph3=graphArray3[currentGraphIndex3];
    }
    $('.progresstab a').eq(currentGraphIndex3).addClass('active').siblings().removeClass('active');
    eventTask3()
}
//项目投资-随着图形、年份，重新绘制
function eventTask(){
    // console.log('图形:'+currentGraph+'年份:'+currentYear);
    if(currentGraph=='pie'){
        $("#graphChart1").show();
        $("#graphChart2").html('<div id="investment-bar" class="bar-investment"></div>').hide();
        investmentPie('investment-pie1','总投资',dataXmtz_jhztz,currentYear);
        investmentPie('investment-pie2','其中政府',dataXmtz_jhzftz,currentYear);
        investmentPie('investment-pie3','总投资',dataXmtz_sjztz,currentYear);
        investmentPie('investment-pie4','其中政府',dataXmtz_sjzftz,currentYear);
    }else if(currentGraph=='bar'){
        $("#graphChart1").html('<ul><li class="investment-title">计划投资</li> <li class="investment-chart"><div id="investment-pie1" class="pie-investment"></div></li> <li class="investment-chart"><div id="investment-pie2" class="pie-investment"></div></li> </ul> <ul> <li class="investment-title">实际投资</li> <li class="investment-chart"><div id="investment-pie3" class="pie-investment"></div></li> <li class="investment-chart"><div id="investment-pie4" class="pie-investment"></div></li></ul>').hide();
        $("#graphChart2").show();
        investmentBar();
    }
    
}
//政府投资-随年份定时器，重新绘制
function eventTask2(){
    // console.log('图形:'+currentGraph);
    if(currentGraph2=='pie'){
        investmentPie2('investment-pie5','中央投资',dataTzjd_zhongy);
        investmentPie2('investment-pie6','省级投资',dataTzjd_shenj);
        investmentPie2('investment-pie7','市级投资',dataTzjd_shij);
        investmentPie2('investment-pie8','旗县投资',dataTzjd_qix);
    }else if(currentGraph2=='bar'){
        investmentBar2('investment-pie5','中央投资',dataTzjd_zhongy,'bar');
        investmentBar2('investment-pie6','省级投资',dataTzjd_shenj,'bar');
        investmentBar2('investment-pie7','市级投资',dataTzjd_shij,'bar');
        investmentBar2('investment-pie8','旗县投资',dataTzjd_qix,'bar');
    }else if(currentGraph2=='line'){
        investmentBar2('investment-pie5','中央投资',dataTzjd_zhongy,'line');
        investmentBar2('investment-pie6','省级投资',dataTzjd_shenj,'line');
        investmentBar2('investment-pie7','市级投资',dataTzjd_shij,'line');
        investmentBar2('investment-pie8','旗县投资',dataTzjd_qix,'line');
    }
}
//项目进度-施工、工程量定时器，重新绘制
function eventTask3(){
    if(currentGraph3=='map'){//地图
        $("#mapChart1").show();
        $("#mapChart2").html('<div id="progress-gautt" class="gautt-progress"></div>').hide();
        $("#mapChart3").html('<div id="progress-gcl" class="gautt-progress"></div>').hide();
        // listRoute();
    }else if(currentGraph3=='gautt'){//施工
        $("#mapChart2").html('<div id="progress-gautt" class="gautt-progress"></div>').show();
        $("#mapChart1").hide();
        $("#mapChart3").html('<div id="progress-gcl" class="gautt-progress"></div>').hide();
        progressGautt();
    }else if(currentGraph3=='line'){//工程量
        $("#mapChart3").html('<div id="progress-gcl" class="gautt-progress"></div>').show();
        $("#mapChart2").html('<div id="progress-gautt" class="gautt-progress"></div>').hide();
        $("#mapChart1").hide();
        progressGcl();
    }
}
function initPieProgress(id,data){
    var basicEchart = echarts.init(document.getElementById(id));
    var  basicOption = {
        title:{
                show:true,
                text:data[0].value+'%',
                x:'center',
                y:'center',
                textStyle: {
                    fontSize: '15',
                    color:'#009cff',
                    fontWeight: 'normal'
                }
           },
        tooltip: {
            show:false
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            show:false
        },
        color:['#009cff','#eaeef1'],
        series: 
            {
                name:'',
                type:'pie',
                radius: ['65%', '95%'],
                avoidLabelOverlap: true,
                hoverAnimation:false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: false
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:data,
            }
        
    };
    basicEchart.setOption(basicOption); 
    window.addEventListener("resize",()=>{
        basicEchart.resize();
    });
}
//资料完整性
function initBar(){
    var progressEchart = echarts.init(document.getElementById('progress-bar'));
    var option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            show:false,
        },
        grid: {
            left: '3%',
            right: '0%',
            bottom: '3%',
            top:'10%',
            containLabel: true
        },
        xAxis:  {
            type: 'category',
            data: ['项目信息','项目手续','项目投资','招标采购','合同备案','项目进度','竣工验收','绩效评价'],
            axisTick: {
                alignWithLabel: true
            }
        },
        yAxis: {
            type: 'value',
            splitLine:{
                lineStyle:{
                    color:'#eaeef1'
                }
            }
        },
        color:['#92cf43','#009cff'],
        series: [
            {
                name: '已完成',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: false,
                        position: 'insideRight'
                    }
                },
                data: dataLc_ywc,
            },
            {
                name: '未完成',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: false,
                        position: 'insideRight'
                    }
                },
                data: dataLc_wwc
            },
        ]
    };
    progressEchart.setOption(option);
    window.addEventListener("resize",()=>{
        progressEchart.resize();
    });
}

//项目投资-初始化数据+定时器
function getInvestmentInit(){
    var paradata = {
        "owid":owid,
        "year":currentYear,
    };
    var params = {
        "data": JSON.stringify(paradata),
        "method":"center/projectreport/projectViewReport/projectPlanFinance",
        timestamp: new Date().getTime()
    };
    $.post(loginUrl + "system/commonApi.htm", params, function (data) {
        if (data.backCode == 0) {
            dataXmtz_jhztz=[{value:data.bean.planDwtz, name:'单位投资'},{value:data.bean.planShzb, name:'社会资本'},{value:data.bean.planJrxd, name:'金融信贷'},{value:data.bean.planZftz, name:'政府投资'},{value:data.bean.planQt, name:'其他'}];
            dataXmtz_jhzftz=[{value:data.bean.planZytz, name:'中央投资'},{value:data.bean.planProv, name:'省级投资'},{value:data.bean.planCity, name:'市级投资'},{value:data.bean.planArea, name:'旗县投资'}];
            dataXmtz_sjztz=[{value:data.bean.sjtzDwtz, name:'单位投资'},{value:data.bean.sjtzShzb, name:'社会资本'},{value:data.bean.sjtzJrxd, name:'金融信贷'},{value:data.bean.sjtzZftz, name:'政府投资'},{value:data.bean.sjtzQt, name:'其他'}];
            dataXmtz_sjzftz=[{value:data.bean.sjtzZytz, name:'中央投资'},{value:data.bean.sjtzProv, name:'省级投资'},{value:data.bean.sjtzCity, name:'市级投资'},{value:data.bean.sjtzArea, name:'旗县投资'}];
            dataXmtz_sjztzR=dataXmtz_sjztz.concat().reverse();
            dataXmtz_sjzftzR=dataXmtz_sjzftz.concat().reverse();
            dataXmtz_jhztzR=dataXmtz_jhztz.concat().reverse();
            dataXmtz_jhzftzR=dataXmtz_jhzftz.concat().reverse();
            sjtzGoverment=data.bean.sjtzGoverment;
            sjtzFzftz=data.bean.sjtzFzftz;
            eventTask();
            // graphInterval=setInterval(function(){//图形选项卡
            //     graphTask()
            // },intervalGraph);
            // yearInterval=setInterval(function(){//年份选项卡
            //     yearTask()
            // },intervalYear);
        }
    })

}
//项目投资-年份数据刷新
function getInvestmentData(){
    var paradata = {
        "owid":owid,
        "year":currentYear,
    };
    var params = {
        "data": JSON.stringify(paradata),
        "method":"center/projectreport/projectViewReport/projectPlanFinance",
        timestamp: new Date().getTime()
    };
    $.post(loginUrl + "system/commonApi.htm", params, function (data) {
        if (data.backCode == 0) {
            dataXmtz_jhztz=[{value:data.bean.planDwtz, name:'单位投资'},{value:data.bean.planShzb, name:'社会资本'},{value:data.bean.planJrxd, name:'金融信贷'},{value:data.bean.planZftz, name:'政府投资'},{value:data.bean.planQt, name:'其他'}];
            dataXmtz_jhzftz=[{value:data.bean.planZytz, name:'中央投资'},{value:data.bean.planProv, name:'省级投资'},{value:data.bean.planCity, name:'市级投资'},{value:data.bean.planArea, name:'旗县政府'}];
            dataXmtz_sjztz=[{value:data.bean.sjtzDwtz, name:'单位投资'},{value:data.bean.sjtzShzb, name:'社会资本'},{value:data.bean.sjtzJrxd, name:'金融信贷'},{value:data.bean.sjtzZftz, name:'政府投资'},{value:data.bean.sjtzQt, name:'其他'}];
            dataXmtz_sjzftz=[{value:data.bean.sjtzZytz, name:'中央投资'},{value:data.bean.sjtzProv, name:'省级投资'},{value:data.bean.sjtzCity, name:'市级投资'},{value:data.bean.sjtzArea, name:'旗县政府'}];
            dataXmtz_sjztzR=dataXmtz_sjztz.concat().reverse();
            dataXmtz_sjzftzR=dataXmtz_sjzftz.concat().reverse();
            dataXmtz_jhztzR=dataXmtz_jhztz.concat().reverse();
            dataXmtz_jhzftzR=dataXmtz_jhzftz.concat().reverse();
            sjtzGoverment=data.bean.sjtzGoverment;
            sjtzFzftz=data.bean.sjtzFzftz;
            eventTask()
        }
    })

}
//项目投资饼图-绘制
function investmentPie(id,title,data){
    var color=['#009cff','#20bf6b','#f7b731','#ff8746','#5144fd'];
    var tooltip={
        trigger: 'item',
        // formatter: "{b} : {c}万元 ({d}%)",
        formatter: function (a) {
            return ( a['name']+" : "+abs(a['value'])+"万元 ("+a['percent']+"%)")
        },
        position: 'right',
    };
    var ho=true;
    var center=['60%', '46%']
    var ti={
        show:true,
        text: title,
        textVerticalAlign:'bottom',
        bottom:0,
        x:'50%',
        textStyle:{
            color:'#636363',
            fontSize:'12'
        }
    }
    var count=0;
    var nameList=[];
    for(var i=0;i<data.length;i++){
        count+=Number(data[i].value);
        nameList.push(data[i].name);
    }
    if(count==0){
        data=[
            {value:0, name:''},
            {value:100, name:''}
        ];
        color=['#009cff','#eaeef1'];
        tooltip={
            show:false
        }
        ho=false;
        center=['50%', '50%']
        ti = {
            show:true,
            text: '无投资',
            textVerticalAlign:'bottom',
            bottom:0,
            subtext:title,
            x:'center',
            padding:0,
            itemGap:60,//主副标题之间的间距
            textStyle:{
                color:'#34343d',
                fontSize:'11',
                fontWeight:'normal'
            },
            subtextStyle:{
                color:'#636363',
                fontSize:'12',
                fontWeight:'bold'
            }
        }
    }
    var chart = echarts.init(document.getElementById(id));
    chart.clear();
    var option = {
        title : ti,
        tooltip : tooltip,
        legend: {
            orient: 'vertical',
            x: '3%',
            y:'15%',
            selectedMode:false,
            itemWidth:8,  //图例标记的图形宽度                       
            itemHeight:8, //图例标记的图形高度 
            icon:"circle",
            textStyle:{ //图例文字样式            
                fontSize:12,
            },
            data:nameList
        },
        series : [
            {
                name: '',
                type: 'pie',
                radius: ['0%', '70%'],
                center: center,
                color:color,
                data:data,
                hoverAnimation:ho,
                labelLine: {
                    normal: {
                        show:false,
                        // length: 5,
                        // length2: 40,
                    }

                },
                label: {
                    normal: {
                        show:false,
                        formatter: '{b}\n ',
                        fontSize:11,
                        // borderWidth: 20,
                        // borderRadius: 4,
                        padding: [-10,-20],

                    }
                },
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    chart.setOption(option)
    window.addEventListener("resize",()=>{
        chart.resize();
    });
}
//项目投资柱状图曲线图-绘制
function investmentBar(){
    var chart = echarts.init(document.getElementById('investment-bar'));
    chart.clear();
    var option = {
        tooltip : {
            trigger: 'axis',//axis
            backgroundColor: 'rgba(0,0,0,0.5)',
            formatter: function(params) {
                var res="<div>";
                for(var i=0;i<params.length;i++){
                    if((params[i].dataIndex==0)&&(params[i].seriesIndex<9)){
                        if(params[i].data){
                            res += "<span style='color: #ffffff;'><a style='background:"+params[i].color+";width:6px;height: 6px;border-radius:50%;display: inline-block;'></a>&nbsp;&nbsp;"+params[i].seriesName+" :&nbsp;&nbsp;&nbsp;</span><span style='color:#fff;'>" + abs(params[i].data)+ " 万元</span></br>"
                        }
                    }else{
                        res += "<span style='color: #ffffff;'><a style='background:"+params[i].color+";width:6px;height: 6px;border-radius:50%;display: inline-block;'></a>&nbsp;&nbsp;"+params[i].seriesName+" :&nbsp;&nbsp;&nbsp;</span><span style='color:#fff;'>" + abs(params[i].data)+ " 万元</span></br>"
                    }
                }
                res+='</div>'
                return res

            },
        },
        legend: {
            // x: 'right',    //图例位置            
            right:'10px',
            y:'5px',
            selectedMode:false,
            // icon: "line",
            data: ['实际政府投资','实际投资其他'],
        },
        grid: {
            left: '0%',
            right: '0%',
            bottom: '3%',
            top:'40px',
            containLabel: true
        },
        xAxis:  {
            type: 'category',
            data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
            axisTick: {
                alignWithLabel: true
            }
        },
        yAxis:[
            {
                type: 'value',
                splitLine: {
                    lineStyle: {
                        color: '#eaeef1'
                    }
                },
            },
            {
                type: 'value',
                splitLine: {
                    lineStyle: {
                        color: '#eaeef1'
                    }
                },
            }
        ],
        // color:['#009cff','#20bf6b','#f7b731','#ff8746','#5144fd','#ff8746','#5144fd'],
        series: [
            {
                name:'计划单位投资',
                type:'bar',
                stack: '计划总投资',
                data:[dataXmtz_jhztz[0].value],
                color:['#009cff']
            },
            {
                name:'计划社会资本',
                type:'bar',
                stack: '计划总投资',
                data:[dataXmtz_jhztz[1].value],
                color:['#20bf6b']
            },
            {
                name:'计划金融信贷',
                type:'bar',
                stack: '计划总投资',
                data:[dataXmtz_jhztz[2].value],
                color:['#f7b731']
            },
            {
                name:'计划政府投资',
                type:'bar',
                stack: '计划总投资',
                data:[dataXmtz_jhztz[3].value],
                color:['#ff8746']
            },
            {
                name:'计划投资其他',
                type:'bar',
                stack: '计划总投资',
                data:[dataXmtz_jhztz[4].value],
                color:['#5144fd']
            },
            {
                name:'计划中央投资',
                type:'bar',
                stack: '计划政府投资',
                data:[dataXmtz_jhzftz[0].value],
                color:['#009cff']
            },
            {
                name:'计划省级投资',
                type:'bar',
                stack: '计划政府投资',
                data:[dataXmtz_jhzftz[1].value],
                color:['#20bf6b']
            },
            {
                name:'计划市级投资',
                type:'bar',
                stack: '计划政府投资',
                data:[dataXmtz_jhzftz[2].value],
                color:['#f7b731']
            },
            {
                name:'计划旗县投资',
                type:'bar',
                stack: '计划政府投资',
                data:[dataXmtz_jhzftz[3].value],
                color:['#ff8746']
            },
            // {
            //     name:'实际单位投资',
            //     type:'bar',
            //     stack: '实际总投资',
            //     data:[dataXmtz_sjztz[0].value]
            // },
            // {
            //     name:'实际社会资本',
            //     type:'bar',
            //     stack: '实际总投资',
            //     data:[dataXmtz_sjztz[1].value]
            // },
            // {
            //     name:'实际金融信贷',
            //     type:'bar',
            //     stack: '实际总投资',
            //     data:[dataXmtz_sjztz[2].value]
            // },
            // {
            //     name:'实际政府投资',
            //     type:'bar',
            //     stack: '实际总投资',
            //     data:[dataXmtz_sjztz[3].value]
            // },
            // {
            //     name:'实际投资其他',
            //     type:'bar',
            //     stack: '实际总投资',
            //     data:[dataXmtz_sjztz[4].value]
            // },
            // {
            //     name:'实际中央投资',
            //     type:'bar',
            //     stack: '实际政府投资',
            //     data:[dataXmtz_sjzftz[0].value]
            // },
            // {
            //     name:'实际省级投资',
            //     type:'bar',
            //     stack: '实际政府投资',
            //     data:[dataXmtz_sjzftz[1].value]
            // },
            // {
            //     name:'实际市级投资',
            //     type:'bar',
            //     stack: '实际政府投资',
            //     data:[dataXmtz_sjzftz[2].value]
            // },
            // {
            //     name:'实际旗县投资',
            //     type:'bar',
            //     stack: '实际政府投资',
            //     data:[dataXmtz_sjzftz[3].value]
            // },
            {
                name:'实际政府投资',
                type:'line',
                yAxisIndex: 1,
                data:sjtzGoverment,
                color:['#20bf6b']
            },
            {
                name:'实际投资其他',
                type:'line',
                yAxisIndex: 1,
                data:sjtzFzftz,
                color:['#5144fd']
            }
        ]
    };
    chart.setOption(option)
    window.addEventListener("resize",()=>{
        chart.resize();
    });
}


//政府投资-初始化数据
//年份刷新数据
function getInvestmentData2(){
    $("#investment-pie5").parent().html('<div id="investment-pie5" class="pie-investment"></div><p class="note"></p>');
    $("#investment-pie6").parent().html('<div id="investment-pie6" class="pie-investment"></div><p class="note"></p>');
    $("#investment-pie7").parent().html('<div id="investment-pie7" class="pie-investment"></div><p class="note"></p>');
    $("#investment-pie8").parent().html('<div id="investment-pie8" class="pie-investment"></div><p class="note"></p>');
    var paradata = {
        "owid":owid,
        "year":currentYear,
    };
    var params = {
        "data": JSON.stringify(paradata),
        "method":"center/projectreport/projectViewReport/projectZftzBrzj",
        timestamp: new Date().getTime()
    };
    $.post(loginUrl + "system/commonApi.htm", params, function (data) {
        if (data.backCode == 0) {
            if(data.bean.zytz<0){data.bean.zytz=0}
            if(data.bean.prov.ELE_COMM<0){data.bean.prov.ELE_COMM=0}
            if(data.bean.prov.ELE_POLICY<0){data.bean.prov.ELE_POLICY=0}
            if(data.bean.prov.ELE_CONTRY<0){data.bean.prov.ELE_CONTRY=0}
            if(data.bean.prov.ELE_OTHER1<0){data.bean.prov.ELE_OTHER1=0}
            if(data.bean.city.ELE_COMM<0){data.bean.city.ELE_COMM=0}
            if(data.bean.city.ELE_POLICY<0){data.bean.city.ELE_POLICY=0}
            if(data.bean.city.ELE_CONTRY<0){data.bean.city.ELE_CONTRY=0}
            if(data.bean.city.ELE_OTHER1<0){data.bean.city.ELE_OTHER1=0}
            if(data.bean.area.ELE_COMM<0){data.bean.area.ELE_COMM=0}
            if(data.bean.area.ELE_POLICY<0){data.bean.area.ELE_POLICY=0}
            if(data.bean.area.ELE_CONTRY<0){data.bean.area.ELE_CONTRY=0}
            if(data.bean.area.ELE_OTHER1<0){data.bean.area.ELE_OTHER1=0}

            dataTzjd_zhongy=[{value:data.bean.zytz, name:'中央投资'}];//投资进度-中央投资
            dataTzjd_shenj=[{value:data.bean.prov.ELE_COMM, name:'一般公共预算'},{value:data.bean.prov.ELE_POLICY, name:'政府基金'},{value:data.bean.prov.ELE_CONTRY, name:'国有资本'},{value:data.bean.prov.ELE_OTHER1, name:'其他'}];//投资进度-省级投资
            dataTzjd_shij=[{value:data.bean.city.ELE_COMM, name:'一般公共预算'},{value:data.bean.city.ELE_POLICY, name:'政府基金'},{value:data.bean.city.ELE_CONTRY, name:'国有资本'},{value:data.bean.city.ELE_OTHER1, name:'其他'}];//投资进度-市级投资
            dataTzjd_qix=[{value:data.bean.area.ELE_COMM, name:'一般公共预算'},{value:data.bean.area.ELE_POLICY, name:'政府基金'},{value:data.bean.area.ELE_CONTRY, name:'国有资本'},{value:data.bean.area.ELE_OTHER1, name:'其他'}];//投资进度-旗县政府

            if(data.bean.zytz!=0){
                $(".investment-body2").find(".investment-chart").eq(0).find("p.note").html(data.bean.zytz+"万");
            }else{
                dataTzjd_zhongy=[]
                $(".investment-body2").find(".investment-chart").eq(0).find("p.note").html("无中央投资");
            }
            var provZtz=Number(data.bean.prov.ELE_COMM)+Number(data.bean.prov.ELE_POLICY)+Number(data.bean.prov.ELE_CONTRY)+Number(data.bean.prov.ELE_OTHER1)
            if(provZtz!=0){
                var node=""
                if((data.bean.prov.dw)&&(data.bean.prov.dw.length>10)){
                    node=data.bean.prov.dw.substring(0,10)+'...'
                }else{
                    node=convertStr(data.bean.prov.dw,"")
                }
                $(".investment-body2").find(".investment-chart").eq(1).find("p.note").html(provZtz+"万<br>"+node).attr("title",convertStr(data.bean.prov.dw,""));
            }else{
                dataTzjd_shenj=[]
                $(".investment-body2").find(".investment-chart").eq(1).find("p.note").html("无省级投资");
            }
            var cityZtz=Number(data.bean.city.ELE_COMM)+Number(data.bean.city.ELE_POLICY)+Number(data.bean.city.ELE_CONTRY)+Number(data.bean.city.ELE_OTHER1)
            if(cityZtz!=0){
                var node=""
                if((data.bean.city.dw)&&(data.bean.city.dw.length>10)){
                    node=data.bean.city.dw.substring(0,10)+'...'
                }else{
                    node=convertStr(data.bean.city.dw,"")
                }
                $(".investment-body2").find(".investment-chart").eq(2).find("p.note").html(cityZtz+"万<br>"+node).attr("title",convertStr(data.bean.city.dw,""));
            }else{
                dataTzjd_shij=[]
                $(".investment-body2").find(".investment-chart").eq(2).find("p.note").html("无市级投资");
            }
            var areaZtz=Number(data.bean.area.ELE_COMM)+Number(data.bean.area.ELE_POLICY)+Number(data.bean.area.ELE_CONTRY)+Number(data.bean.area.ELE_OTHER1)
            if(areaZtz!=0){
                var node=""
                if((data.bean.area.dw)&&(data.bean.area.dw.length>10)){
                    node=data.bean.area.dw.substring(0,10)+'...'
                }else{
                    node=convertStr(data.bean.area.dw,"")
                }
                $(".investment-body2").find(".investment-chart").eq(3).find("p.note").html(areaZtz+"万<br>"+node).attr("title",convertStr(data.bean.area.dw,""));
            }else{
                dataTzjd_qix=[]
                $(".investment-body2").find(".investment-chart").eq(3).find("p.note").html("无旗县投资");
            }
            eventTask2();
        }else{
            $("#investment-pie5").html(nullTip);
            $("#investment-pie6").html(nullTip);
            $("#investment-pie7").html(nullTip);
            $("#investment-pie8").html(nullTip);
        }
    })
}
//政府投资-饼图绘制
function investmentPie2(id,title,dt){
    var data=dt;
    var color=['#5144fd','#ff8746','#f7b731','#009cff'];
    var tooltip={
        trigger: 'item',
        // formatter: "{b} : {c}万元 ({d}%)",
        formatter: function (a) {
            return ( a['name']+" : "+abs(a['value'])+"万元 ("+a['percent']+"%)")
        },
        position: 'right',
    };
    var ho=true;
    var ti={
        show:true,
        text: title,
        // textVerticalAlign:'top',
        top:0,
        x:'center',
        textStyle:{
            color:'#282828',
            fontSize:'13'
        }
    }
    if(data.length==0){
        data=[
            {value:0, name:''},
            {value:100, name:''}
        ];
        color=['#009cff','#eaeef1'];
        tooltip={
            show:false
        }
        ho=false;
        ti = {
            show:true,
            text: title,
            subtext:'无投资',
            x:'center',
            itemGap:50,//主副标题之间的间距
            textStyle:{
                color:'#282828',
                fontSize:'13'
            },
            subtextStyle:{
                color:'#34343d',
                fontSize:'11',
            }
        }
    }
    var chart = echarts.init(document.getElementById(id));
    chart.clear();
    var option = {
        title : ti,
        tooltip : tooltip,
        series : [
            {
                name: '',
                type: 'pie',
                radius: ['36%', '60%'],
                center: ['50%', '45%'],
                color:color,
                data:data,
                hoverAnimation:ho,
                labelLine: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                },
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                },
                itemStyle: {
                    emphasis: {
                        shadowBlur: 30,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.3)'
                    }
                }
            }
        ]
    };
    chart.setOption(option)
    window.addEventListener("resize",()=>{
        chart.resize();
    });
}
//政府投资-柱状绘制（预留）
function investmentBar2(id,title,dt,type){
    var data=[];
    if(dt.length>0){
        for(var i=0;i<dt.length;i++){
            data.push(dt[i].value)
        }
    }else{
        investmentPie2(id,title,dt)
        return;
    }
    var tooltip={
        trigger: 'axis',
        // formatter: "{b} : {c}万元",
        formatter: function (a) {
            return ( a['name']+" : ("+abs(a['value'])+"万元)")
        },
        position: function (point, params, dom, rect, size) {
            // 固定在顶部
            return [point[0], '10%'];
        }
    };
    var ti={
        show:true,
        text: title,
        top:0,
        x:'center',
        textStyle:{
            color:'#282828',
            fontSize:'13'
        }
    }
    var chart = echarts.init(document.getElementById(id));
    chart.clear();
    var option = {
        title : ti,
        tooltip : tooltip,
        color: ['#5144fd','#ff8746','#f7b731','#20bf6b','#009cff'],
        grid: {
            left: '5',
            right: '0%',
            bottom: '45',
            top:'35',
            // containLabel: true
        },
        xAxis:  {
            type: 'category',
            axisLabel:{
                show:false,
                interval:0,
                rotate:-30,
                margin: 20,
                textStyle:{
                    align: 'center',
                    color: function (value, index) {
                        var colorList = ['#5144fd','#ff8746','#f7b731','#20bf6b','#009cff']
                        return colorList[index];
                        
                    }
                },
                
            },
            data : ['一般公共预算','政府基金','国有资本','其他'],
        },
        yAxis: {
            show:false,
        },
        series : [
            {
                type:type,
                itemStyle: {
                    normal: {
                        color: function(params) {
                            var colorList = ['#5144fd','#ff8746','#f7b731','#20bf6b','#009cff']
                            return colorList[params.dataIndex];
                        },
                        lineStyle:{  
                            width:1
                        }, 
                        label: {
                            show: true, //开启显示
                            position: 'top', //在上方显示
                        }
                    }
                },
                data:data
            }
        ]
    };
    chart.setOption(option)
    window.addEventListener("resize",()=>{
        chart.resize();
    });
}

//招标采购、合同备案、竣工验收
function biddingPie(){
    var urlArr = ["center/projectreport/projectViewReport/projectZbcgTj","center/projectreport/projectViewReport/projectHtbaTj","center/projectreport/projectViewReport/projectJgysTj"];
    var paradata = {
        "owid":owid,
        "year":currentYear,
    };
    var params = {
        "data": JSON.stringify(paradata),
        "method":"",
        timestamp: new Date().getTime()
    };
    var dataList=[];
    var colorlist=[];
    var legendlist=[];
    var total=0;
    $.each(chartDom,function(k,p) {
        pieChart[k] = echarts.init(p);
        pieChart[k].showLoading({color: '#5caefd'});
        params.method = urlArr[k];
        $.post(loginUrl + "system/commonApi.htm", params, function (data) {
            // window.parent.ajax(urlArr[k],params,function(data){
            pieChart[k].hideLoading();
            if (data.backCode == 0) {
                 if((data.bean)&&(data.bean.length>0)){
                     dataList=[];
                     colorlist=[];
                     legendlist=[];
                     total=0;
                     if(k==0){
                         colorlist=['#ff635e','#ffb22d','#92cf43','#009cff']
                         for(var i=0;i<data.bean.length;i++){
                             var obj={};
                             if(data.bean[i].MODULE_OWID=='D011'||data.bean[i].MODULE_OWID=='D008 '){
                                 obj.value=data.bean[i].CS;
                                 obj.je=data.bean[i].BUY_FINAL_AMOUNT//金额，只有自行建设和金额招标需要显示
                             }else{
                                 obj.value=data.bean[i].CS;
                             }
                             obj.name=data.bean[i].NAME;
                             dataList.push(obj);
                             legendlist.push(data.bean[i].NAME)
                             total+=data.bean[i].CS
                         }
                     }else if(k==1){
                         colorlist=['#ffb22d','#92cf43','#009cff']
                         for(var i=0;i<data.bean.length;i++){
                             var obj={};
                             obj.value=data.bean[i].CS;
                             obj.je=data.bean[i].CONTRACT_AMOUNT
                             obj.name=data.bean[i].NAME;
                             dataList.push(obj);
                             legendlist.push(data.bean[i].NAME)
                             total+=data.bean[i].CS
                         }
                     }else if(k==2){
                         colorlist=['#ffb22d','#92cf43','#009cff']
                         for(var i=0;i<data.bean.length;i++){
                             var obj={};
                             obj.value=data.bean[i].CS;
                             obj.name=data.bean[i].MODULE_NAME;
                             dataList.push(obj);
                             legendlist.push(data.bean[i].MODULE_NAME)
                             total+=data.bean[i].CS
                         }
                     }
                     var index = 0;//默认选中高亮模块索引
                     var  option = {
                         tooltip : {
                             trigger: 'item',
                             formatter: function(a) {
                                 if(a['data'].je){
                                     return ( a['name']+" : "+a['value']+"次 ("+abs(a['data'].je)+"万元)")
                                 }else{
                                     return ( a['name']+" : "+a['value']+"次")
                                 }

                             },
                             // "{b} : {c} ({d}%)",
                             position: 'right',
                         },
                         legend: {
                             x: 'center',    //图例位置            
                             top:'8',
                             selectedMode:false,
                             itemWidth:8,  //图例标记的图形宽度                       
                             itemHeight:8, //图例标记的图形高度 
                             icon: "circle",
                             padding:10,
                             // itemGap:-5,               
                             textStyle:{ //图例文字样式            
                                 fontSize:12,
                                 color:'#282828'
                             },
                             data: legendlist,
                         },
                         color:colorlist,
                         series:
                             {
                                 name:'',
                                 type:'pie',
                                 radius: ['43%', '70%'],
                                 center: ['50%', '57%'],
                                 avoidLabelOverlap: false,
                                 hoverAnimation:false,
                                 label: {
                                     normal: {
                                         show: false,
                                         position: 'center'
                                     },
                                     emphasis: {
                                         show: true,
                                         formatter:[
                                             '{bt|{b}}',
                                             '({num|{c}次})'
                                         ].join('\n'),
                                         rich: {
                                             bt: {
                                                 fontSize: '15',
                                                 lineHeight:'25',
                                             },
                                             num:{
                                                 fontSize: '12',
                                             }
                                         },
                                     },
                                 },
                                 labelLine: {
                                     normal: {
                                         show: false
                                     }
                                 },
                                 data:dataList
                             }

                     };
                     pieChart[k].setOption(option);
                     pieChart[k].dispatchAction({type: 'highlight',seriesIndex: 0,dataIndex: 0});//设置默认选中高亮部分
                     window.addEventListener("resize",()=>{
                         pieChart[k].resize();
                    });
                     pieChart[k].on('mouseover',function(e){
                         if(e.dataIndex != index){
                             pieChart[k].dispatchAction({type: 'downplay', seriesIndex: 0, dataIndex: index  });
                         }
                     });
                     pieChart[k].on('mouseout',function(e){
                         index = e.dataIndex;
                         pieChart[k].dispatchAction({type: 'highlight',seriesIndex: 0,dataIndex: e.dataIndex});
                     });
                 } else{
                     $(chartDom[k]).html(nullTip);
                 }
            }
        });
    });

}

//项目进度-地图、施工、工程量+定时器
function progress_Init() {
    // xmjdInterval=setInterval(function(){//图形选项卡
    //     graphTask3()
    // },intervalGraph3);
}
//获取地图
function listRoute() {
    var paradata = {
        "owid":owid,//测试ff8080816b6b62f5016b796945b30ec4
        "year":currentYear,
    };
    var params = {
        "data": JSON.stringify(paradata),
        "method":"center/projectreport/projectViewReport/projectMap",
        timestamp: new Date().getTime()
    };
    $.post(loginUrl + "system/commonApi.htm", params, function (data) {
        if(data.backCode == 0) {
            progress_Init();
            if(!data.bean){//表示没有地图进度

            }else{
                var point = new BMap.Point(data.bean.jhwc[0][0], data.bean.jhwc[0][1]);
                map.centerAndZoom(point, 12);
                // map.panTo(map.centerAndZoom(data.bean.jhwc[0][0], 13));
                //计划
                let pointsPlan = [];
                $.each(data.bean.jhwc, function(k, p) {
                    pointsPlan.push(new BMap.Point(p[0], p[1]));
                });
                addPolyLine(pointsPlan, strokeStyleRed);
                if((data.bean.sjwc)&&(data.bean.sjwc.length>0)){
                    //实际
                    let pointsAct = [];
                    $.each(data.bean.sjwc, function(k, p) {
                        pointsAct.push(new BMap.Point(p[0], p[1]));
                    });
                    addPolyLine(pointsAct, strokeStyleGrn);
                }
            }


        }
    });

}
//获取甘特图数据
function getGauttData() {
    var paradata = {
        "owid":owid,
        "year":currentYear,
    };
    var params = {
        "data": JSON.stringify(paradata),
        "method":"center/projectreport/projectViewReport/projectGtt",
        timestamp: new Date().getTime()
    };
    $.post(loginUrl + "system/commonApi.htm", params, function (data) {
        if (data.backCode == 0) {
            if(data.bean){
                gauttFlag="1";
                dataGautt_y=data.bean.smallTask;//任务名称
                dataGautt_jhks=data.bean.beginDt;
                dataGautt_jhsj=data.bean.endDt;
                dataGautt_sjks=data.bean.realBeginDt;
                dataGautt_sjsj=data.bean.realEndDt;
                progressGautt()
            }else{
                gauttFlag="null"
            }

        }
    })
}
function progressGautt(){
    if(gauttFlag=="null"){
        $("#progress-gautt").html(nullTip)
    }else{
        var sgjdchart = echarts.init(document.getElementById('progress-gautt'));
        var option = {
            title: {
                show:false,
            },
            legend: {
                show:false,
            },
            grid: {
                containLabel: true,
                left: 20,
                right:30,
            },
            xAxis: {
                type: 'time',
                position: "top",
                axisTick: {
                    inside: true
                },
                axisLabel: {
                    color:'#282828',
                    fontWeight:'bold',
                    formatter: function(value, index) {
                        // 格式化成月/日，只在第一个刻度显示年份
                        var date = new Date(value);
                        var texts = [date.getFullYear(), (date.getMonth() + 1)];
                        return texts.join('.');
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: '#282828',
                        width: 1,
                    }
                }
            },
            // dataZoom: [
            //     {
            //         show: true,
            //         realtime: true,
            //         position: 'bottom',
            //         height: 20,
            //         start: 0,
            //         end: 100,
            //         type: 'inside'
            //     },
            //     {
            //         show: true,
            //         realtime: true,
            //         position: 'bottom',
            //         height: 20,
            //         start: 0,
            //         end: 100,
            //         type: 'slider'
            //     }
            // ],
            yAxis: {
                axisTick: {
                    inside: true
                },
                axisLabel:{
                    color:'#282828',
                    fontWeight:'bold',
                    // rotate:60,
                    // margin: 10,
                    // inside:true,
                    formatter: function(value) {
                        if (value.length > 6) {
                            return value.substring(0, 6) + "...";
                        } else {
                            return value;
                        }
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: '#282828',
                        width: 1,
                    }
                },
                inverse:true,
                type:"category",
                data: dataGautt_y,

            },
            tooltip: {
                trigger: 'axis',
                backgroundColor: 'rgba(0,0,0,0.5)',
                formatter: function(params) {
                    var res = "";
                    var date0 = new Date(params[0].data);
                    var date1 = new Date(params[1].data);
                    var date2 = new Date(params[2].data);
                    var date3 = new Date(params[3].data);
                    date0 = date0.getFullYear() + "-" + (date0.getMonth() + 1) + "-" + date0.getDate();
                    date1 = date1.getFullYear() + "-" + (date1.getMonth() + 1) + "-" + date1.getDate();
                    date2 = date2.getFullYear() + "-" + (date2.getMonth() + 1) + "-" + date2.getDate();
                    date3 = date3.getFullYear() + "-" + (date3.getMonth() + 1) + "-" + date3.getDate();
                    res += "<div><div>"+params[0].name+"</div><span style='color: #fed755;'>计划 :&nbsp;&nbsp;&nbsp;</span><span style='color:#fff;'>" + date0 + "~" + date1 + "</span></br>"
                    res += "<span style='color: #92cf43;'>实际 :&nbsp;&nbsp;&nbsp;</span><span style='color:#fff;'>" + date2 + "~" + date3 + "</span></br></div>"
                    // res += "计划:" + date0 + "~" + date1 + "</br>"
                    // res += "实际:" + date2 + "~" + date3 + "</br>"
                    // console.log(params[0]);
                    return res;
                }
            },
            series: [
                {
                    name: '计划开始时间',
                    type: 'bar',
                    stack: 'test1',
                    itemStyle: {
                        normal: {
                            color: 'rgba(0,0,0,0)'
                        }
                    },
                    data: dataGautt_jhks
                },
                {
                    name: '计划时间',
                    type: 'bar',
                    stack: 'test1',
                    barGap:'10%',
                    //修改地方2
                    itemStyle: {
                        normal: {
                            color: '#fed755'
                        }
                    },
                    data: dataGautt_jhsj
                },
                {
                    name: '实际开始时间',
                    type: 'bar',
                    stack: 'test2',
                    itemStyle: {
                        normal: {
                            color: 'rgba(0,0,0,0)'
                        }
                    },
                    data: dataGautt_sjks
                },
                {
                    name: '实际时间',
                    type: 'bar',
                    stack: 'test2',
                    //修改地方3
                    itemStyle: {
                        normal: {
                            color: '#92cf43'
                        }
                    },
                    data: dataGautt_jhsj
                }
            ]
        };
        sgjdchart.setOption(option);
        window.addEventListener("resize",()=>{
            sgjdchart.resize();
    });
    }

    //   window.onresize = sgjdchart.resize;
}
//获取工程量
function getGcl() {
    var paradata = {
        "owid":owid,
        "year":currentYear,
    };
    var params = {
        "data": JSON.stringify(paradata),
        "method":"center/projectreport/projectViewReport/projectGclQd",
        timestamp: new Date().getTime()
    };
    $.post(loginUrl + "system/commonApi.htm", params, function (data) {
        if (data.backCode == 0) {
            if(data.bean){
                gclFlag="1";
                dataGcl_wc=data.bean.finishGcl;
                dataGcl_jh=data.bean.totalGcl;
                dataGcl_xlabel=data.bean.xlabel;
                progressGcl()
            }else {
                gclFlag="null";
            }

        }
    })
}
function progressGcl() {
    if(gclFlag=="null"){
        $("#progress-gcl").html(nullTip)
    }else{
        var chart = echarts.init(document.getElementById('progress-gcl'));
        chart.clear();
        var option = {
            tooltip : {
                trigger: 'axis',//axis
                backgroundColor: 'rgba(0,0,0,0.5)',
                // formatter: "{b} : {c} ({d}%)",
            },
            legend: {
                show:false,
            },
            grid: {
                left: '3%',
                right: '3%',
                bottom: '5%',
                top:'10%',
                containLabel: true
            },
            xAxis:  {
                type: 'category',
                data: dataGcl_xlabel,
                axisLabel:{
                    interval:0,
                    rotate:-20,
                    formatter: function(value) {
                        if (value.length > 8) {
                            return value.substring(0, 8) + "...";
                        } else {
                            return value;
                        }
                    }
                },
                axisTick: {
                    alignWithLabel: true
                }
            },
            yAxis:[
                {
                    type: 'value',
                    splitLine: {
                        lineStyle: {
                            color: '#eaeef1'
                        }
                    },
                }
            ],
            color:['#92cf43','#fed755'],
            series: [{
                name:'实际量',
                type:'line',
                data:dataGcl_wc
            },
                {
                    name:'计划量',
                    type:'line',
                    data:dataGcl_jh
                }
            ]
        };
        chart.setOption(option)
        window.addEventListener("resize",()=>{
            chart.resize();
    });
    }
}

//图片进度
function projectImgList() {
    var paradata = {
        "owid":owid,
        "year":currentYear,
    };
    var params = {
        "data": JSON.stringify(paradata),
        "method":"center/projectreport/projectViewReport/projectImgList",
        timestamp: new Date().getTime()
    };
   $.post(loginUrl + "system/commonApi.htm", params, function (data) {
        if (data.backCode == 0) {
               if((data.bean)&&(data.bean.length>0)){
                   var str='<ul class="swiper-wrapper">'
                   $.each(data.bean,function (k,p) {
                         str+='<li class="swiper-slide"><img src="'+imgUrl+"/"+p.IMG+'" onclick="bigPic(\''+imgUrl+"/"+p.IMG+'\')" /></li>'
                   })
                   str+='</ul>'
                   $('#imageSlider').html(str);
                   var imageSwiper = new Swiper('#imageSlider', {
                       freeMode: true,
                       slidesPerView: 3,
                       slidesPerGroup: 3,
                       spaceBetween: 15,
                       autoplay: {
                           delay: 30000,
                       }
                   })
               }else{
                   $('#imageSlider').html(nullTip);
               }
        }
   })
}
//图片放大
function bigPic(pic) {
    $("#imgForBig").find("img").attr("src",pic);
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        area: '',
        skin: '#FFF', //没有背景色
        shadeClose: true,
        content: $('#imgForBig')
    });
}
// 取消null显示
function convertStr(val,val1) {
    if ("" == val || null == val || 'null' == val || 'undefined' == val) {
        return val1;
    } else
        return val;
}

//金额格式化
function abs(val){
//金额转换 去除小数点 并每隔3位用逗号分开 1,234
    var str = parseInt(val) + '';
    var intSum = str.replace( /\B(?=(?:\d{3})+$)/g, ',' );//取到整数部分
    var ret = intSum;
    return ret;
}
//地图相关++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
var map; //Map实例
var strokeStyleRed = {
    strokeColor: "#fc625d",
    strokeWeight: 6,
    strokeOpacity: 0.9
};
var strokeStyleGrn = {
    strokeColor: "#43cfa9",
    strokeWeight: 6,
    strokeOpacity: 0.9
};
function map_init() {
    //添加地图类型控件
    map = new BMap.Map("progress-map", {
        mapType: BMAP_HYBRID_MAP
    }); //混合图   2D图BMAP_NORMAL_MAP,

    //移动到当前所在的位置
    /*var geolocation = new BMap.Geolocation();

     geolocation.getCurrentPosition(function(result) {
     if(this.getStatus() == BMAP_STATUS_SUCCESS) {
     map.panTo(new BMap.Point(result.point.lng, result.point.lat));
     }
     });*/

    //第1步：设置地图中心点，默认包头
    var point = new BMap.Point(109.83, 40.65);
    map.centerAndZoom(point, 11);
    //第2步：初始化地图,设置中心点坐标和地图级别。

    //第3步：启用滚轮放大缩小
    map.enableScrollWheelZoom(true);
    //第4步：向地图中添加缩放控件

    //第6步：向地图中添加比例尺控件
    /*var ctrlSca = new window.BMap.ScaleControl({
     anchor: BMAP_ANCHOR_TOP_LEFT
     });
     map.addControl(ctrlSca);*/

    //第7步：向地图添加地图类型
    map.addControl(new BMap.MapTypeControl({
        mapTypes: [
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ],
        anchor: BMAP_ANCHOR_TOP_LEFT,
        offset: new BMap.Size(5, 60), //偏离值
    }));

    //第8步：添加当前定位
    // 添加定位控件
    var geolocationControl = new BMap.GeolocationControl({
        anchor: BMAP_ANCHOR_BOTTOM_RIGHT,
        offset: new BMap.Size(5, 400), //偏离值
    });
    geolocationControl.addEventListener("locationSuccess", function(e) {
        // 定位成功事件
        var address = '';
        address += e.addressComponent.province;
        address += e.addressComponent.city;
        address += e.addressComponent.district;
        address += e.addressComponent.street;
        address += e.addressComponent.streetNumber;
        //alert("当前定位地址为：" + address);
    });
    geolocationControl.addEventListener("locationError", function(e) {
        // 定位失败事件
        //alert(e.message);
    });
    map.addControl(geolocationControl);

    var overlays = [];

    var styleOptions = {
        strokeColor: "rgba(40, 175, 255, 1)", //边线颜色。
        fillColor: "rgba(40, 175, 255, 1)", //填充颜色。当参数为空时，圆形将没有填充效果。
        strokeWeight: 3, //边线的宽度，以像素为单位。
        strokeOpacity: 1, //边线透明度，取值范围0 - 1。
        fillOpacity: 0.5, //填充的透明度，取值范围0 - 1。
        strokeStyle: 'solid' //边线的样式，solid或dashed。
    }
    listRoute();
}

//异步调用百度js
function map_load() {
    var load = document.createElement("script");
    load.src = "https://api.map.baidu.com/api?v=2.0&ak=DDd05fcba9fea5b83bf648515e04fc4c&callback=map_init";
    document.body.appendChild(load);
}

//添加线
function addPolyLine(a, b) {
    var newPolyline = new BMap.Polyline(a, b);
    map.addOverlay(newPolyline);
}