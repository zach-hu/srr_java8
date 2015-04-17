package com.tsa.puridiom.vendorcommrel.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class VendorCommRelSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			VendorCommRelPK comp_id = new VendorCommRelPK();
			VendorCommRel vendorCommRel = (VendorCommRel) incomingRequest.get("vendorCommRel");
			if (vendorCommRel == null)
			{
				vendorCommRel = new VendorCommRel();
			}

			if (incomingRequest.containsKey("VendorCommRel_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("VendorCommRel_vendorId");
				comp_id.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("VendorCommRel_commodityCode"))
			{
				String commodityCode = (String ) incomingRequest.get("VendorCommRel_commodityCode");
				comp_id.setCommodityCode(commodityCode);
			}
			vendorCommRel.setComp_id(comp_id);

			result = vendorCommRel;
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