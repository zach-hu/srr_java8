/*
 * Created on Apr 19, 2005
 */
package com.tsa.puridiom.requisition.buyerassignment.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Kelli
 */
public class ManualAssignmentByHeader extends Task {

    public Object executeTask(Object object) throws Exception {
        Object ret = null;

        try {
            Map incomingRequest = (Map) object;

            String	assignTo = (String) incomingRequest.get("assignTo");

            if (!HiltonUtility.isEmpty(assignTo)) {
                Object icReqHeaderObj = incomingRequest.get("RequisitionHeader_icReqHeader");

	            if (icReqHeaderObj instanceof String[]) {
	                String	icReqHeaderArray[] = (String[]) icReqHeaderObj;

	                for(int i = 0; i < icReqHeaderArray.length; i++) {
	                	if ( ! HiltonUtility.isEmpty(icReqHeaderArray[i])) {
		                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		    				PuridiomProcess process = processLoader.loadProcess("requisitionheader-buyer-assignment-update.xml");
		    				String	icReqHeader = icReqHeaderArray[i];
		    				Map updateParameters = new HashMap();

		    				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
		                    updateParameters.put("organizationId", incomingRequest.get("organizationId"));
		                    updateParameters.put("userId", incomingRequest.get("userId"));
		                    updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
		                    updateParameters.put("RequisitionHeader_icReqHeader", icReqHeader);
		                    updateParameters.put("assignTo", assignTo);

		    				process.executeProcess(updateParameters);

		    				if (process.getStatus() < Status.SUCCEEDED) {
		    					throw new Exception("Requisition Header Update process failed for icReqHeader " + icReqHeader + ".");
		    				}
	                	}
	                }
	            } else if (icReqHeaderObj instanceof String) {
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
					PuridiomProcess process = processLoader.loadProcess("requisitionheader-buyer-assignment-update.xml");
					String	icReqHeader = (String) icReqHeaderObj;
					Map updateParameters = new HashMap();

					updateParameters.put("dbsession", incomingRequest.get("dbsession"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
					updateParameters.put("RequisitionHeader_icReqHeader", icReqHeader);
					updateParameters.put("assignTo", assignTo);

					process.executeProcess(updateParameters);

					if (process.getStatus() < Status.SUCCEEDED) {
						throw new Exception("Requisition Header Update process failed for icReqHeader " + icReqHeader + ".");
					}
	            } else {
	                Log.debug(this, "No requisitions were set to be reassigned.");
	            }
            } else {
                Log.debug(this, "No requisitions will be reassigned (assignTo was not specified).");
            }

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
