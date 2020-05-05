package com.coins.queue;

// 消息生产者接口
public interface RedisService {
	void saveQueue (String queueKey ,String msgBody) ;
}
