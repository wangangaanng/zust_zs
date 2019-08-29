var colorList = ['#43cfa9', '#43a9cf','#3e8be2','#2b63f0','#5144fd',"#aa4ef8",'#f84ebe','#ff635e','#ff8746','#ffb22d','#fed755','#92cf43','#2fa4f5',"#7089fa"];
var colorLine =["#ff7800","#009cff"];
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
    getDepartment();//获取部门
    getAreas();//获取地区

    topChart();
    pointPie();

    $(".select-type .top-select").click(function (){
        $(".select-type .top-select").removeClass("slect-active");
        $(this).addClass("slect-active");
        type = $(this).attr("val");
        $(".finish-amount span").css("border","none");
        projectId = "";
        topChart();
        pointPie();
    });


});

//获取部门
function getDepartment(){
    var params = {
        timestamp : new Date().getTime()
    };
    //测试
    // window.parent.ajax("center/projectreport/leaderIndexApi/getAreas",params,function(data){});

    window.parent.ajax("review/leaderView/manageDeptList",params,function(data){
        if(data.backCode == 0) {
            $("#depart-list").html("");
            if(data.bean.length>0){
                var str = "";
                $.each(data.bean, function(k,p) {
                    if(k==0){
                        str = "<li><a class='slect-active' val="+p.deptId+" >"+p.deptName+"</a></li>";
                    }else
                        str += "<li><a val="+p.deptId+">"+p.deptName+"</a></li>";
                });
                $("#depart-list").html(str);
                $("#depart-list li").click(function(){
                    $("#depart-list li a").removeClass("slect-active");
                    $(this).find("a").addClass("slect-active");
                    deptId = $(this).find("a").attr("val");
                    topChart();
                    pointPie();
                })
            }
        }
    })
}

//获取盟市/旗县
function getAreas(){
    var params = {
        timestamp : new Date().getTime()
    };
    window.parent.ajax("projectApi/listTjArea",params,function(data){
        if(data.backCode == 0) {
            $("#area-select").html("");
            if(data.bean.length>0){
                var str = "";
                $.each(data.bean, function(k,p) {
                    if(k==0){
                        str = "<li><a class='slect-active' val="+p.value+">"+p.label+"</a></li>";
                    }else
                        str += "<li><a val="+p.value+">"+p.label+"</a></li>";
                });
                $("#area-select").html(str);
                $("#area-select li").click(function(){
                    $("#area-select li a").removeClass("slect-active");
                    $(this).find("a").addClass("slect-active");
                    reginCode = $(this).find("a").attr("val");
                    topChart();
                    pointPie();
                })
            }
        }
    })
}


//项目政策总览
function topChart(isAll) {
    if(!isAll){isAll=false}
    var urlArr = ["center/projectreport/leaderViewer/policyTotal","center/projectreport/leaderViewer/tzgmTotal","center/projectreport/leaderViewer/zjjd","center/projectreport/leaderViewer/xmjd"];
    var paradata = {
        "deptId":deptId,
        "reginCode":reginCode,
    };
    var params = {
        "data": JSON.stringify(paradata),
        "method":"",
        timestamp: new Date().getTime()
    };
    $.each(chartDom,function(k,p){
        pieChart[k] = echarts.init(p);
        pieChart[k].showLoading({ color: '#5caefd'});
        params.method = urlArr[k];
        $.post(loginUrl+"system/commonApi.htm", params, function (data) {
            // window.parent.ajax(urlArr[k],params,function(data){
            pieChart[k].hideLoading();
            if(data.backCode == 0) {
                if(!emptyCheck(data.bean.policyNum)){data.bean.policyNum=0}
                if(!emptyCheck(data.bean.projectNum)){data.bean.projectNum=0}
                if(!emptyCheck(data.bean.zftz)){data.bean.zftz=0}
                if(!emptyCheck(data.bean.tzgm)){data.bean.tzgm=0}
                if(!emptyCheck(data.bean.ztz)){data.bean.ztz=0}
                if(!emptyCheck(data.bean.wctz)){data.bean.wctz=0}
                if(!emptyCheck(data.bean.sgsl)){data.bean.sgsl=0}
                if(!emptyCheck(data.bean.wcjd)){data.bean.wcjd=0}
                if(!emptyCheck(data.bean.centerFinance)){data.bean.centerFinance=0}
                if(!emptyCheck(data.bean.provinceFinance)){data.bean.provinceFinance=0}
                if(!emptyCheck(data.bean.cityFinance)){data.bean.cityFinance=0}
                if(!emptyCheck(data.bean.areaFinance)){data.bean.areaFinance=0}
                if(k==0){
                    colorList = ['#43cfa9', '#43a9cf','#3e8be2','#2b63f0','#5144fd',"#aa4ef8",'#f84ebe','#ff635e','#ff8746','#ffb22d','#fed755','#92cf43','#2fa4f5',"#7089fa"];
                    var str1 ='<tr><td>政策数量</td><td><span>'+data.bean.policyNum+'</span><label>个</label></td></tr><tr><td>项目数量</td><td><span>'+data.bean.projectNum+'</span><label>个</label></td> </tr>';
                    $("#xmzc").html(str1);
                }
                if(k==1){
                    colorList = ["#009cff","#ffb22d",'#43cfa9','#aa4ef8','#ff635e'];
                    var str2 ='<tr><td>投资规模</td><td><span>'+data.bean.tzgm+'</span><label>亿</label></td></tr><tr><td>其中政府</td><td><span>'+data.bean.zftz+'</span><label>亿</label></td> </tr>';
                    $("#jsms").html(str2);
                }
                if(k==2){
                    colorList = ["#92cf43","#f84ebe","#ff635e","#ff8746","#ffb22d"];
                    var str3 = '<tr><td>中央投资</td><td><span>'+data.bean.centerFinance+'</span><label>亿</label></td><td>省级投资</td><td><span>'+data.bean.provinceFinance+'</span><label>亿</label></td></tr>';
                    str3 +='<tr><td>市级投资</td><td><span id="jsgm">'+data.bean.cityFinance+'</span><label>亿</label></td><td>旗县投资</td><td><span id="tzjd">'+data.bean.areaFinance+'</span><label>亿</label></td></tr>';

                    $("#tzgc").html(str3);
                }
                if(k==3){
                    colorList = ["#009cff","#ffb22d","#f84ebe","#ff8746","#ffb22d"];
                    pieChart[k] = echarts.init(p);
                    if(data.bean.pies.length>0){
                        dataPie = data.bean.pies;
                    }else{
                        $(chartDom[k]).removeAttr("_echarts_instance_");
                        $(chartDom[k]).html(nullTip);
                    }
                    var str4 = '<tr><td>施工数量</td><td><span>'+data.bean.sgsl+'</span><label>个</label></td><td>完成投资</td><td><span>'+data.bean.wctz+'</span><label>亿</label></td></tr>';
                    str4 +='<tr><td>完成进度</td><td><span id="jsgm">'+data.bean.wcjd+'</span><label>%</label></td><td>其中政府</td><td><span id="tzjd">'+data.bean.ztz+'</span><label>%</label></td></tr>';
                    $("#jd").html(str4);
                }else{
                    if(data.bean.pies.length>0){
                        dataPie = data.bean.pies;
                    }else{
                        $(chartDom[k]).removeAttr("_echarts_instance_");
                        $(chartDom[k]).html(nullTip);
                    }
                }
            }
            pieOption = {
                backgroundColor: 'inhert',
                tooltip: {
                    trigger: 'item',
                    formatter: "{b} ({d}%)" //"{b} : {c} ({d}%)"
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

function pointPie(isAll) {
    if(!isAll){isAll=false}
    var params = {
        "deptId":deptId,
        "reginCode":reginCode,
        timestamp : new Date().getTime()
    };
    var barChart = echarts.init(document.getElementById('indexpoint'));
    var legendData = [];
    var seriesData = [];
    barChart.showLoading({ color: '#5caefd'});
    window.parent.ajax("leaderViewer/projectProcessReport",params,function(data){
        barChart.hideLoading();
        if(data.backCode == 0) {
            /* var str = "<ul>";*/
            var nameArr = ["项目信息","项目手续","项目投资","招标采购","合同备案","项目进度","竣工验收","绩效评价"];
            var str ="<div class='chart-bottom_title'><p>项目数量</p><p>流程选项</p><p>项目流程</p></div><ul>"
            $.each(data.bean.mksl,function (k,p) {
                str +="<li><span>"+data.bean.xmsl[k]+"</span>" +
                    "<span>"+p+"</span>" +
                    "<span>"+nameArr[k]+"</span></li>";
            });
            str +="</ul>";
            $(".chart-bottom").html(str);

            var nameArr = ["流程选项","流程进度"];
            legendData = nameArr;
            var lineData = [data.bean.lcxx,data.bean.lxjd];
            $.each(nameArr, function (k,p) {
                seriesData.push( {
                    name: p,
                    type: 'line',
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
                    y2: 5,
                    x: 60,
                    x2:20
                },
                //animationDuration: 4000,
                tooltip: {
                    trigger: 'axis',
                    backgroundColor: 'rgba(255,255,255,0.7)',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: function (params) {
                        var res = '<div style="border:1px solid #5caefd;padding:10px;">';
                        res += '<strong style="color:#3e3a39;">' + params[0].name + '</strong>';
                        var k = "";
                        for (var i = 0, l = params.length; i < l; i++) {
                            res += '<br/><span style="color:' + colorLine[i] + '">' + params[i].seriesName + ' &nbsp;&nbsp;&nbsp;&nbsp;';
                            if(i==1){
                                if(params[0].name=="项目信息"){k=0};
                                if(params[0].name=="项目手续"){k=1};
                                if(params[0].name=="项目投资"){k=2};
                                if(params[0].name=="招标采购"){k=3};
                                if(params[0].name=="合同备案"){k=4};
                                if(params[0].name=="项目进度"){k=5};
                                if(params[0].name=="竣工验收"){k=6};
                                if(params[0].name=="绩效评价"){k=7};
                                res +='<span style="color:#000;font-size:12px;"> ' + params[i].value + '('+data.bean.pjwcd[k]+'%)</span>';
                            }else{
                                res +='<span style="color:#000;font-size:12px;"> ' + params[i].value + '</span>';
                            }
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
                        },
                        axisLabel: {
                            show: false
                        },
                        axisLine:axisLine,
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        axisTick: {
                            inside: true
                        },
                        axisLine:axisLine,
                    }
                ],
                series: seriesData,
            };
            barChart.setOption(option5);
            window.onresize = barChart.resize;
        }
    })
}


