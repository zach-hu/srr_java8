package com.tsa.puridiom.invbinlocation.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvBinLocationDataRetrieve extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result;
        try
        {
            Map incomingRequest = (Map)object;
            List invBinLocationList = (List)incomingRequest.get("invBinLocationList");

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("invbinlocationdata-retrieve.xml");

            for(int i = 0; i < invBinLocationList.size(); i++)
            {
            	InvBinLocation invBinLocation = (InvBinLocation)invBinLocationList.get(i);
                Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("invBinLocation", invBinLocation);
				updateParameters.put("InvBinLocation_icRc", invBinLocation.getIcRc().toString());

				process.executeProcess(updateParameters);
				this.setStatus(process.getStatus());
				if (process.getStatus() != Status.SUCCEEDED)
				{
					throw new TsaException("InvBinLocation failed.");
				}
				else
				{
					invBinLocation = (InvBinLocation)updateParameters.get("invBinLocation");
					invBinLocationList.set(i, invBinLocation);
				}
			}
			result = invBinLocationList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}
