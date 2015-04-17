/*
 * Created on Nov 20, 2003 
 */
package com.tsa.puridiom.inventory.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.InvLocation;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventory.StandardBinSetup.java
 */
public class StandardBinSetup extends Task
{
	
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		InvLocation inventory = (InvLocation)incomingRequest.get("invLocation");
		try
		{
			DisbLine disbLine = (DisbLine)incomingRequest.get("disbLine");
			RequisitionLine reqLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			
			
			BigDecimal reqQty = disbLine.getQuantity();
			BigDecimal qtyToDisburse = new BigDecimal(0);
			BigDecimal qtyOnHand = inventory.getQtyOnHand();
			BigDecimal qtyAlloc = inventory.getQtyAlloc();
			
			if(qtyAlloc == null)
			{
				qtyAlloc = new BigDecimal(0);
			}
			
			BigDecimal qtyAvailable = qtyOnHand.subtract(qtyAlloc);
			
			BigDecimal qtyBackord = new BigDecimal(0);
			
			if(qtyAvailable.compareTo(reqQty) > -1)
			{
				qtyToDisburse = reqQty;
			}
			else
			{
				qtyToDisburse = qtyOnHand;
				qtyBackord = reqQty.subtract(qtyOnHand);
			}
			disbLine.setQuantity(qtyToDisburse);
			
			//qtyOnHand = qtyOnHand.subtract(qtyToDisburse);
			incomingRequest.put("InvLocation_qtyOnHand", qtyToDisburse);
			//inventory.setQtyOnHand(qtyOnHand);
			qtyAlloc = qtyAlloc.add(qtyToDisburse);
			incomingRequest.put("InvLocation_qtyAlloc", qtyToDisburse);
			if(qtyBackord.compareTo(new BigDecimal(0)) > 0 )
			{
				reqLine.setStatus(DocumentStatus.INV_BACKORDERED);
				reqLine.setBackordered(qtyBackord);
			}
			else
			{
				reqLine.setStatus(DocumentStatus.INV_INPROGRESS);
			}
			incomingRequest.put("requisitionLine", reqLine);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
		}
		finally
		{
			return inventory;
		}
	}
}
