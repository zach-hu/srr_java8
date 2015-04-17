package com.tsa.puridiom.vendorregister.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class VendorRegisterSetValuesPK
{
	public void setValues(Map incomingRequest, VendorRegister vendorregister ) throws Exception
	{
		try
		{
			String vendorId = (String ) incomingRequest.get("VendorRegister_vendorId");
			String contactEmailAddr = (String ) incomingRequest.get("VendorRegister_contactEmailAddr");
			VendorRegisterPK comp_id = new VendorRegisterPK();
			comp_id.setContactEmailAddr(contactEmailAddr);
			comp_id.setVendorId(vendorId);
			vendorregister.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}