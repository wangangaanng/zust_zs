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
            width: 17cm;
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
            padding: 8px 12px;
        }
    </style>
</head>
<body>
<!--startprint-->
<!--第一页开始-->
<div class="page">
    <table width="100%" align="center" style="border-collapse:collapse">
        <tr>
            <td align="center">
                <h1 style="font-style: initial;margin: 5px;">浙江科技学院${bmnd}年“三位一体”综合评价招生综合测试通知单</h1>
            </td>
        </tr>
    </table>
	<div style="font-style: normal;font-weight: 400;text-align: center;">
		<span>学科类别：</span><span style="margin-right: 20px;">${xklb}</span>
		<span>外语语种：</span><span style="margin-right: 20px;">${yzmc}</span>
		<span>类别：</span><span style="margin-right: 20px;">${bklb}</span>
		<span>招生专业：</span><span style="margin-right: 20px;">${xzzymc}</span>
	</div>
    <table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" style="margin-top: 10px;border-collapse:collapse">
        <tr>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">姓名
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 150px;">
			${xm}
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">性别
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 150px;">
			${xbStr}
            </td>
			<td align="center"  valign="middle" class="contentFont han" style="width: 150px;">QQ:
			</td>
			<td align="center"  valign="middle" class="contentFont han" style="width: 150px;">
			${qq!''}
			</td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">民族
            </td>
            <td align="center"  valign="middle" class="contentFont han" style="width: 50px;">
			${mz}
            </td>
			<td align="center"  rowspan="4" valign="middle" class="contentFont han" style="width: 300px;">请粘贴一寸照
			</td>
        </tr>
        <tr>
			<td align="center" valign="middle" class="contentFont han" style="width: 180px;">通讯地址</td>
			<td align="center" colspan="3" valign="middle" class="contentFont han" style="width: 300px;">
			${jtzz}
			</td>
            <td align="center" valign="middle" class="contentFont han" style="width: 180px;">电子邮箱</td>
            <td align="center" colspan="3" valign="middle" class="contentFont han" style="width: 300px;">
			${yx}
            </td>
        </tr>
        <tr>
			<td align="center" valign="middle" class="contentFont han" style="width: 180px;">身份证号
			</td>
			<td align="center" colspan="3" valign="middle" class="contentFont han" style="width: 300px;">
			${sfzh}
			</td>
            <td align="center" valign="middle" class="contentFont han" style="width: 160px;">手机号
            </td>
            <td align="center"  colspan="3" valign="middle" class="contentFont han" style="width: 300px;">
			${lxdh}
            </td>
        </tr>
		<tr>
			<td align="center" valign="middle" class="contentFont han" style="width: 180px;">准考证号
			</td>
			<td align="center" colspan="7" valign="middle" class="contentFont han" style="width: 300px;">
			${zkzh}
			</td>
		</tr>
		<tr>
			<td align="center" colspan="9" valign="middle" class="contentFont han" style="width: 180px;">
				祝贺您，您已获得浙江科技学院${bmnd}年“三位一体”综合评价招生综合面试资格。<br>
				您的面试时间为 <span style="color: red;font-size: 16px;">${mssj}</span>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="9" valign="middle" class="contentFont han" style="width: 180px;">
				${mssm}
			</td>
		</tr>
    </table>
	<table width="100%" align="center" style="margin-top: 20px;border-collapse:collapse">
	    <tr>
	        <td align="center">
	            <h1 style="font-style: initial;margin: 5px;">浙江科技学院${bmnd}年“三位一体”综合评价招生综合测试通知单(复联)</h1>
	        </td>
	    </tr>
	</table>
	<div style="font-style: normal;font-weight: 400;text-align: center;">
		<span>学科类别：</span><span style="margin-right: 20px;">${xklb}</span>
		<span>外语语种：</span><span style="margin-right: 20px;">${yzmc}</span>
		<span>类别：</span><span style="margin-right: 20px;">${bklb}</span>
		<span>招生专业：</span><span style="margin-right: 20px;">${xzzymc}</span>
	</div>
	<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" style="margin-top: 10px;border-collapse:collapse">
	    <tr>
	        <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">姓名
	        </td>
	        <td align="center"  valign="middle" class="contentFont han" style="width: 150px;">
				${xm}
	        </td>
	        <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">性别
	        </td>
	        <td align="center"  valign="middle" class="contentFont han" style="width: 150px;">
				${xbStr}
	        </td>
			<td align="center"  valign="middle" class="contentFont han" style="width: 150px;">QQ:
			</td>
			<td align="center"  valign="middle" class="contentFont han" style="width: 150px;">
				${qq}
			</td>
	        <td align="center"  valign="middle" class="contentFont han" style="width: 120px;">民族
	        </td>
	        <td align="center"  valign="middle" class="contentFont han" style="width: 50px;">
				${mz}
	        </td>
			<td align="center"  rowspan="4" valign="middle" class="contentFont han" style="width: 300px;">请粘贴一寸照
			</td>
	    </tr>
	    <tr>
			<td align="center" valign="middle" class="contentFont han" style="width: 180px;">身份证号</td>
			<td align="center" colspan="3" valign="middle" class="contentFont han" style="width: 300px;">
				${sfzh}
			</td>
	        <td align="center" valign="middle" class="contentFont han" style="width: 180px;">准考证号</td>
	        <td align="center" colspan="3" valign="middle" class="contentFont han" style="width: 300px;">
				${zkzh}
	        </td>
	    </tr>
		<tr>
			<td align="left" colspan="8" valign="middle" class="contentFont han" style="width: 180px;">
				${mssm}
			</td>
		</tr>
		<tr>
			<td align="center" colspan="8" valign="middle" class="contentFont han" style="width: 180px;">
				面试时间: <span style="color: red;font-size: 16px;">${mssj}</span>
			</td>
		</tr>
	</table>
</div>
<!--endprint-->
</body>
</html>