package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.util.*;

public class PoLineListUpdateReceivedQty extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List	ivcLineList = (List) incomingRequest.get("invoiceLineList");

			for (int i=0; i < ivcLineList.size(); i++)
			{
				InvoiceLine invoiceLine = (InvoiceLine) ivcLineList.get(i);
				PoLine poLine = invoiceLine.getPoLine();

				if (poLine != null)
				{
					BigDecimal qty = invoiceLine.getQuantity();
					if (qty.compareTo(new BigDecimal(0)) > 0)
					{
						BigDecimal qtyReceived = poLine.getQtyReceived();
						BigDecimal newReceivedQty = qty.add(qtyReceived);

						poLine.setQtyReceived(newReceivedQty);

						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("poLine", poLine);

						PoLineUpdateById task = new PoLineUpdateById();
						task.executeTask(updateParameters);

						if (task.getStatus() < Status.SUCCEEDED)
						{
							throw new TsaException(this.getName() + " failed!");
						}
						ivcLineList.set(i, invoiceLine);
					}
				}
			}

			result = ivcLineList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}