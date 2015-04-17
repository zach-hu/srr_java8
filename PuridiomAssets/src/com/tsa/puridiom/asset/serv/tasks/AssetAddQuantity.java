/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.serv.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class AssetAddQuantity extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	 * returns the asset quantity according to poline or receiptline
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String Asset_number = "1";
		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			Asset_number = poLine.getQuantity().toString();
			if (PropertiesManager.getInstance(organizationId).getProperty("ASSET", "ADDFROM", "P").equalsIgnoreCase("R")) {
				ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
				Asset_number = receiptLine.getQtyAccepted().toString();
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return Asset_number;
	}
}
