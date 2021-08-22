package service.impl;

import dao.impl.JedisDaoImpl;
import factory.BeanFactory;
import redis.clients.jedis.Jedis;
import service.EssayService;
import service.JedisService;
import utils.JedisUtils;

import java.sql.SQLException;
import java.util.Map;

/**
 * @author Tptogiar
 * @description
 * @date 2021/8/17 - 14:06
 */
public class JedisServiceImpl implements JedisService {

    private JedisDaoImpl jedisDao = BeanFactory.getBeanIntance(JedisDaoImpl.class);
    private static EssayServiceImpl essayService = BeanFactory.getBeanIntance(EssayServiceImpl.class);
    private static Integer curUpdateCount = 0;
    @Override
    public void countForUpdateToDb() {
        curUpdateCount++;
        if (curUpdateCount>=updateRate){
            curUpdateCount=0;
            updateToDb();
        }
    }

    @Override
    public void updateToDb() {
        Map<String, String> fieldValueMap = jedisDao.hgetAll(EssayServiceImpl.BROWSER_KEY);
        try {
            essayService.updateBrowserCount(fieldValueMap);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public long hset(String key, String field, String value) {
        countForUpdateToDb();
        return jedisDao.hset(key, field, value);
    }

    public String hget(String key, String fieid) {
        return jedisDao.hget(key, fieid);
    }

    public long hincrBy(String key, String field, long value) {
        countForUpdateToDb();
        return jedisDao.hincrBy(key, field, value);
    }


}

