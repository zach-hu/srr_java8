/*
 * Created on Dec 9, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryTransferReqStandardUnitPrice.java
 */
 
package com.tsa.puridiom.inventory.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.InvItem;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class TransferReqStandardUnitPrice extends Task
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
			InvItem item = (InvItem)incomingRequest.get("invItem");
			
			DisbLine disbLine = (DisbLine)incomingRequest.get("disbLine");
			disbLine.setUmCode(item.getUnitOfIssue());
			BigDecimal factor = item.getFactor();
			if(factor.compareTo(new BigDecimal(0)) == 0)
			{
				factor = new BigDecimal(1);
			}
			BigDecimal unitPrice = disbLine.getUnitPrice().divide(factor, 2, BigDecimal.ROUND_UP); 
			disbLine.setUnitPrice(unitPrice);
			incomingRequest.put("disbLine", disbLine);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		finally
		{
			return ret;
		}
	}
}
