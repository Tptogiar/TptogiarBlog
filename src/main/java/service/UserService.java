package service;

import pojo.User;

public interface UserService {




    User login(User user);

    User regist(String username, String password, String email, Integer gender);

    User queryOne(User user);

    int updateOne(User user);
}
