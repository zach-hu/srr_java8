package com.tsa.puridiom.assetcost.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AssetCostRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String AssetCost_tagNumber = (String ) incomingRequest.get("AssetCost_tagNumber");
			String AssetCost_sequenceNoString = (String) incomingRequest.get("AssetCost_sequenceNo");
			BigDecimal AssetCost_sequenceNo = new BigDecimal ("0");

			if (Utility.isEmpty(AssetCost_tagNumber))
			{
				AssetCost_tagNumber = (String) incomingRequest.get("Asset_tagNumber");
			}
			if (Utility.isEmpty(AssetCost_sequenceNoString))
			{
				AssetCost_sequenceNo = (BigDecimal) incomingRequest.get("assetCostLastSequence");
			}
			else
			{
				AssetCost_sequenceNo = new BigDecimal (AssetCost_sequenceNoString );
			}

			String queryString = "from AssetCost AssetCost where AssetCost.id.tagNumber = ? and AssetCost.id.sequenceNo = ? ";
			List resultList = dbs.query(queryString, new Object[] {AssetCost_tagNumber, AssetCost_sequenceNo} , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;

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