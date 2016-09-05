package com.hydra.tool.spring.trans;

import com.google.common.collect.Lists;
import com.hydra.tool.object.ObjectUtil;

import java.util.List;

/**
 * Created by gongzheng on 16/4/29.
 * project tool
 * packageName com.hydra.tool.spring.trans
 * Description 事务2阶段提交管理器
 */
public class Transcation2PCManager {
    public static TransactionsEntryBuilder build() {
        return new TransactionsEntryBuilder();
    }

    public static class TransactionsEntryBuilder {
        private List<TransExec.TransactionsEntry> transactionsEntryList = Lists.newArrayList();


        public TransactionsEntryBuilder add(AbstractTrans abstractTrans, ITrans.ITransactionExecute transactionExecute) {
            transactionsEntryList.add(new TransExec.TransactionsEntry(transactionExecute, abstractTrans));
            return this;
        }

        public TransExec build() {
            return new TransExec(transactionsEntryList);
        }
    }
}
