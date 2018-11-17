var api = require('../../api.js');
Page({

  data: {
    name: '游客',
    avatar: '../../img/login.png',
    islogin:false,
    userid:0
  },

  onLoad: function(options) {
    var user = wx.getStorageSync('user');
    var that = this;
    console.log(user)
    if (user.length == 0) {
     
    } else {
      that.setData({
        state: '签到',
        islogin: true,
        userid: user.userid,
        name: user.name,
        avatar: user.avatar,
      });
    }
  },
  tologin: function (e) {
    console.log(e.detail.userInfo)
    var res = e.detail.userInfo;
    if (res == undefined) {
      wx.showModal({
        title: '温馨提示',
        content: '由于我们没能获取到您的授权,登录失败',
        showCancel: false
      })
    } else {
      console.log(res)
      this.login();
    }
  },
  login: function () {
    var that = this;
    wx.showLoading({
      title: '登录中',
    })
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          wx.getUserInfo({
            success: function (e) {
              var data = e.userInfo
              var name = data.nickName
              var avatar = data.avatarUrl
              var gender = data.gender //性别 0：未知、1：男、2：女
              var province = data.province
              var city = data.city
              var country = data.country;
              wx.login({
                success: function (res) {
                  var code = res.code;
                  wx.request({
                    url: api.login,
                    data: {
                      code: code,
                      name: name,
                      avatar: avatar,
                      gender: gender,
                      province: province,
                      city: city,
                      country: country,
                    },
                    success: function (res) {
                      console.log(res)
                      wx.hideLoading();
                      var data = res.data.data;
                      that.setData({
                        state: '同学',
                        islogin: true,
                        userid: data,
                        name: name,
                        avatar: avatar,
                      });
                      var temp = {
                        'userid': data,
                        'name': name,
                        'avatar': avatar
                      };
                      wx.setStorageSync('user', temp)
                    },
                    fail: function () {
                      wx.hideLoading();
                      wx.showToast({
                        title: 'error',
                        icon: 'none'
                      })
                    }
                  })
                }
              })
            }
          })
        } else {
          wx.hideLoading()
          wx.showModal({
            title: '提示',
            content: '您没有授权,操作失败',
          })
        }
      },
      fail: res => {
        wx.hideLoading()
        console.log(res)
      }
    })
  },

})