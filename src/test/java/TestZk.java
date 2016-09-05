//import com.github.zkclient.IZkChildListener;
//import com.github.zkclient.ZkClient;
//import com.hydra.tool.zookeeper.zkclient.ZookeeperHelper;
//
//import java.util.List;
//
///**
// * Created by lee on 15/11/25.
// */
//public class TestZk {
//    public static void main(String[] args) {
//        ZookeeperHelper zookeeperHelper=ZookeeperHelper.getInstance();
//        ZkClient zk = zookeeperHelper.getZkClient();
//        System.out.println(zk.isConnected());
//
//        zk.subscribeChildChanges("/jike", new IZkChildListener() {
//            @Override
//            public void handleChildChange(String path, List<String> list) throws Exception {
//                System.out.println(path);
//                System.out.println(list.toArray());
//            }
//        });
////        System.out.
//    }
//}
