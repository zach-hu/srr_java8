package com.tsa.puridiom.vendorregister.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.VendorRegister;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorRegisterReject extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			VendorRegister vendorRegister = (VendorRegister) incomingRequest.get("vendorRegister");

			if (vendorRegister != null)
			{
				String rejectionNotes = HiltonUtility.ckNull((String) incomingRequest.get("VendorRegister_rejectionNotes"));
				vendorRegister.setRejectionNotes(rejectionNotes);
				vendorRegister.setStatus("99");
			}

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