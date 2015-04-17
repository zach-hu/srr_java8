package com.tsa.puridiom.inspectionheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.InspectionHeaderPK;
import com.tsa.puridiom.entity.PoLine ;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.math.BigDecimal;
import java.util.*;

public class InspectionHeaderReceiptUpdate extends Task
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

			inspectionHeader.setInspectorId(HiltonUtility.ckNull(receiptLine.getInspectorAssigned()) );
			inspectionHeader.setEngineerId(HiltonUtility.ckNull(receiptLine.getEngineerAssigned()));

			inspectionHeader.setPurchaseSpecs(HiltonUtility.ckNull(poLine.getUdf1Code()));
			inspectionHeader.setArea(HiltonUtility.ckNull(receiptLine.getInspArea()));
			inspectionHeader.setStorage(HiltonUtility.ckNull(receiptLine.getInspStorage()));
			inspectionHeader.setLocation(HiltonUtility.ckNull(receiptLine.getInspLocation()));
//			inspectionHeader.setApprDt(originalInspectionHeader.getApprDt());
			inspectionHeader.setDateEntered(d_today);
			inspectionHeader.setStatus(DocumentStatus.INSP_PENDING);
			inspectionHeader.setLastChange(d_today);
			inspectionHeader.setLastChangeBy(userId);
			inspectionHeader.setIcRecLine(receiptLine.getIcRecLine()) ;

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