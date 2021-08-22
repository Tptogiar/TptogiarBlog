package pojo;

import annotation.DbField;
import annotation.IndirectDbField;

import java.time.LocalDateTime;

/**
 * @author Tptogiar
 * @descripiton 文章
 * @create 2021/07/22-11:40
 */


public class Essay {

    @DbField(fieldName = "essay_id", isInsert = false, isUpdate = false,isQuery = true)
    private Integer essayId;
    @DbField(fieldName = "title", isInsert = true, isUpdate = true,isQuery = true)
    private String title;
    @DbField(fieldName = "summary",isInsert = true,isUpdate = true,isQuery = true)
    private String summary;
    @DbField(fieldName = "content", isInsert = true, isUpdate = true,isQuery = true)
    private String content;
    @DbField(fieldName = "author_id", isInsert = true, isUpdate = false,isQuery = true)
    private Integer authorId;
    @DbField(fieldName = "publish_time", isInsert = true, isUpdate = false,isQuery = true)
    private LocalDateTime publishTime;
    @DbField(fieldName = "last_time", isInsert = true, isUpdate = true,isQuery = true)
    private LocalDateTime lastTime;
    @DbField(fieldName = "browse_count", isInsert = true, isUpdate = true,isQuery = true)
    private Integer browsedCount =0;
    @DbField(fieldName = "like_count", isInsert = true, isUpdate = true,isQuery = true)
    private Integer likedCount =0;
    @DbField(fieldName = "column_id", isInsert = true, isUpdate = false,isQuery = true)
    private Integer columnId;
    @IndirectDbField(fieldName = "username")
    private String authorUsername;
    @IndirectDbField(fieldName = "avatar_path")
    private String authorAvatar;



    public Essay() {
    }

    public Essay(Integer essayId, String title, String summary, String content, Integer authorId, LocalDateTime publishTime, LocalDateTime lastTime, Integer browseCount, Integer likedCount, Integer columnId) {
        this.essayId = essayId;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.authorId = authorId;
        this.publishTime = publishTime;
        this.lastTime = lastTime;
        this.browsedCount = browseCount;
        this.likedCount = likedCount;
        this.columnId = columnId;
    }

    public Integer getEssayId() {
        return essayId;
    }

    public void setEssayId(Integer essayId) {
        this.essayId = essayId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getBrowsedCount() {
        return browsedCount;
    }

    public void setBrowsedCount(Integer browsedCount) {
        this.browsedCount = browsedCount;
    }

    public Integer getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(Integer likedCount) {
        this.likedCount = likedCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }


    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    @Override
    public String toString() {
        return "Essay{" +
                "id=" + essayId +
                ", title='" + title + '\'' +
                ", authorId=" + authorId +
                ", publishTime=" + publishTime +
                ", lastTime=" + lastTime +
                ", browseCount=" + browsedCount +
                ", likeCount=" + likedCount +
                ", content='" + content + '\'' +
                ", columnId=" + columnId +
                '}';
    }
}
