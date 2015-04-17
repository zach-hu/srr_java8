package com.tsa.puridiom.rfqquestion.tasks;
import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class RfqQuestionDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icRfqHeader = (String)incomingRequest.get("RfqQuestion_icRfqHeader");
		String icQuestion = (String) incomingRequest.get("RfqQuestion_icQuestion");
		BigDecimal bdRfqHeader = new BigDecimal(icRfqHeader);
		BigDecimal bdQuestion = new BigDecimal(icQuestion);

		String queryString = "from RfqQuestion as r where r.id.icRfqHeader = ? and r.id.icQuestion = ? " ;
		
		dbs.delete(queryString, new Object[] {bdRfqHeader, bdQuestion, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;
		
		this.setStatus(dbs.getStatus()) ;
		return null;
	}

}