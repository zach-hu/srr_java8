package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.DisbLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.requisitionline.tasks.RequisitionLineUpdate;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class DisbLineListSetStatus extends Task {
	public Object executeTask (Object object) throws Exception {
		Object result = null;
		try{
			Map incomingRequest = (Map)object;
			String oid = (String)incomingRequest.get("organizationId");
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);
			
			DisbLine disbLine = null;
			RequisitionLine msrLine = null;
			
			DisbHeader disbHeader = (DisbHeader)incomingRequest.get("disbHeader");
			List disbLineList = (List)incomingRequest.get("disbLineList");
			List disbLineTrackList = (List)incomingRequest.get("disbLineTrackList");
//			List invBinLocationList = (List)incomingRequest.get("invBinLocationList");
//			List receiptLineList = (List)incomingRequest.get("receiptLineList");
			List msrLineList = (List)incomingRequest.get("msrLineList");
			
			for (int i = 0; i < disbLineList.size(); i++) {
				disbLine = (DisbLine)disbLineList.get(i);
				List disbLineTrack = (List) disbLineTrackList.get(i);
				
				if(disbHeader != null && disbLine != null && msrLineList != null && msrLineList.size() >= 0){
					msrLine = (RequisitionLine)msrLineList.get(i);
					BigDecimal totalDisbursed = new BigDecimal(0);
					
					for (int j = 0; j < disbLineTrack.size(); j++) {
						DisbLine disbLineRecord = (DisbLine)disbLineTrack.get(j);
						if(disbLineRecord.getStatus().equals(DocumentStatus.INV_DISBURSED)){
							totalDisbursed = totalDisbursed.add(disbLineRecord.getQuantity());
						}
					}
					
					if(msrLine != null && disbLine.getStatus().compareToIgnoreCase(DocumentStatus.INV_DISBURSED) == 0){
							
						if(totalDisbursed.compareTo(msrLine.getQuantity()) < 0 && msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_PARTIALLYISSUED) < 0)
						{
							msrSetStatus(DocumentStatus.REQ_PARTIALLYISSUED, msrLine, incomingRequest, processLoader);
//							updateMsrHeader(msrLine, incomingRequest, processLoader);
//							msrLineListUpdate = retrieveMsrLineList(msrLine.getIcReqHeader().toString(), incomingRequest, processLoader);
//							updateStatus(msrHeader, msrLineListUpdate, incomingRequest);
						}
						if(totalDisbursed.compareTo(msrLine.getQuantity()) == 0 && msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_ISSUED) != 0)
						{
							msrSetStatus(DocumentStatus.REQ_ISSUED, msrLine, incomingRequest, processLoader);
//							updateMsrHeader(msrLine, incomingRequest, processLoader);
//							msrLineListUpdate = retrieveMsrLineList(msrLine.getIcReqHeader().toString(), incomingRequest, processLoader);
//							updateStatus(msrHeader, msrLineListUpdate, incomingRequest);

//							if(disbHeader.getDisbType().compareTo(DisbursementType.TRANSFER_REQUEST) != 0){
							msrSetStatus(DocumentStatus.REQ_CLOSED, msrLine, incomingRequest, processLoader);
//							updateMsrHeader(msrLine, incomingRequest, processLoader);
//							msrLineListUpdate = retrieveMsrLineList(msrLine.getIcReqHeader().toString(), incomingRequest, processLoader);
//							updateStatus(msrHeader, msrLineListUpdate, incomingRequest);
//							}
						}
					} 
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
	
	private void msrSetStatus(String status, RequisitionLine msrLine, Map incomingRequest, PuridiomProcessLoader processLoader) throws Exception {
		if(msrLine != null && msrLine.getStatus().compareToIgnoreCase(status) != 0){
			
			PuridiomProcess process = processLoader.loadProcess("requisitionline-history.xml");
			Map historyIncominRequest = new HashMap();

			msrLine.setStatus(status);

			RequisitionLineUpdate update = new RequisitionLineUpdate();
			historyIncominRequest.put("newHistoryRequisitionLine", msrLine);
			historyIncominRequest.put("requisitionLine", msrLine);
			historyIncominRequest.put("RequisitionHeader_icReqHeader", msrLine.getIcReqHeader().toString());
			historyIncominRequest.put("organizationId", incomingRequest.get("organizationId"));
			historyIncominRequest.put("userId", incomingRequest.get("userId"));
			historyIncominRequest.put("dbsession", incomingRequest.get("dbsession"));

			update.executeTask(historyIncominRequest);
			process.executeProcess(historyIncominRequest);
		}
	}
	
}
