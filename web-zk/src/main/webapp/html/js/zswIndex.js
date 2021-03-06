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

var limit;

$(document).ready(function(){
    change();
    initPie();  //饼图
    initBar();  //柱状图

});

function change() {
    limit = $('#selection').val();
    initBar();
}

//饼图
function initPie(isAll) {
    if(!isAll){isAll=false}
    var urlArr = ["web/zustzs/common/lntjPieChart","web/zustzs/common/zsjhPieChart","web/zustzs/common/ksbmPieChart","web/zustzs/common/cjcxPieChart"];
    var paramsArr=[{},{},{},{}];
    $.each(chartDom,function(k,p){
        pieChart[k] = echarts.init(p);
        pieChart[k].showLoading({ color: '#5caefd'});
        // $.post("../ajax/executeAPI.do", params, function (data) {
        if(urlArr[k]){
            window.parent.ajax(urlArr[k],paramsArr[k],function(data){
                pieChart[k].hideLoading();
                if(data.backCode == 0 && data.bean) {
                    if(k==0){
                        colorList = ['#43cfa9', '#43a9cf','#3e8be2','#2b63f0','#5144fd',"#aa4ef8",'#f84ebe','#ff635e','#ff8746','#ffb22d','#fed755','#92cf43','#2fa4f5',"#7089fa"];
                        var str1 = '<tr><td>总人数</td><td><span>'+data.bean.zrs+'</span></td><td>类别数</td><td><span>'+data.bean.lb+'</span></td></tr>';
                        $("#qytj").html(str1);
                    }
                    if(k==1){
                        colorList = ["#009cff","#ffb22d",'#43cfa9','#aa4ef8','#ff635e'];
                        var str2 = '<tr><td>总人数</td><td><span>'+data.bean.zrs+'</span></td><td>类别数</td><td><span>'+data.bean.lb+'</span></td></tr>';
                        $("#zwtj").html(str2);
                    }
                    if(k==2){
                        colorList = ["#92cf43","#f84ebe","#ff635e","#ff8746","#ffb22d"];
                        var str3 = '<tr><td>总人数</td><td><span>'+data.bean.zrs+'</span></td><td>类别数</td><td><span>'+data.bean.lb+'</span></td></tr>';
                        $("#zphtj").html(str3);
                    }
                    if(k==3){
                        colorList = ["#009cff","#ffb22d","#f84ebe","#ff8746","#ffb22d"];
                        var str4 = '<tr><td>总人数</td><td><span>'+data.bean.zrs+'</span></td><td>类别数</td><td><span>'+data.bean.lb+'</span></td></tr>';
                        $("#xjhtj").html(str4);
                    }
                    if((data.bean.pieData)&&(data.bean.pieData.length>0)){
                        dataPie = data.bean.pieData;
                    }else{
                        $(chartDom[k]).removeAttr("_echarts_instance_");
                        $(chartDom[k]).html(nullTip);
                    }
                } else{
                    $(chartDom[k]).removeAttr("_echarts_instance_");
                    $(chartDom[k]).html(nullTip);
                }
                pieOption = {
                    backgroundColor: 'inhert',
                    tooltip: {
                        trigger: 'item',
                        formatter: "{b}: {c}人 ({d}%)" //"{b} : {c} ({d}%)"
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
        limit: limit
    };
    var barChart = echarts.init(document.getElementById('indexBar'));
    var legendData = [];
    var seriesData = [];
    var zyList=[];
    barChart.showLoading({ color: '#5caefd'});
    window.parent.ajax('web/zustzs/common/lqxsBarChart',params,function(data){
        barChart.hideLoading();
        if(data.backCode == 0) {
            zyList=data.bean.zyList;
            var nameArr = ["录取人数"];
            legendData = nameArr;
            var lineData = [data.bean.series[0].data];
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
                    x2:20,
                    bottom: "20%"
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
                        data: zyList,
                        axisTick: {
                            inside: true
                        },
                        axisLabel: {
                            rotate: 20,
                            interval: 0
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
            barChart.setOption(option5,true);
            window.onresize = barChart.resize;
        }
    })
}


