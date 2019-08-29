 /*
 time:2019/3/25 version:01
 Author：2515421994@qq.com
 function:全国地图进入初始化
 detail"处理全国地图和省级地图的变化关系
 */

var myChart = echarts.init(document.getElementById('china-map'));
var geoCoordMap = {};//省份（区市）经纬度组成的对象
var seriesData = [];
var colorArr = [];
 var visualMax = "";//init 颜色控制 不同地方省或区数量不一致
//对应省份的JS文件名称 用于取对应的文件
var provinces = ['shanghai', 'hebei', 'shanxi', 'neimenggu', 'liaoning', 'jilin', 'heilongjiang', 'jiangsu', 'zhejiang', 'anhui', 'fujian', 'jiangxi', 'shandong', 'henan', 'hubei', 'hunan', 'guangdong', 'guangxi', 'hainan', 'sichuan', 'guizhou', 'yunnan', 'xizang', 'shanxi1', 'gansu', 'qinghai', 'ningxia', 'xinjiang', 'beijing', 'tianjin', 'chongqing', 'xianggang', 'aomen'];
//对应省份文件键值为中文
var provincesText = ['上海', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '江苏', '浙江', '安徽', '福建', '江西', '山东', '河南', '湖北', '湖南', '广东', '广西', '海南', '四川', '贵州', '云南', '西藏', '陕西', '甘肃', '青海', '宁夏', '新疆', '北京', '天津', '重庆', '香港', '澳门'];

$(function(){
	initEcharts("china", "中国");
});

// 初始化echarts
function initEcharts(pName, Chinese_) {

    if(Chinese_ == "中国"){
       $("#cityName").hide();
    }
	var mapFeatures = echarts.getMap(pName).geoJson.features;
	var tmpSeriesData = "";
	mapFeatures.forEach(function(v) {
		// 地区名称
		var name = v.properties.name;
		// 地区经纬度
		geoCoordMap[name] = v.properties.cp;
		//console.log(v.properties.name+"**"+v.properties.cp);
	});	
   
	tmpSeriesData = pName === "china" ? seriesData1 : seriesData;

	//var tmpSeriesData = pName = seriesData ;
	//console.log(tmpSeriesData);
	var convertData = function(data) {
		var res = [];
		for(var i = 0; i < data.length; i++) {
			var geoCoord = geoCoordMap[data[i].name];
			if(geoCoord) {
				res.push({
					name: data[i].name,
					value: geoCoord.concat(data[i].value),
                    cityId:data[i].cityId
				});
			}
		}
		//console.log("转换结果"+JSON.stringify(res));
		return res;
	};

	var option = {
		/*title: {
			text: Chinese_ || pName,
			left: 'center'
		},*/
		visualMap: {
			min: 0,
			max: (pName === "china" ? visualMaxChina : visualMax),
			left: 'left',
			top: 'bottom',
			text: ['高', '低'],
			calculable: true,
			show: false,
			seriesIndex: [1],
			inRange: {
				color: (pName === "china" ? colorArrChina : colorArr)
			}
			//北京：1      四川：2    河南：3     内蒙：4     安徽：5    新疆：6     福建：7 
		},
		tooltip: {
			trigger: 'item',
			formatter: '{b}<br/>'
		},
		geo: {
			map: pName || Chinese_,
			roam: true,
			top:80,
			zoom:1.2,
			label: {
				emphasis: {
					show: false
				}
			},
			roam: false,

			itemStyle: {
				normal: {
					borderWidth: pName != "china" ? 0 : 0.5, //区域边框宽度
					borderColor: '#eef2f4', //区域边框颜色
					areaColor: "#6acbcc", //区域颜色

				},

				emphasis: {
					borderWidth: pName != "china" ? 0 : 0.5,
					borderColor: '#eef2f4',
					areaColor: "#009cff",
				}
			}
		},
		series: [{
				type: 'scatter',
				coordinateSystem: 'geo',
				data: convertData(tmpSeriesData),
				symbolSize: 5,
				symbolRotate: 35,
				label: {
					normal: {
						formatter: '{b}',
						position: 'right',
						show: true,
						textStyle: {
							color: "#333",
							fontSize:pName === "china" ? 13 : 14,
						}
					},
					emphasis: {
						show: true
					}
				},
				itemStyle: {
					normal: {
						color: '#FF0000',

					}
				}
			},
			{
				type: 'map',
				map: Chinese_ || pName,
				mapType: pName,
				geoIndex: 0,
				top: "3%", //组件距离容器的距离
				zoom: 1.1,
				selectedMode: 'single',
				showLegendSymbol: false, // 存在legend时显示
				label: {
					normal: {
		                      textStyle: {
		                         // 用 itemStyle.normal.label.textStyle.fontSize 來更改餅圖上項目字體大小
		                        fontSize:22,
		                        fontWeight: 'bold',
                                color: 'red'
		                      },
						show: true
					},
					emphasis: {
						show: false,
						textStyle: {
							color: '#fff',
							fontsize:20
						}
					}
				},
				roam: false,

				animation: true,
				data: tmpSeriesData
			}
		]

	};
	if(pName === "内蒙古" ){
    	//增加额外地点标注
    	option.series.push(extraObj,extraSz);
    }
    myChart.clear()
	myChart.setOption(option);
	myChart.off("click");
	
	if(pName === "china") { // 全国时，添加click 进入省级
		myChart.on('click', function(param) {
			//console.log("点击的城市名字"+param.name);
			$("#cityName").html(param.name);
			// 遍历取到provincesText 中的下标  去拿到对应的省js
			for(var i = 0; i < provincesText.length; i++) {
				//console.log(provincesText[i]);
				if(param.name === provincesText[i]) {
					//显示对应省份的方法
                    $("#cityName").show();
					showProvince(provinces[i], provincesText[i]);
					break;
				}
			}
			if(param.componentType === 'series') {
				var provinceName = param.name;
				$('#box').css('display', 'block');
				$("#box-title").html(provinceName);
			}
		});
	} else { // 省份，添加双击 回退到全国
		myChart.on("dblclick", function() {
			$(".china-map").hide();
			initEcharts("china", "中国");
		});
		myChart.on("click", function(param) {
		    if(param.data.cityId){
               window.location.href="../login.htm?cityId="+param.data.cityId;
            }
			//点击跳转在这里 获取区或者城市名称
			//console.log("点击的城市名字"+param.name+"111111");
		});
	}
}



// 展示对应的省
function showProvince(pName, Chinese_) {
	$(".china-map").show();
    removeScript();//移除其他多余省份
	if($("script[src='./js/map/province/"+pName+".js']").length > 0){
        initEcharts(Chinese_);//省份Json以中文为key
        return;
     }else{
     	//这写省份的js都是通过在线构建工具生成的，保存在本地，需要时加载使用即可，最好不要一开始全部直接引入
		loadBdScript('$' + pName + 'JS', '../map/province/' + pName + '.js', function() {
			initEcharts(Chinese_);//省份Json以中文为key
	    });
     }
}

// 加载对应的JS
function loadBdScript(scriptId, url, callback) {
	var script = document.createElement("script");
	script.type = "text/javascript";
	if(script.readyState) { //IE
		script.onreadystatechange = function() {
			if(script.readyState === "loaded" || script.readyState === "complete") {
				script.onreadystatechange = null;
				callback();
			}
		};
	} else { // Others
		script.onload = function() {
			callback();
		};
	}
	script.src = url;
	script.id = scriptId;
	$("head")[0].appendChild(script);
}

//点击返回全国地图
function back(a){
	$(a).hide();
	initEcharts("china", "中国");
};

// 删除多余其他省份文件 只显示目前省份地图
function removeScript(src) {
    var scripts = document.getElementsByTagName("script");
    for (var i = 0; i < scripts.length; i++) {
    	var thisScript = scripts[i];
    	if($(thisScript).attr("id")){
            if($(thisScript).attr("id").indexOf("$")>-1){
                scripts[i].parentNode.removeChild(scripts[i]);
            }
		}

	}
}