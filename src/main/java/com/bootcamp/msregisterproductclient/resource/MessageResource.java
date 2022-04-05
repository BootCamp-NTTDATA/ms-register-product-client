package com.bootcamp.msregisterproductclient.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageResource<T> {
    private static final Logger logger = LoggerFactory.getLogger(MessageResource.class);
    private KafkaTemplate<String, T> kafkaTemplate;
    public MessageResource(KafkaTemplate<String, T> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(T t, String topic){
        logger.info("Message -> {}",t);
        this.kafkaTemplate.send(topic,t);
    }
}
