package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class PoLineSetQtyReceived extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			if (poLine == null)
			{
				throw new Exception("PoLine was not found");
			}
			ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
			if (receiptLine == null)
			{
				return poLine;
			}

			String receiptMethod = HiltonUtility.ckNull((String)incomingRequest.get("receiptMethod"));
			BigDecimal newQtyReceived = receiptLine.getQtyAccepted();
			BigDecimal qtyReceived = poLine.getQtyReceived();
			BigDecimal bdQtyOrdered = poLine.getQuantity();
			BigDecimal balance = qtyReceived.add(newQtyReceived);
            BigDecimal Tolerance =  bdQtyOrdered.add(bdQtyOrdered.multiply(new BigDecimal(10)).divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP));

            if(Tolerance.compareTo(balance) >= 0 || receiptMethod.equalsIgnoreCase("Adjustment"))
            {
            	poLine.setQtyReceived(balance);
            }
            else
            {
            	balance = newQtyReceived;
            	poLine.setQtyReceived(balance);
            }

			result = poLine;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}