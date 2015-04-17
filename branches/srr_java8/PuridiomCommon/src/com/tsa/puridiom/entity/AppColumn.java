package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AppColumn implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.AppColumnPK comp_id;

    /** nullable persistent field */
    private String coltype;
    private String coldesc;

    /** full constructor */
    public AppColumn(com.tsa.puridiom.entity.AppColumnPK comp_id, java.lang.String coltype, String coldesc) {
        this.comp_id = comp_id;
        this.coltype = coltype;
        this.coldesc = coldesc;
    }

    /** default constructor */
    public AppColumn() {
    }

    /** minimal constructor */
    public AppColumn(com.tsa.puridiom.entity.AppColumnPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.AppColumnPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.AppColumnPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getColtype() {
		return (java.lang.String)HiltonUtility.ckNull(this.coltype).trim();
    }

    public java.lang.String getColdesc() {
		return (java.lang.String)HiltonUtility.ckNull(this.coldesc).trim();
    }

    public void setColtype(java.lang.String coltype) {
		if (!HiltonUtility.isEmpty(coltype) && coltype.length() > 25) {
			coltype = coltype.substring(0, 25);
		}
		this.coltype = coltype;
    }

    public void setColdesc(java.lang.String coltype) {
		if (!HiltonUtility.isEmpty(coldesc) && coldesc.length() > 40) {
			coldesc = coldesc.substring(0, 40);
		}
		this.coldesc = coldesc;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AppColumn) ) return false;
        AppColumn castOther = (AppColumn) other;
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
