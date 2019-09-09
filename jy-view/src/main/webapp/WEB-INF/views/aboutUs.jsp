<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="commonProperty.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
		<title>鄂尔多斯市转型发展投资有限公司</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/com.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index.css" />
		<style>
			@media (max-width: 767px) {
				.container ,html, body{
					min-width: 100%;
					background-color: #fff;
				}
				.table{
					display: block;
					border:none;
				}
				.table_right_ul{
					padding-top: 10px;
				}
				.table_right{
					padding: 0 15px;
				}
				.table_right_li div{
					overflow: hidden;
					text-overflow: ellipsis;
					display: -webkit-box !important;
					-webkit-box-orient: vertical;
					-webkit-line-clamp: 1;
					width: 70%;
				}
				.table_left{
					width: 100%;
					padding: 15px;
					background-color: #fff;
					border-right: 0;
				}
				.table_left ul{
					background-color: #f6f6f6;
					border-radius: 6px;
					display: flex;
					overflow: hidden;
					width: 100%;
				}
				.table_li{
					float: left;
					flex: 1;
					font-size: 14px;
					border-bottom: none;
				}
				.table_right_btnList span,.table_right_btnList input{
					display: none;
				}
				.table_right_btnList{
					text-align: center;
				}
				.table_right_btnList button:last-child{
					display: none;
				}
				.table_right span,.table_right text{
					font-size: 14px !important;
					line-height: 35px !important;
				}
				.table_right_title2 {
					font-size: 27px;
					padding: 20px 0 !important;
				}
				#gsjj1,#gsjj2{
					width: 100% !important;
					height: auto !important;
					padding: 10px 0;
					margin: 0 !important;
				}
				.table_people{
					font-size: 14px !important;
				}
			}
		</style>
	</head>
	<body>
	<%@include file="top.jsp"%>

		<div class="bodyContent container">
			<div class="route hidden-xs">
				<img src="image/route.png">
				<ul>
					<%--<li>首页</li>
					<li>关于我们</li>
					<li>公司简介</li>--%>
				</ul>
			</div>
			<div class="table">
				<div class="table_left">
					<div class="table_left_title hidden-xs">
						<%--关于我们
						<p>About Us</p>--%>
					</div>
					<ul>
						<%--<li class="table_li active">公司简介</li>
						<li class="table_li">总裁致辞</li>
						<li class="table_li">投资理念</li>
						<li class="table_li">大事记</li>
						<li class="table_li">招贤纳士</li>
						<li class="table_li">联系我们</li>--%>
					</ul>
					<img src="image/left.png" style="width: 100%;" class="hidden-xs">
				</div>
				<div class="table_right">
					<%--<div id="cov1">
						<p>鄂尔多斯市转型发展投资有限责任公司成立于2015年12月16日，是经市人民政府批准成立的国有独资公司。公司注册资本10亿元，主要经营范围包括：受托从事转型发展基金的经营管理、与基金管理人合作设立私募投资基金、投融资管理和相关咨询服务等业务。</p>
						<img src="image/right2.png" class="col-sm-12 col-xs-12 col-md-12 col-lg-8">
						<img src="image/right1.png" class="hidden-xs hidden-sm hidden-md col-lg-4 " style="padding: 50px 0;">
						<p>按照《鄂尔多斯市转型发展基金暂行管理办法》有关规定，市转型发展投资有限责任公司将通过市人民政府预算安排，以单独出资或与社会资本共同出资设立，采取股权投资等市场化方式，引导社会各类资本投资经济社会发展的重点领域和薄弱环节，重点支持全市产业转型升级和发展、创新创业、中小企业发展、基础设施、公共服务领域和市委、政府研究确定的其他重点领域。通过发挥财政资金的激励引导和杠杆放大效应，将财政资金变“无偿投入”为“滚动使用”，变“直接投入”为“间接引导
							”，变“行政性分配”为“市场化动作”，进而吸引外来资本、激活民间资本，带动私募基金发展，丰富企业融资需求，促进创业创新，推进全市产业结构优化调整。</p>
					</div>
					<div id="cov2" style="display: table;padding-bottom: 173px;">
						<div class="table_right_title2">总裁致辞</div>
						<p>鄂尔多斯市转型发展投资有限责任公司成立于2015年12月16日，是经市人民政府批准成立的国有独资公司。公司注册资本10亿元，主要经营范围包括：受托从事转型发展基金的经营管理、与基金管理人合作设立私募投资基金、投融资管理和相关咨询服务等业务。鄂尔多斯市转型发展投资有限责任公司成立于2015年12月16日，是经市人民政府批准成立的国有独资公司。公司注册资本10亿元，主要经营范围包括：受托从事转型发展基金的经营管理、与基金管理人合作设立私募投资基金投融资管理和相关咨询服务等业务。</p>
						<p style="width: 55%;float: left;">鄂尔多斯市转型发展投资有限责任公司成立于2015年12月16日，是经市人民政府批准成立的国有独资公司。公司注册资本10亿元，主要经营范围包括：受托从事转型发展基金的经营管理、与基金管理人合作设立私募投资基金、投融资管理和相关咨询服务等业务。
							鄂尔多斯市转型发展投资有限责任公司成立于2015年12月16日，是经市人民政府批准成立的国有独资公司。公司注册资本10亿元，主要经营范围包括：受托从事转型发展基金的经营管理、与基金管理人合作设立私募投资基金、投融资管理和相关咨询服务等业务。</p>
						<img src="image/zhici.png" style="position: absolute;right: 35px;width: 60%;bottom: 20px;">
					</div>
					<div id="cov3">
						<p> <span class="table_right_title3">总裁寄语：</span>携带保险基因的人保资产公募基金，将坚持价值投资和追求绝对收益的投资理念，发挥保险机构投资者市场稳定器、社会保障器和实体经济助推器的作用。通过我们的价值创造让更多的同业机构和公众投资者获得收益。</p>
						<p style="text-align: right;">——鄂尔多斯市转型基金 张董</p>
						<p><span class="table_right_title3">投资理念：</span>坚持价值投资和追求绝对收益的投资理念，发挥保险机构投资者市场稳定器、社会保障器和实体经济助推器的作用。通过我们的价值创造让更多的同业机构和公众投资者获得收益。</p>
					</div>
					<div id="cov4">
					</div>
					<div id="cov5">
						<img src="image/takeJob.png" class="takeJob">
						<div class="takeJob_search">
							<div>
								<label class="section">招聘部门</label>
								<div class="dropdown">
									<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown"
									 aria-haspopup="true" aria-expanded="true">
										全部
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
										<li><a href="#">Action</a></li>
										<li><a href="#">Another action</a></li>
										<li><a href="#">Something else here</a></li>
									</ul>
								</div>
							</div>
							<div>
								<label class="section">招聘部门</label>
								<div class="dropdown">
									<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown"
									 aria-haspopup="true" aria-expanded="true">
										全部
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
										<li><a href="#">Action</a></li>
										<li><a href="#">Another action</a></li>
										<li><a href="#">Something else here</a></li>
									</ul>
								</div>
							</div>
							<div class="takeJob_search_btn">搜索</div>
						</div>
						<table border="0" class="takeJob_table">
							<tr>
								<td>岗位名称</td>
								<td>招聘部门</td>
								<td>工作地点</td>
								<td>招聘人数</td>
								<td>发布日期</td>
								<td>查看详情<td>
							</tr>
							<tr>
								<td class="takeJob_table_name">基金销售营销策划</td>
								<td>市场部</td>
								<td>北京</td>
								<td>3</td>
								<td>2017-04-14</td>
								<td><img src="image/detail.png" ></td>
							</tr>
						</table>
						<div class="table_right_btnList">
							<button type="button">首页</button>
							<button type="button">上一页</button>
							<button type="button">下一页</button>
							<button type="button">尾页</button>
							<span>共1页</span>
							<span>当前第</span><input type="number" value="1" /><span>页</span>
							<button type="button">确认</button>
						</div>
					</div>
					<div id="cov6">
						<img src="image/phone.png" class="tel_img hidden-xs hidden-sm hidden-md">
						<div class="table_right_title2">
							联系我们
						</div>
						<text class="tel_text" style="margin-top: 55px;">客服电话：000-8886666</text>
						<text class="tel_text">客服邮箱 ：64632131@qq.com</text>
						<div class="tel_address">
							<img src="image/dibiao.png">
							<text>鄂尔多斯市办公地</text>
						</div>
						<text class="tel_text" style="margin-top: 30px;">公司地址：鄂尔多斯市康巴什新区金财大厦321室</text>
						<text class="tel_text">公司邮编：2250000</text>
						<div id="map" style="width: 100%;height: 333px;margin: 70px 0 35px;"></div>
					</div>--%>
				</div>
			</div>
		</div>
	<%@include file="footer.jsp"%>
	</body>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=z9fqvjTYG2GqnnWTvzIbAj2RoI8GFWff"></script>
	<script src="<%=request.getContextPath()%>/js/aboutUs.js" type="text/javascript" charset="utf-8"></script>
</html>
