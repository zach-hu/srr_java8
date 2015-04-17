/**
 * Created on July 20, 2004
 * project: HiltonRequisitions
 * com.tsa.puridiom.requisitionline.tasks.RequisitionLineGetIcReqHeader.java
 *
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionLineGetIcReqHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			RequisitionLine reqLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			
			incomingRequest.put("RequisitionHeader_icReqHeader", reqLine.getIcReqHeader().toString());
			incomingRequest.put("RequisitionLine_icReqHeader", reqLine.getIcReqHeader().toString());
			
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
