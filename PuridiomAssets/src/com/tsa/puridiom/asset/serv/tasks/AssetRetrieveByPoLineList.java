package com.tsa.puridiom.asset.serv.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
//import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AssetRetrieveByPoLineList extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		/*
		 * @author EDSAC
		 * return an poLine list with his icLineKey
		 * */

		Map incomingRequest = (Map)object;
		List result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			List poLineList = (List) incomingRequest.get("poLineList");
			for (int i=0; i<poLineList.size(); i++)
			{
				PoLine poLine = (PoLine) poLineList.get(i);
				BigDecimal icLineKey = poLine.getIcLineKey();
				String queryString = "from Asset Asset where Asset.icLineKey = ? ";
				List resultList = dbs.query(queryString, new Object[] {icLineKey } , new Type[] { Hibernate.BIG_DECIMAL}) ;
				if (resultList != null && resultList.size() > 0)
				{
					result.addAll(resultList);
				}
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