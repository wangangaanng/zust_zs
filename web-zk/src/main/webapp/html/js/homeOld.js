var thisPie1 = 1;
var thisPie2 = 1;
var thisPie3 = 1;
var thisPie4 = 1;
var thisBar = 1;
$(document).ready(function(){
    revertFun();
});
function revertFun()
{   policyTotal();
    rzmsTotal();
    totalFinance();
    totalPolicyFinance();
    myProcessReport();
    setTimeout(revertFun,5000);
}
var nullTip = "<div class = 'null-tip'><img src = 'img/index_nulltip.png'/><p>暂时还没有信息</p></div>";
var  labelLinePie = {
    normal: {
        lineStyle: {
            color: '#7c7c7c'
        },
        smooth: false,
        length: 13,
        length2: 18
    }
};
var pirColorList = ['#c6e2e6', '#d7ebd2','#9eb4b5','#5d798a','#1cb1c7','#c9e5e9','#8aba4f','rgb(157,147,128)','#ec9c5b',"#54a690","#94002D"];
//项目政策总览
function policyTotal() {
    var params = {
        timestamp : new Date().getTime()
    };
    var dataPie = [];
    var dataLegend = [];
    var pieChart = echarts.init(document.getElementById('taskcartogram'));
    if(thisPie1 == 1){
        pieChart.showLoading({ color: '#9ec0c7'});
    }
    window.parent.ajax("adminIndexApi/policyTotal",params,function(data){
        pieChart.hideLoading();
        if(data.backCode == 0) {
            if(data.bean.toString() != thisPie1.toString()){
                thisPie1 = data.bean;
            $.each(data.bean, function (k, p) {
                var obj = new Object();
                if (p.value == "" || p.value == null) {
                    p.value = "0"
                }
                obj.value = p.value;
                obj.name = p.name + ": " + p.value;
                dataPie.push(obj);
                dataLegend.push(obj.name);
            });

            option = {
                backgroundColor: '#FFF',
                tooltip: {
                    trigger: 'item',
                    formatter: "{b} ({d}%)" //"{b} : {c} ({d}%)"
                },
                series: [
                    {
                        name: '项目政策总览',
                        type: 'pie',
                        radius: '50%',
                        center: ['50%', '42%'],
                        roseType: 'radius',
                        color: pirColorList,
                        data: dataPie.sort(function (a, b) {
                            return a.value - b.value;
                        }),
                        label: {
                            normal: {
                                textStyle: {
                                    color: '#7c7c7c'
                                },
                                formatter: function (param) {
                                    return +Math.round(param.percent) + '%';
                                }

                            }
                        },
                        labelLine: labelLinePie,
                        itemStyle: {
                            normal: {
                                /*  color: '#cae6ea',*/
                                shadowBlur: 20,
                                shadowColor: 'rgba(0, 0, 0, 0.1)'
                            }
                        },
                        animationType: 'scale',
                        animationEasing: 'elasticOut',
                        animationDelay: function (idx) {
                            return Math.random() * 200;
                        }
                    }
                ],
                legend: {
                    orient: 'horizontal',
                    // top: 'middle',
                    bottom: 10,
                    itemGap: 4,
                    itemWidth: 6,
                    itemHeight: 6,
                    data: dataLegend,
                },
            };
            pieChart.setOption(option);
            window.onresize = pieChart.resize;
        }
        }
    })
}

//建设模式总览
function rzmsTotal() {
    var params = {
        timestamp : new Date().getTime()
    };
    var dataPie = [];
    var dataLegend = [];
    var pieChart = echarts.init(document.getElementById('taskcartogram2'));
    if(thisPie2 == 1) {
        pieChart.showLoading({color: '#9ec0c7'});
    }
    window.parent.ajax("adminIndexApi/rzmsTotal",params,function(data){
        pieChart.hideLoading();
        if(data.backCode == 0){
            if(data.bean.toString() != thisPie2.toString()) {
                thisPie2 = data.bean;
                $.each(data.bean, function (k, p) {
                    var obj = new Object();
                    if (p.value == "" || p.value == null) {
                        p.value = "0"
                    }
                    obj.value = p.value;
                    obj.name = p.name + ": " + p.value;
                    dataPie.push(obj);
                    dataLegend.push(obj.name);
                });
                option2 = {
                    backgroundColor: '#FFF',
                    tooltip: {
                        trigger: 'item',
                        formatter: "{b} ({d}%)" //"{b} : {c} ({d}%)"
                    },
                    series: [
                        {
                            name: '项目政策总览',
                            type: 'pie',
                            radius: '50%',
                            center: ['50%', '42%'],
                            roseType: 'radius',
                            color: pirColorList,
                            data: dataPie.sort(function (a, b) {
                                return a.value - b.value;
                            }),
                            label: {
                                normal: {
                                    textStyle: {
                                        color: '#7c7c7c'
                                    },
                                    formatter: function (param) {
                                        return +Math.round(param.percent) + '%';
                                    }

                                }
                            },
                            labelLine: labelLinePie,
                            itemStyle: {
                                normal: {
                                    /*  color: '#cae6ea',*/
                                    shadowBlur: 20,
                                    shadowColor: 'rgba(0, 0, 0, 0.1)'
                                }
                            },

                            animationType: 'scale',
                            animationEasing: 'elasticOut',
                            animationDelay: function (idx) {
                                return Math.random() * 200;
                            }
                        }
                    ],
                    legend: {
                        orient: 'horizontal',
                        // top: 'middle',
                        bottom: 10,
                        itemGap: 4,
                        itemWidth: 6,
                        itemHeight: 6,
                        data: dataLegend,
                    },
                };
                pieChart.setOption(option2);
                window.onresize = pieChart.resize;
            }
        }
    })
}

//总投资总览
function totalFinance() {
    var params = {
        timestamp : new Date().getTime()
    };
    var dataPie = [];
    var dataLegend = [];
    var pieChart = echarts.init(document.getElementById('taskcartogram3'));
    if(thisPie3==1){
        pieChart.showLoading({ color: '#9ec0c7'});
    }
    window.parent.ajax("adminIndexApi/totalFinance",params,function(data){
        pieChart.hideLoading();
        if(data.backCode == 0){
            if(data.bean.toString() != thisPie3.toString()) {
                thisPie3 = data.bean;
                $.each(data.bean, function (k, p) {
                    var obj = new Object();
                    if (p.value == "" || p.value == null) {
                        p.value = "0"
                    }
                    obj.value = p.value;
                    obj.name = p.name + ": " + p.value;
                    dataPie.push(obj);
                    dataLegend.push(obj.name);
                });
                option3 = {
                    backgroundColor: '#FFF',
                    tooltip: {
                        trigger: 'item',
                        formatter: "{b} ({d}%)" //"{b} : {c} ({d}%)"
                    },
                    series: [
                        {
                            name: '项目政策总览',
                            type: 'pie',
                            radius: '50%',
                            center: ['50%', '42%'],
                            roseType: 'radius',
                            color: pirColorList,
                            data: dataPie.sort(function (a, b) {
                                return a.value - b.value;
                            }),
                            label: {
                                normal: {
                                    textStyle: {
                                        color: '#7c7c7c'
                                    },
                                    formatter: function (param) {
                                        return +Math.round(param.percent) + '%';
                                    }

                                }
                            },
                            labelLine: labelLinePie,
                            itemStyle: {
                                normal: {
                                    /*  color: '#cae6ea',*/
                                    shadowBlur: 20,
                                    shadowColor: 'rgba(0, 0, 0, 0.1)'
                                }
                            },

                            animationType: 'scale',
                            animationEasing: 'elasticOut',
                            animationDelay: function (idx) {
                                return Math.random() * 200;
                            }
                        }
                    ],
                    legend: {
                        orient: 'horizontal',
                        // top: 'middle',
                        bottom: 10,
                        itemGap: 4,
                        itemWidth: 6,
                        itemHeight: 6,
                        data: dataLegend,
                    },
                };
                pieChart.setOption(option3);
                window.onresize = pieChart.resize;
            }
        }
    })
}

//政府投资总览
function totalPolicyFinance() {
    var params = {
        timestamp : new Date().getTime()
    };
    var dataPie = [];
    var dataLegend = [];
    var pieChart = echarts.init(document.getElementById('taskcartogram4'));
    if(thisPie4==1){
        pieChart.showLoading({ color: '#9ec0c7'});
    }
    window.parent.ajax("adminIndexApi/totalPolicyFinance",params,function(data){
        pieChart.hideLoading();
        if(data.backCode == 0){
            if(data.bean.toString() != thisPie4.toString()) {
                thisPie4 = data.bean;
                $.each(data.bean, function (k, p) {
                    var obj = new Object();
                    if (p.value == "" || p.value == null) {
                        p.value = "0"
                    }
                    obj.value = p.value;
                    obj.name = p.name + ": " + p.value;
                    dataPie.push(obj);
                    dataLegend.push(obj.name);
                });
                option4 = {
                    backgroundColor: '#FFF',
                    tooltip: {
                        trigger: 'item',
                        formatter: "{b} ({d}%)" //"{b} : {c} ({d}%)"
                    },
                    series: [
                        {
                            name: '项目政策总览',
                            type: 'pie',
                            radius: '50%',
                            center: ['50%', '42%'],
                            roseType: 'radius',
                            color: pirColorList,
                            data: dataPie.sort(function (a, b) {
                                return a.value - b.value;
                            }),
                            label: {
                                normal: {
                                    textStyle: {
                                        color: '#7c7c7c'
                                    },
                                    formatter: function (param) {
                                        return +Math.round(param.percent) + '%';
                                    }

                                }
                            },
                            labelLine: {
                                normal: {
                                    lineStyle: {
                                        color: '#7c7c7c'
                                    },
                                    smooth: false,
                                    length: 13,
                                    length2: 18
                                }
                            },
                            itemStyle: {
                                normal: {
                                    /*  color: '#cae6ea',*/
                                    shadowBlur: 20,
                                    shadowColor: 'rgba(0, 0, 0, 0.1)'
                                }
                            },

                            animationType: 'scale',
                            animationEasing: 'elasticOut',
                            animationDelay: function (idx) {
                                return Math.random() * 200;
                            }
                        }
                    ],
                    legend: {
                        orient: 'horizontal',
                        // top: 'middle',
                        bottom: 10,
                        itemGap: 4,
                        itemWidth: 6,
                        itemHeight: 6,
                        data: dataLegend,
                    },
                };
                pieChart.setOption(option4);
                window.onresize = pieChart.resize;
            }
        }
    })
}

//大流程情况统计
function myProcessReport() {
    var params = {
        timestamp : new Date().getTime()
    };
    var barChart = echarts.init(document.getElementById('indexbar'));
    var legendData = [];
    var seriesData = [];
    var colorList = ['#94d7e1', '#84cf72','#df6f6f','#5d798a','#1cb1c7','#c9e5e9','#8aba4f','rgb(157,147,128)','#ec9c5b',"#54a690","#eb836b"];
    if(thisBar==1){
        barChart.showLoading({ color: '#9ec0c7'});
    }
    window.parent.ajax("adminIndexApi/myProcessReport",params,function(data){
        barChart.hideLoading();
        if(data.backCode == 0) {
            if(data.bean.toString() != thisBar.toString()){
                thisBar = data.bean;
            $.each(data.bean, function (k, p) {
                var obj = new Object();
                legendData.push(p.label);
                obj.name = p.label;
                obj.type = "bar";
                obj.data = p.data;
                var _obj = new Object();
                var _color = new Object();
                _obj.normal = _color;
                _color.color = colorList[k],
                    obj.itemStyle = _obj;
                seriesData.push(obj);
            });
            option5 = {
                title: {
                    text: '',
                    subtext: '',
                    sublink: ''
                },
                grid: {//直角坐标系中除坐标轴外的绘图网格，用于定义直角系整体布局
                    left: '15',
                    right: '15',
                    bottom: '15',
                    top: '50',
                    containLabel: true
                },
                //animationDuration: 4000,
                tooltip: {
                    trigger: 'axis',
                    backgroundColor: 'rgba(255,255,255,0.7)',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: function (params) {
                        // for text color
//			            var color = colorList[params[0].dataIndex];
                        var res = '<div style="border:1px solid #9ec0c7;padding:10px;">';
                        res += '<strong style="color:#3e3a39;">' + params[0].name + '</strong>'
                        for (var i = 0, l = params.length; i < l; i++) {
                            res += '<br/><span style="color:' + colorList[i] + '">' + params[i].seriesName + ' &nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#000;font-size:12px;"> ' + params[i].value + '</span></span>'
                        }
                        res += '</div>';
                        return res;
                    }
                },
                legend: {
                    x: 'left',
                    left: 40,
                    show: true,
                    data: legendData,
                    top: 5,
                },
                toolbox: {
                    feature: {
                        mark: {show: true},
                        /*dataView : {show: true, readOnly: false},*/
                        magicType: {show: true, type: ['line', 'bar']},
                        dataZoom: {
                            show: true,
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
                        data: ["项目信息", "项目手续", "项目投资", "招标采购", "合同备案", "项目进度", "竣工验收", "绩效评价"],
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
                        }
                    }
                ],
                series: seriesData,
            };
            barChart.setOption(option5);
            window.onresize = barChart.resize;
        }
        }else{
            $("#indexbar").html(nullTip);
        }
    })
}

