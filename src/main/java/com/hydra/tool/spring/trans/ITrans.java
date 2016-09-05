package com.hydra.tool.spring.trans;

/**
 * Created by gongzheng on 16/4/29.
 * project tool
 * packageName com.hydra.tool.spring.trans
 * Description
 */
public interface ITrans {
    void doTransaction() throws InterruptedException;

    void doCommit();

    void doRollback();

    void doWait() throws InterruptedException;

    void doStart();

    boolean isTimeout();

    Status status();

    interface ITransactionExecute {
        long getTimeout();

        void doTransaction();
    }

    enum Status {
        RUN, ERROR, FINISH;
    }
}
