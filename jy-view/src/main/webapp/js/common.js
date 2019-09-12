/**
 * Created by zhang on 2019/9/11.
 */
document.write('<link rel="stylesheet" href="css/common.css" />');
document.write('<script language=javascript src="js/mustache.js"></script>');
$(document).ready(function () {
    mustache_init();
})
var data={nav:[
    {url:'index.html',title:'首页'},
    {url:'',title:'学院概况',sub:[{url:'articleTpl.html',title:'学校简介'},{url:'',title:'学院专业'},{url:'',title:'中心介绍'}]},
    {url:'',title:'新闻公告',sub:[{url:'newsList.html',title:'通知公告'},{url:'',title:'新闻快递'},{url:'',title:'校内公示'}]},
    {url:'',title:'招聘信息',sub:[{url:'',title:'浙科院·职来职往'},{url:'',title:'社会招聘会'},{url:'',title:'企业招聘信息'},{url:'',title:'职位招聘信息'},{url:'',title:'招考公告'}]},
    {url:'',title:'职业指导',sub:[{url:'',title:'政策法规'},{url:'',title:'就业指导'},{url:'',title:'创业指导'},{url:'',title:'生涯规划'},{url:'',title:'技能培训'}]},
    {url:'',title:'企业指南',sub:[{url:'',title:'招聘指南'},{url:'',title:'生源速览'}]},
    {url:'',title:'学生服务',sub:[{url:'',title:'办事流程'},{url:'',title:'常用下载'},{url:'',title:'档案查询'}]},
    {url:'',title:'联系我们'},
]}
var headerTpl='<header><div class="top"><div class="container"><div class="top-logo"><img class="logo" src="img/logo-zust.png"><div class="title">就业信息网</div> </div>'+
    '<div class="nav"><ul>{{>navList}}</ul></div> </div> </div></header>'
var headerPart={navList:"{{#nav}}<li onclick='openUrl(\"{{url}}\")'>{{title}}<ul class='subnav'>{{>subnavList}}</ul></li>{{/nav}}",subnavList:"{{#sub}}<li onclick='openUrl(\"{{url}}\")'><a><span>{{title}}</span></a></li>{{/sub}}"}
function mustache_init() {
    var header=Mustache.render(headerTpl, data,headerPart);
    $("body").prepend(header);
}
function openUrl(url) {
    if(url){
        window.location.href=url
        // window.open(url);
    }
}