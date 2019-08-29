var pageNo = 0;
var pageSize = 4;
var dataArr = [];
var num = 0;

var type = 0;
var projectIdTop="";
var chartDom = $(".pie-list .pie-wrap");
var pieChart = ["pieChart1","pieChart2","pieChart3","pieChart4"];

var policyType = "";
var jsmsType = "";
var projectId="";
var colorList = ['#ef8947','#5390ef','rgb(161,181,48)','rgb(157,147,128)','#E5367E','#609F9E','#FF6666','#CC99CC','#747F9F','#149F2E','#909F4F','#9F6D20','#6C9F8A'];

var empNo = "";
var empPsw = "";
var pieFinishArr = [];
$(function(){
    ajaxPost("companyIndexApi/listNewsType.do","",0);//
    topChart();
    pointPie();
    topProjrctList();

    policy();
    rzmsList();
    projectList();
    $(".login-ways li").click(function(){
        $(".login-ways li p").removeClass("tab-current");
        $(this).find("p").addClass("tab-current");
        var a = $(this).index();
        if(a==0){
            $(".login-inform").removeClass("show").addClass("hide");
            $(".login-code").removeClass("hide").addClass("show");
          //  ajaxPost("companyIndexApi/qrCode.do","",2);//生成二维码接口
        }else{
            $(".login-code").removeClass("show").addClass("hide");
            $(".login-inform").removeClass("hide").addClass("show");
        }
    });
    //登录
    $(".login-btn").click(function(){
        empNo = $(".login-act").val();
        empPsw = $(".login-pswd").val();
        if(!empNo){
            $(".login-inform span").html("请输入用户名").addClass("show").removeClass("hide");
            $(".login-act").focus();
            return;
        }else if(!empPsw){
            $(".login-inform span").html("请输入密码").addClass("show").removeClass("hide");
            $(".login-pswd").focus();
            $(".null-tip").show();
            return;
        }
        ajaxPost("companyIndexApi/login.do",{"empNo":empNo,"empPsw":empPsw},3);
        //登录提示
        $("body").mLoading({
         text:"登录中请等待...",//加载文字，默认值：加载中...
        // icon:"",//加载图标，默认值：一个小型的base64的gif图片
         html:false,//设置加载内容是否是html格式，默认值是false
         content:"",//忽略icon和text的值，直接在加载框中显示此值
         mask:true//是否显示遮罩效果，默认显示
         });
    });

    $("body").bind("click", function(){
        $(".select-option").removeClass("show");
        $(".select-option").addClass("hide");
    });

    $(".select-type .top-select").click(function (){
        $(".select-type .top-select").removeClass("slect-active");
        $(this).addClass("slect-active");
        type = $(this).attr("val");
        $(".finish-amount span").css("border","none");
        $(".finish-amount span:first-child").css("border-bottom","1px solid #99CCCC");
        topChart();
        topProjrctList();
    });

    $(".down-condition li a").click(function(){
        $(".select-option").addClass("hide");
        $(this).next().removeClass("hide");
        $(this).next().addClass("show");
        return false;
    });
    $(".select-option li").click(function(){
        $(this).attr("val");
        $(".select-option").addClass("hide");
    });

    $(".finish-amount span").click(function (){
        $(".finish-amount span").css("border","none");
        $(this).css("border-bottom","1px solid #99CCCC");
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
    
    //注册
    $(".login-btn_group li").click(function () {
        var thisIndex = $(this).index();
        if(thisIndex==0){
            window.location.href= loginUrl+"/register.do";
        }
    });

    //找回密码
    $(".login-forgetpsd a").click(function () {
        window.location.href= loginUrl+"/html/forgetPsw.html";
    });
});
function handleData(data,index,typeId) {
    if(index==0){
        if(data.backCode == 0){
            var str = "";
            var str2 = "";
            $.each(data.bean,function(k,p){
                if(k<7){
                    if(k==0){
                        str +="<li class='current' val="+p.dicVal1+"><a>"+p.dicVal2+"</a></li>";
                        str2 += "<div class="+p.dicVal1+"><ul></ul></div>";
                        ajaxPost("companyIndexApi/listNewsByType.do",{"type":p.dicVal1},1,"",p.dicVal1);//通知公告类型
                    }else{
                        str +="<li val="+p.dicVal1+"><a>"+p.dicVal2+"</a></li>";
                        str2 += "<div class='hide "+p.dicVal1+"'><ul></ul></div>";
                    }

                }
            });
            $(".tab_menu").html(str);
            $(".tab_box").html(str2);
            $('.login-left_tab').Tabs({
                auto:5000
            });
        }else{
            console.log(data.errorMess);
        }
    }
    if(index==1){
        var str3 = "";
        $.each(data.bean.records, function(k,p) {
            p.newsShort = p.newsShort.substr(0,95)+"...";
            str3 += '<li owid='+p.owid+'><div class="tab_box-time"><span>'+p.newsTime.substr(8,2)+'</span><em>'+p.newsTime.substr(0,7)+'</em></div><div class="tab_box-content">';
            str3 +='<p title='+p.newsTitle+'>'+p.newsTitle+'</p><h5>'+p.newsShort+'</h5></div></li>';
        });
        str3 += '<a href="loginDetail.html?type='+typeId+'" ><input type="button" class="orange-linear tab_box-btn" value="更多 +"/></a>';
        $("."+typeId).find("ul").html(str3);
        $(".tab_box li").click(function(){
            var owid = $(this).attr("owid");
            window.location.href = 'loginDetail.html?type='+typeId+'&owid='+owid+'';
        })
    }

    if(index==3){//登录
        if(data.backCode == 0){
            addCookie("empNo",empNo);
            document.getElementById("loginPage").src=loginUrl+data.bean;
            //$("body").mLoading("hide");
        }else{
            $(".login-inform span").html(data.errorMess).addClass("show").removeClass("hide");
            $("body").mLoading("hide");
        }
    }
}

function topChart() {
    var urlArr = ["indexReportApi/policyTotal.do","indexReportApi/rzmsTotal.do","indexReportApi/tzgcTotal.do","indexReportApi/wcjdTotal.do"];
    var paradata = {
        "type":type,
        "projectId":projectIdTop,
    };
    var params = {
        "data":JSON.stringify(paradata),
        timestamp : new Date().getTime()
    };
    $.each(chartDom,function(k,p){
        pieChart[k] = echarts.init(p);
        pieChart[k].showLoading({ color: '#9ec0c7'});
        $.post(urlApi+urlArr[k], params, function(data) {
            // window.parent.ajax(urlArr[k],params,function(data){
            pieChart[k].hideLoading();
            if(data.backCode == 0) {
                if(k==0){
                    var str1 ='<tr><td>政策数量</td><td><span>'+data.bean.total+'</span><label>个</label></td></tr><tr><td>投资规模</td><td><span>'+data.bean.tzgm+'</span><label>亿</label></td> </tr>';
                    $("#xmzc").html(str1);
                }
                if(k==1){
                    var str2 ='<tr><td>模式数量</td><td><span>'+data.bean.total+'</span><label>个</label></td></tr><tr><td>建设规模</td><td><span>'+data.bean.tzgm+'</span><label>亿</label></td> </tr>';
                    $("#jsms").html(str2);
                }
                if(k==2){
                    var str3 ='<tr><td>项目数量</td><td><span>'+data.bean.total+'</span><label>个</label></td></tr><tr><td>投资规模</td><td><span>'+data.bean.tzgm+'</span><label>亿</label></td> </tr>';
                    $("#tzgc").html(str3);
                }
                if(k==3){
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
                        center: ['50%', '52%'],
                        color: colorList,
                        data: dataPie,
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
function topProjrctList() {
    var paradata = {
        "projectType":type,
    };
    var params = {
        "data":JSON.stringify(paradata),
        timestamp : new Date().getTime()
    };
    $.post(urlApi+"indexReportApi/projectPieList.do", params, function(data) {
        if(data.backCode == 0) {
            if(data.bean.length>0){
                projectIdTop="";
                var str = "<li val = ''>项目查询</li>";
                $.each(data.bean,function (k,p) {
                    str += "<li val = "+p.owid+">"+p.projectTitle+"</li>";
                });
                $("#top-project_list").html('<a>项目查询</a><ul class="select-option">'+str+"</ul>");
            }else{
                projectIdTop="";
                $("#top-project_list").css("background-image","none");
                $("#top-project_list").html('<a>暂无项目</a>');
            }
        };
        $("#top-project_list li").click(function () {
            var val = $(this).attr("val");
            var text = $(this).html();
            projectIdTop = val;
            $("#top-project_list a").html(text);
            $(".select-option").removeClass("show");
            $(".select-option").addClass("hide");
            $(".finish-amount span").css("border","none");
            $(".finish-amount span:first-child").css("border-bottom","1px solid #99CCCC");
            topChart();
        });

        $("#top-project_list a").click(function(){
            $(".select-option").addClass("hide");
            $(this).next(".select-option").removeClass("hide");
            $(this).next().addClass("show");
            event.stopPropagation();
        });
    });
}
var thisUrl = ["indexReportApi/policyList.do","indexReportApi/rzmsList.do","indexReportApi/projectList.do"];
function policy() {
    var params = {
        timestamp : new Date().getTime()
    };
    $.post(urlApi+thisUrl[0], params, function(data) {
        if(data.backCode == 0) {
            if(data.bean.length>0){
                str = "<li val = ''>项目政策</li>";
                $.each(data.bean,function (k,p) {
                    str += "<li val = "+p.path+">"+p.policyTitle+"</li>";
                });
                $("#plocy-list").html('<a>项目政策</a><ul class="select-option">'+str+"</ul>");
            }else{
                policyType="";
                $("#plocy-list").css("background-image","none");
                $("#plocy-list").html('<a>暂无项目政策</a>');
            }
        };
        $("#plocy-list li").click(function () {
            var val = $(this).attr("val");
            var text = $(this).html();
            policyType = val;
            $("#plocy-list a").html(text);
            $(".select-option").addClass("hide");
            pointPie();
            projectList();
        });
        thisClick("plocy-list");
    });

}

function rzmsList() {
    var params = {
        timestamp : new Date().getTime()
    };
    $.post(urlApi+thisUrl[1], params, function(data) {
        if(data.backCode == 0) {
            var str = "";
            if(data.bean.length>0) {
                str = "<li val = ''>建设模式</li>";
                $.each(data.bean, function (k, p) {
                    str += "<li val = " + p.dicVal1 + " >" + p.dicVal2 + "</li>";
                });
                $("#conMode-list").html('<a>建设模式</a>' + '<ul class="select-option">' + str + "</ul>");
            }else{
                jsmsType="";
                $("#conMode-list").css("background-image","none");
                $("#conMode-list").html('<a>暂无建设模式</a>');
            }
        }
        $("#conMode-list li").click(function () {
            var val = $(this).attr("val");
            var text = $(this).html();
            jsmsType = val;
            $("#conMode-list a").html(text);
            $(".select-option").addClass("hide");
            pointPie();
            projectList();

        });
        thisClick("conMode-list");
    });
}

function projectList() {
    var paradata = {
        "policyType":policyType,
        "jsmsType":jsmsType,
    };
    var params = {
        "data":JSON.stringify(paradata),
        timestamp : new Date().getTime()
    };
    $.post(urlApi+thisUrl[2], params, function(data) {
        if(data.backCode == 0) {
            if(data.bean.length>0){
                var str = "<li val = ''>项目查询</li>";
                $.each(data.bean,function (k,p) {
                    str += "<li val = "+p.owid+">"+p.projectTitle+"</li>";
                });
                $("#project-list").html('<a>项目查询</a><ul class="select-option">'+str+"</ul>");
            }else{
                projectId="";
                $("#project-list a:after").css("background-image","url('')");
                $("#project-list").html('<a>无符合条件项目</a>');
            }

            $("#project-list li").click(function(){
                var val = $(this).attr("val");
                var text = $(this).html();
                projectId = val;
                $("#project-list a").html(text);
                $(".select-option").addClass("hide");
                pointPie();
            });
            thisClick("project-list");
        };

    });
}
function pointPie() {
    var paradata = {
        "policyType":policyType,
        "jsmsType":jsmsType,
        "projectId":projectId,
    };
    var params = {
        "data":JSON.stringify(paradata),
        timestamp : new Date().getTime()
    };
    var barChart = echarts.init(document.getElementById('indexpoint'));
    var legendData = [];
    var seriesData = [];
    barChart.showLoading({ color: '#9ec0c7'});
    $.post(urlApi+"indexReportApi/projectProcessReport.do", params, function(data) {
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
                            color: colorList[k],
                            label: {
                                show: true,
                                textStyle: {
                                    color:  colorList[k],
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
                    y:40,
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
                            res += '<br/><span style="color:' + colorList[i] + '">' + params[i].seriesName + ' &nbsp;&nbsp;&nbsp;&nbsp;';
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
    })
}
function thisClick(paras) {
    $("#"+paras+" a").click(function(){
        $(".select-option").addClass("hide");
        $(this).next(".select-option").removeClass("hide");
        $(this).next().addClass("show");
        event.stopPropagation();
    });
}

$(".down-condition").click(function (){
    $(".select-option").removeClass("show");
    $(".select-option").addClass("hide");
});

function goNext(url){
    window.location.href = url;
}

function displayImg(imgurl){
    $(".login-code").html('<img src='+imgurl+' />');
}

function KeyDown()
{
    if (event.keyCode == 13)
    {
        empNo = $(".login-act").val();
        empPsw = $(".login-pswd").val();
        if(!empNo){
            $(".login-inform span").html("请输入用户名").addClass("show").removeClass("hide");
            $(".login-act").focus();
            return;
        }else if(!empPsw){
            $(".login-inform span").html("请输入密码").addClass("show").removeClass("hide");
            $(".login-pswd").focus();
            $(".null-tip").show();
            return;
        }
        ajaxPost("companyIndexApi/login.do",{"empNo":empNo,"empPsw":empPsw},3);
        $(".login-btn").val("登录中...");
    }
}