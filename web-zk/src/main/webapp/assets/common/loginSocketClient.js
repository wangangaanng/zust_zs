/**
 * Created by win on 2015/6/25.
 */
var websocket = null; //websocket实例
var lockReconnect = false;//避免重复连接
var _websocketUrl = "";
var _sockJSUrl = "";
var _scenceid = "";

function connectionWithLogin(websocketUrl,sockJSUrl,scenceid) {
   /*
    if (!websocketUrl || !sockJSUrl) {
        alert("链接地址不能为空");
    }
    _websocketUrl = websocketUrl;
    _sockJSUrl = sockJSUrl;
    _scenceid = scenceid;
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://" + websocketUrl);
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://" + websocketUrl);
    } else {
        websocket = new SockJS("ws://" + sockJSUrl);
    }
    websocket.onopen = function (evnt) {
        //登陆成功后，用员工编号进行登陆
        sendLoginRequest();
    };
    websocket.onmessage = function (evnt) {
        console.log(evnt);
        var data = evnt.data;
        data = jQuery.parseJSON(data);
        if(data.msgBody==_scenceid){
            sendLoginOk();
        }
    };
    websocket.onerror = function (evnt) {
        sendMessZK("alert您的浏览器不支持进行扫码登陆");
    };
    websocket.onclose = function (evnt) {
        reconnect();
        //sendMessZK("alertWebSocket已经关闭");
    }
    */
}

function sendLoginRequest() {
    if (websocket) {
        var msgClient = {
            "targetId":"0",
            "msgType": 3,
            "dataType":0,
            "msgBody": "",
            "msgUrl":"",
            "msgIcon":"",
            "empId":_scenceid
        };
        websocket.send(JSON.stringify(msgClient));
    } else {

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

//发送消息给zk后台
function sendLoginOk() {
    var jq = window.jq;
    var zk = window.zk;
    var btnLogin = zk.Widget.$(jq("$commandBtn"), window.document);
    window.zAu.send(new zk.Event(btnLogin, "onClick", ""));
}

function openUrl(url){
    window.parent.location.href = url;
    // window.opener.location.href = url;
}
function displayImg(imgurl){
    window.parent.displayImg(imgurl);
}