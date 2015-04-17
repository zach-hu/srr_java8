/*
 * Created on October 12, 2007
 * package com.tsa.puridiom.historylog.tasks.VendorSetupValues.java
 *
 */
package com.tsa.puridiom.historylog.tasks;

import java.math.BigDecimal;

import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.Vendor;

public class VendorSetupValues extends HistoryLogSetupValues
{
    private String reason = "";

	/**
     * @param newVendor
     * @return HistoryLog
     */
    public HistoryLog getVendorHistoryLog(Vendor newVendor)
    {
        HistoryLog history = null;

        history = this.getHistoryRecord(newVendor);

        return history;
    }

    public HistoryLog setText(Vendor vendor, HistoryLog history)
    {
        HistoryLogVendorText text = new HistoryLogVendorText(vendor);
        //String status = vendor.getStatus();

        text.setOrganizationId(this.getOrganizationId());

        history.setDescription(text.apReferenceText(vendor.getVendorId(), vendor.getApReference()));

        /*
        if (status.equals(DocumentStatus.IVC_INPROGRESS))
        {
            history.setDescription(text.createText());
        }
        else if(status.equals(DocumentStatus.IVC_APPROVED))
        {
            history.setDescription(text.approveText());
        }
        else if(status.equals(DocumentStatus.CANCELLED))
        {
            history.setDescription(text.cancelText());
        }
        else
        {
            history = null;
        }
        */
        return history;
    }

    /**
     * @param status
     */
    private HistoryLog getHistoryRecord(Vendor vendor)
    {
        HistoryLog history = this.vendorSetup(null, vendor);
        history = this.setText(vendor, history);

        return history;
    }


    public HistoryLog vendorSetup(HistoryLog history, Vendor vendor)
    {
        if (history == null)
        {
        	history = this.setUpHistory(history);
        }
        history.setIcHeaderHistory(vendor.getIcHistory());
        history.setIcLineHistory(new BigDecimal("0"));
        history.setIcHeader(new BigDecimal("0"));
        history.setIcLine(new BigDecimal("0"));
 	    history.setDoctype("VND");
 	    history.setStatus(vendor.getStatus());
 	    history.setUserid(this.getUserId());

 	   return history;
    }

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
