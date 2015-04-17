package com.tsa.puridiom.docattachment.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class DocAttachmentRetrieveByHeader extends Task
{

    public Object  executeTask (Object object) throws Exception
    {
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("DocAttachment_icHeader");

			if (Utility.isEmpty(icHeaderString)) {
				throw new Exception ("DocAttachment icHeader OR icLine cannot be empty.");
			}

			String queryString = "from DocAttachment as d where d.id.icHeader = ?  AND d.id.icLine = 0 order by d.id.docIc ASC" ;
			BigDecimal icHeader = new BigDecimal(icHeaderString);

			ret = dbs.query(queryString,	new Object[] {icHeader}, new Type[] {Hibernate.BIG_DECIMAL}) ;

			this.setStatus(dbs.getStatus()) ;

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return ret ;
	}
}