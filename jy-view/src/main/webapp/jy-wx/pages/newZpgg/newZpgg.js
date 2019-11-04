// pages/newJob/newJob.js
var common = require('../../libs/common/common.js')
var util = require('../../utils/util.js')
import WxValidate from '../../libs/wx-validate/WxValidate'
const app = getApp()
var url = app.globalData.ApiUrl;
var imgPath = app.globalData.imgPath;

Page({

    data: {
        hidden1: false,
        imgPath: imgPath,
        zwMxxyColumns: [],
        zwMxzyColumns: [],
        show: {
            bottom: false,
            zwMxxy: false,
            zwMxzy: false
        },
        area: '请选择',
        areaList: {},
        loading: true,
        value: 330106,
        zwMxxyStr: '请选择',
        zwMxzyStr: '请选择',
        form: {

        },
        btndisabled: false,
        parentId:''
    },
    showModal(error) {
        wx.showModal({
            content: error.msg,
            showCancel: false,
        })
    },
    submitForm(e) {
        var that = this
        const params = e.detail.value

        console.log(params)

        // 传入表单数据，调用验证方法
        if (!this.WxValidate.checkForm(params)) {
            const error = this.WxValidate.errorList[0]
            this.showModal(error)
            return false
        }
        params.zwlx = 2
        params.qyxxRefOwid = wx.getStorageSync('yhOwid')
        common.ajax('zustjy/bckjBizJob/addOneJob', params, function (res) {
            if (res.data.backCode == 0) {
                that.setData({
                    btndisabled: true
                })
                wx.showModal({
                    title: '提示',
                    showCancel: false,
                    content: "发布成功",
                    success(res) {
                        if (res.confirm) {
                            wx.navigateBack({
                                delta: 1
                            })
                            console.log('用户点击确定')
                        } else if (res.cancel) {
                            console.log('用户点击取消')
                        }
                    }
                })
            } else {
                wx.showToast({
                    title: res.data.errorMess,
                    icon: 'none',
                    duration: 2000
                })
            }
        });
    },
    initValidate() {
        // 验证字段的规则
        const rules = {
            zwbt: {
                required: true,
            },
            zwPro: {
                required: true,
            },
            zwCity: {
                required: true
            },
            memo: {
                required: true,
                minlength: 50,
            },
        }

        // 验证字段的提示信息，若不传则调用默认的信息
        const messages = {
            zwbt: {
                required: '请填写标题'
            },
            zwPro: {
                required: '请选择所在省市',
            },
            zwCity: {
                required: '请选择所在省市',
            },
            memo: {
                required: '请填写详细介绍',
                minlength: "详细介绍不得少于50字",
            }

        }
        this.WxValidate = new WxValidate(rules, messages)
    },
    onConfirm(e) {
        if (e.target.dataset.type == 1) {
            const { mz, owid } = e.detail.value;
            this.setData({
                zwMxxyStr: mz,
                'form.zwMxxy': mz,
                parentId: owid
            })
            getZyList1(this)
            this.toggle('zwMxxy', false);
        } else if (e.target.dataset.type == 2) {
            const { mz, owid } = e.detail.value;
            this.setData({
                zwMxzyStr: mz,
                'form.zwMxzy': mz
            })
            this.toggle('zwMxzy', false);
        } else if (e.target.dataset.type == 7) {
            this.setData({
                area: `${e.detail.values[0].name}-${e.detail.values[1].name}`,
                'form.zwPro': e.detail.values[0].name,
                'form.zwCity': e.detail.values[1].name
            })
            this.toggle('bottom', false);
        }

    },
    onCancel(e) {
        if (e.target.dataset.type == 1) {
            this.toggle('zwMxxy', false);
        } else if (e.target.dataset.type == 2) {
            this.toggle('zwMxzy', false);
        } else if (e.target.dataset.type == 7) {
            this.toggle('bottom', false);
        }
    },
    toggle(type, show) {
        this.setData({
            [`show.${type}`]: show,
            hidden1: show
        });
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.initValidate()
        getOneCompany(this)
        getZyList(this)
        // getZyList1(this)
    },
    showBottom(e) {
        if (e.target.dataset.type == 1) {
            this.toggle('zwMxxy', true);
        } else if (e.target.dataset.type == 2) {
            if (this.data.parentId){
                this.toggle('zwMxzy', true);
            }else{
                wx.showToast({
                    title: '请先选择面向学院',
                    icon:'none'
                })
            }

        } else if (e.target.dataset.type == 7) {
            this.toggle('bottom', true);
        }
    },

    hideBottom(e) {
        if (e.target.dataset.type == 1) {
            this.toggle('zwMxxy', false);
        } else if (e.target.dataset.type == 2) {
            this.toggle('zwMxzy', false);
        } else if (e.target.dataset.type == 7) {
            this.toggle('bottom', false);
        }
    },
    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {
        wx.request({
                url: app.globalData.imgPath + 'getAllRegion.json',
                success: response => {
                this.setData({
                loading: false,
                areaList: response.data.data
            });
    }
    });

    },
    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    }
})

var getZyList = function (that) {
    var data = { "parentId": '-1' };
    common.ajax('zustcommon/bckjBizXyzy/getZyList', data, function (res) {
        if (res.data.backCode == 0) {
            var data = res.data;
            if (data.bean && data.bean.length > 0) {
                for (var i in data.bean) {
                    var obj = {}
                    obj.mz = data.bean[i].mz
                    obj.owid = data.bean[i].owid
                    that.data.zwMxxyColumns.push(obj)
                }
                that.setData({
                    zwMxxyColumns: that.data.zwMxxyColumns
                })
            }
        } else {
            wx.showToast({
                title: res.data.errorMess,
                icon: 'none',
                duration: 2000
            })
        }
    });
}

var getZyList1 = function (that) {
    var data = { "parentId": that.data.parentId };
    common.ajax('zustcommon/bckjBizXyzy/getZyList', data, function (res) {
        if (res.data.backCode == 0) {
            var data = res.data;
            if (data.bean && data.bean.length > 0) {
                for (var i in data.bean) {
                    var obj = {}
                    obj.mz = data.bean[i].mz
                    obj.owid = data.bean[i].owid
                    that.data.zwMxzyColumns.push(obj)
                }
                that.setData({
                    zwMxzyColumns: that.data.zwMxzyColumns
                })
            }
        } else {
            wx.showToast({
                title: res.data.errorMess,
                icon: 'none',
                duration: 2000
            })
        }
    });
}

var getOneCompany = function (that) {
    var data = { "owid": wx.getStorageSync('yhOwid') };
    common.ajax('zustjy/bckjBizQyxx/getOneCompany', data, function (res) {
        if (res.data.backCode == 0) {
            var data = res.data;
            if (data.bean) {
                if (data.bean.qyYx) {
                    that.setData({
                        'form.zwLxyx': data.bean.qyYx
                    })
                }
                if (data.bean.qyProv && data.bean.qyCity && data.bean.qyArea) {
                    that.setData({
                        area: `${data.bean.qyProv}-${data.bean.qyCity}`,
                        'form.zwPro': data.bean.qyProv,
                        'form.zwCity': data.bean.qyCity
                    })
                }
            }
        } else {
            wx.showToast({
                title: res.data.errorMess,
                icon: 'none',
                duration: 2000
            })
        }
    });
}