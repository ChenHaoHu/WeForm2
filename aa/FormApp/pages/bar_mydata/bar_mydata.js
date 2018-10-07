var api = require('../../api.js');
Page({
  /**
   * 页面的初始数据
   */
  data: {
    userdata: {
      usericon: "http://p19.qhimg.com/bdm/341_210_0/t0173e87bd6deb606d4.jpg",
      username: "简单的年华"
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    console.log(api.login.adduser)
  },

  onGotUserInfo: function(e) {
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          console.log(e)
          var data = e.detail.userInfo
          var nickname = data.nickName
          var avatarUrl = data.avatarUrl
          console.log(nickname + "  " + avatarUrl)

         
          wx.login({
            success: function(res) {
              var code = res.code;
              console.log(code)
              wx.request({
                url: api.login.adduser,
                data: {
                  storeid: '11223',
                  code: code,
                  nickname: nickname,
                  avatar: avatarUrl,
                  mobile: '18642316507',
                  gender: '0'
                },
                header: {
                  'content-type': 'application/json'
                },
                success: function(res) {
                  console.log(res.data)
                },
                fail: function() {
                  console.log("bad ")
                }
              })
            }
          })
        } else {
          console.log("no")
        }
      }
    })
  }
})