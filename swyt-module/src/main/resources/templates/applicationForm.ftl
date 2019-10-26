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
            size: A4 landscape;
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

        .titleFont {
            font-size: 20px;
            color: #000000;
            font-style: normal;
            width: 200px;
        }

        .contentFont {
            font-size: 14px;
            font-style: normal;
        }

        .han {
            margin: 10px;
            padding: 12px;
        }
    </style>
    <script type="text/javascript">
        var projectTitle = "${mianInfo.projectTitle}";
        var projectRefOwid = "${projectRefOwid}";
        var reviewListRefId = "${reviewListRefId}";
        var planRefId = "${planRefId}";

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
            <td align="center">
                <h1 style="font-style: initial;">浙江科技学院“三位一体”综合评价招生申请表</h1>
            </td>
        </tr>

    </table>
    <table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" style="margin-top: 10px;border-collapse:collapse">
        <tr>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">姓名
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 150px;">
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">性别
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 150px;">
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">民族
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 150px;">
            </td>
            <td align="center"  rowspan="4" valign="middle" class="contentFont han" style="width: 200px;">
            </td>
        </tr>
        <tr>
            <td align="center" valign="middle" class="contentFont han" style="width: 160px;">QQ
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 300px;">
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 180px;">电子邮箱</td>
            <td align="center" colspan="3" valign="middle" class="contentFont han" style="width: 300px;">
            </td>
        </tr>
        <tr>
            <td align="center" valign="middle" class="contentFont han" style="width: 160px;">手机号
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 300px;">
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 180px;">身份证号
            </td>
            <td align="center" colspan="3" valign="middle" class="contentFont han" style="width: 300px;">
            </td>
        </tr>
        <tr>
            <td align="center" valign="middle" class="contentFont han" style="width: 160px;">家庭地址
            </td>
            <td align="center" colspan="5" valign="middle" class="contentFont han" style="width: 300px;">
            </td>
        </tr>
    </table>
    <table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" style="margin-top: 10px;border-collapse:collapse">
        <tr>
            <td align="center" colspan="12" valign="middle" class="contentFont han" style="width: 120px;">学考等第
            </td>
        </tr>
        <tr>
            <td align="center" valign="middle" class="contentFont han" style="width: 278px;">
                科目
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 120px;">
                历史
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 120px;">
                地理
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 120px;">
                技术
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">
                英语
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">
                物理
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">
                化学
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">
                思想政治
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">
                生物
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">
                语文
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">
                数学
            </td>
        </tr>
        <tr>
            <td align="center" valign="middle" class="contentFont han" style="width: 278px;">
                等第
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 120px;">
                A
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 120px;">
                B
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 120px;">
                A
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 120px;">
                B
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">
                A
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">
                B
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">
                A
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">
                B
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">
                A
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">
                B
            </td>
        </tr>
    </table>
    <table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" style="margin-top: 10px;border-collapse:collapse">
        <tr>
            <td align="center" colspan="12" valign="middle" class="contentFont han" style="width: 120px;">综合素质评价
            </td>
        </tr>
        <tr>
            <td align="center" valign="middle" class="contentFont han" style="width: 133px;">
                品德表现
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 65px;">
                A
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 133px;">
                运动健康
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 65px;">
                B
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 133px;">
                艺术素养
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 65px;">
                A
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 133px;">
                创新实践
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 65px;">
                B
            </td>
        </tr>
        <tr>
            <td align="center" colspan="2" valign="middle" class="contentFont han" style="width: 133px;">
                选考成绩
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 65px;">
                物理
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 133px;">
                95
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 65px;">
                化学
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 133px;">
                90
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 65px;">
                生物
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 133px;">
                88
            </td>
        </tr>
        <tr>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 133px;">
                外语成绩
            </td>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 65px;">
                100
            </td>
        </tr>
    </table>
    <table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" style="margin-top: 10px;border-collapse:collapse">
        <tr>
            <td align="center" colspan="8" valign="middle" class="contentFont han" style="width: 120px;">专项条件（严格按招生章程中的5类条件填写）
            </td>
        </tr>
        <tr>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 133px;">
                专项类别（可多选）
            </td>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 425px;">
                学科竞赛类、科技创新类、语言文学特长类
            </td>
        </tr>
        <tr>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 133px;">
                高中阶段参加的竞赛类别、竞赛名称、时间、竞赛级别、取得名次
            </td>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 425px;">

            </td>
        </tr>
        <tr>
            <td align="center" colspan="8" valign="middle" class="contentFont han" style="width: 120px;">其他情况
            </td>
        </tr>
        <tr>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 133px;">
                高中阶段参加的社会工作和课外活动（含活动时间、受过何种奖励、本人在活动中的职务或职责）
            </td>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 425px;">

            </td>
        </tr>
        <tr>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 133px;">
                特长和爱好
            </td>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 425px;">

            </td>
        </tr>
    </table>
</div>
<!--endprint-->
</body>
</html>