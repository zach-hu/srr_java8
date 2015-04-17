package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqCalculateVendorSubtotals extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			RfqHeader	rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			List	rfqLineList = (List) incomingRequest.get("rfqLineList");
			Map	vendorBids = new HashMap();
			BigDecimal bdZero = new BigDecimal("0.00000");
			//BigDecimal bdLowSubtotal = new BigDecimal("0.00000");
			//String	lowVendor = "";

			if (rfqHeader == null || rfqLineList == null) {
				// no header, no lines - no bids
				//	do nothing
				return null;
			}

			for (int i=0; i < rfqLineList.size(); i++) {
				RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
				if (rfqLine != null) {
					List bidList = rfqLine.getRfqBidList();
					BigDecimal bdQuantity = rfqLine.getQuantity();
					BigDecimal bdUmFactor = rfqLine.getUmFactor();

					if (bidList != null) {
						if (bdUmFactor.compareTo(bdZero) <= 0) {
							bdUmFactor = new BigDecimal(1);
						}

						for (int ib = 0; ib < bidList.size(); ib++) {
							RfqBid rfqBid = (RfqBid) bidList.get(ib);
							if (rfqBid != null) {
								BigDecimal bdUnitPrice = rfqBid.getUnitPrice();
								String	vendorId = rfqBid.getComp_id().getVendorId();

								if (bdUnitPrice != null && bdUnitPrice.compareTo(bdZero) == 1) {
									BigDecimal bdVendorSubtotal = bdZero;

									if (vendorBids.containsKey(vendorId)) {
										bdVendorSubtotal = (BigDecimal) vendorBids.get(vendorId);
									}
									bdVendorSubtotal = bdVendorSubtotal.add(bdUnitPrice.multiply(bdQuantity).multiply(bdUmFactor));
									vendorBids.put(vendorId, bdVendorSubtotal);
								}
							}
						}
					}
				}
			}

			List	rfqVendorList = (List) incomingRequest.get("rfqVendorList");

			if (rfqVendorList != null)
			{
			    for (int i = 0; i < rfqVendorList.size(); i++)
			    {
			        RfqVendor rfqVendor = (RfqVendor) rfqVendorList.get(i);
			        rfqVendor.setBidSubtotal((BigDecimal) vendorBids.get(rfqVendor.getComp_id().getVendorId()));
			        rfqVendorList.set(i, rfqVendor);
			    }

			    String vendorsToViewList = (String)incomingRequest.get("PdfRfq_vendorId");
			    if(vendorsToViewList != null)
			    {
					String vendorsToView[] = vendorsToViewList.split(";");
				    List newVendorsList = new ArrayList();
				    for (int i = 0; i < rfqVendorList.size(); i++)
				    {
				    	RfqVendor rfqVendor = (RfqVendor) rfqVendorList.get(i);
				    	String vendorId = rfqVendor.getComp_id().getVendorId();
				    	for(int j = 0; j < vendorsToView.length; j++)
				    	{
				    		if(vendorId.equalsIgnoreCase(vendorsToView[j]))
				    		{
				    			newVendorsList.add(rfqVendor);
				    		}
				    	}
				    }
				    incomingRequest.put("rfqVendorList", newVendorsList);
			    }
			    else
			    {
			    	incomingRequest.put("rfqVendorList", rfqVendorList);
			    }
			}
			incomingRequest.put("rfqVendorList", rfqVendorList);

            this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}
