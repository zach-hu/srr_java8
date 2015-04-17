package com.tsa.puridiom.assetnote.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AssetNoteRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String AssetNote_tagNumber = (String ) incomingRequest.get("AssetNote_tagNumber");
			String AssetNote_sequenceNoString = (String) incomingRequest.get("AssetNote_sequenceNo");
			BigDecimal AssetNote_sequenceNo = new BigDecimal ("0");
			if (Utility.isEmpty(AssetNote_tagNumber))
			{
				AssetNote_tagNumber = (String) incomingRequest.get("Asset_tagNumber");
			}
			if (Utility.isEmpty(AssetNote_sequenceNoString))
			{
				AssetNote_sequenceNo = (BigDecimal) incomingRequest.get("assetNoteLastSequence");
			}
			else
			{
				AssetNote_sequenceNo = new BigDecimal (AssetNote_sequenceNoString );
			}

			String queryString = "from AssetNote AssetNote where AssetNote.id.tagNumber = ? and AssetNote.id.sequenceNo = ? ";
			List resultList = dbs.query(queryString, new Object[] {AssetNote_tagNumber, AssetNote_sequenceNo} , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;

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