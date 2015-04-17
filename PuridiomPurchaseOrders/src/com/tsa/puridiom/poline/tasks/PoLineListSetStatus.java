package com.tsa.puridiom.poline.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class PoLineListSetStatus extends Task {
	public Object executeTask (Object object) throws Exception {
		Object result = null;
		try{
			Map incomingRequest = (Map)object;
			String oid = (String)incomingRequest.get("organizationId");
			String userId = (String)incomingRequest.get("userId");
			DBSession dbsession = (DBSession)incomingRequest.get("dbsession");

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);
			PuridiomProcess process = processLoader.loadProcess("requisitionline-history.xml");
			
			PoLine poLine = null;
			RequisitionLine requisitionLine = null;
			RequisitionLine msrLine = null;

			List poLineList = HiltonUtility.ckNull((List)incomingRequest.get("poLineList"));
			List requisitionLineList = HiltonUtility.ckNull((List)incomingRequest.get("requisitionLineList"));
			List msrLineList = HiltonUtility.ckNull((List)incomingRequest.get("msrLineList"));
			
			for (int i = 0; i < poLineList.size(); i++) {
				poLine = (PoLine)poLineList.get(i);

				if(poLine.getStatus().compareTo(DocumentStatus.PO_AWARDED) == 0 || poLine.getStatus().compareTo(DocumentStatus.RCV_RECEIVED) == 0){

						try{
							requisitionLine = (RequisitionLine)requisitionLineList.get(i);
						} catch (Exception e) {
							requisitionLine = new RequisitionLine();
						}

						if(poLine.getIcLineHistory().compareTo(requisitionLine.getIcLineHistory()) == 0){

							RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
							
							if(poLine.getStatus().compareTo(DocumentStatus.PO_AWARDED) == 0 && requisitionLine.getStatus().compareToIgnoreCase(DocumentStatus.PO_AWARDED) != 0){
								requisitionLine.setStatus(DocumentStatus.PO_AWARDED);
								addHistory(process, requisitionLine, requisitionHeader, oid, userId, dbsession);
							}

							if(poLine.getStatus().compareTo(DocumentStatus.RCV_RECEIVED) == 0 && requisitionLine.getStatus().compareToIgnoreCase(DocumentStatus.RCV_RECEIVED) != 0){
								requisitionLine.setStatus(DocumentStatus.RCV_RECEIVED);
								addHistory(process, requisitionLine, requisitionHeader, oid, userId, dbsession);
							}

						}

						for (int j = 0; j < msrLineList.size(); j++) {
							msrLine = (RequisitionLine)msrLineList.get(j);

							if(poLine.getIcLineHistory().compareTo(msrLine.getIcLineHistory()) == 0){

								RequisitionHeader msrHeader = (RequisitionHeader)incomingRequest.get("msrHeader");
								
								String msrKit = "N";
								if(msrHeader != null){
									msrKit = HiltonUtility.ckNull(msrHeader.getKit());
								}
								
								if(poLine.getStatus().compareTo(DocumentStatus.PO_AWARDED) == 0 && msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_ORDERED) != 0){
									msrLine.setStatus(DocumentStatus.REQ_ORDERED);
									addHistory(process, msrLine, msrHeader, oid, userId, dbsession);
								}

								if(poLine.getStatus().compareTo(DocumentStatus.RCV_RECEIVED) == 0 && msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_RECEIVED) != 0){
									msrLine.setStatus(DocumentStatus.REQ_RECEIVED);
									addHistory(process, msrLine, msrHeader, oid, userId, dbsession);
								}
								
								if(!msrKit.equalsIgnoreCase("Y") && poLine.getStatus().compareTo(DocumentStatus.RCV_RECEIVED) == 0){
									msrLine.setStatus(DocumentStatus.REQ_CLOSED);
									addHistory(process, msrLine, msrHeader, oid, userId, dbsession);
								}
								
							}
						}
				}
			}

			incomingRequest.put("poLineList", poLineList);
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
	
	private void addHistory(PuridiomProcess process, RequisitionLine requisitionLine, RequisitionHeader requisitionHeader, String oid, String userId, DBSession dbsession) throws Exception{
		
		Map historyIncominRequest = new HashMap();
		historyIncominRequest.put("newHistoryRequisitionLine", requisitionLine);
		historyIncominRequest.put("requisitionHeader", requisitionHeader);
		historyIncominRequest.put("organizationId", oid);
		historyIncominRequest.put("userId", userId);
		historyIncominRequest.put("dbsession", dbsession);
		process.executeProcess(historyIncominRequest);
		
	}
}
