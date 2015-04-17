package com.tsa.puridiom.entity.sungard;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Account implements Serializable {

    /** nullable persistent field */
    private String companyCode;

    /** nullable persistent field */
    private String acctNumber;

    /** nullable persistent field */
    private String shortDescription;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String acctgBasisCode;

    /** nullable persistent field */
    private String acctClassInd;

    /** nullable persistent field */
    private String gnLsAcctNumber;

    /** nullable persistent field */
    private String processClassCode;

    /** nullable persistent field */
    private String acctabilityCode;

    /** nullable persistent field */
    private String corpAcctNumber;

    /** nullable persistent field */
    private String amtTypeCode;

    /** nullable persistent field */
    private java.util.Date effStartDate;

    /** nullable persistent field */
    private java.util.Date effEndDate;

    /** full constructor */
    public Account(java.lang.String companyCode, java.lang.String acctNumber, java.lang.String shortDescription, java.lang.String description, java.lang.String acctgBasisCode, java.lang.String acctClassInd, java.lang.String gnLsAcctNumber, java.lang.String processClassCode, java.lang.String acctabilityCode, java.lang.String corpAcctNumber, java.lang.String amtTypeCode, java.util.Date effStartDate, java.util.Date effEndDate) {
        this.companyCode = companyCode;
        this.acctNumber = acctNumber;
        this.shortDescription = shortDescription;
        this.description = description;
        this.acctgBasisCode = acctgBasisCode;
        this.acctClassInd = acctClassInd;
        this.gnLsAcctNumber = gnLsAcctNumber;
        this.processClassCode = processClassCode;
        this.acctabilityCode = acctabilityCode;
        this.corpAcctNumber = corpAcctNumber;
        this.amtTypeCode = amtTypeCode;
        this.effStartDate = effStartDate;
        this.effEndDate = effEndDate;
    }

    /** default constructor */
    public Account() {
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

    public java.lang.String getAcctNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.acctNumber).trim();
    }

    public void setAcctNumber(java.lang.String acctNumber) {
		if (!HiltonUtility.isEmpty(acctNumber) && acctNumber.length() > 15) {
			acctNumber = acctNumber.substring(0, 15);
		}
		this.acctNumber = acctNumber;
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

    public java.lang.String getAcctgBasisCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.acctgBasisCode).trim();
    }

    public void setAcctgBasisCode(java.lang.String acctgBasisCode) {
		if (!HiltonUtility.isEmpty(acctgBasisCode) && acctgBasisCode.length() > 5) {
			acctgBasisCode = acctgBasisCode.substring(0, 5);
		}
		this.acctgBasisCode = acctgBasisCode;
    }

    public java.lang.String getAcctClassInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.acctClassInd).trim();
    }

    public void setAcctClassInd(java.lang.String acctClassInd) {
		if (!HiltonUtility.isEmpty(acctClassInd) && acctClassInd.length() > 1) {
			acctClassInd = acctClassInd.substring(0, 1);
		}
		this.acctClassInd = acctClassInd;
    }

    public java.lang.String getGnLsAcctNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.gnLsAcctNumber).trim();
    }

    public void setGnLsAcctNumber(java.lang.String gnLsAcctNumber) {
		if (!HiltonUtility.isEmpty(gnLsAcctNumber) && gnLsAcctNumber.length() > 15) {
			gnLsAcctNumber = gnLsAcctNumber.substring(0, 15);
		}
		this.gnLsAcctNumber = gnLsAcctNumber;
    }

    public java.lang.String getProcessClassCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.processClassCode).trim();
    }

    public void setProcessClassCode(java.lang.String processClassCode) {
		if (!HiltonUtility.isEmpty(processClassCode) && processClassCode.length() > 5) {
			processClassCode = processClassCode.substring(0, 5);
		}
		this.processClassCode = processClassCode;
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

    public java.lang.String getCorpAcctNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.corpAcctNumber).trim();
    }

    public void setCorpAcctNumber(java.lang.String corpAcctNumber) {
		if (!HiltonUtility.isEmpty(corpAcctNumber) && corpAcctNumber.length() > 15) {
			corpAcctNumber = corpAcctNumber.substring(0, 15);
		}
		this.corpAcctNumber = corpAcctNumber;
    }

    public java.lang.String getAmtTypeCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.amtTypeCode).trim();
    }

    public void setAmtTypeCode(java.lang.String amtTypeCode) {
		if (!HiltonUtility.isEmpty(amtTypeCode) && amtTypeCode.length() > 5) {
			amtTypeCode = amtTypeCode.substring(0, 5);
		}
		this.amtTypeCode = amtTypeCode;
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

    public String toString() {
        return new ToStringBuilder(this)
        .append("companyCode", getCompanyCode())
        .toString();
    }

}
