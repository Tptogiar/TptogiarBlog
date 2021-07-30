package pojo;

import java.util.List;

/**
 * @author Tptogiar
 * @descripiton 分页模型
 * @create 2021/07/30-23:45
 */


public class Page<T> {

    public static final Integer PAGE_SIZE=10;
    private Integer pageSize=PAGE_SIZE;
    private Integer curPage;
    private Integer totalPage;
    private Integer totalItems=0;
    private List<T> items;


    public Page() {
    }

    public Page(Integer pageSize, Integer curPage, Integer totalPage, Integer totalItems, List<T> tiems) {
        this.pageSize = pageSize;
        this.curPage = curPage;
        this.totalPage = totalPage;
        this.totalItems = totalItems;
        this.items = tiems;
    }


    public int initPage(int pageSize, int curPage, Integer totalItems){
        this.pageSize=pageSize;
        this.curPage=curPage;
        this.totalItems=totalItems;
        this.totalPage=totalItems/pageSize;
        if (totalItems%pageSize>0){
            this.totalPage+=1;
        }
        return (curPage-1)*pageSize;
    }







    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> tiems) {
        this.items = tiems;
    }
}
