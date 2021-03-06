<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
</head>
<style>
    .main{background: #fbffff;}
    .bottom{margin-top: 0px;}
    .table tr,td{text-align: center;vertical-align: middle !important;font-size: 16px;
        color: #000;}
    .total td{color:red !important;}
    .xymc{font-size: 20px;}
    .xypm{font-size: 32px;color: #6c9d9c !important;}
    .table-title{width: 100%;text-align: center;overflow: hidden;font-size: 26px;    background: #008784;
        color: #fff;
        padding: 15px;border-radius: 35px 35px 0px 0px;}
    .year{color: #f5b900;
        font-style: oblique;
        font-weight: bold;
        font-size: 32px;
        position: relative;
        top: 2px;
        right: 5px;}
</style>

<body>
<#include "com/header.ftl">
<div class="main">
    <div class="container">
        <div class="routes">
            <div class="location">
                <i></i> 当前位置：
            </div>

            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li class="active">就业排行榜</li>
            </ol>
            <#--<div class="form-group" style="float: right;">-->
                <#--<label for="year" class="col-sm-2 col-sm-offset-8 control-label text-right" style="line-height: 34px;padding-right: 0;margin-left: 0;width: 100px;">选择年份：</label>-->
                <#--<div class="col-sm-2" style="padding-left: 0;padding-right: 0;width: 100px;">-->
                    <#--<select class="form-control" onchange="jypmList()" name="year" id="year">-->
                        <#--<option value="">请选择</option>-->

                    <#--</select>-->
                <#--</div>-->
            <#--</div>-->
        </div>

        <div class="content" style="margin-top: -20px;">
            <div class="table-title">浙江科技学院就业排行榜</div>
            <table class="table table-bordered" style="margin-top: 0px;background: #fff;">
                <thead class="thead1">
                <tr>
                    <th>学院名称</th>
                    <th>专业名称</th>
                    <th>毕业生人数</th>
                    <th>就业人数</th>
                <#--<th>应聘数</th>
                <th>签约率</th>-->
                    <th>就业率</th>
                    <th>排名</th>
                </tr>
                </thead>
                <tbody>
                <#--<tr>
                    <td colspan="8"></td>
                </tr>
                <tr>
                    <td rowspan="3" class="xymc">中德学院</td>
                    <td>国际班</td>
                    <td>66</td>
                    <td>66</td>
                    <td>0</td>
                    <td>100%</td>
                    <td>100%</td>
                    <td rowspan="3" class="xypm">1</td>
                </tr>
                <tr>
                    <td>国际班</td>
                    <td>66</td>
                    <td>66</td>
                    <td>0</td>
                    <td>100%</td>
                    <td>100%</td>
                </tr>
                <tr>
                    <td>合计</td>
                    <td>66</td>
                    <td>66</td>
                    <td>0</td>
                    <td>100%</td>
                    <td>100%</td>
                </tr>

                <tr>
                    <td colspan="8"></td>
                </tr>-->
                </tbody>
            </table>
        </div>

    </div>
</div>

<#include "com/footer.ftl">
    <script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>

    <script>
        $(document).ready(function () {
                jypmList()
//            getRecentYears()
        })

        /*function getRecentYears() {
            beginLoad()
            ajax("zustjy/bckjBizJypm/getRecentYears", {}, function (data) {
                if(data.backCode==0){
                    if(data.bean && data.bean.recentYears && data.bean.recentYears.length>0){
                        for(var i=0;i<data.bean.recentYears.length;i++){
                            if(i==0){
                                $("#year").append('<option value="'+data.bean.recentYears[i]+'" selected>'+data.bean.recentYears[i]+'</option>')
                            }else{
                                $("#year").append('<option value="'+data.bean.recentYears[i]+'">'+data.bean.recentYears[i]+'</option>')
                            }
                        }
                        jypmList()
                    }
                }else{
                    walert(data.errorMess)
                }
            })
        }*/

        function jypmList() {
//            if(!$("#year").val()){
//                walert("请选择年份")
//                return
//            }
//            $("#year1").html($("#year").val())
            $(".table tbody").html("")
            var jsonObj={
//                "pmnf":$("#year").val()
            }
            beginLoad()
            ajax("zustjy/bckjBizJypm/jypmList", jsonObj, function (data) {
                if(data.backCode==0){
                    finishLoad()
                    if(data.bean && data.bean.length>0){
                        $.each(data.bean,function (k,p) {
                            var str='';
                            $.each(p.pmzyList,function (m,n) {
                                if(m==0){
                                    if(k!=0){
                                        str='<tr>\n' +
                                                '<td rowspan="'+p.pmzyList.length+'" class="xymc">'+p.szxy+'</td>\n' +
                                                '<td>'+n.pmzy+'</td>\n' +
                                                '<td>'+n.pmbyrs+'</td>\n' +
                                                '<td>'+n.pmqyrs+'</td>\n' +
                                                /*'<td>'+n.pmyprs+'</td>\n' +
                                                '<td>'+n.pmqyl+'%</td>\n' +*/
                                                '<td>'+n.pmjyl+'%</td>\n' +
                                                '<td rowspan="'+p.pmzyList.length+'" class="xypm">'+parseInt(k)+'</td>\n' +
                                                '</tr>'
                                    }else{//第一行合计
                                        str='<tr style="background: #fff8e2;">\n' +
                                                '<td rowspan="'+p.pmzyList.length+'" class="xymc">'+p.szxy+'</td>\n' +
                                                '<td>'+n.pmzy+'</td>\n' +
                                                '<td>'+n.pmbyrs+'</td>\n' +
                                                '<td>'+n.pmqyrs+'</td>\n' +
                                                /*'<td>'+n.pmyprs+'</td>\n' +
                                                '<td>'+n.pmqyl+'%</td>\n' +*/
                                                '<td>'+n.pmjyl+'%</td>\n' +
                                                '<td rowspan="'+p.pmzyList.length+'" class="xypm"></td>\n' +
                                                '</tr>'
                                    }

                                }else{
                                    var totalClass='';
                                    if(n.pmzy=="合计"){
                                        totalClass='total';
                                    }else{
                                        totalClass='';
                                    }
                                    str+='<tr class="'+totalClass+'">\n' +
                                            '<td>'+n.pmzy+'</td>\n' +
                                            '<td>'+n.pmbyrs+'</td>\n' +
                                            '<td>'+n.pmqyrs+'</td>\n' +
                                            /*'<td>'+n.pmyprs+'</td>\n' +
                                            '<td>'+n.pmqyl+'%</td>\n' +*/
                                            '<td>'+n.pmjyl+'%</td>\n' +
                                            '</tr>'
                                }

                            })
                            if(k<data.bean.length-1){
                                str+='<tr>\n' +
                                        '<td colspan="8"></td>\n' +
                                        '</tr>'
                            }

                            $(".table tbody").append(str);
                        })
                    }else{
                        layer.open({
                            title: '提示',
                            scrollbar: false,
                            content: '暂无数据'
                        });
                    }
                }else{
                    walert(data.errorMess)
                }
            })
        }
    </script>
</body>

</html>