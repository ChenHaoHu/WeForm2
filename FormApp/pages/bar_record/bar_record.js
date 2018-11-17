const api = require('../../api.js');

Page({
  data: {
    current: 'tab1',
    sign: [],
    build: [],
    height: "1000"
  },


  onLoad: function() {},

  onShow() {

    this.loadData();
  },


  loadData: function() {
    var that = this;
    var infor = wx.getSystemInfoSync();
    console.log(infor.windowHeight)
    that.setData({
      height: infor.windowHeight
    });
    // var form = wx.getStorageSync("form");
    // var sign = [];
    // var build = [];
    // console.log(form)
    // console.log(form.size)
    // for (var i = 0; i < form.length; i++) {
    //   console.log(form[i].type)
    //   if (form[i].type == "build") {
    //     build.push(form[i]);
    //     console.log(build)
    //   }
    //   if (form[i].type == "sign") {
    //     sign.push(form[i]);
    //     console.log(sign)
    //   }
    // } 
    var user = wx.getStorageSync("user");
    var sign = [];
    var build = [];

    if (user == undefined || user.length == 0) {
      wx.showModal({
        title: '温馨提示',
        content: '您还未登录,暂无记录',
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
            that.setData({
              sign: sign.reverse(),
              build: build.reverse(),
            });
          }
        })
      }
    })
  },

  handleChange({
    detail
  }) {
    this.setData({
      current: detail.key
    });
  },

  handleChangeScroll({
    detail
  }) {
    this.setData({
      current_scroll: detail.key
    });
  },

  showjoin: function(e) {
    var that = this;
    console.log(e.currentTarget.dataset.id)
    var joinid = e.currentTarget.dataset.id;
    wx.showActionSheet({
      itemList: ['再次报名', '重新填写', '取消报名'],
      success: function(res) {
        console.log(res.tapIndex)
        var index = res.tapIndex
        if (index == 0) {
          wx.request({
            url: api.getpassword,
            data: {
              id: joinid
            },
            success: function(res) {
              var password = res.data.data;
              wx.navigateTo({
                url: '/pages/formdetail/formdetail?password=' + password
              })
            }
          })
        }
        if (index == 1) {
          /**删除本次的报名，重新跳转报名页 */
          wx.request({
            url: api.deletejoin,
            data: {
              id: joinid
            },
            success: function(res) {
              var password = res.data.data;
              /*****此处进行删除缓存操作 */
              wx.showModal({
                title: '温馨提示',
                content: "您之前报名的记录已经删除，请重新报名",
                showCancel: false,
                success: function() {
                  wx.navigateTo({
                    url: '/pages/formdetail/formdetail?password=' + password
                  })
                }
              })

            }
          })
        }
        if (index == 2) {
          wx.request({
            url: api.deletejoin,
            data: {
              id: joinid
            },
            success: function(res) {
              var password = res.data.data;
              /*****此处进行删除缓存操作 */
              wx.showModal({
                title: '温馨提示',
                content: "您之前报名的记录已经删除",
                showCancel: false,
                success: function() {
                  that.onShow();
                }
              })

            }
          })
        }
      }
    })
  },
  showbuild: function(e) {
    console.log(e.currentTarget.dataset.id)
    var formid = e.currentTarget.dataset.id;
    wx.showActionSheet({
      itemList: ['查看内容', '修改内容', '查看报名情况', '导出报名表'],
      success: function(res) {
        var index = res.tapIndex;
        if (index == 0) {
          wx.request({
            url: api.getPasswordById,
            data: {
              formid: formid
            },
            success: function(res) {
              var password = res.data.data;
              wx.navigateTo({
                url: '/pages/formdetail/formdetail?password=' + password
              })
            }
          })
        }
        if (index == 1) {
          wx.showModal({
            title: '温馨提示',
            content: '暂不支持修改',
            showCancel: false
          })
        }
        if (index == 2) {
          wx.navigateTo({
            url: '/pages/joinlist/joinlist?formid=' + formid,
          })

        }
        if (index == 3) {

          wx.request({
            url: api.exportexcel,
            data: {
              formid: formid
            },
            success: function(res) {
              console.log(res)
              wx.showLoading({
                title: '加载中',
              })
              wx.downloadFile({
                url: res.data.data,
                success: function(res) {
                  const filePath = res.tempFilePath
                  wx.openDocument({
                    filePath: filePath,
                    success: function(res) {
                      wx.hideLoading()
                      console.log('打开文档成功')
                    }
                  })
                }
              })

            }
          })
        }
      }
    });
  },
  onPullDownRefresh() {
    wx.setNavigationBarTitle({
      title: '加载中',
    })
    wx.showNavigationBarLoading();
    this.loadData();
    wx.hideNavigationBarLoading();

  }
});