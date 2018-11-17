// pages/bar_add/bar_add.js
Page({


  buildactivity: function() {
    wx.navigateTo({
      url: '../addactivity/addactivity',
    })

    // wx.showModal({
    //   title: '温馨提示',
    //   content: '暂不开放,您可以使用创建表单来执行',
    // })
  },

  buildquestionnaire: function() {
    wx.navigateTo({
      url: '../addform/addform',
    })
  },

  buildarticle: function() {
    wx.navigateTo({
      url: '../article/write/write',
    })
  },

  buildexcel : function () {
    wx.navigateTo({
      url: '../addexcel/addexcel',
    })
  },

  topassword:function(){
    wx.navigateTo({
      url: '/pages/password/password',
    })
  }
})