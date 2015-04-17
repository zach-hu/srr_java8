package com.tsa.puridiom.entity;

import java.io.Serializable;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InsCategoryLevel extends Entity implements Serializable
{
	/** identifier field */
	private java.math.BigDecimal icIcl;

	/** identifier field */
	private java.math.BigDecimal iclLevel;

	/** nullable persistent field */
	private String iclDescription;

	/** nullable persistent field */
	private String iclRequired1;

	/** nullable persistent field */
	private java.math.BigDecimal iclMinimum1;

	/** nullable persistent field */
	private String iclRequired2;

	/** nullable persistent field */
	private java.math.BigDecimal iclMinimum2;

	/** nullable persistent field */
	private String iclRequired3;

	/** nullable persistent field */
	private java.math.BigDecimal iclMinimum3;

	/** nullable persistent field */
	private String iclRequired4;

	/** nullable persistent field */
	private java.math.BigDecimal iclMinimum4;

	/** nullable persistent field */
	private String iclRequired5;

	/** nullable persistent field */
	private java.math.BigDecimal iclMinimum5;

	/** nullable persistent field */
	private String iclRequired6;

	/** nullable persistent field */
	private java.math.BigDecimal iclMinimum6;

	/** nullable persistent field */
	private String status;

	/** nullable persistent field */
	private String owner;

	/** nullable persistent field */
	private java.util.Date dateEntered;

	/** nullable persistent field */
	private java.util.Date dateExpires;

	/** nullable persistent field */
	private String lastChgBy;

	/** nullable persistent field */
	private java.util.Date lastChgDate;

	/** full constructor */
	public InsCategoryLevel(java.math.BigDecimal icIcl, java.math.BigDecimal iclLevel, String iclDescription, String iclRequired1, java.math.BigDecimal iclMinimum1, String iclRequired2, java.math.BigDecimal iclMinimum2, String iclRequired3, java.math.BigDecimal iclMinimum3, String iclRequired4, java.math.BigDecimal iclMinimum4, String iclRequired5, java.math.BigDecimal iclMinimum5, String iclRequired6, java.math.BigDecimal iclMinimum6, String status, String owner, java.util.Date dateEntered, java.util.Date dateExpires, String lastChgBy, java.util.Date lastChgDate)
	{
		this.icIcl = icIcl;
		this.iclLevel = iclLevel;
		this.iclDescription = iclDescription;
		this.iclRequired1 = iclRequired1;
		this.iclMinimum1 = iclMinimum1;
		this.iclRequired2 = iclRequired2;
		this.iclMinimum2 = iclMinimum2;
		this.iclRequired3 = iclRequired3;
		this.iclMinimum3 = iclMinimum3;
		this.iclRequired4 = iclRequired4;
		this.iclMinimum4 = iclMinimum4;
		this.iclRequired5 = iclRequired5;
		this.iclMinimum5 = iclMinimum5;
		this.iclRequired6 = iclRequired6;
		this.iclMinimum6 = iclMinimum6;
		this.status = status;
		this.owner = owner;
		this.dateEntered = dateEntered;
		this.dateExpires = dateExpires;
		this.lastChgBy = lastChgBy;
		this.lastChgDate = lastChgDate;
	}

	/** default constructor */
	public InsCategoryLevel() {
	}

	/** minimal constructor */
	public InsCategoryLevel(java.math.BigDecimal icIcl) {
		this.icIcl = icIcl;
	}

	public java.math.BigDecimal getIcIcl() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icIcl);
	}

	public void setIcIcl(java.math.BigDecimal icIcl) {
		this.icIcl = icIcl;
	}

	public java.math.BigDecimal getIclLevel() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.iclLevel);
	}

	public void setIclLevel(java.math.BigDecimal iclLevel) {
		this.iclLevel = iclLevel;
	}

	public java.lang.String getIclDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.iclDescription).trim();
	}

	public void setIclDescription(java.lang.String iclDescription) {
		if (!HiltonUtility.isEmpty(iclDescription) && iclDescription.length() > 90) {
			iclDescription = iclDescription.substring(0, 90);
		}
		this.iclDescription = iclDescription;
	}

	public java.lang.String getIclRequired1() {
		return (java.lang.String)HiltonUtility.ckNull(this.iclRequired1).trim();
	}

	public void setIclRequired1(java.lang.String iclRequired1) {
		if (!HiltonUtility.isEmpty(iclRequired1) && iclRequired1.length() > 1) {
			iclRequired1 = iclRequired1.substring(0, 1);
		}
		this.iclRequired1 = iclRequired1;
	}

	public java.math.BigDecimal getIclMinimum1() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.iclMinimum1);
	}

	public void setIclMinimum1(java.math.BigDecimal iclMinimum1) {
		this.iclMinimum1 = iclMinimum1;
	}

	public java.lang.String getIclRequired2() {
		return (java.lang.String)HiltonUtility.ckNull(this.iclRequired2).trim();
	}

	public void setIclRequired2(java.lang.String iclRequired2) {
		if (!HiltonUtility.isEmpty(iclRequired2) && iclRequired2.length() > 1) {
			iclRequired2 = iclRequired2.substring(0, 1);
		}
		this.iclRequired2 = iclRequired2;
	}

	public java.math.BigDecimal getIclMinimum2() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.iclMinimum2);
	}

	public void setIclMinimum2(java.math.BigDecimal iclMinimum2) {
		this.iclMinimum2 = iclMinimum2;
	}

	public java.lang.String getIclRequired3() {
		return (java.lang.String)HiltonUtility.ckNull(this.iclRequired3).trim();
	}

	public void setIclRequired3(java.lang.String iclRequired3) {
		if (!HiltonUtility.isEmpty(iclRequired3) && iclRequired3.length() > 1) {
			iclRequired3 = iclRequired3.substring(0, 1);
		}
		this.iclRequired3 = iclRequired3;
	}

	public java.math.BigDecimal getIclMinimum3() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.iclMinimum3);
	}

	public void setIclMinimum3(java.math.BigDecimal iclMinimum3) {
		this.iclMinimum3 = iclMinimum3;
	}

	public java.lang.String getIclRequired4() {
		return (java.lang.String)HiltonUtility.ckNull(this.iclRequired4).trim();
	}

	public void setIclRequired4(java.lang.String iclRequired4) {
		if (!HiltonUtility.isEmpty(iclRequired4) && iclRequired4.length() > 1) {
			iclRequired4 = iclRequired4.substring(0, 1);
		}
		this.iclRequired4 = iclRequired4;
	}

	public java.math.BigDecimal getIclMinimum4() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.iclMinimum4);
	}

	public void setIclMinimum4(java.math.BigDecimal iclMinimum4) {
		this.iclMinimum4 = iclMinimum4;
	}

	public java.lang.String getIclRequired5() {
		return (java.lang.String)HiltonUtility.ckNull(this.iclRequired5).trim();
	}

	public void setIclRequired5(java.lang.String iclRequired5) {
		if (!HiltonUtility.isEmpty(iclRequired5) && iclRequired5.length() > 1) {
			iclRequired5 = iclRequired5.substring(0, 1);
		}
		this.iclRequired5 = iclRequired5;
	}

	public java.math.BigDecimal getIclMinimum5() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.iclMinimum5);
	}

	public void setIclMinimum5(java.math.BigDecimal iclMinimum5) {
		this.iclMinimum5 = iclMinimum5;
	}

	public java.lang.String getIclRequired6() {
		return (java.lang.String)HiltonUtility.ckNull(this.iclRequired6).trim();
	}

	public void setIclRequired6(java.lang.String iclRequired6) {
		if (!HiltonUtility.isEmpty(iclRequired6) && iclRequired6.length() > 1) {
			iclRequired6 = iclRequired6.substring(0, 1);
		}
		this.iclRequired6 = iclRequired6;
	}

	public java.math.BigDecimal getIclMinimum6() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.iclMinimum6);
	}

	public void setIclMinimum6(java.math.BigDecimal iclMinimum6) {
		this.iclMinimum6 = iclMinimum6;
	}

	public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
	}

	public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 2) {
			status = status.substring(0, 2);
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

	public java.util.Date getDateEntered() {
		return this.dateEntered;
	}

	public void setDateEntered(java.util.Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public java.util.Date getDateExpires() {
		return this.dateExpires;
	}

	public void setDateExpires(java.util.Date dateExpires) {
		this.dateExpires = dateExpires;
	}

	public java.lang.String getLastChgBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChgBy).trim();
	}

	public void setLastChgBy(java.lang.String lastChgBy) {
		if (!HiltonUtility.isEmpty(lastChgBy) && lastChgBy.length() > 15) {
			lastChgBy = lastChgBy.substring(0, 15);
		}
		this.lastChgBy = lastChgBy;
	}

	public java.util.Date getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(java.util.Date lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public String toString() {
		return new ToStringBuilder(this).append("icIcl", getIcIcl()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof InsCategoryLevel))
			return false;
		InsCategoryLevel castOther = (InsCategoryLevel) other;
		return new EqualsBuilder().append(this.getIcIcl(), castOther.getIcIcl()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getIcIcl()).toHashCode();
	}
}
