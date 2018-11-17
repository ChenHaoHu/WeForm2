const api = require('../../api.js');
Page({
  data: {
    imgUrls: [
      'http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg',
      'http://img06.tooopen.com/images/20160818/tooopen_sy_175866434296.jpg',
      'http://img06.tooopen.com/images/20160818/tooopen_sy_175833047715.jpg'
    ],
    detail: {

    }
  },
  onLoad: function(options) {
    var that = this;
    var pass = options.password;
    wx.request({
      url: api.findFormByPass,
      data: {
        password: pass
      },
      success: function(res) {
        console.log(res)
        var data = res.data.data;
        // console.log(JSON.parse(JSON.parse(data.posterurl)))
        that.setData({
          detail: data,
          imgUrls: JSON.parse(data.posterurl)
        })

      }
    })
  },
  sign: function(e) {
    wx.navigateTo({
      url: '/pages/input/input?password=' + this.data.detail.password
    })
  },
  onShareAppMessage(e) {
    console.log(e)
  }

})