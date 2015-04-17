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
public class ReceiptLineCheckMarkTag extends Task
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

			if (Utility.isEmpty(icRecLineString))
			{
				icRecLineString = "0";
			}
			BigDecimal icRecLine = new BigDecimal (icRecLineString);

			String queryString = "from ReceiptLine as receiptLine, PoLine as poLine, RequisitionLine requisitionLine where " +
					" receiptLine.icRecLine = ? and " +
					" receiptLine.icPoLine = poLine.icPoLine and " +
					" poLine.udf1Code =  'Y' ";

			List resultList = dbs.query(queryString, new Object[] {icRecLine, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

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
