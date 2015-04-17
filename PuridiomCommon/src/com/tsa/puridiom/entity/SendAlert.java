package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class SendAlert implements Serializable {

    /** identifier field */
    private java.math.BigDecimal queueid;

    /** nullable persistent field */
    private String doctype;

    /** nullable persistent field */
    private java.math.BigDecimal docic;

    /** nullable persistent field */
    private String subject;

    /** nullable persistent field */
    private String messagetext;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String sendfromtype;

    /** nullable persistent field */
    private String sendfrom;

    /** nullable persistent field */
    private String sendtotype;

    /** nullable persistent field */
    private String sendto;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String dateadded;

    /** nullable persistent field */
    private String timeadded;

    /** nullable persistent field */
    private String action;

    /** nullable persistent field */
    private String datesent;

    /** nullable persistent field */
    private String timesent;

    /** nullable persistent field */
    private String attachment;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private java.math.BigDecimal attempts;

    /** nullable persistent field */
    private String errorText;

    private String args;

    /** full constructor */
    public SendAlert(java.math.BigDecimal queueid, java.lang.String doctype, java.math.BigDecimal docic, java.lang.String subject, java.lang.String messagetext, java.lang.String owner, java.lang.String sendfromtype, java.lang.String sendfrom, java.lang.String sendtotype, java.lang.String sendto, java.lang.String status, java.lang.String dateadded, java.lang.String timeadded, java.lang.String action, java.lang.String datesent, java.lang.String timesent, java.lang.String attachment, java.lang.String vendorId,java.math.BigDecimal attempts, String errorText, String args) {
        this.queueid = queueid;
        this.doctype = doctype;
        this.docic = docic;
        this.subject = subject;
        this.messagetext = messagetext;
        this.owner = owner;
        this.sendfromtype = sendfromtype;
        this.sendfrom = sendfrom;
        this.sendtotype = sendtotype;
        this.sendto = sendto;
        this.status = status;
        this.dateadded = dateadded;
        this.timeadded = timeadded;
        this.action = action;
        this.datesent = datesent;
        this.timesent = timesent;
        this.attachment = attachment;
        this.vendorId = vendorId;
        this.attempts = attempts;
        this.errorText = errorText;
        this.args = args;
    }

    /** default constructor */
    public SendAlert() {
    }

    /** minimal constructor */
    public SendAlert(java.math.BigDecimal queueid) {
        this.queueid = queueid;
    }

    public java.math.BigDecimal getQueueid() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.queueid);
    }

    public void setQueueid(java.math.BigDecimal queueid) {
        this.queueid = queueid;
    }

    public java.lang.String getDoctype() {
		return (java.lang.String)HiltonUtility.ckNull(this.doctype).trim();
    }

    public void setDoctype(java.lang.String doctype) {
		if (!HiltonUtility.isEmpty(doctype) && doctype.length() > 3) {
			doctype = doctype.substring(0, 3);
		}
		this.doctype = doctype;
    }

    public java.math.BigDecimal getDocic() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.docic);
    }

    public void setDocic(java.math.BigDecimal docic) {
        this.docic = docic;
    }

    public java.lang.String getSubject() {
		return (java.lang.String)HiltonUtility.ckNull(this.subject).trim();
    }

    public void setSubject(java.lang.String subject) {
		if (!HiltonUtility.isEmpty(subject) && subject.length() > 4000) {
			subject = subject.substring(0, 4000);
		}
		this.subject = subject;
    }

    public java.lang.String getMessagetext() {
		return (java.lang.String)HiltonUtility.ckNull(this.messagetext).trim();
    }

    public void setMessagetext(java.lang.String messagetext) {
		if (!HiltonUtility.isEmpty(messagetext) && messagetext.length() > 4000) {
			messagetext = messagetext.substring(0, 4000);
		}
		this.messagetext = messagetext;
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

    public java.lang.String getSendfromtype() {
		return (java.lang.String)HiltonUtility.ckNull(this.sendfromtype).trim();
    }

    public void setSendfromtype(java.lang.String sendfromtype) {
		if (!HiltonUtility.isEmpty(sendfromtype) && sendfromtype.length() > 10) {
			sendfromtype = sendfromtype.substring(0, 10);
		}
		this.sendfromtype = sendfromtype;
    }

    public java.lang.String getSendfrom() {
		return (java.lang.String)HiltonUtility.ckNull(this.sendfrom).trim();
    }

    public void setSendfrom(java.lang.String sendfrom) {
		if (!HiltonUtility.isEmpty(sendfrom) && sendfrom.length() > 255) {
			sendfrom = sendfrom.substring(0, 255);
		}
		this.sendfrom = sendfrom;
    }

    public java.lang.String getSendtotype() {
		return (java.lang.String)HiltonUtility.ckNull(this.sendtotype).trim();
    }

    public void setSendtotype(java.lang.String sendtotype) {
		if (!HiltonUtility.isEmpty(sendtotype) && sendtotype.length() > 10) {
			sendtotype = sendtotype.substring(0, 10);
		}
		this.sendtotype = sendtotype;
    }

    public java.lang.String getSendto() {
		return (java.lang.String)HiltonUtility.ckNull(this.sendto).trim();
    }

    public void setSendto(java.lang.String sendto) {
		if (!HiltonUtility.isEmpty(sendto) && sendto.length() > 2000) {
			sendto = sendto.substring(0, 2000);
		}
		this.sendto = sendto;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
    }

    public java.lang.String getDateadded() {
		return (java.lang.String)HiltonUtility.ckNull(this.dateadded).trim();
    }

    public void setDateadded(java.lang.String dateadded) {
		if (!HiltonUtility.isEmpty(dateadded) && dateadded.length() > 10) {
			dateadded = dateadded.substring(0, 10);
		}
		this.dateadded = dateadded;
    }

    public java.lang.String getTimeadded() {
		return (java.lang.String)HiltonUtility.ckNull(this.timeadded).trim();
    }

    public void setTimeadded(java.lang.String timeadded) {
		if (!HiltonUtility.isEmpty(timeadded) && timeadded.length() > 10) {
			timeadded = timeadded.substring(0, 10);
		}
		this.timeadded = timeadded;
    }

    public java.lang.String getAction() {
		return (java.lang.String)HiltonUtility.ckNull(this.action).trim();
    }

    public void setAction(java.lang.String action) {
		if (!HiltonUtility.isEmpty(action) && action.length() > 10) {
			action = action.substring(0, 10);
		}
		this.action = action;
    }

    public java.lang.String getDatesent() {
		return (java.lang.String)HiltonUtility.ckNull(this.datesent).trim();
    }

    public void setDatesent(java.lang.String datesent) {
		if (!HiltonUtility.isEmpty(datesent) && datesent.length() > 10) {
			datesent = datesent.substring(0, 10);
		}
		this.datesent = datesent;
    }

    public java.lang.String getTimesent() {
		return (java.lang.String)HiltonUtility.ckNull(this.timesent).trim();
    }

    public void setTimesent(java.lang.String timesent) {
		if (!HiltonUtility.isEmpty(timesent) && timesent.length() > 10) {
			timesent = timesent.substring(0, 10);
		}
		this.timesent = timesent;
    }

    public java.lang.String getAttachment() {
		return (java.lang.String)HiltonUtility.ckNull(this.attachment).trim();
    }

    public void setAttachment(java.lang.String attachment) {
		if (!HiltonUtility.isEmpty(attachment) && attachment.length() > 255) {
			attachment = attachment.substring(0, 255);
		}
		this.attachment = attachment;
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId).trim();
    }

    public void setVendorId(java.lang.String vendorId) {
		if (!HiltonUtility.isEmpty(vendorId) && vendorId.length() > 15) {
			vendorId = vendorId.substring(0, 15);
		}
		this.vendorId = vendorId;
    }

    public java.math.BigDecimal getAttempts() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.attempts);
    }

    public void setAttempts(java.math.BigDecimal attempts) {
        this.attempts = attempts;
    }

    public java.lang.String getErrorText() {
		return (java.lang.String)HiltonUtility.ckNull(this.errorText).trim();
    }

    public void setErrorText(java.lang.String errorText) {
		if (!HiltonUtility.isEmpty(errorText) && errorText.length() > 2000) {
			errorText = errorText.substring(0, 2000);
		}
		this.errorText = errorText;
    }


    public String toString() {
        return new ToStringBuilder(this)
            .append("queueid", getQueueid())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SendQueue) ) return false;
        SendQueue castOther = (SendQueue) other;
        return new EqualsBuilder()
            .append(this.getQueueid(), castOther.getQueueid())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getQueueid())
            .toHashCode();
    }

    /**
     * @return Returns the args.
     */
    public String getArgs()
    {
        return args;
    }

    /**
     * @param args The args to set.
     */
    public void setArgs(String args)
    {
        this.args = args;
    }

}
