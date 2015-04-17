package com.tsa.puridiom.catalog.tasks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.account.tasks.AccountAdd;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.CatalogItem;
import com.tsa.puridiom.entity.CatalogItemPK;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class CatalogItemCopy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Object result = null;

		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String userId = (String)incomingRequest.get("userId") ;

		String catalogId = (String) incomingRequest.get("CatalogItem_catalogId");
		String itemNumber = (String) incomingRequest.get("CatalogItem_itemNumber");

    	PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
        PuridiomProcess process = null;
    	Map newIncomingRequest = new HashMap();
    	newIncomingRequest.put("dbsession", dbs);
		newIncomingRequest.put("CatalogItem_itemNumber", itemNumber);
		process = processLoader.loadProcess("catalog-item-retrieve-by.xml");
		process.executeProcess(newIncomingRequest);

		List catalogItemList = (List) newIncomingRequest.get("catalogItemList");

		for (Iterator iterator = catalogItemList.iterator(); iterator.hasNext();)
        {
			CatalogItem catalogItem = (CatalogItem)iterator.next();
			if (catalogItem.getComp_id().getCatalogId().equals(catalogId))
			{
				return result;
			}
        }

		if (catalogItemList != null &&  catalogItemList.size() > 0)
		{

			CatalogItem catalogItem = (CatalogItem) catalogItemList.get(0);


			Map newIncomingRequest2 = new HashMap();
			newIncomingRequest2.put("dbsession", dbs);
			newIncomingRequest2.put("userId", userId);
			newIncomingRequest2.put("newCatalogId", catalogId);
			newIncomingRequest2.put("catalogItem", catalogItem);

			CatalogItemSaveas catalogItemSaveas = new CatalogItemSaveas();

			CatalogItem newCatalogItem = (CatalogItem) catalogItemSaveas.executeTask(newIncomingRequest2);

			newIncomingRequest2.put("Account_icHeader", catalogItem.getIcAccount().toString());
			newIncomingRequest2.put("newAccount_icHeader", newCatalogItem.getIcAccount().toString());
			newIncomingRequest2.put("newAccount_icLine", "0");



			incomingRequest.put("CatalogItem_catalogId",(String) incomingRequest.get("CatalogItem_catalogId"));
			incomingRequest.put("CatalogItem_itemNumber",(String) incomingRequest.get("CatalogItem_itemNumber"));

			process = processLoader.loadProcess("account-saveas.xml");
			process.executeProcess(newIncomingRequest2);

			result = newCatalogItem;

		}

		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}