<!--
    作者：2515421994@qq.com
    时间：2019-10-26
    描述：报名表1和面试通知单发送到邮箱5
-->
<div class="group-wrap">
<#switch page>
    <#case "1">
        <#assign tip="你的报名表已成功提交,请确认邮箱地址。点击确认发送，报名表和承诺书将发送到你的邮箱。"/>
        <#break>
    <#case "5">
        <#assign tip="恭喜你已成功通过初审，请确认邮箱地址。点击确认发送，面试通知单将发送到你的邮箱。"/>
        <#break>
</#switch>
<#include "com/SWtip.ftl">
    <ul class="list-group row">
        <div class="grade-result row">
        </div>
        <li class="list-group-item col-sm-offset-2 col-sm-8">
            <label class="group-label">${fileName}：</label>
            <a type="button" class="btn btn-default fr preview-btn" target="_blank" href="${imagePath}${filePath}">预览${fileName}</a>
        </li>
        <li class="list-group-item col-sm-offset-2 col-sm-8">
            <label class="group-label">邮箱地址：</label>
            <label class="group-value fr" style="width: 70%">
                <input type="text" class="form-control" name="email" id="email"  value="${(email??&&email!="")?string(email,'')}" placeholder="请输入邮箱" style="text-align: right;background: inherit">
            </label>
        </li>
        <div class="form-group">
            <div class="col-sm-12 text-center" style="margin-top: 60px;">
                <button type="submit" id="sendMail" class="btn btn-default btn-common yellow">确认发送</button>
            </div>
        </div>
    </ul>
</div>
<script>
    <#if fileName=="报名表">
        <#assign fileName="报名表和承诺书"/>
    </#if>
    $("#sendMail").click(function () {
        var email = $("#email").val();
        if(emptyCheck(email)){
            var index = layer.confirm('${fileName}将发送至邮箱:&nbsp<span class="green fb">'+email+'</span>', {
                btn: ['确认','取消'] //按钮
                ,title:"确认邮箱地址"
            }, function(){//确认
                layer.close(index);
                sendMail(email);
            }, function(){
            });
        }else{
          walert("请先输入邮箱地址")
        }
    });

    //发送邮箱
    function sendMail(email) {
        var arrUrl = ["zustswyt/bckjBizBm/sendEmail","zustswyt/bckjBizBm/sendView"];
        var curUrl = "";
        var data = {
            "applyOwid":formOwid,//申请表owid在trinityEnrollment取得
            "yx":email,
        }
        switch (${page}){
            case "1"://报名表
                curUrl = arrUrl[0];
                break;
            case "5"://面试通知单
                curUrl = arrUrl[1];
                break;
        }
        ajax(curUrl, data, function (data) {
            if(data.backCode==0){
                var data = data.bean;
                walert("发送成功，请注意查收");
            }else{
                walert(data.errorMess)
            }
        })
    }
</script>



