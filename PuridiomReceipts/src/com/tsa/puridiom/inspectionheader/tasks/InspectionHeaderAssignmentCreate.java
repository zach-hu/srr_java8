package com.tsa.puridiom.inspectionheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.InspectionHeaderPK;
import com.tsa.puridiom.entity.PoLine ;
import com.tsa.puridiom.inspectionline.tasks.InspectionLineRetrieveByIcInspNo;
import com.tsa.puridiom.inspectionline.tasks.InspectionLineSaveas;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.math.BigDecimal;
import java.util.*;

public class InspectionHeaderAssignmentCreate extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PoLine poLine = (PoLine) incomingRequest.get("poLine") ;
			if (poLine == null) poLine = new  PoLine() ;

			ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine") ;
			if (receiptLine ==  null) receiptLine = new ReceiptLine() ;

			String	userId = (String) incomingRequest.get("userId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            Date d_today = Dates.getCurrentDate(userTimeZone);

			/* Expects incoming request to contain inspectionHeader */
			InspectionHeaderPK comp_id = new InspectionHeaderPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InspectionHeader	originalInspectionHeader = (InspectionHeader) incomingRequest.get("inspectionHeader");
			InspectionHeader	inspectionHeader = new InspectionHeader();

			comp_id.setIcInspNo(new BigDecimal(ukg.getUniqueKey()));
			comp_id.setIcMsrLine(receiptLine.getIcLineHistory());
			inspectionHeader.setInspectType(originalInspectionHeader.getInspectType());

			inspectionHeader.setInspectorId(receiptLine.getInspectorAssigned() );
			inspectionHeader.setEngineerId(receiptLine.getEngineerAssigned());

			inspectionHeader.setRemoteInspId(originalInspectionHeader.getRemoteInspId());
			inspectionHeader.setPurchaseSpecs(poLine.getUdf1Code());
//			inspectionHeader.setArea(receiptLine.getInspArea());
//			inspectionHeader.setStorage(receiptLine.getInspStorage());
//			inspectionHeader.setLocation(receiptLine.getInspLocation());
			inspectionHeader.setStandardCode(originalInspectionHeader.getStandardCode());
			inspectionHeader.setUdf01(originalInspectionHeader.getUdf01());
			inspectionHeader.setUdf02(originalInspectionHeader.getUdf02());
			inspectionHeader.setUdf03(originalInspectionHeader.getUdf03());
			inspectionHeader.setUdf04(originalInspectionHeader.getUdf04());
			inspectionHeader.setUdf05(originalInspectionHeader.getUdf05());
			inspectionHeader.setInspType(originalInspectionHeader.getInspType());
//			inspectionHeader.setApprDt(originalInspectionHeader.getApprDt());
			inspectionHeader.setDateEntered(d_today);
			inspectionHeader.setAssignedDate(d_today) ;
			inspectionHeader.setStatus(DocumentStatus.INSP_PENDING);
			inspectionHeader.setOwner(userId);
			inspectionHeader.setLastChange(d_today);
			inspectionHeader.setLastChangeBy(userId);
			inspectionHeader.setIcRecLine(receiptLine.getIcRecLine()) ;
			inspectionHeader.setPoNumber(poLine.getPoNumber());
			inspectionHeader.setIcPoLine(poLine.getIcPoLine()) ;
			inspectionHeader.setCgdNo(originalInspectionHeader.getCgdNo()) ;
//			inspectionHeader.setCgdRev(originalInspectionHeader.getCgdRev()) ;

			inspectionHeader.setComp_id(comp_id);
			
			incomingRequest.put("inspectionHeader", inspectionHeader);

			InspectionHeaderAdd addTask = new InspectionHeaderAdd();
			inspectionHeader = (InspectionHeader) addTask.executeTask(incomingRequest);

			// Save Inspection Lines
			InspectionLineRetrieveByIcInspNo inspectionLineRetrieve = new InspectionLineRetrieveByIcInspNo();
			Map newIncomingRequest = new HashMap();
			newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
			newIncomingRequest.put("InspectionLine_icInspNo", originalInspectionHeader.getComp_id().getIcInspNo());
			
			List inspectionLineList = (List)inspectionLineRetrieve.executeTask(newIncomingRequest);
			if(inspectionLineList == null) inspectionLineList = new ArrayList();
			
			for (int i = 0; i < inspectionLineList.size(); i++) {
				InspectionLineSaveas inspectionLineSave = new InspectionLineSaveas(); 
				newIncomingRequest.put("inspectionHeader", inspectionHeader);
				newIncomingRequest.put("inspectionLine", inspectionLineList.get(i));
				newIncomingRequest.put("userId", userId);
				newIncomingRequest.put("userTimeZone", userTimeZone);
				
				inspectionLineSave.executeTask(newIncomingRequest);
			}

			this.setStatus(addTask.getStatus()) ;

			result = inspectionHeader;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}