/*
 * Created on Dec 19, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryDisbLineExtendedCancelIt.java
 */
 
package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class DisbLineExtendedCancelIt extends Task
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
			DisbLine disbLine = (DisbLine) incomingRequest.get("disbLine");
			disbLine.setStatus(DocumentStatus.CANCELLED);
			
			BigDecimal qty = disbLine.getQuantity();
			//update bin
			InvBinLocation bin = (InvBinLocation)incomingRequest.get("invBinLocation");
			BigDecimal binAllocated = bin.getQtyAlloc();
			binAllocated = binAllocated.subtract(qty);
			bin.setQtyAlloc(binAllocated);
			incomingRequest.put("invBinLocation", bin);
			BigDecimal qtyToCancel = (BigDecimal)incomingRequest.get("qtyToCancel");
			qtyToCancel = qtyToCancel.add(qty);
			incomingRequest.put("qtyToCancel", qtyToCancel);
			
			incomingRequest.put("disbLine", disbLine);
			this.setStatus(Status.SUCCEEDED);
			ret = incomingRequest;
		}
		catch (Exception e)
		{
		    this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}
}
