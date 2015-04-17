package com.tsa.puridiom.vendorcommrel.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;

public class VendorCommRelSetValuesPK
{
	public void setValues(Map incomingRequest, VendorCommRel vendorcommrel ) throws Exception
	{
		try
		{
			VendorCommRelPK comp_id = new VendorCommRelPK();
			
			if (incomingRequest.containsKey("VendorCommRel_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("VendorCommRel_vendorId");
				comp_id.setVendorId(vendorId);
				vendorcommrel.setComp_id(comp_id);
			}
			if (incomingRequest.containsKey("VendorCommRel_commodityCode"))
			{
				String commodityCode = (String ) incomingRequest.get("VendorCommRel_commodityCode");
				comp_id.setCommodityCode(commodityCode);
				vendorcommrel.setComp_id(comp_id);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}