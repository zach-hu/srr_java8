package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;

public class SetupRfqBidHistoryList extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List vendorList = new ArrayList();
		try
		{
			Object RfqBid_vendorIdObj		= incomingRequest.get("RfqBid_vendorId");
			String RfqBid_vendorId[] 		= null;
			Object RfqBid_unitPriceObj		= incomingRequest.get("RfqBid_unitPrice");
			String RfqBid_unitPrice[]		= null;
			Object RfqBid_unitPriceOldObj	= incomingRequest.get("RfqBid_unitPriceOld");
			String RfqBid_unitPriceOld[]	= null;

			if (RfqBid_vendorIdObj instanceof String[]) {
				RfqBid_vendorId = (String[]) RfqBid_vendorIdObj;
				RfqBid_unitPrice = (String[]) RfqBid_unitPriceObj;
				RfqBid_unitPriceOld = (String[]) RfqBid_unitPriceOldObj;
			}
			else {
				RfqBid_vendorId = new String[1];		RfqBid_vendorId[0] = (String) RfqBid_vendorIdObj;
				RfqBid_unitPrice = new String[1];		RfqBid_unitPrice[0] = (String) RfqBid_unitPriceObj;
				RfqBid_unitPriceOld = new String[1];	RfqBid_unitPriceOld[0] = (String) RfqBid_unitPriceOldObj;
			}

			vendorList = SetVendorList(vendorList,RfqBid_vendorId,RfqBid_unitPrice,RfqBid_unitPriceOld);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return vendorList;
	}

	private List SetVendorList(List vendorList, String[] rfqBid_vendorId, String[] rfqBid_unitPrice, String[] rfqBid_unitPriceOld) {
		try
		{
			for(int i=0; i<rfqBid_vendorId.length; i++) {
				BigDecimal unitPrice = new BigDecimal(rfqBid_unitPrice[i]);
				BigDecimal unitPriceOld = new BigDecimal(rfqBid_unitPriceOld[i]);
				if (unitPrice.compareTo(unitPriceOld)!= 0){
					List vendorItem = new ArrayList();
					vendorItem.add(rfqBid_vendorId[i]);
					vendorItem.add(unitPrice.toString());
					vendorList.add(vendorItem);
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return vendorList;
	}

}