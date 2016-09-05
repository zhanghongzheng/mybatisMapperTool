//package trans;
//
//import com.hydra.tool.spring.trans.AbstractTrans;
//import com.hydra.tool.spring.trans.TransExec;
//import com.hydra.tool.spring.trans.Transcation2PCManager;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * Created by gongzheng on 16/5/3.
// * project tool
// * packageName trans
// * Description
// */
//public class SimpleTrans extends AbstractTrans {
//
//    @Override
//    @Transactional
//    public void doTransaction() throws InterruptedException {
//        doStart();
//
//        getTransactionExecute().doTransaction();
//
//        doWait();
//
//        doCommit();
//    }
//
//    public static void main(String[] args) {
//        TransExec t = Transcation2PCManager.build().add(new SimpleTrans(), new ITransactionExecute() {
//            @Override
//            public long getTimeout() {
//                return 1000;
//            }
//
//            @Override
//            public void doTransaction() {
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).build();
//
//        if (!t.doTrans()) {
//            throw new RuntimeException("");
//        }
//    }
//
//}
