package dao.impl;

import dao.UserDao;
import org.junit.Test;
import pojo.User;

import static org.junit.Assert.*;

public class BaseDaoImplTest {

    UserDao userDao= new UserDaoImpl();

    @Test
    public void insertOne() {
        User user = new User(null, "username2", "password", "email2", "avatarPath", "description",  0);
        userDao.insertOne(user);
    }

    @Test
    public void deleteById() {
        userDao.deleteById(1);
    }

    @Test
    public void deleteOne() {
        User user = new User(null, "username2", "password", "email2", "avatarPath", "description", 0);
        userDao.deleteOne(user);
    }

    @Test
    public void updateOne() {
        User user = new User(null, "username", "password", "email", "avatarPath", "description",  10);
        userDao.updateOne(user);
    }

    @Test
    public void queryForOne() {
        User user = new User(2,null,null,null,null,null,null);
        User user1 = userDao.queryForOne(user);
        System.out.println(user1);
        System.out.println(user.hashCode());
        System.out.println(user1.hashCode());
    }

    @Test
    public void queryForList() {
    }
}