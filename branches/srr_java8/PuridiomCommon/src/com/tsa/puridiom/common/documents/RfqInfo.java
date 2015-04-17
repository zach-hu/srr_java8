/*
 * Created on January 10, 2007
 */
package com.tsa.puridiom.common.documents;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.math.BigDecimal;

/**
 * @author Kathleen
 */
public class RfqInfo
{
	private BigDecimal icRfqHeader = new BigDecimal(0);
	private String rfqNumber;
	private String rfqAmendment;
	private String rfqDescription;
	private String status;

    /**
     * @return Returns the icRfqHeader.
     */
    public BigDecimal getIcRfqHeader() {
        return (java.math.BigDecimal) HiltonUtility.ckNull(icRfqHeader);
    }
    /**
     * @param icRfqHeader The icRfqHeader to set.
     */
    public void setIcRfqHeader(BigDecimal icRfqHeader) {
        this.icRfqHeader = icRfqHeader;
    }

    /**
     * @return Returns the rfqNumber.
     */
    public String getRfqNumber() {
        return (java.lang.String) HiltonUtility.ckNull(rfqNumber).trim();
    }
    /**
     * @param rfqNumber The rfqNumber to set.
     */
    public void setRfqNumber(String rfqNumber) {
        this.rfqNumber = rfqNumber;
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
     * @return Returns the rfqDescription.
     */
    public String getRfqDescription() {
        return (java.lang.String) HiltonUtility.ckNull(rfqDescription).trim();
    }
    /**
     * @param rfqDescription The rfqDescription to set.
     */
    public void setRfqDescription(String rfqDescription) {
        this.rfqDescription = rfqDescription;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return (java.lang.String) HiltonUtility.ckNull(status).trim();
    }
    /**
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }


	public String toString()
	{
		return "ReferenceInfo - " + this.getRfqNumber() + " [" + String.valueOf(this.getIcRfqHeader()) + "][" + this.getStatus() + "]" ;
	}
}
