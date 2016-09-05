//import com.hydra.tool.config.ConfigUtil;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.framework.api.BackgroundCallback;
//import org.apache.curator.framework.api.CuratorEvent;
//import org.apache.curator.framework.api.CuratorEventType;
//import org.apache.curator.retry.RetryUntilElapsed;
//import org.apache.zookeeper.CreateMode;
//import org.apache.zookeeper.data.Stat;
//
//import java.util.Map;
//
///**
// * Created by lee on 15/11/25.
// */
//public class TestCurator {
//
//    private static final String CONNECT_STRING_NAME = "zookeeper.connectString";
//    private static final String SESSION_TIMEOUT_NAME = "zookeeper.sessionTimeout";
//    private static final String CONNECTION_TIMEOUT_NAME = "zookeeper.connectionTimeout";
//
//    public static void main(String[] args) throws Exception {
//        Map<String, String> properties = ConfigUtil.getConfigsByFile("zookeeper", "zookeeper");
//        CuratorFramework client= CuratorFrameworkFactory.builder().connectString(properties.get(CONNECT_STRING_NAME))
//                .sessionTimeoutMs(5000)
//                .connectionTimeoutMs(5000)
//                .retryPolicy(new RetryUntilElapsed(5000,1000)).build();
//        client.start();
//
//        Stat stat=new Stat();
//
////        byte[] ret=client.getData().storingStatIn(stat).forPath("/jike");
//
////        String path=client.create()
////                .creatingParentsIfNeeded()
////                .withMode(CreateMode.PERSISTENT)
////                .forPath("/jike2","213".getBytes());
//
////        System.out.println(stat);
//
////        client.setData().forPath("/jike","123".getBytes());
////        client.checkExists().inBackground(new BackgroundCallback() {
////            @Override
////            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
////                CuratorEventType t=curatorEvent.getType();
////                curatorEvent.getResultCode();
////                curatorEvent.getContext();
////            }
////        },"123").forPath("/jike2")
//
//        System.out.println(client.checkExists().forPath("/jike2"));
//    }
//}
