package com.tsa.puridiom.po.rules;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineExistAccount extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String existAccountLine = "succeeded";

		try
        {
			PoHeader header = (PoHeader) incomingRequest.get("header");
			List lineItems = (List) incomingRequest.get("lineitems");
			List existAccount = header.getAccountList();

			if ((existAccount == null) || (existAccount.isEmpty()))
			{
				if ((lineItems != null) && (!lineItems.isEmpty()))
				{
					for (Iterator iterator = lineItems.iterator(); iterator.hasNext();)
					{
						PoLine poLine = (PoLine) iterator.next();

						List existLineAccount = poLine.getAccountList();

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
