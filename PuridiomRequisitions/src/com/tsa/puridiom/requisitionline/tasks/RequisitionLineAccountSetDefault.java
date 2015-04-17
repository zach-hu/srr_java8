package com.tsa.puridiom.requisitionline.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.account.tasks.AccountRetrieveByLine;
import com.tsa.puridiom.catalog.tasks.CatalogItemManager;
import com.tsa.puridiom.catalog.tasks.CatalogRetrieveById;
import com.tsa.puridiom.entity.CatalogItem;
import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RequisitionLineAccountSetDefault extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String organizationId = (String)incomingRequest.get("organizationId");

			List accountList = new ArrayList();

			RequisitionLine requisitionLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			if (requisitionLine != null)
			{
				CatalogItem catalogItem = (CatalogItem)CatalogItemManager.getInstance().getCatalogItem(organizationId, requisitionLine.getCatalogId(), requisitionLine.getItemNumber());
				if (catalogItem != null)
				{
					Map newIncomingRequest = new HashMap();
					newIncomingRequest.put("dbsession", dbs);
					newIncomingRequest.put("Account_icHeader", catalogItem.getIcAccount().toString());
					newIncomingRequest.put("Account_icLine", "0");

					AccountRetrieveByLine retrieveAccount = new AccountRetrieveByLine();
					accountList = (List)retrieveAccount.executeTask(newIncomingRequest);

					if (!(accountList != null && accountList.size() > 0))
					{
						newIncomingRequest.put("dbsession", dbs);
						newIncomingRequest.put("Catalog_catalogId", requisitionLine.getCatalogId());
						CatalogRetrieveById catalogRetrieveById = new CatalogRetrieveById();
						Catalog catalog = (Catalog)catalogRetrieveById.executeTask(newIncomingRequest);
						if (catalog != null)
						{
							newIncomingRequest.put("dbsession", dbs);
							newIncomingRequest.put("Account_icHeader", catalog.getIcAccount().toString());
							newIncomingRequest.put("Account_icLine", "0");

							retrieveAccount = new AccountRetrieveByLine();
							accountList = (List)retrieveAccount.executeTask(newIncomingRequest);
						}
					}
				}
			}

			incomingRequest.put("Commodity_commodity", requisitionLine.getCommodityCode());

			result = accountList;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
