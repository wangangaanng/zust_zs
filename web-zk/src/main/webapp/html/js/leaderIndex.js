var colorList = ['#43cfa9', '#43a9cf','#3e8be2','#2b63f0','#5144fd',"#aa4ef8",'#f84ebe','#ff635e','#ff8746','#ffb22d','#fed755','#92cf43','#2fa4f5',"#7089fa"];
var colorLine =["#ff7800","#009cff","#cc2639"];
var pieChart = ["pieChart1","pieChart2","pieChart3","pieChart4"];
var nullTip = "<div class = 'null-tip'><img src = 'img/index_nulltip.png' style='margin-top: 60px'/><p style='margin-top: 10px'>查询结果暂为空</p></div>";
var chartDom = $(".pie-list .pie-wrap");
var pieOption = "";
var dataPie = [];
var type = 0;
var projectId="";

var nullThisTip='<a class="wrap-list">暂无</a>';

var deptId="";//科室编号
var reginCode="";//地区编号
$(document).ready(function(){
    getJob(3);//待举办招聘会
    getJob(4);//待举办宣讲会
    getQy();//待审核企业
    topChart();
    initBar();//柱状图

});

function getJob(zwlx) {
    var params={
        "zwlx":zwlx
    }
    window.parent.ajax("web/zustjy/common/todoJob",params,function(data){
        if(data.backCode == 0) {
            var str='';
            if((data.bean)&&(data.bean.length>0)){
                $.each(data.bean,function (k,p) {
                    str += '<div class="article skt">';
                    str += '<div class="bt col2">'+p.zwbt+'</div>';
                    str += '<div class="fbsj col1">举办日期：'+(p.ksrq).substring(0,10)+'</div>';
                    str += '</div>';
                })
            }else{
                str="<div class = 'null-tip'><img src = 'img/index_nulltip.png' style='margin-top: 0px'/><p style='margin-top: 10px'>查询结果暂为空</p></div>";
            }

            if(zwlx==3){
                $("#xxzph").append(str);
            }
            if(zwlx==4){
                $("#xxxjh").append(str);
            }
        }
    })
}
function getQy() {
    var params={

    }
    window.parent.ajax("web/zustjy/common/todoCompany",params,function(data){
        if(data.backCode == 0) {
            var str='';
            if((data.bean)&&(data.bean.length>0)){
                $.each(data.bean,function (k,p) {
                    str += '<div class="article skt">';
                    str += '<div class="bt col2">'+p.qymc+'</div>';
                    str += '<div class="fbsj col1">'+(p.createtime).substring(0,10)+'</div>';
                    str += '</div>';
                })
            }else{
                str='<p style="text-align: center;margin: 20px auto;">暂无数据</p>'
            }

            $("#xxqy").append(str);

        }
    })
}

//饼图
function topChart(isAll) {
    if(!isAll){isAll=false}
    var urlArr = ["web/zustjy/common/companyPieChart","web/zustjy/common/jobPieChart","web/zustjy/common/meetingPieChart","web/zustjy/common/meetingPieChart"];
    var paramsArr=[{},{},{"zwlx":3},{"zwlx":4}]
    $.each(chartDom,function(k,p){
        pieChart[k] = echarts.init(p);
        pieChart[k].showLoading({ color: '#5caefd'});
        // $.post("../ajax/executeAPI.do", params, function (data) {
        if(urlArr[k]){
            window.parent.ajax(urlArr[k],paramsArr[k],function(data){
                pieChart[k].hideLoading();
                if(data.backCode == 0) {
                    if(k==0){
                        colorList = ['#43cfa9', '#43a9cf','#3e8be2','#2b63f0','#5144fd',"#aa4ef8",'#f84ebe','#ff635e','#ff8746','#ffb22d','#fed755','#92cf43','#2fa4f5',"#7089fa"];
                        var str1 = '<tr><td>企业数</td><td><span>'+data.bean.qys+'</span></td><td>涉及行业</td><td><span>'+data.bean.sjhy+'</span><label>个</label></td></tr>';
                        str1 += '<tr><td>审核数</td><td><span>'+data.bean.shs+'</span></td><td>涉及类型</td><td><span>'+data.bean.sjlx+'</span><label>个</label></td></tr>';
                        $("#qytj").html(str1);
                    }
                    if(k==1){
                        colorList = ["#009cff","#ffb22d",'#43cfa9','#aa4ef8','#ff635e'];
                        var str2 = '<tr><td>职位数</td><td><span>'+data.bean.zws+'</span></td><td>涉及行业</td><td><span>'+data.bean.sjhy+'</span><label>个</label></td></tr>';
                        str2 += '<tr><td>关注数</td><td><span>'+data.bean.gzs+'</span></td><td>报名数</td><td><span>'+data.bean.bms+'</span></td></tr>';
                        $("#zwtj").html(str2);
                    }
                    if(k==2){
                        colorList = ["#92cf43","#f84ebe","#ff635e","#ff8746","#ffb22d"];
                        var str3 = '<tr><td>招聘会</td><td><span>'+data.bean.total+'</span></td><td>未举办</td><td><span>'+data.bean.wjb+'</span><label>个</label></td></tr>';
                        str3 += '<tr><td>企业报名数</td><td><span>'+data.bean.qys+'</span></td><td>学生报名数</td><td><span>'+data.bean.xss+'</span></td></tr>';
                        $("#zphtj").html(str3);
                    }
                    if(k==3){
                        colorList = ["#009cff","#ffb22d","#f84ebe","#ff8746","#ffb22d"];
                        var str4 = '<tr><td>宣讲会</td><td><span>'+data.bean.total+'</span></td><td>未举办</td><td><span>'+data.bean.wjb+'</span><label>个</label></td></tr>';
                        str4 += '<tr><td>企业报名数</td><td><span>'+data.bean.qys+'</span></td><td>学生报名数</td><td><span>'+data.bean.xss+'</span></td></tr>';
                        $("#xjhtj").html(str4);
                    }
                    if((data.bean.pieData)&&(data.bean.pieData.length>0)){
                        dataPie = data.bean.pieData;
                    }else{
                        $(chartDom[k]).removeAttr("_echarts_instance_");
                        $(chartDom[k]).html(nullTip);
                    }
                }
                pieOption = {
                    backgroundColor: 'inhert',
                    tooltip: {
                        trigger: 'item',
                        formatter: "{b} : {c} ({d}%)" //"{b} : {c} ({d}%)"
                    },
                    series: [
                        {
                            name: '项目政策总览',
                            type: 'pie',
                            radius: '60%',
                            radius: ['30%', '60%'],
                            center: ['50%', '52%'],
                            color: colorList,
                            data: dataPie,
                            label: {
                                normal: {
                                    /* textStyle: {
                                     color: '#7c7c7c'
                                     },*/
                                    formatter: function (param) {
                                        return +Math.round(param.percent) + '%';
                                    }

                                }
                            },
                            labelLine: {
                                normal: {
                                    /* lineStyle: {
                                     color: '#7c7c7c'
                                     },*/
                                    smooth: false,
                                    length: 13,
                                    length2: 18
                                }
                            },
                            itemStyle: {
                                normal: {
                                    /*  color: '#cae6ea',*/
                                    shadowBlur: 20,
                                    shadowColor: 'rgba(0, 0, 0, 0.1)',
                                    label:{ show: true,  formatter: '{b} \n ({d}%)', textStyle:{}}
                                }
                            },
                            animationType: 'scale',
                            animationEasing: 'elasticOut',
                            animationDelay: function (idx) {
                                return Math.random() * 200;
                            }
                        }
                    ],
                };
                pieChart[k].setOption(pieOption);
                window.onresize = pieChart[k].resize;
            })
        }

    });


}

function initBar(isAll) {
    if(!isAll){isAll=false}
    var params = {

    };
    var barChart = echarts.init(document.getElementById('indexBar'));
    var legendData = [];
    var seriesData = [];
    barChart.showLoading({ color: '#5caefd'});
    window.parent.ajax('web/zustjy/common/jobBarChart',params,function(data){
        barChart.hideLoading();
        if(data.backCode == 0) {

            var nameArr = ["职位数","招聘会数","宣讲会数"];
            legendData = nameArr;
            var lineData = [data.bean.series[0].data,data.bean.series[1].data,data.bean.series[2].data];
            $.each(nameArr, function (k,p) {
                seriesData.push( {
                    name: p,
                    type: 'bar',
                    itemStyle: {
                        normal: {
                            color: colorLine[k],
                            label: {
                                show: true,
                                textStyle: {
                                    color:  colorLine[k],
                                    fontSize:13,
                                },
                                position: 'top',
                            },
                        },

                    },
                    data: lineData[k],
                });
            });
            option5 = {
                title: {
                    text: '',
                    subtext: '',
                    sublink: ''
                },
                grid: {
                    y:46,
                    y2: 25,
                    x: 60,
                    x2:20
                },
                //animationDuration: 4000,
                tooltip: {
                    trigger: 'axis',
                    backgroundColor: 'rgba(255,255,255,0.8)',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: function (params) {
                        var res = '<div style="border:1px solid #5caefd;padding:10px;">';
                        res += '<strong style="color:#3e3a39;">' + params[0].name + '</strong>';
                        var k = "";
                        for (var i = 0, l = params.length; i < l; i++) {
                            res += '<br/><span style="color:' + colorLine[i] + '">' + params[i].seriesName + ' &nbsp;&nbsp;&nbsp;&nbsp;';
                            res +='<span style="color:#000;font-size:12px;"> ' + params[i].value + '</span>';
                            res +='</span>';
                        }
                        res += '</div>';
                        return res;
                    }
                },
                legend: {
                    x: 'left',
                    left: 50,
                    show: true,
                    data: legendData,
                    top: 5,
                    textStyle:{
                        fontSize:13
                    }
                },
                toolbox: {
                    feature: {
                        mark: {show: true},
                        magicType: {show: true, type: ['bar', 'line']},
                        dataZoom: {
                            show: false,
                            title: {
                                dataZoom: '区域缩放',
                                dataZoomReset: '区域缩放后退'
                            }
                        },
                        restore: {show: true},
                        saveAsImage: {show: false}
                    }
                },
                calculable: false,
                xAxis: [
                    {
                        type: 'category',
                        data: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
                        axisTick: {
                            inside: true
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        axisTick: {
                            inside: true
                        },

                    }
                ],
                series: seriesData,
            };
            barChart.setOption(option5);
            window.onresize = barChart.resize;
        }
    })
}


