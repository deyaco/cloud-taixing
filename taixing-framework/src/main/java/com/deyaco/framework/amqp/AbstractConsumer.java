package com.deyaco.framework.amqp;

import com.deyaco.framework.core.api.ApiResult;
import com.deyaco.framework.core.util.JsonUtils;
import com.deyaco.framework.utils.AssertUtils;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public class AbstractConsumer<T extends Serializable> {
    private static final Logger log = LoggerFactory.getLogger(AbstractConsumer.class);

    public AbstractConsumer() {
    }

    public void onEvent(Message message, Channel channel) {
        try {
            String msgBody = new String(message.getBody());
            log.info("消费消息，message：" + message);
            MqObject mqObject = (MqObject) JsonUtils.fromJsonString(msgBody, MqObject.class);
            String busiObjectStr = mqObject.getBusiObjectStr();
            T busiObject = JsonUtils.fromJsonString(busiObjectStr, this.getBusiObjectClass());

            try {
                ApiResult consumeResult = this.doBusiness(busiObject);
                log.info("消费结果，consumeResult：" + consumeResult);
                AssertUtils.assertTrue(consumeResult != null && consumeResult.isOk(), AMQPStatusEnum.FAIL_500, consumeResult.getErrorMsg());
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (Exception var11) {
                Integer oldRetryCnt = mqObject.getRetryCnt();
                int newRetryCnt = oldRetryCnt != null ? oldRetryCnt + 1 : 1;
                if (newRetryCnt > this.getRetryCnt()) {
                    this.handleMessageReject(busiObject, var11.getMessage());
                    channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                } else {
                    mqObject.setRetryCnt(newRetryCnt);
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                    this.getRabbitTemplate().convertAndSend(this.getExchange(), this.getRoutingKey(), mqObject, (ms) -> {
                        ms.getMessageProperties().setDelay('\uea60' * newRetryCnt * newRetryCnt);
                        return ms;
                    });
                    log.warn("消费失败，重新入队成功，message：" + message);
                }

                log.error(var11.getMessage(), var11);
            }
        } catch (Exception var12) {
            try {
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException var10) {
                log.error(var10.getMessage(), var10);
            }

            log.error(var12.getMessage(), var12);
        }

    }

    protected abstract void handleMessageReject(T busiObject, String lastRetryInfo);

    protected abstract ApiResult<String> doBusiness(T busiObject);

    protected abstract RabbitTemplate getRabbitTemplate();

    protected abstract int getRetryCnt();

    protected abstract String getRoutingKey();

    protected abstract String getExchange();

    protected Class<T> getBusiObjectClass() {
        Class<?> clazz = this.getClass();
        ParameterizedType type = (ParameterizedType)clazz.getGenericSuperclass();
        return (Class)((Class)type.getActualTypeArguments()[0]);
    }
}
