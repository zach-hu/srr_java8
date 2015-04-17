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

public class InvoiceUseTaxAccountRecalculate extends Task	{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List accountList = (List) incomingRequest.get("usetaxAccountList") ;
			if (accountList != null) {
				if (accountList.size() > 0) {
					InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
					BigDecimal bdUseTax = invoiceHeader.getUseTax();
					BigDecimal bdTotal = new BigDecimal(0);

					Iterator it = accountList.iterator() ;

					while (it.hasNext())
					{
						Account ac = (Account) it.next() ;
						String allocMethod = ac.getAllocMethod();
						BigDecimal bdAllocAmount = ac.getAllocAmount();
						if (Utility.isEmpty(allocMethod))
						{
							allocMethod = "P";
						}
						if (allocMethod.substring(0, 1).equals("P"))
						{
								BigDecimal  percent = ac.getAllocPercent().divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP) ;
								bdAllocAmount = percent.multiply(bdUseTax).setScale(2,BigDecimal.ROUND_HALF_UP);
								//use tax account allocation should be negative
								ac.setAllocAmount(bdAllocAmount.negate()) ;
								if (it.hasNext())
								{
								    bdTotal = bdTotal.add(bdAllocAmount) ;
								}
						}
						else
						{
						    if (it.hasNext())
							{
						        bdTotal = bdTotal.add(bdAllocAmount) ;
							}
						}

						if (! it.hasNext()) {
							bdAllocAmount = bdUseTax.subtract(bdTotal);
						    ac.setAllocAmount(bdAllocAmount.negate());
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
