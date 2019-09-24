<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>就业网</title>
    <#include "com/config.ftl">
</head>
<style>
    .table tr,td{text-align: center;vertical-align: middle !important;font-size: 16px;
        color: #000;}
    .total td{color:red !important;}
    .xymc{font-size: 20px;}
    .xypm{font-size: 32px;color: #6c9d9c !important;}
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
                <li><a href="#">首页</a></li>
                <li class="active">就业排行榜</li>
            </ol>
        </div>
        <div class="content">
            <table class="table table-bordered">
                <thead class="thead1">
                <tr>
                    <th>学院名称</th>
                    <th>专业名称</th>
                    <th>毕业生人数</th>
                    <th>签约数</th>
                    <th>应聘数</th>
                    <th>签约率</th>
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
            getRecentYears()
        })

        function getRecentYears() {
            var jsonObj={
            }
            beginLoad()
            ajax("zustjy/bckjBizJypm/getRecentYears", jsonObj, function (data) {
                if(data.backCode==0){

                }else{
                    walert(data.errorMess)
                }
            })
        }

        function jypmList() {
            $(".table tbody").html("")
            var jsonObj={
                "pmnf":(new Date()).getFullYear()
            }
            beginLoad()
            ajax("zustjy/bckjBizJypm/jypmList", jsonObj, function (data) {
                if(data.backCode==0){
                    finishLoad()
                    if(data.bean.records){
                        $.each(data.bean.records,function (k,p) {
                            var str='';
                            $.each(p.pmzyList,function (m,n) {
                                if(m==0){
                                    str='<tr>\n' +
                                            '<td rowspan="'+p.pmzyList.length+'" class="xymc">'+p.szxy+'</td>\n' +
                                            '<td>'+n.pmzy+'</td>\n' +
                                            '<td>'+n.pmbyrs+'</td>\n' +
                                            '<td>'+n.pmqyrs+'</td>\n' +
                                            '<td>'+n.pmyprs+'</td>\n' +
                                            '<td>'+n.pmqyl+'%</td>\n' +
                                            '<td>'+n.pmjyl+'%</td>\n' +
                                            '<td rowspan="'+p.pmzyList.length+'" class="xypm">'+parseInt(k+1)+'</td>\n' +
                                            '</tr>'
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
                                            '<td>'+n.pmyprs+'</td>\n' +
                                            '<td>'+n.pmqyl+'%</td>\n' +
                                            '<td>'+n.pmjyl+'%</td>\n' +
                                            '</tr>'
                                }

                            })
                            str+='<tr>\n' +
                                    '<td colspan="8"></td>\n' +
                                    '</tr>'
                            $(".table tbody").append(str);
                        })
                    }
                }else{
                    walert(data.errorMess)
                }
            })
        }
    </script>
</body>

</html>