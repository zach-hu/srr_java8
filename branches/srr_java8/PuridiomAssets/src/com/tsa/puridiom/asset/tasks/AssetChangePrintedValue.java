package com.tsa.puridiom.asset.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class AssetChangePrintedValue extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		Asset asset = (Asset) incomingRequest.get("asset");
		try
		{

			String value= (String) asset.getPrinted();
			if(value.compareTo("N")==0) {
					asset.setPrinted("P");
			}
			else{
				     asset.setPrinted("R");
		    }

			result = asset;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}