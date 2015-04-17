package com.tsa.puridiom.requisitionline.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.requisitionheader.tasks.RequisitionHeaderRetrieveByIcReqHeader;
import com.tsa.puridiom.requisitionheader.tasks.RequisitionHeaderUpdate;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class MsrLineListUpdateStatusMsrHeader extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			String oid = (String)incomingRequest.get("organizationId");
			
			
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);
			
			RequisitionHeader msrHeader = null;
			
			List msrLineList = (List) incomingRequest.get("msrLineList");
			List msrLineListUpdate = null;
			
			if(msrLineList != null)
			{
				for (int i=0; i < msrLineList.size(); i++) {
					RequisitionLine msrLine = (RequisitionLine) msrLineList.get(i);
					if(msrLine != null){
						msrHeader = retrieveMsrHeader(msrLine.getIcReqHeader().toString(), incomingRequest, processLoader);
						msrLineListUpdate = retrieveMsrLineList(msrLine.getIcReqHeader().toString(), incomingRequest, processLoader);
						
						updateStatus(msrHeader, msrLineListUpdate, incomingRequest);
					}
				}
			}

			result = msrLineList;			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
	
	private void updateStatus(RequisitionHeader msrHeader, List msrLineList, Map incomingRequest) throws Exception {
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
				/*This is a specific case, only for disbursements*/
				if(status.equalsIgnoreCase(DocumentStatus.REQ_CLOSED)){
					msrHeader.setStatus(DocumentStatus.REQ_ISSUED);
					update(msrHeader, incomingRequest);
				}
				
				msrHeader.setStatus(status);
				
				update(msrHeader, incomingRequest);
				
			}
		}
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

	private RequisitionHeader retrieveMsrHeader(String icReqHeader, Map incomingRequest, PuridiomProcessLoader processLoader) throws Exception {
	
		Map retrieveIncominRequest = new HashMap();
		
		RequisitionHeaderRetrieveByIcReqHeader retrieve =  new RequisitionHeaderRetrieveByIcReqHeader();
		retrieveIncominRequest.put("organizationId", incomingRequest.get("organizationId"));
		retrieveIncominRequest.put("userId", incomingRequest.get("userId"));
		retrieveIncominRequest.put("dbsession", incomingRequest.get("dbsession"));
		retrieveIncominRequest.put("RequisitionHeader_icReqHeader", icReqHeader);
		
		RequisitionHeader requisitionHeader = (RequisitionHeader)retrieve.executeTask(retrieveIncominRequest);
		
		return requisitionHeader;
	}
	
	private List retrieveMsrLineList(String icReqHeader, Map incomingRequest, PuridiomProcessLoader processLoader) throws Exception {
		Map retrieveIncominRequest = new HashMap();
		
		RequisitionLineRetrieveByHeader retrieve = new RequisitionLineRetrieveByHeader();
		retrieveIncominRequest.put("organizationId", incomingRequest.get("organizationId"));
		retrieveIncominRequest.put("userId", incomingRequest.get("userId"));
		retrieveIncominRequest.put("dbsession", incomingRequest.get("dbsession"));
		retrieveIncominRequest.put("RequisitionLine_icReqHeader", icReqHeader);
		
		List requisitionLineList = (List)retrieve.executeTask(retrieveIncominRequest);
		
		return requisitionLineList;
	}

}