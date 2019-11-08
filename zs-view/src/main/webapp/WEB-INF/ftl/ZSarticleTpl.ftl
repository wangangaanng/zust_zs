<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/ZSconfig.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
</head>

<body>
    <#include "com/ZSheader.ftl">
    <img class="ejgg" style="width:100%;height:300px;" src="${base}/img/loginbackgrouind2.jpg">
    <div class="clear"></div>
        <div class="main">
            <div class="container">
                <div class="content">
                   <#include "com/subMenu.ftl">
                       <div class="content-list">
                           <div class="articleTpl-detail">
                               <div class="articleTpl-title">
                               ${thirdDirName!''}
                               </div>
                               <div class="article-detail-text">
                               <#if (bxlx??)&&(bxlx=="0")&&(result??)>
                                   <p>${result.wznr!''}</p>
                                   <#if (result.fileList??)&&(result.fileList?size>0)>
                                       <div class="file">
                                           <div style="font-weight: bold;font-size: 16px;">附件</div>
                                           <ul>
                                               <#list result.fileList as obj>
                                                   <li><a href="${imagePath+obj.FILE_PATH}">${obj.FILE_LABEL!''}</a></li>
                                               </#list>
                                           </ul>
                                       </div>
                                   </#if>

                               </#if>


                               </div>
                           </div>
                       </div>
                </div>

            </div>
        </div>

    <#include "com/footer.ftl">
</body>

</html>
