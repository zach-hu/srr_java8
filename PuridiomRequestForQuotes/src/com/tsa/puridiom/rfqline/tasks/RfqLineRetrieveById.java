package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqLineRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqLineString = (String) incomingRequest.get("RfqLine_icRfqLine");
			BigDecimal icRfqLine = new BigDecimal ( icRfqLineString );
			String rfqLine_icRfqHeader = (String) incomingRequest.get("RfqLine_icRfqLine");


			String queryString = "from RfqLine as RfqLine where RfqLine.icRfqLine = ? ";
			List resultList = dbs.query(queryString, new Object[] {icRfqLine, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				RfqLine rfqLine = (RfqLine) resultList.get(0);
				rfqLine_icRfqHeader = rfqLine.getIcRfqHeader().toString();

				result = resultList.get(0);
			}
			incomingRequest.put("RfqLine_icRfqHeader",rfqLine_icRfqHeader) ;
			incomingRequest.put("RfqHeader_icRfqHeader",rfqLine_icRfqHeader) ;

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