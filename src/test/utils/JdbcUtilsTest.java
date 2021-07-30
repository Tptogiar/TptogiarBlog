package utils;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class JdbcUtilsTest {

    @Test
    public void getConnect() {
        Connection connection = JdbcUtils.getConnect();
        System.out.println(connection);
    }

    @Test
    public void close() {
        Connection connection = JdbcUtils.getConnect();
        System.out.println(connection);
        JdbcUtils.close(connection);
        System.out.println(connection);
    }
}