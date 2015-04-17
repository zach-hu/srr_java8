package com.tsa.puridiom.inspectionheader.tasks;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InspectionHeaderListRetrieveByIcRecLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRecLineString = (String) incomingRequest.get("InspectionHeader_icRecLine");
			if (icRecLineString ==null) icRecLineString = (String) incomingRequest.get("ReceiptLine_icRecLine") ;
			if (icRecLineString == null) icRecLineString = "0" ;
			BigDecimal icRecLine = new BigDecimal ( icRecLineString );

			String queryString = "from InspectionHeader as InspectionHeader where  InspectionHeader.icRecLine = ? ";
			List resultList = dbs.query(queryString, new Object[] { icRecLine, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			result = resultList ;
			if (result == null) result = new ArrayList() ;

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