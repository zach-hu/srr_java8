package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class MsrLineRetrieveRequisitionLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			if (requisitionLine!=null)
			{
				BigDecimal icMsrHistory = requisitionLine.getIcLineHistory();
				String queryString = "from RequisitionLine as RequisitionLine where RequisitionLine.icLineHistory = ? and RequisitionLine.reqType = 'M' ";
				List resultList = dbs.query(queryString, new Object[] {icMsrHistory,} , new Type[] { Hibernate.BIG_DECIMAL}) ;
						
				if (resultList != null && resultList.size() > 0)
				{
					result = resultList.get(0);
				}
				this.setStatus(dbs.getStatus()) ;
			}else{
				this.setStatus(Status.SUCCEEDED) ;
			}
			
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}