function initPagination(totalPage,curPage) {
    var $pagination=$(".pagination");
    if (totalPage>=2){
        $pagination.show();
    }
    for (let i = 1; i <= totalPage; i++) {
        var $li=$("<li><span >"+i+"</span></li>");
        $li.attr("class","pages");
        if (i==curPage){
            $li.attr("class","active");
        }
        $li.click(function () {
            init()
            getEssayProfiles(i)
        });
        $li.appendTo($pagination);
    }

}

function init() {
    $(".pagination").find(".pages").remove();
    $(".pagination").find(".active").remove();
}