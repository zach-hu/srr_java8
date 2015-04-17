/*
 * Created on Oct. 7, 2006
 * @author Kelli
 * com.tsa.puridiom.vendorqualification.tasks.VendorCommRelQualificationSetup.java
 */

package com.tsa.puridiom.vendorqualification.tasks;

import com.tsa.puridiom.entity.VendorRegCommRel;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;

public class VendorCommRelQualificationSetup extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			String	vendorId = (String) incomingRequest.get("VendorRegister_vendorId");
			if (incomingRequest.containsKey("newVendorRegister_vendorId"))
			{
				vendorId = (String) incomingRequest.get("newVendorRegister_vendorId");
			}

			List list = (List) incomingRequest.get("vendorRegCommRelList");

			if (list != null && list.size() > 0) {
			    String codeArray[] = new String[list.size()];
			    for (int i=0; i < list.size(); i++) {
			        VendorRegCommRel vendorRegCommRel = (VendorRegCommRel) list.get(i);
			        String code = vendorRegCommRel.getComp_id().getCommodityCode();
			        codeArray[i] = code;
			    }
			    incomingRequest.put("VendorCommRel_commodityCode", codeArray);
			}

			incomingRequest.put("VendorCommRel_vendorId", vendorId);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return ret;
	}
}
