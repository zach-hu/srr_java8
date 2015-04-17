package com.tsa.puridiom.billto.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class BillToSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain billTo, newBillTo_icHeader, and newBillTo_icLine */
			String icHeader = (String)incomingRequest.get("newBillTo_icHeader");
			String icLine = (String)incomingRequest.get("newBillTo_icLine");
			BillToPK comp_id = new BillToPK();
			BillTo	originalBillTo = (BillTo) incomingRequest.get("billTo");
			BillTo	billTo = new BillTo();

			comp_id.setIcHeader(new BigDecimal(icHeader));
			comp_id.setIcLine(new BigDecimal(icLine));
			comp_id.setBillToCode(originalBillTo.getComp_id().getBillToCode());
			billTo.setQuantity(originalBillTo.getQuantity());
			billTo.setAttention(originalBillTo.getAttention());
			billTo.setComp_id(comp_id);

			incomingRequest.put("billTo", billTo);

			BillToAdd addTask = new BillToAdd();
			billTo = (BillTo) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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