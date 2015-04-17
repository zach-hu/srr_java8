/**
 * Created on Dec 27, 2005
 * @author cesar
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.asset.serv.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoLineItemNumberAssign extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		PoLine poLine = new PoLine();

		/*@author EDSAC
		 *add the itemNumber parameter of poLine and return it
		 */
		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			poLine = (PoLine) incomingRequest.get("poLine");
			String organizationId = (String) incomingRequest.get("organizationId");
			if (Utility.isEmpty(poLine.getItemNumber())) {
				if (PropertiesManager.getInstance(organizationId).getProperty("AUTONUMBER", "AUTOASSET", "Y").equalsIgnoreCase("Y"))
				{
					poLine.setItemNumber(ukg.getUniqueKey().toString());
				}
				else
				{
					poLine.setItemNumber(ukg.getUniqueKey().toString());
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return poLine;
	}
}
