package com.tsa.puridiom.invlocation.tasks;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InvLocationUpdateMsrSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReceiptLine recLine = (ReceiptLine) incomingRequest.get("receiptLine") ;
			incomingRequest.put("InspectionHeader_icMsrLine",recLine.getIcLineHistory().toString() ) ;
			incomingRequest.put("RequisitionLine_icLineHistory",recLine.getIcLineHistory().toString() ) ;
			incomingRequest.put("icMsrLine",recLine.getIcLineHistory().toString() ) ;

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