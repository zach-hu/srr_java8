package com.tsa.puridiom.entity.sungard;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BstrnHeader implements Serializable {

    /** persistent field */
    private java.math.BigDecimal tranId;

    /** persistent field */
    private String sourceCode;

    /** persistent field */
    private java.util.Date effectiveDate;

    /** persistent field */
    private String referenceData;

    /** persistent field */
    private String statusCode;

    /** persistent field */
    private String approvalId;

    /** persistent field */
    private String tranHdrDesc;

    /** full constructor */
    public BstrnHeader(java.math.BigDecimal tranId, java.lang.String sourceCode, java.util.Date effectiveDate, java.lang.String referenceData, java.lang.String statusCode, java.lang.String approvalId, java.lang.String tranHdrDesc) {
        this.tranId = tranId;
        this.sourceCode = sourceCode;
        this.effectiveDate = effectiveDate;
        this.referenceData = referenceData;
        this.statusCode = statusCode;
        this.approvalId = approvalId;
        this.tranHdrDesc = tranHdrDesc;
    }

    /** default constructor */
    public BstrnHeader() {
    }

    public java.math.BigDecimal getTranId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.tranId);
    }

    public void setTranId(java.math.BigDecimal tranId) {
        this.tranId = tranId;
    }

    public java.lang.String getSourceCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.sourceCode).trim();
    }

    public void setSourceCode(java.lang.String sourceCode) {
		if (!HiltonUtility.isEmpty(sourceCode) && sourceCode.length() > 5) {
			sourceCode = sourceCode.substring(0, 5);
		}
		this.sourceCode = sourceCode;
    }

    public java.util.Date getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setEffectiveDate(java.util.Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public java.lang.String getReferenceData() {
		return (java.lang.String)HiltonUtility.ckNull(this.referenceData).trim();
    }

    public void setReferenceData(java.lang.String referenceData) {
		if (!HiltonUtility.isEmpty(referenceData) && referenceData.length() > 20) {
			referenceData = referenceData.substring(0, 20);
		}
		this.referenceData = referenceData;
    }

    public java.lang.String getStatusCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.statusCode).trim();
    }

    public void setStatusCode(java.lang.String statusCode) {
		if (!HiltonUtility.isEmpty(statusCode) && statusCode.length() > 1) {
			statusCode = statusCode.substring(0, 1);
		}
		this.statusCode = statusCode;
    }

    public java.lang.String getApprovalId() {
		return (java.lang.String)HiltonUtility.ckNull(this.approvalId).trim();
    }

    public void setApprovalId(java.lang.String approvalId) {
		if (!HiltonUtility.isEmpty(approvalId) && approvalId.length() > 8) {
			approvalId = approvalId.substring(0, 8);
		}
		this.approvalId = approvalId;
    }

    public java.lang.String getTranHdrDesc() {
		return (java.lang.String)HiltonUtility.ckNull(this.tranHdrDesc).trim();
    }

    public void setTranHdrDesc(java.lang.String tranHdrDesc) {
		if (!HiltonUtility.isEmpty(tranHdrDesc) && tranHdrDesc.length() > 50) {
			tranHdrDesc = tranHdrDesc.substring(0, 50);
		}
		this.tranHdrDesc = tranHdrDesc;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .toString();
    }

}
