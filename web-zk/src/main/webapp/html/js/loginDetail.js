var pageNo = 1;
var pageSize = 20;
var totalColunt = "";
var mark = 0;
var cityId = "";//当前城市id
var preUrl = "";//首页freemarker 当前页html导致路径不一致
$(function(){
	cityId = localStorage.getItem("cityId");
    preUrl = window.location.href.split("loginNewDetail")[0]+"login.htm?cityId="+cityId;
    var name = "政府投资项目管理平台";
    var area = "";
    if(cityId == 484){
        area = "满洲里";
    }
    if(cityId == 470){
        area = "呼伦贝尔市";
    }
    if(cityId == 482){
        area = "兴安盟";
    }
    if(cityId == 485){
        area = "二连浩特";
    }
    if(cityId == 475){
        area = "通辽市";
    }
    if(cityId == 476){
        area = "赤峰市";
    }
    if(cityId == 479){
        area = "锡林郭勒盟";
    }
    if(cityId == 474){
        area = "乌兰察布市";
    }
    if(cityId == 472){
        area = "包头市";
    }
    if(cityId == '015'){
        area = "内蒙古自治区";
    }
    if(cityId == 471){
        area = "呼和浩特市";
    }
    if(cityId == 478){
        area = "巴彦淖尔市";
    }
    if(cityId == 477){
        area = "鄂尔多斯市";
    }
    if(cityId == 473){
        area = "乌海市";
    }
    if(cityId == 483){
        area = "阿拉善盟";
    }
    if(!emptyCheck(cityId)){
        area = "";
    }
    $(".login-header_content p").html(area+name);
	ajaxPost("companyIndexApi/listNewsType.do","",0);
	if(owid){
	    //详情接口
		ajaxPost("companyIndexApi/detailNews.do",{"owid":owid},2);
	}else{
	    //列表接口
		ajaxPost("companyIndexApi/listNewsByType.do",{"type":topicType,"pageNo":pageNo,"pageSize":pageSize,"cityId":cityId},1);
		$("#paginationWrap").show();
	}
});
function handleData(data,index,typeId) {
	if(index==0){
        if(data.backCode == 0){
        	var str = "";
        	var str1 = "";
            $.each(data.bean,function(k,p){
            	if(!topicType&&k==0){
            		str += "<li class='cur-tab'>"+p.dicVal2+"</li>";
            		str +="<li class='active list-detail' val="+p.dicVal1+" text="+p.dicVal2+"><a><i class='icon sideicon1'></i>"+p.dicVal2+"<i class='icon sideicon2'></i></a></li>";
            		ajaxPost("companyIndexApi/listNewsByType.do",{"type":p.dicVal1,"cityId":cityId},1);
            		var thisStr = '<h4 class="logcontent-title">'+p.dicVal2+'<span class="place"><i class="icon homeicon"></i><em><a href="'+preUrl+'">登录</a>><a>'+p.dicVal2+'</a></em></span></h4>';
				$("#page-position").html(thisStr);
            	}else{
            		if(topicType==p.dicVal1){
            			str1 = "<li class='cur-tab'>"+p.dicVal2+"</li>";
            			
            			var a = '<h4 class="logcontent-title">'+p.dicVal2+'<span class="place"><i class="icon homeicon"></i><em><a href="'+preUrl+'">登录</a>><a>'+p.dicVal2+'</a></em></span></h4>';
				        $("#page-position").html(a);
				        
            			str +="<li class='active list-detail' val="+p.dicVal1+" text="+p.dicVal2+"><a><i class='icon sideicon1'></i>"+p.dicVal2+"<i class='icon sideicon2'></i></a></li>";
            		}else{
            		str +="<li class='list-detail'  val="+p.dicVal1+" text="+p.dicVal2+"><a><i class='icon sideicon1'></i>"+p.dicVal2+"<i class='icon sideicon2'></i></a></li>";}
            	}
            });
            $(".content-left_datas").html(str1 + str);
            $(".list-detail").click(function(){
				$(this).addClass("active").siblings().removeClass("active");
				
				var text = $(this).attr("text");
				var thisStr = '<h4 class="logcontent-title">'+text+'<span class="place"><i class="icon homeicon"></i><em><a href="'+preUrl+'">登录</a>><a>'+text+'</a></em></span></h4>';
				$("#page-position").html(thisStr);
				$(".cur-tab").html(text);
				
				topicType = $(this).attr("val");
				pageNo = 0;
				mark = 0;
				$("#paginationWrap").show();
				ajaxPost("companyIndexApi/listNewsByType.do",{"type":topicType,"pageNo":pageNo,"pageSize":pageSize,"cityId":cityId},1);
			});
        }else{
            console.log(data.errorMess);
        }
    }
	if(index==1){
		 if(data.backCode == 0){
        	var str1 = "";
        	if(data.bean.records.length>0){
        		$.each(data.bean.records,function(k,p){
            	   str1+="<li owid ="+p.owid+"><a title="+p.newsTitle+">"+p.newsTitle+"<span>"+p.newsTime.substr(0,10)+"</span></a></li>";
                });
                $(".title-list").html(str1);
                $(".title-list li").click(function(){
                	$("#paginationWrap").hide();
			        owid = $(this).attr("owid");
				    ajaxPost("companyIndexApi/detailNews.do",{"owid":owid},2);
			    });
        	}else{
        		$(".title-list").html("暂无信息");
        	}
        	 totalColunt = data.bean.totalCount;
        	 if(mark==0){
        	 	loadData(1);
        	    loadpage();
        	    mark=1;
        	 }
        	 
        }else{
            console.log(data.errorMess);
        }
	}
	if(index==2){
		if(data.backCode == 0){
			if(!data.bean.newsTypeStr){data.bean.newsTypeStr = "暂无"}
            if(!data.bean.newsTimeStr){data.bean.newsTimeStr = "暂无"}
			var str2 = '<p class="detail-title">'+data.bean.newsTitle+'</p>';
			str2 +='<h6>公告类型：'+data.bean.newsTypeStr+'<span><span class="icon timeicon"></span>时间：'+data.bean.newsTimeStr+'</span></h6>';
			str2 +='<h4>'+data.bean.newsContent+'</h4>';
			if(data.bean.newsImg){
				str2 +='<div class="detail-img"><img src='+imgUrl+data.bean.newsImg+' /></div>';
			}
			if(data.bean.fileList.length>0){
				//str2 +='<a href= "http://www.qzsjcloud.com/projectManageFiles/'+data.bean.exp1+'" class="down-files"  target=“_blank”>附件下载</a>';
                for(var i in data.bean.fileList){
                    str2 +='<div><a href= "http://www.qzsjcloud.com:8081/'+data.bean.fileList[i]+'" class="down-files"  target=“_blank”>附件下载'+(parseInt(i)+1)+'</a><br>';
                }
			}
			$(".title-list").html(str2);
		}
	}
}
function loadData(num) {
    $("#PageCount").val(totalColunt);
}
function exeData(num, type) {
    loadData(num);
    loadpage();
    ajaxPost("companyIndexApi/listNewsByType.do",{"type":topicType,"pageNo":num,"pageSize":pageSize,"cityId":cityId},1);
}
function loadpage() {
    var myPageCount = parseInt($("#PageCount").val());
    var myPageSize = parseInt($("#PageSize").val());
    var countindex = myPageCount % myPageSize > 0 ? (myPageCount / myPageSize) + 1 : (myPageCount / myPageSize);
    $("#countindex").val(countindex);

    $.jqPaginator('#pagination', {
        totalPages: parseInt($("#countindex").val()),
        visiblePages: parseInt($("#visiblePages").val()),
        currentPage: 1,
        first: '<li class="first"><a href="javascript:;">首页</a></li>',
        prev: '<li class="prev"><a href="javascript:;"><i class="arrow arrow2"></i>上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页<i class="arrow arrow3"></i></a></li>',
        last: '<li class="last"><a href="javascript:;">末页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            if (type == "change") {
                exeData(num, type);
            }
        }
    });
}


