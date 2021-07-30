package pojo;

import java.util.Date;

/**
 * @author Tptogiar
 * @descripiton 回复
 * @create 2021/07/22-11:46
 */


public class Reply {

    private Integer id;
    private Date createTime;
    private String content;
    private Integer likeCount;
    private Integer userId;
    private Integer commentId;


    public Reply() {
    }

    public Reply(Integer id, Date createTime, String content, Integer likeCount, Integer userId, Integer commentId) {
        this.id = id;
        this.createTime = createTime;
        this.content = content;
        this.likeCount = likeCount;
        this.userId = userId;
        this.commentId = commentId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                ", userId=" + userId +
                ", commentId=" + commentId +
                '}';
    }
}
