/*
 * Created on October 12, 2007
 * package com.tsa.puridiom.historylog.tasks;.HistoryLogVendorText.java
 *
 */
package com.tsa.puridiom.historylog.tasks;

import com.tsa.puridiom.entity.Vendor;

public class HistoryLogVendorText extends HistoryBaseText
{

    public HistoryLogVendorText(Vendor vendor)
    {
        super(vendor);
    }


    public String headerText(String text)
    {
        return super.headerText(text, this.getVendor().getVendorId());
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#createText()
     */
    public String createText()
    {
        return this.headerText("Created Vendor");
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#cancelText()
     */
    public String cancelText()
    {
        return this.headerText("Cancelled Vendor");
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#deleteText()
     */
    public String deleteText()
    {
    	return this.headerText("Deleted Vendor");
    }

    /* (non-Javadoc)
     * @see com.tsa.puridiom.historylog.tasks.IHistoryText#deleteText()
     */
    public String apReferenceText(String vendorId, String apReference)
    {
    	return ("Updated Vendor " + vendorId + " AP Reference #" + apReference);
    }

    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[HistoryLogVendorText:");
        buffer.append(super.toString());
        buffer.append("]");
        return buffer.toString();
    }

}
