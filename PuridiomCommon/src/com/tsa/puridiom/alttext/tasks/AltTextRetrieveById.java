package com.tsa.puridiom.alttext.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AltTextRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String source = (String ) incomingRequest.get("AltText_source");
			String id = (String ) incomingRequest.get("AltText_id");
			String itemNumber = (String ) incomingRequest.get("AltText_itemNumber");
			String language = (String ) incomingRequest.get("AltText_language");

			String queryString = "from AltText as AltText where AltText.id.source = ? and AltText.id.id = ? and AltText.id.itemNumber = ? and AltText.id.language = ? ";
			List resultList = dbs.query(queryString, new Object[] {source, id, itemNumber, language, } , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) ;

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