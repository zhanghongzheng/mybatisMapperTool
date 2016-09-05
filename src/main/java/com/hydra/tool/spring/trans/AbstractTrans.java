package com.hydra.tool.spring.trans;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by gongzheng on 16/5/2.
 * project tool
 * packageName com.hydra.tool.spring.trans
 * Description
 */
public abstract class AbstractTrans implements ITrans {
    private volatile ITransactionExecute transactionExecute;
    private AtomicBoolean commit = new AtomicBoolean(false);
    private volatile Status status = Status.RUN;
    private volatile long startTime = 0;


    public ITransactionExecute getTransactionExecute() {
        return transactionExecute;
    }

    public void setTransactionExecute(ITransactionExecute transactionExecute) {
        this.transactionExecute = transactionExecute;
    }

    public void doWait() throws InterruptedException {
        status = Status.FINISH;

        while (!commit.get()) {
            if (status == Status.ERROR) {
                throw new RuntimeException("");
            }
            Thread.sleep(1);
        }
    }

    @Override
    public void doStart() {
        if (transactionExecute == null) {
            status = Status.ERROR;
            throw new RuntimeException("transactionExecute is null");
        }
        startTime = System.currentTimeMillis();
    }

    @Override
    public void doCommit() {
        commit.set(true);
    }

    @Override
    public void doRollback() {
        status = Status.ERROR;
    }

    @Override
    public boolean isTimeout() {
        return !(startTime == 0 || transactionExecute.getTimeout() == 0) && System.currentTimeMillis() - startTime > transactionExecute.getTimeout();
    }

    @Override
    public Status status() {
        return status;
    }
}
