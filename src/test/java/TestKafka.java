//import com.hydra.tool.mq.KafkaConsumer;
//import com.hydra.tool.mq.KafkaProducer;
//import com.hydra.tool.mq.KafkaUtil;
//import kafka.consumer.ConsumerIterator;
//import kafka.consumer.KafkaStream;
//import org.junit.Test;
//
//import java.util.Iterator;
//import java.util.List;
//
///**
// * Created by bear_lee on 15/6/23.
// */
//
//public class TestKafka {
//
//    @Test
//    public void testProducer(){
//        KafkaProducer producer = KafkaUtil.getProducer("kafka", "kafka", "test");
//        for(int i = 0; i < 10; ++i) {
//            System.out.println(i);
//            producer.sendMessage("消息:" + i);
//        }
//        producer.close();
//    }
//
//    @Test
//    public void testConsumer(){
//        KafkaConsumer consumer = KafkaUtil.getConsumer("kafka","kafka","test");
//        List kafkaStreamList =consumer.getKafkaStreamList("test");
//        Iterator var5 = kafkaStreamList.iterator();
//
//        while(var5.hasNext()) {
//            KafkaStream kafkaStream = (KafkaStream)var5.next();
//            ConsumerIterator iterable = kafkaStream.iterator();
//
//            while(iterable.hasNext()) {
//                System.out.println(iterable.next().message());
//            }
//        }
//        consumer.close();
//    }
//}
