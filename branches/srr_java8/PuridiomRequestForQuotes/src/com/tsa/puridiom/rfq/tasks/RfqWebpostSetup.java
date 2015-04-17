package com.tsa.puridiom.rfq.tasks;
import java.util.Map;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.common.utility.HiltonUtility;

public class RfqWebpostSetup extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;
        String timeZone = propertiesManager.getProperty("RFQ DEFAULTS", "TimeZone", "");
        String userTimeZone = (String) incomingRequest.get("userTimeZone");

        if (!HiltonUtility.isEmpty(userTimeZone)) {
            timeZone = userTimeZone;
        }

		incomingRequest.put("RfqHeader_webpost", propertiesManager.getProperty("RFQ DEFAULTS", "Webpost", "N"));
		incomingRequest.put("RfqHeader_bidAccess", propertiesManager.getProperty("RFQ DEFAULTS", "BidAccess", "U"));
		incomingRequest.put("RfqHeader_auctionType", propertiesManager.getProperty("RFQ DEFAULTS", "AuctionType", "S"));
		incomingRequest.put("RfqHeader_bidDueTime", propertiesManager.getProperty("RFQ DEFAULTS", "BidDueTime", "23:59"));
		incomingRequest.put("RfqHeader_timeZone", timeZone);
		incomingRequest.put("RfqHeader_bidItemOptions", propertiesManager.getProperty("RFQ DEFAULTS", "BidItemOptions", "N"));
		incomingRequest.put("RfqHeader_lowestBidSource", propertiesManager.getProperty("RFQ DEFAULTS", "LowestBidSource", "L"));
		incomingRequest.put("RfqHeader_lowestBidReq", propertiesManager.getProperty("RFQ DEFAULTS", "LowestBidReq", "N"));
		incomingRequest.put("RfqHeader_lowestDisplay", propertiesManager.getProperty("RFQ DEFAULTS", "LowestDisplay", "A"));

		return null ;
	}

}