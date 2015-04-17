package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqSetLowestBidSubtotal extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			RfqCalculateVendorSubtotals calculateSubtotalTask = new RfqCalculateVendorSubtotals();
			calculateSubtotalTask.executeTask(incomingRequest);

		    RfqHeader	rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			BigDecimal bdZero = new BigDecimal("0.00000");
			BigDecimal bdLowSubtotal = new BigDecimal("0.00000");
			String	lowVendor = "";
			
			if (rfqHeader == null) {
				return null;
			}
			
			List	rfqVendorList = rfqHeader.getRfqVendorList();
			
			if (rfqVendorList != null) {
			    for (int i = 0; i < rfqVendorList.size(); i++) {
			        RfqVendor rfqVendor = (RfqVendor) rfqVendorList.get(i);
			        BigDecimal bdVendorSubtotal = rfqVendor.getBidSubtotal();
					
					if (bdVendorSubtotal != null && bdVendorSubtotal.compareTo(bdZero) == 1) {
						if (bdLowSubtotal.compareTo(bdZero) == 0 || bdVendorSubtotal.compareTo(bdLowSubtotal) == -1) {
							bdLowSubtotal = bdVendorSubtotal;
							lowVendor = rfqVendor.getComp_id().getVendorId();
						}
					}
			    }
			    
				rfqHeader.setLowestBidSubtotal(bdLowSubtotal);
				rfqHeader.setLowestVendorId(lowVendor);
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
