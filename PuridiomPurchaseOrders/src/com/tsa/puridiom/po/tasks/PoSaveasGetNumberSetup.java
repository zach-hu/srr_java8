package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class PoSaveasGetNumberSetup extends Task 
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		try
		{
			PoHeader originalPoHeader = (PoHeader) incomingRequest.get("poHeader");
			String fiscalYear = (String) incomingRequest.get("PoHeader_fiscalYear");
			String poType = HiltonUtility.ckNull(originalPoHeader.getPoType());
			if (fiscalYear == null || fiscalYear.trim().length() == 0)
			{
				fiscalYear = originalPoHeader.getFiscalYear();
			}
			if (fiscalYear == null || fiscalYear.trim().length() == 0)
			{
				fiscalYear = "1994";
			}
			incomingRequest.put("AutoGen_documentType", "PO");
			incomingRequest.put("AutoGen_Type", poType);
			incomingRequest.put("AutoGen_genYear", fiscalYear);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}
}
