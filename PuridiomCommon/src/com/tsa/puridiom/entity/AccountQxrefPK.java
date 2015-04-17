package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AccountQxrefPK implements Serializable {

    /** identifier field */
    private java.lang.String location;

    /** identifier field */
    private java.lang.String afe;

    /** full constructor */
    public AccountQxrefPK(java.lang.String location, java.lang.String afe) {
        this.location = location;
        this.afe = afe;
    }

    /** default constructor */
    public AccountQxrefPK() {
    }

    public java.lang.String getLocation() {
		return (java.lang.String)HiltonUtility.ckNull(this.location).trim();
    }

    public void setLocation(java.lang.String location) {
		if (!HiltonUtility.isEmpty(location) && location.length() > 4) {
			location = location.substring(0, 4);
		}
		this.location = location;
    }

    public java.lang.String getAfe() {
		return (java.lang.String)HiltonUtility.ckNull(this.afe).trim();
    }

    public void setAfe(java.lang.String afe) {
		if (!HiltonUtility.isEmpty(afe) && afe.length() > 15) {
			afe = afe.substring(0, 15);
		}
		this.afe = afe;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("location", getLocation())
            .append("afe", getAfe())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AccountQxrefPK) ) return false;
        AccountQxrefPK castOther = (AccountQxrefPK) other;
        return new EqualsBuilder()
            .append(this.getLocation(), castOther.getLocation())
            .append(this.getAfe(), castOther.getAfe())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getLocation())
            .append(getAfe())
            .toHashCode();
    }

}
