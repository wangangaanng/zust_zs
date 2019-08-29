var owid = "";//文章主键
var pageSize = 10;
var currPage = 0;

var thisPie = 1;
var thisBar = 1;
$(document).ready(function(){
    undoTask();
    noticeAd();
    noticelist();
    revertFun();



    $(".close-wrap").click(function(){
        if($(".overlay-wrap").hasClass("overlay-show")){
            $(".overlay-list li span").css("color","#999");
            $(".overlay-list li").css("color","#595757");
            $(".overlay-wrap").removeClass("overlay-show");
        }else{
            $(".overlay-wrap").addClass("overlay-show")
        }
    });


    /*下一页*/
    /*$(".overlay-close").click(function(){
        if($(".overlay-wrap").hasClass("overlay-show")){
            $(".overlay-wrap").removeClass("overlay-show");
        }else{
            $(".overlay-wrap").addClass("overlay-show")
        }
    });*/

});
function revertFun()
{   pieData();
    processReport();
    setTimeout(revertFun,5000);
}
function noticelist() {
   /* if(currPage == 0){
        $(".list-pre").hide();
    }else{
        $(".list-pre").show();
    }*/

    var params = {
        "pageNo":currPage,
        "pageSize":pageSize,
        timestamp : new Date().getTime()
    };
    window.parent.ajax("companyIndexApi/listAllNews",params,function(data){
        if(data.backCode == 0){
            var str = "";
            if(data.bean.length>0){
                $.each(data.bean,function(k,p){
                    if(!p.newsTypeStr){
                        p.newsTypeStr = "暂无公告类型"
                    }
                    str = "<li owid = "+p.owid+" title = "+p.newsTitle+">"+p.newsTitle+"<span>时间:"+ p.newsTimeStr.slice(0,10)+"</span></li>";
                    $(".overlaylist-detail").append(str);
                });
                currPage ++;
            }else{
                $(".noticeDatas").html(nullTip);
            }
            //点击出现详细
            $(".overlay-list li").click(function(){
                owid = $(this).attr("owid");
                $(".overlay-list li span").css("color","#999");
                $(this).children("span").css("color","#6a9dad");
                $(this).css("color","#6a9dad").siblings().css("color","#595757");
                notDetail();
            });

        }
    })
}

var nullTip = "<div class = 'null-tip'><img src = 'img/index_nulltip.png'/><p>暂时还没有信息</p></div>";
//饼图
function pieData() {
    var params = {
        timestamp : new Date().getTime()
    };
	var dataPie = [];
	var dataLegend = [];
    var pieChart = echarts.init(document.getElementById('taskcartogram'));
    if(thisPie == 1){
        pieChart.showLoading({ color: '#9ec0c7'});
    }
	window.parent.ajax("companyIndexApi/myTotalFinance",params,function(data){
        pieChart.hideLoading();
            if(data.backCode == 0){
                if(data.bean.toString() != thisPie.toString()){
                    thisPie = data.bean;
                $.each(data.bean,function(k,p){
                    var obj = new Object();
                    if(p.value == ""|| p.value == null){p.value= 0}
                    obj.value = p.value;
                    obj.name = p.name +": "+p.value;
                    dataPie.push(obj);
                    dataLegend.push(obj.name);
                });

                option = {
                    backgroundColor: '#FFF',
                    tooltip : {
                        trigger: 'item',
                        formatter: "{b} ({d}%)" //"{b} : {c} ({d}%)"
                    },

                    /* visualMap: {
                     show: true,
                     min: 80,
                     max: 600,
                     inRange: {
                     colorLightness: [0, 1]
                     }
                     },*/
                    series : [
                        {
                            name:'资金统计',
                            type:'pie',
                            radius : '60%',
                            center: ['44%', '50%'],
                            roseType: 'radius',
                            color: ['#c6e2e6', '#d7ebd2','#9eb4b5','#5d798a','#1cb1c7'],
                            data:dataPie.sort(function (a, b) { return a.value - b.value; }),
                            label: {
                                normal: {
                                    textStyle: {
                                        color: '#7c7c7c'
                                    },
                                    formatter: function(param) {
                                        return   +Math.round(param.percent) + '%';
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
                        orient: 'vertical',
                        // top: 'middle',
                        top: 10,
                        right: 14,
                        itemGap:5,
                        itemWidth:8,
                        itemHeight:8,
                        data: dataLegend,
                    },
                };
                pieChart.setOption(option);
                window.onresize = pieChart.resize;
            }
        }
	})
}
//未完成任务列表
function undoTask() {
    var params = {
        timestamp : new Date().getTime()
    };
    var lk = "";
    window.parent.ajax("companyIndexApi/myNotFinishTaskList",params,function(data){
        if(data.backCode == 0){
        	var str = "";
        	if(data.bean.length>0){
                $.each(data.bean,function(k,p){
                    if(k<4){
                    	lk = k + 1;
                    	str = "<tr><td title="+p.project.projectTitle+">"+lk+"."+p.project.projectTitle+"</td><td>"+p.project.projectPolicyName+"</td><td>"+p.createtime.slice(0,10)+"</td></tr>";
                        $(".datatask").append(str);
					}
				});
			}else{
                $(".datatask").html(nullTip);
			}
		}
	})
}

//通知公告
function noticeAd() {
    var params = {
        "pageNo":0,
        "pageSize":4,
        timestamp : new Date().getTime()
    };
    var lk = "";
    window.parent.ajax("companyIndexApi/listAllNews",params,function(data){
        if(data.backCode == 0){
            var str = "";
            if(data.bean.length>0){
                $.each(data.bean,function(k,p){
                    if(k<4){
                        if(!p.newsTypeStr){
                            p.newsTypeStr = "暂无公告类型"
                        }
                        lk = k + 1;
                        str = "<tr  owid="+p.owid+" ><td title="+p.newsTitle+">"+lk+"."+p.newsTitle+"</td><td>"+p.newsTypeStr+"</td><td>"+p.newsTimeStr.slice(0,10)+"</td></tr>";
                    }
                    $(".noticeDatas").append(str);
                });
            }else{
                $(".noticeDatas").html(nullTip);
            }
            //点击标题查看文章详细
            $(".noticeDatas tr").click(function(){
                owid = $(this).attr("owid");
                var index = $(this).index();
                $(".overlay-list li span").css("color","#999");
                $(".overlay-list li span").eq(index).css("color","#6a9dad");
                $(".overlay-list li").eq(index).css("color","#6a9dad").siblings().css("color","#595757");
                $(".overlay-wrap").addClass("overlay-show");
                notDetail();

            });
            $(".index-overlay").click(function(){
                if($(".overlay-wrap").hasClass("overlay-show")){
                    $(".overlay-wrap").removeClass("overlay-show");
                }else{
                    $(".overlay-wrap").addClass("overlay-show")
                }

            });
        }
    })
}

//柱状图
function processReport() {
    var params = {
        timestamp : new Date().getTime()
    };
    var barChart = echarts.init(document.getElementById('indexbar'));
    var legendData = [];
    var seriesData = [];
    var colorList = ['#eb836b','#c9e5e9','#8aba4f','rgb(157,147,128)','#ec9c5b',"#54a690"];
    if(thisBar == 1) {
        barChart.showLoading({color: '#9ec0c7'});
    }
    window.parent.ajax("companyIndexApi/myProcessReport",params,function(data){
        barChart.hideLoading();
        if(data.backCode == 0){
            if(data.bean.toString() != thisBar.toString()) {
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
                option2 = {
                    title: {
                        text: '',
                        subtext: '',
                        sublink: ''
                    },
                    grid: {//直角坐标系中除坐标轴外的绘图网格，用于定义直角系整体布局
                        left: '15',
                        right: '15',
                        bottom: '15',
                        top: '40',
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
                        left: 30,
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
                barChart.setOption(option2);
                window.onresize = barChart.resize;
            }
        }else{
            $("#indexbar").html(nullTip);
        }
    })
}

//文章详细
function notDetail() {
    var params = {
        "owid":owid,
        timestamp : new Date().getTime(),
    };
    $(".load-img").show();
    window.parent.ajax("companyIndexApi/detailNews",params,function(data){
        $(".load-img").hide();
        if(data.backCode == 0){
           if(data.bean.owid){
               var a = data.bean;
              $(".content-detail>p").html(a.newsTitle);
              $(".newsTypeStr span").html(a.newsTypeStr);
              $(".ad-time span").html(a.newsTimeStr);
              $(".ad-details").html(a.newsContent);
              if(a.newsImg){
                 $(".index-imgwrap").html("<img src="+a.newsImg+"/>")
              }
              $(".overlay-close").show();
           }else{
               $(".ad-details").html(nullTip);
           }
        }
    })
}