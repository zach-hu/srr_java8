package com.tsa.puridiom.validationrules.receipt.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PackageQtyRecivedNotZero extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String packageQtyRecivedNotZero = "succeeded";

		try
        {
	    	// line accounts
			ReceiptHeader header = (ReceiptHeader) incomingRequest.get("header");
			List lineItems = (List) incomingRequest.get("lineitems");
			BigDecimal numberPackage = HiltonUtility.ckNull(header.getPkgsReceived());

			if (numberPackage.compareTo(new BigDecimal(1)) >= 0)
			{
				if ((lineItems != null) && (!lineItems.isEmpty()))
				{
					for (Iterator iterator = lineItems.iterator(); iterator.hasNext();)
					{
						ReceiptLine recLine = (ReceiptLine) iterator.next();
						BigDecimal qtyReceived = HiltonUtility.ckNull(recLine.getQtyReceived());
						
						if (qtyReceived.compareTo(new BigDecimal(1)) < 0)
						{
							packageQtyRecivedNotZero = "failed";
						}
					}
				}

			}
	        incomingRequest.put("PackageQtyRecivedNotZero", packageQtyRecivedNotZero);
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred at AccountExistRules", e);
		}
		return null;
    }
}