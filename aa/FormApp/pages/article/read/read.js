const api = require('../../../api.js');
Page({

  data: {
    id: "",
    title: "",
    content: "",
    iconurl: "",
    intro: "",
    username: "",
    tag: [],
    link: [],
    iszan: false
  },

  onLoad: function(options) {
    var that = this;
    var id = options.id;
    wx.request({
      url: api.findarticle,
      data: {
        id: id
      },
      success: function(res) {
        var data = res.data.data;
        console.log(data)
        console.log(JSON.parse(data.article.tags))
        that.setData({
          title: data.article.title,
          content: data.article.content,
          iconurl: data.article.iconurl,
          intro: data.article.intro,
          username: data.user.name,
          tag: JSON.parse(data.article.tags),
          link: JSON.parse(data.article.link),
          id: data.article.id
        });
      }
    })

  },
  lookform: function(e) {
    // console.log(e.currentTarget.dataset.title)
    // var title = e.currentTarget.dataset.title;
    var password = e.currentTarget.dataset.password;
    // var form = wx.getStorageSync("form");
    // if (form == undefined || form.length == 0) {
    //   wx.showModal({
    //     title: '温馨提示',
    //     content: '打开失败',
    //     showCancel: false
    //   })
    // } else {
    //   for (var i = 0; i < form.length; i++) {
    //     if (form[i].title == title) {
    //       password = form[i].password;
    //       wx.navigateTo({
    //         url: '/pages/formdetail/formdetail?password=' + password
    //       })
    //     }
    //   }
    //   if (password == "") {
    //     wx.showModal({
    //       title: '温馨提示',
    //       content: '打开失败',
    //       showCancel: false
    //     })
    //     return;
    //   }
    // }
    wx.request({
      url: api.checkform,
      data: {
        password: password
      },
      success: function(res) {
        if (res.data.data == false) {
          wx.showModal({
            title: '温馨提示',
            content: '您输入的密匙对应的表单不存在',
            showCancel: false
          })
        } else {
          wx.navigateTo({
            url: '/pages/formdetail/formdetail?password=' + password
          })
        }
      }

    })

  },

  author: function(e) {
    wx.showModal({
      title: '温馨提示',
      content: '因为隐私问题,暂不显示作者信息',
      showCancel: false
    })
  },

  zan: function(e) {
    /****TODO：添加赞的个数 */
    var that = this;
    wx.request({
      url: api.zan,
      data: {
        id: that.data.id
      },
      success:function(res){
        if(res.data.data == true){
          that.setData({
            iszan: true
          })
        }
      }
    })
  }

})