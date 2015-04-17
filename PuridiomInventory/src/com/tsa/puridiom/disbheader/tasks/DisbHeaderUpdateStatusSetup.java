package com.tsa.puridiom.disbheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class DisbHeaderUpdateStatusSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try
		{
			List disbLineList = (List) incomingRequest.get("disbLineList");
			
			String lineStatus = "";
			
			incomingRequest.put("updateStatus", "TRUE");
			incomingRequest.put("DisbHeader_status", DocumentStatus.CANCELLED);
			
			for (int i = 0; i < disbLineList.size(); i++)
			{
				DisbLine disbLine = (DisbLine) disbLineList.get(i);
				lineStatus = disbLine.getStatus();
				
				if (lineStatus.compareTo(DocumentStatus.CANCELLED) != 0)
				{
					incomingRequest.put("updateStatus", "FALSE");
				}
			}
			
			incomingRequest.put("RequisitionHeader_status", DocumentStatus.CANCELLED);
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
