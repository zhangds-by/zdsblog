package com.zhangds.zdsblog.model.constants;

/**
 * RabbitMQ常量池
 * Create by zhangds
 * 2020-02-28 17:50
 **/
public interface RabbitConstants {

    /**
     * 直接交换器模式
     */
    String DIRECT_MODE_QUEUE = "direct.mode";

    /**
     * 分列交换器模式
     */
    String FANOUT_MODE_QUEUE = "fanout.mode";

    /**
     * 主题交换器模式
     */
    String TOPIC_MODE_QUEUE = "topic.mode";

    /**
     * 延迟队列交换器
     */
    String DELAY_MODE_QUEUE = "delay.mode";


    /**
     * 匹配路由方式一
     */
    String TOPIC_ROUTING_KEY_ONE = "queue.#";

    /**
     * 匹配路由方式二
     */
    String TOPIC_ROUTING_KEY_TWO = "*.queue";

    /**
     * 延迟队列
     */
    String DELAY_QUEUE = "delay.queue";

    /**
     * 主题队列
     */
    String TOPIC_QUEUE = "queue.topic";


}
