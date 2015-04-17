
package com.tsa.puridiom.receipt.inspectorassignment.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.receiptline.tasks.ReceiptLineRetrieveByHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class ManualAssignmentByLine extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map) object;

            String[] inspectorArray;
            String[] engineerArray;
            String[] icRecHeaderArray;

            String	reAssignAll = HiltonUtility.ckNull((String) incomingRequest.get("reAssignAll"));
            boolean reAssign = reAssignAll.equalsIgnoreCase("Y");
            boolean reAssignFlow = false;

            Object inspectorObj = incomingRequest.get("inspectorToAssign");
            Object engineerObj = incomingRequest.get("engineerToAssign");
            Object icRecHeaderObj = incomingRequest.get("ReceiptHeader_icRecHeader");

            String	assignToInspector = (String) incomingRequest.get("assignTo");
            String	assignToEngineer = (String) incomingRequest.get("assignToEngine");


            if(reAssign){
            	inspectorArray = new String[1];
            	inspectorArray[0] = assignToInspector;
            	engineerArray = new String[1];
            	engineerArray[0] = assignToEngineer;
            } else {
	            if (inspectorObj instanceof String[]) {
	            	inspectorArray = (String[]) inspectorObj;
	            	engineerArray =  (String[]) engineerObj;
	            } else {
	            	inspectorArray = new String[1];
	            	inspectorArray[0] = (String)inspectorObj;
	            	engineerArray = new String[1];
	            	engineerArray[0] = (String)engineerObj;
	            }
            }

            if (icRecHeaderObj instanceof String[]) {
            	icRecHeaderArray =  (String[]) icRecHeaderObj;
            } else {
            	icRecHeaderArray = new String[1];
            	icRecHeaderArray[0] = (String)icRecHeaderObj;
            }

            for (int i = 0; i < icRecHeaderArray.length; i++) {
            	if(reAssign && !HiltonUtility.isEmpty(inspectorArray[0]) && !HiltonUtility.isEmpty(engineerArray[0])){
            		reAssignFlow = true;
            	} else if(!HiltonUtility.isEmpty(inspectorArray[i]) && !HiltonUtility.isEmpty(engineerArray[i])){
            		reAssignFlow = true;
            	} else {
            		reAssignFlow = false;
            	}

            	if(reAssignFlow){
	            	ReceiptLineRetrieveByHeader receiptLineRetrieve = new ReceiptLineRetrieveByHeader();
	            	Map newIncomingRequest = new HashMap();
	            	newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
	            	newIncomingRequest.put("ReceiptLine_icRecHeader", icRecHeaderArray[i]);

	            	List receiptLineList = (List)receiptLineRetrieve.executeTask(newIncomingRequest);

	            	Map recsAssigned = new HashMap();
	            	String	icRecHeader = icRecHeaderArray[i];
	            	int		inx = 0;

	            	 for(int j = 0; j < receiptLineList.size(); j++) {
		                	PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			                PuridiomProcess process = processLoader.loadProcess("receiptline-inspector-assignment-update.xml");
							ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(j);
							String oldAssignTo = HiltonUtility.ckNull(receiptLine.getInspectorAssigned()) ;
							String oldAssignToEngine = HiltonUtility.ckNull(receiptLine.getEngineerAssigned());

							if(receiptLine.getInspectionRequired().equalsIgnoreCase("Y")){
								String	icRecLine = receiptLine.getIcRecLine().toString();
								//icRecHeader = receiptLine.getIcRecHeader().toString();
								Map updateParameters = new HashMap();

								updateParameters.put("dbsession", incomingRequest.get("dbsession"));
								updateParameters.put("organizationId", incomingRequest.get("organizationId"));
								updateParameters.put("userId", incomingRequest.get("userId"));
								updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
								updateParameters.put("ReceiptLine_icRecLine", icRecLine);

								 if(reAssign){
									 inx = 0 ;
								 } else {
									 inx = i ;
								 }

							 updateParameters.put("assignTo", inspectorArray[inx]);
							 updateParameters.put("assignToEngine", engineerArray[inx]);

								if (!recsAssigned.containsKey(icRecHeader)) {
				                    updateParameters.put("assignHeaderRequired", "Y");
				                } else {
				                    updateParameters.put("assignHeaderRequired", "N");
				                }
								if (oldAssignTo.equalsIgnoreCase(HiltonUtility.ckNull(inspectorArray[inx]))  && oldAssignToEngine.equalsIgnoreCase(HiltonUtility.ckNull(engineerArray[inx]))) {
									// Assignment has not changed
								} else {
									process.executeProcess(updateParameters);
								}
							}
							recsAssigned.put(icRecHeader, "Y");
		                }
            	}
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
