package com.tsa.puridiom.invoice.tasks;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InvoiceShippingAccountRecalculate extends Task	{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List accountList = (List) incomingRequest.get("shippingAccountList") ;
			if (accountList != null) {
				if (accountList.size() > 0) {
					InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
					BigDecimal bdShipping = invoiceHeader.getInvoiceShipping();
					BigDecimal bdTotal = new BigDecimal(0);

					Iterator it = accountList.iterator() ;

					while (it.hasNext())
					{
						Account ac = (Account) it.next() ;
						String allocMethod = ac.getAllocMethod();
						if (Utility.isEmpty(allocMethod))
						{
							allocMethod = "P";
						}
						if (allocMethod.substring(0, 1).equals("P"))
						{
								BigDecimal  percent = ac.getAllocPercent().divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP) ;
								ac.setAllocAmount(percent.multiply(bdShipping).setScale(2,BigDecimal.ROUND_HALF_UP)) ;
								if (it.hasNext())
								{
								    bdTotal = bdTotal.add(ac.getAllocAmount()) ;
								}
						}
						else
						{
						    if (it.hasNext())
							{
						        bdTotal = bdTotal.add(ac.getAllocAmount()) ;
							}
						}

						if (! it.hasNext()) {
						    ac.setAllocAmount(bdShipping.subtract(bdTotal));
						}
						Log.debug(this.getName(), "allocated: " + ac.getAllocAmount().toString());
					}
				}
			} else {
				// Nothing to allocate
			}
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result ;

	}
}
