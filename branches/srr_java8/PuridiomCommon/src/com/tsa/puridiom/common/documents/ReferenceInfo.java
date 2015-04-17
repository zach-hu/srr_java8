/*
 * Created on November 1, 2006
 */
package com.tsa.puridiom.common.documents;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.math.BigDecimal;

/**
 * @author Kelli
 */
public class ReferenceInfo
{
    private String formType;
    private String formNumber;
    private String requisitionerCode;
    private String buyerCode;
    private String rfqAmendment;
    private BigDecimal releaseNumber;
    private BigDecimal icHeader = new BigDecimal(0);
	private BigDecimal icLine = new BigDecimal(0);

    /**
     * @return Returns the formType.
     */
    public String getFormType() {
        return (java.lang.String) HiltonUtility.ckNull(formType).trim();
    }
    /**
     * @param formType The formType to set.
     */
    public void setFormType(String formType) {
        this.formType = formType;
    }
    /**
     * @return Returns the formNumber.
     */
    public String getFormNumber() {
        return (java.lang.String) HiltonUtility.ckNull(formNumber).trim();
    }
    /**
     * @param formNumber The formNumber to set.
     */
    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }
    /**
     * @return Returns the requisitionerCode.
     */
    public String getRequisitionerCode() {
        return (java.lang.String) HiltonUtility.ckNull(requisitionerCode).trim();
    }
    /**
     * @param requisitionerCode The requisitionerCode to set.
     */
    public void setRequisitionerCode(String requisitionerCode) {
        this.requisitionerCode = requisitionerCode;
    }
    /**
     * @return Returns the buyerCode.
     */
    public String getBuyerCode() {
        return (java.lang.String) HiltonUtility.ckNull(buyerCode).trim();
    }
    /**
     * @param buyerCode The buyerCode to set.
     */
    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }
    /**
     * @return Returns the rfqAmendment.
     */
    public String getRfqAmendment() {
        return (java.lang.String) HiltonUtility.ckNull(rfqAmendment).trim();
    }
    /**
     * @param rfqAmendment The rfqAmendment to set.
     */
    public void setRfqAmendment(String rfqAmendment) {
        this.rfqAmendment = rfqAmendment;
    }
    /**
     * @return Returns the releaseNumber.
     */
    public BigDecimal getReleaseNumber() {
        return (java.math.BigDecimal) HiltonUtility.ckNull(releaseNumber);
    }
    /**
     * @param releaseNumber The releaseNumber to set.
     */
    public void setReleaseNumber(BigDecimal releaseNumber) {
        this.releaseNumber = releaseNumber;
    }
    /**
     * @return Returns the icHeader.
     */
    public BigDecimal getIcHeader() {
        return (java.math.BigDecimal) HiltonUtility.ckNull(icHeader);
    }
    /**
     * @param icHeader The icHeader to set.
     */
    public void setIcHeader(BigDecimal icHeader) {
        this.icHeader = icHeader;
    }
    /**
     * @return Returns the icLine.
     */
    public BigDecimal getIcLine() {
        return (java.math.BigDecimal) HiltonUtility.ckNull(icLine);
    }
    /**
     * @param icLine The icLine to set.
     */
    public void setIcLine(BigDecimal icLine) {
        this.icLine = icLine;
    }

	public String toString()
	{
		return "ReferenceInfo - " + this.getFormType() + " - " + this.getFormNumber() + " [" + String.valueOf(this.getIcHeader()) + "][" + String.valueOf(this.getIcLine()) + "]" ;
	}
}
