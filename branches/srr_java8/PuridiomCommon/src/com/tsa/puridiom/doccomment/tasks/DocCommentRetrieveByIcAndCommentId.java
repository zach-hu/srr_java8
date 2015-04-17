package com.tsa.puridiom.doccomment.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class DocCommentRetrieveByIcAndCommentId extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("DocComment_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String icLineString = (String) incomingRequest.get("DocComment_icLine");
			BigDecimal icLine = new BigDecimal ( icLineString );
			String commentId = (String) incomingRequest.get("DocComment_commentId");

			String queryString = "from DocComment as DocComment where DocComment.id.icHeader = ? and DocComment.id.icLine = ? and DocComment.commentId = ? ";
			List resultList = dbs.query(queryString, new Object[] {icHeader, icLine, commentId, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;

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