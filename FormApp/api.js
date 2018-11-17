// var ip = "http://120.78.174.107:8090";
// var ip = "http://localhost:8080";
var ip = "https://www.hcyang.top";
var api = {
  login: ip + "/user/login",
  addform: ip + "/form/add",
  upload: ip + "/common/upload",
  findFormByPass: ip +"/form/find/password",
  join: ip +"/join/form",
  deletejoin: ip +"/join/delete",
  exportexcel: ip + "/join/export",
  getJoinUser: ip + "/join/user",
  getFormTitle: ip + "/form/title",
  getpassword: ip + "/join/password", 
  getAllTags: ip + "/tag/all",
  addarticle: ip +"/article/add",
  findarticle: ip + "/article/find",
  findallbytag: ip + "/tag/value",
  checkform: ip + "/form/check",
  onloaddata: ip + "/common/onload",
  getPasswordById: ip +"/form//password",
  getmybuild: ip + "/form/user/build",
  getmysign: ip + "/join/user/sign",
  getmyarticle: ip + "/user/article",
  zan: ip + "/article/zan",
  getArticleByPage: ip + "/article/page",
  getActivityByPage: ip + "/form/activity/page",
  getFormByPage: ip + "/form/form/page",

};


module.exports = api;