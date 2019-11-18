<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="Content-Style-Type" content="text/css"/>
    <title></title>
    <#assign base=request.contextPath />
    <style mce_bogus="1" type="text/css">
        body{user-select: none; font-size:12px; color:#000000;font-family:pingfang sc light;margin: 0 auto;}
        body {font-family: pingfang sc light;font-weight:bold;background:none;margin-left: auto;margin-right: auto;}
        .page {
            /*width: 21cm;*/
            width: 29.7cm;
            /*height: 21cm;*/
            padding: 1cm;
            margin: 1cm auto;
            border: 1px #D3D3D3 solid;
            border-radius: 5px;
            background: white;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            position: relative;
        }
        .noprint h6{margin: 0px;font-style: normal;color: #333;text-align: center}
        .noprint{position: fixed;bottom: 50px;right: 100px;text-decoration: none;z-index: 99999;width: 70px;height: 70px;border-radius: 50%;background: rgba(0,0,0,0.1)}
        .noprint img{width: 39px;margin: 0 auto;display: block;margin-top: 13px;}
        .name{padding: 10px 20px;border-bottom:1px solid #000;}
        .zy{padding: 0px 100px 5px;border-bottom:1px solid #000;}
        @page {
            /*size: A4;*/
            size: landscape;
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
            .noprint{display:none;}
        }
    </style>
    <script type="text/javascript">
        function doPrint() {
            bdhtml=window.document.body.innerHTML;
            sprnstr="<!--startprint-->";
            eprnstr="<!--endprint-->";
            prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
            console.log(prnhtml);
            prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
            console.log(prnhtml);
            window.document.body.innerHTML=prnhtml;
            window.print();
        }
    </script>
</head>
<body>
<!--startprint-->
<!--第一页开始-->
<div class="page" >
    <a href="javascript:doPrint()" class="noprint">
        <img src="${base}/img/print.png"/>
        <h6>打印</h6>
    </a>
    <table width="100%" align="center" style="margin-top: 60px;border-collapse:collapse" >
        <tr>
            <td align="center"  valign="middle" style="font-weight:bold;font-size:30px;color:#000000;font-style: normal;">
                录取通知书
            </td>
        </tr>
    </table>
    <#if result??>
        <p style="margin: 0;padding-top: 100px;font-size: 25px;line-height: 100px;width: 90%;margin-left: 5%;">
            <a class="name">${result.xm!''}</a>同学：<br/>
            祝贺你被我校<a class="zy">${result.lqzy!''}</a>录取！最终录取请查询当地考试院。
            <#if result.jcsj?exists>
                录取通知单已经于${result.jcsj?substring(0,10)}寄出
            </#if>
            <#if result.ems?exists>
                EMS单号：${result.ems!''}，请注意查收！！！
            </#if>


        </p>
    </#if>

</div>
<!--endprint-->
</body>
</html>