package com.coins.queue;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

// 消息消费者，实现后端api日志队列
@Component
public class CLogListen implements RedisHandler {
    @Resource
    private RedisLock redisLock;
    @Override
    public String queueName() {
        return "CLog-key";
    }
    @Override
    public String consume(String msgBody) {
        // 加锁，防止消息重复投递
        String lockKey = "lock-order-uuid-C";
        boolean lock = false;
        try {
            lock = redisLock.addLock(lockKey, 60);
            if (!lock) {
                return "success";
            }
            System.out.println("######## CLog-key");
            System.out.println(msgBody);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (lock) {
                redisLock.removeLock(lockKey);
            }
        }
        return "success";
    }
}
