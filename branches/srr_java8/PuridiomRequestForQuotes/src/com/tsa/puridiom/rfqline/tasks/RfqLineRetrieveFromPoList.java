package com.tsa.puridiom.rfqline.tasks;

import com.tsagate.foundation.processengine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RfqLineRetrieveFromPoList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = new ArrayList();

		try
		{
			List poDataList = (List) incomingRequest.get("poDataList");
			for (int i=0; i<poDataList.size(); i++) {
				List values = (List) poDataList.get(i);
				List lines  = (List) values.get(2);
				for (int j=0; j<lines.size(); j++) {
					result.add(lines.get(j));
				}
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}
}