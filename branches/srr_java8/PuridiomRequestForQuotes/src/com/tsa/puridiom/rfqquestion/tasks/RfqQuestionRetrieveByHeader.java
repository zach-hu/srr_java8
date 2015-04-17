package com.tsa.puridiom.rfqquestion.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.Map;

public class RfqQuestionRetrieveByHeader extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeaderString = (String) incomingRequest.get("RfqQuestion_icRfqHeader");
			BigDecimal icRfqHeader = new BigDecimal(icRfqHeaderString);
			StringBuffer queryString = new StringBuffer("from RfqQuestion as rfqquestion where rfqquestion.id.icRfqHeader = '" + icRfqHeader + "'");

			result = dbs.query(queryString.toString()) ;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}
}