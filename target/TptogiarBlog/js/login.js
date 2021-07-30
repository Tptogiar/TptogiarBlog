$("#login").click(function () {
    let emailOrUsername = $("#emailOrUsername").val();
    let password=$("#password").val();
    let code= $("#code").val()
    var data;
    if (emailOrUsername.toString().search("@")===-1){
        //    为用户名登录 ,检查用户名是否合法
        var usernamePatt=/^\w{5,12}$/;
        if (!usernamePatt.test(emailOrUsername)){
            alert("用户名长度必须在5~12之间");
            return;
        }
    }else{
        //    为邮箱登录，检查邮箱
        var emailPatt=/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
        if(!emailPatt.test(emailOrUsername)){
            alert("邮箱地址不合法");
            return ;
        }
    }
    //检查用户密码
    var passwordPatt=/^\w{5,12}$/;
    if (!passwordPatt.test(password)){
        alert("密码长度必须在5~12之间");
        return ;
    }
    if(code==="" || code.length<=4){
        alert("验证码输入有误");
        return ;
    }
    sendData={emailOrUsername:emailOrUsername,
        password:password,
        code:code};
    $.ajax({
        type:"POST",
        url:"/TptogiarBlog/visitor?action=login",
        data:sendData,
        success:function (revr) {
            if(revr.resultCode==="200"){
                window.location="/TptogiarBlog";
            }else{
                $("#msg").text(revr.msg);
                updateCodeImg();
            }
        },
        dataType:"json"
    });

});

function updateCodeImg() {
    $("#codeImg").attr("src","/TptogiarBlog/code.jpg?"+new Date().getMilliseconds());
};

$("#codeImg").click(function () {updateCodeImg()});


$("#regist").click(function () {
    window.location="regist.html";
});

