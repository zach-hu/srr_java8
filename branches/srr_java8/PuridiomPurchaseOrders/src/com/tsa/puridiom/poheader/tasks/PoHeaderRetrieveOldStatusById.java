package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoHeaderRetrieveOldStatusById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icPoHeaderString = (String) incomingRequest.get("icPoHeader");
			BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
			String status = "";
			boolean awarded = true;
			boolean received = true;
			String queryString = "from PoLine as PoLine where PoLine.icPoHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {icPoHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				for(int x = 0; x < resultList.size(); x++)
				{
	            	PoLine icPoLine = (PoLine)resultList.get(x);
	            	BigDecimal qty = HiltonUtility.ckNull((BigDecimal) icPoLine.getQuantity());
	            	BigDecimal qtyRcv = HiltonUtility.ckNull((BigDecimal) icPoLine.getQtyReceived());
	            	qtyRcv = qtyRcv.abs();
	            	qty = qty.abs();
	            	if (qtyRcv.compareTo(new BigDecimal(0)) == 0 && awarded) {
	            		status = DocumentStatus.PO_AWARDED;
	            		received = false;
	            	} else if (qtyRcv.compareTo(qty) >= 0 && received) {
	            		status = DocumentStatus.RCV_RECEIVED;
	            		awarded = false;
		            } else {
		            	status = DocumentStatus.RCV_PARTIALLYRECEIVED;
		            	awarded = false;
		            	received = false;
		            }
				}
			}
			else
			{
				status = DocumentStatus.PO_INPROGRESS;
			}
			incomingRequest.put("OldStatus", status);
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