package utils;

import dao.impl.JedisDaoImpl;
import factory.BeanFactory;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisUtilsTest {

    @Test
    public void getJedis() {
        JedisDaoImpl jedisDaoImpl = BeanFactory.getBeanIntance(JedisDaoImpl.class);
        if (jedisDaoImpl.hget("browser","24")==null){
            Integer browsedCount = 32;
            jedisDaoImpl.hset("browser","24",browsedCount.toString());
        }else{
            long incr = jedisDaoImpl.hincrBy("browser","24",1);
            System.out.println(incr);
        }
    }

    @Test
    public void close() {
        Jedis jedis = JedisUtils.getJedis();
        jedis.set("test","testtest");

        System.out.println(jedis.get("test"));
        JedisUtils.close(jedis);
    }
}