package com.tsa.puridiom.asset.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

public class AssetGetNumberSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object ret = null;

		try
		{
			String	tagNumber = (String) incomingRequest.get("Asset_tagNumber");
			String	fiscalYear = (String) incomingRequest.get("Asset_fiscalYear");
			if (Utility.isEmpty(fiscalYear))
			{
				fiscalYear = "1994";
			}
			incomingRequest.put("AutoGen_documentType", "AST") ;
			incomingRequest.put("AutoGen_genYear", fiscalYear) ;
			incomingRequest.put("Asset_tagNumber", tagNumber);

			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return ret ;
	}
}

