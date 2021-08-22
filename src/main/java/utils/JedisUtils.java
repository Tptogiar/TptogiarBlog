package utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Tptogiar
 * @description
 * @date 2021/8/17 - 12:19
 */
public class JedisUtils {

    private static JedisPool jedisPool;
    // 更新频率，即当redis中的数据更新次数超过updateRate后才会向数据库写入一次
    public static Integer updateRate =null;

    /**
     * 初始化
     */
    static{
        Properties properties = new Properties();
        try {
            properties.load(JedisUtils.class.getClassLoader().getResourceAsStream("Jedis.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        String host = properties.getProperty("host");
        int port = Integer.parseInt(properties.getProperty("port"));
        jedisPool = new JedisPool(config,host,port);

        updateRate=Integer.parseInt(properties.getProperty("updateRate"));
    }


    /**
     * 获取redis连接
     * @return
     */
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    /**
     * 关闭redis连接
     * @param jedis
     */
    public static void close(final Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

}
