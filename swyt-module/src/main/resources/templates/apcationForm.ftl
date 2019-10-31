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
            font-family: SimHei;
            margin: 0 auto;
            width: 794px;
        }

        body {
            font-family: SimHei;
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
            width: 16cm;
            /*height: 29.7cm;*/
            padding: .5cm 1cm;
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
            padding: 9px;
        }
    </style>
</head>
<body>
<div class="page">
    <table width="100%" align="center" style="border-collapse:collapse">
        <tr>
            <td align="center">
                <h1 style="margin: 8px; font-style: initial;">浙江科技学院“三位一体”综合评价招生申请表</h1>
            </td>
        </tr>
    </table>
    <div style="font-style: normal;font-weight: 400;text-align: center;">
        <span>学科类别：</span><span style="margin-right: 20px;">${xklb}</span>
        <span>外语语种：</span><span style="margin-right: 20px;">${yzmc}</span>
        <span>类别：</span><span style="margin-right: 20px;">${bklb}</span>
        <span>招生专业：</span><span style="margin-right: 20px;">${xzzymc}</span>
    </div>
    <table width="100%" border="1" align="center" cellpadding="0" cellspacing="0"
           style="margin-top: 10px;border-collapse:collapse">
        <tr>
            <td align="center" colspan="1" valign="middle" class="contentFont han" style="width: 120px;">姓名
            </td>
            <td align="center" colspan="2" valign="middle" class="contentFont han" style="width: 150px;">
            ${xm}
            </td>
            <td align="center" colspan="1" valign="middle" class="contentFont han" style="width: 120px;">性别
            </td>
            <td align="center" colspan="2" valign="middle" class="contentFont han" style="width: 150px;">
            ${xbStr}
            </td>
            <td align="center" colspan="1" valign="middle" class="contentFont han" style="width: 120px;">民族
            </td>
            <td align="center" colspan="2" valign="middle" class="contentFont han" style="width: 150px;">
            ${mz}
            </td>
            <td align="center" colspan="2" rowspan="3" valign="middle" class="contentFont han" style="width: 200px;">
                请粘贴一寸照
            </td>
        </tr>
        <tr>
            <td align="center" colspan="1" valign="middle" class="contentFont han" style="width: 160px;">QQ
            </td>
            <td align="center" colspan="3" valign="middle" class="contentFont han" style="width: 300px;">
            ${qq}
            </td>
            <td align="center" colspan="1" valign="middle" class="contentFont han" style="width: 180px;">电子邮箱</td>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 300px;">
            ${yx}
            </td>
        </tr>
        <tr>
            <td align="center" colspan="1" valign="middle" class="contentFont han" style="width: 160px;">手机号
            </td>
            <td align="center" colspan="3" valign="middle" class="contentFont han" style="width: 300px;">
            ${lxdh}
            </td>
            <td align="center" colspan="1" valign="middle" class="contentFont han" style="width: 180px;">身份证号
            </td>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 300px;">
            ${sfzh}
            </td>
        </tr>
        <tr>
            <td align="center" colspan="1" valign="middle" class="contentFont han" style="width: 160px;">家庭地址
            </td>
            <td align="center" colspan="10" valign="middle" class="contentFont han" style="width: 300px;">
            ${jtzz}
            </td>
        </tr>
        <tr>
            <td align="center" colspan="12" valign="middle" class="contentFont han" style="width: 120px;">学考等第
            </td>
        </tr>
        <tr>
            <td align="center" valign="middle" class="contentFont han" style="width: 278px;">
                科目
            </td>
              <#list hkList as hk>
          <td align="center" valign="middle" class="contentFont han" style="width: 120px;">
              ${hk.kmmc}
          </td>
              </#list>
        </tr>
        <tr>
            <td align="center" valign="middle" class="contentFont han" style="width: 278px;">
                等第
            </td>
            <#list hkList as hk>
            <td align="center" valign="middle" class="contentFont han" style="width: 120px;">
                ${hk.kmdj}
            </td>
            </#list>
        </tr>
        <tr>
            <td align="center" colspan="12" valign="middle" class="contentFont han" style="width: 120px;">综合素质评价
            </td>
        </tr>
		<tr>
		    <td align="center" colspan="1" valign="middle" class="contentFont han" style="width: 133px;">
		        ${zcList[0].kmmc}
		    </td>
		    <td align="center" valign="middle" class="contentFont han" style="width: 65px;">
		        ${zcList[0].kmdj}
		    </td>
		    <td align="center" colspan="2" valign="middle" class="contentFont han" style="width: 133px;">
		        ${zcList[1].kmmc}
		    </td>
		    <td align="center"  valign="middle" class="contentFont han" style="width: 65px;">
		        ${zcList[1].kmdj}
		    </td>
		    <td align="center" colspan="2" valign="middle" class="contentFont han" style="width: 133px;">
		        ${zcList[2].kmmc}
		    </td>
		    <td align="center" valign="middle" class="contentFont han" style="width: 65px;">
		        ${zcList[2].kmdj}
		    </td>
		    <td align="center" colspan="2" valign="middle" class="contentFont han" style="width: 133px;">
		        ${zcList[3].kmmc}
		    </td>
		    <td align="center" valign="middle" class="contentFont han" style="width: 65px;">
		        ${zcList[3].kmdj}
		    </td>
		</tr>
        <tr>
            <td align="center" colspan="2" valign="middle" class="contentFont han" style="width: 133px;">
                选考成绩
            </td>
                 <#list xkList as xk>
            <td align="center" colspan="2" valign="middle" class="contentFont han" style="width: 65px;">
                ${xk.kmmc}
            </td>
            <td align="center" valign="middle" class="contentFont han" style="width: 133px;">
                ${xk.kmcj}
            </td>
                 </#list>
        </tr>
        <tr>
            <td align="center" colspan="6" valign="middle" class="contentFont han" style="width: 133px;">
                外语成绩
            </td>
            <td align="center" colspan="6" valign="middle" class="contentFont han" style="width: 65px;">
            ${wycj}
            </td>
        </tr>
        <tr>
            <td align="center" colspan="12" valign="middle" class="contentFont han" style="width: 120px;">
                专项条件（严格按招生章程中的5类条件填写）
            </td>
        </tr>
        <tr>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 300px;">
                专项类别（可多选）
            </td>
            <td align="center" colspan="8" valign="middle" class="contentFont han" style="width: 425px;">
            ${zxlb}
            </td>
        </tr>
        <tr>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 300px;">
                高中阶段参加的竞赛类别、竞赛名称、时间、竞赛级别、取得名次
            </td>
            <td align="center" colspan="8" valign="middle" class="contentFont han" style="width: 425px;">
            ${jssm}
            </td>
        </tr>
        <tr>
            <td align="center" colspan="12" valign="middle" class="contentFont han" style="width: 120px;">其他情况
            </td>
        </tr>
        <tr>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 300px;">
                高中阶段参加的社会工作和课外活动（含活动时间、受过何种奖励、本人在活动中的职务或职责）
            </td>
            <td align="center" colspan="8" valign="middle" class="contentFont han" style="width: 425px;">
            ${qtqk}
            </td>
        </tr>
        <tr>
            <td align="center" colspan="4" valign="middle" class="contentFont han" style="width: 300px;">
                特长和爱好
            </td>
            <td align="center" colspan="8" valign="middle" class="contentFont han" style="width: 425px;">
            ${tcah}
            </td>
        </tr>
    </table>
    <div style="text-align: right;margin-right: 150px;font-style: normal;margin-top: 10px;">本人签名：</div>
</div>
</body>
</html>