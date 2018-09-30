var requests = require('../../utils/requests.js');
const api = require('../../api.js');
Page({

  data: {
    imgUrls: [
      'http://p19.qhimg.com/bdm/720_444_0/t012168a8f58bfb5203.jpg',
      'http://p17.qhimg.com/bdm/360_222_0/t01a36f6e90f6319a4b.jpg',
      'http://p17.qhimg.com/bdm/720_444_0/t01ed8c6214fbd7842b.jpg',
    ],
    name: '游客',
    avatar: '../../img/login.png',
    inputShowed: false,
    inputVal: "",
    indicatorDots: true, //是否显示面板指示点
    autoplay: true, //是否自动切换
    interval: 3000, //自动切换时间间隔
    duration: 1000, //滑动动画时长
    inputShowed: false,
    inputVal: "",
    state: '未登录',
    islogin: false,
    userid: ''
  },

  onLoad: function(e) {
    var user = wx.getStorageSync('user');
    var that = this;
    console.log(user)
    if (user.length == 0) {
      setTimeout(function() {
        wx.showModal({
          title: '温馨提示',
          content: '您还有授权登录哦,现在是游客状态,可以点击未登录进行登录',
          showCancel: false
        })
      }, 1000)
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

  showInput: function() {
    this.setData({
      inputShowed: true
    });
  },
  hideInput: function() {
    this.setData({
      inputVal: "",
      inputShowed: false
    });
  },
  clearInput: function() {
    this.setData({
      inputVal: ""
    });
  },
  inputTyping: function(e) {
    this.setData({
      inputVal: e.detail.value
    });
  },
  // ********************检验登录**********************
  tologin: function(e) {
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
  login: function() {
    var that = this;
    wx.showLoading({
      title: '登录中',
    })
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          wx.getUserInfo({
            success: function(e) {
              var data = e.userInfo
              var name = data.nickName
              var avatar = data.avatarUrl
              var gender = data.gender //性别 0：未知、1：男、2：女
              var province = data.province
              var city = data.city
              var country = data.country;
              wx.login({
                success: function(res) {
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
                    success: function(res) {
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
                    fail: function() {
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
  }
})