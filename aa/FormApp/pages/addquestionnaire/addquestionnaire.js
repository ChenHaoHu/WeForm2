const api = require('../../api.js');

Page({
  data: {
    current: 0,
    intro: {
      value: "",
      length: "0"
    },
    postimages: [
      'http://p17.qhimg.com/bdm/720_444_0/t01e207d09a4bc31c9f.jpg',
      'http://p18.qhimg.com/bdm/360_222_0/t011ef720af7c32ffb1.jpg'
    ],
    password: "",
    tags: ['运动', '教育'],
    ispublic: true,
    compstatue: [1, 1, 1],
    showselect: false,
    listitem: [{
      type: "输入框",
      text: "姓名",
      id: 1
    }],
    time: {
      start: ['开始日期', '开始时间'],
      end: ['结束时间', '结束时间']
    },
    detail: {
      title: "",
      userid: "",
      username: "",
      type: "",
      intro: "",
      mode: "",
      start: "点击设置时间",
      end: "点击设置时间",
      maxnum: "",
      iconurl: "",
      posterurl: [],
      ispublic: "",
      tags: '教育'
    }

  },

  onLoad(option) {
    var that = this
    wx.request({
      url: api.getAllTags,
      success: function(res) {
        console.log(res)
        var temp = [];

        for (var i = 0; i < res.data.data.length; i++) {
          temp.push(res.data.data[i].name)
        }
        that.setData({
          tags: temp
        });

      }
    })
  },
  edittitle: function(e) {
    var that = this;
    var type = e.target.id.split('&')[0];
    var id = e.target.id.split('&')[1];
    console.log(type + "   " + id)
    console.log(e.detail.value)
    var data = that.data.listitem;
    for (var i = 0; i < data.length; i++) {
      if (data[i].type == type && data[i].id == id) {
        console.log(type + "   " + id)
        data[i].text = e.detail.value;
        console.log(data)
      }
    }
    that.setData({
      listitem: data
    });
  },


  deleComp: function(e) {
    var that = this;
    var type = e.target.id.split('&')[0];
    var id = e.target.id.split('&')[1];
    // console.log(type)
    // console.log(id)
    var data = that.data.listitem;
    for (var i = 0; i < data.length; i++) {
      // console.log(data[i])
      if (data[i].type == type) {
        if (data[i].id == id) {
          console.log(type + "  " + id);
          data.splice(i, 1);
        }
      }
    }
    that.setData({
      listitem: data
    });
  },

  deleradioselect: function(e) {
    var that = this;
    var type = e.target.id.split('&')[0];
    var id = e.target.id.split('&')[1];
    console.log(type + " " + id);
    var data = that.data.listitem;
    for (var i = 0; i < data.length; i++) {
      if (data[i].type == '单选框') {
        if (data[i].id == type) {
          var temp = data[i].selects;
          for (var j = 0; j < temp.length; j++) {
            console.log(temp[j].id);
            if (temp[j].id == type + "&" + id) {
              console.log(j)
              temp.splice(j, 1);
            }
          }
        }
      }
    }
    that.setData({
      listitem: data
    });

  },


  delecheckedselect: function(e) {
    var that = this;
    var type = e.target.id.split('&')[0];
    var id = e.target.id.split('&')[1];
    console.log(type + " " + id);
    var data = that.data.listitem;
    for (var i = 0; i < data.length; i++) {
      if (data[i].type == '多选框') {
        if (data[i].id == type) {
          var temp = data[i].selects;
          for (var j = 0; j < temp.length; j++) {
            if (temp[j].id == type + "&" + id) {
              console.log(j)
              temp.splice(j, 1);
            }
          }
        }
      }
    }
    that.setData({
      listitem: data
    });

  },

  addradioselect: function(e) {
    var that = this;
    var id = e.target.id;
    console.log(" " + id);
    var data = that.data.listitem;
    for (var i = 0; i < data.length; i++) {
      if (data[i].type == '单选框' && data[i].id == id) {
        data[i].selects.push({
          id: id + "&" + data[i].statue + 1,
          text: " "
        });
        data[i].statue = data[i].statue + 1;
      }
    }
    that.setData({
      listitem: data
    });
  },

  addcheckedselect: function(e) {
    var that = this;
    var id = e.target.id;
    console.log(" " + id);
    var data = that.data.listitem;
    for (var i = 0; i < data.length; i++) {
      if (data[i].type == '多选框' && data[i].id == id) {
        data[i].selects.push({
          id: id + "&" + data[i].statue + 1,
          text: ""
        });
        data[i].statue = data[i].statue + 1;
      }
    }

    that.setData({
      listitem: data,
    });
  },

  editcheckedselect: function(e) {
    var that = this;
    var type = e.target.id.split('&')[0];
    var id = e.target.id.split('&')[1];
    console.log(type + " " + id);
    var data = that.data.listitem;
    for (var i = 0; i < data.length; i++) {
      if (data[i].type == '多选框') {
        if (data[i].id == type) {
          for (var j = 0; j < data[i].selects.length; j++) {
            if (data[i].selects[j].id == type + "&" + id) {
              data[i].selects[j].text = e.detail.value;
            }
          }
        }
      }
    }
    that.setData({
      listitem: data
    });
  },

  editradioselect: function(e) {
    var that = this;
    var type = e.target.id.split('&')[0];
    var id = e.target.id.split('&')[1];
    console.log(type + " " + id);
    var data = that.data.listitem;
    for (var i = 0; i < data.length; i++) {
      if (data[i].type == '单选框') {
        if (data[i].id == type) {
          for (var j = 0; j < data[i].selects.length; j++) {
            if (data[i].selects[j].id == type + "&" + id) {
              data[i].selects[j].text = e.detail.value;
            }
          }
        }
      }
    }
    that.setData({
      listitem: data
    });
  },

  addcomp: function(e) {
    console.log(e.currentTarget.id)
    var that = this;
    //根据传来的值进行判断添加什么类型
    switch (e.currentTarget.id) {
      case 'name':
        {
          var temp = that.data.listitem;
          var statue = that.data.compstatue;
          temp.push({
            type: "输入框",
            text: "姓名",
            id: statue[0] + 1
          });
          statue[0]++;
          that.setData({
            listitem: temp,
            compstatue: statue
          });
          break;
        }
      case 'number':
        {
          var temp = that.data.listitem;
          var statue = that.data.compstatue;
          temp.push({
            type: "输入框",
            text: "电话号",
            id: statue[0] + 1
          });
          statue[0]++;
          that.setData({
            listitem: temp,
            compstatue: statue
          });
          break;
        }
      case 'mail':
        {
          var temp = that.data.listitem;
          var statue = that.data.compstatue;
          temp.push({
            type: "输入框",
            text: "邮箱号",
            id: statue[0] + 1
          });
          statue[0]++;
          that.setData({
            listitem: temp,
            compstatue: statue
          });
          break;
        }
      case 'address':
        {
          var temp = that.data.listitem;
          var statue = that.data.compstatue;
          temp.push({
            type: "输入框",
            text: "地址",
            id: statue[0] + 1
          });
          statue[0]++;
          that.setData({
            listitem: temp,
            compstatue: statue
          });
          break;
        }
      case 'idcard':
        {
          var temp = that.data.listitem;
          var statue = that.data.compstatue;
          temp.push({
            type: "输入框",
            text: "证件号",
            id: statue[0] + 1
          });
          statue[0]++;
          that.setData({
            listitem: temp,
            compstatue: statue
          });
          break;
        }
      case 'wechat':
        {
          var temp = that.data.listitem;
          var statue = that.data.compstatue;
          temp.push({
            type: "输入框",
            text: "微信号",
            id: statue[0] + 1
          });
          statue[0]++;
          that.setData({
            listitem: temp,
            compstatue: statue
          });
          break;
        }
      case 'input':
        {
          var temp = that.data.listitem;
          var statue = that.data.compstatue;
          temp.push({
            type: "输入框",
            text: "添加标题",
            id: statue[0] + 1
          });
          statue[0]++;
          that.setData({
            listitem: temp,
            compstatue: statue
          });
          break;
        }
      case 'radio':
        {
          var temp = that.data.listitem;
          var statue = that.data.compstatue;
          temp.push({
            type: "单选框",
            text: "添加标题",
            selects: [{
              id: statue[1] + 1 + "&01",
              text: " "
            }],
            id: statue[1] + 1,
            statue: 1
          });
          statue[1]++;
          that.setData({
            listitem: temp,
            compstatue: statue
          });
          break;
        }
      case 'checked':
        {
          var temp = that.data.listitem;
          var statue = that.data.compstatue;
          temp.push({
            type: "多选框",
            text: "添加标题",
            selects: [{
              id: statue[2] + 1 + "&01",
              text: " "
            }],
            id: statue[2] + 1,
            statue: 1
          });
          statue[2]++;
          that.setData({
            listitem: temp,
            compstatue: statue
          });
          break;
        }
    }
    that.setData({
      showselect: false
    });
  },
  addcompbutton: function(e) {
    this.setData({
      showselect: true
    });
  },

  next: function() {
    this.setData({
      current: 1,
      showselect: false
    });
  },
  back: function() {
    this.setData({
      current: 0
    });
  },
  preview: function() {


    var userid = wx.getStorageSync("user").userid;
    if (userid == undefined) {
      wx.showModal({
        title: '温馨提示',
        content: '用户暂未登录,请进入首页登录',
        showCancel: false
      })
      return 0;
    }

    var that = this;
    var time = that.data.time;
    var detail = that.data.detail;
    detail.userid = userid;
    detail.mode = that.data.listitem;
    detail.type = "form";
    detail.ispublic = that.data.ispublic;
    detail.start = time.start[0] + " " + time.start[1];
    detail.end = time.end[0] + " " + time.end[1];
    detail.intro = that.data.intro.value;

    for (var tt in detail) {
      if (detail[tt] == 0) {
        wx.showModal({
          title: '温馨提示',
          content: '有字段为空',
          showCancel: false
        })
        return 0;
      }
    }

    console.log(detail)
    wx.showLoading({
      title: '创建中',
    })
    wx.request({
      url: api.addform,
      data: detail,
      success: function(res) {
        wx.hideLoading();
        var data = res.data;

        var form = wx.getStorageSync("form");
        if (form == undefined || form.length == 0) {
          form = [];
        }

        var newDate = new Date();
        var year = newDate.getFullYear();
        var month = (newDate.getMonth() + 1) < 10 ? "0" + (newDate.getMonth() + 1) : newDate.getMonth() + 1;
        var day = newDate.getDay() < 10 ? "0" + newDate.getDay() : newDate.getDay();
        var hours = newDate.getHours() < 10 ? "0" + newDate.getHours() : newDate.getHours();
        var minuts = newDate.getMinutes() < 10 ? "0" + newDate.getMinutes() : newDate.getMinutes();
        var seconds = newDate.getSeconds() < 10 ? "0" + newDate.getSeconds() : newDate.getSeconds();
        var time = year + "-" + month + "-" + day + " " + hours + ":" + minuts + ":" + seconds;

        form.push({
          title: detail.title,
          id: JSON.parse(data.data).formid,
          password: JSON.parse(data.data).password,
          iconurl: detail.iconurl,
          type: "build",
          time: time,
          intro: detail.intro
        });

        wx.setStorageSync("form", form);

        if (data.msg == "请求成功") {
          wx.showModal({
            title: '提示',
            showCancel: false,
            content: '创建成功,查找密匙:' +
              JSON.parse(data.data).password,
            success: function() {
              that.setData({
                current: 2,
                password: JSON.parse(data.data).password
              });
            }
          })
        }

      }
    })
  },
  changeispublicselect: function(e) {
    const detail = e.detail;
    this.setData({
      'ispublic': detail.value
    })
  },
  sendrepeat: function(e) {
    const detail = e.detail;
    this.setData({
      'sendrepeat': detail.value
    })
  },

  inputpassword: function(e) {
    var lenth = e.detail.value.length;
    var value = e.detail.value;
    var select = e.target.id;
    var password = this.data.password;
    var that = this;
    if (lenth == 1) {
      if (password.value.length < 5) {
        password.value.push(value)
        password.inputvalue = ""
        that.setData({
          password: password,
        });
      }
    }
  },

  changepassword: function(e) {
    console.log(e)
    var id = e.target.id;
    var value = e.detail.value;
    var that = this;
    var temp = that.data.password;
    if (value.length == 1) {
      temp.value[id] = value;
      that.setData({
        password: temp,
      });
    }
    if (value.length == 0) {
      temp.value[id] = '';
      that.setData({
        password: temp,
      });
    }
  },

  choosePosterImage: function(e) {
    var that = this
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function(res) {
        wx.showLoading({
          title: '上传中',
        })
        var tempFilePaths = res.tempFilePaths
        //上传图片
        wx.uploadFile({
          url: api.upload,
          filePath: tempFilePaths[0],
          name: 'file',
          success: function(res) {
            wx.hideLoading()
            var path = JSON.parse(res.data).data;
            var temp = that.data.detail;
            var tt = temp.posterurl;
            tt.push(path);
            that.setData({
              detail: temp
            });
          }
        })
      }
    })
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
            var temp = that.data.detail;
            temp.iconurl = path;
            that.setData({
              detail: temp
            });
          }
        })
      }
    })
  },

  uploaddoc: function(e) {
    wx.showModal({
      title: '提示',
      content: '成功复制链接',
    })
  },

  changetextare: function(e) {
    // console.log(e.detail.value.length)

    var temp = this.data.intro;
    temp.value = e.detail.value;
    temp.length = e.detail.value.length
    this.setData({
      intro: temp
    });
  },
  chageinput: function(e) {
    console.log(e.target.id)
    var that = this;
    var id = e.target.id;
    switch (id) {
      case 'title':
        {
          var temp = that.data.detail;
          temp.title = e.detail.value;
          that.setData({
            detail: temp
          });
          break;
        }
      case 'username':
        {
          var temp = that.data.detail;
          temp.username = e.detail.value;
          that.setData({
            detail: temp
          });
          break;
        }
      case 'title':
        {
          var temp = that.data.detail;
          temp.title = e.detail.value;
          that.setData({
            detail: temp
          });
          break;
        }
      case 'maxnum':
        {
          var temp = that.data.detail;
          temp.maxnum = e.detail.value;
          that.setData({
            detail: temp
          });
          break;
        }
      case 'tag':
        {
          var temp = that.data.detail;
          temp.tags = that.data.tags[e.detail.value];
          that.setData({
            detail: temp
          });
          break;
        }
      case 'start1':
        {
          var temp = that.data.time;
          temp.start[0] = e.detail.value;
          that.setData({
            time: temp
          });
          break;
        }
      case 'start2':
        {
          var temp = that.data.time;
          temp.start[1] = e.detail.value;
          that.setData({
            time: temp
          });
          break;
        }
      case 'end1':
        {
          var temp = that.data.time;
          temp.end[0] = e.detail.value;
          that.setData({
            time: temp
          });
          break;
        }
      case 'end2':
        {
          var temp = that.data.time;
          temp.end[1] = e.detail.value;
          that.setData({
            time: temp
          });
          break;
        }
    }
  },

  showposter: function() {
    wx.navigateTo({
      url: '../poster/poster?posterurl=' + this.data.detail.posterurl[0] + "&title=" +
        this.data.detail.title + "&password=" + this.data.password,
    })
  },

  onShareAppMessage: function() {

    return {
      title: '表单分享',
      desc: '填写表单即可报名',
      // imageUrl: this.data.posterurl[0],
      path: '/pages/formdetail/formdetail?password=' + this.data.password
    }
  },

  sign: function() {
    wx.navigateTo({
      url: '/pages/formdetail/formdetail?password=' + this.data.password
    })
  }


})