var currPage = 1;
var pageSize = 10;
var moreFlag = false;
//var name="2017zszy";
var name="zsdt";
var xsts = 10;//记录学院动态显示条数
var res = 0;
var lb = "";//新闻关键字
$(document).ready(function(){
	init();
});

function init()
{
	//初始化招生专业
	init_zszy();

	//获取学院介绍
	// init_xyIntro();
	//初始化查询选项
	// init_chaXun();
    $(".zylb").find("li").click(function(){
        $(".zylb_li_a").parent().css("background-color","white");
        $(".zylb_li_a").css("color","rgb(150,150,150)");
        $(this).css("background-color","#008784");
        $(this).find("a").css("color","white");
    });
	$(".jhcx").click(function(){
		$(".jhcx_form").css("display","block");
		$(".cjcx_form").css("display","none");
		$(".lqcx_form").css("display","none");
		$(".lncx_form").css("display","none");
		$(".cjcx_icon").children().eq(0).css("color","rgb(184,184,184)");
		$(".cjcx_icon").css("background-color","white");
		$(".cjcx_icon").next().css("color","rgb(184,184,184)");
		$(".cjcx_icon").css("border-color","rgb(184,184,184)");
		$(".lqcx_icon").children().eq(0).css("color","rgb(184,184,184)");
		$(".lqcx_icon").css("background-color","white");
		$(".lqcx_icon").next().css("color","rgb(184,184,184)");
		$(".lqcx_icon").css("border-color","rgb(184,184,184)");
		$(".lncx_icon").children().eq(0).css("color","rgb(184,184,184)");
		$(".lncx_icon").css("background-color","white");
		$(".lncx_icon").next().css("color","rgb(184,184,184)");
		$(".lncx_icon").css("border-color","rgb(184,184,184)");
		$(".jhcx_icon").children().eq(0).css("color","white");
		$(".jhcx_icon").css("border-color","rgb(31,26,23)");
		$(".jhcx_icon").css("background-color","rgb(31,26,23)");
		$(".jhcx_icon").next().css("color","rgb(31,26,23)");
	});
	$(".cjcx").click(function(){
		$(".jhcx_form").css("display","none");
		$(".cjcx_form").css("display","block");
		$(".lqcx_form").css("display","none");
		$(".lncx_form").css("display","none");
		$(".jhcx_icon").children().eq(0).css("color","rgb(184,184,184)");
		$(".jhcx_icon").css("background-color","white");
		$(".jhcx_icon").next().css("color","rgb(184,184,184)");
		$(".jhcx_icon").css("border-color","rgb(184,184,184)");
		$(".lqcx_icon").children().eq(0).css("color","rgb(184,184,184)");
		$(".lqcx_icon").css("background-color","white");
		$(".lqcx_icon").next().css("color","rgb(184,184,184)");
		$(".lqcx_icon").css("border-color","rgb(184,184,184)");
		$(".lncx_icon").children().eq(0).css("color","rgb(184,184,184)");
		$(".lncx_icon").css("background-color","white");
		$(".lncx_icon").next().css("color","rgb(184,184,184)");
		$(".lncx_icon").css("border-color","rgb(184,184,184)");
		$(".cjcx_icon").children().eq(0).css("color","white");
		$(".cjcx_icon").css("border-color","rgb(31,26,23)");
		$(".cjcx_icon").css("background-color","rgb(31,26,23)");
		$(".cjcx_icon").next().css("color","rgb(31,26,23)");
	});
	$(".lqcx").click(function(){
		$(".jhcx_form").css("display","none");
		$(".cjcx_form").css("display","none");
		$(".lqcx_form").css("display","block");
		$(".lncx_form").css("display","none");
		$(".jhcx_icon").children().eq(0).css("color","rgb(184,184,184)");
		$(".jhcx_icon").css("background-color","white");
		$(".jhcx_icon").next().css("color","rgb(184,184,184)");
		$(".jhcx_icon").css("border-color","rgb(184,184,184)");
		$(".cjcx_icon").children().eq(0).css("color","rgb(184,184,184)");
		$(".cjcx_icon").css("background-color","white");
		$(".cjcx_icon").next().css("color","rgb(184,184,184)");
		$(".cjcx_icon").css("border-color","rgb(184,184,184)");
		$(".lncx_icon").children().eq(0).css("color","rgb(184,184,184)");
		$(".lncx_icon").css("background-color","white");
		$(".lncx_icon").next().css("color","rgb(184,184,184)");
		$(".lncx_icon").css("border-color","rgb(184,184,184)");
		$(".lqcx_icon").children().eq(0).css("color","white");
		$(".lqcx_icon").css("border-color","rgb(31,26,23)");
		$(".lqcx_icon").css("background-color","rgb(31,26,23)");
		$(".lqcx_icon").next().css("color","rgb(31,26,23)");
	});
	$(".lncx").click(function(){
		$(".jhcx_form").css("display","none");
		$(".cjcx_form").css("display","none");
		$(".lqcx_form").css("display","none");
		$(".lncx_form").css("display","block");
		$(".jhcx_icon").children().eq(0).css("color","rgb(184,184,184)");
		$(".jhcx_icon").css("background-color","white");
		$(".jhcx_icon").next().css("color","rgb(184,184,184)");
		$(".jhcx_icon").css("border-color","rgb(184,184,184)");
		$(".cjcx_icon").children().eq(0).css("color","rgb(184,184,184)");
		$(".cjcx_icon").css("background-color","white");
		$(".cjcx_icon").next().css("color","rgb(184,184,184)");
		$(".cjcx_icon").css("border-color","rgb(184,184,184)");
		$(".lqcx_icon").children().eq(0).css("color","rgb(184,184,184)");
		$(".lqcx_icon").css("background-color","white");
		$(".lqcx_icon").next().css("color","rgb(184,184,184)");
		$(".lqcx_icon").css("border-color","rgb(184,184,184)");
		$(".lncx_icon").children().eq(0).css("color","white");
		$(".lncx_icon").css("border-color","rgb(31,26,23)");
		$(".lncx_icon").css("background-color","rgb(31,26,23)");
		$(".lncx_icon").next().css("color","rgb(31,26,23)");
	});
}


function init_lbt(){
	var url = "webAjaxAction!Lbtp.htm";
	var params = {
			"sessionId": _sessionid
	};
	$.post(url, params, function(data){
		if(data.backCode == 0){
			var x = "";
			var y = "";
			$.each(data.bean,function(j,k){
				if(j==0){
					x += "<li class='imgOn'><a href='#'><img style='height:400px' class='tplb_img' src='"+k.url+"'></a></li>";
					y += "<li class='indexOn'></li>";
				}
				else{
					x += "<li><a href='#'><img style='height:400px' class='tplb_img' src='"+k.url+"'></a></li>";
					y += "<li></li>";
				}
			});
			$(".imgList").append(x);
			$(".indexList").append(y);
			$(".indexList").css("width",(55*data.bean.length)+"px");
			$(".indexList").css("margin-right","auto");
			$(".indexList").css("margin-left","auto");
		}else{
			
		}
		slide();
	});
}

function getLabel(){
	var url = "webAjaxAction!zxxwLabel.htm";
	var params = {
	        "sessionId": _sessionid,
	        timestamp : new Date().getTime()
	    };
	$.post(url, params, function(data){
		if(data.backCode == 0){
			var str = "";
            $("#zylb").empty();
			$.each(data.bean,function(j,p){
				if(emptyCheck(p)){
			    str = "<li val='"+p.value+"' name='"+name+"' style='width: "+(16*p.length+20)+"px' onClick='changeNews(this)'><a class='zylb_li_a'>"+p.value+"</a></li>";
			    $("#zylb").append(str);
				}
			});
			$(".zylb").find("li").first().css("background-color","rgb(85,167,153)");
			$(".zylb").find("li").first().find("a").css("color","white");
			lb = $(".zylb").find("li").first().attr("val");
			$(".zylb").find("li").click(function(){
				$(".zylb_li_a").parent().css("background-color","white");
				$(".zylb_li_a").css("color","rgb(150,150,150)");
				$(this).css("background-color","rgb(85,167,153)");
				$(this).find("a").css("color","white");
			});
		}else {
		}
	});
}

function changeNews(obj){
	// lb = $(obj).attr("val");
	currPage=1;
	var type=$(obj).attr("type");
	if(emptyCheck($(obj).attr("name"))){
		name = $(obj).attr("name");
	}
	$(".dyn_details").empty();
	if(name=='zsdt'){//招生动态
        $("#zylbDiv").hide();
        $(".dyn_details").css("margin-top","30px");
		getList('119',1);//一级
	}else if(name=='zszy'){//招生专业
        $("#zylbDiv").show();
        $(".dyn_details").css("margin-top","0px");
		// getListOne('119');
	}else if(name=='zsjd'){//生源基地
        $("#zylbDiv").hide();
        $(".dyn_details").css("margin-top","30px");
        getList('126',2)//二级
    }
}
function init_details(name) {
    if(name=='zsdt'){//招生动态
        getList('119',1);//一级
    }else if(name=='zszy'){//招生专业
        // getListOne('119');
    }else if(name=='zsjd'){//生源基地
        getList('126',2)//二级
    }
}
function getList(lmbh,type) {
	var jsonObj=""
	var method=""
	if(type==1){//一级
        jsonObj = {
            "lmbh": lmbh,
            "pageNo":currPage,
            "pageSize":pageSize,
        };
        method="zustcommon/bckjBizArticle/searchByYjlm"
	}else if(type==2){//二级
        jsonObj = {
            "lmbh": lmbh,
			"wzzt":'1',
			"isDetail":"1",
            "pageNo":currPage,
            "pageSize":pageSize,
        };
        method="zustcommon/bckjBizArticle/getMuArticle"
    }
    ajax(method, jsonObj, function (data) {
        var x = "";
        if(data.backCode == 0){
            if((data.bean.records)&&(data.bean.records.length>0)){
                $.each(data.bean.records,function(j,k){
                    x = "<div class='detail' articleId='"+k.owid+"'><a href=''>";
                    if(emptyCheck(k.tpjj))
                        x += "<img class='detail_img' src='"+imagePath+k.tpjj+"'>";
                    else{
                        x += "<img class='detail_img' src='"+imagePath+"defaultImg.png'/>";
                    }
                    x += "<strong class='detail_str' title='"+k.wzbt+"'>"+k.wzbt+"</strong>";
                    x += "<p class='detail_p'>"+convertStr(k.jjnr,'暂无简介')+"</p></a>";
                    x += "<span class='glyphicon glyphicon-time'></span><p class='detail_date'>"+(convertStr(k.fbsj,'2017-01-01')).substr(0,10)+"</p>";
                    x += "<div class='rnum'>";
                    if(k.ydcs!="0"&&k.ydcs!=null)
                        x += "<span class='glyphicon glyphicon-eye-open'></span><p class='detail_num'>"+(parseInt(k.ydcs)+500)+"</p>";
                    else{
                        x += "<span class='glyphicon glyphicon-eye-open'></span><p class='detail_num'>"+parseInt(Math.random()*300+100)+"</p>";
                    }
                    x += "</div></div>";
                    $(".dyn_details").append(x);
                });
                currPage++;
                $.each($(".detail"),function(i){
                    var articleId = $(".detail:eq("+i+")").attr("articleId");
                    $(".detail:eq("+i+")").children().eq(0).attr("href",base+"/wzxq/"+articleId);
                    $(".detail:eq("+i+")").children().eq(0).attr("target","_blank");
                    if((i+1)%5==0)
                        $(".detail:eq("+i+")").css("margin-right","0px");
                });
			}
			if(currPage==data.bean.totalPage){
            	$(".detail_more").hide();
			}else{
                $(".detail_more").show();
			}
            moreFlag = true;
        }else if(data.backCode == 2){
            window.location="";
        }else{
            moreFlag = false;
        }
    })
}

function init_zszy(){
	init_details("zsdt");
	//查看更多按钮
	$(".detail_more").click(function(){
		if(!moreFlag){
			
		}else{
            init_details(name);
		}
	});
}

var timeout_hid_ggIntro;


function init_chaXun()
{
	var url = "webAjaxAction!changLnfs.htm";
	var params = {
			"sessionId": _sessionid,
			"fl": "nf"
	};
	var x = "";
	$.post(url,params,function(data){
		if(data.backCode==0){
			$.each(data.bean,function(j,k){
				if(k.length>=4)
					x = "<option value='"+k.substr(0,4)+"'>&nbsp;"+k+"</option>";
				$(".nf").append(x);
			});
		}else if(data.backCode == 2){
			window.location="";
		}else{
//			walert(data.errorMess);
		}
	});
	params = {
			"sessionId": _sessionid,
			"fl": "sf"
	};
	$.post(url,params,function(data){
		if(data.backCode==0){
			$.each(data.bean,function(j,k){
				x = "<option value='"+k+"'>&nbsp;"+k+"</option>";
				$(".sf").append(x);
			});
		}else if(data.backCode == 2){
			window.location="";
		}else{
//			walert(data.errorMess);
		}
	});
	params = {
			"sessionId": _sessionid,
			"fl": "kl"
	};
	$.post(url,params,function(data){
		if(data.backCode==0){
			$.each(data.bean,function(j,k){
				x = "<option value='"+k+"'>&nbsp;"+k+"</option>";
				$(".kl").append(x);
			});
		}else if(data.backCode == 2){
			window.location="";
		}else{
//			walert(data.errorMess);
		}
	});
	params = {
			"sessionId": _sessionid,
			"fl": "pc"
	};
	$.post(url,params,function(data){
		if(data.backCode==0){
			$.each(data.bean,function(j,k){
				x = "<option value='"+k+"'>&nbsp;"+k+"</option>";
				$(".pc").append(x);
			});
		}else if(data.backCode == 2){
			window.location="";
		}else{
			walert(data.errorMess);
		}
	});
	params = {
			"sessionId": _sessionid,
			"fl": "zy"
	};
	$.post(url,params,function(data){
		if(data.backCode==0){
			$.each(data.bean,function(j,k){
				x = "<option value='"+k+"'>&nbsp;"+k+"</option>";
				$(".zy").append(x);
			});
		}else if(data.backCode == 2){
			window.location="";
		}else{
			walert(data.errorMess);
		}
	});
}

var loadcs = 0;
var curPage = 1;
//执行查询操作
function jhcx_chaXun(){
	$("#mymodal").on('hidden.bs.modal',function(){
		loadcs = 0;
		curPage = 1;
	});
    $('#table-zsjh').bootstrapTable('destroy');
    if(curPage==1&&loadcs==0){
        $("#mymodal").modal("toggle");
    }
    $('#table-zsjh').bootstrapTable({
        ajax: function (request) {
            ajax("zustzs/bckjBizZsjh/getResult", {
                "nf": $("#jhcx_nf").val(),
				"sf": $("#jhcx_sf").val(),
				"kl": $("#jhcx_kl").val(),
				"pc": $("#jhcx_pc").val(),
				"zy": $("#jhcx_zy").val(),
                "pageNo": $('#table-zsjh').bootstrapTable('getOptions').pageNumber || 1,
                "pageSize": $('#table-zsjh').bootstrapTable('getOptions').pageSize || pageSize
            }, function (data) {
                if (data.backCode === 0) {
                    request.success({
                        row: convertStr(data.bean.records, []),
                        total: data.bean.totalCount
                    })
                }
            })
        },
        responseHandler:function(res){
            $('#table-zsjh').bootstrapTable('load', res.row);
            return {
                "total":res.total,
                "pageNumber":res.pageNumber,
                "pageSize":res.pageSize
            }
        },
        toolbar: '#toolbar', //工具按钮用哪个容器
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
        sortable: true, //是否启用排序
        sortOrder: "asc", //排序方式
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
        smartDisplay: false,
        search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: false, //是否显示所有的列
        showRefresh: false, //是否显示刷新按钮
        minimumCountColumns: 2, //最少允许的列数
        clickToSelect: true, //是否启用点击选中行
        uniqueId: "owid", //每一行的唯一标识，一般为主键列
        showToggle: false, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子表
        theadClasses: "thead1",
        queryParamsType:"limit",
        columns: [
            {
                align: 'center',
                field: 'nf',
                title: '年份'
            },
            {
                align: 'center',
                field: 'sf',
                title: '省份'
            },
            {
                align: 'center',
                field: 'kl',
                title: '科类'
            },
            {
                align: 'center',
                field: 'pc',
                title: '批次'
            },
            {
                align: 'center',
                field: 'zy',
                title: '专业'
            },
            {
                align: 'center',
                field: 'xz',
                title: '学制'
            },
            {
                align: 'center',
                field: 'zss',
                title: '招生数'
            },
            {
                align: 'center',
                field: 'xf',
                title: '学费/年'
            },
            {
                align: 'center',
                field: 'syxw',
                title: '授予学位'
            },
        ]
    });
}
function cjcx_chaXun(){
    var jsonObj = {
        "lmbh": lmbh,
        "wzzt":'1',
        "isDetail":"1",
        "pageNo":currPage,
        "pageSize":pageSize,
    };
	ajax(method, jsonObj, function (data) {
		var x = "";
		$(".cxRes").empty();
		$(".cxRes").next().remove();
		x = "<tr style='height:30px'><th>准考证号</th><th>身份证号</th><th >姓名</th><th>科目名称</th><th>成绩</th><th>录入时间</th><th>是否合格</th><th>备注或名次</th></tr>";
		if(data.backCode == 1){
			$.each(data.bean,function(j,k){
				x += "<tr style='height:30px'><td>"+k.ksh+"</td>";
				x += "<td>"+k.sfzh+"</td>";
				x += "<td>"+k.xm+"</td>";
				x += "<td>"+k.jtdz+"</td>";
				x += "<td>"+k.yw+"</td>";
				x += "<td>"+k.createtime.substr(0,10)+"</td>";
				x += "<td>"+k.mzdm+"</td>";
				if(k.bz!=null)
					x += "<td>"+k.bz+"</td>";
				else
					x += "<td>"+""+"</td>";
				x += "</tr>";
				$(".cxRes").append(x);
			});
			$("#mymodal").modal("toggle");
		}else if(data.backCode == 2){
			window.location="";
		}else{
			walert(data.errorMess);
		}
	});
}
function lqcx_chaXun(){
	var url = "webAjaxAction!lqcx.htm";
	var params = {
	        "sessionId": _sessionid,
	        "input_zkzh": $("#lqcx_zkzh").val(),
	        "input_sfzh": $("#lqcx_sfzh").val(),
	        "curPage": 0,
	        "itemsPerPage": 1
	    };
	$.post(url, params, function(data){
		var x = "";
		$(".cxRes").empty();
		$(".cxRes").next().remove();
		if(data.backCode == 1){
			if(data.bean!=null){
				x = "<div><label style='margin-left:30px;' class='lqInfo'>准考证号："+data.bean.ksh+"</label>";
				x += "<label style='margin-left:30px;' class='lqInfo'>身份证号："+data.bean.sfzh+"</label>";
				x += "<label style='margin-left:30px;' class='lqInfo'>姓名："+data.bean.xm+"</label></div>";
				if(data.bean.lxdh!=""&&data.bean.lxdh!=null)
					x += "<label class='lqInfo'>联系电话："+data.bean.lxdh+"</label>";
				$(".cxRes").parent().append(x);
				x = "<img style='float:left;margin:15px' src='"+IMAGEPATH+"smile.png'></img>";
				x += "<div style='float:left;' class='lqxx'><h4 style='color:red'>恭喜你</h4>";
				x += "<p>你已经被<label style='color:red'>浙江科技学院"+data.bean.lqzy+"</label>专业 预录取</p>";
				x += "<p style='color:red'>最终录取请查询当地考试院。</p>";
				x += "<p>EMS单号：<span  style='cursor: pointer' class='emsUrl' ems='"+data.bean.emsId+"'>"+data.bean.emsId+"</span>，请注意查收！</p></div>";
				$(".cxRes").append(x);
				$(".emsUrl").click(function () {
					var emsUrl=$(this).attr("ems");
					window.open("http://www.ems.com.cn/?mailNum="+emsUrl,"_blank");
				})
			}
			$("#mymodal").modal("toggle");
		}else if(data.backCode == 2){
			window.location="";
		}else{
			x = "<img style='float:left;margin:15px' src='"+IMAGEPATH+"depress.png'></img>";
			x += "<label style='margin-top:60px'>很抱歉，目前系统里没有你的录取信息，或者你的录取批次还未开始，请继续关注本网站公告，谢谢！</label>";
			$(".cxRes").append(x);
			$("#mymodal").modal("toggle");
//			walert(data.errorMess);
		}
	});
}
function lncx_chaXun(){
    $("#mymodal").on('hidden.bs.modal',function(){
        loadcs = 0;
        curPage = 1;
    });
    $('#table-zsjh').bootstrapTable('destroy');
    if(curPage==1&&loadcs==0){
        $("#mymodal").modal("toggle");
    }
    $('#table-zsjh').bootstrapTable({
        ajax: function (request) {
            ajax("zustzs/bckjBizLntj/getResult", {
                "nf": $("#lncx_nf").val(),
                "sf": $("#lncx_sf").val(),
                "kl": $("#lncx_kl").val(),
                "pc": $("#lncx_pc").val(),
                "zy": $("#lncx_zy").val(),
                "pageNo": $('#table-zsjh').bootstrapTable('getOptions').pageNumber || 1,
                "pageSize": $('#table-zsjh').bootstrapTable('getOptions').pageSize || pageSize
            }, function (data) {
                if (data.backCode === 0) {
                    request.success({
                        row: convertStr(data.bean.records, []),
                        total: data.bean.totalCount
                    })
                }
            })
        },
        responseHandler:function(res){
            $('#table-zsjh').bootstrapTable('load', res.row);
            return {
                "total":res.total,
                "pageNumber":res.pageNumber,
                "pageSize":res.pageSize
            }
        },
        toolbar: '#toolbar', //工具按钮用哪个容器
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
        sortable: true, //是否启用排序
        sortOrder: "asc", //排序方式
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
        smartDisplay: false,
        search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: false, //是否显示所有的列
        showRefresh: false, //是否显示刷新按钮
        minimumCountColumns: 2, //最少允许的列数
        clickToSelect: true, //是否启用点击选中行
        uniqueId: "owid", //每一行的唯一标识，一般为主键列
        showToggle: false, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子表
        theadClasses: "thead1",
        queryParamsType:"limit",
        columns: [
            {
                align: 'center',
                field: 'nf',
                title: '年份'
            },
            {
                align: 'center',
                field: 'sf',
                title: '省份'
            },
            {
                align: 'center',
                field: 'kl',
                title: '科类'
            },
            {
                align: 'center',
                field: 'pc',
                title: '批次'
            },
            {
                align: 'center',
                field: 'zy',
                title: '专业'
            },
            {
                align: 'center',
                field: 'xz',
                title: '学制'
            },
            {
                align: 'center',
                field: 'lqs',
                title: '录取数'
            },
            {
                align: 'center',
                field: 'zgf',
                title: '最高分'
            },
            {
                align: 'center',
                field: 'zdf',
                title: '最低分'
            },
            {
                align: 'center',
                field: 'pjf',
                title: '平均分'
            }
        ]
    });
}


//重置历年查询
function lncx_init() {
    var nf1 = "";
    var sf1 = "";
    var kl1 = "";
    var pc1 = "";
    var zy1 = "";
    var url = "zustzs/bckjBizLntj/getChanges";
    var params = {
        "nf" : nf1,
        "sf" : sf1,
        "kl" : kl1,
        "pc" : pc1,
        "zy" : zy1,
        timestamp:new Date().getTime()
    };
    ajax(url, params, function (data) {
        var backCode = data.backCode;
        if(backCode==0){
            $("#lncx_nf").empty();
            $("#lncx_sf").empty();
            $("#lncx_pc").empty();
            $("#lncx_kl").empty();
            $("#lncx_zy").empty();


            $("#lncx_nf").append("<option value=''>年份</option>");
            $("#lncx_sf").append("<option value=''>省份</option>");
            $("#lncx_pc").append("<option value=''>批次</option>");
            $("#lncx_kl").append("<option value=''>科类</option>");
            $("#lncx_zy").append("<option value=''>专业</option>");

            $.each(data.bean.nfList, function (k, p) {
                str = "<option  value='"+p.nf+"'>"+p.nf+"</option>";
                $("#lncx_nf").append(str);
            });
            $.each(data.bean.sfList, function (k, p) {
                str = "<option  value='"+p.sf+"'>"+p.sf+"</option>";
                $("#lncx_sf").append(str);
            });
            $.each(data.bean.klList, function (k, p) {
                str = "<option value='"+p.kl+"'>"+p.kl+"</option>";
                $("#lncx_kl").append(str);
            });
            $.each(data.bean.pcList, function (k, p) {
                str = "<option  value='"+p.pc+"'>"+p.pc+"</option>";
                $("#lncx_pc").append(str);
            });
            $.each(data.bean.zyList, function (k, p) {
                str = "<option  value='"+p.zy+"'>"+p.zy+"</option>";
                $("#lncx_zy").append(str);
            });
        }
    })
}
var lnjlid="";
function mychange(obj){
	lnjlid = $(obj).attr("id");
    var nf1 = $("#lncx_nf").val();
    var sf1 = $("#lncx_sf").val();
    var kl1 = $("#lncx_kl").val();
    var pc1 = $("#lncx_pc").val();
    var zy1 = $("#lncx_zy").val();
    var params = {
        "nf" : nf1,
        "sf" : sf1,
        "kl" : kl1,
        "pc" : pc1,
        "zy" : zy1,
        timestamp:new Date().getTime()
    };
    ajax("zustzs/bckjBizLntj/getChanges", params, function (data) {
        var backCode = data.backCode;
        // layer.close(layId);
        if(backCode==0){
            if(lnjlid!='lncx_nf')
                $("#lncx_nf").empty();
            if(lnjlid!='lncx_sf')
                $("#lncx_sf").empty();
            if(lnjlid!='lncx_pc')
                $("#lncx_pc").empty();
            if(lnjlid!='lncx_kl')
                $("#lncx_kl").empty();
            if(lnjlid!='lncx_zy')
                $("#lncx_zy").empty();

            if(lnjlid!='lncx_nf'){
                var str = "<option value=''>--年份--</option>";
                $("#lncx_nf").append(str);
            }
            if(lnjlid!='lncx_sf'){
                var str = "<option value=''>--省份--</option>";
                $("#lncx_sf").append(str);
            }
            if(lnjlid!='lncx_pc'){
                var str = "<option value=''>--批次--</option>";
                $("#lncx_pc").append(str);
            }
            if(lnjlid!='lncx_kl'){
                var str = "<option value=''>--科类--</option>";
                $("#lncx_kl").append(str);
            }
            if(lnjlid!='lncx_zy'){
                var str = "<option value=''>--专业--</option>";
                $("#lncx_zy").append(str);
            }
            if(lnjlid!='lncx_nf') {
                $.each(data.bean.nfList, function (k, p) {
                    if (nf1 === p.nf) {
                        str = "<option selected='selected' value='" + p.nf + "'>" + p.nf + "</option>"
                    } else {
                        str = "<option  value='" + p.nf + "'>" + p.nf + "</option>";
                    }
                    $("#lncx_nf").append(str);
                });
            }
            if(lnjlid!='lncx_sf'){
                $.each(data.bean.sfList, function (k, p) {
                    if (sf1 === p.sf) {
                        str = "<option selected='selected' value='"+ p.sf +"'>"+ p.sf +"</option>"
                    } else {
                        str = "<option  value='"+p.sf+"'>"+p.sf+"</option>";
                    }
                    $("#lncx_sf").append(str);
                });
            }
            if(lnjlid!='lncx_kl')
                $.each(data.bean.klList, function (k, p) {
                    if (kl1 === p.kl) {
                        str = "<option selected='selected' value='"+ p.kl +"'>"+ p.kl +"</option>"
                    } else {
                        str = "<option value='"+p.kl+"'>"+p.kl+"</option>";
                    }
                    $("#lncx_kl").append(str);
                });
            if(lnjlid!='lncx_pc')
                $.each(data.bean.pcList, function (k, p) {
                    if (pc1 === p.pc) {
                        str = "<option selected='selected' value='"+ p.pc +"'>"+ p.pc +"</option>"
                    } else {
                        str = "<option  value='"+p.pc+"'>"+p.pc+"</option>";
                    }
                    $("#lncx_pc").append(str);
                });
            if(lnjlid!='lncx_zy')
                $.each(data.bean.zyList, function (k, p) {
                    if (zy1 === p.zy) {
                        str = "<option selected='selected' value='"+ p.zy +"'>"+ p.zy +"</option>"
                    } else {
                        str = "<option  value='"+p.zy+"'>"+p.zy+"</option>";
                    }
                    $("#lncx_zy").append(str);
                });
        }else {
            // tip("无记录",$("#jhnf"));
        }
    });
}

//重置计划查询
function jhcx_init() {
    var nf2 = "";
    var sf2 = "";
    var kl2 = "";
    var pc2 = "";
    var zy2 = "";
    var url = "zustzs/bckjBizZsjh/getChanges";
    var params = {
        "nf" : nf2,
        "sf" : sf2,
        "kl" : kl2,
        "pc" : pc2,
        "zy" : zy2,
        timestamp:new Date().getTime()
    };
    ajax(url, params, function (data) {
        var backCode = data.backCode;
        if(backCode==0){
			$("#jhcx_nf").empty();
			$("#jhcx_sf").empty();
			$("#jhcx_pc").empty();
			$("#jhcx_kl").empty();
			$("#jhcx_zy").empty();


			$("#jhcx_nf").append("<option value=''>年份</option>");
			$("#jhcx_sf").append("<option value=''>省份</option>");
			$("#jhcx_pc").append("<option value=''>批次</option>");
			$("#jhcx_kl").append("<option value=''>科类</option>");
			$("#jhcx_zy").append("<option value=''>专业</option>");

			$.each(data.bean.nfList, function (k, p) {
				str = "<option  value='"+p.nf+"'>"+p.nf+"</option>";
				$("#jhcx_nf").append(str);
			});
			$.each(data.bean.sfList, function (k, p) {
				str = "<option  value='"+p.sf+"'>"+p.sf+"</option>";
				$("#jhcx_sf").append(str);
			});
			$.each(data.bean.klList, function (k, p) {
				str = "<option value='"+p.kl+"'>"+p.kl+"</option>";
				$("#jhcx_kl").append(str);
			});
			$.each(data.bean.pcList, function (k, p) {
				str = "<option  value='"+p.pc+"'>"+p.pc+"</option>";
				$("#jhcx_pc").append(str);
			});
			$.each(data.bean.zyList, function (k, p) {
				str = "<option  value='"+p.zy+"'>"+p.zy+"</option>";
				$("#jhcx_zy").append(str);
			});
        }
    })
}
var jhid="";
function jhchang(obj){
    jhid = $(obj).attr("id");
    var nf2 = $("#jhcx_nf").val();
    var sf2 = $("#jhcx_sf").val();
    var kl2 = $("#jhcx_kl").val();
    var pc2 = $("#jhcx_pc").val();
    var zy2 = $("#jhcx_zy").val();
    var params = {
        "nf" : nf2,
        "sf" : sf2,
        "kl" : kl2,
        "pc" : pc2,
        "zy" : zy2,
        timestamp:new Date().getTime()
    };
    ajax("zustzs/bckjBizZsjh/getChanges", params, function (data) {
        var backCode = data.backCode;
        // layer.close(layId);
        if(backCode==0){
            if(jhid!='jhcx_nf')
                $("#jhcx_nf").empty();
            if(jhid!='jhcx_sf')
                $("#jhcx_sf").empty();
            if(jhid!='jhcx_pc')
                $("#jhcx_pc").empty();
            if(jhid!='jhcx_kl')
                $("#jhcx_kl").empty();
            if(jhid!='jhcx_zy')
                $("#jhcx_zy").empty();

            if(jhid!='jhcx_nf'){
                var str = "<option value=''>--年份--</option>";
                $("#jhcx_nf").append(str);
			}
            if(jhid!='jhcx_sf'){
                var str = "<option value=''>--省份--</option>";
                $("#jhcx_sf").append(str);
			}
            if(jhid!='jhcx_pc'){
                var str = "<option value=''>--批次--</option>";
                $("#jhcx_pc").append(str);
			}
            if(jhid!='jhcx_kl'){
                var str = "<option value=''>--科类--</option>";
                $("#jhcx_kl").append(str);
			}
            if(jhid!='jhcx_zy'){
                var str = "<option value=''>--专业--</option>";
                $("#jhcx_zy").append(str);
			}
            if(jhid!='jhcx_nf') {
                $.each(data.bean.nfList, function (k, p) {
                    if (nf2 === p.nf) {
                        str = "<option selected='selected' value='" + p.nf + "'>" + p.nf + "</option>"
                    } else {
                        str = "<option  value='" + p.nf + "'>" + p.nf + "</option>";
                    }
                    $("#jhcx_nf").append(str);
                });
            }
            if(jhid!='jhcx_sf'){
                $.each(data.bean.sfList, function (k, p) {
                    if (sf2 === p.sf) {
                        str = "<option selected='selected' value='"+ p.sf +"'>"+ p.sf +"</option>"
                    } else {
                        str = "<option  value='"+p.sf+"'>"+p.sf+"</option>";
                    }
                    $("#jhcx_sf").append(str);
                });
			}
            if(jhid!='jhcx_kl')
                $.each(data.bean.klList, function (k, p) {
                    if (kl2 === p.kl) {
                        str = "<option selected='selected' value='"+ p.kl +"'>"+ p.kl +"</option>"
                    } else {
                        str = "<option value='"+p.kl+"'>"+p.kl+"</option>";
                    }
                    $("#jhcx_kl").append(str);
                });
            if(jhid!='jhcx_pc')
                $.each(data.bean.pcList, function (k, p) {
                    if (pc2 === p.pc) {
                        str = "<option selected='selected' value='"+ p.pc +"'>"+ p.pc +"</option>"
                    } else {
                        str = "<option  value='"+p.pc+"'>"+p.pc+"</option>";
                    }
                    $("#jhcx_pc").append(str);
                });
            if(jhid!='jhcx_zy')
                $.each(data.bean.zyList, function (k, p) {
                    if (zy2 === p.zy) {
                        str = "<option selected='selected' value='"+ p.zy +"'>"+ p.zy +"</option>"
                    } else {
                        str = "<option  value='"+p.zy+"'>"+p.zy+"</option>";
                    }
                    $("#jhcx_zy").append(str);
                });
        }else {
            // tip("无记录",$("#jhnf"));
		}
    });
}