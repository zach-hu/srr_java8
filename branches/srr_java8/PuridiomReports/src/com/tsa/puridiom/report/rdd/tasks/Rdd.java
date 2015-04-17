package com.tsa.puridiom.report.rdd.tasks;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ShipTo;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class Rdd extends Task
{
	public Date getHeaderRequiredDate(Map incomingRequest)
	{
		return null;
	}

	public List getLineList(Map incomingRequest)
	{
		return null;
	}

	public List getShipToList(List lineList, int index)
	{
		return null;
	}
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			boolean seeBelow = true;

			List lineList = this.getLineList(incomingRequest);
			Date headerRdd = this.getHeaderRequiredDate(incomingRequest);
			for(int i = 0; i < lineList.size(); i++)
			{
				List shipToList = this.getShipToList(lineList, i);
				for(int is = 0; is < shipToList.size(); is++)
				{
					ShipTo shipTo = (ShipTo)shipToList.get(is);
					Date lineShipToDate = shipTo.getShipDate();
					if(lineShipToDate == null){		lineShipToDate = headerRdd;		}
					if(lineShipToDate.equals(headerRdd))
					{
						seeBelow = false;
						is = shipToList.size();
						i = lineList.size();
					}
				}
			}
			ret = Boolean.valueOf(seeBelow);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, this.getClass() + " failed: " + e.getMessage());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}

}
