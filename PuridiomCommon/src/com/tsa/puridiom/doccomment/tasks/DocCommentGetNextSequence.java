package com.tsa.puridiom.doccomment.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class DocCommentGetNextSequence extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("DocComment_icHeader");
			String icLineString = (String) incomingRequest.get("DocComment_icLine");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			BigDecimal icLine = new BigDecimal( icLineString );
			BigDecimal sequence = new BigDecimal("0");

			String queryString = "select max(DocComment.id.commentOrder) from DocComment as DocComment where DocComment.id.icHeader = ? and DocComment.id.icLine = ? ";
			List resultList = dbs.query(queryString, new Object[] {icHeader, icLine} , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				if (resultList.get(0) != null)
				{
					sequence = (BigDecimal) resultList.get(0);
				}
			}
			result = String.valueOf(sequence.add(new BigDecimal(1)));
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