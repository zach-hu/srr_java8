package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsa.puridiom.entity.RfqBidHistory;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


public class RfqBidHistoryListTotalPrice extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List rfqbidhistorylist = (List)incomingRequest.get("rfqBidHistoryList");
			List UnitPriceList = new ArrayList();

			if (rfqbidhistorylist != null && !rfqbidhistorylist.isEmpty()) {
				RfqBidHistory bid0 = (RfqBidHistory)rfqbidhistorylist.get(0);
				String sequence = bid0.getComp_id().getBidSequence().toString();

				BigDecimal totalPrice = new BigDecimal("0");

				for(int i = 0; i < rfqbidhistorylist.size(); i++) {
			        RfqBidHistory bid = (RfqBidHistory)rfqbidhistorylist.get(i);
			        String newSequence = bid.getComp_id().getBidSequence().toString();
			        if (newSequence.compareTo(sequence) == 0) {
			        	String newPrice = bid.getUnitPrice().toString();
			        	totalPrice = totalPrice.add(new BigDecimal(newPrice));
			        }
			        else {
			        	UnitPriceList.add(totalPrice);
			        	totalPrice = bid.getUnitPrice();
			        }
			        sequence = newSequence;
			        if (i == rfqbidhistorylist.size()-1) {
			        	UnitPriceList.add(totalPrice);
			        }
			    }
			}

			result = UnitPriceList;
			this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		}
		return result ;
	}
}