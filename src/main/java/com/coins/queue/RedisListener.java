package com.coins.queue;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;


import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.coins.utils.SpringUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

// 监听队列
public class RedisListener implements InitializingBean {
	/**
     * Redis
     */
	@Autowired
    private JedisPool jedisPool;
    private List<RedisHandler> handlers = null;
    private ExecutorService product = null;
    private ExecutorService consumer = null;
    /**
     * 初始化配置
     */
    @Override
    public void afterPropertiesSet() {
        handlers = SpringUtil.getBeans(RedisHandler.class) ;
        product = new ThreadPoolExecutor(10,15,60 * 3,
                TimeUnit.SECONDS,new SynchronousQueue<>());
        consumer = new ThreadPoolExecutor(10,15,60 * 3,
                TimeUnit.SECONDS,new SynchronousQueue<>());
        for (RedisHandler redisHandler : handlers){
            product.execute(() -> {
                redisTask(redisHandler);
            });
        }
    }
    /**
     * 队列监听
     */
    public void redisTask (RedisHandler redisHandler){
        Jedis jedis = null ;
        while (true){
            try {
                jedis = jedisPool.getResource() ;
                List<String> msgBodyList = jedis.brpop(0, redisHandler.queueName());
                if (msgBodyList != null && msgBodyList.size()>0){
                    consumer.execute(() -> {
                        redisHandler.consume(msgBodyList.get(1)) ;
                    });
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (jedis != null) jedis.close();
            }
        }
    }
}
