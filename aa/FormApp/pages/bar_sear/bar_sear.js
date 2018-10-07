var api = require('../../api.js');

Page({
  data: {
    current_scroll: '1',
    tabs: [],
    data: [],
    height: "1000"
  },


  handleChangeScroll({
    detail
  }) {
    var that = this;
    that.setData({
      current_scroll: detail.key
    })
    wx.showLoading({
      title: '加载中',
    })
    var select = that.data.tabs[detail.key].name;
    console.log(select)
    wx.request({
      url: api.findallbytag,
      data: {
        tag: select
      },
      success: function(res) {
        wx.hideLoading()
        console.log(res)
        var temp = res.data.data;
        temp = that.bubbleSort(temp);
        that.setData({
          data: temp.reverse(),

        });
      },
      fail: function() {
        wx.hideLoading()
      }
    })
  },

  onLoad(option) {
    var that = this;

    var infor = wx.getSystemInfoSync();
    console.log(infor.windowHeight)
    that.setData({
      height: infor.windowHeight
    });


    wx.request({
      url: api.getAllTags,
      success: function(res) {
        console.log(res)
        that.setData({
          tabs: res.data.data
        });
        wx.request({
          url: api.findallbytag,
          data: {
            tag: that.data.tabs[1].name
          },
          success: function(res) {
            var temp = res.data.data;
            temp = that.bubbleSort(temp);
            that.setData({
              data: temp.reverse()
            });
          }
        })
      }

    })
  },
  bubbleSort: function(arr) {
    var len = arr.length;
    for (var i = 0; i < len; i++) {
      for (var j = 0; j < len - 1 - i; j++) {

        var year = arr[j].time.slice(0, 4);
        var month = arr[j].time.slice(5, 7);
        var day = arr[j].time.slice(8, 10);
        var hour = arr[j].time.slice(11, 13);
        var min = arr[j].time.slice(14, 16);
        var second = arr[j].time.slice(17, 19);
        var time1 = parseInt(year + month + day + hour + min + second);
        year = arr[j + 1].time.slice(0, 4);
        month = arr[j + 1].time.slice(5, 7);
        day = arr[j + 1].time.slice(8, 10);
        hour = arr[j + 1].time.slice(11, 13);
        min = arr[j + 1].time.slice(14, 16);
        second = arr[j + 1].time.slice(17, 19);
        var time2 = parseInt(year + month + day + hour + min + second);

        if (time1 > time2) { //相邻元素两两对比
          var temp = arr[j + 1]; //元素交换
          arr[j + 1] = arr[j];
          arr[j] = temp;
        }
      }
    }
    return arr;
  },

  look: function(e) {
    console.log(e)
    var type = e.currentTarget.dataset.type;
    if (type == "表单") {
      wx.navigateTo({
        url: '/pages/formdetail/formdetail?password=' + e.currentTarget.dataset.id
      })
    }
    if (type == "分享") {
      wx.navigateTo({
        url: '/pages/article/read/read?id=' + e.currentTarget.dataset.id
      })
    }
  }
});


//     id: 1234,
//     username: "胡晨阳",
//     usericon: "http://p18.qhimg.com/bdm/720_444_0/t01267fbc124a357621.jpg",
//     title: "论人类的发展史",
//     intro: "研究如何写代码哈哈哈哈哈，就是这样坑",
//     icon: "http://p17.qhimg.com/bdm/720_444_0/t0182df7a1171d3a143.jpg",
//     tag: "运动"
//   },
//   {
//     id: 1234,
//     username: "王顺顺",
//     usericon: "http://p18.qhimg.com/bdm/720_444_0/t01267fbc124a357621.jpg",
//     title: "如何下载小黄片？",
//     intro: "研究如何写代码哈哈哈哈哈，就是这样坑",
//     icon: "http://p17.qhimg.com/bdm/720_444_0/t0182df7a1171d3a143.jpg",
//     tag: "运动"
//   },
//   {
//     id: 1234,
//     username: "张康",
//     usericon: "http://p18.qhimg.com/bdm/720_444_0/t01267fbc124a357621.jpg",
//     title: "如何寻找好基友？",
//     intro: "研究如何写代码哈哈哈哈哈，就是这样坑",
//     icon: "http://p17.qhimg.com/bdm/720_444_0/t0182df7a1171d3a143.jpg",
//     tag: "运动"
//   },
//   {
//     id: 1234,
//     username: "胡晨阳",
//     usericon: "http://p18.qhimg.com/bdm/720_444_0/t01267fbc124a357621.jpg",
//     title: "论人类的发展史",
//     intro: "研究如何写代码哈哈哈哈哈，就是这样坑",
//     icon: "http://p17.qhimg.com/bdm/720_444_0/t0182df7a1171d3a143.jpg",
//     tag: "运动"
//   },
//   {
//     id: 1234,
//     username: "王顺顺",
//     usericon: "http://p18.qhimg.com/bdm/720_444_0/t01267fbc124a357621.jpg",
//     title: "如何下载小黄片？",
//     intro: "研究如何写代码哈哈哈哈哈，就是这样坑",
//     icon: "http://p17.qhimg.com/bdm/720_444_0/t0182df7a1171d3a143.jpg",
//     tag: "运动"
//   },