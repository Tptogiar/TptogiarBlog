//判断是否为登录状态
var user =  $.cookie("username");
if(user!=null){
   $("#loginOrRegistItem").remove();
   $("#usernameItem").show();
}else{
   $("#loginOrRegistItem").show();
}
$("#username").text(user);


//给侧边的选项栏添加点击事件
$("#login").click(function () {
   window.location="login.html";
});

$("#regist").click(function () {
   window.location="regist.html";
});

$("#logout").click(function () {
   $.removeCookie('userId');
   $.removeCookie("username");
   window.location="/TptogiarBlog";
});

$("#usernameItem").click(function () {
   $("#userOptionItem").fadeToggle();
   return false;
});

$("#issue").click(function () {
   window.location="/TptogiarBlog/pages/edit.html?"+new Date().getSeconds();
});

$("#profile").click(function () {
   window.location="/TptogiarBlog/pages/userProfile.html";

});


//给文章简介模板内的各个按钮添加点击事件
$("#like").click(function () {
   var parent=$(this).parent().parent();
   var likedCount =parseInt(parent.find("#likedCount").text());
   parent.find("#likedCount").text(likedCount+1);
   parent.find("#like").hide();
   parent.find("#liked").show();
   parent.find("#dislike").show();
   parent.find("#disliked").hide();
   return false;
});

$("#liked").click(function () {
   var parent=$(this).parent().parent();
   $(this).hide();
   parent.find("#like").show();
   return false;
});

$("#dislike").click(function () {
   var parent=$(this).parent().parent();
   var likedCount =parseInt(parent.find("#likedCount").text());
   parent.find("#likedCount").text(likedCount-1);
   $(this).hide();
   parent.find("#disliked").show();
   parent.find("#like").show();
   parent.find("#liked").hide();
   return false;
});

$("#disliked").click(function () {
   var parent=$(this).parent().parent();
   $(this).hide();
   parent.find("#dislike").show();
   return false;
});


$("#comment").click(function () {
   $('#commentModal').modal('show');
   return false;
});

$("#btn_reset").click(function () {
   getEssayProfiles();
});

//初始化文章简介模板
function initEssayProfile(essayProfile,data,index) {
   var userAvatar=essayProfile.find("img:nth-of-type(1)");
   var essayImg=essayProfile.find("img:nth-of-type(2)");
   var essayAuthor=userAvatar.parent().next();
   var authorId=userAvatar.next();
   var essayTitle=essayProfile.find("h4:nth-of-type(1)");
   var essaySummary=essayTitle.next();
   var likedCount=essayProfile.find("svg:nth-of-type(2)").next();
   var commentCount=likedCount.parent().next().next().next().find("svg:nth-of-type(1)").next();
   var publishTime=likedCount.parent().parent().next().find("span:nth-of-type(1)");
   var browsedCount=likedCount.parent().parent().next().find("span:nth-of-type(2)");

   userAvatar.attr("src","img.png");
   essayImg.attr("src","img.png");
   essayAuthor.text(data.essays[index].authorUsername);
   authorId.text(data.essays[index].authorId);
   essayTitle.text(data.essays[index].title);
   essaySummary.text(data.essays[index].summary);
   likedCount.text(data.essays[index].likedCount);
   commentCount.text(data.essays[index].browsedCount+"评论");
   publishTime.text(parseTime(data.essays[index].publishTime));
   browsedCount.text(data.essays[index].browsedCount+"浏览");
}


//向服务器请求文章简介
function getEssayProfiles() {
   var $errorMsg=$("#errorMsg");
   $.ajax({
      type:"post",
      url:"/TptogiarBlog/visitor?action=queryEssayProfiles",
      dataType:"json",
      data:{curPage:"1",
         pageSize:"10"
            },
      success:function (revr) {
         if (revr.resultCode==="200"){
            for (let i = 0; i < revr.essays.length; i++) {
               var clone=$("#essayProfileTemplate").clone(true);
               initEssayProfile(clone,revr,i);
               initPagintion(revr.totalPage);
               clone.show();
               $errorMsg.hide();
               $("#essayContainer").append(clone);
            }
         }else{
            $errorMsg.show();
            $errorMsg.find("#text").text(revr.msg);
         }
      }
   });
}

getEssayProfiles();


function parseTime(initTime){
   var resultTime=initTime.date.year;
   resultTime+="-"+initTime.date.month;
   resultTime+="-"+initTime.date.day;
   resultTime+=" "+initTime.time.hour;
   resultTime+=":"+initTime.time.minute;
   resultTime+=":"+initTime.time.second;
   return resultTime;
}

function initPagintion(totalPage) {

}
