const api = require('../../api.js');
Page({
  data: {
    list: [
    ],
    title: "",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this
    var formid = options.formid;
    wx.request({
      url: api.getJoinUser,
      data: {
        formid: formid
      },
      success: function(res) {
        console.log(res)
        that.setData({
          list: res.data.data
        });
        wx.request({
          url: api.getFormTitle,
          data: {
            formid: formid
          },
          success:function(res){
            that.setData({
              title: res.data.data
            });
          }
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})