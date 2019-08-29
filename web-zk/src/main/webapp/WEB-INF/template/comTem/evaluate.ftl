<#--中介机构评价-->
<style>
    .up-btn{text-align: center;padding: 0px 15px 0px 115px}
    .up-btn input{background-color: #009cff;border: none;line-height: 38px;color: #FFF;font-size: 18px;border-radius: 4px;padding-left: 0px;width: 100%;margin: 20px auto 0px}
    .eva-detail{color: #595757;font-size: 15px;  padding: 15px 15px 0px 15px;}
    .eva-detail li{margin-bottom: 10px;}
    .eva-title{display: inline-block;width: 100px;float: left;}
    .eva-textarea{border: 1px solid #eaeef1;padding: 6px 10px;display: block;width: 100%;font-size: 14px;border-radius: 4px;}
    .eva-top h6{color: #666;font-size: 15px;text-align: center; padding-bottom: 8px;margin-bottom: 10px;}
    .eva-wrap{display: none;z-index: 99;background: #FFF;padding: 20px 20px 35px;border-radius: 12px;}
    .eva-stars{   white-space: nowrap;  text-align: left;  margin-top: 10px;  margin-bottom: 0px;display: inline;}
    .eva-stars li{   display: inline-block;  color: #e8e8e8;  font-size: 40px;cursor: pointer}
    #showLevel{margin-left: 5px;color: #009cff}
    .eva-level{display: inline-block;  line-height: 32px;  height: 32px;  vertical-align: text-bottom;display: none;color: rgb(255, 168, 1);margin-left: 10px;}
</style>

<div id="ser-evulate" class="eva-wrap">
    <span class="layui-layer-setwin"><a class="layui-layer-ico layui-layer-close layui-layer-close1" href="javascript:;"></a></span>

    <ul class="eva-detail">

    <#list  evaContent as item>
        <li>
            <div class="eva-title">${item.DIC_VAL2!"该项评价名称暂无"}</div>

                <ul class="eva-stars" score="0">
                    <li>★</li>
                    <li>★</li>
                    <li>★</li>
                    <li>★</li>
                    <li>★</li>
                </ul>
            <span class="eva-level">好</span>
        </li>
    </#list>

        <li>
            <div class="eva-title">评语</div>
            <div>
                <div style="width: 100%;padding-left: 100px;">
                    <textarea placeholder="请输入评价内容" class="eva-textarea" rows="3" ></textarea>
                </div>
            </div>
        </li>
    </ul>

    <div class="up-btn">
        <input type="button" value="提交" >
    </div>
</div>

<script>
    var beforeClickedIndex = "";//前一次点击第几课星星
    var beforeParentIndex = "";//上一次点击哪一个评分项
    var clickIndex = 0;//没有点击的 或者取消1颗星
    var clickNum = "";//累计点击次数 用于区分奇数偶数
    //星星点击事件:存在bug换评分项 取消最后一个第一次不能取消 需要点击2次
    $('.eva-stars li').click(function() {
        var parentIndex = $(this).parents("li").index();
        var $parent = $(this).parents(".eva-stars");
        var sameMark = beforeParentIndex == parentIndex;
        sameMark?"":clickNum ="";//不同评分项 进行重置点击次数
        $parent.find("li").css('color', '#e8e8e8');
        var index = $(this).index();
        for(var i = 1; i <= index+1; i++) {
            $parent.find('li:nth-child(' + i + ')').css('color', '#ffa801');
        }
        if(((index == beforeClickedIndex && sameMark))||(index+1) ==  $parent.attr("score")) { //两次点击同一颗星星 该星星颜色变化
            clickNum++;
             if(clickNum % 2 == 1) {//奇数次点击-去
                 clickIndex = index-1;
                 $parent.find('li:nth-child(' + (index + 1) + ')').css('color', '#e8e8e8');
             } else {
                 clickIndex = index;
                 $parent.find('li:nth-child(' + (index + 1) + ')').css('color', '#ffa801');
             }
        } else {
            clickIndex = index;
            clickNum = 0;
            beforeClickedIndex = index;
        }
        beforeParentIndex = parentIndex;
        $parent.attr("score",clickIndex+1);//直接attr上每一题的分值 方便取值
        //显示星级对应的文字描述
        var $level = $(this).parents("li").find(".eva-level").show();
        switch (clickIndex){
            case 0: $level.html("非常差");
                break;
            case 1: $level.html("差");
                break;
            case 2: $level.html("一般");
                break;
            case 3: $level.html("好");
                break;
            case 4: $level.html("非常好");
                break;
            default:$level.html("");
        }
    });
</script>