package dao.impl;

import dao.UserDao;
import org.junit.Test;
import pojo.Essay;
import pojo.User;

import java.sql.SQLException;

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


    @Test
    public void batch() {
        EssayDaoImpl essayDao=new EssayDaoImpl();
        String sql="update t_essay set  browse_count = ?  where essay_id = ?";
        Object[] values=new Object[]{"11","111","1111"};
        Object[] ids=new Object[]{"18","19","20"};

        try {
            essayDao.batch(sql,values,ids);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}