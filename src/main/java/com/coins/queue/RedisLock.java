package com.coins.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;
// 锁功能，防止重复添加
@Component
public class RedisLock {
	private static String keyPrefix = "RedisLock:";
	@Autowired
    private JedisPool jedisPool;
    public boolean addLock(String key, long expire) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            /*
             * nxxx的值只能取NX或者XX，如果取NX，则只有当key不存在是才进行set，如果取XX，则只有当key已经存在时才进行set
             * expx的值只能取EX或者PX，代表数据过期时间的单位，EX代表秒，PX代表毫秒。
             */
            //jedis3.0之后才提供的这个对象
            SetParams setParams = new SetParams();
            setParams.ex((int) expire);//设置过期时间为2秒
            setParams.nx();
            String value = jedis.set(keyPrefix + key,"1",setParams);
            return value != null;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null) jedis.close();
        }
        return false;
    }
    public void removeLock(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(keyPrefix + key);
        } finally {
            if (jedis != null) jedis.close();
        }
    }
}
