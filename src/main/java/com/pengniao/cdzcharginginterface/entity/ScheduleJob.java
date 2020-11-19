package com.pengniao.cdzcharginginterface.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassNameScheduleJob
 * @Description
 * @Author
 * @Date2020/8/25 9:37
 * @Version V1.0
 **/

@Component
public class ScheduleJob implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final  String JOB_PARAM_KEY="JOB_PARAM_KEY"; // 任务调度key

    @JsonSerialize(using = ToStringSerializer.class)
    private Long jobId; // 任务ID

    private String beanName; // spring bean名称

    private String params; // 参数

    private String cronExpression; // cron表达式

    private Integer status; // 任务状态

    private String remark; // 备注

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}