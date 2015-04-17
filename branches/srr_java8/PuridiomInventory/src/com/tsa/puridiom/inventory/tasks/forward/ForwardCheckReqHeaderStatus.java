/*
 * Created on Dec 3, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventory.forwardForwardCheckReqHeaderStatus.java
 */
 
package com.tsa.puridiom.inventory.tasks.forward;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class ForwardCheckReqHeaderStatus extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			List reqLines = (List)incomingRequest.get("allReqLines");
			String status = DocumentStatus.INV_DISBURSED;
			RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			
			boolean foundStatus = true;
			                                                                                                                /* fully backorder */
			for (Iterator iter = reqLines.iterator(); iter.hasNext();)
			{
				RequisitionLine reqLine = (RequisitionLine) iter.next();
				if(!(reqLine.getBackordered().compareTo(reqLine.getQuantity()) == 0))
				{
				    foundStatus = false;
				}
			}
			
			if(!foundStatus)
			{                                                                                                                                   /* fully disbursed */
			    foundStatus = true;
			    for (Iterator iter = reqLines.iterator(); iter.hasNext();)
				{
					RequisitionLine reqLine = (RequisitionLine) iter.next();
					if(!(reqLine.getBackordered().compareTo(new BigDecimal(0)) == 0))
					{
					    foundStatus = false;
					}
				}
			    if(!foundStatus)
			    {
			                                                                                                                          /* Partially disbursed/backorder */
				    foundStatus = true;
				    status = DocumentStatus.INV_PARTIAL;
			        
			    }
			    else                                                                                                                            /* fully disbursed */
			    {
			        status = DocumentStatus.INV_DISBURSED;
			    }
			}
			else                                                                                                            /* fully backorder */
			{
			    status = DocumentStatus.INV_BACKORDERED;
			}
			
			reqHeader.setStatus(status);
			ret = reqHeader;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		
		return ret;
	}
}
