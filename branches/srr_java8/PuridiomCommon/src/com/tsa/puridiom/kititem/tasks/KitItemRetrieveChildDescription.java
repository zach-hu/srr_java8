/*
 * Created on January 18, 2007
 */
package com.tsa.puridiom.kititem.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.KitItem;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kathleen
 */
public class KitItemRetrieveChildDescription extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("kititem-retrieve-childdescription.xml");

			List kitItemList = (List) incomingRequest.get("kitItemList");
			int	ii = 0;
	        for ( Iterator it = kitItemList.iterator(); it.hasNext(); )
	        {
	        	KitItem kitItem = (KitItem) it.next();
	        	Map requestParameters = new HashMap();

				String childDescription = "";


				requestParameters.put("userId", incomingRequest.get("userId"));
				requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
	        	requestParameters.put("organizationId", incomingRequest.get("organizationId"));
	        	requestParameters.put("dbsession", incomingRequest.get("dbsession"));
	        	requestParameters.put("CatalogItem_catalogId", kitItem.getComp_id().getChildCatalogId());
	        	requestParameters.put("CatalogItem_itemNumber", kitItem.getComp_id().getChildItemNumber());

				process.executeProcess(requestParameters);

				this.setStatus(process.getStatus());
				if (process.getStatus() == Status.FAILED)
				{
					break;
				}

				childDescription = HiltonUtility.ckNull((String) requestParameters.get("childDescription"));

				kitItem.setChildDescription(childDescription);

				kitItemList.set(ii, kitItem);
				ii++;
	        }

	        result = kitItemList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}

}
