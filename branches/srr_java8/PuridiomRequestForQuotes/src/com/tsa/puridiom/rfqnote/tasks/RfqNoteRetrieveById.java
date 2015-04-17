package com.tsa.puridiom.rfqnote.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.*;

public class RfqNoteRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("RfqNote_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String icLineString = (String) incomingRequest.get("RfqNote_icLine");
			BigDecimal icLine = new BigDecimal ( icLineString );
			String vendorId = (String ) incomingRequest.get("RfqNote_vendorId");

			String queryString = "from RfqNote as RfqNote where RfqNote.id.icHeader = ? and RfqNote.id.icLine = ? and RfqNote.id.vendorId = ? ";
			List resultList = dbs.query(queryString, new Object[] {icHeader, icLine, vendorId, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;
					
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