// JavaScript Document
var dDialog = null;
// var ajaxUrl = '/default/date';
var json = null;
var temp=new Date().getTime();
var weekday= new Array(7);

function initDatePicker(){
	var dat = new Date();
    var jsonObj = {
        "startTime": dat.getFullYear()+'-'+(dat.getMonth()+1)+'-01',
        "endTime":dat.getFullYear()+'-'+(dat.getMonth()+1)+'-31',
    }
    ajax("zustjy/bckjBizJob/jobByMonth", jsonObj, function (data) {
        if(data.backCode==0){
            if(data.bean){
                json = data.bean;
            }else{
                json=[]
            }
			weekday[0]="周天";
			weekday[1]="周一";
			weekday[2]="周二";
			weekday[3]="周三";
			weekday[4]="周四";
			weekday[5]="周五";
			weekday[6]="周六";
			$("#datepicker").datepicker({
				inline: true,
				onSelect:onSelect,
				onChangeMonthYear:onChangeMonthYear,
				beforeShowDay:beforeShowDay,
				showMonthAfterYear:true,
				dateFormat:"yy-mm-dd"
			});
			initDayHover();


        }
    })
}
function onSelect(){
	return false;
}
function initContent(date){
	var str='';
	if(json[date].length>0){
		$.each(json[date],function (k, p) {
			str+='<li><a title="'+p.zwbt+'" target="_blank" href="'+base+'/positionDetail/'+p.owid+'">'+p.zwbt+'</a></li>'
        })
	}
	html = '<div class="datePickerLayout"> \
		<ul>'+str+'</ul> \
	</div>';
	return  html;
}
function initDayHover(){
	var hoverTimer, outTimer;

	$('#datepicker').on('mouseover','.dayTip',function(){
			var t = this;
			var title = $(this).data('year')+'-'+(fixZero($(this).data('month')+1, 2))+'-'+(fixZero($(this).text(), 2));
        hoverTimer = setTimeout(function(){
            if(json[title]){
                if(dDialog){
                    dDialog.follow(t).content(initContent(title)).title(title);
                }else{
                    dDialog = $.dialog({
                        title:title,
                        id:'date',
                        resize:false,
                        drag :false,
                        content:initContent(title),
                        close: function () {
                            dDialog = null;
                        }
                    }).follow(t);
                }
            }
        },300);
    }).on('mouseout','.dayTip',function(){
		clearTimeout(hoverTimer);
	});

	$('#datepicker').on('mouseover','.dayTip_last',function(){
		var t = this;
        var title = $(this).data('year')+'-'+(fixZero($(this).data('month')+1, 2))+'-'+(fixZero($(this).text(), 2));
        hoverTimer = setTimeout(function(){
            if(json[title]){
                if(dDialog){
                    dDialog.follow(t).content(initContent(title)).title(title);
                }else{
                    dDialog = $.dialog({
                        title:title,
                        id:'date',
                        resize:false,
                        drag :false,
                        content:initContent(title),
                        close: function () {
                            dDialog = null;
                        }
                    }).follow(t);
                }
            }
        },300);
    }).on('mouseout','.dayTip_last',function(){
		clearTimeout(hoverTimer);
	});
}

function onChangeMonthYear(year,month,inst){
    var jsonObj = {
        "startTime": year+'-'+month+'-01',
        "endTime":year+'-'+month+'-31',
    }
    ajax("zustjy/bckjBizJob/jobByMonth", jsonObj, function (data) {
        if(data.backCode==0){
            if(data.bean){
                json = data.bean;
            }else{
            	json=[]
			}
            // console.log(json["2019-08-08"])
            weekday[0]="周天";
            weekday[1]="周一";
            weekday[2]="周二";
            weekday[3]="周三";
            weekday[4]="周四";
            weekday[5]="周五";
            weekday[6]="周六";
            $("#datepicker").datepicker("refresh");
            if(dDialog){
                dDialog.close();
                dDialog = null;
            }

        }
    })
}

function strtotime(date){
	return date.getFullYear()+'-'+fixZero(date.getMonth() + 1, 2)+'-'+fixZero(date.getDate(), 2);
}
function fixZero(num, length) {
    var str = "" + num;
    var len = str.length;
    var s = "";
    for (var i = length; i-- > len;) {
        s += "0";
    }
    return s + str;
}

function beforeShowDay(date) {
    var d = strtotime(date);
    // var d_temp=new Date(d).getTime();
    var d_temp = new Date(date.getFullYear(), date.getMonth(), date.getDate()).getTime();
	if(json && json[d]){
		if(d_temp>temp){
			return [true, 'dayTip',''];
		}else{
			return [true, 'dayTip_last',''];
		}
	}else{
		return [false, '', ''];
	}
}

$(function(){
	initDatePicker();
})
