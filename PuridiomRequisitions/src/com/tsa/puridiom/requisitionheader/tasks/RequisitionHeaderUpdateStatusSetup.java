package com.tsa.puridiom.requisitionheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RequisitionHeaderUpdateStatusSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try
		{
			List reqLineList = (List) incomingRequest.get("reqLineList");
			
			String lineStatus = "";
			
			incomingRequest.put("updateStatus", "TRUE");
			incomingRequest.put("RequisitionHeader_status", DocumentStatus.CANCELLED);
			
			if(reqLineList.size() == 1)
			{
			    if(((Boolean)incomingRequest.get("disbursed")).booleanValue())
			    {
			        incomingRequest.put("RequisitionHeader_status", DocumentStatus.INV_DISBURSED);
			    }
			    else
			    {
			        incomingRequest.put("RequisitionHeader_status", DocumentStatus.CANCELLED);
			    }
			}
			else
			{
			    String temp = DocumentStatus.INV_INPROGRESS;
				for (int i = 0; i < reqLineList.size(); i++)
				{
					RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
					lineStatus = reqLine.getStatus();
					
					if (lineStatus.compareTo(temp) > 0)
					{
						temp = lineStatus;
					}
					if(lineStatus.compareTo(DocumentStatus.INV_BACKORDERED) == 0)
					{
					    i = reqLineList.size();
					}
				}
				incomingRequest.put("RequisitionHeader_status", lineStatus);
			}
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) 
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
