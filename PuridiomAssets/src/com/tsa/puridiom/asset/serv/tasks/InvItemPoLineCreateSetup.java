/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.serv.tasks;

import java.util.Date;
import java.util.Map;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class InvItemPoLineCreateSetup extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	 * return an InvItem depending of poLine information
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		InvItem invItem = new InvItem();
		try
		{
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			String userId = (String) incomingRequest.get("userId");
			invItem.setOwner(userId);
			invItem.setDateEntered(new Date());
			invItem.setCommodity(poLine.getCommodity());
			invItem.setItemNumber(poLine.getItemNumber());
			invItem.setPoNumber(poLine.getPoNumber());
			invItem.setStatus(poLine.getStatus());
			invItem.setCost(poLine.getUnitPrice());
			invItem.setTaxable(poLine.getTaxable());
			invItem.setFactor(poLine.getUmFactor());
			invItem.setDescription(poLine.getDescription());
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
