package com.tsa.puridiom.inspectionheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.InspectionHeaderPK;
import com.tsa.puridiom.entity.PoLine ;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.math.BigDecimal;
import java.util.*;

public class InspectionHeaderAssignmentUpdate extends Task
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
            InspectionHeader inspectionHeader = (InspectionHeader) incomingRequest.get("inspectionHeader") ;

			inspectionHeader.setInspectorId(receiptLine.getInspectorAssigned() );
			inspectionHeader.setEngineerId(receiptLine.getEngineerAssigned());

			inspectionHeader.setPurchaseSpecs(poLine.getUdf1Code());
//			inspectionHeader.setArea(receiptLine.getInspArea());
//			inspectionHeader.setStorage(receiptLine.getInspStorage());
//			inspectionHeader.setLocation(receiptLine.getInspLocation());
//			inspectionHeader.setApprDt(originalInspectionHeader.getApprDt());
			inspectionHeader.setDateEntered(d_today);
			inspectionHeader.setStatus(DocumentStatus.INSP_PENDING);
			inspectionHeader.setLastChange(d_today);
			inspectionHeader.setLastChangeBy(userId);
			inspectionHeader.setIcRecLine(receiptLine.getIcRecLine()) ;
			inspectionHeader.setPoNumber(poLine.getPoNumber());
			inspectionHeader.setIcPoLine(poLine.getIcPoLine()) ;

			incomingRequest.put("inspectionHeader", inspectionHeader);

			InspectionHeaderUpdate updateTask = new InspectionHeaderUpdate() ;
			inspectionHeader = (InspectionHeader) updateTask.executeTask(incomingRequest) ;
			this.setStatus(updateTask.getStatus()) ;

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