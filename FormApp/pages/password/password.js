const api = require('../../api.js');
Page({


  data: {
    value: [],
    focus:true,
    str:""
  },

  input: function(e) {
    console.log(e)
    var that = this;
    var temp = [];
    var str = e.detail.value;
    for(var i = 0; i<str.length;i++ ){
      temp.push(str[i])
    }
    this.setData({
      value: temp
    });

  if(str.length > 4){
    that.setData({
      str:this.data.str.slice(0,4)
    });
  }


    if(str.length == 4){
     wx.request({
       url: api.checkform,
       data:{
         password:str.slice(0,4)
       },
       success:function(res){
         if(res.data.data == false){
           wx.showModal({
             title: '温馨提示',
             content: '您输入的密匙对应的表单不存在',
             showCancel:false
           })
         }else{
           wx.navigateTo({
             url: '/pages/formdetail/formdetail?password=' + str.slice(0, 4)
           })
         }

       }
     })
    }
  },
  getfoucus:function(e){
    this.setData({
      focus:true
    });
  }
})