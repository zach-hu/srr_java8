package com.tsa.puridiom.inspection.tasks;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.InspectionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class InspectionRetrieveListByLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		BigDecimal icMsrLine = (BigDecimal) incomingRequest.get("InspectionLine_icMsrLine") ;
		if (icMsrLine == null) icMsrLine = new BigDecimal(0) ;

		StringBuffer queryString = new StringBuffer("from  InspectionHeader as inspectionHeader where inspectionHeader.id.icMsrLine = ?  and  (inspectionHeader.icRecLine is null or inspectionHeader.icRecLine = 0)");
		List result = dbs.query(queryString.toString(), new Object[] { icMsrLine }, new Type[] { Hibernate.BIG_DECIMAL } ) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}