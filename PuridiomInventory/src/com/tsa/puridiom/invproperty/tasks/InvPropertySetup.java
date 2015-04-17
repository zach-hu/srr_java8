package com.tsa.puridiom.invproperty.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InvPropertySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InvProperty_icProperty", "0");
			incomingRequest.put("InvProperty_propertyType", "");
			incomingRequest.put("InvProperty_tagNumber", "");
			incomingRequest.put("InvProperty_icRc", "0");
			incomingRequest.put("InvProperty_itemNumber", "");
			incomingRequest.put("InvProperty_serialNumber", "");
			incomingRequest.put("InvProperty_poNumber", "");
			incomingRequest.put("InvProperty_contract", "");
			incomingRequest.put("InvProperty_receiptNumber", "");
			incomingRequest.put("InvProperty_shipNumber", "");
			incomingRequest.put("InvProperty_remarks", "");
			incomingRequest.put("InvProperty_dateIn", Dates.today("", ""));
			incomingRequest.put("InvProperty_dateOut", "");
			incomingRequest.put("InvProperty_status", "");
			incomingRequest.put("InvProperty_owner", "");
			incomingRequest.put("InvProperty_cblOutNumber", "");
			incomingRequest.put("InvProperty_armyNumber", "");
			incomingRequest.put("InvProperty_icRecLine", "0");
			incomingRequest.put("InvProperty_icPoLine", "0");
			incomingRequest.put("InvProperty_assetNumber", "");
			incomingRequest.put("InvProperty_source", "INV");
			incomingRequest.put("InvProperty_icDsbLine", "0");

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