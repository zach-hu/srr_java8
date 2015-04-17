package com.tsa.puridiom.rfqvendor.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.util.*;

public class RfqVendorSetBidsEnteredFromBidUpdate extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			if (incomingRequest.containsKey("RfqBid_vendorId")) {
				Object vendorIdObj = incomingRequest.get("RfqBid_vendorId");
				Object bidCodeObj = incomingRequest.get("RfqBid_bidCode");
				Object unitPriceObj = incomingRequest.get("RfqBid_unitPrice");
				BigDecimal bdZero = new BigDecimal(0);
				Map vendorMap = new HashMap();
				List bidCodeList = new ArrayList();
				
				if (vendorIdObj instanceof String[]) {
					//if the vendorIdObj is an array, all RfqBid values should be an array of the same size
					
				    String vendorIds[] = (String[]) vendorIdObj;
				    String bidCodes[] = (String[]) bidCodeObj;
				    String unitPrices[] = (String[]) unitPriceObj;
				    int	bidCount = vendorIds.length;
					
					for (int i=0; i < bidCount; i++) {
						String vendorId = (String) vendorIds[i];
						String bidCode = (String) bidCodes[i];
						String unitPrice = (String) unitPrices[i];
						BigDecimal bdUnitPrice;
						
						if (!Utility.isEmpty(vendorId)) {
							try {
							    bdUnitPrice = new BigDecimal(unitPrice);
							} catch (Exception e) {
							    bdUnitPrice = bdZero;
							}
							
							if ((!Utility.isEmpty(bidCode) && !bidCode.equals("NE")) || bdUnitPrice.compareTo(bdZero) > 0) {
							    // Bids have been entered for this vendor
							    vendorMap.put(vendorId, Boolean.TRUE);
							    bidCodeList.add(bidCode);
							}
						}
					}
				}
				else if (vendorIdObj != null) {
				    String vendorId = (String) vendorIdObj;
					String bidCode = (String) bidCodeObj;
					String unitPrice = (String) unitPriceObj;
					BigDecimal bdUnitPrice;
					
					if (!Utility.isEmpty(vendorId)) {
						try {
						    bdUnitPrice = new BigDecimal(unitPrice);
						} catch (Exception e) {
						    bdUnitPrice = bdZero;
						}
						
						if ((!Utility.isEmpty(bidCode) && !bidCode.equals("NE")) || bdUnitPrice.compareTo(bdZero) > 0) {
						    // Bids have been entered for this vendor
						    vendorMap.put(vendorId, Boolean.TRUE);
						    bidCodeList.add(bidCode);
						}
					}
				}
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				processLoader.setApplicationName(this.getApplicationName());
				PuridiomProcess process = processLoader.loadProcess("rfqvendor-update-by-id.xml");
				
				Set keySet = vendorMap.keySet();
				Iterator iterator = keySet.iterator();
				int i=0;
				while (iterator.hasNext()) {
					String vendorId = (String) iterator.next();
					boolean bidsEntered = ((Boolean) vendorMap.get(vendorId)).booleanValue();
					String bidCode = (String) bidCodeList.get(i);
					
					if (bidsEntered) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("RfqVendor_icRfqHeader", incomingRequest.get("RfqHeader_icRfqHeader")) ;
						updateParameters.put("RfqVendor_vendorId", vendorId) ;
						updateParameters.put("RfqVendor_bidsEntered", "Y") ;
						updateParameters.put("RfqVendor_bidCode", bidCode) ;
						
						process.executeProcess(updateParameters);
						if(process.getStatus() != Status.SUCCEEDED)
						{
							this.setStatus(Status.FAILED);
							throw new TsaException("Error ocurred updating rfq bids!");
						}
						i++;
					}
				}
			}
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}