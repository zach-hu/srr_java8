package com.tsa.puridiom.trackingnumber.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RetrieveTrackingNumber extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String formType = (String) incomingRequest.get("formType");
			BigDecimal icHeader = new BigDecimal("0");
			if(("REC").equalsIgnoreCase(formType))
			{
				String icRecHeaderString = (String ) incomingRequest.get("ReceiptHeader_icRecHeader");

				icHeader = new BigDecimal(icRecHeaderString);
			}

			String queryString = "from TrackingData as TrackingData where TrackingData.id.icHeader = ?";
			List result = dbs.query(queryString, new Object[] {icHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			this.setStatus(dbs.getStatus()) ;
			return result;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
	}

}