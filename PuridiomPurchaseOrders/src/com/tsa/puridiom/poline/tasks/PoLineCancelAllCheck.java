package com.tsa.puridiom.poline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineCancelAllCheck extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		boolean cancelAll = true;
		try
		{
			List poLineList = (List)incomingRequest.get("poLineList");
			if(poLineList != null)
			{
				for(int i = 0; i< poLineList.size(); i++)
				{
					PoLine poLine = (PoLine)poLineList.get(i);
					if(poLine.getStatus().compareTo(DocumentStatus.CLOSED) < 0)
					{
						cancelAll = false;
						i = poLineList.size();
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
			throw new TsaException("An error ocurred verifyin order status ", e);
		}
		return ret;
	}
}
