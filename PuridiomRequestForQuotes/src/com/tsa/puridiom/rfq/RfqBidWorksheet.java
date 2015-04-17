/*
 * Created on Dec 7, 2005
 */
package com.tsa.puridiom.rfq;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.common.utility.HiltonUtility;
import java.util.List;

/**
 * @author kelli
 */
public class RfqBidWorksheet {
    private RfqHeader rfqHeader;
    private List rfqVendorGroupList;
    private List rfqLineGroupList; //List of RfqLines (each containing a list of rfqBidGroupList (3 or less)
    private List rfqQuestionGroupList;

    /**
     * @return Returns the rfqHeader.
     */
    public RfqHeader getRfqHeader() {
        if (this.rfqHeader == null) {
            setRfqHeader(new RfqHeader());
        }
        return this.rfqHeader;
    }
    /**
     * @param rfqHeader The rfqHeader to set.
     */
    public void setRfqHeader(RfqHeader rfqHeader) {
        this.rfqHeader = rfqHeader;
    }
    /**
     * @return Returns the rfqLineList.
     */
    public List getRfqLineGroupList() {
        return HiltonUtility.ckNull(this.rfqLineGroupList);
    }
    /**
     * @param rfqLineList The rfqLineGroupList to set.
     */
    public void setRfqLineGroupList(List rfqLineGroupList) {
        this.rfqLineGroupList = rfqLineGroupList;
    }
    /**
     * @return Returns the rfqVendorGroupList.
     */
    public List getRfqVendorGroupList() {
        return HiltonUtility.ckNull(this.rfqVendorGroupList);
    }
    /**
     * @param rfqVendorGroupList The rfqVendorGroupList to set.
     */
    public void setRfqVendorGroupList(List rfqVendorGroupList) {
        this.rfqVendorGroupList = rfqVendorGroupList;
    }
	/**
	 * @return the rfqQuestionGroupList
	 */
	public List getRfqQuestionGroupList()
	{
		return HiltonUtility.ckNull(this.rfqQuestionGroupList);
	}
	/**
	 * @param rfqQuestionGroupList the rfqQuestionGroupList to set
	 */
	public void setRfqQuestionGroupList(List rfqQuestionGroupList)
	{
		this.rfqQuestionGroupList = rfqQuestionGroupList;
	}
}
