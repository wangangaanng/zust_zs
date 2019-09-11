/**
 * Created by zhang on 2019/9/11.
 */
document.write('<link rel="stylesheet" href="../css/common.css" />');
document.write('<script language=javascript src="../js/mustache.js"></script>');
$(document).ready(function () {
    mustache_init();
})
var data={nav:[
    {url:'',title:'首页'},
    {url:'',title:'学员概况'},
    {url:'',title:'新闻公告'},
    {url:'',title:'招聘信息'},
    {url:'',title:'职业指导'},
    {url:'',title:'企业指南'},
    {url:'',title:'学生服务'},
    {url:'',title:'联系我们'},
]}
var headerTpl='<header><div class="top"><div class="container"><div class="top-logo"><img class="logo" src="../img/logo-zust.png"><div class="title">就业信息网</div> </div>'+
    '<div class="nav"><ul>{{>navList}}</ul></div> </div> </div></header>'
var headerPart={navList:"{{#nav}}<li>{{title}}</li>{{/nav}}"}
function mustache_init() {
    var header=Mustache.render(headerTpl, data,headerPart);
    $("body").prepend(header);
}