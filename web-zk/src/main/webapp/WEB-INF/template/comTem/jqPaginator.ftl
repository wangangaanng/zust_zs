<#--
    info:com pagination
    author:2515421994@qq.com
    time:2019-04-12
-->

<script src="html/js/jqPaginator.min.js" type="text/javascript"></script>
<style>
    .pagination li.active a{background: #fffbf7;color: #ffa801;border: 1px solid #ffa801;z-index: 1;}
    .pagination li a{margin: 0px 1px;}
</style>

<!--pagination begin-->
<div style="text-align: center;position: absolute;bottom: 10px;width: 100%;" id="paginationWrap">
    <ul class="pagination" id="pagination">
    </ul>
    <input type="hidden" id="PageCount" runat="server" value="${(totalCount==0)?string("1",totalCount)}" /> <#--总条数：ftl中获取-->
    <input type="hidden" id="PageSize" runat="server" value="${pageSize!1}" /><#--每一页显示条数 controller中设置-->
    <input type="hidden" id="countindex" runat="server" value="" />
    <!--设置最多显示的页码数 可以手动设置 默认为7-->
    <input type="hidden" id="visiblePages" runat="server" value="6" />
</div>
<!--pagination end-->

<script>
    var curNum = 1;//与myService共用 当前页
    var thisUrl = window.location.href;//与myService共用 当前地址
    var myPageCount = parseInt($("#PageCount").val());
    var myPageSize = parseInt($("#PageSize").val());
    var countindex = myPageCount % myPageSize > 0 ? (myPageCount / myPageSize) + 1 : (myPageCount / myPageSize);
    $("#countindex").val(countindex);
    $.jqPaginator('#pagination', {
        totalPages: parseInt($("#countindex").val()),
        visiblePages: parseInt($("#visiblePages").val()),
        currentPage: ${pageNo!1}, //当前页
        first: '<li class="first"><a href="javascript:;">首页</a></li>',
        prev: '<li class="prev"><a href="javascript:;"><i class="arrow arrow2"></i>上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页<i class="arrow arrow3"></i></a></li>',
        last: '<li class="last"><a href="javascript:;">末页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            if (type == "change") {
                curNum = num;
                //点击翻页
                if(thisUrl.indexOf("pageNo")!=-1){
                    window.location.href = changeURLArg(thisUrl,"pageNo",num);
                }else{
                    window.location.href = thisUrl+"&pageNo="+num;
                }
            }
        }
    });
    //改变地址中某个指定字段的值
    function changeURLArg(url,arg,arg_val){//地址，参数，替换值
        var pattern=arg+'=([^&]*)';
        var replaceText=arg+'='+arg_val;
        if(url.match(pattern)){
            var tmp='/('+ arg+'=)([^&]*)/gi';
            tmp=url.replace(eval(tmp),replaceText);
            return tmp;
        }else{
            if(url.match('[\?]')){
                return url+'&'+replaceText;
            }else{
                return url+'?'+replaceText;
            }
        }
        return url+'\n'+arg+'\n'+arg_val;
    }
</script>