package com.tsa.puridiom.disbline.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class DisbLineSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

			incomingRequest.put("DisbLine_icDsbLine", "0");
			incomingRequest.put("DisbLine_lineNumber", "0");
			incomingRequest.put("DisbLine_disbNumber", "");
			incomingRequest.put("DisbLine_icDsbHeader", "0");
			incomingRequest.put("DisbLine_itemNumber", "");
			incomingRequest.put("DisbLine_itemSource", "");
			incomingRequest.put("DisbLine_umCode", "");
			incomingRequest.put("DisbLine_quantity", "0");
			incomingRequest.put("DisbLine_unitPrice", "0");
			incomingRequest.put("DisbLine_commodityCode", "");
			incomingRequest.put("DisbLine_icReqLine", "0");
			incomingRequest.put("DisbLine_status", "");
			incomingRequest.put("DisbLine_commentFlag", "");
			incomingRequest.put("DisbLine_itemLocation", "");
			incomingRequest.put("DisbLine_icDsbAccount", "0");
			incomingRequest.put("DisbLine_umFactor", "0");
			incomingRequest.put("DisbLine_lineTotal", "0");
			incomingRequest.put("DisbLine_shiptoFlag", "");
			incomingRequest.put("DisbLine_aisle", "");
			incomingRequest.put("DisbLine_locrow", "");
			incomingRequest.put("DisbLine_tier", "");
			incomingRequest.put("DisbLine_bin", "");
			incomingRequest.put("DisbLine_vendorId", "");
			incomingRequest.put("DisbLine_manufactNo", "");
			incomingRequest.put("DisbLine_lot", "");
			incomingRequest.put("DisbLine_locIc", "");
			incomingRequest.put("DisbLine_qtyBkord", "0");
			incomingRequest.put("DisbLine_disbDate", Dates.today("", userTimeZone));
			incomingRequest.put("DisbLine_assetCode", "");
			incomingRequest.put("DisbLine_icLineHistory", "0");
			incomingRequest.put("DisbLine_description", "");
			incomingRequest.put("DisbLine_icAccount", "0");
            incomingRequest.put("DisbLine_timeZone", userTimeZone);
			incomingRequest.put("DisbLine_duomUmCode", "");
			incomingRequest.put("DisbLine_duomQuantity", "0");

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}