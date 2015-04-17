/**
 *
 * Created on Feb 11, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invbinlocation.tasks.InvLocationFromUpdateSetup.java
 *
 */
package com.tsa.puridiom.invbinlocation.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class InvBinLocationFromUpdateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			InvBinLocation bin = (InvBinLocation)incomingRequest.get("fromBin");
			incomingRequest.put("invBinLocation", bin);
			incomingRequest.put("binAction", "MOVEFROM") ;
			incomingRequest.put("InvBinLocHistory_qtyMoved", incomingRequest.get("qtyToMove"));
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}

}
