package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqHeaderRetrieveByRfqLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine");
			if (rfqLine == null)
			{
				throw new Exception("The Rfq Line oject cannot be empty.");
			}
			BigDecimal icRfqHeader = rfqLine.getIcRfqHeader();

			StringBuffer queryString = new StringBuffer("from RfqHeader as RfqHeader where RfqHeader.icRfqHeader = '" + icRfqHeader + "'");
			List res = dbs.query(queryString.toString()) ;
			result = res.get(0);

			//result = (RfqHeader) dbs.retrieveId(icRfqHeader,RfqHeader.class) ;

			incomingRequest.put("RfqHeader_icRfqHeader",icRfqHeader.toString());
			incomingRequest.put("RfqLine_icRfqHeader",icRfqHeader.toString());

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result ;
	}

}