package com.tsa.puridiom.commodity.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Commodity;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class CommodityGetReceiptRequired extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Commodity commodity = (Commodity) incomingRequest.get("commodity");
			if (commodity == null)
			{
				commodity = new Commodity();
			}

			String RequisitionLine_commodityCode = HiltonUtility.ckNull((String)incomingRequest.get("RequisitionLine_commodityCode"));
			String PoLine_commodity = HiltonUtility.ckNull((String)incomingRequest.get("PoLine_commodity"));
			String originalCommodityCode = HiltonUtility.ckNull((String)incomingRequest.get("originalCommodityCode"));

			String receipt_Required = (String) HiltonUtility.ckNull(commodity.getReceiptRequired());
			if (!RequisitionLine_commodityCode.equalsIgnoreCase(originalCommodityCode)) {
				incomingRequest.put("RequisitionLine_receiptRequired", receipt_Required);
			}
			if (!PoLine_commodity.equalsIgnoreCase(originalCommodityCode)) {
				incomingRequest.put("PoLine_receiptRequired", receipt_Required);
			}
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
