package com.tsa.puridiom.poheader.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class PoHeaderSetStatusByLineList extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			List poLineList = (List) incomingRequest.get("poLineList");
			if (poHeader == null)
			{
				throw new Exception("PoHeader was not found");
			}
			Integer	poStatus = new Integer(0);
			boolean partiallyReceived = false;
			boolean fullyReceived = false;
			boolean noneReceived = false;
			
			if (poLineList != null) {
				for (int i = 0; i < poLineList.size(); i++) {
				    PoLine poLine = (PoLine) poLineList.get(i);
				    if (poLine.getStatus().compareTo(DocumentStatus.RCV_PARTIALLYRECEIVED) < 0) {
					    noneReceived = true;
					} else 	if (poLine.getStatus().equals(DocumentStatus.RCV_PARTIALLYRECEIVED)) {
						partiallyReceived = true;
					} else if (poLine.getStatus().equals(DocumentStatus.RCV_RECEIVED)) {
						fullyReceived = true;
					} 
					
					Integer lineStatus = new Integer(poLine.getStatus());
					if (i == 0) {
					    poStatus = lineStatus;
					} else if (lineStatus.compareTo(poStatus) < 0) {
					    poStatus = lineStatus;
					}
				}

				if (partiallyReceived || (noneReceived && fullyReceived)) {
				    poHeader.setStatus(DocumentStatus.RCV_PARTIALLYRECEIVED);
				} else if (fullyReceived) {
				    poHeader.setStatus(DocumentStatus.RCV_RECEIVED);
				} else if (poStatus.compareTo(new Integer(0)) > 0) {
				    poHeader.setStatus(String.valueOf(poStatus));
				}
			}
						
			result = poHeader;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}