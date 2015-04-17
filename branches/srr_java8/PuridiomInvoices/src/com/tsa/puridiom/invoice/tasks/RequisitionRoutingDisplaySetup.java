package com.tsa.puridiom.invoice.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class RequisitionRoutingDisplaySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			List invoicePoLineList = (List) incomingRequest.get("invoiceLineList");

			for(int i = 0; i < invoicePoLineList.size(); i++)
			{
				InvoiceLine invLine = (InvoiceLine)invoicePoLineList.get(i);
				PoLine poLine = invLine.getPoLine();
				if(poLine != null)
				{
					BigDecimal icReqLine = poLine.getIcReqLine();
					if(icReqLine.compareTo(new BigDecimal(0)) > 0 )
					{
						ret = icReqLine.toString();
						i = invoicePoLineList.size();
					}
				}

			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.getMessage());
		}
		return ret;
	}
}
