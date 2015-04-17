package com.tsa.puridiom.inscategorylevel.tasks;

import com.tsa.puridiom.commodity.CommodityManager;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Commodity;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.vendor.tasks.VendorRetrieveById;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsCategoryLevelCheckFromPoLines extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");

			List lineitems = (List) incomingRequest.get("lineitems");
			PoHeader poHeader = (PoHeader) incomingRequest.get("header");

			BigDecimal maxIclLevel = new BigDecimal(0);
			if (lineitems != null && lineitems.size() > 0)
			{
				for (int i = 0; i < lineitems.size(); i++)
				{
					PoLine poLine = (PoLine)lineitems.get(i);
					if (poLine != null)
					{
						Commodity commodity = CommodityManager.getInstance().getCommodity(organizationId, poLine.getCommodity());
						if (commodity != null) {
							BigDecimal iclLevel = commodity.getIclLevel();
							if (iclLevel.compareTo(maxIclLevel) > 0)
							{
								maxIclLevel = iclLevel;
							}
						}
					}
				}
			}

			String validateVendorInsuranceDefault = "N";

			String vendorId = "";
			if (poHeader != null && !HiltonUtility.isEmpty(poHeader.getVendorId()))
			{
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
				newIncomingRequest.put("Vendor_vendorId", poHeader.getVendorId());

				VendorRetrieveById vendorRetrieveById = new VendorRetrieveById();
				Vendor vendor = (Vendor)vendorRetrieveById.executeTask(newIncomingRequest);
				if (vendor != null)
				{
					BigDecimal iclLevel = vendor.getIclLevel();
					if (maxIclLevel.compareTo(iclLevel) > 0)
					{
						validateVendorInsuranceDefault = "Y";
					}
				}

				vendorId = poHeader.getVendorId();
			}

			incomingRequest.put("isVendorInsuranceDefaultValid", "Y");
			incomingRequest.put("validateVendorInsuranceDefault", validateVendorInsuranceDefault);

			incomingRequest.put("InsCategoryLevel_iclLevel", maxIclLevel.toString());
			incomingRequest.put("VendorInsuranceDefault_vendorId", vendorId);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
