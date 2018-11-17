// pages/poster/poster.js
Page({
  data: {
    width: 100,
    height: 100,
    canvaspath: "",
    title:"",
    password:"",
    posterurl:""
  },

  onLoad: function(options) {
    console.log(options)
    var that = this;
    wx.showLoading({
      title: '生成中',
    })
    wx.downloadFile({
      url: options.posterurl,
      success: function (res) {
        console.log(res)
        const filePath = res.tempFilePath
        that.setData({
          title: options.title,
          posterurl: filePath,
          password: options.password,
        });
        wx.hideLoading();
        that.onSaveImg()
      }
    })
  },

  //先制作一个canvas标签，再保存成图片
  onSaveImg: function() {
    const ctx = wx.createCanvasContext('myCanvas');
    var that = this;
    var mobile = wx.getSystemInfoSync();
    console.log(mobile)
    var width = mobile.windowWidth;
    var height = mobile.windowHeight;
    var barHeight = mobile.statusBarHeight;
    var sp = 20;
    this.setData({
      width: width,
      height: height
    })
    width = width - sp;
    height = height - barHeight;

    var title = that.data.title;
    var password = that.data.password;



    ctx.rect(0, 0, width, height);
    ctx.setFillStyle("#fff");
    ctx.fill();
    ctx.stroke();
    ctx.setFillStyle("#111");
    ctx.setFontSize(26);
    ctx.fillText("标题:" + title, 80, height / 2);
    ctx.fillText("密匙:" + password, 80, height / 2 +40 );
    ctx.setFontSize(15);
    ctx.setFillStyle("red");
    ctx.fillText("扫码进入并输入密匙进行查看", 80, height / 2 + 60);
    ctx.setTextAlign("center");
    ctx.drawImage(that.data.posterurl, 0, 0, width, height / 2 -80);
    ctx.drawImage("../../img/poster/test1.png", width * 0.1, height - (width * 0.9 * 2) / 7 - 100, width * 0.9, (width * 0.9 * 2) / 7);

    ctx.draw();

  },

  save: function() {
    var that = this

    wx.canvasToTempFilePath({
      x: 0,
      y: 0,
      width: 646,
      height: 966,
      destWidth: 646,
      destHeight: 966,
      canvasId: 'myCanvas',
      success: function(res) {
        // self.saveImageToPhoto();
        that.setData({
          canvaspath: res.tempFilePath
        });

        if (that.data.canvaspath != "") {
          wx.saveImageToPhotosAlbum({
            filePath: that.data.canvaspath,
            success: function () {
              wx.showModal({
                title: '保存图片成功',
                content: '海报已经保存到相册，您可以手动分享到朋友圈！',
                showCancel: false
              });
            },
            fail: function (res) {
              if (res.errMsg == "saveImageToPhotosAlbum:fail cancel") {
                wx.showModal({
                  title: '保存图片失败',
                  content: '您已取消保存图片到相册！',
                  showCancel: false
                });
              } else {

              }
            }
          })
        }
      },
      fail: function(e) {
        wx.showModal({
          title: '温馨提示',
          content: e.errMsg,
          showCancel: false
        })
      }
    })
  }

})