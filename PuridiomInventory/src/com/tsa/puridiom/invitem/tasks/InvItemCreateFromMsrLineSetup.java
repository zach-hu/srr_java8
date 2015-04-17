package com.tsa.puridiom.invitem.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;

import java.util.Map;

public class InvItemCreateFromMsrLineSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			RequisitionLine requisitionLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			String organizationId = (String)incomingRequest.get("organizationId");
			String userDateFormat = (String) incomingRequest.get("userDateFormat");

	        if (HiltonUtility.isEmpty(userDateFormat)) {
	            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
	        }
			
			incomingRequest.put("InvItem_itemNumber", requisitionLine.getItemNumber());
			incomingRequest.put("InvItem_description", requisitionLine.getDescription());
			incomingRequest.put("InvItem_commodity", requisitionLine.getCommodityCode());
			incomingRequest.put("InvItem_cost", requisitionLine.getUnitPrice().toString());
			incomingRequest.put("InvItem_taxable", requisitionLine.getTaxable());
			incomingRequest.put("InvItem_dateEntered", Dates.today(userDateFormat));
			incomingRequest.put("InvItem_dateExpires", Dates.today(userDateFormat));
			incomingRequest.put("InvItem_owner", requisitionLine.getRequisitionerCode());
			incomingRequest.put("InvItem_unitOfOrder", requisitionLine.getUmCode());
			incomingRequest.put("InvItem_restrictedItem", "Y");
			incomingRequest.put("InvItem_assetCode", "N");
			
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}