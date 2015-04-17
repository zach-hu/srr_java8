/**
 * Created on January 19, 2005
 * @author Kelli
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineSetAwardedByList.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PoLineSetAwardedByList extends Task {

	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			String autoAwardedRequisition = HiltonUtility.ckNull((String) incomingRequest.get("autoAwardedRequisition"));
			boolean line_status = true;
			String vendorId = "";
			if(poHeader == null)
			{
				poHeader = new PoHeader();
			}
			else
			{
				vendorId = HiltonUtility.ckNull(poHeader.getVendorId());
				if (autoAwardedRequisition.equalsIgnoreCase("Y") && HiltonUtility.isEmpty(vendorId))
				{
					line_status = false;
				}
			}
		    List poLineList = (List) incomingRequest.get("poLineList");
            String newStatus = (String) incomingRequest.get("newStatus");
		    if (poLineList != null) {
		        for (int i=0; i < poLineList.size(); i++) {
		            PoLine poLine = (PoLine) poLineList.get(i);

		            if (!poLine.getStatus().equals(DocumentStatus.CANCELLED)) {
			            if (Utility.isEmpty(poLine.getReceiptRequired())) {
			                poLine.setReceiptRequired("R");
			            }

                        if (HiltonUtility.ckNull(newStatus).equals(DocumentStatus.CT_AWARDED)) {
                            poLine.setStatus(DocumentStatus.CT_AWARDED);
                        } else {
    			            if (poLine.getReceiptRequired().equals("R") || poLine.getReceiptRequired().equals("E")) {
    			                if (poLine.getQtyReceived().compareTo(new BigDecimal(0)) <= 0) {
    			                    poLine.setStatus(DocumentStatus.PO_AWARDED);
    			                } else if (poLine.getQtyReceived().compareTo(poLine.getQuantity()) >= 0) {
    			                    poLine.setStatus(DocumentStatus.RCV_RECEIVED);
    			                } else {
    			                    poLine.setStatus(DocumentStatus.RCV_PARTIALLYRECEIVED);
    			                }
    			            } else {
    			                //Create receipts
    			                poLine.setStatus(DocumentStatus.RCV_RECEIVED);
    			                poLine.setQtyReceived(poLine.getQuantity());
    			            }
                        }
                        if(!line_status)
                        {
                        	poLine.setStatus(DocumentStatus.PO_INPROGRESS);
                        }

			            poLineList.set(i, poLine);
		            }
		        }
		    }

		    result = poLineList;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
		    this.setStatus(Status.FAILED);
		    throw e;
		}
		return result;
	}

}
