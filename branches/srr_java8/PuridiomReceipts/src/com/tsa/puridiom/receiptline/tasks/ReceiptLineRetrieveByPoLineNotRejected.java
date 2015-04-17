package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.List;
import java.util.Map;

public class ReceiptLineRetrieveByPoLineNotRejected extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try {
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icPoLine = (String) incomingRequest.get("ReceiptLine_icPoLine");

			if (Utility.isEmpty(icPoLine)) {
			    icPoLine = (String) incomingRequest.get("PoLine_icPoLine");
			}
			if (Utility.isEmpty(icPoLine)) {
			    icPoLine = (String) incomingRequest.get("RequisitionLine_icPoLine");
			}
			if (Utility.isEmpty(icPoLine)) {
			    PoLine poLine = (PoLine) incomingRequest.get("poLine");
			    if (poLine != null) {
			        icPoLine = String.valueOf(poLine.getIcPoLine());
			    }
			}
			if (Utility.isEmpty(icPoLine)) {
			    RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			    if (requisitionLine != null) {
			        icPoLine = String.valueOf(requisitionLine.getIcPoLine());
			    }
			}
			if (Utility.isEmpty(icPoLine)) {
				ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
				if (receiptLine != null) {
					icPoLine = String.valueOf(receiptLine.getIcPoLine());
				}
			}

			if (!Utility.isEmpty(icPoLine)) {
				String queryString = "from ReceiptLine as receiptline where receiptline.icPoLine = " + icPoLine +
					" and (receiptline.status > '" + DocumentStatus.RCV_INPROGRESS + "' AND receiptline.status < '4300' )" ;

				result = dbs.query(queryString.toString()) ;
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result ;
	}
}