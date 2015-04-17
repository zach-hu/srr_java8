package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RfqLineCopy extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain rfqLine */
		    RfqLine	originalRfqLine = (RfqLine) incomingRequest.get("rfqLine");
			RfqLine	rfqLine = new RfqLine();

			rfqLine.setIcRfqLine(originalRfqLine.getIcRfqLine());
			rfqLine.setIcRfqHeader(originalRfqLine.getIcRfqHeader());
			rfqLine.setRfqLine(originalRfqLine.getRfqLine());
			rfqLine.setRfqNumber(originalRfqLine.getRfqNumber());
			rfqLine.setSource(originalRfqLine.getSource());
			rfqLine.setItemNumber(originalRfqLine.getItemNumber());
			rfqLine.setUmCode(originalRfqLine.getUmCode());
			rfqLine.setQuantity(originalRfqLine.getQuantity());
			rfqLine.setTaxable(originalRfqLine.getTaxable());
			rfqLine.setStatus(originalRfqLine.getStatus());
			rfqLine.setCommodity(originalRfqLine.getCommodity());
			rfqLine.setIcSource(originalRfqLine.getIcSource());
			rfqLine.setCommentFlag(originalRfqLine.getCommentFlag());
			rfqLine.setAsset(originalRfqLine.getAsset());
			rfqLine.setSplits(originalRfqLine.getSplits());
			rfqLine.setCatalogId(originalRfqLine.getCatalogId());
			rfqLine.setUmFactor(originalRfqLine.getUmFactor());
			rfqLine.setIcReqLine(originalRfqLine.getIcReqLine());
			rfqLine.setShiptoFlag(originalRfqLine.getShiptoFlag());
			rfqLine.setAllocated(originalRfqLine.getAllocated());
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

			rfqLine.setLineRevNo(originalRfqLine.getLineRevNo());
			rfqLine.setIcLineHistory(originalRfqLine.getIcLineHistory());
			rfqLine.setItemLocation(originalRfqLine.getItemLocation());
			rfqLine.setDescription(originalRfqLine.getDescription());
			rfqLine.setTaxCode(originalRfqLine.getTaxCode());
			rfqLine.setRouting(originalRfqLine.getRouting());
			rfqLine.setReceiptRequired(originalRfqLine.getReceiptRequired());
			rfqLine.setIcAccount(originalRfqLine.getIcAccount());


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
