package com.tsa.puridiom.vendorquestion.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.util.Map;

public class VendorQuestionAddSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		    incomingRequest.put("VendorQuestion_icVendorQuestion",ukg.getUniqueKey().toString());

		    if (!incomingRequest.containsKey("VendorQuestion_icRfqHeader")) {
		        if (incomingRequest.containsKey("RfqHeader_icRfqHeader")) {
		            incomingRequest.put("VendorQuestion_icRfqHeader", incomingRequest.get("RfqHeader_icRfqHeader"));
		        } else {
		            incomingRequest.put("VendorQuestion_icRfqHeader", "0");
		        }
		    }
		    if (!incomingRequest.containsKey("VendorQuestion_vendorId")) {
		        incomingRequest.put("VendorQuestion_vendorId", "");
		    }
			if (!incomingRequest.containsKey("VendorQuestion_questionTitle")) {
			    incomingRequest.put("VendorQuestion_questionTitle", "");
			}
			if (!incomingRequest.containsKey("VendorQuestion_questionText")) {
			    incomingRequest.put("VendorQuestion_questionText", "");
			}
			if (!incomingRequest.containsKey("VendorQuestion_datePosted")) {
			    incomingRequest.put("VendorQuestion_datePosted", Dates.today("yyyy-MM-dd", ""));
                // Supplier Portal time zone is always based on the system default server time zone
			}
			if (!incomingRequest.containsKey("VendorQuestion_timePosted")) {
			    incomingRequest.put("VendorQuestion_timePosted", Dates.today("hh:mm:ss", ""));
                //Supplier Portal time zone is always based on the system default server time zone
			}
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