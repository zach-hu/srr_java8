package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


public class PoLineSaveasSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest= (Map) object;
		try
		{
			PoLine pol = (PoLine)incomingRequest.get("poLine");
			if (pol == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("PoLine entity was not found!");
			}
			else
			{
				String icHeader = (String)incomingRequest.get("PoLine_icPoHeader");
				String icLine = (String) incomingRequest.get("PoLine_icPoLine");
				String newIcHeader= pol.getIcPoHeader().toString();
				String newIcLine= pol.getIcPoLine().toString();

				if (Utility.isEmpty(icHeader))
				{
					this.setStatus(Status.FAILED);
					throw new TsaException("PoLine.icPoHeader can not be empty!");
				}
				else
				{
					incomingRequest.put("BillTo_icHeader", icHeader);
					incomingRequest.put("BillTo_icLine", icLine);
					incomingRequest.put("ShipTo_icHeader", icHeader);
					incomingRequest.put("ShipTo_icLine", icLine);
					incomingRequest.put("Account_icHeader", icHeader);
					incomingRequest.put("Account_icLine", icLine);
					incomingRequest.put("DocComment_icHeader", icHeader);
					incomingRequest.put("DocComment_icLine", icLine);
					incomingRequest.put("DocAttachment_icHeader", icHeader);
					incomingRequest.put("DocAttachment_icLine", icLine);
					incomingRequest.put("newBillTo_icHeader", newIcHeader);
					incomingRequest.put("newBillTo_icLine", newIcLine);
					incomingRequest.put("newShipTo_icHeader", newIcHeader);
					incomingRequest.put("newShipTo_icLine", newIcLine);
					incomingRequest.put("newAccount_icHeader", newIcHeader);
					incomingRequest.put("newAccount_icLine", newIcLine);
					incomingRequest.put("newDocComment_icHeader", newIcHeader);
					incomingRequest.put("newDocComment_icLine", newIcLine);
					incomingRequest.put("newDocAttachment_icHeader", newIcHeader);
					incomingRequest.put("newDocAttachment_icLine", newIcLine);
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}
