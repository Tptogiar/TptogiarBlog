package dao;

import redis.clients.jedis.Jedis;
import utils.JedisUtils;

import java.util.Map;

public interface JedisDao {

    /**
     *  向set集合中添加键值对
     * @param key
     * @param fieid
     * @param value
     * @return
     */
    long hset(String key, String fieid, String value);


    /**
     * 查询set集合中某个键值对
     * @param key
     * @param fieid
     * @return
     */
    String hget(String key, String fieid);


    /**
     * 对指定的key自增1
     * @param key
     * @return
     */
    long hincrBy(String key,String field,long value);

    /**
     * 获取集合中所有的键值对
     * @param key
     * @return
     */
    Map<String, String> hgetAll(String key);
}
