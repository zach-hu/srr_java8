package com.tsa.puridiom.requisitionline.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class RequisitionLineListSetStatus extends Task {
	public Object executeTask (Object object) throws Exception {
		Object result = null;
		try{
			Map incomingRequest = (Map)object;
			String oid = (String)incomingRequest.get("organizationId");
			String userId = (String)incomingRequest.get("userId");
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);
			Map historyIncominRequest = null;
			
			RequisitionLine requisitionLine = null;
			RequisitionLine msrLine = null;
			
			List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
			List msrLineList = (List)incomingRequest.get("msrLineList");
			
			for (int i = 0; i < requisitionLineList.size(); i++) {
				requisitionLine = (RequisitionLine)requisitionLineList.get(i);
				
				if(msrLineList != null && msrLineList.size() >= 0 && requisitionLine.getStatus().equals(DocumentStatus.REQ_APPROVED)){
					PuridiomProcess process = processLoader.loadProcess("requisitionline-history.xml");
					
					for (int j = 0; j < msrLineList.size(); j++) {
						msrLine = (RequisitionLine)msrLineList.get(j);
						
						if(requisitionLine.getIcLineHistory().compareTo(msrLine.getIcLineHistory()) == 0 && 
								msrLine.getStatus().compareToIgnoreCase(requisitionLine.getStatus()) != 0){
							
							msrLine.setStatus(requisitionLine.getStatus());
							
							historyIncominRequest = new HashMap();
							historyIncominRequest.put("newHistoryRequisitionLine", msrLine);
							historyIncominRequest.put("requisitionHeader", incomingRequest.get("msrHeader"));
							historyIncominRequest.put("organizationId", oid);
							historyIncominRequest.put("userId", userId);
							historyIncominRequest.put("dbsession", incomingRequest.get("dbsession"));
							process.executeProcess(historyIncominRequest);
						}
					}
				}
			}
			
			incomingRequest.put("requisitionLineList", requisitionLineList);
			incomingRequest.put("msrLineList", msrLineList);
			
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			Log.error(this, e);
			e.printStackTrace();
		}
		return result;
	}
}
