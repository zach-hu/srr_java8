package com.tsa.puridiom.inspection.tasks;

import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.InspectionLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class RecInspectionHeaderRetrieveSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null ;

		ReceiptLine rl = (ReceiptLine) incomingRequest.get("receiptLine") ;
		if (rl != null)
		{
			incomingRequest.put("InspectionLine_icMsrLine", rl.getIcLineHistory()) ;
			incomingRequest.put("InspectionLine_icRecLine", rl.getIcRecLine()) ;
			incomingRequest.put("InspectionHistory_icRecLine",rl.getIcRecLine().toString()) ;
			incomingRequest.put("StdTable_tableType","RECSTAT") ;
		}
		else{
			incomingRequest.put("InspectionHistory_icRecLine", "-1") ;
		}

		return result ;
	}
}