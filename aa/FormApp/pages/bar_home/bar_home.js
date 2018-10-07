const api = require('../../api.js');
Page({

  data: {
    name: '游客',
    avatar: '../../img/login.png',
    inputShowed: false,
    inputVal: "",
    inputShowed: false,
    inputVal: "",
    state: '未登录',
    islogin: false,
    userid: '',
    num:{
      form:0,
      activity:0,
      article:0
    },
    good:[],
    tags:[]
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

    wx.request({
      url: api.onloaddata,
      data:{
        level:5
      },
      success:function(res){
        console.log(res)
        var good = res.data.data.good;
        for (var i = 0; i < good.length;i++){
          good[i].tags = JSON.parse(good[i].tags);
}

        that.setData({
          num:res.data.data.num,
          good: good,
          tags: res.data.data.tags,
        });
      }
    })
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
  },

  moretag:function(){
    wx.switchTab({
      url: "/pages/bar_sear/bar_sear",
    })
  },

  lookarticle:function(e){
  
   var id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '/pages/article/read/read?id=' + id,
    })
  }
})