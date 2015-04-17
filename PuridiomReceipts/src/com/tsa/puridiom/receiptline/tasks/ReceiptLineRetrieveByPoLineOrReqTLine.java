package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.receiptheader.tasks.ReceiptHeaderRetrieveById;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptLineRetrieveByPoLineOrReqTLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try {
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icPoLine = (String) incomingRequest.get("ReceiptLine_icPoLine");
			String icReqLine = (String) incomingRequest.get("ReceiptLine_icReqLine");
			String reqLineType= (String) incomingRequest.get("reqLineTypeSourced");

			if (Utility.isEmpty(icPoLine)) {
			    PoLine poLine = (PoLine) incomingRequest.get("poLine");
			    if (poLine != null) {
			        icPoLine = String.valueOf(poLine.getIcPoLine());
			    }
			}
			if (!Utility.isEmpty(icPoLine) && !icPoLine.equals("0")) {
				String queryString = "from ReceiptLine as receiptline where receiptline.icPoLine = " + icPoLine +
					" and receiptline.status > '" + DocumentStatus.RCV_INPROGRESS + "' and receiptline.qtyReceived > 0" ;

				result = dbs.query(queryString.toString()) ;
				if(result != null && ((List)result).size()>0)
				{
					List receiptHeaderList = new ArrayList();
					for(int r = 0 ; r < ((List)result).size() ; r++)
					{
						Map incoRLr = new HashMap();
						incoRLr.put("dbsession", dbs);
						incoRLr.put("ReceiptHeader_icRecHeader", ((ReceiptLine)(((List)result)).get(r)).getIcRecHeader().toString());
						ReceiptHeaderRetrieveById  rlr = new ReceiptHeaderRetrieveById();
						ReceiptHeader rgh = (ReceiptHeader)rlr.executeTask(incoRLr);
						receiptHeaderList.add(rgh);
					}
					incomingRequest.put("receiptHeaderList", receiptHeaderList);
				}
			}else if(!Utility.isEmpty(icReqLine) && reqLineType.equals("T")){
				String queryString = "from ReceiptLine as receiptline where receiptline.icReqLine = " + icReqLine +
						" and receiptline.status > '" + DocumentStatus.RCV_INPROGRESS + "' and receiptline.qtyReceived > 0" ;

					result = dbs.query(queryString.toString()) ;
					if(result != null && ((List)result).size()>0)
					{
						List receiptHeaderList = new ArrayList();
						for(int r = 0 ; r < ((List)result).size() ; r++)
						{
							Map incoRLr = new HashMap();
							incoRLr.put("dbsession", dbs);
							incoRLr.put("ReceiptHeader_icRecHeader", ((ReceiptLine)(((List)result)).get(r)).getIcRecHeader().toString());
							ReceiptHeaderRetrieveById  rlr = new ReceiptHeaderRetrieveById();
							ReceiptHeader rgh = (ReceiptHeader)rlr.executeTask(incoRLr);
							receiptHeaderList.add(rgh);
						}
						incomingRequest.put("receiptHeaderList", receiptHeaderList);
					}
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