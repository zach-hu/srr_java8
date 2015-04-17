/*
 * Created on Sept 14, 2005
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Task;

/**
 * @author kathleen
 */
public class PoLineGetExtendedCost extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		PoLine poLine = (PoLine) incomingRequest.get("poLine");
        BigDecimal bdQuantity = new BigDecimal(0);
        BigDecimal bdUnitPrice = new BigDecimal(0);
        BigDecimal bdExtendedCost = new BigDecimal(0);
        BigDecimal bdUmFactor = new BigDecimal(0);
        BigDecimal bdQtyReceived = new BigDecimal(0);
        BigDecimal bdQtyPoInvoiced = new BigDecimal(0);

        if (poLine != null) {
    		bdQuantity = poLine.getQuantity();
    		bdUnitPrice = poLine.getUnitPrice();
    		bdExtendedCost = new BigDecimal(0);
    		bdUmFactor = poLine.getUmFactor();
    		bdQtyPoInvoiced = poLine.getQtyInvoiced();
    		
    		if (bdUmFactor == null || bdUmFactor.compareTo(new BigDecimal(0)) == 0)
    		{
    			bdUmFactor = new BigDecimal(1);
    		}
    		bdExtendedCost = bdQuantity.multiply(bdUmFactor).multiply(bdUnitPrice);
    		bdQtyReceived = poLine.getQtyReceived();
        }

		incomingRequest.put("qtyOrdered", bdQuantity);
		incomingRequest.put("orderUmFactor", bdUmFactor);
		incomingRequest.put("orderUnitPrice", bdUnitPrice);
		incomingRequest.put("amountOrdered", bdExtendedCost);
		incomingRequest.put("qtyReceived", bdQtyReceived);
		incomingRequest.put("bdQtyPoInvoiced", bdQtyPoInvoiced);
		return null;
	}

}
