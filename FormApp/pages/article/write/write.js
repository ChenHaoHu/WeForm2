const api = require('../../../api.js');
Page({
  data: {
    link: [],
    tag: [],
    iconurl: "",
    title: "",
    intro: "",
    content: "",
    userid: ""
  },
  onShow: function() {

    var userid = wx.getStorageSync("user").userid;
    if (userid == undefined) {
      wx.showModal({
        title: '温馨提示',
        content: '您还没登录,无法编写文章',
        showCancel: false
      })
      wx.navigateBack({

      })

      return;
    } else {
      this.setData({
        userid: userid
      })
    }


    var that = this;
    var data = wx.getStorageSync("data");
    if (data != undefined && data.length != 0) {
      if (data.tag != "") {
        var temp = that.data.tag;
        for (var i = 0; i < temp.length; i++) {
          if (temp[i] == data.tag) {
            wx.showModal({
              title: '温馨提示',
              content: '重复选择',
              showCancel: false
            })
            return;
          }
        }
        temp.push(data.tag);
        that.setData({
          tag: temp
        });
      }

      if (data.link != "") {
        var temp = that.data.link;

        var title2 = data.link.split("^")[0];
        var id2 = data.link.split("^")[1];
        for (var i = 0; i < temp.length; i++) {
          var id = temp[i].id;
          if (id == id2) {
            wx.showModal({
              title: '温馨提示',
              content: '重复选择',
              showCancel: false
            })
            return;
          }
        }
        temp.push({
          title:title2,
          id:id2
        });
        that.setData({
          link: temp
        });
      }
      var temp = {
        tag: "",
        link: ""
      };
      wx.setStorage({
        key: 'data',
        data: temp,
      })
    }
  },
  addlink: function(e) {
    var that = this;
    wx.navigateTo({
      url: '../list/list?type=link',
    })
  },
  addtag: function(e) {
    var that = this;
    wx.navigateTo({
      url: '../list/list?type=tag',
    })
  },
  deleteTag: function(e) {
    console.log(e.target.dataset.name)
    var value = e.target.dataset.name;
    var that = this;
    var temp = that.data.tag;
    for (var i = 0; i < temp.length; i++) {
      if (temp[i] == value) {
        temp.splice(i, 1);
      }
    }
    that.setData({
      tag: temp
    });
  },
  deleteLink: function(e) {
    console.log(e.target.dataset.name)
    var value = e.target.dataset.name;
    var that = this;
    var temp = that.data.link;
    for (var i = 0; i < temp.length; i++) {
      if (temp[i].title == value) {
        temp.splice(i, 1);
      }
    }
    that.setData({
      link: temp
    });
  },
  chooseIconImage: function(e) {
    var that = this
    wx.chooseImage({
      count: 1, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function(res) {
        wx.showLoading({
          title: '上传中',
        })
        wx.uploadFile({
          url: api.upload,
          filePath: res.tempFilePaths[0],
          name: 'file',
          success: function(res) {
            wx.hideLoading()
            var path = JSON.parse(res.data).data;
            that.setData({
              iconurl: path
            });
          }
        })
      }
    })
  },

  changinput: function(e) {
    var that = this;
    console.log(e.target.id)
    console.log(that.data.intro)
    console.log(e.detail.value)
    console.log(e)
    switch (e.target.id) {
      case 'title':
        {
          that.setData({
            title: e.detail.detail.value
          });
          break;
        }
      case 'intro':
        {
          that.setData({
            intro: e.detail.detail.value
          });
          break;
        }
      case 'content':
        {
          that.setData({
            content: e.detail.value
          });
          break;
        }
    }
  },


  add: function() {
    var that = this;
    wx.request({
      url: api.addarticle,
      data: {
        userid: that.data.userid,
        title: that.data.title,
        content: that.data.content,
        intro: that.data.intro,
        tags: that.data.tag,
        link: that.data.link,
        iconurl: that.data.iconurl,
      },
      success: function(res) {
        var id = res.data.data;
        // var temp = wx.getStorageSync("article");
        // if (temp == undefined || temp.length == 0) {
        //   var temp = [];
        // }
        // // temp.push(id);
        // wx.setStorageSync("article", temp)
        wx.showModal({
          title: '温馨提示',
          content: '提交成功',
          showCancel: false,
          success: function() {
            wx.navigateTo({
              url: '../read/read?id=' + id,
            })
          }
        })
      }
    })
  }
})