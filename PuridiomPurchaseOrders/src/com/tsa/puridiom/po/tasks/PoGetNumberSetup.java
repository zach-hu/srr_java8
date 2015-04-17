package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsa.puridiom.common.documents.AutoGenType;
import com.tsa.puridiom.common.utility.HiltonUtility;

/**
 * @author Kelli
 */
public class PoGetNumberSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			String	oid = (String) incomingRequest.get("organizationId") ;
			String	icPoHeaderString = (String) incomingRequest.get("PoHeader_icPoHeader");
			String	fiscalYear = (String) incomingRequest.get("PoHeader_fiscalYear");
			String	poType = HiltonUtility.ckNull((String) incomingRequest.get("PoHeader_poType"));

			if (Utility.isEmpty(fiscalYear))
			{
				fiscalYear = "1994";
			}

			incomingRequest.put("AutoGen_documentType",  "PO") ;
			incomingRequest.put("AutoGen_Type", poType);
			incomingRequest.put("AutoGen_genYear", fiscalYear) ;
			incomingRequest.put("PoLine_icPoHeader", icPoHeaderString);

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
