package pojo;

import java.util.Date;

/**
 * @author Tptogiar
 * @descripiton 评论
 * @create 2021/07/22-11:44
 */


public class Comment {

    private Integer id;
    private Date createTime;
    private String content;
    private Integer likeCount;
    private Integer essayId;
    private Integer userId;


    public Comment() {
    }

    public Comment(Integer id, Date createTime, String content, Integer likeCount, Integer essayId, Integer userId) {
        this.id = id;
        this.createTime = createTime;
        this.content = content;
        this.likeCount = likeCount;
        this.essayId = essayId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getEssayId() {
        return essayId;
    }

    public void setEssayId(Integer essayId) {
        this.essayId = essayId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                ", essayId=" + essayId +
                ", userId=" + userId +
                '}';
    }
}
