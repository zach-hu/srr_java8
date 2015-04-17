package com.tsa.puridiom.vendorcommrel.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.VendorCommRel;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class VendorCommRelAddFromList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String vendorId = (String)incomingRequest.get("Vendor_vendorId");
			List    poLineList = (ArrayList)incomingRequest.get("poLineList");
			List    vendCommRelAct = (ArrayList)incomingRequest.get("vendorCommRelList");

			Map actVendCommRel = new HashMap();

			for (int i = 0; i < vendCommRelAct.size(); i++){
				VendorCommRel vendCommRel = (VendorCommRel)vendCommRelAct.get(i);
				String commodity = vendCommRel.getComp_id().getCommodityCode();
				actVendCommRel.put(commodity, commodity);
			}

			for (int i = 0; i < poLineList.size(); i++){
				PoLine poLine = (PoLine)poLineList.get(i);
				String commodity = poLine.getCommodity();

				if(!actVendCommRel.containsKey(commodity)){

					actVendCommRel.put(commodity, commodity);

					Map updateParameters = new HashMap();

					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("VendorCommRel_vendorId", vendorId) ;
					updateParameters.put("VendorCommRel_commodityCode", commodity);

					if ((!HiltonUtility.isEmpty(vendorId)) && (!HiltonUtility.isEmpty(commodity))) {
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
						PuridiomProcess process = processLoader.loadProcess("vendorcommrel-add.xml");
						process.executeProcess(updateParameters);
					}
				}
			}
			result = null;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, " Error on VendorCommRelAddFromList " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}