var essayId=getUrlParam("essayId");
var $essayTitle=$("#essayTitle");
var $essayContent=$("#essayContent");
var $essaySummary=$("#essaySummary");


function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

$.ajax({
    type:"post",
    dataType: "json",
    data:{essayId:essayId},
    url: "/TptogiarBlog/visitor?action=wholeEssay",
    success:function (revr) {
        if (revr.resultCode==="200"){
            initEssay(revr.essay)
        }else{
            $("#modalContent").text("获取文章失败");
            $('#msgModal').modal('show');
        }
    }
});


function initEssay(essay) {
    $essayTitle.text(essay.title);
    $essaySummary.text(essay.summary);
    $essayContent.text(essay.content);
}