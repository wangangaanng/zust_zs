var owid = ""; //项目编号
var openId = "";
var flowId = ""; //流程编号
var mapType = ""; //0表示计划，1表示实际
var state = ""; //0是保存按钮在那里，1是不存在保存按钮
var appKey = "";
var pathArr = []; //点集合

var strokeStyleRed = {
    strokeColor: "#fc625d",
    strokeWeight: 6,
    strokeOpacity: 0.9
};
var strokeStyleGrn = {
    strokeColor: "#43cfa9",
    strokeWeight: 6,
    strokeOpacity: 0.9
};
var pointNum = 0;
$(document).ready(function() {
    owid = getRequests()[0];
    flowId = getRequests()[1];
    mapType = parseInt(getRequests()[2]);
    state = parseInt(getRequests()[3]);
    map_init();

    //确认提交轨迹 保存轨迹
    $("#sureBtn").click(function() {
        layer.confirm('提交后不可再修改哦！', {
            btn: ['确认提交', '继续绘制'] //按钮
        }, function() {
            layer.closeAll();
            savePlanPoints();
        }, function() {
            //取消操作
        });
    });

});

//获取地图点位列表
function listRoute() {
    var paramThis = {
        "owid": owid,
        "mapType": mapType
    };
    var raNum = num();
    //$.post("bckjBizProjectMap/listRoute", paramThis, function(data) {
    window.parent.ajax("target/bckjBizProjectMap/listRoute", paramThis, function(data) {
        if(data.backCode == 0) {
            map.panTo(map.centerAndZoom(data.bean.city, 13));

            //计划
            if(mapType == 0) {

                $.each(data.bean.mapList, function(k, p) {
                    let pointsPlan = [];
                    $.each(p,function(x,y){
                        pointsPlan.push(new BMap.Point(y.lang, y.lat));
                    })
                    addPolyLine(pointsPlan, strokeStyleRed);
                });

            } else {

                $.each(data.bean.mapList, function(k, p) {
                    let pointsPlan = [];
                    $.each(p,function(x,y){
                        pointsPlan.push(new BMap.Point(y.lang, y.lat));
                    })
                    addPolyLine(pointsPlan, strokeStyleRed);
                });

                //实际
                $.each(data.bean.mapList2, function(k, p) {
                    let pointsAct = [];
                    $.each(p,function(x,y){
                        pointsAct.push(new BMap.Point(y.lang, y.lat));
                    });
                    addPolyLine(pointsAct, strokeStyleGrn);
                });

            }

        }
    },1,raNum,md5(raNum));

    //});
}

function savePlanPoints() {
    var urlArr = ["bckjBizProjectMap/savePlanRoute", "bckjBizProjectMap/saveActualRoute"];
    let paramThis = {
        "owid": owid,
        "projectFlow": flowId,
        "pathList": pathArr
    };
    let raNum = num();
    window.parent.ajax("target/" + urlArr[mapType], paramThis, function(data) {
        //$.post(thisUrl, paramThis, function(data) {
        if(data.backCode == 0) {
            layer.msg('保存成功');
            $(".map-area-bottom").hide();//隐藏底部避免歧义
            pathArr = []; //存好之后要清空避免点叠加
            location.reload();
        } else {
            layer.msg("保存失败，请重试");
        }
    }, 1, raNum, md5(raNum));
}

//地图相关++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
var map; //Map实例
function map_init() {
    //添加地图类型控件
    map = new BMap.Map("map", {
        mapType: BMAP_HYBRID_MAP
    }); //混合图   2D图BMAP_NORMAL_MAP,

    //移动到当前所在的位置
    /*var geolocation = new BMap.Geolocation();

     geolocation.getCurrentPosition(function(result) {
     if(this.getStatus() == BMAP_STATUS_SUCCESS) {
     map.panTo(new BMap.Point(result.point.lng, result.point.lat));
     }
     });*/

    //第1步：设置地图中心点，默认包头
    var point = new BMap.Point(109.83, 40.65);
    map.centerAndZoom(point, 11);
    //第2步：初始化地图,设置中心点坐标和地图级别。

    //第3步：启用滚轮放大缩小
    map.enableScrollWheelZoom(true);
    //第4步：向地图中添加缩放控件

    //第6步：向地图中添加比例尺控件
    /*var ctrlSca = new window.BMap.ScaleControl({
     anchor: BMAP_ANCHOR_TOP_LEFT
     });
     map.addControl(ctrlSca);*/

    //第7步：向地图添加地图类型
    map.addControl(new BMap.MapTypeControl({
        mapTypes: [
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ],
        anchor: BMAP_ANCHOR_TOP_LEFT,
        offset: new BMap.Size(5, 60), //偏离值
    }));

    //第8步：添加当前定位
    // 添加定位控件
    var geolocationControl = new BMap.GeolocationControl({
        anchor: BMAP_ANCHOR_BOTTOM_RIGHT,
        offset: new BMap.Size(5, 400), //偏离值
    });
    geolocationControl.addEventListener("locationSuccess", function(e) {
        // 定位成功事件
        var address = '';
        address += e.addressComponent.province;
        address += e.addressComponent.city;
        address += e.addressComponent.district;
        address += e.addressComponent.street;
        address += e.addressComponent.streetNumber;
        //alert("当前定位地址为：" + address);
    });
    geolocationControl.addEventListener("locationError", function(e) {
        // 定位失败事件
        //alert(e.message);
    });
    map.addControl(geolocationControl);

    var overlays = [];
    var overlaycomplete = function(e) {

        overlays.push(e.overlay);
        var path = e.overlay.getPath(); //Array<Point> 返回多边型的点数组
        var pstr = '';
        area = "";
        var thisPath = [];
        for(var i = 0; i < path.length; i++) {
            pointNum++;
            thisPath.push({
                "gpsLontitu": path[i].lng,
                "gpsLatitu": path[i].lat
            });
            pstr += '<div>' + (pointNum) + '.<label>经度</label><input type="text" placeholder="经度" readonly="readonly" value="' + path[i].lng + '"/><label>纬度</label><input type="text" placeholder="纬度" readonly="readonly" value="' + path[i].lat + '"/></div>';
            /*console.log("lng:"+path[i].lng+"\n lat:"+path[i].lat);*/
            if(i == 0) {
                area = '经度' + path[i].lng + ' 纬度' + path[i].lat;
            } else {
                area += ',经度' + path[i].lng + ' 纬度' + path[i].lat;
            }
        }

        pathArr.push(thisPath);
        $(".map-area-bottom").show();
        $("#point-list").append(pstr);
    };

    var styleOptions = {
        strokeColor: "rgba(40, 175, 255, 1)", //边线颜色。
        fillColor: "rgba(40, 175, 255, 1)", //填充颜色。当参数为空时，圆形将没有填充效果。
        strokeWeight: 3, //边线的宽度，以像素为单位。
        strokeOpacity: 1, //边线透明度，取值范围0 - 1。
        fillOpacity: 0.5, //填充的透明度，取值范围0 - 1。
        strokeStyle: 'solid' //边线的样式，solid或dashed。
    }

    if(state == 0) { // 0是保存按钮在那里  1是保存按钮不在那里
        //设置鼠标样式
        map.setDefaultCursor("crosshair");
        $(".map-area-tip").show();

        //实例化鼠标绘制工具
        var drawingManager = new BMapLib.DrawingManager(map, {
            isOpen: false, //是否开启绘制模式
            enableDrawingTool: true, //是否显示工具栏
            drawingMode: BMAP_DRAWING_POLYLINE, //绘制模式  多边形
            drawingToolOptions: {
                anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
                offset: new BMap.Size(5, 5), //偏离值
                drawingModes: [
                    BMAP_DRAWING_POLYLINE
                ]
            },
            polylineOptions: styleOptions, //线的样式
        });
        //添加鼠标绘制工具监听事件，用于获取绘制结果
        drawingManager.addEventListener('overlaycomplete', overlaycomplete);
    }

    function clearAll() {
        for(var i = 0; i < overlays.length; i++) {
            map.removeOverlay(overlays[i]);
        }
        overlays.length = 0;
        $("#point-list").html("");
        pointNum = 0;
        pathArr = [];
    }

    $("#resetBtn").click(function() {
        clearAll();
    });

    /* //点击获取其经纬度并添加点
     map.addEventListener("click",function(e){
     //alert(e.point.lng + "," + e.point.lat);
     map.clearOverlays();
     var marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));
     map.addOverlay(marker);
     $("#lng-input").val(e.point.lng);
     $("#lat-input").val(e.point.lat);
     });*/

    listRoute();
}

var key = 0; //开关
var newpoint; //一个经纬度
var points = []; //数组，放经纬度信息
function getlinepoints() {
    var pts = polyline.getPath();
    alert(pts.length);
    var sss = "";
    for(var i = 0; i < pts.length; i++) {
        sss += i + ": lng " + pts[i].lng + ",lat " + pts[i].lat + "\r\n";
    }
    alert(sss);
}

//异步调用百度js
function map_load() {
    var load = document.createElement("script");
    load.src = "https://api.map.baidu.com/api?v=2.0&ak=DDd05fcba9fea5b83bf648515e04fc4c&callback=map_init";
    document.body.appendChild(load);
}

//添加线
function addPolyLine(a, b) {
    var newPolyline = new BMap.Polyline(a, b);
    map.addOverlay(newPolyline);
}