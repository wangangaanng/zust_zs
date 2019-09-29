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
<style>

</style>

<body>
<#include "com/header.ftl">
<div class="main">
    <div class="container">
        <div class="content-form">


        </div>
    </div>

<#include "com/footer.ftl">
    <script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>

    <script>
        $(document).ready(function () {
            var jsonObj={
                "wzbh":1
            }
            ajax("zustjy/bckjBizDcwj/dcwjList", jsonObj, function (data) {
                if(data.backCode==0){
                }
            })

        })

    </script>
</body>

</html>