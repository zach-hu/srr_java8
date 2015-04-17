package com.tsa.puridiom.invvendor.tasks;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;
import com.tsa.puridiom.entity.*;

public class InvVendorSetValues
{
	public void setValues(Map incomingRequest, InvVendor invvendor )
	{
		InvVendorPK comp_id = new InvVendorPK();
		if(incomingRequest.containsKey("InvVendor.itemNumber"))
		{
			String itemNumber = (String ) incomingRequest.get("InvVendor.itemNumber");
			comp_id.setItemNumber(itemNumber);
		}
		if(incomingRequest.containsKey("InvVendor.vendorId"))
		{
			String vendorId = (String ) incomingRequest.get("InvVendor.vendorId");
			comp_id.setVendorId(vendorId);
		}
		if(incomingRequest.containsKey("InvVendor.lastDate"))
		{
			String lastDateString = (String) incomingRequest.get("InvVendor.lastDate");
			long dlastDate = java.sql.Date.parse(lastDateString);
			Date lastDate = new Date(dlastDate);
			invvendor.setLastDate(lastDate);
		}
		if(incomingRequest.containsKey("InvVendor.lastPrice"))
		{
			String lastPriceString = (String) incomingRequest.get("InvVendor.lastPrice");
			BigDecimal lastPrice = new BigDecimal ( lastPriceString );
			invvendor.setLastPrice(lastPrice);
		}
		if(incomingRequest.containsKey("InvVendor.mfgNumber"))
		{
			String mfgNumber = (String ) incomingRequest.get("InvVendor.mfgNumber");
			invvendor.setMfgNumber(mfgNumber);
		}
		if(incomingRequest.containsKey("InvVendor.leadTime"))
		{
			String leadTimeString = (String) incomingRequest.get("InvVendor.leadTime");
			BigDecimal leadTime = new BigDecimal ( leadTimeString );
			invvendor.setLeadTime(leadTime);
		}
		if(incomingRequest.containsKey("InvVendor.primaryVendor"))
		{
			String primaryVendor = (String ) incomingRequest.get("InvVendor.primaryVendor");
			invvendor.setPrimaryVendor(primaryVendor);
		}

		invvendor.setComp_id(comp_id);
	}
}