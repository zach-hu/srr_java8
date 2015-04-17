/*
 * Created on October 27, 2006
 */
package com.tsa.puridiom.rfqheader.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.ReferenceInfo;
import com.tsa.puridiom.common.documents.RfqInfo;
import com.tsa.puridiom.common.utility.HiltonUtility;
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
public class RfqHeaderRetrieveRecentlyClosed extends Task
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
	        String oid = (String) incomingRequest.get("organizationId");
	        String vendorId = (String)incomingRequest.get("vendorId");
	        List rfqInfoList = new ArrayList();

	        if (!Utility.isEmpty(vendorId))
	        {
		        Date 	d_today = new Date();
		        Date 	d_lastMonth		= new Date(d_today.getYear(), d_today.getMonth()-1, d_today.getDate());
		        String	s_lastMonth		= HiltonUtility.getFormattedDate(d_lastMonth, oid, "yyyy-MM-dd");
		        String	s_today = HiltonUtility.getFormattedDate(d_today, oid, "yyyy-MM-dd");

		        String queryString = "select DISTINCT RfqHeader.icRfqHeader, RfqHeader.rfqNumber, RfqHeader.rfqAmendment, RfqHeader.status, RfqHeader.rfqDescription " +
		        "from RfqHeader as RfqHeader, RfqVendor as RfqVendor " +
		        "where RfqHeader.icRfqHeader = RfqVendor.id.icRfqHeader AND RfqHeader.webpost <> 'N' AND RfqHeader.webpost <> ' ' " +
		        "AND RfqHeader.status >= '" + DocumentStatus.RFQ_OPENSOLICITATION + "'  "  +
		        "AND (RfqHeader.bidAccess = 'U' OR RfqHeader.bidAccess = 'I' OR (RfqHeader.bidAccess = 'R' AND RfqVendor.id.vendorId = ?) ) " +
		        "AND RfqHeader.dueDate >= TO_DATE('" + s_lastMonth + "', 'yyyy-MM-dd') AND RfqHeader.dueDate < TO_DATE('" +s_today + "', 'yyyy-MM-dd') ";

		        List list = dbs.query(queryString,	new Object[] {vendorId}, new Type[] {Hibernate.STRING});

		        if (list != null)
		        {
		        	for (int i = 0; i < list.size(); i++)
		        	{
		        		Object infoObj[] = (Object[]) list.get(i);
		        		BigDecimal icRfqHeader = (BigDecimal) infoObj[0];
				        String rfqNumber = (String) infoObj[1];
				        String rfqAmendment = (String) infoObj[2];
				        String status = (String) infoObj[3];
				        String rfqDescription = (String) infoObj[4];

				        RfqInfo rfqInfo = new RfqInfo();
				        rfqInfo.setIcRfqHeader(icRfqHeader);
				        rfqInfo.setRfqNumber(rfqNumber);
				        rfqInfo.setRfqAmendment(rfqAmendment);
				        rfqInfo.setStatus(status);
				        rfqInfo.setRfqDescription(rfqDescription);

				        rfqInfoList.add(rfqInfo);
		        	}
		        }
	        }

	        result = rfqInfoList;

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "RfqHeaderRetrieveRecentlyClosed Failed: " + e.getMessage());
		}
		return result;
	}
}
