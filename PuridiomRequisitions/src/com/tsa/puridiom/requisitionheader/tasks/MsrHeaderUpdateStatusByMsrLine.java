package com.tsa.puridiom.requisitionheader.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class MsrHeaderUpdateStatusByMsrLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		// if you update this part, please update the method "updateStatus(...)" in "MsrLineListUpdateStatusMsrHeader.java" too.
		Object result = null;
		try{
			Map incomingRequest = (Map)object;
			
			List msrLineList = (List)incomingRequest.get("msrLineList");
			RequisitionHeader msrHeader = (RequisitionHeader)incomingRequest.get("msrHeader");
			String status = "";
			int lowestStatus = 99999;
			String secundaryStatus = "";

			if(msrLineList != null && msrLineList.size() > 0)
			{
				for (int i = 0; i < msrLineList.size(); i++) {
					RequisitionLine msrLine = (RequisitionLine)msrLineList.get(i);
					String msrLineStatus = msrLine.getStatus();
					if(msrLineStatus.compareToIgnoreCase(DocumentStatus.REQ_APPROVED) == 0){
						secundaryStatus = msrLineStatus; 
						//continue;
					}
					if(lowestStatus > Integer.parseInt(msrLineStatus)){
						lowestStatus = Integer.parseInt(msrLineStatus);
					}
				}

				if(lowestStatus < Integer.parseInt(DocumentStatus.REQ_ORDERED)){
					status = "0"+lowestStatus;
				} else if(lowestStatus < Integer.parseInt(DocumentStatus.REQ_INPROGRESS)){
					if(!secundaryStatus.isEmpty()){
						status = secundaryStatus;
					} else {
						status = "0"+lowestStatus;
					}
				} else {
					status = ""+lowestStatus;
				}

				if(status.compareToIgnoreCase(DocumentStatus.REQ_PLANNING_APPROVED) == 0 &&
						!msrHeader.getStatus().equalsIgnoreCase(DocumentStatus.REQ_PLANNING_SOURCING)){
					
					msrHeader.setStatus(DocumentStatus.REQ_PLANNING_SOURCING);
					
					update(msrHeader, incomingRequest);
					
				} else if(!msrHeader.getStatus().equalsIgnoreCase(status)){
					
					msrHeader.setStatus(status);
					
					update(msrHeader, incomingRequest);
					
				}
				
			}
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			Log.error(this, e);
			e.printStackTrace();
		}
		return result;
	}
	
	private void update(RequisitionHeader msrHeader, Map incomingRequest) throws Exception {
		
		String oid = (String)incomingRequest.get("organizationId");
		
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);
		PuridiomProcess process = processLoader.loadProcess("requisition-history.xml");
		
		RequisitionHeaderUpdate update = new RequisitionHeaderUpdate();
		Map updateIncomingRequest = new HashMap();
		updateIncomingRequest.put("requisitionHeader", msrHeader);
		updateIncomingRequest.put("organizationId", oid);
		updateIncomingRequest.put("userId", incomingRequest.get("userId"));
		updateIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
		update.executeTask(updateIncomingRequest);
		
		updateIncomingRequest.put("newHistoryRequisitionHeader", msrHeader);
		process.executeProcess(updateIncomingRequest);
	}

}