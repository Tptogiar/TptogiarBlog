var $title=$("#title")
var $content=$("#content");
var $summary=$("#summary");


var essayId=getUrlParam("essayId");
if(essayId!=null){
    $.ajax({
        type:"post",
        dataType: "json",
        data:{essayId:essayId},
        url: "/TptogiarBlog/essay?action=wholeEssay",
        success:function (revr) {
            if (revr.resultCode==="200"){
                initEssay(revr.essay)
            }else{
                $("#modalContent").text("获取文章失败");
                $('#msgModal').modal('show');
            }
        }
    });
};


function initEssay(essay){
    $title.val(essay.title);
    $summary.text(essay.summary);
    $content.text(essay.content);
}


function getUrlParam(name) {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    let r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    };
    return null;
}

$("#confirm").click(function () {
    window.location="/TptogiarBlog";
});


$("#edit").click(function () {
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
        summary:summary,
        essayId:essayId};
    $.ajax({
        type:"post",
        url:"/TptogiarBlog/essay?action=edit",
        data:sendData,
        success:function (recData) {
            if (recData.resultCode==="200"){
                $("#errorMsg").hide();
                $('#issueMsgModal').modal('show');
                $("#issueMsgText").text("文章    ⌊ "+title+"  ⌉    修改成功");
            }else{
                $("#errorMsg").text("文章修改失败 "+recData.msg+" ");
                $("#errorMsg").show();
            }
        },
        dataType:"json"
    })
    return false;
});

$("#cancle").click(function () {
    window.location="/TptogiarBlog";
});