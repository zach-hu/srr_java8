/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.serv.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvItem;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class InvItemCreateFromAsset extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	 * the asset information is copying in InvItem
	 */
	public Object executeTask(Object object) throws Exception
	{
		InvItem invItem = new InvItem();
		try
		{
			Map incomingRequest = (Map) object;
			String Asset_itemNumber = (String)incomingRequest.get("Asset_itemNumber");
			String Asset_description = (String) incomingRequest.get("Asset_description");
			String Asset_commodity = (String) incomingRequest.get("Asset_commodity");
			invItem.setItemNumber(Asset_itemNumber);
			invItem.setDescription(Asset_description);
			invItem.setCommodity(Asset_commodity);
			invItem.setAssetCode("Y");
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return invItem;
	}
}
