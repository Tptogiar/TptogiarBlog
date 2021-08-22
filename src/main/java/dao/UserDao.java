package dao;

import pojo.User;

/**
 * @author Tptogiar
 * @descripiton
 * @create 2021/07/22-14:54
 */


public interface UserDao extends BaseDao<User>{


    /**
     * 更新密码
     * @param user
     * @return
     */
    int updatePassword(User user);
}
