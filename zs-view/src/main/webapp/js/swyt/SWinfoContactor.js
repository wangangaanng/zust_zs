/**
 *  作者：2515421994@qq.com
    时间：2019-10-25
    描述：基本信息2：添加联系人
 */
//获取家庭成员信息
function getContactors() {
    var data = {
        "yhRefOwid":getCookie("swOwid")
    }
    ajax("zustswyt/bckjBizJtcyxx/getInfo", data, function (data) {
        if(data.backCode==0){
            $.each(data.bean,function (k,p) {
                switch(p.cylb){
                    case 0:
                        for (x in p) {
                            key = x;
                            value = p[x];
                            $("input[name='fa"+key+"']").val(value);
                        }
                        break;
                    case 1:
                        for (x in p) {
                            key = x;
                            value = p[x];
                            $("input[name='mo"+key+"']").val(value);
                        }
                        break;
                    case 2:
                        for (x in p) {
                            key = x;
                            value = p[x];
                            $("input[name='te"+key+"']").val(value);
                        }
                        break;
                }
            });
        }else{
            walert(data.errorMess)
        }
    })
}
