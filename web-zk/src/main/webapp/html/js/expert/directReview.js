$(function() {
    $('.close-project').click(function() {
        var str = "";
        var target = $("input[name='checkbox2']:checked");
        if (target.length>0){
            $("input[name='checkbox2']:checked").each(function (i) {
                console.log($(this).val());
                str += "<p>" + $(this).val() + "</p>";
            });
            $(".chose-projects").html(str);  
        }else{
            $(".chose-projects").html('请选择回避项目'); 
        }
    })
}) 