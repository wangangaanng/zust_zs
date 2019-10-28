<!--
    作者：2515421994@qq.com
    时间：2019-10-25
    描述：基本信息4：选考信息
-->

<#--学考等第 start-->
<form class="form-horizontal basic-from" id="selectForm" method="" action="" target="baFrame" style="display: none">
    <#--学考等第 start-->
    <div class="contact-wrap">
        <p class="contact-wrap_title col-sm-offset-1">选考信息</p>
        <div>
            <div class="form-group">
                <label for="qyGsxz" class="col-sm-2 control-label label-select_wrap col-sm-offset-1">
                    <select class="form-control label-select">
                        <option value="1" selected>请选择选考科目</option>
                    </select>
                    <span class="red">*</span>
                </label>
                <div class="col-sm-8">
                    <input type="number" class="form-control" id="zwXs" name="zwXs" placeholder="请输入分数" autocomplete="off">
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
                <label for="qyGsxz" class="col-sm-2 control-label label-select_wrap col-sm-offset-1">
                    <select class="form-control label-select">
                        <option selected disabled>请选择一门外语</option>
                        <option value="1" >英语</option>
                    </select>
                    <span class="red">*</span>
                </label>
                <div class="col-sm-8">
                    <input type="number" class="form-control" id="zwXs" name="zwXs" placeholder="请输入分数" autocomplete="off">
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
                <label for="qyGsxz" class="col-sm-2 control-label col-sm-offset-1">
                   专项类别(可多选) <span class="red">*</span>
                </label>
                <div class="col-sm-8">
                    <ul class="checkbox-wrap">
                        <li>
                            <div class="checkbox">
                                <label class="checkbox"><input type="checkbox" name="checkbox1" value="111" /><span>学科竞赛类</span></label>
                            </div>
                        </li>
                        <li>
                            <div class="checkbox">
                                <label class="checkbox"><input type="checkbox" name="checkbox1" value="111" /><span>科技创新类</span></label>
                            </div>
                        </li>
                        <li>
                            <div class="checkbox">
                                <label class="checkbox"><input type="checkbox" name="checkbox1" value="111" /><span>科技创新类</span></label>
                            </div>
                        </li>
                        <li>
                            <div class="checkbox">
                                <label class="checkbox"><input type="checkbox" name="checkbox1" value="111" /><span>科技创新类</span></label>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="form-group">
                <label for="zwGwzz" class="col-sm-10 control-label col-sm-offset-1 label-full">高中阶段参加的竞赛类别、竞赛名称、时间、竞赛级别、取得名次<span class="red">*</span>：</label>
                <div class="col-sm-10 col-sm-offset-1">
                    <textarea class="form-control" id="zwGwzz" name="zwGwzz" rows="8" placeholder="请输入荣誉竞赛信息"></textarea>
                    <div class="upimg-wrap">
                        <div class="file-btn_wrap">
                             <img src="${base}/img/img-up.png" class="up-btn_img"/>
                            <input type="file" class="file-btn" value="" class="file1" name="file" id="file" accept="image/jpeg,image/jpg,image/png,image/svg" />
                        </div>
                        <label class="uploadlabel" for="file">上传证明图片(可多张）</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="zwGwzz" class="col-sm-10 control-label col-sm-offset-1 label-full">高中阶段参与的社会工作和课外活动（含活动时间、受过何种奖励、本人在活动中的职务或者职责）<span class="red">*</span>：</label>
                <div class="col-sm-10 col-sm-offset-1">
                    <textarea class="form-control" id="zwGwzz" name="zwGwzz" rows="8" placeholder="请输入高中阶段参与的社会工作和课外活动（"></textarea>
                </div>
            </div>

            <div class="form-group">
                <label for="zwGwzz" class="col-sm-10 control-label col-sm-offset-1 label-full">特长爱好<span class="red">*</span>：</label>
                <div class="col-sm-10 col-sm-offset-1">
                    <textarea class="form-control" id="zwGwzz" name="zwGwzz" rows="8" placeholder="请输入特长爱好"></textarea>
                </div>
            </div>

        </div>
    </div>
    <#--专项类别 end-->

    <#--button  start -->
    <div class="row">
        <div class="col-sm-4 col-sm-offset-2">
            <button type="submit" class="btn btn-default btn-common yellow">上一步，学考等第</button>
        </div>
        <div class="col-sm-4">
            <button type="submit" class="btn btn-default btn-common yellow">完成</button>
        </div>
    </div>
    <#--button  end -->
</form>
<#--学考等第 end-->

