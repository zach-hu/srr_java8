/**
 * Created on Mar 5, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineUpdateInventorySetup.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.InvLocation;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineUpdateInventorySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			PoLine poLine = (PoLine)incomingRequest.get("poLine");
			BigDecimal qtyOrdered = poLine.getQuantity();
			InvItem invItem = (InvItem)incomingRequest.get("invItem");
			BigDecimal factor = invItem.getFactor();
			if(factor == null)
			{	
				factor = new BigDecimal(1);
			}
			else if(factor.compareTo(new BigDecimal(0)) == 0)
			{
				factor = new BigDecimal(1);
			}
			RequisitionLine reqLine = (RequisitionLine)incomingRequest.get("reqLine");
			BigDecimal requisitionQty = reqLine.getQuantity();
			if(requisitionQty == null){ 	requisitionQty = new BigDecimal(0);	}
			
			InvLocation invLocation = (InvLocation)incomingRequest.get("invLocation");
			BigDecimal qtyOnOrder = invLocation.getQtyOnOrder();
			BigDecimal qtyRequested = invLocation.getQtyRequested();
			BigDecimal oldQty = (BigDecimal)incomingRequest.get("oldQty");
			
			Boolean revision = (Boolean)incomingRequest.get("revision");
			BigDecimal newQtyOnOrder = new BigDecimal(0);
			if(revision.booleanValue())
			{
				BigDecimal bdTemp = oldQty.multiply(factor);
				newQtyOnOrder = qtyOnOrder.subtract(bdTemp);
				bdTemp = qtyOrdered.multiply(factor);
				newQtyOnOrder = newQtyOnOrder.add(bdTemp);
			}
			else
			{
				BigDecimal bdTemp = qtyOrdered.multiply(factor);
				newQtyOnOrder = qtyOnOrder.add(bdTemp);
			}
			
			BigDecimal newQtyRequested = qtyRequested.subtract(requisitionQty);
			if(newQtyRequested.compareTo(new BigDecimal(0)) < 0)
			{
				newQtyRequested = new BigDecimal(0);
			}
			if(newQtyOnOrder.compareTo(new BigDecimal(0)) < 0)
			{
				newQtyOnOrder = new BigDecimal(0);
			}
			incomingRequest.put("newQtyOnOrder", newQtyOnOrder);
			incomingRequest.put("newQtyRequested", newQtyRequested);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
