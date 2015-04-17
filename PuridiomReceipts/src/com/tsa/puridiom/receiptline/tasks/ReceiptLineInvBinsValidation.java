package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

public class ReceiptLineInvBinsValidation extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			List	receiptLineList = (List) incomingRequest.get("receiptLineList");
			String	receiptNumber = (String) incomingRequest.get("ReceiptHeader_receiptNumber");
			boolean qtyError = false ;
			String	binQuantityMessage = "" ;

			for (int i=0; i < receiptLineList.size(); i++) {
				ReceiptLine receiptLine = (ReceiptLine) receiptLineList.get(i);
				String itemLocation = receiptLine.getItemLocation() ;
				BigDecimal bdReceived = receiptLine.getQtyReceived() ;

				if (! HiltonUtility.isEmpty(receiptLine.getItemLocation()) && receiptLine.getQtyReceived().compareTo(new BigDecimal(0)) != 0) {

					dbs = (DBSession)incomingRequest.get("dbsession") ;
					StringBuffer queryString = new StringBuffer("from InvBinLocation as invbinlocation where 1=1 ");
					BigDecimal qtyTot = new BigDecimal(0) ;

				    String tempIc = receiptLine.getIcRecLine().toString() ;
				    queryString.append(" AND invbinlocation.tempIc = " + tempIc + "");

					List resultList = dbs.query(queryString.toString()) ;
					if (resultList != null) {
						for (int li=0; li < resultList.size(); li++) {
							InvBinLocation binLoc = (InvBinLocation) resultList.get(li) ;
							qtyTot = qtyTot.add(binLoc.getQtyOnHand()) ;
						}
						if (qtyTot.compareTo(receiptLine.getQtyReceived()) != 0) {
							binQuantityMessage = binQuantityMessage + " Line " + receiptLine.getReceiptLine().toString() +  ", ";
							qtyError = true ;
						}
					} else {
						binQuantityMessage = binQuantityMessage + " Line " + receiptLine.getReceiptLine().toString() + ", " ;
						qtyError = true ;
					}
				}
			}
			System.out.println("Error=" + qtyError);
			if (! qtyError) {
				incomingRequest.put("processedValidateError", "false") ;
				incomingRequest.put("processedErrorText", "") ;
				result = "false" ;
			} else {
				incomingRequest.put("processedValidateError", "true") ;
				if (binQuantityMessage.endsWith(", ")) {
					binQuantityMessage = binQuantityMessage.substring(0, binQuantityMessage.length() - 2) + ".";
				}
				incomingRequest.put("processedErrorText", binQuantityMessage) ;
				result = "true" ;
			}

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}

}