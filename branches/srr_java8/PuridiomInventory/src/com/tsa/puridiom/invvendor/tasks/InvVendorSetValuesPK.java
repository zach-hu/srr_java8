package com.tsa.puridiom.invvendor.tasks;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;
import com.tsa.puridiom.entity.*;

public class InvVendorSetValuesPK
{
	public void setValues(Map incomingRequest, InvVendor invvendor )
	{
		String itemNumber = (String ) incomingRequest.get("InvVendor.itemNumber");
		String vendorId = (String ) incomingRequest.get("InvVendor.vendorId");
		InvVendorPK comp_id = new InvVendorPK();
		comp_id.setItemNumber(itemNumber);
		comp_id.setVendorId(vendorId);
		invvendor.setComp_id(comp_id);
	}
}