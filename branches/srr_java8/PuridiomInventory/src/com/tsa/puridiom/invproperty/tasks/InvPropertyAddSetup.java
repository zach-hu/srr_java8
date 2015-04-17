package com.tsa.puridiom.invproperty.tasks;

import com.tsa.puridiom.entity.InvProperty;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.math.BigDecimal;
import java.util.Map;

/**
 * InvPropertyAddSetup.java
 * @author renzo
 * Jan 16, 2004
 */

public class InvPropertyAddSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvProperty invProperty = new InvProperty();
			ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine") ;

			UniqueKeyGenerator uk = UniqueKeyGenerator.getInstance();
			BigDecimal icProperty = new BigDecimal(uk.getUniqueKey().toString());
			invProperty.setIcProperty(icProperty);
			incomingRequest.put("InvProperty_icProperty", icProperty.toString()) ;
//			incomingRequest.put("InvProperty_assetNumber",incomingRequest.get("documentNumber")) ;


			if (receiptLine != null) {
				incomingRequest.put("InvProperty_icRecLine", receiptLine.getIcRecLine().toString()) ;
			}
            if (!incomingRequest.containsKey("InvProperty_dateIn")) {
                String userTimeZone = (String) incomingRequest.get("userTimeZone");
                incomingRequest.put("InvProperty_dateIn", Dates.today("", userTimeZone));
            }
            if (!incomingRequest.containsKey("InvProperty_status")) {
                incomingRequest.put("InvProperty_status", "02");
            }
            if (!incomingRequest.containsKey("InvProperty_owner")) {
                incomingRequest.put("InvProperty_owner", incomingRequest.get("userId"));
            }
            if (!incomingRequest.containsKey("InvProperty_receiptNumber")) {
                incomingRequest.put("InvProperty_receiptNumber", incomingRequest.get("ReceiptHeader_receiptNumber"));
            }
            if (!incomingRequest.containsKey("InvProperty_icRecLine")) {
                incomingRequest.put("InvProperty_icRecLine", incomingRequest.get("ReceiptLine_icRecLine"));
            }


			result = invProperty ;
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return result;
	}

}