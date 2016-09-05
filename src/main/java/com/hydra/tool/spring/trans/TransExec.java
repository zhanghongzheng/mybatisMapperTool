package com.hydra.tool.spring.trans;

import com.google.common.collect.Lists;
import com.hydra.tool.object.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by gongzheng on 16/5/2.
 * project tool
 * packageName com.hydra.tool.spring.trans
 * Description
 */
public class TransExec implements ITransExec {
    private static final Logger LOG = LoggerFactory.getLogger(TransExec.class);
    private static final ExecutorService EXEC = Executors.newFixedThreadPool(20);

    private List<AbstractTrans> transList = Lists.newCopyOnWriteArrayList();

    public TransExec(List<TransactionsEntry> list) {
        if (ObjectUtil.isNotEmpty(list)) {
            for (TransactionsEntry t : list) {
                t.getTrans().setTransactionExecute(t.getTranscationExecute());
                transList.add(t.getTrans());
            }
        }
    }

    public boolean doTrans() {
        List<Future<?>> futureList = Lists.newArrayList();
        for (final ITrans iTrans : transList) {
            futureList.add(EXEC.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        iTrans.doTransaction();
                    } catch (Exception e) {
                        iTrans.doRollback();
                        LOG.error("***", e);
                    }
                }
            }));
        }

        int size = transList.size();

        while (true) {
            int finishSize = 0;
            for (ITrans iTrans : transList) {
                if (iTrans.status().equals(ITrans.Status.FINISH)) {
                    finishSize++;
                } else if (iTrans.status().equals(ITrans.Status.ERROR)) {
                    doRollback();
                    doStop(futureList);
                    return false;
                } else if (iTrans.status().equals(ITrans.Status.RUN)) {
                    if (iTrans.isTimeout()) {
                        doRollback();
                        doStop(futureList);
                        return false;
                    }
                }
            }

            if (size == finishSize) {
                doCommit();
                break;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                LOG.error("sleep error", e);
            }
        }

        for (Future<?> f : futureList) {
            try {
                f.get();
            } catch (Exception e) {
                LOG.error("future error", e);
            }
        }

        return true;
    }

    private void doRollback() {
        for (ITrans iTrans : transList) {
            iTrans.doRollback();
        }
    }

    private void doCommit() {
        for (ITrans iTrans : transList) {
            iTrans.doCommit();
        }
    }

    private void doStop(List<Future<?>> list) {
        for (Future<?> future : list) {
            future.cancel(true);
        }
    }

    public void doDestory() {
        EXEC.shutdownNow();
    }

    public static class TransactionsEntry {
        private ITrans.ITransactionExecute transcationExecute;
        private AbstractTrans trans;

        public TransactionsEntry(ITrans.ITransactionExecute transcationExecute, AbstractTrans trans) {
            this.transcationExecute = transcationExecute;
            this.trans = trans;
        }

        public ITrans.ITransactionExecute getTranscationExecute() {
            return transcationExecute;
        }

        public void setTranscationExecute(ITrans.ITransactionExecute transcationExecute) {
            this.transcationExecute = transcationExecute;
        }

        public AbstractTrans getTrans() {
            return trans;
        }

        public void setTrans(AbstractTrans trans) {
            this.trans = trans;
        }

        @Override
        public String toString() {
            return "TransactionsEntry{" +
                    "transcationExecute=" + transcationExecute +
                    ", trans=" + trans +
                    '}';
        }
    }
}
