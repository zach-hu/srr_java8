package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ScheduledTask implements Serializable {

    /** identifier field */
    private BigDecimal icSchedule;

    /** nullable persistent field */
    private String taskName;

    /** nullable persistent field */
    private String taskType;

    /** nullable persistent field */
    private String taskProcess;

    /** nullable persistent field */
    private String taskStartDate;

    /** nullable persistent field */
    private String taskEndDate;

    /** nullable persistent field */
    private String taskStatus;
    
    /** nullable persistent field */
    private String taskNotify;

    /** full constructor */
    public ScheduledTask(BigDecimal icSchedule, String taskName, String taskType, String taskProcess, String taskStartDate, String taskEndDate, String taskStatus, String taskNotify) {
        this.icSchedule = icSchedule;
        this.taskName = taskName;
        this.taskType = taskType;
        this.taskProcess = taskProcess;
        this.taskStartDate = taskStartDate;
        this.taskEndDate = taskEndDate;
        this.taskStatus = taskStatus;
        this.taskNotify = taskNotify;
    }

    /** default constructor */
    public ScheduledTask() {
    }

    /** minimal constructor */
    public ScheduledTask(BigDecimal icSchedule) {
        this.icSchedule = icSchedule;
    }

    public BigDecimal getIcSchedule() {
		return (BigDecimal)HiltonUtility.ckNull(icSchedule);
    }

    public void setIcSchedule(BigDecimal icSchedule) {
		this.icSchedule = icSchedule;
    }

    public java.lang.String getTaskName() {
		return (java.lang.String)HiltonUtility.ckNull(this.taskName).trim();
    }

    public void setTaskName(java.lang.String taskName) {
		if (!HiltonUtility.isEmpty(taskName) && taskName.length() > 60) {
			taskName = taskName.substring(0, 60);
		}
		this.taskName = taskName;
    }

    public java.lang.String getTaskType() {
		return (java.lang.String)HiltonUtility.ckNull(this.taskType).trim();
    }

    public void setTaskType(java.lang.String taskType) {
		if (!HiltonUtility.isEmpty(taskType) && taskType.length() > 1) {
			taskType = taskType.substring(0, 1);
		}
		this.taskType = taskType;
    }

    public java.lang.String getTaskProcess() {
		return (java.lang.String)HiltonUtility.ckNull(this.taskProcess).trim();
    }

    public void setTaskProcess(java.lang.String taskProcess) {
		if (!HiltonUtility.isEmpty(taskProcess) && taskProcess.length() > 60) {
			taskProcess = taskProcess.substring(0, 60);
		}
		this.taskProcess = taskProcess;
    }

    public String getTaskStartDate() {
		return this.taskStartDate;
    }

    public void setTaskStartDate(String taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public String getTaskEndDate() {
		return this.taskEndDate;
    }

    public void setTaskEndDate(String taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public java.lang.String getTaskStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.taskStatus).trim();
    }

    public void setTaskStatus(java.lang.String taskStatus) {
		if (!HiltonUtility.isEmpty(taskStatus) && taskStatus.length() > 4) {
			taskStatus = taskStatus.substring(0, 4);
		}
		this.taskStatus = taskStatus;
    }
    
    public String getTaskNotify() {
    	return taskNotify;
    }
    
    public void setTaskNotify(String taskNotify) {
    	this.taskNotify = taskNotify;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icSchedule", getIcSchedule())
            .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcSchedule())
            .toHashCode();
    }

}
