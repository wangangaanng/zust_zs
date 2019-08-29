/**
 * Created by Xia Yu on 2018/8/20.
 */
$(function(){
    //var barChart = echarts.init(document.getElementById('first-pie'));
    var pieArr = $(".chart-wrap li");
    var nameArr = ["项目信息","项目手续","项目投资","招标采购","合同备案","项目进度","竣工验收","绩效评价"];
    var axisLine= ["债务率","偿债率","逾期债务率","逾期债务率","综合债务率","债务依存度","待偿率","负债率","债务负担率"];
    var seriesData = [{
        data:[0.6, 0.8, 0.4, 0.14, 0.9, 0.88, 0.54,0.5,0.34],
        type:'bar',
        itemStyle: {
            //通常情况下：
            normal:{
                color: function (params){
                    var colorList = ['#30a8dc','#e08543','#7d9f34','#ee4d4d',"#8f82bc","#9d9380","#fd6565","#6898e2","#5f9c9b"];
                    return colorList[params.dataIndex];
                }
            },
            //鼠标悬停时：
            emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
        },
        barWidth: '38%',
        label: {
            normal: {
                show: true,
                // formatter: '{a}'
            }
        },

    }];

    var  dataPieArr = [[
        {value:20, name:'中央'},
        {value:30, name:'省级'},
        {value:25, name:'市级'},
        {value:25, name:'旗县'}
    ],[
        {value:10, name:'融资平台'},
        {value:20, name:'部门机构'},
        {value:30, name:'事业单位'},
        {value:18, name:'国有企业'},
        {value:22, name:'其它'}
    ],[
        {value:20, name:'银行信贷'},
        {value:30, name:'政府债券'},
        {value:20, name:'企业债券'},
        {value:18, name:'信托'},
        {value:12, name:'其它'}
    ],[
        {value:10, name:'2017'},
        {value:5, name:'2018'},
    ]]
    var legendData = ["债务率","偿债率","联盟广告","逾期债务率","综合债务率","债务依存度","待偿率"]
    var legendDataArr = [["中央","省级","市级","旗县"],["融资平台","部门机构","事业单位","国有企业","其它"],["银行信贷","政府债券","企业债券","信托","其它"],["2017","2018"]];
    var titleArr = ["债务结构","举借主体","债务来源","债务期限"]
    $.each(pieArr,function(k,p){
        pieChart(echarts.init(document.getElementById($(p).attr("id"))),dataPieArr[k],titleArr[k],legendDataArr[k]);
    });

    barChart(echarts.init(document.getElementById("bar-chart")),axisLine,seriesData)
})