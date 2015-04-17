package com.tsa.puridiom.invformcatalog.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvFormCatalogSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvFormCatalogPK comp_id = new InvFormCatalogPK();
			InvFormCatalog invFormCatalog = (InvFormCatalog) incomingRequest.get("invFormCatalog");
			if (invFormCatalog == null)
			{
				invFormCatalog = new InvFormCatalog();
			}

			if (incomingRequest.containsKey("InvFormCatalog_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("InvFormCatalog_itemNumber");
				comp_id.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("InvFormCatalog_invCatid"))
			{
				String invCatid = (String ) incomingRequest.get("InvFormCatalog_invCatid");
				comp_id.setInvCatid(invCatid);
			}
			invFormCatalog.setComp_id(comp_id);

			result = invFormCatalog;
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