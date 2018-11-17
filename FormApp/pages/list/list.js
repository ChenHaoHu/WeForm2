var api = require('../../api.js');
Page({

  data: {
    type: "",
    pageNum: 1,
    pageSize: 4,
    data: [],
    height: "1000",
    over: false
  },
  onLoad(options) {
    var type = options.type;
    var that = this;
    var infor = wx.getSystemInfoSync();
    console.log(infor.windowHeight)
    that.setData({
      height: infor.windowHeight,
      type: type
    });
    if (type == 'article') {
      wx.setNavigationBarTitle({
        title: '分享',
      })
      wx.request({
        url: api.getArticleByPage,
        data: {
          pageNum: that.data.pageNum,
          pageSize: that.data.pageSize
        },
        success: function(res) {
          console.log(res)
          that.setData({
            data: res.data.data.data.reverse()
          })
        }
      })
    }

    if (type == 'form') {
      wx.setNavigationBarTitle({
        title: '调查问卷',
      })
      wx.request({
        url: api.getFormByPage,
        data: {
          pageNum: that.data.pageNum,
          pageSize: that.data.pageSize
        },
        success: function(res) {
          console.log(res)
          console.log(res.data.data.pageSize)
          that.setData({
            data: res.data.data.data.reverse()
          })
        }
      })
    }


    if (type == 'activity') {
      wx.setNavigationBarTitle({
        title: '活动',
      })
      wx.request({
        url: api.getActivityByPage,
        data: {
          pageNum: that.data.pageNum,
          pageSize: that.data.pageSize
        },
        success: function(res) {
          console.log(res)
          console.log(res.data.data.pageSize)
          that.setData({
            data: res.data.data.data.reverse()
          })
        }
      })
    }
  },
  look: function(e) {
    var type = this.data.type;
    if (type == "article") {
      wx.navigateTo({
        url: '/pages/data/read/read?id=' + e.currentTarget.dataset.id
      })
    } else if (type == "form") {
      wx.navigateTo({
        url: '/pages/formdetail/formdetail?password=' + e.currentTarget.dataset.id
      })
    } else if (type == "activity") {
      wx.navigateTo({
        url: '/pages/formdetail/formdetail?password=' + e.currentTarget.dataset.id
      })
    }
  },


  onReachBottom() {
    var that = this;
    var url = "";
    if (that.data.type == 'form') {
      url = api.getFormByPage;
    } else if (that.data.type == 'article') {
      url = api.getArticleByPage;
    } else if (that.data.type == 'activity') {
      url = api.getActivityByPage;
    }


    wx.showLoading({
      title: '加载中',
    })

    var num = that.data.pageNum;
    wx.request({
      url: url,
      data: {
        pageNum: num + 1,
        pageSize: that.data.pageSize
      },
      success: function(res) {
        console.log(res)
        console.log(res.data.data.pageSize)
        var list = that.data.data;
        var data = res.data.data.data.reverse()

        for (var i = 0; i < data.length; i++) {
          list.push(data[i]);
        }
        if (that.data.over == false) {
          that.setData({
            data: list,
            pageNum: num + 1
          })
        } else {
          wx.showModal({
            title: '温馨提示',
            content: '您已经刷到最底下了',
            showCancel: false
          })
        }
        console.log(data.length);
        console.log(that.data.pageSize);
        if (data.length < that.data.pageSize) {
          that.setData({
            over: true
          });
        }
        wx.hideLoading();
      }
    })
  }
})