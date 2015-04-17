package com.tsa.puridiom.entity.sungard;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BusinessUnit implements Serializable {

    /** persistent field */
    private String buCode;

    /** persistent field */
    private String buTypeCode;

    /** persistent field */
    private String shortDescription;

    /** persistent field */
    private String description;

    /** persistent field */
    private java.util.Date effStartDate;

    /** persistent field */
    private java.util.Date effEndDate;

    /** persistent field */
    private String acctabilityCode;

    /** persistent field */
    private String companyCode;

    /** full constructor */
    public BusinessUnit(java.lang.String buCode, java.lang.String buTypeCode, java.lang.String shortDescription, java.lang.String description, java.util.Date effStartDate, java.util.Date effEndDate, java.lang.String acctabilityCode, java.lang.String companyCode) {
        this.buCode = buCode;
        this.buTypeCode = buTypeCode;
        this.shortDescription = shortDescription;
        this.description = description;
        this.effStartDate = effStartDate;
        this.effEndDate = effEndDate;
        this.acctabilityCode = acctabilityCode;
        this.companyCode = companyCode;
    }

    /** default constructor */
    public BusinessUnit() {
    }

    public java.lang.String getBuCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.buCode).trim();
    }

    public void setBuCode(java.lang.String buCode) {
		if (!HiltonUtility.isEmpty(buCode) && buCode.length() > 15) {
			buCode = buCode.substring(0, 15);
		}
		this.buCode = buCode;
    }

    public java.lang.String getBuTypeCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.buTypeCode).trim();
    }

    public void setBuTypeCode(java.lang.String buTypeCode) {
		if (!HiltonUtility.isEmpty(buTypeCode) && buTypeCode.length() > 5) {
			buTypeCode = buTypeCode.substring(0, 5);
		}
		this.buTypeCode = buTypeCode;
    }

    public java.lang.String getShortDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.shortDescription).trim();
    }

    public void setShortDescription(java.lang.String shortDescription) {
		if (!HiltonUtility.isEmpty(shortDescription) && shortDescription.length() > 20) {
			shortDescription = shortDescription.substring(0, 20);
		}
		this.shortDescription = shortDescription;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 50) {
			description = description.substring(0, 50);
		}
		this.description = description;
    }

    public java.util.Date getEffStartDate() {
        return this.effStartDate;
    }

    public void setEffStartDate(java.util.Date effStartDate) {
        this.effStartDate = effStartDate;
    }

    public java.util.Date getEffEndDate() {
        return this.effEndDate;
    }

    public void setEffEndDate(java.util.Date effEndDate) {
        this.effEndDate = effEndDate;
    }

    public java.lang.String getAcctabilityCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.acctabilityCode).trim();
    }

    public void setAcctabilityCode(java.lang.String acctabilityCode) {
		if (!HiltonUtility.isEmpty(acctabilityCode) && acctabilityCode.length() > 5) {
			acctabilityCode = acctabilityCode.substring(0, 5);
		}
		this.acctabilityCode = acctabilityCode;
    }

    public java.lang.String getCompanyCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.companyCode).trim();
    }

    public void setCompanyCode(java.lang.String companyCode) {
		if (!HiltonUtility.isEmpty(companyCode) && companyCode.length() > 5) {
			companyCode = companyCode.substring(0, 5);
		}
		this.companyCode = companyCode;
    }

    public String toString() {
        return new ToStringBuilder(this)
        .append("buCode", getBuCode())
        .append("buTypeCode", getBuTypeCode())
        .toString();
    }

}
