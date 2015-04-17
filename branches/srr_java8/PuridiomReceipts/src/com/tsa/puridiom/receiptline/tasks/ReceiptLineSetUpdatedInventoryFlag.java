/**
 * Created on Mar 5, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineUpdateInventorySetup.java
 *
 */
package com.tsa.puridiom.receiptline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ReceiptLineSetUpdatedInventoryFlag extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result ;
		try
		{
            ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
            if (receiptLine != null) {
            	receiptLine.setInventoryFlag("Y") ;
            	
            	ReceiptLineUpdate update = new ReceiptLineUpdate();
            	incomingRequest.put("receiptLine", receiptLine);
            	update.executeTask(incomingRequest);
            }

            result = receiptLine ;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result ;
	}

}
