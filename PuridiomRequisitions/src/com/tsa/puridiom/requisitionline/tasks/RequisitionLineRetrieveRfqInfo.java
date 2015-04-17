/*
 * Created on October 26, 2006
 */
package com.tsa.puridiom.requisitionline.tasks;

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
public class RequisitionLineRetrieveRfqInfo extends Task
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
			String icReqLine = (String)incomingRequest.get("RequisitionLine_icReqLine");
			List referenceInfoList = new ArrayList();

			if (!Utility.isEmpty(icReqLine) && !icReqLine.equals("0")) {
		        String queryString = "select rfqLine.icRfqHeader, rfqLine.icRfqLine, rfqLine.rfqNumber, rfqHeader.rfqAmendment " +
		        "from RfqHeader as rfqHeader, RfqLine as rfqLine " +
		        "where rfqLine.icRfqHeader = rfqHeader.icRfqHeader AND rfqLine.status <> '" +DocumentStatus.RFQ_AMENDED + "' AND rfqLine.icReqLine = ? AND rfqLine.rfqNumber <> 'N/A'" ;

				BigDecimal bdLine = new BigDecimal(icReqLine);

				List list = dbs.query(queryString,	new Object[] {bdLine}, new Type[] {Hibernate.BIG_DECIMAL});

				if (list != null)
				{
				    for (int ix=0; ix < list.size(); ix++)
				    {
				        Object infoObj[] = (Object[]) list.get(ix);
				        BigDecimal icHeader = (BigDecimal) infoObj[0];
				        BigDecimal icLine = (BigDecimal) infoObj[1];
				        String formNumber = (String) infoObj[2];
				        String rfqAmendment = (String) infoObj[3];

				        ReferenceInfo referenceInfo = new ReferenceInfo();
				        referenceInfo.setFormType("POL");
				        referenceInfo.setFormNumber(formNumber);
				        referenceInfo.setRfqAmendment(rfqAmendment);
				        referenceInfo.setIcHeader(icHeader);
				        referenceInfo.setIcLine(icLine);

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
			Log.error(this, "RequisitionLineRetrieveRfqInfo Failed: " + e.getMessage());
		}
		return result;
	}
}
