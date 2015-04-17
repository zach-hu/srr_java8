package com.tsa.puridiom.inspectionheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InspectionHeaderRetrieveByIdAndIcRecLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icInspNoString = (String) incomingRequest.get("InspectionHeader_icInspNo");
			if (icInspNoString == null) icInspNoString = "0" ;
			BigDecimal icInspNo = new BigDecimal ( icInspNoString );
			String icMsrLineString = (String) incomingRequest.get("InspectionHeader_icMsrLine");
			if (icMsrLineString == null) icMsrLineString = "0" ;
			BigDecimal icMsrLine = new BigDecimal ( icMsrLineString );
			String icRecLineString = (String) incomingRequest.get("InspectionHeader_icRecLine");
			if (icRecLineString ==null) icRecLineString = (String) incomingRequest.get("ReceiptLine_icRecLine") ;
			if (icRecLineString == null) icRecLineString = "0" ;
			BigDecimal icRecLine = new BigDecimal ( icRecLineString );

			String queryString = "from InspectionHeader as InspectionHeader where InspectionHeader.id.icInspNo = ? and InspectionHeader.id.icMsrLine = ?  and InspectionHeader.icRecLine = ? ";
			List resultList = dbs.query(queryString, new Object[] {icInspNo, icMsrLine, icRecLine } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;

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