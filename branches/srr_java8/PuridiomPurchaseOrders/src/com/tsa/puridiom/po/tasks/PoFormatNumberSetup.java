package com.tsa.puridiom.po.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

/**
 * @author Renzo
 */
public class PoFormatNumberSetup extends Task 
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;

		try 
		{
			String	poNumber = (String) incomingRequest.get("PoHeader_poNumber");
			String	fiscalYear = (String) incomingRequest.get("PoHeader_fiscalYear");
			if (Utility.isEmpty(fiscalYear)) 
			{
				fiscalYear = "1994";
			}
			incomingRequest.put("AutoGen_documentType", "PO") ;
			incomingRequest.put("AutoGen_genYear", fiscalYear) ;
			incomingRequest.put("documentNumber", poNumber);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) 
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
