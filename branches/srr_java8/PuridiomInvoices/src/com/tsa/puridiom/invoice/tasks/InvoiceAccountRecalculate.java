package com.tsa.puridiom.invoice.tasks;

import com.tsa.puridiom.entity.Account;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InvoiceAccountRecalculate extends Task	{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			incomingRequest.put("hasAccountRows","N") ;
			List accountList = (List) incomingRequest.get("accountList") ;
			if (accountList != null) {
				if (accountList.size() > 0) {
					incomingRequest.put("hasAccountRows","Y") ;
					String allocateTotal = (String)incomingRequest.get("allocateTotal");
					String allocatedTotal = (String)incomingRequest.get("allocatedTotal");
					Log.debug(this.getName(), "allocatedTotal: " + allocatedTotal);
					Log.debug(this.getName(), "allocateTotal: " + allocateTotal);
					if (allocateTotal == null) {
						allocateTotal = "0" ;
					}
					if (allocatedTotal == null) {
						allocatedTotal = "0" ;
					}

					BigDecimal bdAllocateTotal = new BigDecimal(allocateTotal) ;
					//BigDecimal bdAllocatedTotal = new BigDecimal(allocatedTotal) ;

					//BigDecimal bdToAllocate = bdAllocateTotal.subtract(bdAllocatedTotal) ;
					BigDecimal bdToAllocate = bdAllocateTotal;
					BigDecimal bdTotal = new BigDecimal(0) ;
					Log.debug(this.getName(), "bdToAllocate: " + bdToAllocate);

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
								BigDecimal  percent = ac.getAllocPercent().divide(new BigDecimal(100),5,BigDecimal.ROUND_HALF_UP) ;
								ac.setAllocAmount(percent.multiply(bdToAllocate).setScale(2,BigDecimal.ROUND_HALF_UP)) ;
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
							//ac.setAllocAmount(ac.getAllocAmount().add(bdTotal.subtract(bdToAllocate))) ;
						    ac.setAllocAmount(bdToAllocate.subtract(bdTotal));
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
