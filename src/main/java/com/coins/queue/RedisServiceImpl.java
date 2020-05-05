package com.coins.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

// 实现消息生产者服务
@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
    private JedisPool jedisPool;

    @Override
    public void saveQueue(String queueKey, String msgBody) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.lpush(queueKey,msgBody) ;
        } catch (Exception e){
          e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
    }
}