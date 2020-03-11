package com.zhangds.zdsblog.config;

import com.google.common.collect.Maps;
import com.zhangds.zdsblog.common.model.constants.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Create by zhangds
 * 2020-02-28 17:11
 **/
@Slf4j
@Configuration
public class RabbitMQConfig {


    /**
     * rabbitmq控制台操作
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        //autoStartup必须设置true，否则Spring容器不会加载RabbitAdmin类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    /**
     * 注入队列
     */
    @Bean
    public Queue topicQueue() {
        return new Queue(RabbitConstants.TOPIC_QUEUE);
    }

    @Bean
    public Queue delayQueue() {
        return new Queue(RabbitConstants.DELAY_QUEUE, true);
    }

    /**
     * 主题模式
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(RabbitConstants.TOPIC_MODE_QUEUE , true , false);
    }


    /**
     * 主题模式绑定队列,按路由1规则
     * @return
     */
    @Bean
    public Binding topicBinding(Queue topicQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue).to(topicExchange).with(RabbitConstants.TOPIC_ROUTING_KEY_ONE);
    }

    /**
     * 分列模式绑定队列
     * @return
     */
    @Bean
    public Binding fanoutBinding(Queue topicQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(topicQueue).to(fanoutExchange);
    }

    /**
     * 主题模式绑定分列模式
     * @return
     */
    @Bean
    public Binding topicBindingFanout(FanoutExchange fanoutExchange, TopicExchange topicExchange) {
        return BindingBuilder.bind(fanoutExchange).to(topicExchange).with(RabbitConstants.TOPIC_ROUTING_KEY_ONE);
    }

    /**
     * 延迟模式
     * @return
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = Maps.newHashMap();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(RabbitConstants.DELAY_MODE_QUEUE, "x-delayed-message", true, false, args);
    }

    /**
     * 自定义交换机绑定队列
     * @param delayQueue
     * @param delayExchange
     * @return
     */
    @Bean
    public Binding delayBinding(Queue delayQueue, CustomExchange delayExchange) {
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(RabbitConstants.DELAY_QUEUE).noargs();
    }


    /**
     * 直接队列
     * @return
     */
    @Bean
    public Queue directQueue() {
        return new Queue(RabbitConstants.DIRECT_MODE_QUEUE);
    }

    /**
     * 分列模式队列
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitConstants.FANOUT_MODE_QUEUE);
    }


    /**
     * 消息模板
     */
    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }
}
