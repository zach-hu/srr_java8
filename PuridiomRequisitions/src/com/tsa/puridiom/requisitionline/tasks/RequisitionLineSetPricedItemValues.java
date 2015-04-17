package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RequisitionLineSetPricedItemValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine");
			RfqBid rfqBid = (RfqBid) incomingRequest.get("rfqBid");
			RequisitionLine reqLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			List updatedReqLineList = (List) incomingRequest.get("updatedReqLineList");

			reqLine.setAsset(rfqLine.getAsset());
			reqLine.setCatalogId(rfqLine.getCatalogId());
			reqLine.setCommodityCode(rfqLine.getCommodity());
			reqLine.setDescription(rfqLine.getDescription());
			reqLine.setItemLocation(rfqLine.getItemLocation());
			reqLine.setItemNumber(rfqLine.getItemNumber());
			reqLine.setItemSource(rfqLine.getSource());
			reqLine.setLineNumber(new BigDecimal((String) incomingRequest.get("requisitionLineNumber")));
			reqLine.setMfgName(rfqLine.getMfgName());
			reqLine.setModelNumber(rfqLine.getModelNumber());
			reqLine.setTaxable(rfqLine.getTaxable());
			reqLine.setUdf1Code(rfqLine.getUdf1Code());
			reqLine.setUdf2Code(rfqLine.getUdf2Code());
			reqLine.setUdf3Code(rfqLine.getUdf3Code());
			reqLine.setUdf4Code(rfqLine.getUdf4Code());
			reqLine.setUdf5Code(rfqLine.getUdf5Code());
			reqLine.setUdf6Code(rfqLine.getUdf6Code());
			reqLine.setUdf7Code(rfqLine.getUdf7Code());
			reqLine.setUdf8Code(rfqLine.getUdf8Code());
			reqLine.setUdf9Code(rfqLine.getUdf9Code());
			reqLine.setUdf10Code(rfqLine.getUdf10Code());
			reqLine.setMemoLine(rfqLine.getMemoLine());

			reqLine.setUmFactor(rfqLine.getUmFactor());
			reqLine.setQuantity(rfqBid.getQuantity());
			reqLine.setUmCode(rfqBid.getUmCode());
			reqLine.setUnitPrice(rfqBid.getUnitPrice());

			if (reqLine.getStatus().equals(DocumentStatus.REQ_INPROGRESS) || reqLine.getStatus().equals(DocumentStatus.RFQ_INPROGRESS))
			{
				reqLine.setStatus(DocumentStatus.RFQ_PRICED);
			}

			rfqLine.setIcSource(reqLine.getIcReqLine());

			result = reqLine;
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