/**
 * Created on August 29, 2005
 * @author Kelli
 * project: HiltonInventory
 * com.tsa.puridiom.invbinlocation.tasks.InvBinLocationSetUpFromRequisitionLine.java
 */
package com.tsa.puridiom.invbinlocation.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class InvBinLocationSetupFromRequisitionLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
		    Map incomingRequest = (Map) object;
		    ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader") ;
		    if (receiptHeader == null) {
		    	receiptHeader = new ReceiptHeader() ;
		    }
			RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			if (requisitionLine != null) {
                String itemNumber = requisitionLine.getItemNumber();


                //                if (!Utility.isEmpty(requisitionLine.getAltItemNumber())) {
//                    itemNumber = requisitionLine.getAltItemNumber();
//                }

				// InvBinLocation_icRecLine is initially set to the icRequisitionLine as a temporary bin record
				//	This will be reset to the ReceiptLine_icRecLine when the tempIc is removed and status changed to perm.
				incomingRequest.put("InvBinLocation_icRecLine", requisitionLine.getIcReqLine().toString());
                incomingRequest.put("InvBinLocation_itemNumber", itemNumber);
				incomingRequest.put("InvLocation_itemNumber", itemNumber);
				incomingRequest.put("InvItem_itemNumber", itemNumber);
				if (receiptHeader.getReceiptType().equals("T")) {
					incomingRequest.put("InvBinLocation_itemLocation", receiptHeader.getShipToCode());
					incomingRequest.put("InvLocation_itemLocation", receiptHeader.getShipToCode());
				} else {
					incomingRequest.put("InvBinLocation_itemLocation", requisitionLine.getItemLocation());
					incomingRequest.put("InvLocation_itemLocation", requisitionLine.getItemLocation());
				}
			} else {
//			    throw new Exception("RequisitionLine entity was not found.");
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			//throw new TsaException(this.getName(), e);
			throw e;
		}
		return super.executeTask(object);
	}

}
