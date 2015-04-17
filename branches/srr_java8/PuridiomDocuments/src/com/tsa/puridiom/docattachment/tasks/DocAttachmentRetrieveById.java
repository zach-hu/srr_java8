package com.tsa.puridiom.docattachment.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class DocAttachmentRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("DocAttachment_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String docIcString = (String) incomingRequest.get("DocAttachment_docIc");
			BigDecimal docIc = new BigDecimal ( docIcString );

			String queryString = "from DocAttachment as DocAttachment where DocAttachment.id.icHeader = ? and DocAttachment.id.docIc = ? ";
			List resultList = dbs.query(queryString, new Object[] {icHeader, docIc } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;
					
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