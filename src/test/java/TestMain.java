//import com.github.zkclient.ZkClient;
//import com.hydra.tool.system.Sys;
//import com.hydra.tool.zookeeper.zkclient.ZookeeperHelper;
//
///**
// * Created by ZhengGong on 15/6/12.
// * Description
// */
//
//public class TestMain {
//    public static void main(String[] args) throws Exception {
//        ZookeeperHelper zookeeperHelper = ZookeeperHelper.getInstance();
//
//        ZkClient zkClient = zookeeperHelper.getZkClient();
//
//        Sys.pl(zkClient.getChildren("/zookeeper/quota"));
////
//        if (!zookeeperHelper.getZkClient().exists("/123")) {
//            zookeeperHelper.getZkClient().createPersistent("/123");
//        }
////
////        zkClient.subscribeDataChanges("/123", new IZkDataListener() {
////            @Override
////            public void handleDataChange(String dataPath, byte[] data) throws Exception {
////                Sys.pl("********" + dataPath + " " + new String(data));
////            }
////
////            @Override
////            public void handleDataDeleted(String dataPath) throws Exception {
////
////            }
////        });
////
////        if (zookeeperHelper.getZkClient().exists("/123") && !zookeeperHelper.getZkClient().exists("/123/123")) {
////            zookeeperHelper.getZkClient().createPersistent("/123/123");
////        }
////
//        Sys.pl(new String(zookeeperHelper.getZkClient().readData("/123")));
////
////
//        zookeeperHelper.getZkClient().writeData("/123", "666".getBytes());
////        Thread.sleep(1000);
////        zookeeperHelper.getZkClient().writeData("/123", "777".getBytes());
////        Thread.sleep(1000);
////        zookeeperHelper.getZkClient().writeData("/123", "123".getBytes());
////        Thread.sleep(1000);
////        zookeeperHelper.getZkClient().writeData("/123", "999".getBytes());
////
////        Sys.pl(new String(zookeeperHelper.getZkClient().readData("/123")));
////
////        Thread.sleep(2000);
//        zookeeperHelper.close();
//
////        Sys.pl(JSON.class.getPackage().getImplementationVersion());
////        Sys.pl(StrUtil.class.getPackage().getSpecificationVersion());
////        Sys.pl(StrUtil.class.getProtectionDomain().getCodeSource().getLocation());
////
////        String file = JSON.class.getProtectionDomain().getCodeSource().getLocation().getFile();
////
////        if (file != null && file.length() > 0 && file.endsWith(".jar")) {
////            file = file.substring(0, file.length() - 4);
////            int i = file.lastIndexOf('/');
////            if (i >= 0) {
////                file = file.substring(i + 1);
////            }
////            i = file.indexOf("-");
////            if (i >= 0) {
////                file = file.substring(i + 1);
////            }
////            while (file.length() > 0 && ! Character.isDigit(file.charAt(0))) {
////                i = file.indexOf("-");
////                if (i >= 0) {
////                    file = file.substring(i + 1);
////                } else {
////                    break;
////                }
////            }
////            Sys.pl(file);
////        }
////        HttpClient httpClient = new DefaultHttpClient();
////        httpClient.getParams().setIntParameter(
////                CoreConnectionPNames.CONNECTION_TIMEOUT, 3500);
////        httpClient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
////        HttpGet get = new HttpGet("http://weixin.sogou.com/");
////        HttpResponse httpResponse = httpClient.execute(get);
////        if (httpResponse.getStatusLine().getStatusCode() == 200) {
////            Sys.pl(EntityUtils.toString(httpResponse.getEntity()));
////        }
////
////        HttpGet get1 = new HttpGet("http://weixin.sogou.com/websearch/art.jsp?sg=CBf80b2xkgbp3iR_t0ZX-FCrvwVkU57LVAfLMe1e6ZOtSPzwSdZCERZYJdTO2JrxkV1QLmlqCXt5GZN7kkeUWe7E_9cjSm0a4w_cKZqaCZHOcK5HhpC9jJjF6wOQJ8L3S8-7Zz5sxcKd4Gg1RTE1MaPfcMuIPlGNoKlZqn5POiVkn8d__3EbjQ..&url=p0OVDH8R4SHyUySb8E88hkJm8GF_McJfBfynRTbN8wjgNTWV8BRBXH0VwWyjQaNCDprM5crRRNlaTVexpbyICmQ3JxMQ3374WGOYe1ZsR4zG33uTv1DHDuXsTmIVPXTD7UAbu8Yw7vBYy-5x5In7jJFmExjqCxhpkyjFvwP6PuGcQ64lGQ2ZDMuqxplQrsbk");
////        HttpResponse httpResponse1 = httpClient.execute(get1);
////        if (httpResponse1.getStatusLine().getStatusCode() == 200) {
////            Sys.pl(EntityUtils.toString(httpResponse1.getEntity()));
////        }
//    }
//}
