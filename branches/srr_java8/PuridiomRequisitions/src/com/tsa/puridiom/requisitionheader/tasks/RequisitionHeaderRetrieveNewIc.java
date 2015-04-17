/**
 * @author kathleen
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.tsa.puridiom.requisitionheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class RequisitionHeaderRetrieveNewIc extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map)object;
		String ret = "";
		try 
		{
			RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			ret = reqHeader.getIcReqHeader().toString();
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) 
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
		}
		return ret;
	}

}
