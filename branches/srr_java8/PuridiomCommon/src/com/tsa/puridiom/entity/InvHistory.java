package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class InvHistory implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.InvHistoryPK comp_id;

    /** nullable persistent field */
    private java.util.Date docPrtDate;

    /** nullable persistent field */
    private String primUser;

    /** nullable persistent field */
    private java.util.Date puAppDate;

    /** nullable persistent field */
    private String faId;

    /** nullable persistent field */
    private java.util.Date faAppDate;

    /** full constructor */
    public InvHistory(com.tsa.puridiom.entity.InvHistoryPK comp_id, java.util.Date docPrtDate, java.lang.String primUser, java.util.Date puAppDate, java.lang.String faId, java.util.Date faAppDate) {
        this.comp_id = comp_id;
        this.docPrtDate = docPrtDate;
        this.primUser = primUser;
        this.puAppDate = puAppDate;
        this.faId = faId;
        this.faAppDate = faAppDate;
    }

    /** default constructor */
    public InvHistory() {
    }

    /** minimal constructor */
    public InvHistory(com.tsa.puridiom.entity.InvHistoryPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.InvHistoryPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.InvHistoryPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.util.Date getDocPrtDate() {
		return this.docPrtDate;
//		return HiltonUtility.ckNull(this.docPrtDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.docPrtDate);
    }

    public void setDocPrtDate(java.util.Date docPrtDate) {
        this.docPrtDate = docPrtDate;
    }

    public java.lang.String getPrimUser() {
		return (java.lang.String)HiltonUtility.ckNull(this.primUser).trim();
    }

    public void setPrimUser(java.lang.String primUser) {
		if (!HiltonUtility.isEmpty(primUser) && primUser.length() > 15) {
			primUser = primUser.substring(0, 15);
		}
		this.primUser = primUser;
    }

    public java.util.Date getPuAppDate() {
		return this.puAppDate;
//		return HiltonUtility.ckNull(this.puAppDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.puAppDate);
    }

    public void setPuAppDate(java.util.Date puAppDate) {
        this.puAppDate = puAppDate;
    }

    public java.lang.String getFaId() {
		return (java.lang.String)HiltonUtility.ckNull(this.faId).trim();
    }

    public void setFaId(java.lang.String faId) {
		if (!HiltonUtility.isEmpty(faId) && faId.length() > 15) {
			faId = faId.substring(0, 15);
		}
		this.faId = faId;
    }

    public java.util.Date getFaAppDate() {
		return this.faAppDate;
//		return HiltonUtility.ckNull(this.faAppDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.faAppDate);
    }

    public void setFaAppDate(java.util.Date faAppDate) {
        this.faAppDate = faAppDate;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvHistory) ) return false;
        InvHistory castOther = (InvHistory) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
