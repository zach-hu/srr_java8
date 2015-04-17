package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.tsagate.foundation.utility.Dates;
import com.tsa.puridiom.common.documents.ReportQueueDecoder;

/** @author Hibernate CodeGenerator */
public class ReportQueue implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icReportQueue;

    /** nullable persistent field */
    private String module;

    /** nullable persistent field */
    private String type;

    /** nullable persistent field */
    private String frequency;

    /** nullable persistent field */
    private java.util.Date startDate;

    /** nullable persistent field */
    private java.util.Date endDate;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String sendFrom;

    /** nullable persistent field */
    private String sendTo;

    /** nullable persistent field */
    private String dateAdded;

    /** nullable persistent field */
    private String timeAdded;

    /** nullable persistent field */
    private String dateSent;

    /** nullable persistent field */
    private String timeSent;

    /** nullable persistent field */
    private String deliveryTime;

    /** nullable persistent field */
    private String deliveryDay;

    /** nullable persistent field */
    private String whereClause;

    /** nullable persistent field */
    private java.util.Date nextRun;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private String alias;

    /** nullable persistent field */
    private String publicView;

    private java.math.BigDecimal attempts;
    
    /** full constructor */
    public ReportQueue(java.math.BigDecimal icReportQueue, java.lang.String module, java.lang.String type, java.lang.String frequency, java.util.Date startDate, java.util.Date endDate, java.lang.String status, java.lang.String owner, java.lang.String sendFrom, java.lang.String sendTo, java.lang.String dateAdded, java.lang.String timeAdded, java.lang.String dateSent, java.lang.String timeSent, java.lang.String deliveryTime, java.lang.String deliveryDay, java.lang.String whereClause, java.util.Date nextRun, java.lang.String name, java.lang.String alias, java.lang.String publicView, java.math.BigDecimal attempts) {
        this.icReportQueue = icReportQueue;
        this.module = module;
        this.type = type;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.owner = owner;
        this.sendFrom = sendFrom;
        this.sendTo = sendTo;
        this.dateAdded = dateAdded;
        this.timeAdded = timeAdded;
        this.dateSent = dateSent;
        this.timeSent = timeSent;
        this.deliveryTime = deliveryTime;
        this.deliveryDay = deliveryDay;
        this.whereClause = whereClause;
        this.nextRun = nextRun;
        this.name = name;
        this.alias = alias;
        this.publicView = publicView;
        this.attempts = attempts;
    }

    /** default constructor */
    public ReportQueue() {
    }

    /** minimal constructor */
    public ReportQueue(java.math.BigDecimal icReportQueue) {
        this.icReportQueue = icReportQueue;
    }

    public java.math.BigDecimal getIcReportQueue() {
        return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReportQueue);
    }

    public void setIcReportQueue(java.math.BigDecimal icReportQueue) {
        this.icReportQueue = icReportQueue;
    }

    public java.lang.String getModule() {
        return (java.lang.String)HiltonUtility.ckNull(this.module).trim();
    }

    public void setModule(java.lang.String module) {
        if (!HiltonUtility.isEmpty(module) && module.length() > 20) {
            module = module.substring(0, 20);
        }
        this.module = module;
    }

    public java.lang.String getType() {
        return (java.lang.String)HiltonUtility.ckNull(this.type).trim();
    }

    public void setType(java.lang.String type) {
        if (!HiltonUtility.isEmpty(type) && type.length() > 10) {
            type = type.substring(0, 10);
        }
        this.type = type;
    }

    public java.lang.String getFrequency() {
        return (java.lang.String)HiltonUtility.ckNull(this.frequency).trim();
    }

    public void setFrequency(java.lang.String frequency) {
        if (!HiltonUtility.isEmpty(frequency) && frequency.length() > 2) {
            frequency = frequency.substring(0, 2);
        }
        this.frequency = frequency;
    }

    public java.util.Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    public java.util.Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    public java.lang.String getStatus() {
        return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
        if (!HiltonUtility.isEmpty(status) && status.length() > 15) {
            status = status.substring(0, 15);
        }
        this.status = status;
    }

    public java.lang.String getOwner() {
        return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
        if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
            owner = owner.substring(0, 15);
        }
        this.owner = owner;
    }

    public java.lang.String getSendFrom() {
        return (java.lang.String)HiltonUtility.ckNull(this.sendFrom).trim();
    }

    public void setSendFrom(java.lang.String sendFrom) {
        if (!HiltonUtility.isEmpty(sendFrom) && sendFrom.length() > 50) {
            sendFrom = sendFrom.substring(0, 50);
        }
        this.sendFrom = sendFrom;
    }

    public java.lang.String getSendTo() {
        return (java.lang.String)HiltonUtility.ckNull(this.sendTo).trim();
    }

    public void setSendTo(java.lang.String sendTo) {
        if (!HiltonUtility.isEmpty(sendTo) && sendTo.length() > 2000) {
            sendTo = sendTo.substring(0, 2000);
        }
        this.sendTo = sendTo;
    }

    public java.lang.String getDateAdded() {
        return (java.lang.String)HiltonUtility.ckNull(this.dateAdded).trim();
    }

    public void setDateAdded(java.lang.String dateAdded) {
        if (!HiltonUtility.isEmpty(dateAdded) && dateAdded.length() > 20) {
            dateAdded = dateAdded.substring(0, 20);
        }
        this.dateAdded = dateAdded;
    }

    public java.lang.String getTimeAdded() {
        return (java.lang.String)HiltonUtility.ckNull(this.timeAdded).trim();
    }

    public void setTimeAdded(java.lang.String timeAdded) {
        if (!HiltonUtility.isEmpty(timeAdded) && timeAdded.length() > 20) {
            timeAdded = timeAdded.substring(0, 20);
        }
        this.timeAdded = timeAdded;
    }

    public java.lang.String getDateSent() {
        return (java.lang.String)HiltonUtility.ckNull(this.dateSent).trim();
    }

    public void setDateSent(java.lang.String dateSent) {
        if (!HiltonUtility.isEmpty(dateSent) && dateSent.length() > 20) {
            dateSent = dateSent.substring(0, 20);
        }
        this.dateSent = dateSent;
    }

    public java.lang.String getTimeSent() {
        return (java.lang.String)HiltonUtility.ckNull(this.timeSent).trim();
    }

    public void setTimeSent(java.lang.String timeSent) {
        if (!HiltonUtility.isEmpty(timeSent) && timeSent.length() > 20) {
            timeSent = timeSent.substring(0, 20);
        }
        this.timeSent = timeSent;
    }

    public java.lang.String getDeliveryTime() {
        return (java.lang.String)HiltonUtility.ckNull(this.deliveryTime).trim();
    }

    public void setDeliveryTime(java.lang.String deliveryTime) {
        if (!HiltonUtility.isEmpty(deliveryTime) && deliveryTime.length() > 20) {
            deliveryTime = deliveryTime.substring(0, 20);
        }
        this.deliveryTime = deliveryTime;
    }

    public java.lang.String getDeliveryDay() {
        return (java.lang.String)HiltonUtility.ckNull(this.deliveryDay).trim();
    }

    public void setDeliveryDay(java.lang.String deliveryDay) {
        if (!HiltonUtility.isEmpty(deliveryDay) && deliveryDay.length() > 20) {
            deliveryDay = deliveryDay.substring(0, 20);
        }
        this.deliveryDay = deliveryDay;
    }

    public java.lang.String getWhereClause() {
        return (java.lang.String)HiltonUtility.ckNull(this.whereClause).trim();
    }

    public void setWhereClause(java.lang.String whereClause) {
        if (!HiltonUtility.isEmpty(whereClause) && whereClause.length() > 1000) {
            whereClause = whereClause.substring(0, 1000);
        }
        this.whereClause = whereClause;
    }

    public java.lang.String getUserWhereClause(String organizationId) {
        String userWhereClause = null;

        if ((this.whereClause.toLowerCase().indexOf("2 = 2 and") < 0) || HiltonUtility.isEmpty(this.getWhereClause())) {
            userWhereClause = "Print All";
        } else {
        	userWhereClause = ReportQueueDecoder.decode(this.whereClause, organizationId);
        }

        return userWhereClause;
    }

    public java.util.Date getNextRun() {
        return this.nextRun;
    }

    public void setNextRun(java.util.Date nextRun) {
        this.nextRun = nextRun;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icReportQueue", getIcReportQueue())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ReportQueue) ) return false;
        ReportQueue castOther = (ReportQueue) other;
        return new EqualsBuilder()
            .append(this.getIcReportQueue(), castOther.getIcReportQueue())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcReportQueue())
            .toHashCode();
    }

    public java.lang.String getName() {
        return (java.lang.String)HiltonUtility.ckNull(this.name).trim();
    }

    public void setName(java.lang.String name) {
        if (!HiltonUtility.isEmpty(name) && name.length() > 255) {
            name = name.substring(0, 255);
        }
        this.name = name;
    }

    public java.lang.String getAlias() {
        return (java.lang.String)HiltonUtility.ckNull(this.alias).trim();
    }

    public void setAlias(java.lang.String alias) {
        this.alias = alias;
    }

    public java.lang.String getPublicView() {
        return (java.lang.String)HiltonUtility.ckNull(this.publicView).trim();
    }

    public void setPublicView(java.lang.String publicView) {
        this.publicView = publicView;
    }

    public void setDateTimeSent()
      {
          this.setDateSent(Dates.today("", ""));
          this.setTimeSent(Dates.getNow("", ""));
      }
    
    public java.math.BigDecimal getAttempts() {
        return (java.math.BigDecimal)HiltonUtility.ckNull(this.attempts);
    }

    public void setAttempts(java.math.BigDecimal attempts) {
        this.attempts = attempts;
    }

}
