package dao.impl;

import dao.JedisDao;
import redis.clients.jedis.Jedis;
import utils.JedisUtils;

import java.util.Map;

/**
 * @author Tptogiar
 * @description 用于操作redis
 * @date 2021/8/17 - 12:34
 */
public class JedisDaoImpl implements JedisDao {
    @Override
    public long hset(String key, String field, String value) {
        Jedis jedis = JedisUtils.getJedis();
        return jedis.hset(key, field, value);
    }

    @Override
    public String hget(String key, String fieid) {
        Jedis jedis = JedisUtils.getJedis();
        return jedis.hget(key, fieid);
    }

    @Override
    public long hincrBy(String key, String field, long value) {
        Jedis jedis = JedisUtils.getJedis();
        return jedis.hincrBy(key, field, value);
    }

    @Override
    public Map<String, String> hgetAll(String key){
        Jedis jedis = JedisUtils.getJedis();
        return jedis.hgetAll(key);
    }




}
