package com.hydra.tool.crontask.constants;

/**
 * Created by ZhengGong on 15/9/17.
 * Description
 */
public final class E {
    public enum CronTaskType {
        ACCURATE, // 精确定时
        INTERVAL  // 间隔定时
    }

    public enum AccurateTimeType {
        MINUTE,   // 分
        HOUR,     // 小时
        DAY,      // 天
        MONTH,    // 月
        YEAR      // 年
    }

    public final class Status {
        public static final int INIT = 0;
        public static final int FINISH = 1;
        public static final int ERROR = 2;
    }

}
