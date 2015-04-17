package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.*;
import java.util.*;
import java.math.BigDecimal;
import com.tsa.puridiom.entity.*;

public class RequisitionCreateSplit extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			RequisitionHeader oReqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
		    List	splitReqList = (List) incomingRequest.get("splitRequisitionList");
		    List	oReqLineList = (List) incomingRequest.get("requisitionLineList");
		    String createdFrom = (String) incomingRequest.get("createdFrom") ;
		    List reqSplitList = new ArrayList();

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("requisition-split.xml");

			for (int i=0; i < splitReqList.size(); i++) {
			    RequisitionHeader splitReq = (RequisitionHeader) splitReqList.get(i);
			    List reqLineList = splitReq.getRequisitionLineList();

			    incomingRequest.put("splitType",splitReq.getRequisitionType()) ;
			    incomingRequest.put("splitSubType", splitReq.getRqSubType()) ;
			    incomingRequest.put("splitLocation", splitReq.getItemLocation()) ;
			    incomingRequest.put("splitShipToCode", splitReq.getShipToCode()) ;
			    incomingRequest.put("splitPcardExpires",splitReq.getPcardExpires()) ;
			    incomingRequest.put("splitPcardNumber",splitReq.getPcardNumber()) ;
			    incomingRequest.put("splitPcardHolder",splitReq.getPcardHolder()) ;
			    incomingRequest.put("splitIcRevisedOrder", splitReq.getIcRevisedOrder().toString()) ;

				// Renumber for saveas
				/*for (int il=0; il < reqLineList.size(); il++) {
					RequisitionLine reqLine = (RequisitionLine) reqLineList.get(il);
					if (createdFrom != null && createdFrom.equalsIgnoreCase("SOURCING")) {
//							reqLine.setItemLocation(itemLocation) ;
					}

					//reqLine.setLineNumber(new BigDecimal(il+1)) ;
					incomingRequest.put("newRequisitionLine_lineNumber", new BigDecimal(il+1).toString()) ;
					//reqLine.setReqType(splitReq.getRequisitionType()) ;
				}*/

				incomingRequest.put("RequisitionHeader_icReqHeader",oReqHeader.getIcReqHeader().toString()) ;
				incomingRequest.put("allocatedTotal","0") ;
				incomingRequest.put("requisitionLineList",reqLineList) ;

				process.executeProcess(incomingRequest);
				RequisitionHeader newReqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
				newReqHeader.setRequisitionLineList(reqLineList);
				reqSplitList.add(newReqHeader);

				incomingRequest.put("requisitionHeader",oReqHeader);

				if (process.getStatus() < Status.SUCCEEDED) {
					throw new Exception("Intelligent Requisition Split process failed.");
				}

			}

			incomingRequest.put("RequisitionHeader_icReqHeader",oReqHeader.getIcReqHeader().toString()) ;
			incomingRequest.put("requisitionHeader",oReqHeader) ;
			incomingRequest.put("requisitionLineList",oReqLineList) ;
			incomingRequest.put("splitRequisitionList", reqSplitList);
			incomingRequest.put("newStatus",DocumentStatus.HISTORY) ;

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}