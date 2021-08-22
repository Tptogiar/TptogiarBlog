package dao.impl;

import dao.UserDao;
import pojo.User;

import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * @author Tptogiar
 * @descripiton
 * @create 2021/07/22-16:02
 */


public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {


    @Override
    public String getTableName() {
        return "t_user";
    }

    /**
     * 有id直接根据id查询
     * 没有则根据邮箱和密码查询   或是  根据用户名密码查询
     * 都没有就根据用户名查询  或者  根据邮箱查询
     * @param user
     * @param values
     * @return
     */
    @Override
    public String getQueryCondition(User user, ArrayList<Object> values) {
        StringBuilder sqlBuilder = new StringBuilder();
        if(null!=user.getId()){
            sqlBuilder.append(" id = ? ");
            values.add(user.getId());
        }else if (! "".equals(user.getEmail()) && ! "".equals(user.getPassword())){
            sqlBuilder.append(" email = ? and password = ? ");
            values.add(user.getEmail());
            values.add(user.getPassword());
        }else if (! "".equals(user.getUsername()) && ! "".equals(user.getPassword())){
            sqlBuilder.append(" username = ? and password = ? ");
            values.add(user.getUsername());
            values.add(user.getPassword());
        }else if (! "".equals(user.getEmail())){
            sqlBuilder.append(" email = ? ");
            values.add(user.getEmail());
        }else if (! "".equals(user.getUsername())){
            sqlBuilder.append(" username = ? ");
            values.add(user.getUsername());
        }
        return sqlBuilder.toString();
    }


    @Override
    public int updatePassword(User user) {
        String baseSql="update {0} set password = ?  where id = ?";
        String sql = MessageFormat.format(baseSql, getTableName());
        updateOne(user,sql,user.getPassword(),user.getId());
        return 0;
    }


}
