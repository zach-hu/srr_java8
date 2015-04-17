package com.tsa.puridiom.receiptheader.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ReceiptHeaderSetValuesFromPoHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			if (receiptHeader == null)
			{
				receiptHeader = new ReceiptHeader();
			}
			if (poHeader == null)
			{
				return receiptHeader;
			}

			receiptHeader.setIcPoHeader(poHeader.getIcPoHeader());
			receiptHeader.setVendorId(poHeader.getVendorId());
			receiptHeader.setRefNumber(poHeader.getPoNumber());
			receiptHeader.setRefRelease(String.valueOf(poHeader.getReleaseNumber()));
			receiptHeader.setRefDate(poHeader.getPoDate());
			receiptHeader.setRefType(poHeader.getPoType());
			receiptHeader.setVendorName(poHeader.getVendorName());
			receiptHeader.setFiscalYear(poHeader.getFiscalYear());
            receiptHeader.setShipToCode(poHeader.getShipToCode());
            receiptHeader.setShipToInv(poHeader.getShipToInv());
            receiptHeader.setIcReqHeader(poHeader.getIcReqHeader());
            receiptHeader.setItemLocation(poHeader.getItemLocation());
            receiptHeader.setCorrosionEval(poHeader.getCorrosionEval());

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