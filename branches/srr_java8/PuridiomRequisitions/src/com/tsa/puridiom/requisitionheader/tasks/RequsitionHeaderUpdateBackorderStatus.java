/*
 * Created on Nov 19, 2003 
 */
package com.tsa.puridiom.requisitionheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class RequsitionHeaderUpdateBackorderStatus extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("RequisitionHeader");
		List reqLines = (List)incomingRequest.get("RequisitionLines");
		String status = DocumentStatus.INV_INPROGRESS;
		
		if(reqHeader.getStatus().compareTo(DocumentStatus.REQ_APPROVED) < 1)
		{
		    status = DocumentStatus.INV_INPROGRESS;
		}
		else
		{
		    status = DocumentStatus.INV_PARTIAL;
		}
		
		/*for(int i = 0; i < reqLines.size(); i++)
		{
			RequisitionLine reqLine = (RequisitionLine)reqLines.get(i);
			if(reqLine.getStatus().equals(DocumentStatus.INV_BACKORDERED))
			{
				status = DocumentStatus.INV_BACKORDERED;
			}
			incomingRequest.put("requisitionLine", reqLine);
			RequisitionLineUpdate update = new RequisitionLineUpdate();
			update.executeTask(incomingRequest);
			
			this.setStatus(update.getStatus());
			
			if(this.getStatus() != Status.SUCCEEDED)
			{
				i = reqLines.size();
			}
		}*/
		reqHeader.setStatus(status);
		incomingRequest.put("requisitionHeader", reqHeader);
		return null;
	}
}
