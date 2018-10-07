const api = require('../../api.js');
Page({
  data: {
    listitem: [],
    formid: "",
    title:"",
    password:""
  },
  sublimt(e) {
    console.log(e)
    var userid = wx.getStorageSync("user").userid;
    var that = this;
    wx.request({
      url: api.join,
      data: {
        formid: that.data.formid,
        userid: userid + "",
        content: e.detail.value
      },
      success: function(res) {
        console.log(res);
      if(isNaN(res.data.data)){
        wx.showModal({
          title: '温馨提示',
          content: res.data.data,
          showCancel: false,
          success: function () {
            wx.switchTab({
              url: '/pages/bar_home/bar_home',
            })
          }
        })
      }else{
        var form = wx.getStorageSync("form");
        if (form == undefined || form.length == 0) {
          form = [];
        }

        var newDate = new Date();
        var year = newDate.getFullYear();
        var month = (newDate.getMonth() + 1) < 10 ? "0" + (newDate.getMonth() + 1) : newDate.getMonth() + 1;
        var day = newDate.getDay() < 10 ? "0" + newDate.getDay() : newDate.getDay();
        var hours = newDate.getHours() < 10 ? "0" + newDate.getHours() : newDate.getHours();
        var minuts = newDate.getMinutes() < 10 ? "0" + newDate.getMinutes() : newDate.getMinutes();
        var seconds = newDate.getSeconds() < 10 ? "0" + newDate.getSeconds() : newDate.getSeconds();
        var time = year + "-" + month + "-" + day + " " + hours + ":" + minuts + ":" + seconds;

        form.push({
          title: that.data.title,
          password: that.data.password,
          type: "sign",
          time: time
        });
        wx.setStorageSync("form", form);
        wx.showModal({
          title: '温馨提示',
          content: '提交成功',
          showCancel: false,
          success: function () {
            wx.switchTab({
              url: '/pages/bar_home/bar_home',
            })
          }
        })
      }
      },
      fail: function() {
        wx.showModal({
          title: '温馨提示',
          content: '提交失败',
          showCancel: false,
          success: function() {
            wx.switchTab({
              url: '/pages/bar_home/bar_home',
            })
          }
        })

      }
    })
  },
  onLoad(options) {
    console.log(options);
    var that = this;
    var pass = options.password;
    wx.request({
      url: api.findFormByPass,
      data: {
        password: pass
      },
      success: function(res) {
        var data = res.data.data;
        console.log(data)
        that.setData({
          listitem: JSON.parse(data.mode),
          formid: data.formid,
          title: data.title,
          password: data.password
        })
      }
    })
  }

})