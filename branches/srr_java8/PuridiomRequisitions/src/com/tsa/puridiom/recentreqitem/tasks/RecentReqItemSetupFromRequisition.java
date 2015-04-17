package com.tsa.puridiom.recentreqitem.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class RecentReqItemSetupFromRequisition extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
		    RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");

		    if (requisitionHeader == null) {
		        throw new Exception ("Recent Requisition Item cannot be setup.  RequisitionHeader was not found.");
		    }
		    if (requisitionLine == null) {
		        throw new Exception ("Recent Requisition Item cannot be setup.  RequisitionLine was not found.");
		    }

			String	source = requisitionLine.getItemSource();
		    String	location = requisitionLine.getItemLocation();
		    if (source.equals("CAT")) {
		        location = requisitionLine.getCatalogId();
		    }
		    incomingRequest.put("RecentReqItem_requisitionerCode", requisitionHeader.getRequisitionerCode());
			incomingRequest.put("RecentReqItem_itemNumber", requisitionLine.getItemNumber());
		    incomingRequest.put("RecentReqItem_itemSource", source);
		    incomingRequest.put("RecentReqItem_itemLocation", location);
			incomingRequest.put("RecentReqItem_description", requisitionLine.getDescription());
			incomingRequest.put("RecentReqItem_dateEntered", Dates.today("", ""));
            // Use date in default system time zone to ensure proper sorting

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