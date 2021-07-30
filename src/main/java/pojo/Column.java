package pojo;

import java.util.Date;

/**
 * @author Tptogiar
 * @descripiton 文章专栏
 * @create 2021/07/22-11:36
 */


public class Column {

    private Integer id;
    private Integer userId;
    private Date createTime;
    private String description;
    private String title;

    public Column() {
    }

    public Column(Integer id, Integer userId, Date createTime, String description, String title) {
        this.id = id;
        this.userId = userId;
        this.createTime = createTime;
        this.description = description;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Column{" +
                "id=" + id +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
