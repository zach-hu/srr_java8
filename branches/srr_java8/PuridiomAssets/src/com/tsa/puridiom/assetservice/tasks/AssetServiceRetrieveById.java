package com.tsa.puridiom.assetservice.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AssetServiceRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String AssetService_tagNumber = (String) incomingRequest.get("AssetService_tagNumber");
			String AssetService_sequenceNoString = (String) incomingRequest.get("AssetService_sequenceNo");
			BigDecimal AssetService_sequenceNo = new BigDecimal("0");
			if (Utility.isEmpty(AssetService_tagNumber))
			{
				AssetService_tagNumber = (String) incomingRequest.get("Asset_tagNumber");
			}
			if (Utility.isEmpty(AssetService_sequenceNoString))
			{
				AssetService_sequenceNo = (BigDecimal) incomingRequest.get("assetServiceLastSequence");
			}
			else
			{
				AssetService_sequenceNo = new BigDecimal (AssetService_sequenceNoString );
			}

			String queryString = "from AssetService AssetService where AssetService.id.tagNumber = ? and AssetService.id.sequenceNo = ? ";
			List resultList = dbs.query(queryString, new Object[] {AssetService_tagNumber, AssetService_sequenceNo, } , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;

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