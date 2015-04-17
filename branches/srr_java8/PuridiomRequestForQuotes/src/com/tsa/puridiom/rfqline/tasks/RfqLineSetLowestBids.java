package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqLineSetLowestBids extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			List	rfqLineList = (List) incomingRequest.get("rfqLineList");
			BigDecimal bdZero = new BigDecimal("0.00000");
			BigDecimal bdLowBid = new BigDecimal("0.00000");
			String	lowVendor = "";

			if (rfqLineList == null) {
				// no lines - no bids
				//	do nothing
				this.setStatus(Status.SUCCEEDED);
				return null;
			}

			for (int i=0; i < rfqLineList.size(); i++) {
				RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
				if (rfqLine != null) {
					List bidList = rfqLine.getRfqBidList();
					if (bidList != null) {
						for (int ib = 0; ib < bidList.size(); ib++) {
							RfqBid rfqBid = (RfqBid) bidList.get(ib);
							if (rfqBid != null) {
								BigDecimal bdUnitPrice = rfqBid.getUnitPrice();
								if (bdUnitPrice != null && bdUnitPrice.compareTo(bdZero) == 1) {
									if (bdLowBid.compareTo(bdZero) == 0 || bdUnitPrice.compareTo(bdLowBid) == -1) {
										bdLowBid = bdUnitPrice;
										lowVendor = rfqBid.getComp_id().getVendorId();
									}
								}
							}
						}
					}

					rfqLine.setLowestBid(bdLowBid);
					rfqLine.setLowestVendorId(lowVendor);

					bdLowBid = bdZero;
					lowVendor = "";
				}
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}
