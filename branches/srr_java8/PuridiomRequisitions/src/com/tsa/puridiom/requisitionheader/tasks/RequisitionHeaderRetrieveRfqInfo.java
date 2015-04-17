/*
 * Created on October 26, 2006
 */
package com.tsa.puridiom.requisitionheader.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.ReferenceInfo;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author Kathleen
 */
public class RequisitionHeaderRetrieveRfqInfo extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = new ArrayList();
		try
		{
	        DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String icReqHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
			List referenceInfoList = new ArrayList();

			if (!Utility.isEmpty(icReqHeader) && !icReqHeader.equals("0")) {
		        String queryString = "select rfqHeader.icRfqHeader, rfqHeader.rfqNumber, rfqHeader.rfqAmendment from RfqHeader as rfqHeader " +
		        "where rfqHeader.status <> '" +DocumentStatus.RFQ_AMENDED + "' AND rfqHeader.icReqHeader = ? AND rfqHeader.rfqNumber <> 'N/A'" ;

				BigDecimal bdHeader = new BigDecimal(icReqHeader);

				List list = dbs.query(queryString,	new Object[] {bdHeader}, new Type[] {Hibernate.BIG_DECIMAL});

				if (list != null)
				{
				    for (int ix=0; ix < list.size(); ix++)
				    {
				        Object infoObj[] = (Object[]) list.get(ix);
				        BigDecimal icHeader = (BigDecimal) infoObj[0];
				        String formNumber = (String) infoObj[1];
				        String rfqAmendment = (String) infoObj[2];

				        ReferenceInfo referenceInfo = new ReferenceInfo();
				        referenceInfo.setFormType("RFH");
				        referenceInfo.setFormNumber(formNumber);
				        referenceInfo.setRfqAmendment(rfqAmendment);
				        referenceInfo.setIcHeader(icHeader);

				        referenceInfoList.add(referenceInfo);
				    }
				}
			}

			result = referenceInfoList;

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "RequisitionHeaderRetrieveRfqInfo Failed: " + e.getMessage());
		}
		return result;
	}
}
