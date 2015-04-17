package com.tsa.puridiom.entity.sungard;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AccountBuType implements Serializable {

    /** persistent field */
    private String companyCode; 
    
    /** persistent field */
    private String acctNumber;

    /** persistent field */
    private String buTypeCode;

    /** persistent field */
    private String reqFlag;

    /** full constructor */
    public AccountBuType(java.lang.String companyCode, java.lang.String acctNumber, java.lang.String buTypeCode, java.lang.String reqFlag) {
        this.companyCode = companyCode;
        this.acctNumber = acctNumber;
        this.buTypeCode = buTypeCode;
        this.reqFlag = reqFlag;
    }

    /** default constructor */
    public AccountBuType() {
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
		    acctNumber = acctNumber.substring(0, 5);
		}
		this.acctNumber = acctNumber;
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
    
    public java.lang.String getReqFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.reqFlag).trim();
    }

    public void setReqFlag(java.lang.String reqFlag) {
		if (!HiltonUtility.isEmpty(reqFlag) && reqFlag.length() > 1) {
		    reqFlag = reqFlag.substring(0, 1);
		}
		this.reqFlag = reqFlag;
    }

    public String toString() {
        return new ToStringBuilder(this)
        .append("companyCode", getCompanyCode())
        .append("acctNumber", getAcctNumber())
        .append("buTypeCode", getBuTypeCode())
        .append("reqFlag", getReqFlag())
        .toString();
    }

}
