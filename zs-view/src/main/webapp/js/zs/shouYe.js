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
	// init_lbt();//轮播图
	//初始化招生专业
	// init_zszy();
	//获取通知公告
	// init_gg();
	//获取学院介绍
	// init_xyIntro();
	//初始化查询选项
	// init_chaXun();
	$("#zszylab").click(function(){
		getLabel();
		$("#zylbDiv").show();
		$(".dyn_details").css("margin-top","0px");
	});
	$(".zsdt").click(function(){
		$("#zylbDiv").hide();
		$(".dyn_details").css("margin-top","30px");
	});
    $(".zsjd").click(function(){
        $("#zylbDiv").hide();
        $(".dyn_details").css("margin-top","30px");
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
	lb = $(obj).attr("val");
	currPage=1;
	if(emptyCheck($(obj).attr("name"))){
		name = $(obj).attr("name");
	}
	$(".dyn_details").empty();
	xsts = 10;
	if(name =="zxzx"){
		window.location.href="IndexPage!zxtw.htm";
	}
	init_details(name);
}


function init_details(name){
	var url = "webAjaxAction!zxxwList.htm";
	var params = {
	        "sessionId": _sessionid,
	        "name": name,
	        "pageNo":currPage,
	        "pageSize":pageSize,
	        "lb":lb,
	        timestamp : new Date().getTime()
	    };
	$.post(url, params, function(data){
		var x = "";
		if(data.backCode == 0){
			res = data.bean.length;
			var nn = 1;
			$.each(data.bean,function(j,k){
				x = "<div class='detail' articleId='"+k.id+"'><a href=''>";
				if(emptyCheck(k.tpjj))
					x += "<img class='detail_img' src='"+k.tpjj+"'>";
				else{
					x += "<img class='detail_img' src='"+IMAGEPATH+"BZT"+nn+".png'/>";
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
				$(".detail:eq("+i+")").children().eq(0).attr("href","IndexPage!wzxq.htm?id="+articleId);
                $(".detail:eq("+i+")").children().eq(0).attr("target","_blank");
				if((i+1)%5==0)
					$(".detail:eq("+i+")").css("margin-right","0px");
			});
			moreFlag = true;
		}else if(data.backCode == 2){
			window.location="IndexPage!shouYe.htm";
		}else{
			moreFlag = false;
			//$(".dyn_details").empty();
			//walert(data.errorMess);
		}
	});
}

function init_zszy(){
	init_details("zsdt");
	//查看更多按钮
	$(".detail_more").click(function(){
		if(!moreFlag){
			
		}else{
		//if(res<xsts)
		//	walert("只有那么多了！");
		//else{
		    xsts += 10;
			if(name=="gg"||name=="jxjzc")
				init_details(name);
			else
				init_details(name);
//			$(".dyn_details").css("height",xsts*150+"px");
		//}
		}
	});
}

var timeout_hid_ggIntro;
var gg_num = -1;
function init_gg(){
	var url = "webAjaxAction!zxxwList.htm";
	var params = {
	        "sessionId": _sessionid,
	        "name": "zsdt",
	        "type": "2",
	        "pageNo": "0",
	        "pageSize": "7"
	    };
	//获取公告
	$.post(url, params, function(data){
		var x = "";
		if(data.backCode == 0)
		{
			$.each(data.bean,function(j,k){
				x = "<li class='gongGao_ul_li' num='"+j+"'>";
				x += "<span class='glyphicon glyphicon-volume-up' style='font-size: 20px;float: left;'></span>";
				x += "<p class='gongGao_title'>"+k.wzbt+"</p>";
				x += "<div class='gongGao_introdiv' style='display:none;width:335px;height:80px;line-height:20px;overflow:hidden;'>";
				x += "<a href='IndexPage!wzxq.htm?id="+k.id+"'>";
				if(k.jjnr.length>150)
					x += "<p class='gongGao_intro'>"+"&nbsp&nbsp&nbsp&nbsp&nbsp"+k.jjnr.substr(0,80)+"...";
				else
					x += "<p class='gongGao_intro'>"+"&nbsp&nbsp&nbsp&nbsp&nbsp"+k.jjnr;
				x += "</a></p></div></li>";
				$(".gongGao_ul").append(x);
				if(j>5)
					return false;
			});
			$.each($(".gongGao_title"),function(j,k){
				$(k).click(function(){
					var isOpen = $(this).next().css("display");
					if(isOpen=="none"){
						$(this).prev().css("color","rgb(85,167,153)");
						$(this).css("color","rgb(85,167,153)");
						$(".gongGao_introdiv").slideUp(300);
						$(this).next().slideDown(300);
						var li_num = $(this).parent().attr("num");
						if(li_num>2)
							$(".gongGao_ul_li:eq("+(li_num-2)+")").prevAll().slideUp(300);
					}
					else{
						$(this).prev().css("color","black");
						$(this).css("color","black");
						$(this).next().slideUp(300);
						$(".gongGao_ul_li").slideDown(300);
					}
				});
			});
//			$(".tongZhiGongGao").mouseleave(function(){
//				gg_num = -1;
//				$(".gongGao_ul_li").slideDown(300);
//				$(".gongGao_introdiv").slideUp(300);
//			});
		}else if(data.backCode == 2){
			window.location="IndexPage!shouYe.htm";
		}else{
			//walert(data.errorMess);
		}
	});
}

function init_xyIntro(){
	var url = "webAjaxAction!xyjsList.htm";
	var params = {
	        "sessionId": _sessionid
	    };
	$.post(url, params, function(data){
		if(data.backCode == 0){
			var x = "";
			var intro_hs = Math.ceil(data.bean.length/4);
			$.each(data.bean,function(j,k){
				x = "<a href='#'>";
				x += "<div class='intro_detail'>";
				x += "<img class='intro_detail_img' src='"+IMAGEPATH+"intro"+(j+1)+".png'/>";
				x += "<strong class='intro_str'>"+k.mz+"</strong>";
				x += "<hr />";
				if(k.jj.length>70)
					x += "<p class='intro_p'>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+k.jj.substr(0,70)+"..."+"</p>";
				else
					x += "<p class='intro_p'>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+k.jj+"</p>";
				x += "</div></a>";
				$(".intro_details").append(x);
			});
			x = "<div class='intro_detail' id='intro_moredetail'>";
			x += "<p class='intro_moredetail_p'>MORE+</p></div>";
			$(".intro_details").append(x);
			for(var i=1;i<=intro_hs;i++){
				$(".intro_detail:eq("+(i*4-1)+")").css("margin-right","0px");
			}
		}else if(data.backCode == 2){
			window.location="IndexPage!shouYe.htm";
		}else{
			//walert(data.errorMess);
		}
	});
}
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
			window.location="IndexPage!shouYe.htm";
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
			window.location="IndexPage!shouYe.htm";
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
			window.location="IndexPage!shouYe.htm";
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
			window.location="IndexPage!shouYe.htm";
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
			window.location="IndexPage!shouYe.htm";
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
	var url = "webAjaxAction!zsjhcx.htm";
	var params = {
	        "sessionId": _sessionid,
	        "nf": $("#jhcx_nf").val(),
	        "sf": $("#jhcx_sf").val(),
	        "kl": $("#jhcx_kl").val(),
	        "pc": $("#jhcx_pc").val(),
	        "zy": $("#jhcx_zy").val(),
	        "curPage": curPage,
	        "itemsPerPage": 6
	    };
	$.post(url, params, function(data){
		var x = "";
		$(".cxRes").empty();
		x = "<tr style='height:30px;'><th>年份</th><th>省份</th><th>科类</th><th>批次</th><th>专业</th><th>学制</th><th>招生数</th><th>学费/年</th><th>授予学位</th></tr>";
		if(data.backCode == 1){
			$(".cxRes").empty();
			$.each(data.bean,function(j,k){
				x += "<tr style='height:30px;'><td>"+k.nf+"</td>";
				x += "<td>"+k.sf+"</td>";
				x += "<td>"+k.kl+"</td>";
				x += "<td>"+k.pc+"</td>";
				x += "<td>"+k.zy+"</td>";
				x += "<td>"+k.xz+"</td>";
				x += "<td>"+k.zss+"</td>";
				x += "<td>"+k.xf+"</td>";
				x += "<td>"+k.syxw+"</td>";
				x += "</tr>";
			});
			$(".cxRes").append(x);
			if(curPage==1&&loadcs==0){
				$("#mymodal").modal("toggle");
			}
			if(loadcs==0){
				$(".fenye").empty();
				$(".fenye").createPage({
					pageCount:data.allPages,
					current:curPage,
					backFn:function(p){
						curPage = p;
						jhcx_chaXun();
					}
				});
				loadcs ++;
			}
		}else if(data.backCode == 2){
			window.location="IndexPage!shouYe.htm";
		}else{
			walert(data.errorMess);
		}
	});
}
function cjcx_chaXun(){
	var url = "webAjaxAction!cjcx.htm";
	var params = {
	        "sessionId": _sessionid,
	        "input_zkzh": $("#cjcx_zkzh").val(),
	        "input_sfzh": $("#cjcx_sfzh").val(),
	        "curPage": 0,
	        "itemsPerPage": 1
	    };
	$.post(url, params, function(data){
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
			window.location="IndexPage!shouYe.htm";
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
			window.location="IndexPage!shouYe.htm";
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
		curPage=1;
	});
	var url = "webAjaxAction!lnfs.htm";
	var params = {
	        "sessionId": _sessionid,
	        "nf": $("#lncx_nf").val(),
	        "sf": $("#lncx_sf").val(),
	        "kl": $("#lncx_kl").val(),
	        "pc": $("#lncx_pc").val(),
	        "zy": $("#lncx_zy").val(),
	        "curPage": curPage,
	        "itemsPerPage": 6,
	        timestamp : new Date().getTime()
	    };
	$.post(url, params, function(data){
		var x = "";
		$(".cxRes").empty();
		x = "<tr style='height:30px'><th>年份</th><th>省份</th><th>科类</th><th>批次</th><th>专业</th><th>学制</th><th>录取数</th><th>最高分</th><th>最低分</th><th>平均分</th></tr>";
		if(data.backCode == 1){
			$.each(data.bean,function(j,k){
				x += "<tr style='height:30px'><td>"+k.nf+"</td>";
				x += "<td>"+k.sf+"</td>";
				x += "<td>"+k.kl+"</td>";
				x += "<td>"+k.pc+"</td>";
				x += "<td>"+k.zy+"</td>";
				x += "<td>"+k.xz+"</td>";
				x += "<td>"+k.lqs+"</td>";
				x += "<td>"+k.zgf+"</td>";
				x += "<td>"+k.zdf+"</td>";
				x += "<td>"+k.pjf+"</td>";
				x += "</tr>";
			});
			$(".cxRes").append(x);
			if(curPage==1&&loadcs==0){
				$("#mymodal").modal("toggle");
			}
			if(loadcs==0){
				$(".fenye").createPage({
					pageCount:data.allPages,
					current:curPage,
					backFn:function(p){
						curPage = p;
						lncx_chaXun();
					}
				});
				loadcs ++;
			}
		}else if(data.backCode == 2){
			window.location="IndexPage!shouYe.htm";
		}else{
			walert(data.errorMess);
		}
	});
}
var news_lunbo1 = window.setInterval(news_lunbo,3000);
function show_new(news_num)
{
	clearInterval(news_lunbo1);
	$(".circle").css("background-color","white");
	$(".circle").css("border-color","rgb(184,184,184)");
	$(".li_group2").css("display","none");
	$("#news_circle"+news_num).css("background-color","black");
	$("#news_circle"+news_num).css("border-color","black");
	$("#li_group2_"+news_num).css("display","inline");
}
var num=1;
function news_lunbo(cicle)
{
	if(num>3)num=num-3;
	$(".circle").css("background-color","white");
	$(".circle").css("border-color","rgb(184,184,184)");
	$(".li_group2").css("display","none");
	$("#news_circle"+num).css("background-color","black");
	$("#news_circle"+num).css("border-color","black");
	$("#li_group2_"+num).css("display","inline");
	num++;
}
function restart_news_lunbo()
{
	$(".circle").css("background-color","white");
	$(".circle").css("border-color","rgb(184,184,184)");
	$(".li_group2").css("display","none");
	$("#news_circle1").css("background-color","black");
	$("#news_circle1").css("border-color","black");
	$("#li_group2_1").css("display","inline");
	news_lunbo1 = window.setInterval(news_lunbo,3000);
}
function hid_ggIntro(t)
{
	t.slideUp(300);
}
function show_intro(t)
{
	t.css("display","block");
}
function baoChiXS_intro(t)
{
	clearTimeout(timeout_hid_ggIntro);
	show_intro(t);
}

var lnjlid="";
function mychange(obj){
    var lnjlid = $(obj).attr("id");
    var nf1 = $("#lncx_nf").val();
    var sf1 = $("#lncx_sf").val();
    var kl1 = $("#lncx_kl").val();
    var pc1 = $("#lncx_pc").val();
    var zy1 = $("#lncx_zy").val();
    var url = "zscxAjax!chang.htm";
    var params = {
        "nf" : nf1,
        "sf" : sf1,
        "kl" : kl1,
        "pc" : pc1,
        "zy" : zy1,
        timestamp:new Date().getTime()
    };
    $.post(url,params,function(data){
        var backCode = data.backCode;
        // layer.close(layId);
        if(backCode==0){
            // tip("无记录",$("#jhnf"));
        }else if(backCode==1){
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
            var str = "<option value=''>---请选择---</option>";
            if(lnjlid!='lncx_nf')
                $("#lncx_nf").append(str);
            if(lnjlid!='lncx_sf')
                $("#lncx_sf").append(str);
            if(lnjlid!='lncx_pc')
                $("#lncx_pc").append(str);
            if(lnjlid!='lncx_kl')
                $("#lncx_kl").append(str);
            if(lnjlid!='lncx_zy')
                $("#lncx_zy").append(str);
            if(lnjlid!='lncx_nf')
                $.each(data.bean.nf,function(k,p){
                    if(p==nf1)
                        str = "<option  selected='selected' value='"+p+"'>"+p+"</option>";
                    else
                        str = "<option  value='"+p+"'>"+p+"</option>";
                    $("#lncx_nf").append(str);
                });
            if(lnjlid!='lncx_sf')
                $.each(data.bean.sf,function(k,p){
                    if(p==sf1)
                        str = "<option  selected='selected' value='"+p+"'>"+p+"</option>";
                    else
                        str = "<option  value='"+p+"'>"+p+"</option>";
                    $("#lncx_sf").append(str);
                });
            if(lnjlid!='lncx_kl')
                $.each(data.bean.kl,function(k,p){
                    if(p==kl1)
                        str = "<option  selected='selected' value='"+p+"'>"+p+"</option>";
                    else
                        str = "<option  value='"+p+"'>"+p+"</option>";
                    $("#lncx_kl").append(str);
                });
            if(lnjlid!='lncx_pc')
                $.each(data.bean.pc,function(k,p){
                    if(p==pc1)
                        str = "<option  selected='selected' value='"+p+"'>"+p+"</option>";
                    else
                        str = "<option  value='"+p+"'>"+p+"</option>";
                    $("#lncx_pc").append(str);
                });
            if(lnjlid!='lncx_zy')
                $.each(data.bean.zy,function(k,p){
                    if(p==zy1)
                        str = "<option  selected='selected' value='"+p+"'>"+p+"</option>";
                    else
                        str = "<option  value='"+p+"'>"+p+"</option>";
                    $("#lncx_zy").append(str);
                });
        }
    });
}
var jhid="";
function jhchang(obj){
    jhid = $(obj).attr("id");
    var nf2 = $("#jhcx_nf").val();
    var sf2 = $("#jhcx_sf").val();
    var kl2 = $("#jhcx_kl").val();
    var pc2 = $("#jhcx_pc").val();
    var zy2 = $("#jhcx_zy").val();
    var url = "zscxAjax!changz.htm";
    var params = {
        "nf" : nf2,
        "sf" : sf2,
        "kl" : kl2,
        "pc" : pc2,
        "zy" : zy2,
        timestamp:new Date().getTime()
    };
    $.post(url,params,function(data){
        var backCode = data.backCode;
        // layer.close(layId);
        if(backCode==0){
            // tip("无记录",$("#jhnf"));
        }else if(backCode==1){
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
            var str = "<option value=''>---请选择---</option>";
            if(jhid!='jhcx_nf')
                $("#jhcx_nf").append(str);
            if(jhid!='jhcx_sf')
                $("#jhcx_sf").append(str);
            if(jhid!='jhcx_pc')
                $("#jhcx_pc").append(str);
            if(jhid!='jhcx_kl')
                $("#jhcx_kl").append(str);
            if(jhid!='jhcx_zy')
                $("#jhcx_zy").append(str);
            if(jhid!='jhcx_nf')
                $.each(data.bean.nf,function(k,p){

                    if(p==nf2)
                        str = "<option  selected='selected' value='"+p+"'>"+p+"</option>";
                    else
                        str = "<option  value='"+p+"'>"+p+"</option>";
                    $("#jhcx_nf").append(str);
                });
            if(jhid!='jhcx_sf')
                $.each(data.bean.sf,function(k,p){
                    if(p==sf2)
                        str = "<option  selected='selected' value='"+p+"'>"+p+"</option>";
                    else
                        str = "<option  value='"+p+"'>"+p+"</option>";
                    $("#jhcx_sf").append(str);
                });
            if(jhid!='jhcx_kl')
                $.each(data.bean.kl,function(k,p){
                    if(p==kl2)
                        str = "<option  selected='selected' value='"+p+"'>"+p+"</option>";
                    else
                        str = "<option  value='"+p+"'>"+p+"</option>";
                    $("#jhcx_kl").append(str);
                });
            if(jhid!='jhcx_pc')
                $.each(data.bean.pc,function(k,p){
                    if(p==pc2)
                        str = "<option  selected='selected' value='"+p+"'>"+p+"</option>";
                    else
                        str = "<option  value='"+p+"'>"+p+"</option>";
                    $("#jhcx_pc").append(str);
                });
            if(jhid!='jhcx_zy')
                $.each(data.bean.zy,function(k,p){
                    if(p==zy2)
                        str = "<option  selected='selected' value='"+p+"'>"+p+"</option>";
                    else
                        str = "<option  value='"+p+"'>"+p+"</option>";
                    $("#jhcx_zy").append(str);
                });
        }
    });
}