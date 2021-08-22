//对不同页面共用的js代码进行提取


var $usernameItem=$("#usernameItem")


//判断是否为登录状态
var user =  $.cookie("username");
if(user!=null){
    $("#loginOrRegistItem").remove();
    $usernameItem.show();
}else{
    $("#loginOrRegistItem").show();
}
$("#username").text(user);



//左边选项
$("#logout").click(function () {
    $.removeCookie('userId',{ path: '/TptogiarBlog' });
    $.removeCookie("username",{ path: '/TptogiarBlog' });
    window.location="/TptogiarBlog";
});

$usernameItem.click(function () {
    $("#userOptionItem").fadeToggle();
    return false;
});

$("#profile").click(function () {
    window.location="/TptogiarBlog/pages/userProfile.html";
});

$("#issue").click(function () {
    window.location="/TptogiarBlog/pages/issue.html?"+new Date().getSeconds();
});

$("#essayManager").click(function () {
    window.location="/TptogiarBlog/pages/essayManager.html?"+new Date().getMilliseconds();
});




var userId=$.cookie("userId");
//获取用户信息
$.ajax({
    type:"post",
    dataType: "json",
    data:{userId:userId},
    url: "/TptogiarBlog/user?action=userInfo",
    success:function (revr) {
        if (revr.resultCode==="200"){
            initPagesCommon(revr.user)
        }else{
            $("#modalContent").text("获取用户信息失败");
            $('#msgModal').modal('show');
        }
    }
});

function initPagesCommon(user) {
    $("#userAvatarLogo").attr("src","/TptogiarBlog/"+user.avatarPath);
}