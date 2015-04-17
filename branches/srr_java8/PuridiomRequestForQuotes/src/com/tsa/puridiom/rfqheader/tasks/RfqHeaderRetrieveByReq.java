package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class RfqHeaderRetrieveByReq extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String icReqHeaderString = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
			if (HiltonUtility.isEmpty(icReqHeaderString))
			{
				RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
				if (requisitionHeader != null) {
					icReqHeaderString = requisitionHeader.getIcReqHeader().toString();
				}
			}

			if (!HiltonUtility.isEmpty(icReqHeaderString))
			{
				BigDecimal icReqHeader = new BigDecimal(icReqHeaderString);

				String queryString = "from RfqHeader as RfqHeader where RfqHeader.icReqHeader = ? ";
				List resultList = dbs.query(queryString, new Object[] { icReqHeader }, new Type[] { Hibernate.BIG_DECIMAL });

				if (resultList != null && resultList.size() > 0)
				{
					result = resultList.get(0);

					RfqHeader rfqHeader = (RfqHeader)result;
					if (rfqHeader != null) {
						incomingRequest.put("RfqHeader_icRfqHeader", rfqHeader.getIcRfqHeader().toString());
						incomingRequest.put("viewType", "WIZARD");
					}
				}
			}

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}
}
