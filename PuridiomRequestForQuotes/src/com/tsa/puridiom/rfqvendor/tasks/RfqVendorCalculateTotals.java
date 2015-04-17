/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.rfqvendor.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RfqVendor;
import com.tsa.puridiom.entity.RfqVendorPK;
import com.tsa.puridiom.entity.RfqBid;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import java.math.*;

public class RfqVendorCalculateTotals extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{

			List rfqVendorList = (List)incomingRequest.get("rfqVendorList");


			int i_size = 0;

			if (rfqVendorList != null)
			{
				i_size = rfqVendorList.size();
				for(int i = 0;i < i_size; i++)
				{
					RfqVendor rfqVendor = (RfqVendor)rfqVendorList.get(i);
					RfqVendorPK rfqVendorPK = rfqVendor.getComp_id();

					BigDecimal bd_shippingCharges = rfqVendor.getShippingCharges();
					BigDecimal bd_taxAmount = rfqVendor.getTaxAmount();
					BigDecimal bd_total = new BigDecimal(0);
					BigDecimal bd_otherCharges = rfqVendor.getOtherCharges();
					bd_total = bd_total.add(bd_otherCharges);
					bd_total = bd_total.add(bd_shippingCharges);
					bd_total = bd_total.add(bd_taxAmount);

					List bidList = rfqVendor.getRfqBidList();
					if (bidList != null)
					{
						for (int j = 0; j < bidList.size(); j++)
						{
							RfqBid rfqBid = (RfqBid) bidList.get(j);
							BigDecimal bd_quantity = rfqBid.getQuantity();
							BigDecimal bd_unitprice = rfqBid.getUnitPrice();
							BigDecimal bd_umfactor = rfqBid.getUmFactor();
							if (bd_umfactor.compareTo(new BigDecimal(0)) == 0)
							{
								bd_umfactor = new BigDecimal(1);
							}
							BigDecimal bd_extprice = (bd_quantity.multiply(bd_unitprice)).multiply(bd_umfactor);
							bd_total = bd_total.add(bd_extprice);
						}
					}
					rfqVendor.setBidTotal(bd_total);
					rfqVendorList.set(i, rfqVendor);
			  	}
			}

			this.setStatus(Status.SUCCEEDED);
			result = rfqVendorList;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}

		return result ;
	}
}
