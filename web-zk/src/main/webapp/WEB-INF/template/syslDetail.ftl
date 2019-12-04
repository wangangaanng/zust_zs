<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>生源信息</title>
    <link rel="stylesheet" type="text/css" href="bootstrap/v3/bootstrap/css/bootstrap.min.css">
    <style>
        .h3{padding: 10px;text-align: center;}
        a:hover{text-decoration: none;}
        table>tbody>tr>td{vertical-align: middle !important;}
        .btn-common{
            width: 100px;
            height: 40px;
            background-color: #008784;
            color: #fff;
            border-radius: 4px;
        }
    </style>
</head>

<body>
<div class="main">
    <div class="container">
        <button class="btn btn-common" onclick="saveSy()">保存</button>
        <div class="content">
            <div class="article-detail">

            </div>

        </div>

    </div>
</div>
<script type="text/javascript" src="html/js/jquery-3.2.1.min.js" ></script>
<script type="text/javascript" src="assets/common/func.js"></script>

<script>
    var nf="${nf!''}";
    $(document).ready(function(){
        initTable(1);//本科
    })
    function initTable(lx) {
        if(lx=="1"){
            var params={
                "nf":nf,
                "xlcc":'本',
            }
        }else {
            var params={
                "nf":nf,
                "xlcc":'硕',
            }
        }
        window.parent.ajax("web/zustcommon/bckjBizJyscheme/showJyDyInfo",params,function(data){
            if(data.backCode == 0) {
                var str = '';
                var bt = "";
                var name=""
                if (lx == "1") {
                    name="本科";
                    bt = '浙科院' + nf + '届'+name+'毕业生基本信息'
                } else {
                    name="硕士研究生"
                    bt = '浙科院' + nf + '届'+name+'毕业生基本信息'
                }
                str += '<div class="article-detail-title"><div class="h3">' + bt + '</div></div>' +
                        '<div class="article-detail-text"><table class="table table-bordered" style="text-align: center">' +
                        '<tr><td width="250px">二级学院</td><td>专业名称</td><td>毕业生人数</td><td>联系电话</td><td>联系人</td></tr>';
                if ((data.bean) && (data.bean.length > 0)) {
                    $.each(data.bean, function (k, p) {
                        if ((p.zyList) && (p.zyList.length > 0)) {
                            $.each(p.zyList, function (j, q) {
                                if (j == 0) {
                                    str += '<tr><td rowspan="' + p.zyList.length + '" class="xy" owid="'+p.owid+'">' + p.xsxy + '<br><span>（'+p.xyRsSum+'人）</span></td><td>' + q.zy + '</td><td>' + q.rs + '</td><td rowspan="' + p.zyList.length + '"><input type="text" class="form-control" name="lxfs-input" value="'+base64_decode(p.lxdh||"")+'" /></td><td rowspan="' + p.zyList.length + '"><input type="text" class="form-control" name="lxr-input" value="'+(p.lxr||'')+'" /></td></tr>'
                                } else {
                                    str += '<tr><td>' + q.zy + '</td><td>' + q.rs + '</td></tr>'
                                }

                            })
                        } else {
                            str += '<tr><td class="xy" owid="'+p.owid+'">' + p.xsxy + '</td><td>-</td><td>-</td><td><input type="text" class="form-control" name="lxfs-input" value="'+base64_decode(p.lxdh||"")+'" /></td><td><input type="text" class="form-control" name="lxr-input" value="'+(p.lxr||'') +'" /></td></tr>'
                        }

                    })
                    str+='<tr><td>总 计</td><td>' + data.bean[0].zySum + '个'+name+'专业</td><td>' + data.bean[0].sum + '人</td><td></td><td></td></tr>'
                }
                str += '</table></div>'
                $(".article-detail").append(str);
                if(lx=="1"){
                    initTable(2);//硕士研究生
                }
            }
        })
    }
    function saveSy() {
        var list=[];
        $(".xy").each(function (k,p) {
            var obj=new Object();
            obj.owid= $(p).attr("owid");
            obj.lxfs=$(p).closest("tr").find("input[name='lxfs-input']").val()||"";
            obj.lxr=$(p).closest("tr").find("input[name='lxr-input']").val()||"";
            list.push(obj);
        })
        var params=list
        window.parent.ajax("web/zustcommon/bckjBizJyscheme/saveJyDyInfo",params,function(data) {
            if (data.backCode == 0) {

            }
        })
    }
</script>


</body>

</html>