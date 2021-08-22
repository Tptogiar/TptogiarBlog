package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import factory.BeanFactory;
import org.apache.commons.codec.digest.DigestUtils;
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
        String password=user.getPassword();
        String md5Psw=DigestUtils.md5Hex(password);
        user.setPassword(md5Psw);
        return userDao.queryForOne(user);
    }

    @Override
    public User regist(String username, String password, String email, Integer gender){
        User user = new User();
        user.setUsername(username);
        String md5Psw=DigestUtils.md5Hex(password);
        user.setPassword(md5Psw);
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


    @Override
    public User queryOne(User user){
        return userDao.queryForOne(user);
    }


    @Override
    public int updateOne(User user){
        String password = user.getPassword();
        if (password!=null && password!=""){
            user.setPassword(password);
            userDao.updatePassword(user);
        }
        return userDao.updateOne(user);
    }


    @Override
    public int updatePassword(User user){
        return userDao.updatePassword(user);
    }

}
