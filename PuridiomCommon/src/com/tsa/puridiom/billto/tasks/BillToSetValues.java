package com.tsa.puridiom.billto.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.BillTo;
import com.tsa.puridiom.entity.BillToPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class BillToSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BillToPK comp_id = new BillToPK();
			BillTo billTo = (BillTo) incomingRequest.get("billTo");
			if (billTo == null)
			{
				billTo = new BillTo();
			}

			if (incomingRequest.containsKey("BillTo_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("BillTo_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				comp_id.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("BillTo_icLine"))
			{
				String icLineString = (String) incomingRequest.get("BillTo_icLine");
				if (Utility.isEmpty(icLineString))
				{
					icLineString = "0";
				}
				BigDecimal icLine = new BigDecimal ( icLineString );
				comp_id.setIcLine(icLine);
			}
			if (incomingRequest.containsKey("BillTo_billToCode"))
			{
				String billToCode = (String ) incomingRequest.get("BillTo_billToCode");
				comp_id.setBillToCode(billToCode);
			}
			if (incomingRequest.containsKey("BillTo_quantity"))
			{
				String quantityString = (String) incomingRequest.get("BillTo_quantity");
				if (Utility.isEmpty(quantityString))
				{
					quantityString = "0";
				}
				BigDecimal quantity = new BigDecimal ( quantityString );
				billTo.setQuantity(quantity);
			}
			if (incomingRequest.containsKey("BillTo_attention"))
			{
				String attention = (String ) incomingRequest.get("BillTo_attention");
				billTo.setAttention(attention);
			}
			billTo.setComp_id(comp_id);

			result = billTo;
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