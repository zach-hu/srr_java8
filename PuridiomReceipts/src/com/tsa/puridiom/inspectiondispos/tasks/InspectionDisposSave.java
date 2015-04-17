package com.tsa.puridiom.inspectiondispos.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.InspectionDispos;
import com.tsa.puridiom.entity.InspectionMte;
import com.tsa.puridiom.entity.InspectionMtePK;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;

public class InspectionDisposSave extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			String icRecHeader = (String) incomingRequest.get("InspectionDispos_icRecHeader") ;
			String icRecLine = (String) incomingRequest.get("InspectionDispos_icRecLine") ;
			String icMsrLine = (String) incomingRequest.get("InspectionDispos_icMsrLine") ;
			String icInspNo = (String) incomingRequest.get("InspectionDispos_icInspNo") ;
			String userId = (String) incomingRequest.get("userId") ;
			String organizationId = (String) incomingRequest.get("organizationId") ;
	        String userDateFormat = (String) incomingRequest.get("userDateFormat");
			String icInspDiscrep = (String) incomingRequest.get("InspectionDispos_icInspDiscrep");
			ReceiptLine recLine = (ReceiptLine) incomingRequest.get("receiptLine") ;
			ReceiptHeader recHeader = (ReceiptHeader) incomingRequest.get("receiptHeader") ;

			List inspectionDisposList = (List) incomingRequest.get("inspectionDisposList") ;
			if (inspectionDisposList == null) inspectionDisposList = new ArrayList() ;

	        if (HiltonUtility.isEmpty(userDateFormat)) {
	            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
	        }

			int count = 0;

			if (incomingRequest.containsKey("InspectionDispos_disposition")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("inspection-dispos-add.xml");

				Object dispTypeObj = incomingRequest.get("InspectionDispos_dispType");
				Object dispositionObj = incomingRequest.get("InspectionDispos_disposition");
				Object descriptionObj = incomingRequest.get("InspectionDispos_description");
				Object dispClosedObj = incomingRequest.get("InspectionDispos_dispClosed");
//				Object respGroupObj = incomingRequest.get("InspectionDispos_respGroup");
				Object dispQuantityObj = incomingRequest.get("InspectionDispos_dispQuantity");
				Object statusObj = incomingRequest.get("InspectionDispos_status");


				if (dispTypeObj instanceof String[]) {
					int	arraySize = ((String[]) dispTypeObj).length;

					for (int i=0; i < arraySize; i++) {
						String dispType = ((String[]) dispTypeObj)[i] ;
						String disposition = ((String[]) dispositionObj)[i];
						String description = ((String[]) descriptionObj)[i];
//						String respGroup = ((String[]) respGroupObj)[i] ;
						String dispQuantity = ((String[]) dispQuantityObj)[i];
						String dispClosed = ((String[]) dispClosedObj)[i];

						if ( ! HiltonUtility.isEmpty(dispType) || ! HiltonUtility.isEmpty(disposition)) {
							count++ ;
							List historyLogList = new ArrayList() ;
							Map updateParameters = new HashMap();
							updateParameters.put("userId", incomingRequest.get("userId"));
							updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
							updateParameters.put("organizationId",organizationId);
							updateParameters.put("dbsession", incomingRequest.get("dbsession"));
							updateParameters.put("InspectionDispos_icInspNo",icInspNo) ;
							updateParameters.put("InspectionDispos_icMsrLine",icMsrLine) ;
							updateParameters.put("InspectionDispos_icInspDiscrep",icInspDiscrep) ;
							updateParameters.put("InspectionDispos_icRecHeader",icRecHeader) ;
							updateParameters.put("InspectionDispos_icRecLine",icRecLine) ;
							updateParameters.put("InspectionDispos_keySequence", Integer.toString(count)) ;
							updateParameters.put("InspectionDispos_lastChange", Dates.today(userDateFormat));
							updateParameters.put("InspectionDispos_lastChangedBy", userId);

							updateParameters.put("InspectionDispos_dispType", dispType) ;
//							updateParameters.put("InspectionDispos_respGroup", respGroup) ;
							updateParameters.put("InspectionDispos_disposition", disposition) ;
							updateParameters.put("InspectionDispos_description", description) ;
							updateParameters.put("InspectionDispos_dispQuantity",dispQuantity) ;
							updateParameters.put("InspectionDispos_dispClosed", dispClosed) ;

							updateParameters.put("InspectionHistory_area", incomingRequest.get("InspectionHeader_area"));
							updateParameters.put("InspectionHistory_storage", incomingRequest.get("InspectionHeader_storage"));
							updateParameters.put("InspectionHistory_location", incomingRequest.get("InspectionHeader_location"));

							updateParameters.put("historyLogList", this.getHistory(organizationId, inspectionDisposList, recHeader, recLine, dispQuantity, dispClosed, count)) ;

							process.executeProcess(updateParameters);
						}
					}
				} else {
					String dispType = (String) dispTypeObj ;
					String disposition = (String) dispositionObj;
					String description = (String) descriptionObj;
	//				String respGroup = (String) respGroupObj ;
					String dispQuantity = (String) dispQuantityObj;
					String dispClosed = (String) dispClosedObj;

					if ( ! HiltonUtility.isEmpty(dispType) || ! HiltonUtility.isEmpty(disposition)) {
						List historyLogList = new ArrayList() ;
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId",organizationId);
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("InspectionDispos_icInspNo",icInspNo) ;
						updateParameters.put("InspectionDispos_icMsrLine",icMsrLine) ;
						updateParameters.put("InspectionDispos_icRecHeader",icRecHeader) ;
						updateParameters.put("InspectionDispos_icInspDiscrep",icInspDiscrep) ;
						updateParameters.put("InspectionDispos_icRecLine",icRecLine) ;
						updateParameters.put("InspectionDispos_keySequence", "1") ;
						updateParameters.put("InspectionDispos_lastChange", Dates.today(userDateFormat));
						updateParameters.put("InspectionDispos_lastChangedBy", userId);

						updateParameters.put("InspectionDispos_dispType", dispType) ;
//						updateParameters.put("InspectionDispos_respGroup", respGroup) ;
						updateParameters.put("InspectionDispos_description", description) ;
						updateParameters.put("InspectionDispos_disposition", disposition) ;
						updateParameters.put("InspectionDispos_dispQuantity",dispQuantity) ;
						updateParameters.put("InspectionDispos_dispClosed", dispClosed) ;

						updateParameters.put("InspectionHistory_area", incomingRequest.get("InspectionHeader_area"));
						updateParameters.put("InspectionHistory_storage", incomingRequest.get("InspectionHeader_storage"));
						updateParameters.put("InspectionHistory_location", incomingRequest.get("InspectionHeader_location"));

						updateParameters.put("historyLogList", this.getHistory(organizationId, inspectionDisposList, recHeader, recLine, dispQuantity, dispClosed, 1)) ;

						process.executeProcess(updateParameters);
					}
				}
			}

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}


	private List getHistory(String orgId, List inspectionDisposList, ReceiptHeader recHeader, ReceiptLine recLine, String quantity, String dispClosed, int  seq)
	{
		List historyLogList = new ArrayList() ;
		if (seq > inspectionDisposList.size()) {
			historyLogList.add(this.createHistory(recHeader, recLine, "Created Disposition  line " + seq + " Quantity " + quantity +",  Closed Date " + dispClosed)) ;
		} else {
			for (int ix = 0; ix < inspectionDisposList.size();ix++)  {
				InspectionDispos inspDispos = (InspectionDispos) inspectionDisposList.get(ix) ;
				if (inspDispos.getComp_id().getKeySequence().compareTo( new BigDecimal(seq) ) == 0) {
					String oldType = inspDispos.getDispType() ;
					String oldDisposition = inspDispos.getDisposition() ;
					BigDecimal oldQty = inspDispos.getDispQuantity() ;
					BigDecimal newQty = new BigDecimal(quantity) ;
					if (oldQty.compareTo(newQty) != 0) {
						historyLogList.add(this.createHistory(recHeader, recLine, "Updated Discrepancy line " + seq + " quantity changed from " + oldQty + " to " + newQty )) ;
					}
					Date oldClosed =  inspDispos.getDispClosed() ;
					String oldDispClosed = HiltonUtility.getFormattedDate(oldClosed, orgId) ;
					if (oldDispClosed ==  null) oldDispClosed = "" ;
					if (! HiltonUtility.isEmpty(dispClosed)) {
							if (dispClosed.compareTo(oldDispClosed) != 0)  {
								historyLogList.add(this.createHistory(recHeader, recLine, "Updated Discrepancy line " + seq + " closed date changed to " + dispClosed )) ;
							}
					}
				}
			}
		}
		return  historyLogList ;
	}

	private HistoryLog createHistory(ReceiptHeader recHeader, ReceiptLine recLine, String msg) {

		HistoryLog historyLog = new HistoryLog() ;

		historyLog.setIcHeader(recLine.getIcRecHeader()) ;
		historyLog.setIcLineHistory(recLine.getIcLineHistory()) ;
		historyLog.setIcHeaderHistory(recHeader.getIcHeaderHistory()) ;
		historyLog.setIcLine(recLine.getIcRecLine()) ;
		historyLog.setDescription(msg) ;
		historyLog.setDoctype("INS") ;
		historyLog.setStatus(recLine.getStatus()) ;

		return historyLog ;
	}

}