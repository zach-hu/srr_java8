package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.entity.RfqBid;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqBidUnitPriceValidate extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			boolean rfqBidUnitPriceValid = true;

			List rfqVendorList = (List)incomingRequest.get("rfqVendorList");
			List rfqLineList = (List)incomingRequest.get("lineitems");

			if (rfqVendorList != null && rfqVendorList.size() > 0)
			{
				if (rfqLineList != null && rfqLineList.size() > 0)
				{
					for (int i = 0; i < rfqLineList.size(); i++)
					{
						RfqLine rfqLine = (RfqLine)rfqLineList.get(i);

						if (rfqLine != null)
						{
							List rfqBidList = rfqLine.getRfqBidList();

							boolean rfqBidUnitPriceLineValid = false;

							if (rfqBidList != null && rfqBidList.size() > 0)
							{
								for (int j = 0; j < rfqBidList.size(); j++)
								{
									RfqBid rfqBid = (RfqBid)rfqBidList.get(j);

									if (rfqBid != null && (rfqBid.getUnitPrice().compareTo(new BigDecimal(0)) > 0 || !rfqBid.getBidCode().equalsIgnoreCase("NE")))
									{
										rfqBidUnitPriceLineValid = true;
										break;
									}
								}
							}

							rfqBidUnitPriceValid = rfqBidUnitPriceValid && rfqBidUnitPriceLineValid;
						}
					}
				}
			}

			String isRfqBidUnitPriceValid = "Y";
			if (!rfqBidUnitPriceValid) {
				isRfqBidUnitPriceValid = "N";
			}

			incomingRequest.put("isRfqBidUnitPriceValid", isRfqBidUnitPriceValid);

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
