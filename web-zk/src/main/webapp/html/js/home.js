var colorList = ['#43cfa9', '#43a9cf','#3e8be2','#2b63f0','#5144fd',"#aa4ef8",'#f84ebe','#ff635e','#ff8746','#ffb22d','#fed755','#92cf43','#2fa4f5',"#7089fa"];
var colorLine =["#ff7800","#009cff"];
var pieChart = ["pieChart1","pieChart2","pieChart3","pieChart4"];
var nullTip = "<div class = 'null-tip'><img src = 'img/index_nulltip.png' style='margin-top: 60px'/><p style='margin-top: 10px'>查询结果暂为空</p></div>";
var chartDom = $(".pie-list .pie-wrap");
var pieOption = "";
var dataPie = [];
var pieFinishArr = [];
var type = 0;
var policyType = "";
var jsmsType = ""
var projectId="";

var DetailArr = new Map();
var nullThisTip='<a class="wrap-list">暂无</a>';
var topScroll = "";
var projectTitle = "";//标题
var projectTopDeptPath = "";//主管部门
var projectReginCode = "";//地区
var projectFinancMode = "";//建设模式
var projectLevel = "";//项目级次
var projectPolicyPath = "";//项目政策
var projectIndustry = "";//项目工程类别
$(document).ready(function(){
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

    $(".finish-amount span").click(function (){
        $(".finish-amount span").css("border","none");
        $(this).addClass("tab-selected").siblings().removeClass("tab-selected");
        var thisindex = $(this).attr("val");
        if(pieFinishArr[thisindex].length>0){
            pieOption.series[0].data = pieFinishArr[thisindex];
            var thispie = echarts.init(chartDom[3])
            thispie.setOption(pieOption);
            window.onresize = thispie.resize;
        }else{
            $(chartDom[3]).removeAttr("_echarts_instance_");
            $(chartDom[3]).html(nullTip);
        }
    });

    $(".select-overlay").bind("click", function(){
        $(".select-option").removeClass("show");
        $(".select-option").addClass("hide");
    });

    //弹出层项目列表
    $(document).on("click", "#projectList li", function() {
        projectId = $(this).attr("owid");
        var isAll = $(this).attr("isAll");
        $(".select-option").addClass("hide");
        $(".overlay-mask").removeClass("active");
        $("body").css({"position":"static"});
        $(document).scrollTop(topScroll);
        topChart(isAll);
        pointPie(isAll);
    });

});

function handleData(data,index,typeId) {
    if(index==3 || index==4 ||index==5 ||index==6){
        if (data.backCode == 0) {
            var setting = {
                callback : {
                    // beforeClick: zTreeBeforeClick,
                    onClick : zTreeOnClick,
                    // onAsyncSuccess: zTreeOnAsyncSuccess //异步加载完成调用
                }
            };
            if(index==3){
                var partNodes = data.bean;
                $.fn.zTree.init($("#mainPart"), setting, partNodes);
            }
            if(index==4){
                var areaNode = data.bean;
                $.fn.zTree.init($("#area"), setting, areaNode);
            }

            var str = '';
            var str2 = '';
            DetailArr.set("请选择", "");
            if (data.bean.length > 0) {//第一级
                $.each(data.bean, function (k, p) {
                    DetailArr.set(p.NAME, p.children);
                    if (index == 5 || index == 6) {
                        if (k == 0) {
                            str = '<a class="wrap-list single-select" owid = "" >请选择</a><ul class="select-option"><li>请选择</li>';
                        }
                        str += '<li  owid = ' + p.dicVal1 + '>' + p.dicVal2 + '</li>';
                    }
                });
                str += "</ul>";
                $("#partlist1" + index).html(str);
            } else {
                $("#partlist1" + index).html(nullThisTip);
            }
        }
    }
    if(index==7){
        if(data.bean.length>0){
            var str7 = "";
            $.each(data.bean,function(k,p){
                if(k==0){
                    str7='<a class="wrap-list double-select">请选择</a><ul class="select-option"><li index='+index+' path="">请选择</li>';
                }
                str7 +='<li name= '+p.policyTitle+' index='+index+' path='+p.path+'>'+p.policyTitle+'</li>';
                DetailArr.set(p.policyTitle,p.children);
            })
            str7 +="</ul>";
            $("#partlist1"+index).html(str7);
            $("#partlist2"+index).html("<a class='wrap-list double-select' owid='' path=''>暂无</a>");

        }else{
            $("#partlist1"+index).html(nullThisTip);
        }
    }
    if(index==8){
        $(".search-btn").val("查询");
        if(data.backCode==0){
            var str = "<li owidA='' isAll='true'>当前全部项目</li>";
            $("#projectList").html("");
            if(data.bean.length>0){
                $(".project-num").html("(共"+data.bean.length+"个项目)");
                $.each(data.bean,function (k,p) {
                    str +="<li owid="+p.owid+">"+p.projectTitle+"</li>";
                });
                $("#projectList").html(str);
            }else{
                $(".project-num").html("(共0个项目)");
                $("#projectList").html("<div class = 'null-tip'><img src = 'img/index_nulltip.png' style='margin-top: 0px;'/><p style='margin-top: 10px;border-bottom: none'>查询结果暂为空</p></div>");
            }
        }
    }
}
//项目政策总览
function topChart(isAll) {
    if(!isAll){isAll=false}
    var urlArr = ["indexReportApi/policyTotal","indexReportApi/rzmsTotal","indexReportApi/tzgcTotal","indexReportApi/wcjdTotal"];
    var params = {
        "type":type,
        "isAll":isAll,
        "projectId":projectId,
        "projectTitle":projectTitle,
        "projectTopDeptPath":projectTopDeptPath,
        "projectReginCode":projectReginCode,
        "projectFinancMode":projectFinancMode,
        "projectLevel":projectLevel,
        "projectPolicyPath":projectPolicyPath,
        "projectIndustry":projectIndustry,
        timestamp : new Date().getTime()
    };
    $.each(chartDom,function(k,p){
        pieChart[k] = echarts.init(p);
        pieChart[k].showLoading({ color: '#9ec0c7'});
        window.parent.ajax(urlArr[k],params,function(data){
            pieChart[k].hideLoading();
            if(data.backCode == 0) {
                if(k==0){
                    colorList = ['#43cfa9', '#43a9cf','#3e8be2','#2b63f0','#5144fd',"#aa4ef8",'#f84ebe','#ff635e','#ff8746','#ffb22d','#fed755','#92cf43','#2fa4f5',"#7089fa"];
                    var str1 ='<tr><td>政策数量</td><td><span>'+data.bean.total+'</span><label>个</label></td></tr><tr><td>投资规模</td><td><span>'+data.bean.tzgm+'</span><label>亿</label></td> </tr>';
                    $("#xmzc").html(str1);
                }
                if(k==1){
                    colorList = ["#2b63f0","#5144fd","#aa4ef8","#f84ebe","#ffb22d",'#fed755',"#92cf43","#43cfa9","#ff8746","#ff635e"];
                    var str2 ='<tr><td>模式数量</td><td><span>'+data.bean.total+'</span><label>个</label></td></tr><tr><td>建设规模</td><td><span>'+data.bean.tzgm+'</span><label>亿</label></td> </tr>';
                    $("#jsms").html(str2);
                }
                if(k==2){
                    colorList = ["#92cf43","#f84ebe","#ff635e","#ff8746","#ffb22d"];
                    var str3 ='<tr><td>项目数量</td><td><span>'+data.bean.total+'</span><label>个</label></td></tr><tr><td>投资规模</td><td><span>'+data.bean.tzgm+'</span><label>亿</label></td> </tr>';
                    $("#tzgc").html(str3);
                }
                if(k==3){
                    colorList = ["#009cff","#ffb22d"];
                    pieChart[k] = echarts.init(p);
                    pieFinishArr[0] = data.bean.gcPie;
                    pieFinishArr[1] = data.bean.zjPie;
                    if(pieFinishArr[0].length>0){
                        dataPie =pieFinishArr[0];
                    }else{
                        $(chartDom[k]).removeAttr("_echarts_instance_");
                        $(chartDom[k]).html(nullTip);
                    }
                    var str4 = '<tr><td>施工数量</td><td><span>'+data.bean.sgsl+'</span><label>个</label></td><td>完成投资</td><td><span>'+data.bean.zftz+'</span><label>亿</label></td></tr>';
                        str4 +='<tr><td>投资拨款</td><td><span id="jsgm">'+data.bean.jsgm+'</span><label>亿</label></td><td>投资进度</td><td><span id="tzjd">'+data.bean.tzjd+'</span><label>%</label></td></tr>';
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
        "type":type,
        "isAll":isAll,
        "projectId":projectId,
        "projectTitle":projectTitle,
        "projectTopDeptPath":projectTopDeptPath,
        "projectReginCode":projectReginCode,
        "projectFinancMode":projectFinancMode,
        "projectLevel":projectLevel,
        "projectPolicyPath":projectPolicyPath,
        "projectIndustry":projectIndustry,
        timestamp : new Date().getTime()
    };
    var barChart = echarts.init(document.getElementById('indexpoint'));
    var legendData = [];
    var seriesData = [];
    barChart.showLoading({ color: '#9ec0c7'});
    window.parent.ajax("indexReportApi/projectProcessReport",params,function(data){
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
                            var res = '<div style="border:1px solid #9ec0c7;padding:10px;">';
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

//第三级
function  appendDtail(data,index) {
    var str2 = '';
    $.each(data,function(g,i){
        if(index==7){
            if(g==0){str2='<a class="wrap-list double-select" owid = "" path="" >请选择</a><ul class="select-option"><li path="" index='+index+'>请选择</li>';}
            str2 +='<li path='+i.path+'   index='+index+'>'+i.policyTitle+'</li>';
        }else{
            if(g==0){str2='<a class="wrap-list sec-select" owid = "" path="" >请选择</a><ul class="select-option"><li owid = "" path="" name= "请选择" index='+index+' cc='+cj4+'>请选择</li>';}
            var cj4= i.CC+1;
            str2 +='<li path='+i.PATH+' owid = '+i.OWID+' name= '+i.NAME+' index='+index+' cc='+cj4+' >'+i.NAME+'</li>';
        }
    });
    str2 +="</ul>";
    return str2;
}

//点击展开下拉框
$(document).on("click", ".select-mode li a", function() {
    $(".select-option").addClass("hide");
    $(this).next(".select-option").removeClass("hide");
    $(this).next().addClass("show");
    event.stopPropagation();
});

//下拉框选择后隐藏下拉框
$(document).on("click", ".option-select li", function() {
    var name = $(this).attr("name");
    var index = $(this).attr("index");
    var cc= $(this).attr("cc");
    var children =  DetailArr.get(name);
    if($(this).attr("owid")){
        var owid = $(this).attr("owid");
    }else{
        var owid = "";
    }
    var path = $(this).attr("path");
    if($(this).parents("li").attr("id")=="partlist33"){
        ajaxPost("newProjectApi/policyListInPie.do",{"owid":owid,"path":path},7);//根据部门获取政策
    }
    if(children){
        if(index==7){
            cc=2;
            $("#partlist"+cc+index).html(appendDtail(children,index));
        }else{
            $("#partlist"+cc+index).html(appendDtail(children,index));
            if(children[0].children){
                $("#partlist3"+index).html(appendDtail(children[0].children,index));
            }else{
                if(cc==2){
                    $("#partlist3"+index).html(nullThisTip);
                }
            }
        }
    }else{
        $("#partlist"+cc+index).html(nullThisTip);
    }
    var text = $(this).html();
    if(text=="请选择"){
        $(this).parents("li").nextAll("li").find("a").html("暂无");
        $(this).parents("li").nextAll("li").find("a").html("暂无");
        $(this).parents("li").nextAll("li").find("ul").html("");
    }
    $(this).parents("li").find("a").html(text).attr({"owid":owid,"path":path});
    $(".select-option").removeClass("show").addClass("hide");
    event.stopPropagation();
});
//关闭弹出
$(".ic-overlay-close").click(function () {
    $(".overlay-mask").removeClass("active");
    $("body").css({"position":"static"});
    $(document).scrollTop(topScroll);
})

//点击树事件
function zTreeOnClick(){
    if($.fn.zTree.getZTreeObj("mainPart").getSelectedNodes()[0]){
        projectTopDeptPath = $.fn.zTree.getZTreeObj("mainPart").getSelectedNodes()[0].path;
        closeTree($.fn.zTree.getZTreeObj("mainPart"));
        $("#part-value").html($.fn.zTree.getZTreeObj("mainPart").getSelectedNodes()[0].name);
    }
    if($.fn.zTree.getZTreeObj("area").getSelectedNodes()[0]){
        projectReginCode = $.fn.zTree.getZTreeObj("area").getSelectedNodes()[0].owid;
        closeTree($.fn.zTree.getZTreeObj("area"));
        $("#area-value").html($.fn.zTree.getZTreeObj("area").getSelectedNodes()[0].name);
    }
}
