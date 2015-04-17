package com.tsa.puridiom.trackingnumber.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class TrackingDataSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			TrackingDataPK comp_id = new TrackingDataPK();
			String organizationId = (String)incomingRequest.get("organizationId");
			TrackingData trackingData = (TrackingData) incomingRequest.get("trackingData");
			if (trackingData == null)
			{
				trackingData = new TrackingData();
			}
			
			if (incomingRequest.containsKey("TrackingData_icTracking"))
			{
				String icStringTracking = (String) incomingRequest.get("TrackingData_icTracking");
				BigDecimal icTracking = new BigDecimal ( icStringTracking );
				comp_id.setIcTracking(icTracking);
			}
			if (incomingRequest.containsKey("TrackingData_icHeader"))
			{
				String icStringHeader = (String) incomingRequest.get("TrackingData_icHeader");
				BigDecimal icHeader = new BigDecimal ( icStringHeader );
				comp_id.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("TrackingData_icLine"))
			{
				String icStringLine = (String) incomingRequest.get("TrackingData_icLine");
				if (Utility.isEmpty(icStringLine))
				{
					icStringLine = "0";
				}
				BigDecimal icLine = new BigDecimal ( icStringLine );
				comp_id.setIcLine(icLine);
			}
			if (incomingRequest.containsKey("TrackingData_trackingNumber"))
			{
				String trackingNumber = (String) incomingRequest.get("TrackingData_trackingNumber");
				if (Utility.isEmpty(trackingNumber))
				{
					trackingNumber = " ";
				}
				trackingData.setTrackingNumber(trackingNumber);
			}
			if (incomingRequest.containsKey("TrackingData_trackingDesc"))
			{
				String trackingDesc = (String) incomingRequest.get("TrackingData_trackingDesc");
				if (Utility.isEmpty(trackingDesc))
				{
					trackingDesc = " ";
				}
				trackingData.setTrackingDesc(trackingDesc);
			}
			if (incomingRequest.containsKey("TrackingData_owner"))
			{
				String owner = (String) incomingRequest.get("TrackingData_owner");
				if (Utility.isEmpty(owner))
				{
					owner = " ";
				}
				trackingData.setOwner(owner);
			}
			
			trackingData.setComp_id(comp_id);

			result = trackingData;
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
