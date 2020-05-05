package com.coins.queue;

// 队列接口，消费者
public interface RedisHandler {
	 /**
	 * 队列名称
	 */
    String queueName();

    /**
     * 队列消息内容
     */
    String consume (String msgBody);
}
