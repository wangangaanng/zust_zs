<!--
    作者：2515421994@qq.com
    时间：2019-10-25
    描述：基本信息2：添加联系人
-->

<#--联系人2 start-->
<form class="form-horizontal basic-from" id="contactForm" method="" action="" target="baFrame" style="display: none">
    <#--父亲 start-->
    <div class="contact-wrap">
        <p class="contact-wrap_title col-sm-offset-1">父亲/母亲(或其他监护人)</p>
        <div>
            <div class="form-group">
                <label for="faxm" class="col-sm-2 control-label col-sm-offset-1">姓名<span class="red">*</span>：</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="faxm" name="faxm" placeholder="请输入姓名" autocomplete="off" value="">
                </div>
            </div>
            <div class="form-group">
                <label for="falxsj" class="col-sm-2 control-label col-sm-offset-1">手机号<span class="red">*</span>：</label>
                <div class="col-sm-8">
                    <input type="number" class="form-control" id="falxsj" name="falxsj" placeholder="请输入监护人手机号" autocomplete="off" value="">
                </div>
            </div>
            <div class="form-group">
                <label for="faxb" class="col-sm-2 control-label  col-sm-offset-1">关系<span class="red">*</span>：</label>
                <div class="col-sm-8">
                    <select class="form-control" name="facylb" id="facylb">
                        <option value="0" selected>父亲/母亲</option>
                        <option value="1" >其他监护人</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="faxb" class="col-sm-2 control-label  col-sm-offset-1">性别<span class="red">*</span>：</label>
                <div class="col-sm-8">
                    <select class="form-control" name="faxb" id="faxb">
                        <option value="1" selected>男</option>
                        <option value="2" >女</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="fawhcd" class="col-sm-2 control-label col-sm-offset-1">文化程度：</label>
                <div class="col-sm-8">
                    <select class="form-control" name="fawhcd" id="fawhcd">
                        <option  disabled selected>-- 请选择 --</option>
                       <#if (culList??&&culList?size>0)>
                           <#list culList as data>
                               <option value="${data.dicVal1!""}" >${data.dicVal2!""}</option>
                           </#list>
                       </#if>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="fagzdw" class="col-sm-2 control-label col-sm-offset-1">工作单位：</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="fagzdw" name="fagzdw" placeholder="请输入工作单位" autocomplete="off" value="">
                </div>
            </div>
            <div class="form-group">
                <label for="fagzzw" class="col-sm-2 control-label col-sm-offset-1">职务：</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="fagzzw" name="fagzzw" placeholder="请输入职务" autocomplete="off" value="">
                </div>
            </div>
            <div class="form-group">
                <label for="fadwdh" class="col-sm-2 control-label col-sm-offset-1">单位联系电话：</label>
                <div class="col-sm-8">
                    <input type="number" class="form-control" id="fadwdh" name="fadwdh" placeholder="请输入单位联系电话" autocomplete="off" value="">
                </div>
            </div>
        </div>
    </div>
    <#--父亲 end-->

    <#--母亲 start-->
       <#-- <div class="contact-wrap">
            <p class="contact-wrap_title col-sm-offset-1">母亲(或其他监护人)</p>
            <div>
                <div class="form-group">
                    <label for="moxm" class="col-sm-2 control-label col-sm-offset-1">姓名<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="moxm" name="moxm" placeholder="请输入姓名" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="molxsj" class="col-sm-2 control-label col-sm-offset-1">联系电话<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="molxsj" name="molxsj" placeholder="请输入联系电话" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="moxb" class="col-sm-2 control-label  col-sm-offset-1">性别<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" name="moxb" id="moxb">
                            <option value="1">男</option>
                            <option value="2" selected>女</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="mowhcd" class="col-sm-2 control-label col-sm-offset-1">文化程度：</label>
                    <div class="col-sm-8">
                        <select class="form-control" name="mowhcd" id="mowhcd">
                            <option  disabled selected>-- 请选择 --</option>
                        <#if (culList??&&culList?size>0)>
                            <#list culList as data>
                                <option value="${data.dicVal1!""}">${data.dicVal2!""}</option>
                            </#list>
                        </#if>
                        </select>
                        &lt;#&ndash;<input type="text" class="form-control" id="mowhcd" name="mowhcd" placeholder="请输入文化程度" autocomplete="off" value="">&ndash;&gt;
                    </div>
                </div>
                <div class="form-group">
                    <label for="mogzdw" class="col-sm-2 control-label col-sm-offset-1">工作单位：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="mogzdw" name="mogzdw" placeholder="请输入工作单位" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="mogzzw" class="col-sm-2 control-label col-sm-offset-1">职务：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="mogzzw" name="mogzzw" placeholder="请输入职务" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="modwdh" class="col-sm-2 control-label col-sm-offset-1">单位联系电话：</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="modwdh" name="modwdh" placeholder="请输入单位联系电话" autocomplete="off" value="">
                    </div>
                </div>
            </div>
        </div>-->
    <#--母亲 end-->

    <#--高中联系人 start-->
        <div class="contact-wrap">
            <p class="contact-wrap_title col-sm-offset-1">高中联系人</p>
            <div>
                <div class="form-group">
                    <label for="teName" class="col-sm-2 control-label col-sm-offset-1">姓名<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="texm" name="texm" placeholder="请输入姓名" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="telxsj" class="col-sm-2 control-label col-sm-offset-1">手机号<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="telxsj" name="telxsj" placeholder="请输入手机号" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="texb" class="col-sm-2 control-label  col-sm-offset-1">性别<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" name="texb" id="texb">
                            <option value="1" selected>男</option>
                            <option value="2" >女</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="tewhcd" class="col-sm-2 control-label col-sm-offset-1">文化程度：</label>
                    <div class="col-sm-8">
                        <select class="form-control" name="tewhcd" id="tewhcd">
                            <option  disabled selected>-- 请选择 --</option>
                            <#if (culList??&&culList?size>0)>
                                <#list culList as data>
                                    <option value="${data.dicVal1!""}">${data.dicVal2!""}</option>
                                </#list>
                            </#if>
                        </select>
                        <#--<input type="text" class="form-control" id="tewhcd" name="tewhcd" placeholder="请输入文化程度" autocomplete="off" value="">-->
                    </div>
                </div>
                <div class="form-group">
                    <label for="tegzdw" class="col-sm-2 control-label col-sm-offset-1">工作单位：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="teCom" name="tegzdw" placeholder="请输入工作单位" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="tegzzw" class="col-sm-2 control-label col-sm-offset-1">职务：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="tegzzw" name="tegzzw" placeholder="请输入职务" autocomplete="off" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="tedwdh" class="col-sm-2 control-label col-sm-offset-1">单位联系电话：</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="tedwdh" name="tedwdh" placeholder="请输入单位联系电话" autocomplete="off" value="">
                    </div>
                </div>
            </div>
        </div>
    <#--高中联系人 end-->

    <#--button  start -->
    <div class="row">
        <div class="col-sm-4 col-sm-offset-3">
            <button class="btn btn-default btn-common yellow" type="submit" id="pre">上一步，基本信息</button>
        </div>
        <div class="col-sm-4">
            <button type="submit" class="btn btn-default btn-common yellow" id="saveContact">下一步，学考信息</button>
        </div>
    </div>
    <#--button  end -->
</form>
<#--联系人2 end-->


