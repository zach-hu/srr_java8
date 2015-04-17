package com.tsa.puridiom.asset.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
//import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AssetRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String tagNumber = (String ) incomingRequest.get("Asset_tagNumber");
			String queryString = "from Asset Asset where Asset.id = ? ";
			List resultList = dbs.query(queryString, new Object[] {tagNumber, } , new Type[] { Hibernate.STRING}) ;

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