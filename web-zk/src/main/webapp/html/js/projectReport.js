/*
 * @Author: zhangqiaojun 
 * @Date: 2019-07-29 9:36:44 
 * @Last Modified by: zhangqiaojun
 * @Last Modified time: 2019-08-17 14:50:32
 */

// 年份
var yearArray=['全部','2019','2018']
var currentYear="";//年份值
var currentYearIndex=0;//年份选项
var yearLength="";
yearLength=yearArray.length;
currentYear=yearArray[currentYearIndex];
// yearLength=$(".yeartab").find("a").length;
// currentYear=$(".yeartab").find("a").eq(currentYearIndex).text();
//图形
var graphArray=['pie','bar'];//饼图、折线图
var currentGraph="";//图形值
var currentGraphIndex=0;//图形选项
currentGraph=graphArray[currentGraphIndex];
var intervalGraph="6000";//图形间隔时间
var intervalYear=intervalGraph*(graphArray.length);//年份间隔时间

var graphInterval="";
var yearInterval="";
var eventInterval="";

//图形2
var graphArray2=['pie','bar','line'];//饼图、折线图
var currentGraph2="";//图形值
var currentGraphIndex2=0;//图形选项
currentGraph2=graphArray2[currentGraphIndex2];
var intervalGraph2="6000";//图形间隔时间
// var intervalYear=intervalGraph*(graphArray.length);//年份间隔时间

//图表数据
//基本信息
var dataBasic=[{value:56, name:'已完成'},{value:44, name:'未完成'}]
//生命周期
var dataLife_xmqq=[{value:56, name:'已完成'},{value:44, name:'未完成'}]
var dataLife_xmsg=[{value:56, name:'已完成'},{value:44, name:'未完成'}]
var dataLife_jgys=[]
var dataLife_jxpj=[]

//流程完整模式
var dataLc_lcxx=[10, 12,8,12,18,25,22,8]
var dataLc_lcjd=[20, 20,20,25,20,20,38,6]

//项目投资
var dataXmtz_sjztz=[{value:1548, name:'单位投资'},{value:225, name:'社会资本'},{value:335, name:'金融信贷'},{value:200, name:'政府投资'},{value:510, name:'其他'}];
var dataXmtz_sjzftz=[{value:0, name:'中央投资'},{value:100, name:'省级投资'},{value:50, name:'市级投资'},{value:50, name:'旗县政府'}];
var dataXmtz_jhztz=[{value:1548, name:'单位投资'},{value:225, name:'社会资本'},{value:335, name:'金融信贷'},{value:200, name:'政府投资'},{value:310, name:'其他'}];
var dataXmtz_jhzftz=[{value:0, name:'中央投资'},{value:100, name:'省级投资'},{value:50, name:'市级投资'},{value:50, name:'旗县政府'}];
var dataXmtz_sjztzR=dataXmtz_sjztz.concat().reverse();
var dataXmtz_sjzftzR=dataXmtz_sjzftz.concat().reverse();
var dataXmtz_jhztzR=dataXmtz_jhztz.concat().reverse();
var dataXmtz_jhzftzR=dataXmtz_jhzftz.concat().reverse();


// 政府投资进度
var dataTzjd_zhongy=[];//投资进度-中央投资
var dataTzjd_shenj=[{value:22, name:'一般公告预算'},{value:28, name:'政府资金'},{value:22, name:'国有资本'},{value:12, name:'社会保险'},{value:16, name:'其他'}];//投资进度-省级投资
var dataTzjd_shij=[];//投资进度-市级投资
var dataTzjd_qix=[{value:22, name:'一般公告预算'},{value:28, name:'政府资金'},{value:22, name:'国有资本'},{value:12, name:'社会保险'},{value:16, name:'其他'}];//投资进度-旗县政府

//项目进度
var dataGautt_y=['任务一', '任务二', '任务三', '任务四', '任务五', '任务六', '任务七']
var dataGautt_jhks=[new Date("2015/09/2"),new Date("2015/09/15"),new Date("2015/09/15"),new Date("2015/10/03"),new Date("2015/10/04"),new Date("2015/10/05"),new Date("2015/10/06")]
var dataGautt_jhsj=[new Date("2015/09/12"),new Date("2015/09/20"),new Date("2015/09/25"),new Date("2015/10/05"),new Date("2015/10/07"),new Date("2015/10/09"),new Date("2015/10/12")]
var dataGautt_sjks=[new Date("2015/09/2"),new Date("2015/09/15"),new Date("2015/09/15"),new Date("2015/10/03"),new Date("2015/10/04"),new Date("2015/10/05"),new Date("2015/10/06")]
var dataGautt_sjsj=[new Date("2015/09/12"),new Date("2015/09/20"),new Date("2015/09/25"),new Date("2015/10/05"),new Date("2015/10/07"),new Date("2015/10/09"),new Date("2015/10/12")]

//招标采购
var dataZbcg=[{value:60, name:'金额招标'},{value:60, name:'费率招标'},{value:40, name:'入围招标'},{value:30, name:'自行建设'}]
var dataZbcg_total=190
var dataHtba=[{value:70, name:'工程类'},{value:70, name:'货物类'},{value:60, name:'服务类'},]
var dataHtba_total=200
var dataJgys=[{value:70, name:'工程类'},{value:70, name:'货物类'},{value:60, name:'服务类'},]
var dataJgys_total=200

$(document).ready(function () {
    $(window).keydown(function (event) {
        console.log(event.keyCode)
        if (event.keyCode == 27) {
            $('body').removeClass('full-screen');
        }
    });
    $('.content-a_left_c .feature a').click(function(){//项目投资-图像切换
        $(this).addClass('active').siblings().removeClass('active');
        currentGraphIndex=$(this).index();
        currentGraph=graphArray[currentGraphIndex];
        eventTask();
    })
    $('.content-a_left_c .yeartab a').click(function(){//项目投资-年份切换
        $(this).addClass('active').siblings().removeClass('active');
        currentYearIndex=$(this).index();
        currentYear=yearArray[currentYearIndex];
        // window.clearInterval(graphInterval);
        // window.clearInterval(yearInterval);
        // window.clearInterval(eventInterval);
        // currentYearIndex=0;
        // currentYear=$(".yeartab").find("a").eq(currentYearIndex).text();
        // currentGraphIndex=0;
        // currentGraph=graphArray[currentGraphIndex];
        // $('.content-a_left_c .yeartab a').eq(currentYearIndex).addClass('active').siblings().removeClass('active');
        // $('.content-a_left_c .feature a').eq(currentGraphIndex).addClass('active').siblings().removeClass('active');
        eventTask();
        // graphInterval=setInterval(function(){//图形选项卡
        //     graphTask()
        // },intervalGraph);//3000
        // yearInterval=setInterval(function(){//年份选项卡
        //     yearTask()
        // },intervalYear);//3000*2
        // eventInterval=setInterval(function(){//任务计时器
        //     eventTask()
        // },intervalGraph);//3000
    })
    $('.content-a_left_d .feature a').click(function(){//政府投资进度-图像切换
        $(this).addClass('active').siblings().removeClass('active');
        currentGraphIndex2=$(this).index();
        currentGraph2=graphArray2[currentGraphIndex2];
        eventTask2();

    })

    initPieProgress('basic-pie',dataBasic);
    if(dataLife_xmqq.length>0){
        initPieProgress('life-pie1',dataLife_xmqq);
    }else{
        $("#life-pie1").parent().addClass('life-body-null').html('<div>未开始</div>')
    }
    if(dataLife_xmsg.length>0){
        initPieProgress('life-pie2',dataLife_xmsg);
    }else{
        $("#life-pie2").parent().addClass('life-body-null').html('<div>未开始</div>')
    }
    if(dataLife_jgys.length>0){
        initPieProgress('life-pie3',dataLife_jgys);
    }else{
        $("#life-pie3").parent().addClass('life-body-null').html('<div>未开始</div>')
    }
    if(dataLife_jxpj.length>0){
        initPieProgress('life-pie4',dataLife_jxpj);
    }else{
        $("#life-pie4").parent().addClass('life-body-null').html('<div>未开始</div>')
    }

    initBar();

    // 项目投资
    eventTask();
    // graphInterval=setInterval(function(){//图形选项卡
    //     graphTask()
    // },intervalGraph);//3000
    // yearInterval=setInterval(function(){//年份选项卡
    //     yearTask()
    // },intervalYear);//3000*2
    // eventInterval=setInterval(function(){//任务计时器
    //     eventTask()
    // },intervalGraph);//3000


    // 政府投资进度
    eventTask2();
    // setInterval(function(){//图形选项卡
    //     graphTask2()
    // },intervalGraph);//3000
    // setInterval(function(){//任务计时器
    //     eventTask2()
    // },intervalGraph);//3000

    //招标采购
    biddingPie('bidding-pie1',dataZbcg,dataZbcg_total,['#ff635e','#ffb22d','#92cf43','#009cff'],['金额招标', '费率招标','入围招标','自行建设']);
    //合同备案
    biddingPie('bidding-pie2',dataHtba,dataHtba_total,['#ffb22d','#92cf43','#009cff'],['工程类', '货物类','服务类']);
    //竣工验收
    biddingPie('bidding-pie3',dataJgys,dataJgys_total,['#ffb22d','#92cf43','#009cff'],['工程类', '货物类','服务类']);
    //项目进度
    progressGautt();
})
function stopTiming() {
    window.clearInterval(graphInterval);
    window.clearInterval(yearInterval);
    window.clearInterval(eventInterval);
    currentYearIndex=0;
    currentYear=$(".yeartab").find("a").eq(currentYearIndex).text();
    currentGraphIndex=0;
    currentGraph=graphArray[currentGraphIndex];
}
function yearTask(){//年份切换
    if(currentYearIndex+1<yearLength){
        currentYearIndex+=1;
        currentYear=yearArray[currentYearIndex];
    }else if(currentYearIndex+1==yearLength){
        currentYearIndex=0;
        currentYear=yearArray[currentYearIndex];
    }
    $('.content-a_left_c .yeartab a').eq(currentYearIndex).addClass('active').siblings().removeClass('active');
}
function graphTask(){//图形切换
    if(currentGraphIndex+1<graphArray.length){
        currentGraphIndex+=1;
        currentGraph=graphArray[currentGraphIndex];
    }else if(currentGraphIndex+1==graphArray.length){
        currentGraphIndex=0;
        currentGraph=graphArray[currentGraphIndex];
    }
    $('.content-a_left_c .feature a').eq(currentGraphIndex).addClass('active').siblings().removeClass('active');
    // $(this).addClass('active').siblings().removeClass('active');
}
function graphTask2(){//图形切换
    if(currentGraphIndex2+1<graphArray2.length){
        currentGraphIndex2+=1;
        currentGraph2=graphArray2[currentGraphIndex2];
    }else if(currentGraphIndex2+1==graphArray2.length){
        currentGraphIndex2=0;
        currentGraph2=graphArray2[currentGraphIndex2];
    }
    $('.content-a_left_d .feature a').eq(currentGraphIndex2).addClass('active').siblings().removeClass('active');
    // $(this).addClass('active').siblings().removeClass('active');
}
function eventTask(){
    // console.log('图形:'+currentGraph+'年份:'+currentYear);
    if(currentGraph=='pie'){
        // $("#graphChart1").show();
        // $("#graphChart2").hide();
        investmentPie('investment-pie1','总投资',dataXmtz_sjztzR,currentYear);
        investmentPie('investment-pie2','其中政府',dataXmtz_sjzftzR,currentYear);
        investmentPie('investment-pie3','总投资',dataXmtz_jhztzR,currentYear);
        investmentPie('investment-pie4','其中政府',dataXmtz_jhzftzR,currentYear);
    }else if(currentGraph=='bar'){
        // $("#graphChart1").hide();
        // $("#graphChart2").show();
        investmentBar('investment-pie1','总投资',dataXmtz_sjztz,currentYear);
        investmentBar('investment-pie2','其中政府',dataXmtz_sjzftz,currentYear);
        investmentBar('investment-pie3','总投资',dataXmtz_jhztz,currentYear);
        investmentBar('investment-pie4','其中政府',dataXmtz_jhzftz,currentYear);
    }

}
function eventTask2(){
    // console.log('图形:'+currentGraph);
    if(currentGraph2=='pie'){
        investmentPie2('investment-pie5','中央投资',dataTzjd_zhongy);
        investmentPie2('investment-pie6','省级投资',dataTzjd_shenj);
        investmentPie2('investment-pie7','市级投资',dataTzjd_shij);
        investmentPie2('investment-pie8','旗县政府',dataTzjd_qix);
    }else if(currentGraph2=='bar'){
        investmentBar2('investment-pie5','中央投资',dataTzjd_zhongy,'bar');
        investmentBar2('investment-pie6','省级投资',dataTzjd_shenj,'bar');
        investmentBar2('investment-pie7','市级投资',dataTzjd_shij,'bar');
        investmentBar2('investment-pie8','旗县政府',dataTzjd_qix,'bar');
    }else if(currentGraph2=='line'){
        investmentBar2('investment-pie5','中央投资',dataTzjd_zhongy,'line');
        investmentBar2('investment-pie6','省级投资',dataTzjd_shenj,'line');
        investmentBar2('investment-pie7','市级投资',dataTzjd_shij,'line');
        investmentBar2('investment-pie8','旗县政府',dataTzjd_qix,'line');
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
                radius: ['55%', '85%'],
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
}
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
                name: '流程选项',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: false,
                        position: 'insideRight'
                    }
                },
                data: dataLc_lcxx,
            },
            {
                name: '流程进度',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: false,
                        position: 'insideRight'
                    }
                },
                data: dataLc_lcjd
            },
        ]
    };
    progressEchart.setOption(option);
}

//项目投资1
function investmentPie(id,title,data){
    var chart = echarts.init(document.getElementById(id));
    chart.clear();
    var option = {
        title : {
            show:true,
            text: title,
            textVerticalAlign:'bottom',
            bottom:0,
            x:'center',
            textStyle:{
                color:'#636363',
                fontSize:'12'
            }
        },
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)",
            position: 'right',
        },
        series : [
            {
                name: '',
                type: 'pie',
                radius: ['26%', '50%'],
                center: ['50%', '50%'],
                color:['#5144fd','#ff8746','#f7b731','#20bf6b','#009cff'],
                data:data,
                labelLine: {
                    normal: {
                        // length: 5,
                        // length2: 40,
                    }

                },
                label: {
                    normal: {
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
}
//项目投资2
// function investmentBar(id,title,data){
//     var datax=[];
//     var datay=[];
//     for(var i=0;i<data.length;i++){
//         datax.push(data[i].name)
//         datay.push(data[i].value)
//     }
//     var chart = echarts.init(document.getElementById(id));
//     chart.clear();
//     var option = {
//         title : {
//             show:true,
//             text: title,
//             textVerticalAlign:'bottom',
//             bottom:0,
//             x:'center',
//             textStyle:{
//                 color:'#636363',
//                 fontSize:'12'
//             }
//         },
//         color: ['#009cff','#20bf6b','#f7b731','#ff8746','#5144fd'],
//        tooltip : {
//             trigger: 'axis',
//             formatter: "{b} : {c}",
//             axisPointer : {            // 坐标轴指示器，坐标轴触发有效
//                 type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
//             },
//             position: function (point, params, dom, rect, size) {
//                 // 固定在顶部
//                 return [point[0], '10%'];
//             }
//         },
//         grid: {
//             left: '5',
//             right: '0%',
//             bottom: '65',
//             top:'20',
//         },
//         xAxis:  {
//             type: 'category',
//             axisLabel:{
//                 interval:0,
//                 rotate:-30,
//                 margin: 20,
//                 textStyle:{
//                     align: 'center',
//                     color: function (value, index) {
//                         let colorList = ['#009cff','#20bf6b','#f7b731','#ff8746','#5144fd']
//                         return colorList[index];

//                     }
//                 },

//             },
//             data : datax,
//         },
//         yAxis: {
//             show:false,
//             // type: 'value',
//             // splitLine:{
//             //     lineStyle:{
//             //         color:'#eaeef1'
//             //     }
//             // },
//             // axisLabel:{
//             //     show:false,
//             // }
//         },
//         series : [
//             {
//                 type:'bar',
//                 itemStyle: {
//                     normal: {
//                         color: ['#009cff','#20bf6b','#f7b731','#ff8746','#5144fd'],
//                         color: function(params) {
//                             let colorList = ['#009cff','#20bf6b','#f7b731','#ff8746','#5144fd']
//                             return colorList[params.dataIndex];
//                         },
//                         label: {
//                             show: true, //开启显示
//                             position: 'top', //在上方显示
//                         }
//                     }
//                 },
//                 data:datay
//             }
//         ]
//     };
//     chart.setOption(option)
// }
function investmentBar(id,title,data){
    var datax=[];
    var datay=[];
    var series=[];
    for(var i=0;i<data.length;i++){
        datax.push(data[i].name)
        datay.push(data[i].value)
        series.push({
            name:datax.slice(i,i+1),
            type: 'bar',
            barWidth:80,
            stack: '总量',
            data:datay.slice(i,i+1),
        })
    }
    // console.log(JSON.stringify(series));
    var chart = echarts.init(document.getElementById(id));
    chart.clear();
    var option = {
        title : {
            show:true,
            text: title,
            textVerticalAlign:'bottom',
            bottom:0,
            x:'center',
            textStyle:{
                color:'#636363',
                fontSize:'12'
            }
        },
        color: ['#009cff','#20bf6b','#f7b731','#ff8746','#5144fd'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },
            position: function (point, params, dom, rect, size) {
                // 固定在顶部
                return [point[0], '10%'];
            }
        },
        grid: {
            left: '5',
            right: '0%',
            bottom: '25',
            top:'20',
        },
        xAxis:  {
            type: 'category',
            show:false,
            data:[title]
        },
        yAxis: {
            show:false,
        },
        series:series,
    };
    chart.setOption(option)
}
//政府投资进度
function investmentPie2(id,title,dt){
    var data=dt;
    var color=['#5144fd','#ff8746','#f7b731','#20bf6b','#009cff'];
    var tooltip={
        trigger: 'item',
        formatter: "{b} : {c} ({d}%)",
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
            itemGap:45,//主副标题之间的间距
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
                radius: ['26%', '50%'],
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
}
//政府投资进度2
function investmentBar2(id,title,dt,type){
    // var xAxisText=[];
    // if(dt.length>0){
    //     for(var i=0;i++;i<dt.length){
    //         xAxisText.push(dt[i].name)
    //     }
    // }else{
    //     xAxisText=['一般公告预算','政府资金','国有资本','社会保险','其他']
    // }
    // console.log((dt.length>0));
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
        formatter: "{b} : {c}",
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
            data : ['一般公告预算','政府资金','国有资本','社会保险','其他'],
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
}

//招标采购、合同备案、竣工验收
function biddingPie(id,data,total,colorlist,legendlist){
    var index = 0;//默认选中高亮模块索引
    var chart = echarts.init(document.getElementById(id));
    var  option = {
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)",
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
                        formatter: [
                            '{bt|{b}}',
                            '({num|{c}/'+total+'万})'
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
                data:data
            }

    };
    chart.setOption(option);
    chart.dispatchAction({type: 'highlight',seriesIndex: 0,dataIndex: 0});//设置默认选中高亮部分
    chart.on('mouseover',function(e){
        if(e.dataIndex != index){
            chart.dispatchAction({type: 'downplay', seriesIndex: 0, dataIndex: index  });
        }
    });
    chart.on('mouseout',function(e){
        index = e.dataIndex;
        chart.dispatchAction({type: 'highlight',seriesIndex: 0,dataIndex: e.dataIndex});
    });
}
//项目进度
function progressGautt(){
    var chart = echarts.init(document.getElementById('progress-gautt'));
    var option = {
        title: {
            show:false,
        },
        legend: {
            show:false,
        },
        grid: {
            containLabel: true,
            left: 20
        },
        xAxis: {
            type: 'time',
            position: "top",
            axisTick: {
                inside: true
            },
            axisLabel: {
                color:'#282828',
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
                color:'#363f45',
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
                var date0 = params[0].data;
                var date1 = params[1].data;
                var date2 = params[2].data;
                var date3 = params[3].data;
                date0 = date0.getFullYear() + "-" + (date0.getMonth() + 1) + "-" + date0.getDate();
                date1 = date1.getFullYear() + "-" + (date1.getMonth() + 1) + "-" + date1.getDate();
                date2 = date2.getFullYear() + "-" + (date2.getMonth() + 1) + "-" + date2.getDate();
                date3 = date3.getFullYear() + "-" + (date3.getMonth() + 1) + "-" + date3.getDate();
                res += "<div><span style='color: #fed755;'>计划 :&nbsp;&nbsp;&nbsp;</span><span style='color:#fff;'>" + date0 + "~" + date1 + "</span></br>"
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
    chart.setOption(option);
}
