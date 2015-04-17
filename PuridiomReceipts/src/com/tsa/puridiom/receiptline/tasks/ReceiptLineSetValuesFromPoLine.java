package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ReceiptLineSetValuesFromPoLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			String receiptMethod = HiltonUtility.ckNull((String)incomingRequest.get("receiptMethod"));
			if (receiptLine == null)
			{
				receiptLine = new ReceiptLine();
			}

			if (poLine != null)
			{
				if (receiptMethod.equals("Adjustment"))
				{
					receiptLine.setIcPoLine(poLine.getIcPoLine());
					receiptLine.setIcReqLine(poLine.getIcReqLine());
				}
				else
				{
					receiptLine.setIcPoLine(poLine.getIcLineKey());
				}

				receiptLine.setIcPoHeader(poLine.getIcPoHeader());
				receiptLine.setPoLine(poLine);
			}

			result = receiptLine;
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