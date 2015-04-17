package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.math.BigDecimal;
import java.util.*;

public class RfqLineSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain rfqLine */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			RfqLine	originalRfqLine = (RfqLine) incomingRequest.get("originalRfqLine");
			RfqLine	rfqLine = new RfqLine();

			rfqLine.setIcRfqLine(new BigDecimal(ukg.getUniqueKey().toString()));
			rfqLine.setIcRfqHeader(new BigDecimal((String) incomingRequest.get("newRfqLine_icRfqHeader")));
			rfqLine.setRfqLine(originalRfqLine.getRfqLine());
			rfqLine.setRfqNumber((String) incomingRequest.get("newRfqLine_rfqNumber"));
			rfqLine.setSource(originalRfqLine.getSource());
			rfqLine.setItemNumber(originalRfqLine.getItemNumber());
			rfqLine.setUmCode(originalRfqLine.getUmCode());
			rfqLine.setQuantity(originalRfqLine.getQuantity());
			rfqLine.setTaxable(originalRfqLine.getTaxable());
			if (incomingRequest.containsKey("newRfqLine_status")) {
			    String	status = (String) incomingRequest.get("newRfqLine_status");
			    String	originalStatus = rfqLine.getStatus();
			    if (status.endsWith(DocumentStatus.RFQ_OPENAMENDMENT) && originalStatus.equals(DocumentStatus.CANCELLED)) {
			        rfqLine.setStatus(originalStatus);
			    }
			    rfqLine.setStatus(status);
			} else {
			    rfqLine.setStatus(DocumentStatus.RFQ_INPROGRESS);
			}
			rfqLine.setCommodity(originalRfqLine.getCommodity());
			rfqLine.setIcSource(originalRfqLine.getIcSource());
			rfqLine.setCommentFlag(originalRfqLine.getCommentFlag());
			rfqLine.setAsset(originalRfqLine.getAsset());
			rfqLine.setSplits(originalRfqLine.getSplits());
			rfqLine.setCatalogId(originalRfqLine.getCatalogId());
			rfqLine.setUmFactor(originalRfqLine.getUmFactor());
			rfqLine.setShiptoFlag(originalRfqLine.getShiptoFlag());
			rfqLine.setAllocated(new BigDecimal("0"));
			rfqLine.setMfgName(originalRfqLine.getMfgName());
			rfqLine.setModelNumber(originalRfqLine.getModelNumber());
			rfqLine.setUdf1Code(originalRfqLine.getUdf1Code());
			rfqLine.setUdf2Code(originalRfqLine.getUdf2Code());
			rfqLine.setUdf3Code(originalRfqLine.getUdf3Code());
			rfqLine.setUdf4Code(originalRfqLine.getUdf4Code());
			rfqLine.setUdf5Code(originalRfqLine.getUdf5Code());
			rfqLine.setUdf6Code(originalRfqLine.getUdf6Code());
			rfqLine.setUdf7Code(originalRfqLine.getUdf7Code());
			rfqLine.setUdf8Code(originalRfqLine.getUdf8Code());
			rfqLine.setUdf9Code(originalRfqLine.getUdf9Code());
			rfqLine.setUdf10Code(originalRfqLine.getUdf10Code());
			rfqLine.setMemoLine(originalRfqLine.getMemoLine());

			rfqLine.setLineRevNo("");
			rfqLine.setIcLineHistory(new BigDecimal(ukg.getUniqueKey().toString()));
			rfqLine.setItemLocation(originalRfqLine.getItemLocation());
			rfqLine.setDescription(originalRfqLine.getDescription());
			rfqLine.setTaxCode(originalRfqLine.getTaxCode());
			rfqLine.setRouting(originalRfqLine.getRouting());
			rfqLine.setReceiptRequired(originalRfqLine.getReceiptRequired());

			incomingRequest.put("rfqLine", rfqLine);

			result = rfqLine;
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
