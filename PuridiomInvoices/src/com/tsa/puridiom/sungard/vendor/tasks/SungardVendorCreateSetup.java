package com.tsa.puridiom.sungard.vendor.tasks;

import com.tsa.puridiom.entity.InvoiceVendor;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class SungardVendorCreateSetup extends Task {

	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			InvoiceVendor invoiceVendor = (InvoiceVendor) incomingRequest.get("invoiceVendor");
			InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			String	internalVendorId = (String) incomingRequest.get("internalVendorId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
			String	tin = invoiceHeader.getFederalId();

			if (Utility.isEmpty(tin)) {
			    tin = "1111111111";
			}

			incomingRequest.put("SungardVendor_vendorId", invoiceVendor.getVendorId());
			incomingRequest.put("SungardVendor_internalVendorId", internalVendorId);
			incomingRequest.put("SungardVendor_vendorName", invoiceVendor.getVendorName());
			incomingRequest.put("SungardVendor_statusInd", invoiceVendor.getStatus());
			incomingRequest.put("SungardVendor_tin", tin);
			incomingRequest.put("SungardVendor_discPct", String.valueOf(invoiceHeader.getTermsDiscperc()));
			incomingRequest.put("SungardVendor_discountDays", String.valueOf(invoiceHeader.getTermsDiscdays()));
			incomingRequest.put("SungardVendor_vendorStartDate", Dates.today("MM-dd-yyyy", userTimeZone));
			incomingRequest.put("SungardVendor_vendorEndDate", "12-31-2400");
			incomingRequest.put("SungardVendor_netDueDays", String.valueOf(invoiceHeader.getTermsNetdays()));

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}