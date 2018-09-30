// pages/bar_add/bar_add.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    an:{
      
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },



  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var animation = wx.createAnimation({
      transformOrigin: "50% 50%",
      duration: 1000,
      timingFunction: "ease-in-out",
      delay: 0
    })
    this.animation = animation
    animation.translateY(-999).step()
    this.setData({
      an: this.animation.export(),
    })
  },

  buildactivity:function(){
    wx.navigateTo({
      url: '../addactivity/addactivity',
    })
  },

  buildquestionnaire: function () {
    wx.navigateTo({
      url: '../addquestionnaire/addquestionnaire',
    })
  }
})