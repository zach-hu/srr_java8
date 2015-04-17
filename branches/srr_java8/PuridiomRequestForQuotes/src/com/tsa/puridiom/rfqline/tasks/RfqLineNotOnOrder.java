/**
 *
 * Created on Jan 23, 2004
 * @author renzo
 * project: HiltonRequisitions
 * com.tsa.puridiom.rfqline.tasks.RfqLineNotOnOrder.java
 *
 */
package com.tsa.puridiom.rfqline.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RfqLineNotOnOrder extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		int lineStatus = 0;
		try
		{
			List rfqLines = (List)incomingRequest.get("rfqLines");
			if(rfqLines == null)
			{
				this.setStatus(Status.FAILED);
				throw new Exception("Rfq Lines not found!");
			}

			for (Iterator iter = rfqLines.iterator(); iter.hasNext();)
			{
				RfqLine rfqLine = (RfqLine)iter.next();

				if(rfqLine.getStatus().equals(DocumentStatus.RFQ_PURCHASING))
				{
					lineStatus++;
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("RfqLineNotOnOrder " + e.toString());
		}

		return new BigDecimal(lineStatus);
	}

}
