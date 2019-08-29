<!--
    消息模板
    20190919
-->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>消息提示</title>
    <link rel="icon" href="charisma/img/qzsj.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="html/css/weui.min.css"/>
    <link rel="stylesheet" href="html/css/expertEva.css"/>
    <script type="text/javascript" src="html/js/jquery-3.2.1.min.js" ></script>
    <script src="html/js/layer/layer.js" type="text/javascript"></script>
</head>
<body class="con-wrap">
    <div style="padding: 25px 15px;" class="noti-wrap">
        <p class="noti-title">兆儿河附近生态恢复建设</p>
        <h6 class="noti-info"><span class="noti-sender"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABLElEQVQ4T6WT4W2EMAyFE7MAIzDCWc4AbNDrBjfCjcAIjMAGpRswAJZvhOsGXSBxZQSIckCRGokfEPvLe37Bu38uv9ff9/3Ve/9m+6r6GUJot2o3Acxcq2qeZVltTTHGynv/IKJqDXkBiEiRUmqIqFwWM3MHADdEfC6/bwHKlFK5Po2ZKwDoELH7C3CJMd5DCLdlYd/3jVlCxMchwDZNrnPObDTju8Fua1u2tznEMYEP55z5ze1R1fetJH4BRCSPMVrj11KuiAy2vPcFAFwR8XuyMQOsOaXUAkC1HtRULCI2YBvmDJkBlr1JJqIh+73FzHezNKU0AMbTOyK6nLnZzGwHFfMQR2kv2R+oqAGgNauDAma2mEzWofwJaDZU9Wmp7P5MZ6zs3oOzzVb3A2gRlxF1JCtgAAAAAElFTkSuQmCC"/>发送人：vivian</span><span class="noti-time"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABPklEQVQ4T6WTUW3EMAyGE4dAIQzCRQmAMlghlMEOQiEUwo1Bh2AF0MiDcBBKwM7JUVLlqlaqtLw5sT/bvx2t/nn0UTwifhDRp9a6kfcY42qM+bHWPvf+bwBEbJj5rpRqAGCy1s4SgIgtEfVa6xUABmvtWkAbQIKJ6GGMEYe/EqiUWiv7xswjAHQFsgFCCPLwKM4VQJVK8t2NiO7e+17sBJCembl3zg11j1K62DVA7BDCkJM9E6C+uAKoEybAsiyT977bK3xWQU46O+faUkEyDgAyztIW1qMMIbwBtp7O9goRZQJJOAAYi2ZFRHlsnXPjlcXMms0i7jZG0cEY09dLcgZblqXz3k/bGPMoyyKdQmTZ9gn2qyyiSRu/xpjv4pxX/CvGaPdVnn2mlpllrLf8meQTyahT2fU5BFwRsvi8AEsA0BHkYFL2AAAAAElFTkSuQmCC"/>时间：2019-06-19</span></h6>
       <div class="noti-content">
           vivian编造的具体的通知内容接下来是凑数字丝毫没有别的意义哈哈哈哈哈哈哈哈哈哈哈哈哈哈哦哦哦哦哦哦
       </div>
        <div class="page__bd page__bd_spacing" style="margin-top: 50px;padding: 0px 40px;">
            <a href="javascript:;" class="weui-btn weui-btn_primary exregister-btn" id="reply">回复</a>
        </div>
    </div>

    <div id="answerDet" style="display: none;background: #FFF;padding: 20px;border-radius: 4px;">
        <p class="lay-title">回复</p>
        <span class="layui-layer-setwin"><a class="layui-layer-ico layui-layer-close layui-layer-close1" href="javascript:;"></a></span>
        <div class="lay-center" style="margin-top: 10px;font-size: 15px;">
            <p class="weui-uploader__title" style="font-size: 17px;"><span class="red">*</span>回复内容</p>
            <div contenteditable="true" id="finalMark" placeholder="请输入回复内容" class="weui-textarea final-area " style="border: none;padding: 0px;"></div>
        </div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell" style="padding: 10px 0px;">
                <div class="weui-cell__bd">
                    <div class="weui-uploader">
                        <div class="weui-uploader__hd">
                            <p class="weui-uploader__title">图片上传</p>
                        </div>
                        <div class="weui-uploader__bd">
                            <ul class="weui-uploader__files" id="uploaderFiles">
                               <!-- <li class="weui-uploader__file weui-uploader__file_status" style="background-image:url(./images/pic_160.png)">
                                    <div class="weui-uploader__file-content">
                                        <i class="weui-icon-warn"></i>
                                    </div>
                                </li>-->
                            </ul>
                            <div class="weui-uploader__input-box">
                                <input id="uploaderInput" class="weui-uploader__input" type="file" accept="image/*" multiple="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="lay-btm_btn" style="padding: 0px 20px;">
            <a href="javascript:;" id="submitBtn" class="weui-btn weui-btn_primary " style=" border-radius:24px;background: rgba(0, 156, 255, 1);width: 100%">提交</a>
        </div>
    </div>

    <script src="html/js/common.js" type="text/javascript"></script>
    <script>
        $("#reply").click(function () {
            confirmLayer();
        });
        //提交 弹出最终打分情况
        function confirmLayer() {
            layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                area: '90%',
                skin: 'layui-layer-nobg', //没有背景色
                shadeClose: true,
                content: $('#answerDet')
            });

            //解决ipone7及以上键盘弹出后收起底部留有空白
            const windowHeight = window.innerHeight;
            $("#finalMark").blur(function () {
                var windowFocusHeight = window.innerHeight
                if (windowHeight == windowFocusHeight) {
                    return
                }
                var currentPosition;
                var speed = 1; //页面滚动距离
                currentPosition = document.documentElement.scrollTop || document.body.scrollTop;
                currentPosition -= speed;
                window.scrollTo(0, currentPosition); //页面向上滚动
                currentPosition += speed; //speed变量
                window.scrollTo(0, currentPosition); //页面向下滚动
            });

        }
    </script>
</body>
</html>