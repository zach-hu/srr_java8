package com.tsa.puridiom.receiptline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

/**
 *
 * @author Alexander
 *
 */
public class ReceiptLineCheckInspection extends Task
{
	@SuppressWarnings("unchecked")
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = Boolean.FALSE;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRecLineString = (String) incomingRequest.get("ReceiptLine_icRecLine");
			String icLineHistoryString = (String) incomingRequest.get("ReceiptLine_icLineHistory");

			if (Utility.isEmpty(icRecLineString))
			{
				icRecLineString = "0";
			}
			BigDecimal icRecLine = new BigDecimal (icRecLineString);
			BigDecimal icLineHistory = new BigDecimal (icLineHistoryString);

			String queryString = "from InspectionHeader inspectionHeader where " +
//			String queryString = "from ReceiptLine receiptLine, PoLine poLine, InspectionHeader inspectionHeader where " +
//					" receiptLine.icRecLine = ? and " +
//					" receiptLine.icPoLine = poLine.icPoLine and " +
//					" poLine.icLineHistory =  inspectionHeader.id.icMsrLine ";
					" inspectionHeader.id.icMsrLine = ? ";

			List resultList = dbs.query(queryString, new Object[] {icLineHistory, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = Boolean.TRUE;
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
