//package com.hydra.tool.mq;
//
//import com.hydra.tool.config.ConfigUtil;
//import kafka.consumer.ConsumerConfig;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.*;
//
///**
// * Created by bear_lee on 15/6/23.
// */
//public class KafkaUtil {
//    private static final Logger LOG = LoggerFactory.getLogger(KafkaUtil.class);
//
//    /**
//     * 获取生产者
//     *
//     * @param folderPath
//     * @param filename
//     * @return
//     */
//    public static KafkaProducer getProducer(String folderPath, String filename, String topic) {
//        LOG.info("#####初始kafka配置化内容");
//        KafkaProducer kafkaProducer;
//        Properties properties = new Properties();
//        Map<String, String> cfgMap = ConfigUtil.getConfigsByFile(folderPath, filename);
//        properties.put("custom.encoding", cfgMap.get("zk.custom.encoding"));
//        properties.put("serializer.class", cfgMap.get("zk.serializer.class"));
//        properties.put("metadata.broker.list", cfgMap.get("zk.metadata.broker.list"));
//        properties.put("zookeeper.connect", cfgMap.get("zk.connect"));
//        properties.put("zookeeper.connectiontimeout.ms", cfgMap.get("zk.connectiontimeout.ms"));
//        kafkaProducer = new KafkaProducer(properties, topic);
//        LOG.info("#####读取kafka配置完成，准备打开连接");
//        kafkaProducer.openConnection();
//        LOG.info("#####kafka连接打开成功");
//        return kafkaProducer;
//    }
//
//    /**
//     * 获取消费者
//     *
//     * @param folderPath
//     * @param filename
//     * @return
//     */
//    public static KafkaConsumer getConsumer(String folderPath, String filename, String topic) {
//        KafkaConsumer kafkaConsumer;
//        Properties properties = new Properties();
//        Map<String, String> cfgMap = ConfigUtil.getConfigsByFile(folderPath, filename);
//        properties.put("zookeeper.connect", cfgMap.get("zk.connect"));
//        properties.put("zookeeper.connectiontimeout.ms", cfgMap.get("zk.connectiontimeout.ms"));
//        properties.put("group.id", topic);
//        TreeMap<String, Integer> topicMap = new TreeMap<String, Integer>();
//        topicMap.put(topic, 1);
//        kafkaConsumer = new KafkaConsumer(new ConsumerConfig(properties), topicMap);
//        kafkaConsumer.openConnection();
//        return kafkaConsumer;
//    }
//
//}
