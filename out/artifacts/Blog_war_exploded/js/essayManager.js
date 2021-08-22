

$.ajax({
    dataType:"json",
    url:"/TptogiarBlog/essay?action=essayManager",
    type:"post",
    data:{curPage:"1",
        pageSize:"10"},
    success:function (revr) {
        if (revr.resultCode==="200"){
            for (let i = 0; i < revr.essays.length; i++) {
                var clone=$(".essayProfileTemplate").clone(true);
                clone.css("display","block");
                initEssayProfileForManager(clone,revr,i);
                clone.show();
                $("#essayContainer .list-group").append(clone);
            }
        }else{

        }
    }
});

function initEssayProfileForManager(essayProfile, data, index) {
    essayProfile.attr("class","list-group-item");
    var essayTitle=essayProfile.find("h2:nth-of-type(1)");
    var publishTime=essayTitle.parent().next().find("span:nth-of-type(1)").find("span:nth-of-type(1)");
    var browseCount=publishTime.next();
    var comment=browseCount.next();
    var likedCount=comment.next();
    var editBtn=essayProfile.find("svg:nth-of-type(1)");
    var removeBtn=essayProfile.find("svg:nth-of-type(2)");

    essayTitle.text(data.essays[index].title);
    publishTime.text(parseTime(data.essays[index].publishTime));
    browseCount.text(data.essays[index].browsedCount+"浏览");
    comment.text("23评论");
    likedCount.text(data.essays[index].likedCount+"点赞");

    editBtn.click(function () {
        window.location="/TptogiarBlog/pages/edit.html?essayId="+data.essays[index].essayId;
    });

    removeBtn.click(function () {
        var isComfirm=confirm("确定删除文章    ⌊ "+data.essays[index].title+"  ⌉    ?");
        if (isComfirm){
            $.ajax({
                url:"/TptogiarBlog/essay?action=removeEssay",
                type:"post",
                dataType: "json",
                data:{essayId:data.essays[index].essayId},
                success:function (revr) {
                    if (revr.resultCode==="200"){
                        $("#modalContent").text("文章删除成功");
                    }else{
                        $("#modalContent").text("文章删除失败");
                    }
                    $('#msgModal').modal('show');
                }
            })
        }

    });
}

function parseTime(initTime){
    var resultTime=initTime.date.year;
    resultTime+="-"+initTime.date.month;
    resultTime+="-"+initTime.date.day;
    resultTime+=" "+initTime.time.hour;
    resultTime+=":"+initTime.time.minute;
    resultTime+=":"+initTime.time.second;
    return resultTime;
}


$("#confirm").click(function () {
    window.location.reload();
});