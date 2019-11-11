<!--
    作者：2515421994@qq.com
    时间：2019-10-25
    描述：基本信息4：选考信息
-->

<#--学考等第 start-->
<div class="form-horizontal basic-from" id="selectForm" style="display: none;">
    <#--学考等第 start-->
    <div class="contact-wrap">
        <p class="contact-wrap_title col-sm-offset-1">选考信息</p>
        <div id="xkkm">
            <div class="form-group">
                <label for="" class="col-sm-2 control-label label-select_wrap col-sm-offset-1">
                    <select class="form-control label-select">
                        <option value="">请选择选考科目</option>
                    </select>
                    <span class="red">*</span>
                </label>
                <div class="col-sm-8">
                    <input type="number" class="form-control" id="" name="zwXs" placeholder="请输入分数" autocomplete="off">
                    <span style="position: absolute;right: 25px;top: 7px;">分</span>
                </div>
            </div>
            <div class="form-group">
                <label for="" class="col-sm-2 control-label label-select_wrap col-sm-offset-1">
                    <select class="form-control label-select">
                        <option value="">请选择选考科目</option>
                    </select>
                    <span class="red">*</span>
                </label>
                <div class="col-sm-8">
                    <input type="number" class="form-control" id="" name="zwXs" placeholder="请输入分数" autocomplete="off">
                    <span style="position: absolute;right: 25px;top: 7px;">分</span>
                </div>
            </div>
            <div class="form-group">
                <label for="" class="col-sm-2 control-label label-select_wrap col-sm-offset-1">
                    <select class="form-control label-select">
                        <option value="">请选择选考科目</option>
                    </select>
                    <span class="red">*</span>
                </label>
                <div class="col-sm-8">
                    <input type="number" class="form-control" id="" name="zwXs" placeholder="请输入分数" autocomplete="off">
                    <span style="position: absolute;right: 25px;top: 7px;">分</span>
                </div>
            </div>
        </div>
    </div>
    <#--学考等第 end-->

    <#--外语成绩 start-->
    <div class="contact-wrap">
        <p class="contact-wrap_title col-sm-offset-1">外语成绩</p>
        <div>
            <div class="form-group">
                <label for="" class="col-sm-2 control-label label-select_wrap col-sm-offset-1">
                    <select class="form-control label-select" id="wyyz">
                        <option value="">请选择一门外语</option>
                    </select>
                    <span class="red">*</span>
                </label>
                <div class="col-sm-8">
                    <input type="number" class="form-control" id="wycj" name="zwXs" placeholder="请输入分数" autocomplete="off">
                    <span style="position: absolute;right: 25px;top: 7px;">分</span>
                </div>
            </div>
        </div>
    </div>
    <#--外语成绩 end-->

    <#--专项类别 start-->
    <div class="contact-wrap">
        <p class="contact-wrap_title col-sm-offset-1">专项类别</p>
        <div>
            <div class="form-group">
                <label for="" class="col-sm-2 control-label col-sm-offset-1">
                   专项类别(可多选)
                </label>
                <div class="col-sm-8">
                    <ul class="checkbox-wrap" id="zxlb">
<#--                        <li>-->
<#--                            <div class="checkbox">-->
<#--                                <label class="checkbox"><input type="checkbox" name="checkbox1" value="111" /><span>学科竞赛类</span></label>-->
<#--                            </div>-->
<#--                        </li>-->
<#--                        <li>-->
<#--                            <div class="checkbox">-->
<#--                                <label class="checkbox"><input type="checkbox" name="checkbox1" value="111" /><span>科技创新类</span></label>-->
<#--                            </div>-->
<#--                        </li>-->
<#--                        <li>-->
<#--                            <div class="checkbox">-->
<#--                                <label class="checkbox"><input type="checkbox" name="checkbox1" value="111" /><span>科技创新类</span></label>-->
<#--                            </div>-->
<#--                        </li>-->
<#--                        <li>-->
<#--                            <div class="checkbox">-->
<#--                                <label class="checkbox"><input type="checkbox" name="checkbox1" value="111" /><span>科技创新类</span></label>-->
<#--                            </div>-->
<#--                        </li>-->
                    </ul>
                </div>
            </div>

            <div class="form-group">
                <label for="zwGwzz" class="col-sm-10 control-label col-sm-offset-1 label-full">高中阶段参加的竞赛类别、竞赛名称、时间、竞赛级别、取得名次：</label>
                <div class="col-sm-10 col-sm-offset-1">
                    <textarea class="form-control" id="jssm" name="jssm" rows="8" placeholder="请输入荣誉竞赛信息(最长150字)" oninput="if(value.length>150){value=value.substring(0,150)}"></textarea>
                    <div class="upimg-wrap" style="float: left;">
                        <ul id="fileList">
                        </ul>
                    </div>
                    <div class="upimg-wrap">
                        <div class="file-btn_wrap">
                             <img src="${base}/img/img-up.png" class="up-btn_img"/>
                            <input type="file" class="file-btn file1" name="file" id="file3" accept="image/jpeg,image/jpg,image/png,image/svg" />
                        </div>
                        <label class="uploadlabel" for="file3">上传证明图片(可多张）</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="zwGwzz" class="col-sm-10 control-label col-sm-offset-1 label-full">高中阶段参与的社会工作和课外活动（含活动时间、受过何种奖励、本人在活动中的职务或者职责）：</label>
                <div class="col-sm-10 col-sm-offset-1">
                    <textarea class="form-control" id="qtqk" name="qtqk" rows="8" placeholder="请输入高中阶段参与的社会工作和课外活动(最长150字)" oninput="if(value.length>150){value=value.substring(0,150)}"></textarea>
                </div>
            </div>

            <div class="form-group">
                <label for="zwGwzz" class="col-sm-10 control-label col-sm-offset-1 label-full">特长爱好<span class="red">*</span>：</label>
                <div class="col-sm-10 col-sm-offset-1">
                    <textarea class="form-control" id="tcah" name="tcah" rows="8" placeholder="请输入特长爱好(最长150字)" oninput="if(value.length>150){value=value.substring(0,150)}"></textarea>
                </div>
            </div>

        </div>
    </div>
    <#--专项类别 end-->

    <#--button  start -->
    <div class="row">
        <div class="col-sm-4 col-sm-offset-2">
            <button type="submit" class="btn btn-default btn-common yellow" onclick="finishXk(1)">上一步，学考等第</button>
        </div>
        <div class="col-sm-4">
            <button type="submit" class="btn btn-default btn-common yellow" onclick="finishXk(2)">完成</button>
        </div>
    </div>
    <#--button  end -->
</div>
<div id="majorExam" style="display:none;padding: 80px 50px;">
    <div class="form-horizontal basic-from">
        <div class="contact-wrap">
            <div class="form-group">
                <label class="col-sm-3 control-label  col-sm-offset-1">学科类别<span class="red">*</span>：</label>
                <div class="col-sm-7">
                    <select class="form-control label-select" style="width: 100%;" id="Major1">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label  col-sm-offset-1">报考类别<span class="red">*</span>：</label>
                <div class="col-sm-7">
                    <select class="form-control label-select" style="width: 100%;" id="Major2">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label  col-sm-offset-1">招生专业<span class="red">*</span>：</label>
                <div class="col-sm-7">
                    <select class="form-control label-select" style="width: 100%;" id="Major3">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
            <div style="display: none;text-align: right;padding-right: 0;margin-bottom: 30px;" class="col-sm-11" id="getApply">
                <a href="" target="_Blank">报名表预览</a>
            </div>
        </div>
        <#--button  start -->
        <div class="row" style="text-align: center;">
            <div class="col-sm-12">
                <button type="submit" class="btn btn-default btn-common yellow" onclick="confirmApply()">确认提交报名表</button>
            </div>
        </div>
        <#--button  end -->
    </div>
</div>
<#--学考等第 end-->


