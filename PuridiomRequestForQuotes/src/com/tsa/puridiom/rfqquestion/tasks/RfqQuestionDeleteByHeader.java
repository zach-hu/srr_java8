package com.tsa.puridiom.rfqquestion.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.Map;
import org.hibernate.Hibernate;

public class RfqQuestionDeleteByHeader extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icRfqHeader = (String)incomingRequest.get("RfqQuestion_icRfqHeader");
		BigDecimal bdRfqHeader = new BigDecimal(icRfqHeader) ;

		String queryString = "from RfqQuestion as r where r.id.icRfqHeader = ?" ;

		dbs.delete(queryString, bdRfqHeader, Hibernate.BIG_DECIMAL) ;
		
		this.setStatus(dbs.getStatus()) ;
		return null;
	}

}