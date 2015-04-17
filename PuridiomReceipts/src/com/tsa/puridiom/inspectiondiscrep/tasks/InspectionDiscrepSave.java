package com.tsa.puridiom.inspectiondiscrep.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.InspectionDiscrep;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

public class InspectionDiscrepSave extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			String icRecHeader = (String) incomingRequest.get("InspectionDiscrep_icRecHeader") ;
			String icRecLine = (String) incomingRequest.get("InspectionDiscrep_icRecLine") ;
			String icMsrLine = (String) incomingRequest.get("InspectionDiscrep_icMsrLine") ;
			String icInspNo = (String) incomingRequest.get("InspectionDiscrep_icInspNo") ;
			String userId = (String) incomingRequest.get("userId") ;
			String organizationId = (String) incomingRequest.get("organizationId") ;
	        String userDateFormat = (String) incomingRequest.get("userDateFormat");

	        List  inspectionDiscrepList = (List) incomingRequest.get("inspectionDiscrepList") ;
	        if (inspectionDiscrepList == null) inspectionDiscrepList = new ArrayList() ;
			ReceiptLine recLine = (ReceiptLine) incomingRequest.get("receiptLine") ;
			ReceiptHeader recHeader = (ReceiptHeader) incomingRequest.get("receiptHeader") ;

	        if (HiltonUtility.isEmpty(userDateFormat)) {
	            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
	        }

			int count = 0;

			if (incomingRequest.containsKey("InspectionDiscrep_inspectCode")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("inspection-discrep-add.xml");

				Object inspectCodeObj = incomingRequest.get("InspectionDiscrep_inspectCode");
				Object inspDescriptionObj = incomingRequest.get("InspectionDiscrep_inspDescription");
				Object inspStartDateObj = incomingRequest.get("InspectionDiscrep_inspStartDate");
				Object inspRequirementsObj = incomingRequest.get("InspectionDiscrep_inspRequirements");
				Object inspQuantityObj = incomingRequest.get("InspectionDiscrep_inspQuantity");
				Object statusObj = incomingRequest.get("InspectionDiscrep_status");
				Object icInspDiscrepObj = incomingRequest.get("InspectionDiscrep_icInspDiscrep");


				if (inspectCodeObj instanceof String[]) {
					int	arraySize = ((String[]) inspectCodeObj).length;

					for (int i=0; i < arraySize; i++) {
						String inspectCode = ((String[]) inspectCodeObj)[i] ;
						String inspDescription = ((String[]) inspDescriptionObj)[i];
						String inspRequirements = ((String[]) inspRequirementsObj)[i] ;
						String inspQuantity = ((String[]) inspQuantityObj)[i];
						String status = ((String[]) statusObj)[i];
						String inspStartDate = ((String[]) inspStartDateObj)[i];
						String icInspDiscrep = ((String[]) icInspDiscrepObj)[i];

						if ( ! HiltonUtility.isEmpty(inspectCode) || ! HiltonUtility.isEmpty(inspDescription)) {
							count++ ;
							Map updateParameters = new HashMap();
							updateParameters.put("userId", incomingRequest.get("userId"));
							updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
							updateParameters.put("organizationId",organizationId);
							updateParameters.put("dbsession", incomingRequest.get("dbsession"));
							updateParameters.put("InspectionDiscrep_icInspNo",icInspNo) ;
							updateParameters.put("InspectionDiscrep_icMsrLine",icMsrLine) ;
							updateParameters.put("InspectionDiscrep_icInspDiscrep",icInspDiscrep) ;
							updateParameters.put("InspectionDiscrep_icRecHeader",icRecHeader) ;
							updateParameters.put("InspectionDiscrep_icRecLine",icRecLine) ;
							updateParameters.put("InspectionDiscrep_keySequence", Integer.toString(count)) ;
							updateParameters.put("InspectionDiscrep_lastChange", Dates.today(userDateFormat));
							updateParameters.put("InspectionDiscrep_lastChangedBy", userId);

							updateParameters.put("InspectionDiscrep_inspectCode", inspectCode) ;
							updateParameters.put("InspectionDiscrep_inspRequirements", inspRequirements) ;
							updateParameters.put("InspectionDiscrep_inspDescription", inspDescription) ;
							updateParameters.put("InspectionDiscrep_inspQuantity",inspQuantity) ;
							updateParameters.put("InspectionDiscrep_status", status) ;
							updateParameters.put("InspectionDiscrep_inspStartDate", inspStartDate) ;

							updateParameters.put("InspectionHistory_area", incomingRequest.get("InspectionHeader_area"));
							updateParameters.put("InspectionHistory_storage", incomingRequest.get("InspectionHeader_storage"));
							updateParameters.put("InspectionHistory_location", incomingRequest.get("InspectionHeader_location"));

							updateParameters.put("historyLogList", this.getHistory(inspectionDiscrepList, recHeader, recLine, inspQuantity, inspectCode, inspRequirements, inspDescription, status, inspStartDate, i + 1)) ;

							process.executeProcess(updateParameters);
						}
					}
				} else {
					String inspectCode = (String) inspectCodeObj ;
					String inspDescription = (String) inspDescriptionObj;
					String inspRequirements = (String) inspRequirementsObj ;
					String inspQuantity = (String) inspQuantityObj;
					String status = (String) statusObj;
					String inspStartDate = (String) inspStartDateObj;
					String icInspDiscrep = (String) icInspDiscrepObj;

					if ( ! HiltonUtility.isEmpty(inspectCode) || ! HiltonUtility.isEmpty(inspDescription)) {
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId",organizationId);
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("InspectionDiscrep_icInspNo",icInspNo) ;
						updateParameters.put("InspectionDiscrep_icMsrLine",icMsrLine) ;
						updateParameters.put("InspectionDiscrep_icRecHeader",icRecHeader) ;
						updateParameters.put("InspectionDiscrep_icInspDiscrep",icInspDiscrep) ;
						updateParameters.put("InspectionDiscrep_icRecLine",icRecLine) ;
						updateParameters.put("InspectionDiscrep_keySequence", "1") ;
						updateParameters.put("InspectionDiscrep_lastChange", Dates.today(userDateFormat));
						updateParameters.put("InspectionDiscrep_lastChangedBy", userId);

						updateParameters.put("InspectionDiscrep_inspectCode", inspectCode) ;
						updateParameters.put("InspectionDiscrep_inspRequirements", inspRequirements) ;
						updateParameters.put("InspectionDiscrep_inspDescription", inspDescription) ;
						updateParameters.put("InspectionDiscrep_inspQuantity",inspQuantity) ;
						updateParameters.put("InspectionDiscrep_status", status) ;
						updateParameters.put("InspectionDiscrep_inspStartDate", inspStartDate) ;

						updateParameters.put("InspectionHistory_area", incomingRequest.get("InspectionHeader_area"));
						updateParameters.put("InspectionHistory_storage", incomingRequest.get("InspectionHeader_storage"));
						updateParameters.put("InspectionHistory_location", incomingRequest.get("InspectionHeader_location"));

						updateParameters.put("historyLogList", this.getHistory(inspectionDiscrepList, recHeader, recLine, inspQuantity, inspectCode, inspRequirements, inspDescription, status, inspStartDate, 1)) ;

						process.executeProcess(updateParameters);
					}
				}
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			Log.error(this, e);
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

	private List getHistory(List inspectionDiscrepList, ReceiptHeader recHeader, ReceiptLine recLine, String quantity, String inspCode, String inspRequirements, String inspDescription, String status, String startDate,  int  seq)
	{
		List historyLogList = new ArrayList() ;
		if (seq > inspectionDiscrepList.size()) {
			historyLogList.add(this.createHistory(recHeader, recLine, "Created Discrepancy  line " + seq + " Quantity " + quantity +", Status " + status + ", Startdate " + startDate)) ;

		} else {
			for (int ix = 0; ix < inspectionDiscrepList.size();ix++)  {
				InspectionDiscrep inspDiscrep = (InspectionDiscrep) inspectionDiscrepList.get(ix) ;
				if (inspDiscrep.getComp_id().getKeySequence().compareTo( new BigDecimal(seq) ) == 0) {
					String oldCode = inspDiscrep.getInspectCode() ;
					String oldStatus = inspDiscrep.getStatus();
					Date oldStartDate = inspDiscrep.getInspStartDate() ;
					BigDecimal oldQty = inspDiscrep.getInspQuantity() ;
					BigDecimal newQty = new BigDecimal(quantity) ;
					if (oldQty.compareTo(newQty) != 0) {
						historyLogList.add(this.createHistory(recHeader, recLine, "Updated Discrepancy line " + seq + " quantity changed from " + oldQty + " to " + newQty )) ;
					}

					if (oldStatus.compareTo(status) != 0) {
						historyLogList.add(this.createHistory(recHeader,  recLine,"Updated Discrepancy line " + seq + " status changed from " + oldStatus + " to " + status)) ;
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
