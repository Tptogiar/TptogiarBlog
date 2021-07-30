package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import factory.BeanFactory;
import pojo.User;
import service.UserService;

/**
 * @author Tptogiar
 * @descripiton
 * @create 2021/07/23-22:19
 */


public class UserServiceImpl implements UserService {

    UserDao userDao= BeanFactory.getBeanIntance(UserDaoImpl.class);

    @Override
    public User login(User user){
        user.setPassword(user.getPassword());
        return userDao.queryForOne(user);
    }

    @Override
    public User regist(String username, String password, String email, Integer gender){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setGender(gender);
        int insertId = userDao.insertOne(user);
        if (insertId!=0){
            user.setId(insertId);
            return user;
        }else {
            return null;
        }
    }

    /**
     * 查询单个用户的信息
     * @param user
     * @return
     */
    @Override
    public User queryOne(User user){
        return userDao.queryForOne(user);
    }

    @Override
    public int updateOne(User user){
        return userDao.updateOne(user);
    }


}
