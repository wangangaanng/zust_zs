var pageNo = 0;
var pageSize = 4;
var num = 0;

var type = 0;
var chartDom = $(".pie-list .pie-wrap");
var pieChart = ["pieChart1", "pieChart2", "pieChart3", "pieChart4"];

var projectId = "";
var colorList = ['#43cfa9', '#43a9cf','#3e8be2','#2b63f0','#5144fd',"#aa4ef8",'#f84ebe','#ff635e','#ff8746','#ffb22d','#fed755','#92cf43','#2fa4f5',"#7089fa"];
var colorLine =["#ff7800","#009cff"]
var empNo = "";
var empPsw = "";
var pieFinishArr = [];
var dataPie = [];

var nullThisTip = '<a class="wrap-list">暂无</a>';
var projectTitle = "";//标题
var projectTopDeptPath = "";//主管部门
var projectReginCode = "";//地区
var projectFinancMode = "";//建设模式
var projectLevel = "";//项目级次
var projectPolicyPath = "";//项目政策
var projectIndustry = "";//项目工程类别

var menuId = "";//政策类型id
$(function () {
    document.getElementById("loginPage").location=loginUrl+"login.do";
    ajaxPost("companyIndexApi/listNewsType.do", {"cityId": cityId}, 0); //获取新闻type
    ajaxPost("systemApi/systemName.do", {"cityId": cityId}, 9);//获取系统名称
    pointPie();
    pointLine();
    localStorage.setItem("cityId",cityId);

    $(".login-ways .login-icon").click(function () {
        $(".login-tip").addClass("hide").removeClass("show");
        var thisParent = $(this).parents(".way-wrap");
        thisParent.hide().siblings().show();
        var a = thisParent.index();
        if (a == 0) {
            $(".login-inform").removeClass("show").addClass("hide");
            $(".login-code").removeClass("hide").addClass("show");
            //  ajaxPost("companyIndexApi/qrCode.do","",2);//生成二维码接口
        } else {
            $(".login-code").removeClass("show").addClass("hide");
            $(".login-inform").removeClass("hide").addClass("show");
        }
    });
    //登录
    $(".login-btn").click(function () {
        empNo = $(".login-act").val();
        empPsw = $(".login-pswd").val();
        if (!empNo) {
            $(".login-tip").html("请输入用户名").addClass("show").removeClass("hide");
            $(".login-act").focus();
            return;
        } else if (!empPsw) {
            $(".login-tip").html("请输入密码").addClass("show").removeClass("hide");
            $(".login-pswd").focus();
            $(".null-tip").show();
            return;
        }
        ajaxPost("companyIndexApi/login.do", {"empNo": empNo, "empPsw": empPsw, "cityId": cityId}, 2);
        //登录提示
        $("body").mLoading({
            text: "登录中请等待...",//加载文字，默认值：加载中...
            // icon:"",//加载图标，默认值：一个小型的base64的gif图片
            html: false,//设置加载内容是否是html格式，默认值是false
            content: "",//忽略icon和text的值，直接在加载框中显示此值
            mask: true//是否显示遮罩效果，默认显示
        });
    });

    //项目类型选择
    $(".select-type .top-select").click(function () {
        $(".select-type .top-select").removeClass("slect-active");
        $(this).addClass("slect-active");
        type = $(this).attr("val");
        $(".finish-amount span").css("border", "none");
        $(".finish-amount span:first-child").css("color", "#595757!important");
        projectId = "";
        pointPie();
        pointLine();
    });

    //项目进度 资金进度
    $(".finish-amount span").click(function () {
        $(".finish-amount span").removeClass("tab-selected");
        $(this).addClass("tab-selected");
        var thisindex = $(this).attr("val");
        if (pieFinishArr[thisindex].length > 0) {
            pieOption.series[0].data = pieFinishArr[thisindex];
            var thispie = echarts.init(chartDom[3])
            thispie.setOption(pieOption);
            window.onresize = thispie.resize;
        } else {
            $(chartDom[3]).removeAttr("_echarts_instance_");
            $(chartDom[3]).html(nullTip);
        }
    });

    //找回密码
    $(".login-forgetpsd a").click(function () {
        window.open("forgetPsw.htm?cityId=" + cityId);
    });
    //申报指南
    $(".login-use a").click(function () {
        window.open("http://www.qzsjcloud.com:8081/20180925/1537843909861.pdf");
    });

    //点击更多
    $("body").on("click",".tab_box-btn",function () {
        window.open( 'loginNewDetail.htm?type=' + menuId);
    });

    //二维码登陆
    connectionWithLogin(webSocketUrl,webSocketSocketUrl,scence);
});
function handleData(data, index, typeId) {
    if (index == 0) {
        if (data.backCode == 0) {
            var str = "";
            var str2 = "";
            $.each(data.bean, function (k, p) {
                if (k < 7) {
                    if (k == 0) {
                        menuId = p.dicVal1;
                        str += "<li class='current' val=" + p.dicVal1 + "><a>" + p.dicVal2 + "</a></li>";
                        str2 += "<div class=" + p.dicVal1 + "><ul></ul></div>";
                        ajaxPost("companyIndexApi/listNewsByType.do", {"type": p.dicVal1,"cityId":cityId}, 1, "", p.dicVal1);//通知公告类型
                    } else {
                        str += "<li val=" + p.dicVal1 + "><a>" + p.dicVal2 + "</a></li>";
                        str2 += "<div class='hide " + p.dicVal1 + "'><ul></ul></div>";
                    }

                }
            });
            $(".tab_menu").html(str);
            $(".tab_box").html(str2);
            //点击tab 调用接口
            $(".tab_menu li").click(function () {
                menuId = $(this).attr("val");
                $(this).addClass("current").siblings().removeClass("current");
                ajaxPost("companyIndexApi/listNewsByType.do",{"type":menuId,"cityId":cityId},1,"",menuId);//通知公告类型
            });
        } else {
            console.log(data.errorMess);
        }
    }
    if (index == 1) {
        var str3 = "";
        if(data.bean.records.length>0){
            $.each(data.bean.records, function (k, p) {
                if (k < 4) {
                    p.newsShort = p.newsShort.substr(0, 95) + "...";
                    str3 += '<li owid=' + p.owid + '><div class="tab_box-time"><span>' + p.newsTime.substr(8, 2) + '</span><em>' + p.newsTime.substr(0, 7) + '</em></div><div class="tab_box-content">';
                    str3 += '<p title=' + p.newsTitle + '>' + p.newsTitle + '</p><h5>' + p.newsShort + '</h5></div></li>';
                }
            });
            $("." + typeId).find("ul").html(str3);
        }else{
            $("." + typeId).find("ul").html("<div class = 'null-tip' style='padding-top: 80px!important;'><img src = 'html/img/index_nulltip.png' style='margin-top: 0px;'/><p style='margin-top: 10px;border-bottom: none'>还没有新消息</p></div>");
        }
        $("." + typeId).show().siblings().hide();
        // str3 += '<a href="loginDetail.html?type=' + typeId + '" ><input type="button" class="orange-linear tab_box-btn" value="更多 +"/></a>';
        //点击列表跳转到详情
        $(".tab_box li").click(function () {
            var owid = $(this).attr("owid");
            window.open( 'loginNewDetail.htm?type=' + typeId + '&owid=' + owid);
        });
    }
    if (index == 2) {//登录
        if (data.backCode == 0) {
            addCookie("empNo", empNo);
            window.location.href=loginUrl+data.bean;
            //document.getElementById("loginPage").location.href = loginUrl + data.bean;
        } else {
            $(".login-tip").html(data.errorMess).addClass("show").removeClass("hide");
            $("body").mLoading("hide");
        }
    }

    if(index==9){
        //获取系统名称
        if (data.backCode == 0) {
            if (null!=data.bean) {
                localStorage.setItem("systemName",data.bean.dicVal2);
                $(".login-header_content p").html(data.bean.dicVal2);
            }
        }


    }
}

function pointPie(isAll) {
    if (!isAll) {
        isAll = false
    }
    var urlArr = ["center/projectreport/indexReportApi/policyTotal", "center/projectreport/indexReportApi/rzmsTotal", "center/projectreport/indexReportApi/tzgcTotal", "center/projectreport/indexReportApi/wcjdTotal"];
    var paradata = {
        "type": type,
        "isAll": isAll,
        "projectId": projectId,
        "projectTitle": projectTitle,
        "projectTopDeptPath": projectTopDeptPath,
        "projectReginCode": projectReginCode,
        "projectFinancMode": projectFinancMode,
        "projectLevel": projectLevel,
        "projectPolicyPath": projectPolicyPath,
        "projectIndustry": projectIndustry,
        "cityId":cityId
    };
    var params = {
        "data": JSON.stringify(paradata),
        "method":"",
        timestamp: new Date().getTime()
    };
    $.each(chartDom, function (k, p) {
        pieChart[k] = echarts.init(p);
        pieChart[k].showLoading({color: '#9ec0c7'});
        params.method = urlArr[k];
        $.post(loginUrl+"system/commonApi.htm", params, function (data) {
            pieChart[k].hideLoading();
            if (data.backCode == 0) {
                if (k == 0) {
                    colorList = ['#43cfa9', '#43a9cf','#3e8be2','#2b63f0','#5144fd',"#aa4ef8",'#f84ebe','#ff635e','#ff8746','#ffb22d','#fed755','#92cf43','#2fa4f5',"#7089fa"];
                    var str1 = '<tr><td>政策数量</td><td><span>' + data.bean.total + '</span><label>个</label></td></tr><tr><td>投资规模</td><td><span>' + data.bean.tzgm + '</span><label>亿</label></td> </tr>';
                    $("#xmzc").html(str1);
                }
                if (k == 1) {
                    colorList = ["#2b63f0","#5144fd","#aa4ef8","#f84ebe","#ffb22d",'#fed755',"#92cf43","#43cfa9","#ff8746","#ff635e"];
                    var str2 = '<tr><td>模式数量</td><td><span>' + data.bean.total + '</span><label>个</label></td></tr><tr><td>建设规模</td><td><span>' + data.bean.tzgm + '</span><label>亿</label></td> </tr>';
                    $("#jsms").html(str2);
                }
                if (k == 2) {
                    colorList = ["#92cf43","#f84ebe","#ff635e","#ff8746","#ffb22d"];
                    var str3 = '<tr><td>项目数量</td><td><span>' + data.bean.total + '</span><label>个</label></td></tr><tr><td>投资规模</td><td><span>' + data.bean.tzgm + '</span><label>亿</label></td> </tr>';
                    $("#tzgc").html(str3);
                }
                if (k == 3) {
                    colorList = ["#009cff","#ffb22d","#ff635e"]
                    pieChart[k] = echarts.init(p);
                    pieFinishArr[0] = data.bean.gcPie;
                    pieFinishArr[1] = data.bean.zjPie;
                    if (pieFinishArr[0].length > 0) {
                        dataPie = pieFinishArr[0];
                    } else {
                        $(chartDom[k]).removeAttr("_echarts_instance_");
                        $(chartDom[k]).html(nullTip);
                    }
                    var str4 = '<tr><td>施工数量</td><td><span>' + data.bean.sgsl + '</span><label>个</label></td><td>完成投资</td><td><span>' + data.bean.zftz + '</span><label>亿</label></td></tr>';
                    str4 += '<tr><td>投资拨款</td><td><span id="jsgm">' + data.bean.jsgm + '</span><label>亿</label></td><td>投资进度</td><td><span id="tzjd">' + data.bean.tzjd + '</span><label>%</label></td></tr>';
                    $("#jd").html(str4);
                } else {
                    if (data.bean.pies.length > 0) {
                        dataPie = data.bean.pies;
                    } else {
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
                        radius: ['30%', '60%'],
                        center: ['50%', '52%'],
                        color: colorList,
                        data: dataPie,
                        label: {
                            normal: {
                                // textStyle: {
                                //     color: '#7c7c7c'
                                // },
                                formatter: function (param) {
                                    return +Math.round(param.percent) + '%';
                                }

                            }
                        },
                        labelLine: {
                            normal: {
                                // lineStyle: {
                                //     color: '#7c7c7c'
                                // },
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
                                label: {show: true, formatter: '{b} \n ({d}%)', textStyle: {}}
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
//8大流程
function pointLine(isAll) {
    if (!isAll) {
        isAll = false
    }
    var paradata = {
        "type": type,
        "isAll": isAll,
        "projectId": projectId,
        "projectTitle": projectTitle,
        "projectTopDeptPath": projectTopDeptPath,
        "projectReginCode": projectReginCode,
        "projectFinancMode": projectFinancMode,
        "projectLevel": projectLevel,
        "projectPolicyPath": projectPolicyPath,
        "projectIndustry": projectIndustry,
        "cityId":cityId
    };
    var params = {
        "data": JSON.stringify(paradata),
        timestamp: new Date().getTime()
    };
    var barChart = echarts.init(document.getElementById('indexpoint'));
    var legendData = [];
    var seriesData = [];
    barChart.showLoading({color: '#9ec0c7'});
    $.post(urlApi + "indexReportApi/projectProcessReport.do", params, function (data) {
        barChart.hideLoading();
        if (data.backCode == 0) {
            var nameArr = ["项目信息", "项目手续", "项目投资", "招标采购", "合同备案", "项目进度", "竣工验收", "绩效评价"];
            var str = "<div class='chart-bottom_title'><p>项目数量</p><p>流程选项</p><p>项目流程</p></div><ul>"
            $.each(data.bean.mksl, function (k, p) {
                str += "<li><span>" + data.bean.xmsl[k] + "</span>" +
                    "<span>" + p + "</span>" +
                    "<span>" + nameArr[k] + "</span></li>";
            });
            str += "</ul>";
            $(".chart-bottom").html(str);

            var nameArr = ["流程选项", "流程进度"];
            legendData = nameArr;
            var lineData = [data.bean.lcxx, data.bean.lxjd];
            $.each(nameArr, function (k, p) {
                seriesData.push({
                    name: p,
                    type: 'line',
                    itemStyle: {
                        normal: {
                            color: colorLine[k],
                            label: {
                                show: true,
                                textStyle: {
                                    color: colorLine[k],
                                    fontSize: 13,
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
                    y: 46,
                    y2: 5,
                    x: 60,
                    x2: 20
                },
                tooltip: {
                    trigger: 'axis',
                    backgroundColor: 'rgba(255,255,255,0.7)',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: function (params) {
                        var res = '<div style="border:1px solid #009cff;padding:10px;">';
                        res += '<strong style="color:#3e3a39;">' + params[0].name + '</strong>';
                        var k = "";
                        for (var i = 0, l = params.length; i < l; i++) {
                            res += '<br/><span style="color:' + colorLine[i] + '">' + params[i].seriesName + ' &nbsp;&nbsp;&nbsp;&nbsp;';
                            if (i == 1) {
                                if (params[0].name == "项目信息") {
                                    k = 0
                                }
                                ;
                                if (params[0].name == "项目手续") {
                                    k = 1
                                }
                                ;
                                if (params[0].name == "项目投资") {
                                    k = 2
                                }
                                ;
                                if (params[0].name == "招标采购") {
                                    k = 3
                                }
                                ;
                                if (params[0].name == "合同备案") {
                                    k = 4
                                }
                                ;
                                if (params[0].name == "项目进度") {
                                    k = 5
                                }
                                ;
                                if (params[0].name == "竣工验收") {
                                    k = 6
                                }
                                ;
                                if (params[0].name == "绩效评价") {
                                    k = 7
                                }
                                ;
                                res += '<span style="color:#000;font-size:12px;"> ' + params[i].value + '(' + data.bean.pjwcd[k] + '%)</span>';
                            } else {
                                res += '<span style="color:#000;font-size:12px;"> ' + params[i].value + '</span>';
                            }
                            res += '</span>';
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
                    textStyle: {
                        fontSize: 13
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
                        axisLine: axisLine,
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        axisTick: {
                            inside: true
                        },
                        axisLine: axisLine,
                    }
                ],
                series: seriesData,
            };
            barChart.setOption(option5);
            window.onresize = barChart.resize;
        }
    })
}


function displayImg(imgurl) {
    $(".login-code").html('<img src=' + imgurl + ' />');
}

//按键 enter登录
function KeyDown() {
    if (event.keyCode == 13) {
        empNo = $(".login-act").val();
        empPsw = $(".login-pswd").val();
        if (!empNo) {
            $(".login-right_form span").html("请输入用户名").addClass("show").removeClass("hide");
            $(".login-act").focus();
            return;
        } else if (!empPsw) {
            $(".login-right_form span").html("请输入密码").addClass("show").removeClass("hide");
            $(".login-pswd").focus();
            $(".null-tip").show();
            return;
        }
        ajaxPost("companyIndexApi/login.do", {"empNo": empNo, "empPsw": empPsw,"cityId":cityId}, 2);
        $("body").mLoading();
    }
}




