package com.tsa.puridiom.receiptline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * 
 * @author Alexander
 *
 */
public class ReceiptLineRetrieveByInspection extends Task 
{
	@SuppressWarnings("unchecked")
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List resultList = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRecHeaderString = (String) incomingRequest.get("ReceiptLine_icRecHeader");
			BigDecimal icRecHeader = new BigDecimal(icRecHeaderString);
			
			String queryString = "from ReceiptLine as receiptline where receiptline.inspectionRequired = 'Y' " +
									" and receiptline.inspectionRequired = 'Y' " +
									" and receiptline.status = ? " +
									" and receiptline.icRecHeader = ?";
			
			resultList = dbs.query(queryString, new Object[] {DocumentStatus.RCV_INPROGRESS, icRecHeader} , 
									new Type[] {Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) 
		{
			Log.error(this, e);
			this.setStatus(Status.FAILED);
			throw new TsaException("ReceiptLineRetrieveByInspection was not Retrieved", e);
		}
		return resultList;
	}

}