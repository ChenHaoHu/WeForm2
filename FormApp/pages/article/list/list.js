const api = require('../../../api.js');
Page({
  data: {
    list: [],
    type: ""
  },

  onLoad: function(option) {
    console.log(option)
    var type = option.type;
    var that = this;
    if (type == "tag") {
      var data = [];
      wx.request({
        url: api.getAllTags,
        success: function(res) {
          var tags = res.data.data;
          for (var i = 0; i < tags.length; i++) {
            data.push({
              title: tags[i].name,
              id: tags[i].name
            });
          }
          that.setData({
            list: data,
            type: "tag"
          })
        }
      })
    } else {
      // var form = wx.getStorageSync("form");

      // if (form == undefined || form.length == 0) {
      //   wx.showModal({
      //     title: '温馨提示',
      //     content: '您还没有创建/报名任何项目',
      //     showCancel: false
      //   })
      //   wx.navigateBack({

      //   })
      // }
      var user = wx.getStorageSync("user");
      var sign = [];
      var build = [];

      if (user == undefined || user.length == 0) {
        wx.showModal({
          title: '温馨提示',
          content: '您还未登录,暂为信息',
          showCancel: false
        })
        return;
      }

      wx.request({
        url: api.getmybuild,
        data: {
          userid: user.userid
        },
        success: function(res) {
          build = res.data.data;
          console.log(build);
          wx.request({
            url: api.getmysign,
            data: {
              userid: user.userid
            },
            success: function(res) {
              sign = res.data.data;
              console.log(sign);
              var list = [];
              //处理build
              for (var i = 0; i < build.length; i++) {
                list.push({
                  title: build[i].title,
                  id: build[i].password,
                });
              }
              //处理报名
              var flag = 0;
              for (var i = 0; i < sign.length; i++) {

                for (var j = 0; j < list.length; j++) {
                  if (list[j].title == sign[i].form.title) {
                    flag = 1;
                  }
                }
                if (flag == 0) {
                  list.push({
                    title: sign[i].form.title,
                    id: sign[i].form.password,
                  });
                  flag = 0;
                }
              }
              that.setData({
                list: list,
                type: "link"
              });
            }
          })
        }
      })
    }
  },

  select: function(e) {
    console.log(e)
    var value = e.currentTarget.id;
    var type = this.data.type;
    if (type == "tag") {
      var temp = {
        tag: value,
        link: ""
      }
      wx.setStorageSync("data", temp)
      wx.navigateBack({})
    }
    if (type == "link") {
      var temp = {
        link: value,
        tag: ""
      }
      wx.setStorageSync("data", temp)
      wx.navigateBack({})
    }
  }

})