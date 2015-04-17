package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.rule.operator.OperatorTypes;
import com.tsagate.foundation.utility.*;
import java.util.*;

public class RfqBidFilterListById extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			String	icRfqHeader = (String) incomingRequest.get("icRfqHeader");
			String	icRfqLine = (String) incomingRequest.get("icRfqLine");
			String	vendorCode = (String) incomingRequest.get("vendorCode");
			List	rfqBidList = (List) incomingRequest.get("rfqBidList");
			RfqBid rfqBid = new RfqBid();
			
			FilterList bidFilterList = new FilterList(rfqBidList);
			bidFilterList.addFilter("icRfqHeader", OperatorTypes.equal, icRfqHeader);
			bidFilterList.addLogicValue("AND");
			bidFilterList.addFilter("icRfqLine", OperatorTypes.equal, icRfqLine);
			bidFilterList.addLogicValue("AND");
			bidFilterList.addFilter("vendorId", OperatorTypes.equal, vendorCode);
			List rfqBids = bidFilterList.filter(); 
			
			if (rfqBids != null && rfqBids.size() > 0) {
				rfqBid = (RfqBid) rfqBids.get(0);
			}
			
			result = rfqBid;
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