package com.tsa.puridiom.rfqline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqLineRetrieveIcReqHeader extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icReqLineString = (String) incomingRequest.get("RfqLine_icReqLine");

			if (Utility.isEmpty(icReqLineString))
			{
				throw new Exception("RfqLine_icReqLine cannot be empty.  IcReqHeader for the Requisition could not be retrieved.");
			}
			BigDecimal icReqLine = new BigDecimal ( icReqLineString );

			String queryString = "select RequisitionLine.icReqHeader, RequisitionLine.requisitionNumber from RequisitionLine as RequisitionLine where RequisitionLine.icReqLine = ? ";
			List resultList = dbs.query(queryString, icReqLine, Hibernate.BIG_DECIMAL) ;

			if (resultList != null && resultList.size() > 0)
			{
				Object reqInfoObj[] = (Object[]) resultList.get(0);
				BigDecimal bdIcHeader = (BigDecimal) reqInfoObj[0];
				String reqNumber = (String) reqInfoObj[1];

				String reqInfo[] = new String[2];
				reqInfo[0] = bdIcHeader.toString();
				reqInfo[1] = reqNumber;
				result = reqInfo;
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