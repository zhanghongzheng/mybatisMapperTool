//package curator;
//
//import com.hydra.tool.zookeeper.curator.service.PersonManage;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.util.HashMap;
//
///**
// * Created by lee on 15/12/1.
// */
//public class TestMonitor {
//    public static void main(String[] args) throws InterruptedException {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
//        PersonManage personManage= (PersonManage) context.getBean("personManage");
//        personManage.monitor(new HashMap<String, Object>());
//    }
//}
