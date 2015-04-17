package com.tsa.puridiom.sungard.extract.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SungardCheckRequestExtractByAccountSetup extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		String	organizationId = (String) incomingRequest.get("organizationId");
		String	userId = (String) incomingRequest.get("userId");
		RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
		List requisitionLineList = (List) incomingRequest.get("requisitionLineList");
		List accountList = new ArrayList();

	    try {
	        List	defaultAccountList = requisitionHeader.getAccountList();
	        if (defaultAccountList != null) {
	            accountList.addAll(defaultAccountList);
	        }
            for (int i = 0; i < requisitionLineList.size(); i++) {
                RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(i);

                List	lineAccountList = requisitionLine.getAccountList();
                if (lineAccountList != null) {
                    accountList.addAll(lineAccountList);
                }
            }

            result = accountList;

			this.setStatus(Status.SUCCEEDED);
        }
		catch (Exception e) {
		    throw e;
		}
		return result;
	}
}