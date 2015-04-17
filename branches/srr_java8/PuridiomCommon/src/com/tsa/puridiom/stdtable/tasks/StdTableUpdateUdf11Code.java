package com.tsa.puridiom.stdtable.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.util.List;
import java.util.Map;

public class StdTableUpdateUdf11Code extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String organizationId = (String)incomingRequest.get("organizationId");
			List accountList = (List)incomingRequest.get("accountList");

			String description = "";

			if (accountList != null && accountList.size() > 0)
			{
				Account account = (Account)accountList.get(0);
				if (account != null && !HiltonUtility.isEmpty(account.getFld2()))
				{
					description = StdTableManager.getInstance(organizationId).getStdTableDescription(organizationId, "AC02", account.getFld2());
				}
			}

			if (!HiltonUtility.isEmpty(description))
			{
				String referenceType = HiltonUtility.ckNull((String)incomingRequest.get("referenceType"));

				if (referenceType.equalsIgnoreCase("RQ"))
				{
					RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
					if (requisitionHeader != null && HiltonUtility.isEmpty(requisitionHeader.getUdf11Code()))
					{
						requisitionHeader.setUdf11Code(description);
						dbs.update(requisitionHeader);
					}
				}
				else if (referenceType.equalsIgnoreCase("PO"))
				{
					PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
					if (poHeader != null && HiltonUtility.isEmpty(poHeader.getUdf11Code()))
					{
						poHeader.setUdf11Code(description);
						dbs.update(poHeader);
					}
				}
			}

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
