<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
</head>
<script>
    $(document).ready(function () {
        layer.open({
            title: '提示',
            closeBtn:false
            ,content: '请登录学校内网查看该网页',
            yes:function (index) {
                window.location.href="${base}/"
                layer.close(index)
            }
        });
    });
</script>
<body>

</body>

</html>