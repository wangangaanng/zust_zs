<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="Content-Style-Type" content="text/css"/>
    <title></title>
    <style mce_bogus="1" type="text/css">
        body {
            font-size: 12px;
            color: #000000;
            font-family: pingfang sc light;
            margin: 0 auto;
            width: 794px;
        }

        body {
            font-family: pingfang sc light;
            font-style: italic;
            font-weight: bold;
            background: none;
            margin-left: auto;
            margin-right: auto;
        }

        .bt {
            color: #121212;
            font-size: 26px;
            font-weight: bold;
            line-height: 80px;
            text-align: center;
        }

        .A4 {
            margin: 0 auto;
            width: 794px;
        }

        .f_12 {
            font-size: 12px;
        }

        .f_red {
            color: #F00
        }

        .page {
            width: 21cm;
            /*height: 29.7cm;*/
            padding: 1cm;
            margin: 1cm auto;
            border: 1px #D3D3D3 solid;
            border-radius: 5px;
            background: white;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            position: relative;
        }

        .subpage {
            padding: 1cm;
            border: 5px red solid;
            height: 256mm;
            outline: 2cm #FFEAEA solid;
        }

        .water-print {
            background-color: #FFFFFF;
            background-image: url(img/img_logo2.png);
            background-repeat: repeat;
        }

        .noprint h6 {
            margin: 0px;
            font-style: normal;
            color: #333;
            text-align: center
        }

        .noprint {
            position: fixed;
            bottom: 50px;
            right: 100px;
            text-decoration: none;
            z-index: 99999;
            width: 70px;
            height: 70px;
            border-radius: 50%;
            background: rgba(0, 0, 0, 0.1)
        }

        .noprint img {
            width: 39px;
            margin: 0 auto;
            display: block;
            margin-top: 13px;
        }

        @page {
            size: A4;
            margin: 0;
        }

        @media print {
            .page {
                margin: 0;
                border: initial;
                border-radius: initial;
                width: initial;
                min-height: initial;
                box-shadow: initial;
                background: initial;
                page-break-after: always;
            }

            .noprint {
                display: none;
            }
        }
    </style>
    <script type="text/javascript">
        var projectOwid = "${projectInfo.owid}";
        var cgtypes = "${cgtypes}";
        var httypes = "${httypes}";
        var zctypes = "${zctypes}";
        var fjlb = "${fjlb}";


        function getRequest() {
            var url = window.location.search; //获取url中"?"符后的字串
            console.log(url)
            var theRequest = new Object();
            if (url.indexOf("?") != -1) {
                var str = url.substr(1);
                strs = str.split("&");
                for (var i = 0; i < strs.length; i++) {
                    //就是这句的问题
                    theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
                    //之前用了unescape()
                    //才会出现乱码
                }
            }
            return theRequest;
        }

        //        function refresh(){
        //            window.location.href="report.htm?owid="+projectOwid+"&cgtypes="+cgtypes+"&httypes="+httypes+"&zctypes="+zctypes+"&fjlb="+fjlb;
        //        }


        function doPrint() {
            bdhtml = window.document.body.innerHTML;
            sprnstr = "<!--startprint-->";
            eprnstr = "<!--endprint-->";
            prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
            console.log(prnhtml);
            prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
            console.log(prnhtml);
            window.document.body.innerHTML = prnhtml;
            window.print();
        }
        function openmx() {
            if (parseInt(fjlb) == 1)
                fjlb = "0";
            else
                fjlb = "1";
            refresh();
        }

    </script>
</head>
<body>
<!--startprint-->
<!--第一页开始-->
<div class="page">
    <a href="javascript:doPrint()" class="noprint">
        <img src="img/print.png"/>
        <h6>打印</h6>
    </a>
    <table width="100%" align="center" style="margin-top: 20px;border-collapse:collapse">
        <tr>
            <td align="left">
                <#--<img src="img/logo.png" width="80px"/>-->
                <img src="charisma/img/zust_logo.jpg" style="margin-left: 88px;" />
            </td>
        </tr>

    </table>
    <table width="100%" align="center" style="margin-top: 60px;border-collapse:collapse">
        <#--<tr>-->
            <#--<td align="center" valign="middle"-->
                <#--style="font-weight:bold;font-size:30px;color:#000000;font-style: normal;">-->
            <#--&lt;#&ndash;内蒙古 . 包头市项目投资报告&ndash;&gt;-->
            <#--${name}-->
            <#--</td>-->
        <#--</tr>-->
    </table>
    <table width="80%" align="center" style="margin-top: 100px;border-collapse:collapse">
        <tr>
            <td align="center" colspan="2" valign="middle" style="text-align: center;">
                <img src="${qrCode}" id="qrCode" style="width: 200px;height: 200px;"/>
            </td>
        </tr>
    </table>
    <table width="80%" align="center" style="margin-top:120px;bottom:15px;border-collapse:collapse">
        <tr>
            <td style="font-size:24px;color:#000000;font-style: normal;text-align: center;align-content: center;">
            <#--${name}-->
                浙江科技学院宣讲会签到二维码
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    document.getElementById('qrCode').src = getRequest().qrCode
</script>

<!--endprint-->
</body>
</html>