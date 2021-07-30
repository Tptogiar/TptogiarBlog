var user =  $.cookie("username");
var userId=$.cookie("userId");
$("#username").text(user);

//获取用户信息
$.ajax({
    type:"post",
    dataType: "json",
    data:{userId:userId},
    url: "/TptogiarBlog/user?action=userInfo",
    success:function (revr) {
        if (revr.resultCode==="200"){
            initUserInfo(revr.user)
        }else{
            $('#msgModal').modal('show');
        }
    }
});

//获取给用户发表过的文章
// $.ajax({
//     type:"post",
//     dataType:"json",
//     data:{authorId:userId},
//     url:"/TptogiarBlog/essay?aciton=getEssayByAuthorId",
//     success:function (revr) {
//         if (revr.resultCode==="200"){
//
//         }else{
//             $('#msgModal').modal('show');
//         }
//     }
// });
var $username=$("#usernameDisplay");
var $email=$("#emailDisplay");
var $genderAmbiguous=$("#gender_ambiguous");
var $description=$("#descriptionDisplay");
var $passwordCorrect=$("#passwordCorrect");
var $passwordCorrectAgain=$("#passwordCorrectAgain");
var $passwordCorrectAgainItem=$("#passwordCorrectAgainItem");
var $cancelSubmit=$("#cancelSubmit");

function initUserInfo(user) {
    $username.val(user.username);
    if (user.gender===1){
        $genderAmbiguous.hide();
        $("#gender_male").show();
    }else if (user.gender===2){
        $genderAmbiguous.hide();
        $("#gender_female").show();
    }
    $email.val(user.email);
    $description.val(user.description);
}



$("#correct").click(function () {
    $(this).hide();
    $("#submit").show();
    $cancelSubmit.show();
    $description.removeAttr("readonly");
    $passwordCorrect.removeAttr("readonly");
    $passwordCorrect.val("");
    $passwordCorrectAgain.val("");
    $passwordCorrectAgain.removeAttr("readonly");
    $passwordCorrectAgainItem.show();
});

$cancelSubmit.click(function () {
    $(this).hide();
    $("#submit").hide();
    $passwordCorrect.val("******");
    $("#correct").show();
    $description.attr("readOnly","true");
    $passwordCorrect.attr("readOnly","true");
    $passwordCorrectAgainItem.hide();
});


//修改个人信息
$("#submit").click(function () {
    var password=$passwordCorrect.val();
    var passwordAgain=$passwordCorrectAgain.val();
    if (password!=passwordAgain){
        $("#modalContent").text("两次输入的密码不一致");
        $('#msgModal').modal('show');
        return;
    }
    if (password.length===0){
        $("#modalContent").text("密码不能为空");
        $('#msgModal').modal('show');
        return;
    }
    var passwordPatt=/^[a-zA-Z]\w{5,17}$/;
    if (!passwordPatt.test(password)){
        $("#modalContent").text("密码以字母开头，长度在6~18之间，且只能包含字母、数字和下划线");
        $('#msgModal').modal('show');
        return ;
    }
    var curDescription=$description.val();
    if (curDescription.length>=150){
        $("#modalContent").text("个人简介长度不能超过150");
        $('#msgModal').modal('show');
        return;
    }
    $.ajax({
        url:"/TptogiarBlog/user?action=correctInfo",
        type:"post",
        data:{userId:userId,
            password:password,
            description:curDescription},
        dataType:"json",
        success:function (revr) {
            if (revr.resultCode==="200"){
                window.location="/TptogiarBlog/pages/userProfile.html";
                $(this).hide();
                $("#correct").show();
                $description.attr("readOnly","true");
                $passwordCorrect.attr("readOnly","true");
                $passwordCorrectAgainItem.hide();
            }else{
                $("#modalContent").text(revr.msg);
                $('#msgModal').modal('show');
            }
        }
    })
});



$("#uploadAvatar").click(function () {

});