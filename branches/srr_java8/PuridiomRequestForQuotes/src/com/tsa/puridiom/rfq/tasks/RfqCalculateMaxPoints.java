package com.tsa.puridiom.rfq.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqCalculateMaxPoints extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object returnObj = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	icRfqHeaderString = (String) incomingRequest.get("RfqHeader_icRfqHeader");
			BigDecimal totalPoints = new BigDecimal(0);
			
			if (Utility.isEmpty(icRfqHeaderString)) {
			    icRfqHeaderString = (String) incomingRequest.get("RfqQuestion_icRfqHeader");
			}
			if (!Utility.isEmpty(icRfqHeaderString)) {
				String	sql = "select sum(RfqQuestion.maxPoints) from RfqQuestion RfqQuestion where RfqQuestion.id.icRfqHeader = " + icRfqHeaderString;
				
				List maxPointsList = dbs.query(sql);
	            if (maxPointsList != null && maxPointsList.size() > 0) {
			        BigDecimal maxPoints = (BigDecimal) maxPointsList.get(0);
			        if (maxPoints != null) {
			            totalPoints = totalPoints.add(maxPoints);
			        }
	            }
			
				returnObj = totalPoints.toString();
				this.setStatus(Status.SUCCEEDED) ;
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return returnObj;
	}

}