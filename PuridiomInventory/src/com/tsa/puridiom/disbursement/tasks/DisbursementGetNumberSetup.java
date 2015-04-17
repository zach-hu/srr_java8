package com.tsa.puridiom.disbursement.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

/**
 * @author Kelli
 */
public class DisbursementGetNumberSetup extends Task 
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;

		try 
		{
			String	fiscalYear = (String) incomingRequest.get("DisbHeader_fiscalYear");
			if(Utility.isEmpty(fiscalYear) && incomingRequest.containsKey("disbHeader"))
			{
				if((DisbHeader)incomingRequest.get("disbHeader")!=null){
					fiscalYear = HiltonUtility.ckNull(((DisbHeader)incomingRequest.get("disbHeader")).getFiscalYear());
				}
			}
			if (Utility.isEmpty(fiscalYear)) 
			{
				fiscalYear = "1994";
			}
			incomingRequest.put("AutoGen_documentType", "DSB") ;
			incomingRequest.put("AutoGen_genYear", fiscalYear) ;
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) 
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
		}
		
		return null ;
	}
}
