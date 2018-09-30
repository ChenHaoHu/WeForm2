Page({

  data: {
    current: 1,
    intro: {
      value: "",
      length: "0"
    },
    postimages: [
      'http://p17.qhimg.com/bdm/720_444_0/t01e207d09a4bc31c9f.jpg',
      'http://p18.qhimg.com/bdm/360_222_0/t011ef720af7c32ffb1.jpg'
    ],
    password: {
      inputvalue: "",
      value: []
    },
    sendrepeat: true,
    groupselect: true,
    date: '点击设置时间',

    compstatue: [1, 1, 1],
    showselect: false,
    listitem: [{
      type: "输入框",
      text: "输入框",
      id: "1"
    },
    // {
    //   type: "单选框",
    //   text: "dasdsa",
    //   selects: [{
    //     id: "1&01",
    //     text: ""
    //   }],
    //   id: "1",
    //   statue: 1
    // },
    // {
    //   type: "多选框",
    //   text: "",
    //   selects: [{
    //     id: "1&01",
    //     text: ""
    //   }],
    //   id: "1",
    //   statue: 1
    // }
    ],

  },
  edittitle: function (e) {
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


  deleComp: function (e) {
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

  deleradioselect: function (e) {
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


  delecheckedselect: function (e) {
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

  addradioselect: function (e) {
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

  addcheckedselect: function (e) {
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

  editcheckedselect: function (e) {
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

  editradioselect: function (e) {
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

  addcomp: function (e) {
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
  addcompbutton: function (e) {
    this.setData({
      showselect: true
    });
  },

  next: function () {
    this.setData({
      current: 1,
      showselect: false
    });
  },
  back: function () {
    this.setData({
      current: 0
    });
  },
  preview: function () {
    this.setData({
      current: 2
    });
  },
  bindDateChange: function (e) {
    console.log(e.detail.value)
    this.setData({
      date: e.detail.value
    });
  },
  changegrounpselect: function (e) {
    const detail = e.detail;
    this.setData({
      'groupselect': detail.value
    })
  },
  sendrepeat: function (e) {
    const detail = e.detail;
    this.setData({
      'sendrepeat': detail.value
    })
  },

  inputpassword: function (e) {
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

  changepassword: function (e) {
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

  chooseImage: function (e) {
    var that = this
    wx.chooseImage({
      count: 5, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        var tempFilePaths = res.tempFilePaths
        var temp = that.data.postimages
        for (var i = 0; i < tempFilePaths.length; i++) {
          temp.push(tempFilePaths[i])
        }
        that.setData({
          postimages: temp
        });
      }
    })
  },
  uploaddoc: function (e) {
    wx.showModal({
      title: '提示',
      content: '成功复制链接',
    })
  },
  changetextare: function (e) {
    // console.log(e.detail.value.length)

    var temp = this.data.intro;
    temp.value = e.detail.value;
    temp.length = e.detail.value.length
    this.setData({
      intro: temp
    });
  }

})