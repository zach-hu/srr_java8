/*
 * Created on Feb 9, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionLineSetForwardStatus extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map)object;
		RequisitionLine reqLine = (RequisitionLine)incomingRequest.get("requisitionLine");
		if(reqLine == null)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("RequisitionLine Entity was not found!");
		}
		try 
		{
			reqLine.setStatus((String)incomingRequest.get("RequisitionLine_status"));
			this.setStatus(Status.SUCCEEDED);
		} 
		catch (Exception e) 
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return reqLine;
	}

}
