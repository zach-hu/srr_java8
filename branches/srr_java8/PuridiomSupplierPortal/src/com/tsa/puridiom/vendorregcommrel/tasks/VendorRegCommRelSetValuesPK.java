package com.tsa.puridiom.vendorregcommrel.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class VendorRegCommRelSetValuesPK
{
	public void setValues(Map incomingRequest, VendorRegCommRel vendorregcommrel ) throws Exception
	{
		try
		{
			String vendorId = (String ) incomingRequest.get("VendorRegCommRel_vendorId");
			String commodityCode = (String ) incomingRequest.get("VendorRegCommRel_commodityCode");
			VendorRegCommRelPK comp_id = new VendorRegCommRelPK();
			comp_id.setCommodityCode(commodityCode);
			comp_id.setVendorId(vendorId);
			vendorregcommrel.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}