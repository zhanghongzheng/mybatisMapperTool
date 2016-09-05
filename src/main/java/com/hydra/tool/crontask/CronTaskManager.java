package com.hydra.tool.crontask;

import com.hydra.tool.crontask.constants.E;
import com.hydra.tool.crontask.constants.E.AccurateTimeType;
import com.hydra.tool.crontask.model.CronResult;
import com.hydra.tool.crontask.model.CronTask;
import com.hydra.tool.date.DateUtil;
import com.hydra.tool.nutz.NutzHelper;
import com.hydra.tool.object.ObjectUtil;
import com.hydra.tool.string.StrUtil;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ZhengGong on 15/9/17.
 * Description
 */
public class CronTaskManager {
    private static final CronTaskManager INSTANCE = new CronTaskManager();
    private static final Logger LOG = LoggerFactory.getLogger(CronTaskManager.class);

    private static final String TOKEN = UUID.randomUUID().toString().replace("-", "");
    private static final String SERVER = "hydra";

    private CronTaskManager() {

    }

    public static CronTaskManager me() {
        return INSTANCE;
    }

    public CronResult startAccurate(AccurateTimeType accurateTimeType, String name, int range) {
        CronResult cronResult = new CronResult();
        String[] rangeNames = null;
        if (range > 0) {
            rangeNames = new String[range];
        }

        CronTask cronTask = new CronTask();
        cronTask.setType(E.CronTaskType.ACCURATE.name());
        cronTask.setToken(TOKEN);
        cronTask.setStatus(E.Status.INIT);
        cronTask.setCreateTime(new Date());
        cronTask.setUpdateTime(cronTask.getCreateTime());
        cronTask.setCreateTimeSecondS(cronTask.getCreateTime().getTime());
        cronTask.setUpdateTimeSecondS(cronTask.getUpdateTime().getTime());

        // 生成服务名
        cronTask.setServer(getServer());

        // 生成ip地址
        cronTask.setIp(getIp());

        // 生成唯一名称
        switch (accurateTimeType) {
            case MINUTE:
                if (ObjectUtil.isNotNull(rangeNames)) {
                    for (int i = 0; i < range; i++) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.MINUTE, -i);
                        rangeNames[i] = name + DateUtil.formatToMinute(calendar.getTime());
                    }
                }
                name = name + DateUtil.formatToMinute(cronTask.getCreateTime());
                break;
            case HOUR:
                if (ObjectUtil.isNotNull(rangeNames)) {
                    for (int i = 0; i < range; i++) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.HOUR, -i);
                        rangeNames[i] = name + DateUtil.formatToHour(calendar.getTime());
                    }
                }
                name = name + DateUtil.formatToHour(cronTask.getCreateTime());
                break;
            case DAY:
                if (ObjectUtil.isNotNull(rangeNames)) {
                    for (int i = 0; i < range; i++) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.DAY_OF_YEAR, -i);
                        rangeNames[i] = name + DateUtil.formatToDay(calendar.getTime());
                    }
                }
                name = name + DateUtil.formatToDay(cronTask.getCreateTime());
                break;
            case MONTH:
                if (ObjectUtil.isNotNull(rangeNames)) {
                    for (int i = 0; i < range; i++) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.MONTH, -i);
                        rangeNames[i] = name + DateUtil.formatToMonth(calendar.getTime());
                    }
                }
                name = name + DateUtil.formatToMonth(cronTask.getCreateTime());
                break;
            case YEAR:
                if (ObjectUtil.isNotNull(rangeNames)) {
                    for (int i = 0; i < range; i++) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.YEAR, -i);
                        rangeNames[i] = name + DateUtil.formatToYear(calendar.getTime());
                    }
                }
                name = name + DateUtil.formatToYear(cronTask.getCreateTime());
                break;
            default:
                break;
        }
        cronTask.setTaskName(name);

        // 检查是否已存在
        if (ObjectUtil.isNotNull(rangeNames)) {
            Criteria cri = Cnd.cri();
            cri.where().andIn("taskName", rangeNames).and("server", "=", cronTask.getServer());
            if (NutzHelper.getInstance().getDao().count(CronTask.class, cri) > 0) {
                cronResult.setExecute(false);
                return cronResult;
            }
        }

        // 抢占数据
        try {
            NutzHelper.getInstance().getDao().insert(cronTask);
            cronResult.setCronTask(cronTask);
            cronResult.setExecute(true);
        } catch (Exception e) {
            if (!ObjectUtil.hasNull(e.getCause(), e.getCause().getCause())) {
                String str = e.getCause().getCause().toString();
                if (StrUtil.isNotBlank(str) && str.contains("com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry")) {
                    LOG.info("###############  the cronTask is run with other manager  ###############");
                }
            } else {
                LOG.error("###############  startAccurate error  ###############", e);
            }
            cronResult.setExecute(false);
        }

        return cronResult;
    }

    public CronResult startInterval(String name, long timeout) {
        CronResult cronResult = new CronResult();

        String server = getServer();

        CronTask c = NutzHelper.getInstance().getDao().fetch(CronTask.class, Cnd.where("taskName", "=", name).and("server", "=", server));
        Date currentDate = new Date();

        // 检查是否已存在
        if (ObjectUtil.isNotNull(c)) {
            // 判断是否是自己的
            if (TOKEN.equals(c.getToken())) {
                // 判断是否执行成功
                if (NutzHelper.getInstance().getDao()
                        .update(CronTask.class,
                                Chain.make("updateTime", currentDate)
                                        .add("updateTimeSecondS", currentDate.getTime())
                                        .add("status", E.Status.INIT),
                                Cnd.where("token", "=", TOKEN).and("id", "=", c.getId())) == 0) {
                    cronResult.setExecute(false);
                    return cronResult;
                } else {
                    cronResult.setExecute(true);
                    c.setUpdateTime(currentDate);
                    c.setUpdateTimeSecondS(currentDate.getTime());
                    cronResult.setCronTask(c);
                    return cronResult;
                }
            } else {
                // 不是自己的 判断是否可以抢占
                if (System.currentTimeMillis() - c.getUpdateTimeSecondS() > timeout) {
                    // 判断是否抢占成功
                    if (NutzHelper.getInstance().getDao()
                            .update(CronTask.class,
                                    Chain.make("updateTime", currentDate)
                                            .add("updateTimeSecondS", currentDate.getTime())
                                            .add("token", TOKEN)
                                            .add("status", E.Status.INIT)
                                            .add("ip", getIp()),
                                    Cnd.where("token", "=", c.getToken()).and("id", "=", c.getId()).and("updateTimeSecondS", "<", System.currentTimeMillis() - timeout)) == 0) {
                        cronResult.setExecute(false);
                        return cronResult;
                    } else {
                        c.setToken(TOKEN);
                        c.setUpdateTime(currentDate);
                        c.setUpdateTimeSecondS(currentDate.getTime());
                        cronResult.setCronTask(c);
                        cronResult.setExecute(true);
                        return cronResult;
                    }
                } else {
                    // 不能抢占
                    cronResult.setExecute(false);
                    return cronResult;
                }
            }

            // 不存在 进行抢占
        } else {
            try {
                CronTask cronTask = new CronTask();
                cronTask.setType(E.CronTaskType.INTERVAL.name());
                cronTask.setToken(TOKEN);
                cronTask.setStatus(E.Status.INIT);
                cronTask.setCreateTime(new Date());
                cronTask.setUpdateTime(cronTask.getCreateTime());
                cronTask.setCreateTimeSecondS(cronTask.getCreateTime().getTime());
                cronTask.setUpdateTimeSecondS(cronTask.getUpdateTime().getTime());

                // 生成服务名
                cronTask.setServer(server);

                // 生成ip地址
                cronTask.setIp(getIp());
                cronTask.setTaskName(name);

                NutzHelper.getInstance().getDao().insert(cronTask);
                cronResult.setCronTask(cronTask);
                cronResult.setExecute(true);

                return cronResult;
            } catch (Exception e) {
                // 抢占失败
                if (!ObjectUtil.hasNull(e.getCause(), e.getCause().getCause())) {
                    String str = e.getCause().getCause().toString();
                    if (StrUtil.isNotBlank(str) && str.contains("com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry")) {
                        LOG.info("###############  the cronTask is run with other manager  ###############");
                    }
                } else {
                    LOG.error("###############  startInterval error  ###############", e);
                }
                cronResult.setExecute(false);
                return cronResult;
            }
        }
    }

//    public void (CronTask cronTask) {
////        Sql sql = Sqls.create("update pay_cron_task set status=@status, updateTime=@updateTime, updateTimeSeconds=@updateTimeSeconds where taskName=@taskName and server=@server");
////        sql.params().set("server", cronTask.getServer());
////        sql.params().set("taskName", cronTask.getTaskName());
////        sql.params().set("status", E.Status.FINISH);
////        sql.params().set("updateTime", new Date());
////        sql.params().set("updateTimeSeconds", System.currentTimeMillis());
////        NutzHelper.getInstance().getDao().execute(sql);
//        NutzHelper.getInstance().getDao()
//                .update(CronTask.class,
//                        Chain.make("status", E.Status.FINISH)
//                                .add("updateTimeSecondS", System.currentTimeMillis())
//                                .add("updateTime", new Date()),
//                        Cnd.where("taskName", "=", cronTask.getTaskName())
//                                .and("server", "=", cronTask.getServer()));
//    }

    public void finishTask(CronTask cronTask) {
//        Sql sql = Sqls.create("update pay_cron_task set status=@status, updateTime=@updateTime, updateTimeSeconds=@updateTimeSeconds where taskName=@taskName and server=@server");
//        sql.params().set("server", cronTask.getServer());
//        sql.params().set("taskName", cronTask.getTaskName());
//        sql.params().set("status", E.Status.FINISH);
//        sql.params().set("updateTime", new Date());
//        sql.params().set("updateTimeSeconds", System.currentTimeMillis());
//        NutzHelper.getInstance().getDao().execute(sql);
        NutzHelper.getInstance().getDao()
                .update(CronTask.class,
                        Chain.make("status", E.Status.FINISH)
                                .add("updateTimeSecondS", System.currentTimeMillis())
                                .add("updateTime", new Date()),
                        Cnd.where("taskName", "=", cronTask.getTaskName())
                                .and("server", "=", cronTask.getServer())
                                .and("token", "=", TOKEN));
    }

    private String getServer() {
//        String projectname = System.getProperty("user.dir");
//        return projectname.substring(projectname.lastIndexOf(File.separator) + 1, projectname.length());
        return SERVER;
    }

    private String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            LOG.error("getLocalHost error! ", e);
            return "0.0.0.0";
        }
    }
}
