package com.tsa.puridiom.supplierportal.handlers;

import com.tsa.puridiom.supplierportal.*;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class SetAuctionPageHandler implements IHandler {
    
	public Map  handleRequest (Map incomingRequest) throws Exception {
		try {
			String	organizationId = (String) incomingRequest.get("organizationId");
			PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("rfq-determine-auction-status.xml");
			process.executeProcess(incomingRequest);
			
			if (process.getStatus() == Status.SUCCEEDED) {
			    String	auctionStatus = HiltonUtility.ckNull((String) incomingRequest.get("auctionStatus"));
			    
			    if (auctionStatus.equals("OPEN") && !HiltonUtility.isEmpty((String) incomingRequest.get("openAuctionPage"))) {
					incomingRequest.put("viewPage", incomingRequest.get("openAuctionPage"));
			    } else if (auctionStatus.equals("CLOSED") && !HiltonUtility.isEmpty((String) incomingRequest.get("closedAuctionPage"))) {
					incomingRequest.put("viewPage", incomingRequest.get("closedAuctionPage"));
			    } else {
			        incomingRequest.put("viewPage", incomingRequest.get("successPage"));
			    }
			} else {
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		} catch (Exception exception) {
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("exception", exception);
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
		} finally {
			if (incomingRequest.get("viewPage") == null) {
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		return incomingRequest;
	}

}