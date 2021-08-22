package service;

import pojo.User;

public interface UserService {




    User login(User user);

    User regist(String username, String password, String email, Integer gender);

    /**
     * 查询单个用户的信息
     * @param user
     * @return
     */
    User queryOne(User user);

    int updateOne(User user);

    /**
     * 更新密码
     * @param user
     * @return
     */
    int updatePassword(User user);
}
