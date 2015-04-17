package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class PoLineCancelAll extends Task
{
	public String findReqStatus(String icPoLine, String linesFromReq[], String reqLineStatus[])
	{
		String reqStatus = "";
		if(linesFromReq != null)
		{
			for (int i = 0; i < linesFromReq.length; i++)
			{
				if(icPoLine.equals(linesFromReq[i]))
				{
					reqStatus = reqLineStatus[i];
				}
			}
		}
		return reqStatus;
	}

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		boolean cancelAll = true;
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			List poLineList = poHeader.getPoLineList();
			if(poLineList != null)
			{
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
	            PuridiomProcess process = processLoader.loadProcess("poline-cancel-from-header.xml");

	            for(int i = 0; i< poLineList.size(); i++)
				{
					PoLine poLine = (PoLine)poLineList.get(i);

					if(!poLine.getStatus().equals(DocumentStatus.CANCELLED) && !poLine.getStatus().equals(DocumentStatus.CLOSED))
	            	{
			        	Map newIncomingRequest = new HashMap();
			        	newIncomingRequest.put("poHeader", poHeader);
			            newIncomingRequest.put("poLine", poLine);
			            newIncomingRequest.put("accountList", poLine.getAccountList());
			            newIncomingRequest.put("userId", incomingRequest.get("userId"));
			            newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
			            newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
			            newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
			            newIncomingRequest.put("accounts", poLine.getAccountList());
			            newIncomingRequest.put("poaction", incomingRequest.get("poaction"));


		            	String		sLine =  Integer.toString(poLine.getLineNumber().intValue()) ;
			            if(poLine.getIcReqLine().compareTo(new BigDecimal(0)) > 0)
			            {
			            	newIncomingRequest.put("RequisitionLine_status", (String)incomingRequest.get("RequisitionLine_status_" + sLine));
			            }
			            if(poLine.getIcRfqLine().compareTo(new BigDecimal(0)) > 0)
			            {
			            	newIncomingRequest.put("RfqLine_status", (String)incomingRequest.get("RfqLine_status_" + sLine));
			            }

			            process.executeProcess(newIncomingRequest);
			            this.setStatus(process.getStatus());

			            if (this.getStatus() != Status.SUCCEEDED)
			            {
			                Log.error(this, "Canceling Line item " + poLine.getLineNumber() + " failed with status: " + process.getStatus());
			                throw new TsaException("Error Canceling PoLine: " + poLine.getLineNumber());
			            }
	            	}
				}
			}
			else
			{
				cancelAll = false;
			}
			ret = Boolean.valueOf(cancelAll);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("An error ocurred checking order status ", e);
		}
		return ret;
	}
}
