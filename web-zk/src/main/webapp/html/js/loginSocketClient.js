/**
 * Created by win on 2015/6/25.
 */
var websocket = null; //websocket实例
var lockReconnect = false;//避免重复连接
var _websocketUrl = "";
var _sockJSUrl = "";
var _scenceid = "";

function connectionWithLogin(websocketUrl,sockJSUrl,scenceid) {
    if (!websocketUrl || !sockJSUrl) {
        console.log("链接地址不能为空");
    }
    _websocketUrl = websocketUrl;
    _sockJSUrl = sockJSUrl;
    _scenceid = scenceid;
    if ('WebSocket' in window) {
        websocket = new WebSocket("wss://" + websocketUrl+scenceid);
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("wss://" + websocketUrl+scenceid);
    }
    websocket.onopen = function (evnt) {
        //链接建立成功后，每隔30秒发送数据维持心跳，避免websocket连接因nginx超时而自动断开
        window.setInterval(function(){
            sendHeartRequest();
        },30000);
    };
    websocket.onmessage = function (evnt) {
        var data = evnt.data;
        data = jQuery.parseJSON(data);
        //不能用paseInt来对比，同值字符串的对比会出现有些版本的浏览器值不等于；
        if(data.msgBody==_scenceid){
            sendLoginOk(data.empId,_scenceid);
        }
    };
    websocket.onerror = function (evnt) {
        console.log("当前不支持进行扫码登陆，请用账号密码登录！");
    };
    websocket.onclose = function (evnt) {
        reconnect();
        //sendMessZK("alertWebSocket已经关闭");
    }
}


function sendHeartRequest() {
    if (websocket) {
        var ping = "ping";
        websocket.send(ping);
    } else {
        console.log("websocket connect closed");
    }
}



function send(message, msgType,linkUrl,icon) {
    if (websocket) {
        var msgClient = {
            "targetId": "0",
            "msgType": msgType,
            "msgBody": message,
            "msgUrl":linkUrl,
            "msgIcon":icon
        };
        websocket.send(JSON.stringify(msgClient));
    } else {

    }
}

function reconnect() {
    if(lockReconnect) return;
    lockReconnect = true;
    //没连接上会一直重连，设置延迟避免请求过多
    setTimeout(function () {
        connectionWithLogin(_websocketUrl,_sockJSUrl,_scenceid);
        lockReconnect = false;
    }, 2000);
}
//心跳检测
var heartCheck = {
    timeout: 60000,//60秒
    timeoutObj: null,
    serverTimeoutObj: null,
    reset: function(){
        clearTimeout(this.timeoutObj);
        clearTimeout(this.serverTimeoutObj);
        return this;
    },
    start: function(){
        var self = this;
        this.timeoutObj = setTimeout(function(){
            //这里发送一个心跳，后端收到后，返回一个心跳消息，
            //onmessage拿到返回的心跳就说明连接正常
            websocket.send("HeartBeat");
            self.serverTimeoutObj = setTimeout(function(){//如果超过一定时间还没重置，说明后端主动断开了
                websocket.close();//如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect 会触发onclose导致重连两次
            }, self.timeout)
        }, this.timeout)
    }
  }

//收到messapi处理后的微信用户信息后进行登陆
function sendLoginOk(scence,sessinId) {
    //换展示图片，表示只能扫描一次
    $("#qrLoginImage").attr("src","html/img/reload.png");
    var url = urlApi+"/scanLogin/loginByOpenId.do";
    var params = {
        "empId":scence,
        "cityId":cityId,
        "sessionId":sessinId
    };
    var pdata={
        "data":JSON.stringify(params),
        timestamp : new Date().getTime()
    }
    $.post(url, pdata, function(data) {
        if (data.backCode == 0) {
            // addCookie("empNo", empNo);
            window.location.href=loginUrl+data.bean;
            //document.getElementById("loginPage").location.href = loginUrl + data.bean;
        } else {
            $(".login-tip").html(data.errorMess).addClass("show").removeClass("hide");
            $("body").mLoading("hide");
        }
    });

}

function openUrl(url){
    window.parent.location.href = url;
}
function displayImg(imgurl){
    window.parent.displayImg(imgurl);
}