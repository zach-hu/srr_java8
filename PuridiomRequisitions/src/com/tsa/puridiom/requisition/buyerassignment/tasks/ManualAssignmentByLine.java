/*
 * Created on Apr 19, 2005
 */
package com.tsa.puridiom.requisition.buyerassignment.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.requisitionheader.tasks.ReqIcHeaderRetrieveByLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Kelli
 */
public class ManualAssignmentByLine extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map) object;

            String	assignTo = (String) incomingRequest.get("assignTo");

            if (!HiltonUtility.isEmpty(assignTo)) {
                Object icReqLineObj = incomingRequest.get("RequisitionLine_icReqLine");

	            if (icReqLineObj instanceof String[]) {
	                String	icReqLineArray[] = (String[]) icReqLineObj;
	                String	icReqHeaderArray[] = (String[]) incomingRequest.get("RequisitionLine_icReqHeader");
	                Map reqsAssigned = new HashMap();
	                Map requisitionsToNotify = new HashMap();

	                for(int i = 0; i < icReqLineArray.length; i++) {
		                PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
						PuridiomProcess process = processLoader.loadProcess("requisitionline-buyer-assignment-update.xml");
						String	icReqLine = icReqLineArray[i];
						String	icReqHeader = icReqHeaderArray[i];
						Map updateParameters = new HashMap();

		                updateParameters.put("dbsession", incomingRequest.get("dbsession"));
		                updateParameters.put("organizationId", incomingRequest.get("organizationId"));
		                updateParameters.put("userId", incomingRequest.get("userId"));
		                updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
		                updateParameters.put("RequisitionLine_icReqLine", icReqLine);
		                updateParameters.put("assignTo", assignTo);
		                if (!reqsAssigned.containsKey(icReqHeader)) {
		                    updateParameters.put("assignHeaderRequired", "Y");
		                } else {
		                    updateParameters.put("assignHeaderRequired", "N");
		                }

						process.executeProcess(updateParameters);
						if (process.getStatus() == Status.SUCCEEDED) {
							RequisitionHeader requisitionHeader = (RequisitionHeader) updateParameters.get("requisitionHeader");

							List lines = (List) requisitionsToNotify.get(requisitionHeader.getIcReqHeader());

							if (lines == null)
							{
								lines = new ArrayList();
							}

							lines.add(icReqLine);

							requisitionsToNotify.put(requisitionHeader.getIcReqHeader(), lines);
						}

						if (process.getStatus() < Status.SUCCEEDED) {
							throw new Exception("Requisition Line Update process failed for icReqLine " + icReqLine + ". ");
						}

						reqsAssigned.put(icReqHeader, "Y");
	                }
	                incomingRequest.put("requisitionsToNotify", requisitionsToNotify);

	            } else {
	                PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
					PuridiomProcess process = processLoader.loadProcess("requisitionline-buyer-assignment-update.xml");
					String	icReqLine = (String) icReqLineObj;
					Map updateParameters = new HashMap();

	                updateParameters.put("dbsession", incomingRequest.get("dbsession"));
	                updateParameters.put("organizationId", incomingRequest.get("organizationId"));
	                updateParameters.put("userId", incomingRequest.get("userId"));
	                updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
	                updateParameters.put("RequisitionLine_icReqLine", icReqLine);
	                updateParameters.put("assignTo", assignTo);
	                updateParameters.put("assignHeaderRequired", "Y");


	                ReqIcHeaderRetrieveByLine reqIcHeaderRetrieveByLine = new ReqIcHeaderRetrieveByLine();
	                reqIcHeaderRetrieveByLine.executeTask(incomingRequest);

	                Map requisitionsToNotify = new HashMap();
	                BigDecimal icReqHeader = new BigDecimal((String) incomingRequest.get("RequisitionHeader_icReqHeader"));
	                List reqLines = new ArrayList();
	                reqLines.add(icReqLine);
	                requisitionsToNotify.put(icReqHeader, reqLines);
	                incomingRequest.put("requisitionsToNotify", requisitionsToNotify);
	                process.executeProcess(updateParameters);

					if (process.getStatus() < Status.SUCCEEDED) {
						throw new Exception("Requisition Line Update process failed for icReqLine " + icReqLine + ". ");
					}
	            }

                AssignmentNotificationByLine assignmentNotificationByLine = new AssignmentNotificationByLine();
	            assignmentNotificationByLine.executeTask(incomingRequest);
	            this.setStatus(assignmentNotificationByLine.getStatus());
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
