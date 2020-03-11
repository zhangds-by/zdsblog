package com.zhangds.zdsblog.handler.rebbitmq;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.zhangds.zdsblog.common.model.constants.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 延迟队列处理器
 * Create by zhangds
 * 2020-02-28 18:18
 **/
@Slf4j
@Component
@RabbitListener(queues = RabbitConstants.DELAY_QUEUE)
public class QueueHandler {

    @RabbitHandler
    public void directHandlerManualAck(Message message, Channel channel) {
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // 处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
