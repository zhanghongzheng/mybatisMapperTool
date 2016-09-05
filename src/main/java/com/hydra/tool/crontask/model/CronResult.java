package com.hydra.tool.crontask.model;

/**
 * Created by ZhengGong on 15/9/17.
 * Description
 */
public class CronResult {
    private boolean execute;
    private CronTask cronTask;

    public boolean isExecute() {
        return execute;
    }

    public void setExecute(boolean execute) {
        this.execute = execute;
    }

    public CronTask getCronTask() {
        return cronTask;
    }

    public void setCronTask(CronTask cronTask) {
        this.cronTask = cronTask;
    }

    @Override
    public String toString() {
        return "CronResult{" +
                "execute=" + execute +
                ", cronTask=" + cronTask +
                '}';
    }
}
