package com.tsa.puridiom.asset.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
//import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AssetRetrieveByIcLineKey extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icLineKeyString = (String ) incomingRequest.get("Asset_icLineKey");
			BigDecimal icLineKey = new BigDecimal(icLineKeyString);
			String queryString = "from Asset Asset where Asset.icLineKey = ? ";
			List resultList = dbs.query(queryString, new Object[] {icLineKey } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList;
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