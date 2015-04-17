package com.tsa.puridiom.vendorregcommrel.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class VendorRegCommRelSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			VendorRegCommRelPK comp_id = new VendorRegCommRelPK();
			VendorRegCommRel vendorRegCommRel = (VendorRegCommRel) incomingRequest.get("vendorRegCommRel");
			if (vendorRegCommRel == null)
			{
				vendorRegCommRel = new VendorRegCommRel();
			}

			if (incomingRequest.containsKey("VendorRegCommRel_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("VendorRegCommRel_vendorId");
				comp_id.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("VendorRegCommRel_commodityCode"))
			{
				String commodityCode = (String ) incomingRequest.get("VendorRegCommRel_commodityCode");
				comp_id.setCommodityCode(commodityCode);
			}
			vendorRegCommRel.setComp_id(comp_id);

			result = vendorRegCommRel;
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