/**
 * Created by Xia Yu on 2019/3/4.
 */
// 基于准备好的dmo，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));
$(function() {
	getProProgress();
});

function getProProgress() {
	option.yAxis[0].data = smallTask; //小任务
	//option.yAxis[1].data = bigTask; //大任务

	$.each(beginDt, function(k, p) {
		option.series[0].data.push(new Date(p.replace(/-/g, '/')));
	});
	$.each(endDt, function(k, p) {
		option.series[1].data.push(new Date(p.replace(/-/g, '/')));
	});
	$.each(realBeginDt, function(k, p) {
		option.series[2].data.push(new Date(p.replace(/-/g, '/')));
	});
	$.each(realEndDt, function(k, p) {
		option.series[3].data.push(new Date(p.replace(/-/g, '/')));
	});
	/*option.yAxis[0].data = ['任务1.1', '任务1.2', '任务1.3', '任务2.1', '任务2.2', '任务3.1', '任务3.2', '任务3.3', '任务4.1', '任务4.2', '任务5.1', '任务5.2', '任务6.1', '任务6.2', '任务7'];
	option.yAxis[1].data = ['任务1', '', '', '任务2', '', '任务3', '', '', '任务4', '', '任务5', '', '任务6', '', '任务7'];
	option.series[0].data = [new Date("2015/09/2"), new Date("2015/09/15"), new Date("2015/09/15"), new Date("2015/10/03"), new Date("2015/10/04"), new Date("2015/10/05"), new Date("2015/10/06"), new Date("2015/10/06"), new Date("2015/10/07")];
	option.series[1].data = [new Date("2015/09/12"), new Date("2015/09/20"), new Date("2015/09/25"), new Date("2015/10/05"), new Date("2015/10/07"), new Date("2015/10/09"), new Date("2015/10/12"), new Date("2015/10/12"), new Date("2015/10/12")];
	option.series[2].data = [new Date("2015/09/2"), new Date("2015/09/15"), new Date("2015/09/15"), new Date("2015/10/03"), new Date("2015/10/04"), new Date("2015/10/05"), new Date("2015/10/06"), new Date("2015/10/07"), new Date("2015/10/08")];
	option.series[3].data = [new Date("2015/09/6"), new Date("2015/09/20"), new Date("2015/09/27"), new Date("2015/10/11"), new Date("2015/10/16"), new Date("2015/10/18"), new Date("2015/10/17"), new Date("2015/10/20"), new Date("2015/10/21")];*/
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
    window.onresize = myChart.resize;
}

// 指定图表的配置项和数据
var option = {
	title: {
		text: '项目施工时间进度',
        show: false,
	},
	legend: {
		data: ['计划时间', '实际时间'],
		show: false
	},
	grid: {
		containLabel: true,
        top:0
	},
	xAxis: {
		type: 'time',
		position: 'top',
		axisTick: {
			inside: true
		},
		axisLabel: {
			formatter: function(value, index) {
				// 格式化成月/日，只在第一个刻度显示年份
				var date = new Date(value);
				var texts = [date.getFullYear(), (date.getMonth() + 1)];
				return texts.join('/');
			}
		},
		axisLine: {
			lineStyle: {
				color: 'rgba(30,62,94)',
				width: 2,
			}
		}

	},
	dataZoom: [
        {
            show: true,
            realtime: true,
            position: 'bottom',
            height: 20,
            start: 0,
            end: 100,
            type: 'inside'
        },
        {
            show: true,
            realtime: true,
            position: 'bottom',
            height: 20,
            start: 0,
            end: 100,
            type: 'slider'
        }
    ],
	yAxis: [{
		data: [],
		axisTick: {
			inside: true
		},
		axisLine: {
			lineStyle: {
				color: 'rgba(30,62,94)',
				width: 2,
			}
		}
	}
	/*, {
		axisLine: {
			lineStyle: {
				color: 'rgba(30,62,94)',
				width: 2,
			}
		},
		position: 'left',
		offset: 50,
		type: 'category',
		axisTick: {
			lineStyle: {
				color: '#000'
			},
			inside: true,
			interval: function(index, value) {
				return value !== '';
			}
		},
		axisLabel: {},
		data: []

	} 是第二条先，显示类别，目前不要*/
	],
	tooltip: {
        trigger: 'axis',
        backgroundColor: 'rgba(255,255,255,0.9)',
        formatter: function(params) {
            var res = "";
            var date0 = "暂无",date1 = "暂无",date2 = "暂无",date3 = "暂无";
            $.each(params,function (k,p) {
                res = '<div style="border:1px solid rgba(0,156,255,0.6);padding:10px;"><strong style="color:rgb(30,62,94);">'+p.name + "</strong></br>";
                var getYear =  p.data.getFullYear() + "-" + (p.data.getMonth() + 1) + "-" + p.data.getDate();
                switch(p.seriesName){
                    case "计划开始时间":
                        date0 = getYear;
                        break;
                    case "计划结束时间":
                        date1 = getYear;
                        break;
                    case "实际开始时间":
                        date2 = getYear;
                        break;
                    case "实际结束时间":
                        date3 = getYear;
                        break;
                }

            });
            res += "<span style='color: rgb(255,168,39)'>计划 :&nbsp;&nbsp;&nbsp;</span><span style='color:rgb(30,62,94);'>" + date0 + "~" + date1 + "</span></br>"
            res += "<span style='color: rgb(0,156,255)'>实际 :&nbsp;&nbsp;&nbsp;</span><span style='color:rgb(30,62,94);'>" + date2 + "~" + date3 + "</span></br></div>"
            return res;
        }
	},
	series: [{
			name: '计划开始时间',
			type: 'bar',
			stack: 'test1',
			itemStyle: {
				normal: {
					color: 'rgba(255,168,39,0)'
				}
			},
			data: []
		},
		{
			name: '计划结束时间',
			type: 'bar',
			stack: 'test1',
			itemStyle: {
				normal: {
					color: "rgb(255,168,39)"
				}
			},
			data: []
		},
		{
			name: '实际开始时间',
			type: 'bar',
			stack: 'test2',
			itemStyle: {
				normal: {
					color: 'rgba(0,156,255,0)'
				}
			},
			data: []
		},
		{
			name: '实际结束时间',
			type: 'bar',
			stack: 'test2',
			itemStyle: {
				normal: {
					color: 'rgb(0,156,255)'
				}
			},
			data: []
		}
	]
};