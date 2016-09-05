//package com.hydra.tool.mq;
//
//import kafka.javaapi.producer.Producer;
//import kafka.producer.KeyedMessage;
//import kafka.producer.ProducerConfig;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Properties;
//
///**
// * Created by bear_lee on 15/6/23.
// */
//public class KafkaProducer {
//    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);
//    private Producer<String, String> producer;
//    private ProducerConfig producerConfig;
//    private String topic;
//
//    public KafkaProducer(ProducerConfig producerConfig, String topic) {
//        this.producerConfig = producerConfig;
//        this.topic = topic;
//    }
//
//    public KafkaProducer(Properties properties, String topic) {
//        this.producerConfig = new ProducerConfig(properties);
//        this.topic = topic;
//    }
//
//    public boolean sendMessage(String msg, String partitionKey) {
//        if (!StringUtils.isBlank(msg) && !StringUtils.isBlank(topic)) {
//            KeyedMessage<String, String> data;
//            if (StringUtils.isBlank(partitionKey)) {
//                data = new KeyedMessage<String, String>(topic, msg);
//            } else {
//                data = new KeyedMessage<String, String>(topic, partitionKey, msg);
//            }
//
//            producer.send(data);
//            return true;
//        } else {
//            LOG.info("msg or topic cannot be null,msg:" + msg + " topic:" + topic);
//            return false;
//        }
//    }
//
//    public boolean sendMessage(String msg) {
//        return sendMessage(msg, null);
//    }
//
//    public boolean sendMessageList(List<String> msgList, String partitionKey) {
//        if (msgList != null && msgList.size() != 0 && !StringUtils.isBlank(topic)) {
//            ArrayList<KeyedMessage<String, String>> keyedMessageList = new ArrayList<KeyedMessage<String, String>>();
//            Iterator<String> var3;
//            String msg;
//            if (StringUtils.isBlank(partitionKey)) {
//                var3 = msgList.iterator();
//
//                while (var3.hasNext()) {
//                    msg = var3.next();
//                    keyedMessageList.add(new KeyedMessage<String, String>(topic, msg));
//                }
//            } else {
//                var3 = msgList.iterator();
//
//                while (var3.hasNext()) {
//                    msg = var3.next();
//                    keyedMessageList.add(new KeyedMessage<String, String>(topic, partitionKey, msg));
//                }
//            }
//
//            producer.send(keyedMessageList);
//            return true;
//        } else {
//            LOG.info("msgList or topic cannot be null,topic:" + topic);
//            return false;
//        }
//    }
//
//    public boolean sendMessageList(List<String> msgList) {
//        return sendMessageList(msgList, null);
//    }
//
//    public void openConnection() {
//        producer = new Producer<String, String>(producerConfig);
//    }
//
//    public void close() {
//        producer.close();
//    }
//
//    public String getTopic() {
//        return topic;
//    }
//
//    public void setTopic(String topic) {
//        this.topic = topic;
//    }
//}
