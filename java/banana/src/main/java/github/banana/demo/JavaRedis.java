package github.banana.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis使用
 * 
 * @see https://github.com/xetorthio/jedis
 * 
 * @author zhgxun
 *
 */
public class JavaRedis {

    public static void main(String[] args) {
        get("张三");
        getPool("李四");
    }

    /**
     * 单实例方式访问redis
     * 
     * @param name
     */
    private static void get(String name) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("name", name);
        System.out.println(jedis.get("name"));
        jedis.close();
    }

    /**
     * 连接池的方式访问redis
     * 
     * @param name
     */
    private static void getPool(String name) {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(10);
        // 设置最大空闲数
        config.setMaxIdle(5);

        // 配置连接池
        JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
        Jedis jedis = pool.getResource();
        jedis.set("name", name);
        System.out.println(jedis.get("name"));
        jedis.close();
        pool.close();
    }

}
