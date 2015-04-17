package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineIcAccountSetup extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		try
		{
			PoLine pol = (PoLine) incomingRequest.get("poLine");

			String	delAll = (String) incomingRequest.get("deleteall") ;
			if (delAll == null) delAll = "" ;
			if (delAll.compareTo("TRUE") != 0) {
				incomingRequest.put("PoLine_icAccount", pol.getIcPoLine().toString());
			} else {
				incomingRequest.put("PoLine_icAccount", "0");
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Error occurred seting Line Account Ic. " + e.getMessage() , e);

		}
		return null;
	}
}