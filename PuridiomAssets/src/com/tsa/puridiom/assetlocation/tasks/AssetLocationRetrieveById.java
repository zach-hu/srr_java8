package com.tsa.puridiom.assetlocation.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AssetLocationRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String AssetLocation_tagNumber = (String ) incomingRequest.get("AssetLocation_tagNumber");
			String AssetLocation_sequenceNoString = (String) incomingRequest.get("AssetLocation_sequenceNo");
			BigDecimal AssetLocation_sequenceNo = new BigDecimal("0");
			if (Utility.isEmpty(AssetLocation_tagNumber))
			{
				AssetLocation_tagNumber = (String) incomingRequest.get("Asset_tagNumber");
			}
			if (Utility.isEmpty(AssetLocation_sequenceNoString))
			{
				AssetLocation_sequenceNo = (BigDecimal) incomingRequest.get("assetLocationLastSequence");
			}
			else
			{
				AssetLocation_sequenceNo = new BigDecimal (AssetLocation_sequenceNoString );
			}

			String queryString = "from AssetLocation AssetLocation where AssetLocation.id.tagNumber = ? and AssetLocation.id.sequenceNo = ? ";
			List resultList = dbs.query(queryString, new Object[] {AssetLocation_tagNumber, AssetLocation_sequenceNo} , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}