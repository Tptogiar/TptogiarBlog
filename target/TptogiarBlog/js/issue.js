var $title=$("#title")
var $content=$("#content");
var $summary=$("#summary");



$("#issue").click(function () {
    var title=$title.val();
    var content=$content.val();
    var summary=$summary.val();


    if (title.length<2 || title.length>50){
        alert("标题长度必须大于等于2,且不能大于30");
        return false;
    }
    if (summary.length>150){
        alert("摘要长度不能大于150");
        return false;
    }
    if (content.length===""){
        alert("当前文章内容为空");
        return false;
    }
    if (content.length>50000){
        alert("暂不支持长篇大论");
        return false;
    }
    sendData={title:title,
            content:content,
            summary:summary};
    $.ajax({
        type:"post",
        url:"/TptogiarBlog/essay?action=issue",
        data:sendData,
        success:function (recData) {
            if (recData.resultCode==="200"){
                $("#errorMsg").hide();
                $('#issueMsgModal').modal('show');
                $("#issueMsgText").text("文章    ⌊ "+title+"  ⌉    发布成功");
            }else{
                $("#errorMsg").text("文章发布失败 "+recData.msg+" ");
                $("#errorMsg").show();
            }
        },
        dataType:"json"
    })
    return false;
});


$("#confirm").click(function () {
    window.location="/TptogiarBlog";
});

$("#cancle").click(function () {
    window.location="/TptogiarBlog";
});