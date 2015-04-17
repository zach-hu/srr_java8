/*
 * Created on September 20, 2005
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author kathleen
 */
public class InvoiceLineAllocateAmount extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		String result = null ;

		try {
			InvoiceLine ivl = (InvoiceLine) incomingRequest.get("invoiceLine");
			BigDecimal bdTotal = ivl.getLineTotal();

			if (bdTotal == null)
			{
				bdTotal = new BigDecimal(0);
			}

			result = bdTotal.toString();
			/*
			BigDecimal bdQuantity = ivl.getQuantity();
			BigDecimal bdUnitPrice = ivl.getUnitPrice();
			BigDecimal bdUmFactor = ivl.getUmFactor();

			if (bdUmFactor.compareTo(new BigDecimal(0)) == 0)
			{
				bdUmFactor = new BigDecimal(1);
			}

			BigDecimal bdExtCost = bdQuantity.multiply(bdUnitPrice).multiply(bdUmFactor);

			String discountPercent = (String) incomingRequest.get("discountPercent");
			BigDecimal bdDiscPerc = new BigDecimal(0);
			if (!HiltonUtility.isEmpty(discountPercent))
			{
				BigDecimal bdDiscount = new BigDecimal(0);
				bdDiscPerc = new BigDecimal(discountPercent);
				bdDiscount = bdExtCost.multiply(bdDiscPerc);
				bdExtCost = bdExtCost.subtract(bdDiscount);
			}

			result = bdExtCost.toString();
			*/
	        this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}
}
