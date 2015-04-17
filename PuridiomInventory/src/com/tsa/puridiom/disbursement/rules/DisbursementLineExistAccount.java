package com.tsa.puridiom.disbursement.rules;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class DisbursementLineExistAccount extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String existAccountLine = "succeeded";

		try
		{
			DisbHeader header = (DisbHeader) incomingRequest.get("header");
			List lineItems = (List) incomingRequest.get("lineitems");
			List existAccount = header.getAccountList();

			if ((existAccount == null) || (existAccount.isEmpty()))
			{
				if ((lineItems != null) && (!lineItems.isEmpty()))
				{
					for (Iterator iterator = lineItems.iterator(); iterator.hasNext();)
					{
						DisbLine disbLine = (DisbLine) iterator.next();

						List existLineAccount = disbLine.getAccountList();

						if(existLineAccount == null || (existLineAccount.isEmpty()))
						{
							existAccountLine = "failed";
						}
					}
				}
			}

	        incomingRequest.put("existAccountEachLine", existAccountLine);
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("An Error occurred at AccountExistRules", e);
		}
		return null;
	}
}
