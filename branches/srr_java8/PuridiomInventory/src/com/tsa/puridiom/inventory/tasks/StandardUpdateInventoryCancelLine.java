/*
 * Created on Dec 10, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryStandardUpdateInventoryCancelLine.java
 */
 
package com.tsa.puridiom.inventory.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.InvLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class StandardUpdateInventoryCancelLine extends Task
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
			InvLocation inventory = (InvLocation)incomingRequest.get("invLocation");
			DisbLine disbLine = (DisbLine)incomingRequest.get("disbLine");
			BigDecimal qtyAlloc = inventory.getQtyAlloc();
			BigDecimal qty = disbLine.getQuantity();
			BigDecimal newAlloc = qtyAlloc.subtract(qty); 
			if(newAlloc.compareTo(new BigDecimal(0)) < 1)
			{
				inventory.setQtyAlloc(new BigDecimal(0));
			}
			else
			{
				inventory.setQtyAlloc(newAlloc);
			}
			ret = inventory;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		finally
		{
			return ret;
		}
	}
}
