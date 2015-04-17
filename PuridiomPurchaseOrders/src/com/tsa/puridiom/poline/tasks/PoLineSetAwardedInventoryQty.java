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

public class PoLineSetAwardedInventoryQty extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			PoLine poLine = (PoLine)incomingRequest.get("poLine");
			BigDecimal qtyOrdered = poLine.getQuantity();
			InvItem invItem = (InvItem)incomingRequest.get("invItem");
            if (invItem != null) {
    			BigDecimal factor = invItem.getFactor();
    			if(factor == null)
    			{
    				factor = new BigDecimal(1);
    			}
    			else if(factor.compareTo(new BigDecimal(0)) == 0)
    			{
    				factor = new BigDecimal(1);
    			}

    			InvLocation invLocation = (InvLocation)incomingRequest.get("invLocation");
                if (invLocation!= null) {
        			BigDecimal qtyOnOrder = invLocation.getQtyOnOrder();

                    qtyOrdered = qtyOrdered.multiply(factor);

                    BigDecimal newQtyOnOrder = qtyOnOrder.add(qtyOrdered);

                    if(newQtyOnOrder.compareTo(new BigDecimal(0)) < 0)
        			{
        				newQtyOnOrder = new BigDecimal(0);
        			}

                    BigDecimal qtyRequested = invLocation.getQtyRequested();
                    BigDecimal newQtyRequested = new BigDecimal(0);
                    RequisitionLine reqLine = (RequisitionLine) incomingRequest.get("reqLine");
                    if (reqLine != null) {
                        BigDecimal requisitionQty = reqLine.getQuantity();
                        if(requisitionQty == null){     requisitionQty = new BigDecimal(0); }

                        newQtyRequested = qtyRequested.subtract(requisitionQty);
                        if(newQtyRequested.compareTo(new BigDecimal(0)) < 0)
                        {
                            newQtyRequested = new BigDecimal(0);
                        }
                    } else {
                        newQtyRequested = qtyRequested;
                    }

                    invLocation.setQtyOnOrder(newQtyOnOrder);
                    invLocation.setQtyRequested(newQtyRequested);
                }
            }

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
