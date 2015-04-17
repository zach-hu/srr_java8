package com.tsa.puridiom.otcline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class OtcLineSetupForLookup extends Task
{

	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;
		String userId = (String) incomingRequest.get("userId");

		this.setStatus(Status.COMPLETED);

		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		incomingRequest.put("DisbLine_icDsbLine", ukg.getUniqueKey().toString());
		incomingRequest.put("DisbLine_icLineHistory", ukg.getUniqueKey().toString());

		if(!incomingRequest.containsKey("DisbLine_itemNumber"))
		{
			incomingRequest.put("DisbLine_itemNumber", "");
		}
		if(!incomingRequest.containsKey("DisbLine_itemSource"))
		{
			incomingRequest.put("DisbLine_itemSource", "");
		}
		if(!incomingRequest.containsKey("DisbLine_umCode"))
		{
			incomingRequest.put("DisbLine_umCode", "EA");
		}
		if(!incomingRequest.containsKey("DisbLine_quantity"))
		{
			incomingRequest.put("DisbLine_quantity", "1");
		}
		if(!incomingRequest.containsKey("DisbLine_unitPrice"))
		{
			incomingRequest.put("DisbLine_unitPrice", "0");
		}
		if(!incomingRequest.containsKey("DisbLine_commodityCode"))
		{
			incomingRequest.put("DisbLine_commodityCode", "");
		}
		if(!incomingRequest.containsKey("DisbLine_status"))
		{
			incomingRequest.put("DisbLine_status", "1000");
		}
		if(!incomingRequest.containsKey("DisbLine_commentFlag"))
		{
			incomingRequest.put("DisbLine_commentFlag", "N");
		}
		if(!incomingRequest.containsKey("DisbLine_umFactor"))
		{
			incomingRequest.put("DisbLine_umCode", "1");
		}
		if(!incomingRequest.containsKey("DisbLine_lineTotal"))
		{
			incomingRequest.put("DisbLine_lineTotal", "0");
		}
		if(!incomingRequest.containsKey("DisbLine_shipToFlag"))
		{
			incomingRequest.put("DisbLine_shipToFlag", "N");
		}
		if(!incomingRequest.containsKey("DisbLine_aisle"))
		{
			incomingRequest.put("DisbLine_aisle", "");
		}
		if(!incomingRequest.containsKey("DisbLine_locrow"))
		{
			incomingRequest.put("DisbLine_locrow", "");
		}
		if(!incomingRequest.containsKey("DisbLine_tier"))
		{
			incomingRequest.put("DisbLine_tier", "");
		}
		if(!incomingRequest.containsKey("DisbLine_bin"))
		{
			incomingRequest.put("DisbLine_bin", "");
		}
		if(!incomingRequest.containsKey("DisbLine_vendorId"))
		{
			incomingRequest.put("DisbLine_vendorId", "");
		}
		if(!incomingRequest.containsKey("DisbLine_manufactNo"))
		{
			incomingRequest.put("DisbLine_manufactNo", "");
		}
		if(!incomingRequest.containsKey("DisbLine_lot"))
		{
			incomingRequest.put("DisbLine_lot", "");
		}
		if(!incomingRequest.containsKey("DisbLine_locIc"))
		{
			incomingRequest.put("DisbLine_locIc", "");
		}
		if(!incomingRequest.containsKey("DisbLine_qtyBkord"))
		{
			incomingRequest.put("DisbLine_qtyBkord", "0");
		}
		if(!incomingRequest.containsKey("DisbLine_disbDate"))
		{
			incomingRequest.put("DisbLine_disbDate", Dates.today("", (String) incomingRequest.get("userTimeZone")));
            incomingRequest.put("DisbLine_timeZone", (String) incomingRequest.get("userTimeZone"));
		}
		if(!incomingRequest.containsKey("DisbLine_assetCode"))
		{
			incomingRequest.put("DisbLine_assetCode", "");
		}
		if(!incomingRequest.containsKey("DisbLine_icRc"))
		{
			incomingRequest.put("DisbLine_icRc", "");
		}
		if(!incomingRequest.containsKey("DisbLine_description"))
		{
			incomingRequest.put("DisbLine_description", "");
		}

		// The following should be passed

		DisbHeader disbHeader = (DisbHeader) incomingRequest.get("disbHeader");

		if(disbHeader != null)
		{
			// Defaults from header
			incomingRequest.put("DisbLine_icReqHeader", disbHeader.getIcReqHeader().toString());
			incomingRequest.put("DisbLine_disbNumber", disbHeader.getDisbNumber());
			if(!incomingRequest.containsKey("DisbLine_itemLocation"))
			{
				incomingRequest.put("DisbLine_itemLocation", disbHeader.getItemLocation());
			}
			incomingRequest.put("DisbLine_disbType", disbHeader.getDisbType());
		}

		return null;
	}
}