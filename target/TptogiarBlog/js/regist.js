$("#submit").click(function () {
    var username =$("#username").val();
    var password =$("#password").val();
    var passwordAgain =$("#passwordAgain").val();
    var email =$("#email").val();
    var code =$("#code").val();
    //检查用户名
    var usernamePatt=	/^[a-zA-Z0-9_]{5,12}$/;
    if (!usernamePatt.test(username)){
        alert("用户名长度必须在5~12之间,且只能包含字母，数字和下划线");
        return;
    }
    //检查密码
    if (password !==passwordAgain){
        alert("两次输入的密码不一致");
        return;
    }
    var passwordPatt=/^[a-zA-Z]\w{5,17}$/;
    if (!passwordPatt.test(password)){
        alert("密码以字母开头，长度在6~18之间，且只能包含字母、数字和下划线");
        return ;
    }

    //检查电子邮箱
    var emailPatt=/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
    if(!emailPatt.test(email)){
        alert("邮箱地址不合法");
        return ;
    }

    //检查验证码
    if(code==="" || code.length<=4){
        alert("验证码输入有误");
        return;
    }
    gender = $("input[name='gender']:checked").val();
    if (gender==="0"){
        if(!confirm("注册成功后性别将无法修改!")){
            return;
        }
    }
    sendData={
        username:username,
        password:password,
        email:email,
        gender:gender,
        code:code};
    $.ajax({
        type:"post",
        url:"/TptogiarBlog/visitor?action=regist",
        data:sendData,
        success:function (recData) {
            if(recData.resultCode==="200"){
                window.location="login.html";
            }else{
                $("#msg").text(recData.msg);
                updateCodeImg();
            }
        },
        dataType:"json"
    });
});

function updateCodeImg() {
    $("#codeImg").attr("src","/TptogiarBlog/code.jpg?"+new Date().getMilliseconds());
}

$("#codeImg").click(function () {updateCodeImg()});


$("#username").blur(function () {
    var curUsername=$(this).val();
    var usernamePatt=	/^[a-zA-Z0-9_]{5,12}$/;
    $usernameCheck=$("#usernameCheck");
    if (curUsername.length===0){
        $usernameCheck.hide();
        $("#msg").text("");
        return;
        return;
    }
    if (!usernamePatt.test(curUsername)){
        $usernameCheck.hide();
        $("#msg").text("用户名格式不正确")
        return;
    }
    $.ajax({
        type:"post",
        url:"/TptogiarBlog/visitor?action=checkUsername",
        data: {username:curUsername},
        success:function (recData) {
            if(recData.resultCode==="200"){
                $("#msg").text("");
                $usernameCheck.show();
            }else{
                $("#msg").text(recData.msg);
                $usernameCheck.hide();
            }
        },
        dataType:"json"
    });
});

$("#email").blur(function () {
    var curEmail=$(this).val();
    var emailPatt=/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
    var $emailCheck=$("#emailCheck");
    if (curEmail.length===0){
        $emailCheck.hide();
        $("#msg").text("")
        return ;
    }
    if(!emailPatt.test(curEmail)){
        $emailCheck.hide();
        $("#msg").text("邮箱格式不正确")
        return ;
    }
    $.ajax({
        type:"post",
        url:"/TptogiarBlog/visitor?action=checkEmail",
        data: {email:curEmail},
        success:function (recData) {
            if(recData.resultCode==="200"){
                $("#msg").text("");
                $emailCheck.show()
            }else{
                $("#msg").text(recData.msg);
                $emailCheck.hide();
            }
        },
        dataType:"json"
    });
});



$("#password").blur(function () {
    var curPassword=$(this).val();
    var passwordPatt=/^[a-zA-Z]\w{5,17}$/;
    var $passwordCheck=$("#passwordCheck")
    if (curPassword.length===0){
        $passwordCheck.hide();
        $("#msg").text("");
        return;
    }
    if(!passwordPatt.test(curPassword)){
        $passwordCheck.hide();
        $("#msg").text("密码格式不正确")
        return;
    }
    $("#msg").text("");
    $passwordCheck.show();
});

$("#passwordAgain").blur(function () {
    var curPassword=$("#password").val();
    var curPasswordAgain=$(this).val();
    var $passwordAgainCheck=$("#passwordAgainCheck");
    if (curPasswordAgain.length===0){
        $passwordAgainCheck.hide();
        $("#msg").text("");
        return;
    }
    if(curPasswordAgain!==curPassword){
        $passwordAgainCheck.hide();
        $("#msg").text("两次输入的密码不一致")
        return;
    }
    $passwordAgainCheck.show();
    $("#msg").text("");
});

$("#login").click(function () {
    window.location="login.html";
});