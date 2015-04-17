package com.tsa.puridiom.bomcomponent.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class BomComponentSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("BomComponent_icComponent", "0");
			incomingRequest.put("BomComponent_parentItem", "");
			incomingRequest.put("BomComponent_componentItem", "");
			incomingRequest.put("BomComponent_componentType", "");
			incomingRequest.put("BomComponent_bomLine", "0");
			incomingRequest.put("BomComponent_description", "");
			incomingRequest.put("BomComponent_unitOfMeasure", "");
			incomingRequest.put("BomComponent_usageQty", "0");
			incomingRequest.put("BomComponent_estCost", "0");
			incomingRequest.put("BomComponent_scrapPerc", "0");
			incomingRequest.put("BomComponent_fromDate", Dates.today("", ""));
			incomingRequest.put("BomComponent_thruDate", Dates.today("", ""));
			incomingRequest.put("BomComponent_methodId", "");
			incomingRequest.put("BomComponent_invtype", "");
			incomingRequest.put("BomComponent_notes", "");
			incomingRequest.put("BomComponent_stageId", "");
			incomingRequest.put("BomComponent_descType", "");
			incomingRequest.put("BomComponent_fixOrvar", "");
			incomingRequest.put("BomComponent_taxAc", "");
			incomingRequest.put("BomComponent_inOut", "");
			incomingRequest.put("BomComponent_unitPrice", "0");
			incomingRequest.put("BomComponent_unitCost", "0");
			incomingRequest.put("BomComponent_primaryRes", "");
			incomingRequest.put("BomComponent_operName", "");
			incomingRequest.put("BomComponent_featureSl", "");
			incomingRequest.put("BomComponent_costRatio", "0");
			incomingRequest.put("BomComponent_dateEntered", Dates.today("", ""));
			incomingRequest.put("BomComponent_owner", "");

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