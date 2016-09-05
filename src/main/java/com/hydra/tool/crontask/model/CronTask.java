package com.hydra.tool.crontask.model;

import org.nutz.dao.entity.annotation.*;

import java.util.Date;

/**
 * Created by ZhengGong on 15/9/17.
 * Description
 */

@Table("pay_cron_task")
public class CronTask {
    @Id
    private Long id;
    @Column
    @ColDefine(width = 32, notNull = true)
    private String ip;
    @Column("taskName")
    @ColDefine(width = 64, notNull = true)
    private String taskName;
    @Column
    @ColDefine(width = 64, notNull = true)
    private String server;
    @Column
    @ColDefine(width = 64, notNull = true)
    private String token;
    @Column
    @ColDefine(notNull = true)
    private Integer status;
    @Column
    @ColDefine(width = 16, notNull = true)
    private String type;
    @Column("createTime")
    @ColDefine(notNull = true)
    private Date createTime;
    @Column("updateTime")
    @ColDefine(notNull = true)
    private Date updateTime;
    @Column("createTimeSeconds")
    @ColDefine(notNull = true)
    private Long createTimeSecondS;
    @Column("updateTimeSeconds")
    @ColDefine(notNull = true)
    private Long updateTimeSecondS;
    @Column
    @ColDefine(width = 512, notNull = true)
    @Default("")
    private String ext;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateTimeSecondS() {
        return createTimeSecondS;
    }

    public void setCreateTimeSecondS(Long createTimeSecondS) {
        this.createTimeSecondS = createTimeSecondS;
    }

    public Long getUpdateTimeSecondS() {
        return updateTimeSecondS;
    }

    public void setUpdateTimeSecondS(Long updateTimeSecondS) {
        this.updateTimeSecondS = updateTimeSecondS;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "CronTask{" +
                "id=" + id + "," +
                "ip=" + ip + "," +
                "taskName=" + taskName + "," +
                "server=" + server + "," +
                "token=" + token + "," +
                "status=" + status + "," +
                "type=" + type + "," +
                "createTime=" + createTime + "," +
                "updateTime=" + updateTime + "," +
                "createTimeSecondS=" + createTimeSecondS + "," +
                "updateTimeSecondS=" + updateTimeSecondS + "," +
                "ext=" + ext +
                "}";
    }
}
