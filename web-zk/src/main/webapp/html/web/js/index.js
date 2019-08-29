var colorList = ['rgb(25,161,203)',"#e6703c","RGB(83,125,107)","#e64b52","RGB(103,181,215)"];
var pieChart = echarts.init(document.getElementById('leftPie'));
var barChart = echarts.init(document.getElementById('barChart'));
$(document).ready(function(){
  ajaxPost("outUseReportApi/tzgcTotal.do","",0);//5.1 投资组成接口
  ajaxPost("outUseReportApi/ndtzTotal.do",{"financeDirection":0},1);//5.2 分年度项目和投资图
  $(".ehcart-img_btn").click(function(){
  	 window.open("https://www.qzsjcloud.com/login.htm?cityId=472");
  	 // window.open("http://localhost:8081/");
  });
  
  $(".tab-select span").click(function(){
  	var index = $(this).attr("val"); 
  	$(this).addClass("active").siblings().removeClass("active")
  	ajaxPost("outUseReportApi/ndtzTotal.do",{"financeDirection":index},1);//5.2 分年度项目和投资图
  });
});

function handleData(data,index){
	if(index==0){
		if(data.backCode == 0){
        if(data.bean.pie.length>0){
        	  var str = "<p><label>项目：</label><span>"+data.bean.num+"</span>个</p><p><label>投资：</label><span>"+data.bean.ztz+"</span>亿</p>";
        	  $(".ehcart_wrap-title").html(str);
        	  
        	  var pieLegend = []; 
        	  $.each(data.bean.pie,function(k,p){
        	  	pieLegend.push(p.name);
        	  });
						option = {
							    backgroundColor: 'inhert',
							    tooltip : {
							        trigger: 'item',
							        formatter: "{b} {c} ({d}%)"
							    },
							   legend: {
							        bottom: 11,
							        itemGap:5,
							        itemWidth:16,
							        itemHeight:12,
							        itemGap: 6,
							        data: pieLegend,
								    textStyle: {
									   fontSize:13,
								    },
							    },
							    series : [
							        {
							            name:'',
							            type:'pie',
							            radius : '58%',
							            center: ['46%', '49%'],
                                        startAngle:200,
                                        color: colorList,
                                        label: {
                                            normal: {
                                                fontSize:14,
                                            },
                                        },
							            data:data.bean.pie.sort(function (a, b) { return a.value - b.value; }),
							            itemStyle: {
							                normal: {
							                    shadowBlur: 20,
							                    shadowColor: 'rgba(0, 0, 0, 0.1)',
							                }
							            },
							
							            animationType: 'scale',
							            animationEasing: 'elasticOut',
							            animationDelay: function (idx) {
							                return Math.random() * 200;
							            }
							        }
							    ]
						};
						pieChart.setOption(option);
						window.onresize = pieChart.resize;
        }
   }
	}
	if(index==1){
		if(data.backCode == 0){	
	//柱状图
			var option2 = {
				backgroundColor: 'inhert',
			    title: {
			        text: '',
			        subtext: '',
			        sublink: ''
			    },
			    grid: [{//柱状图
			        y:'28%',
			        y2: 40,
			        x: 40,
			        x2:40,
			        containLabel: true
			     }, {//折线图
			        y:26,
			        y2: '66%',
			        x: 40,
			        x2:40,
			        containLabel: true
			     }],
			    background:"#FFF",
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'shadow'
			        },
			        formatter: function(params) {
						  var w = "";
						  $.each(data.bean.labels,function (k,p) {
							  if(params[0].name==p){
							  	w=k;
							  }
                          });
			        	  if(params[0].seriesType == "bar"){
					            var res = '<div style="border:1px solid #c4e8f7;padding:10px;">';
					           // res += '<strong style="color:#FFF;">' + params[0].name + '</strong>'
					            for (var i = 0, l = params.length; i < l; i++) {
                                    res += '<span style="color:#f9e3c9">项目数量&nbsp;&nbsp;<span> ' + data.bean.zsl[w]+'&nbsp;个</span></span>'
                                    res += '<br/><span style="color:#c4e8f7">' + params[i].seriesName.substring(0,5)+ '&nbsp;&nbsp;<span> ' + params[i].value+'&nbsp;亿元</span></span>'
					            }
					            res += '</div>';
					            return res;
					        }
			        	  if(params[0].seriesType == "line"){
					            var res = '<div style="border:1px solid #f9e3c9;padding:10px;">';
					            for (var i = 0, l = params.length; i < l; i++) {
					                res += '<span style="color:#f9e3c9">' + params[i].seriesName.substring(0,5) + '&nbsp;&nbsp;<span> ' + params[i].value+'&nbsp;个</span></span>'
                                    res += '<br/><span style="color:#c4e8f7">项目投资&nbsp;&nbsp;<span> ' +  data.bean.ztz[w]+'&nbsp;亿元</span></span>'
					            }
					            res += '</div>';
					            return res;
					        }
			        }
			    },
			    legend: [{
			        x: 'center',
			        y2:10,
			        show:true,
			        itemWidth:16,
		            itemHeight:12,
		            itemGap: 30,
			        data:[{name:'项目投资 单位：亿',icon:'roundRect'},{name:'项目数量 单位：个'}],
                    textStyle: {
                        fontSize:13,
                    },
			    }],
			    calculable: false,
			    xAxis: [
			        {
		        	  gridIndex:0,
		            type: 'category',
		            data: data.bean.labels,
		        	  axisTick:{
		        		  alignWithLabel:true
		        	  },
		        	  
		        	 axisLabel:{
		        	  	backgroundColor:"#FFF",
		        	  	color:'#333',
		        	  	padding: [2, 10, 2, 10],
		        	  	borderRadius:2,
		        	    //interval:0 //0：表示全部显示不间隔；auto:表示自动根据刻度个数和宽度自动设置间隔个数
		        	 }
			        }, 
			        {
			        	show:false,
			        	gridIndex:1,
		            type: 'category',
		            data: data.bean.labels,
			        }
			    ],
			    yAxis: [
			        {
			        	gridIndex:0,
			            type: 'value',
			           
			        } ,
			         {
							    show: false,
							    gridIndex: 1,
							    type: "value",
							    name: "项目数量 单位:个",
							    splitLine: {
							        show: false
							    },
							    axisLine: {
							        show: false
							    },
							    axisTick: {
							        show: false
							    }
							}
			    ],
			    series: [
			        {
			        	xAxisIndex: 0,
                yAxisIndex: 0,
			            name: '项目投资 单位：亿',
			            type: 'bar',
			            itemStyle: {
						      normal: {
						        color: colorList[0]
							}
							},
				            data: data.bean.ztz
				      },
				      {
          
            symbolSize:4,
            name: "项目数量 单位：个",
            xAxisIndex: 1,
            yAxisIndex: 1,
            type: "line",
            yAxisIndex: 1,
            label: {
                normal: {
                    show: true,
                    fontSize:15,
                },

            },
            data: data.bean.zsl,
           itemStyle : {
                    normal : {
                    	  borderWidth: 2,
                        color:'#FF0000',
                        lineStyle:{
             						color:'#f19a31'},
                    }
                }
        }
			    ]
			};
    barChart.setOption(option2);
    window.onresize = barChart.resize;
		}
	}
}


