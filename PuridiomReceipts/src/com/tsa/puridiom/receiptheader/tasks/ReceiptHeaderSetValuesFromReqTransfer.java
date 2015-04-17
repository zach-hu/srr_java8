package com.tsa.puridiom.receiptheader.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ReceiptHeaderSetValuesFromReqTransfer extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			if (receiptHeader == null)
			{
				receiptHeader = new ReceiptHeader();
			}
			if (requisitionHeader == null)
			{
				return receiptHeader;
			}

			receiptHeader.setIcReqHeader(requisitionHeader.getIcReqHeader());
			receiptHeader.setVendorId(requisitionHeader.getVendorId());
			receiptHeader.setRefNumber(requisitionHeader.getRequisitionNumber());
			//receiptHeader.setRefRelease(String.valueOf(requisitionHeader.getrel.getReleaseNumber()));
			receiptHeader.setRefDate(requisitionHeader.getRequisitionDate());
			receiptHeader.setRefType(requisitionHeader.getRequisitionType());
			receiptHeader.setVendorName(requisitionHeader.getVendorName());
			receiptHeader.setFiscalYear(requisitionHeader.getFiscalYear());
            receiptHeader.setShipToCode(requisitionHeader.getShipToCode());
            receiptHeader.setShipToInv("Y");		//SHIP TO INV
            receiptHeader.setCorrosionEval(requisitionHeader.getCorrosionEval());

			result = receiptHeader;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}