var colorList = ['#ef8947','#5390ef','rgb(161,181,48)','rgb(157,147,128)','#E5367E','#609F9E','#FF6666','#CC99CC','#747F9F','#149F2E','#909F4F','#9F6D20','#6C9F8A'];
function pieChart(dom,dataPie,title,legendData){
    pieOption = {
        backgroundColor: 'inhert',
        tooltip: {
            trigger: 'item',
            formatter: "{b} ({d}%)" //"{b} : {c} ({d}%)"
        },
        legend: {
            x: 'left',
            left: 'center',
            show: true,
            data: legendData,
            bottom: 5,
            itemGap:5,
            itemWidth:8,
            itemHeight:8,
            textStyle:{
                fontSize:13
            }
        },
        title: {
            text:title,
            left:'20',
            top:20,
            subtext: '',
            sublink: '',
            textStyle:{
                color:'#333',
                fontStyle:'lighter',
                fontSize:19
            }
        },
        series: [
            {
                name: '项目政策总览',
                type: 'pie',
                radius: '50%',
                center: ['50%', '46%'],
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
    dom.setOption(pieOption);
    window.onresize = dom.resize;
}

function barChart(dom,axisLine,seriesData){
    option5 = {
        title: {
            text: '',
            subtext: '',
            sublink: ''
        },
        grid: {
            y: 46,
            y2: 20,
            x: 60,
            x2: 20
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
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
                data: axisLine,
                axisTick: {
                    inside: true
                },
                axisLabel: {
                    show: true
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
    dom.setOption(option5);
    window.onresize = dom.resize;
}
