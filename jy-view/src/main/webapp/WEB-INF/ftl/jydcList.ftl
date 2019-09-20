<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>就业网</title>
    <#include "com/config.ftl">
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
                "wzbh":""
            }
            ajax("zustjy/bckjBizDcwj/dcwjList", jsonObj, function (data) {
                if(data.backCode==0){
                }
            })

        })

    </script>
</body>

</html>