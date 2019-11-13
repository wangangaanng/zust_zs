var colorList = ['#43cfa9', '#43a9cf','#3e8be2','#2b63f0','#5144fd',"#aa4ef8",'#f84ebe','#ff635e','#ff8746','#ffb22d','#fed755','#92cf43','#2fa4f5',"#7089fa"];
var colorLine =["#ff7800","#009cff","#cc2639"];
var pieChart = ["pieChart1","pieChart2","pieChart3"];
var nullTip = "<div class = 'null-tip'><img src = 'img/index_nulltip.png' style='margin-top: 60px'/><p style='margin-top: 10px'>查询结果暂为空</p></div>";
var chartDom = $(".pie-list .pie-wrap");
var pieOption = "";
var dataPie = [];
var type = 0;
var projectId="";

var nullThisTip='<a class="wrap-list">暂无</a>';

var deptId="";//科室编号
var reginCode="";//地区编号
//var currentYear=(new Date()).getFullYear()

$(document).ready(function(){
    /*$("#year").text(currentYear);
    $(".dropdown-menu").html('<li><a>'+currentYear+'</a></li> <li><a>'+(currentYear-1)+'</a></li> <li><a>'+(currentYear-2)+'</a></li>')
    $(".dropdown-menu").on("click",'li',function () {
        currentYear=$(this).text();
        $("#year").text(currentYear);
        initBar();
    })*/
    getJob(1);//1注册用户
    getJob(3);//3待初审用户
    getJob(7);//7面试未分配用户
    //getQy();//
    topChart();
    //initBar();//柱状图
});

function getJob(state) {
    var params={
        "state":state
    }
    window.parent.ajax("web/zustswyt/bckjBizBm/todoMan",params,function(data){
        if(data.backCode == 0) {
            var str='';
            if(data.bean!=null&&data.bean!=undefined){
                var i =''
                if(state==1){i='注册用户'}
                if(state==3){i='待初审用户'}
                if(state===7){i='面试未分配用户'}
                str += '<div class="article skt" style="line-height: 55px;">';
                str += '<div class="bt col2" style="font-size: 16px;padding: 7px 0;">系统实时'+i+':</div>';
                str += '<div class="fbsj col1" style="font-size: 16px;padding: 9px 0;">'+data.bean+'人</div>';
                str += '</div>';
            }else{
                str="<div class = 'null-tip'><img src = 'img/index_nulltip.png' style='margin-top: 0px'/><p style='margin-top: 10px'>查询结果暂为空</p></div>";
            }

            if(state==1){
                $("#xxzph").append(str);
            }
            if(state==3){
                $("#xxxjh").append(str);
            }
            if(state==7){
                $("#xxqy").append(str);
            }
        }
    })
}
/*function getQy() {
    var params={

    }
    window.parent.ajax("web/zustjy/common/todoCompany",params,function(data){
        if(data.backCode == 0) {
            var str='';
            if((data.bean)&&(data.bean.length>0)){
                $.each(data.bean,function (k,p) {
                    if(k<6) {
                        str += '<div class="article skt">';
                        str += '<div class="bt col2">' + p.qymc + '</div>';
                        str += '<div class="fbsj col1">' + (p.createtime).substring(0, 10) + '</div>';
                        str += '</div>';
                    }
                })
            }else{
                str='<p style="text-align: center;margin: 20px auto;">暂无数据</p>'
            }

            $("#xxqy").append(str);

        }
    })
}*/

//饼图
function topChart() {
    $.each(chartDom,function(k,p){
        pieChart[k] = echarts.init(p);
        pieChart[k].showLoading({ color: '#5caefd'});
        window.parent.ajax('web/zustswyt/bckjBizBm/bmPie',{type:k+1},function(data){
            pieChart[k].hideLoading();
            if(data.backCode == 0) {
                //var str1='<tr><td>总人数</td><td><span>'+data.bean.total+'人</span></td>';
                //str1 +='<tr><td>类别数</td><td><span>'+data.bean.lb+'</span></td>'
                var str1 ='';
                var str2 = '<div style="text-align: center;font-size: 18px;color: #009cff;margin-bottom: 20px;"><span>总人数:'+data.bean.total+'人</span><span style="margin-left: 20px;">类别数:'+data.bean.lb+'</span></div>';
                for(var g=0;g<data.bean.pieData.length;g++){
                    str1+='<tr><td>'+data.bean.pieData[g].name+'</td><td><span>'+data.bean.pieData[g].value+'人</span></td>'
                }
                if(k==0){
                    colorList = ['#43cfa9', '#43a9cf','#3e8be2','#2b63f0','#5144fd',"#aa4ef8",'#f84ebe','#ff635e','#ff8746','#ffb22d','#fed755','#92cf43','#2fa4f5',"#7089fa"];
                    $("#qytj").html(str1);
                    $("#qytj").before(str2);
                }
                if(k==1){
                    colorList = ["#009cff","#ffb22d",'#43cfa9','#aa4ef8','#ff635e'];
                    $("#zwtj").html(str1);
                    $("#zwtj").before(str2);
                }
                if(k==2){
                    colorList = ["#92cf43","#f84ebe","#ff635e","#ff8746","#ffb22d"];
                    $("#zphtj").html(str1);
                    $("#zphtj").before(str2);
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

    });

}

/*function initBar(isAll) {
    if(!isAll){isAll=false}
    var params = {
        year:currentYear
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
            barChart.setOption(option5,true);
            window.onresize = barChart.resize;
        }
    })
}*/


