<!--
    作者：2515421994@qq.com
    时间：2019-10-25
    描述：基本信息2：添加联系人
-->

<#--联系人2 start-->
<form class="form-horizontal basic-from" id="contactForm" method="" action="" target="baFrame" style="display: none">
    <#--父亲 start-->
    <div class="contact-wrap">
        <p class="contact-wrap_title col-sm-offset-1">父亲(或其他监护人)</p>
        <div>
            <div class="form-group">
                <label for="faName" class="col-sm-2 control-label col-sm-offset-1">姓名<span class="red">*</span>：</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="faName" name="faName" placeholder="请输入姓名" autocomplete="off" value="">
                </div>
            </div>
            <div class="form-group">
                <label for="faTel" class="col-sm-2 control-label col-sm-offset-1">联系电话<span class="red">*</span>：</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="faTel" name="faTel" placeholder="请输入联系电话" autocomplete="off" value="">
                </div>
            </div>
            <div class="form-group">
                <label for="faSex" class="col-sm-2 control-label  col-sm-offset-1">性别<span class="red">*</span>：</label>
                <div class="col-sm-8">
                    <select class="form-control" name="faSex" id="faSex">
                        <option value="1" selected>男</option>
                        <option value="2" >女</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="faEdu" class="col-sm-2 control-label col-sm-offset-1">文化程度：</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="faEdu" name="faEdu" placeholder="请输入文化程度" autocomplete="off" value="">
                </div>
            </div>
            <div class="form-group">
                <label for="faCom" class="col-sm-2 control-label col-sm-offset-1">工作单位：</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="faCom" name="faCom" placeholder="请输入工作单位" autocomplete="off" value="">
                </div>
            </div>
            <div class="form-group">
                <label for="faJob" class="col-sm-2 control-label col-sm-offset-1">职务：</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="faJob" name="faJob" placeholder="请输入职务" autocomplete="off" value="">
                </div>
            </div>
            <div class="form-group">
                <label for="faComTel" class="col-sm-2 control-label col-sm-offset-1">单位联系电话：</label>
                <div class="col-sm-8">
                    <input type="number" class="form-control" id="faComTel" name="faComTel" placeholder="请输入单位联系电话" autocomplete="off" value="">
                </div>
            </div>
        </div>
    </div>
    <#--父亲 end-->

    <#--母亲 start-->
        <div class="contact-wrap">
            <p class="contact-wrap_title col-sm-offset-1">母亲(或其他监护人)</p>
            <div>
                <div class="form-group">
                    <label for="moName" class="col-sm-2 control-label col-sm-offset-1">姓名<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="moName" name="moName" placeholder="请输入姓名" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="moTel" class="col-sm-2 control-label col-sm-offset-1">联系电话<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="moTel" name="moTel" placeholder="请输入联系电话" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="moSex" class="col-sm-2 control-label  col-sm-offset-1">性别<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" name="moSex" id="moSex">
                            <option value="1" selected>男</option>
                            <option value="2" >女</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="moEdu" class="col-sm-2 control-label col-sm-offset-1">文化程度：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="moEdu" name="moEdu" placeholder="请输入文化程度" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="moCom" class="col-sm-2 control-label col-sm-offset-1">工作单位：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="moCom" name="moCom" placeholder="请输入工作单位" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="moJob" class="col-sm-2 control-label col-sm-offset-1">职务：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="moJob" name="moJob" placeholder="请输入职务" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="moComTel" class="col-sm-2 control-label col-sm-offset-1">单位联系电话：</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="moComTel" name="moComTel" placeholder="请输入单位联系电话" autocomplete="off" value="">
                    </div>
                </div>
            </div>
        </div>
    <#--母亲 end-->

    <#--高中联系人 start-->
        <div class="contact-wrap">
            <p class="contact-wrap_title col-sm-offset-1">高中联系人</p>
            <div>
                <div class="form-group">
                    <label for="teName" class="col-sm-2 control-label col-sm-offset-1">姓名<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="teName" name="teName" placeholder="请输入姓名" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="teTel" class="col-sm-2 control-label col-sm-offset-1">联系电话<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="teTel" name="teTel" placeholder="请输入联系电话" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="teSex" class="col-sm-2 control-label  col-sm-offset-1">性别<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" name="teSex" id="teSex">
                            <option value="1" selected>男</option>
                            <option value="2" >女</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="teEdu" class="col-sm-2 control-label col-sm-offset-1">文化程度：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="teEdu" name="teEdu" placeholder="请输入文化程度" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="teCom" class="col-sm-2 control-label col-sm-offset-1">工作单位：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="teCom" name="teCom" placeholder="请输入工作单位" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="teJob" class="col-sm-2 control-label col-sm-offset-1">职务：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="teJob" name="teJob" placeholder="请输入职务" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="teComTel" class="col-sm-2 control-label col-sm-offset-1">单位联系电话：</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="teComTel" name="teComTel" placeholder="请输入单位联系电话" autocomplete="off" value="">
                    </div>
                </div>
            </div>
        </div>
    <#--高中联系人 end-->

    <#--button  start -->
    <div class="row">
        <div class="col-sm-4 col-sm-offset-3">
            <button class="btn btn-default btn-common yellow" id="showBasic">上一步，基本信息</button>
        </div>
        <div class="col-sm-4">
            <button type="submit" class="btn btn-default btn-common yellow" id="saveContact">下一步，学考信息</button>
        </div>
    </div>
    <#--button  end -->
</form>
<#--联系人2 end-->
<script src="${base}/js/swyt/SWinfoContactor.js"></script>
<script>
    //联系人保存
    $("#saveContact").click(function () {
        $("#basicForm").hide();
        $("#contactForm").hide();
        $("#gradeForm").show();
        $(".jf-items .jf-item").eq(2).addClass("jf-active");
    });

    //上一步
//    $("#showBasic").click(function () {
//        $("#basicForm").show();
//        $("#contactForm").hide();
//    });
</script>


