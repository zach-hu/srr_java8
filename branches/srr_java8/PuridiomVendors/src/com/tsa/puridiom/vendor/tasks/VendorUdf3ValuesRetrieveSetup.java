package com.tsa.puridiom.vendor.tasks;

import java.util.Map;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class VendorUdf3ValuesRetrieveSetup extends Task {

    public Object executeTask(Object object) throws Exception {
		Object ret = null;
		try {
			Map incomingRequest = (Map) object;

			incomingRequest.put("StdTable_tableType", "VN03");
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return ret;
	}
}
