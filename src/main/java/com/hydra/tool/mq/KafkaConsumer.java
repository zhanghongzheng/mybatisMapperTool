//package com.hydra.tool.mq;
//
//import kafka.consumer.Consumer;
//import kafka.consumer.ConsumerConfig;
//import kafka.consumer.KafkaStream;
//import kafka.javaapi.consumer.ConsumerConnector;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.*;
//
///**
// * Created by bear_lee on 15/6/23.
// */
//public class KafkaConsumer {
//    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);
//    private ConsumerConfig consumerConfig;
//    private ConsumerConnector consumerConnector;
//    private Map<String, Integer> topicCountMap;
//    private Map<String, List<KafkaStream<String, String>>> kafkaStreamListMap;
//
//    public KafkaConsumer(ConsumerConfig consumerConfig, Map<String, Integer> topic) {
//        this.consumerConfig = consumerConfig;
//        this.topicCountMap = topic;
//    }
//
//    public void openConnection() {
//        this.consumerConnector = Consumer.createJavaConsumerConnector(this.consumerConfig);
//        this.kafkaStreamListMap = this.consumerConnector.createMessageStreams(this.topicCountMap, new StringMessageSerializer(), new StringMessageSerializer());
//    }
//
//    public Map<String, List<KafkaStream<String, String>>> getKafkaStreamListMap() {
//        if (this.kafkaStreamListMap == null) {
//            this.openConnection();
//            if (this.kafkaStreamListMap == null || this.kafkaStreamListMap.size() == 0) {
//                return null;
//            }
//        }
//
//        return this.kafkaStreamListMap;
//    }
//
//    public List<KafkaStream<String, String>> getKafkaStreamList(String topic) {
//        return this.getKafkaStreamListMap() == null ? null : this.getKafkaStreamListMap().get(topic);
//    }
//
//    public void close() {
//        this.consumerConnector.shutdown();
//    }
//
//}
