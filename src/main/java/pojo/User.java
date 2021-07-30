package pojo;

import annotation.DbField;

/**
 * @author Tptogiar
 * @descripiton 用户
 * @create 2021/07/22-11:29
 */


public class User {

    @DbField(fieldName = "id",isInsert = false,isUpdate = false,isQuery = true)
    private Integer id;
    @DbField(fieldName = "username",isInsert = true,isUpdate=true,isQuery = true)
    private String username="";
    @DbField(fieldName = "password",isInsert = true,isUpdate=true,isQuery = false)
    private String password="";
    @DbField(fieldName = "email",isInsert = true,isUpdate=true,isQuery = true)
    private String email="";
    @DbField(fieldName = "avatar_path",isInsert = true,isUpdate=true,isQuery = true)
    //TODO 添加默认图片
    private String avatarPath="image/avatar/default.png";
    @DbField(fieldName = "description",isInsert = true,isUpdate=true,isQuery = true)
    private String description="这家伙很懒，什么都没留下";
    @DbField(fieldName = "gender",isInsert = true,isUpdate=true,isQuery = true)
    //0 代表未知，1 代表男，2代表女
    private Integer gender=0;

    public User() {
    }

    public User(Integer id, String username, String password, String email, String avatarPath, String description, Integer gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatarPath = avatarPath;
        this.description = description;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", description='" + description + '\'' +
                ", gender=" + gender +
                '}';
    }
}
