package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RequisitionLineLookupSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		String	icHeader = (String) incomingRequest.get("icHeader");
		ItemLookup item = (ItemLookup) incomingRequest.get("itemLookup");
		if (Utility.isEmpty(icHeader)) {
			throw new Exception ("icHeader was not specified.");
		}
		else {
			incomingRequest.put("RequisitionLine_icReqHeader", icHeader);
			incomingRequest.put("RequisitionHeader_icReqHeader", icHeader);
		}

		if (!incomingRequest.containsKey("RequisitionLine_icReqLine")) {
			incomingRequest.put("RequisitionLine_icReqLine", incomingRequest.get("icLine"));
		}

		if (item != null)
			incomingRequest.put("Vendor_vendorId", item.getVendorId());
		else
			incomingRequest.put("Vendor_vendorId", "");

		this.status = Status.SUCCEEDED;
		return result;
	}
}