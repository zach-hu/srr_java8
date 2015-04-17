package com.tsa.puridiom.entity.sungard;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AccountLocType implements Serializable {

    /** persistent field */
    private String companyCode; 
    
    /** persistent field */
    private String acctNumber;

    /** persistent field */
    private String locationTypeCode;

    /** persistent field */
    private String reqFlag;

    /** full constructor */
    public AccountLocType(java.lang.String companyCode, java.lang.String acctNumber, java.lang.String locationTypeCode, java.lang.String reqFlag) {
        this.companyCode = companyCode;
        this.acctNumber = acctNumber;
        this.locationTypeCode = locationTypeCode;
        this.reqFlag = reqFlag;
    }

    /** default constructor */
    public AccountLocType() {
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
    
    public java.lang.String getLocationTypeCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.locationTypeCode).trim();
    }

    public void setLocationTypeCode(java.lang.String locationTypeCode) {
		if (!HiltonUtility.isEmpty(locationTypeCode) && locationTypeCode.length() > 5) {
		    locationTypeCode = locationTypeCode.substring(0, 5);
		}
		this.locationTypeCode = locationTypeCode;
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
        .append("locationTypeCode", getLocationTypeCode())
        .append("reqFlag", getReqFlag())
        .toString();
    }

}
