/**
 * Created on Feb 18, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoReqSourceSetup.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoReqSourceSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			incomingRequest.put("RequisitionHeader_icReqHeader", poHeader.getIcReqHeader().toString());
			incomingRequest.put("RequisitionLine_icReqHeader", poHeader.getIcReqHeader().toString());
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
