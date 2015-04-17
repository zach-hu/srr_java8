package com.tsa.puridiom.disbursement.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

/**
 * @author Kelli
 */
public class DisbursementFormatNumberSetup extends Task 
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;

		try 
		{
			String	dsbNumber = (String) incomingRequest.get("DisbHeader_disbNumber");
			String	fiscalYear = (String) incomingRequest.get("DisbHeader_fiscalYear");
			if (Utility.isEmpty(fiscalYear)) 
			{
				fiscalYear = "1994";
			}
			incomingRequest.put("AutoGen_documentType", "DSB") ;
			incomingRequest.put("AutoGen_genYear", fiscalYear) ;
			incomingRequest.put("documentNumber", dsbNumber);
			
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
